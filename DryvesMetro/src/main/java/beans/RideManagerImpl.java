package beans;

import entity.Ride;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vincent
 */

@Stateless
public class RideManagerImpl {
@PersistenceContext(unitName = "PU")
    EntityManager em;
    
    
    public List<Ride> findRides(){
        System.out.println(em.find(Ride.class, 2).getAskingPrice());
     List<Ride> rides = em.createNativeQuery("select * from ride",Ride.class).getResultList();
     return rides;
    }
    
    
}
