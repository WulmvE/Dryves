/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Dryver;
import entity.Friend;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hctung
 */
@Stateless
public class FriendFacade extends AbstractFacade<Friend> {
    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Friend> findByDryver(Dryver dryver) {
        return em.createNamedQuery("Friend.findByIdMember").setParameter("idMember",dryver).getResultList();
    }
    
    public FriendFacade() {
        super(Friend.class);
    }
    
}
