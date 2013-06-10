/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Dryver;
import entity.Negotiation;
import entity.Ride;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hctung
 */
@Stateless
public class NegotiationFacade extends AbstractFacade<Negotiation> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NegotiationFacade() {
        super(Negotiation.class);
    }

    public Negotiation findByIdMemberAndIdRide(int idMember, int idRide) {
        return (Negotiation) em.createNamedQuery("Negotiation.findByIdMemberAndIdRide").setParameter("idMember", idMember).setParameter("idRide", idRide).getSingleResult();
    }

    public void accept(Negotiation negotiation) {
        negotiation.setAcceptedDriver(1);
        em.persist(negotiation);
        em.flush();
    }

    public void createNegotiation(Dryver dryver, Ride ride) {

        Negotiation negotiation = new Negotiation();

        negotiation.setAcceptedDriver(0);
        negotiation.setAcceptedPassenger(1);
        negotiation.setDryver(dryver);
        negotiation.setRide(ride);

        em.persist(negotiation);
        em.flush();
    }
}
