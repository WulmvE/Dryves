/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Percentage;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Patrick
 */
@Stateless
public class PercentageFacade extends AbstractFacade<Percentage> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PercentageFacade() {
        super(Percentage.class);
    }

    @Override
    public List<Percentage> findAll() {
        return em.createNamedQuery("Percentage.findAll").getResultList();
    }
    
    public Percentage findByDateToIsNull() {
        return (Percentage) em.createNamedQuery("Percentage.findByDateToIsNull").getSingleResult();
    }
}
