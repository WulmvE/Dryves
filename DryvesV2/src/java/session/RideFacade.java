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

    public List<Ride> searchRideByStart(String van) {
        return em.createNamedQuery("Ride.findByStartLocation").setParameter("startLocation", van).getResultList();
    }

    public List<Ride> searchByEnd(String naar) {
        return em.createNamedQuery("Ride.findByEndLocation").setParameter("endLocation", naar).getResultList();
    }

    public List<Ride> searchByDate(String op) {
        return em.createNamedQuery("Ride.findByDepartureDate").setParameter("departureDate", op).getResultList();
    }

    public List<Ride> searchRideByStartEnd(String van, String naar) {
        return em.createNamedQuery("Ride.findByStartEnd").setParameter("startLocation", van).setParameter("endLocation", naar).getResultList();
    }

    public List<Ride> searchRideByStartDate(String van, String op) {
        return em.createNamedQuery("Ride.findByStartDate").setParameter("startLocation", van).setParameter("departureDate", op).getResultList();
    }

    public List<Ride> searchRideByEndDate(String naar, String op) {
        return em.createNamedQuery("Ride.findByEndDate").setParameter("endLocation", naar).setParameter("departureDate", op).getResultList();
    }

    public List<Ride> searchRideByAll(String van, String naar, String op) {
        return em.createNamedQuery("Ride.findByAll").setParameter("startLocation", van).setParameter("endLocation", naar).setParameter("departureDate", op).getResultList();
    }
    
}
