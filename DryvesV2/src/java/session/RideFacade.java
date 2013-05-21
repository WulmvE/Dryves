/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Ride;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hctung
 */
@Stateless
public class RideFacade extends AbstractFacade<Ride> {
    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RideFacade() {
        super(Ride.class);
    }
    
    public List<Ride> searchedRides(String van , String naar , String date){
        String query = "select * from Ride where startLocation ='"+van+"'";
        List<Ride> rides = new ArrayList<Ride>();
        
        try{
       rides = em.createNativeQuery(query, Ride.class).getResultList();
        for (Ride ride : rides) {
            System.out.println(ride.getAskingPrice());
           
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
       return null;
    }
    }

