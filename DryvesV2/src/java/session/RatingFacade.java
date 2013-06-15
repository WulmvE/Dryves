/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Dryver;
import entity.Rating;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hctung
 */
@Stateless
public class RatingFacade extends AbstractFacade<Rating> {
    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Rating> findByIdMember(Dryver dryver){
        return (List<Rating>) em.createNamedQuery("Rating.findByIdMember").setParameter("idMember", dryver).getResultList();
    }
    
    public RatingFacade() {
        super(Rating.class);
    }
    
}
