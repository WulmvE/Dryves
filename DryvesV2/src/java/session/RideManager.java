/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import entity.Dryver;
import entity.Ride;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vincent
 */
@Stateless
public class RideManager {
    
     @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    public int placeRide(String startLocation, String endLocation, Dryver dryver, Car car) {   
        Ride ride = new Ride();
        ride.setStartLocation(startLocation);
        System.out.println("startlocatie" + startLocation);
        ride.setEndLocation(endLocation);
        System.out.println("endLocatie" + endLocation);
        Date date = new Date();
        System.out.println(date);
        ride.setDepartureDate(date);
        Date time = new Date();
        ride.setDepartureTime(time);
        ride.setAskingPrice(10.0);
        ride.setSeatsAvailable("2");
        ride.setStatus(true);  
        ride.setIdMember(dryver);
        ride.setIdCar(car);
        
        em.persist(ride);
        em.flush();
        return 1;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
