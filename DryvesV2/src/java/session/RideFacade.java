/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import entity.Dryver;
import entity.Ride;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Ride> searchRideByStart(String van) {
        return em.createNamedQuery("Ride.findByStartLocation").setParameter("startLocation", van).getResultList();
    }

    public List<Ride> searchByEnd(String naar) {
        return em.createNamedQuery("Ride.findByEndLocation").setParameter("endLocation", naar).getResultList();
    }

    public List<Ride> searchByDate(Date op) {
        return em.createNamedQuery("Ride.findByDepartureDate").setParameter("departureDate", op).getResultList();
    }

    public List<Ride> searchRideByStartEnd(String van, String naar) {
        return em.createNamedQuery("Ride.findByStartEnd").setParameter("startLocation", van).setParameter("endLocation", naar).getResultList();
    }

    public List<Ride> searchRideByStartDate(String van, Date op) {
        return em.createNamedQuery("Ride.findByStartDate").setParameter("startLocation", van).setParameter("departureDate", op).getResultList();
    }

    public List<Ride> searchRideByEndDate(String naar, Date op) {
        return em.createNamedQuery("Ride.findByEndDate").setParameter("endLocation", naar).setParameter("departureDate", op).getResultList();
    }

    public List<Ride> searchRideByAll(String van, String naar, Date op) {
        return em.createNamedQuery("Ride.findByAll").setParameter("startLocation", van).setParameter("endLocation", naar).setParameter("departureDate", op).getResultList();
    }

    public List<Ride> findByDryver(Dryver dryver) {
        return em.createNamedQuery("Ride.findByIdMember").setParameter("idMember", dryver).getResultList();
    }

    public List<Ride> findByNegotiationIdMember(Dryver dryver) {
        return em.createNamedQuery("Ride.findByNegotiationIdMember").setParameter("idMember", dryver.getIdMember()).getResultList();
    }

    public int placeRide(String startLocation, String endLocation, Dryver dryver, Car car, Date date, int numseats, String price, double distance) {
        Ride ride = new Ride();
        startLocation = trimSearchString(startLocation);
        ride.setStartLocation(startLocation);
        ride.setEndLocation(trimSearchString(endLocation));
        ride.setDepartureDate(date);

        // time not implemented yet
        Date time = new Date();
        ride.setDepartureTime(time);
        ride.setAskingPrice(Double.parseDouble(price));
        ride.setSeatsAvailable(numseats);
        ride.setStatus(false);
        ride.setIdMember(dryver);
        ride.setIdCar(car);
        ride.setDistance(distance);

        em.persist(ride);
        em.flush();
        return ride.getIdRide();
    }

    public String trimSearchString(String searchString) {

        return searchString.replace(", Nederland", "");
    }
}
