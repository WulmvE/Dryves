/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import Utils.Stat;
import entity.Admin;
import java.util.AbstractList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hctung
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }

    public List<Object[]> getStats(String type) {

        String query = "";


        if (type.equals("mostCreated")) {

            query = "SELECT M.alias, COUNT(*)AS aantal_ritten\n"
                    + "FROM ride R, member M\n"
                    + "WHERE R.idMember = M.idMember\n"
                    + "GROUP BY R.idmember ORDER BY aantal_ritten desc limit 5";
        }
        else if (type.equals("mostDriven")) {

            query = "SELECT M.alias, COUNT(*) AS aantal_ritten\n"
                    + "FROM negotiation N, member M\n"
                    + "WHERE N.acceptedDriver= 1\n"
                    + "AND N.acceptedPassenger= 1\n"
                    + "AND N.idMember = M.idMember\n"
                    + "GROUP BY N.idMember\n"
                    + "ORDER BY aantal_ritten desc limit 5";

        }
        else if (type.equals("mostDestination")) {
            query = "SELECT R.endLocation, COUNT(*) AS aantal_ritten\n"
                    + "FROM ride R\n"
                    + "GROUP BY R.endLocation\n"
                    + "ORDER BY aantal_ritten desc limit 5";
        }
        else if (type.equals("mostDeparture")) {
            query = "SELECT R.startLocation, COUNT(*) AS aantal_ritten\n"
                    + "FROM ride R\n"
                    + "GROUP BY R.startLocation\n"
                    + "ORDER BY aantal_ritten desc limit 5";

        }
        
        Query q = em.createNativeQuery(query);
        List<Object[]> stats = q.getResultList();

        return stats;
    }
}
