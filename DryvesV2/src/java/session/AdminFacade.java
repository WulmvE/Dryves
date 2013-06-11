package session;

import entity.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author patrick
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
    
    /**
     * Executes different type of statistical SQL Queries, based upon which type is requested
     * @param type
     * @return List<Object[]> 
     */
    public List<Object[]> getStats(String type) {

        String query = "";

        //Several SQL queries. 'type' determines which one is chosen
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
        
        //Entity manager executes the chosen query. Result are stored in a List and returned
        Query q = em.createNativeQuery(query);
        List<Object[]> stats = q.getResultList();
        return stats;
    }
}
