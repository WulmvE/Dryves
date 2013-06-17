/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Dryver;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hctung
 */
@Stateless
public class DryverFacade extends AbstractFacade<Dryver> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DryverFacade() {
        super(Dryver.class);
    }

    public int createDryver(String alias, String city, String email, String firstName, String adjective, String lastName, String password, String gender, String birthDate) {

        Dryver dryver = new Dryver();
        DateFormat format = new SimpleDateFormat("DD-MM-yyyy");

        dryver.setAlias(alias);
        dryver.setFirstName(firstName);
        dryver.setAdjective(adjective);
        dryver.setLastName(lastName);
        dryver.setEmail(email);
        dryver.setCity(city);
        dryver.setPassword(password);
        dryver.setGender(gender);

        //parse the birthDate string to an actual date
        try {
            dryver.setBirthDate(format.parse(birthDate));
        }
        catch (ParseException ex) {
            Logger.getLogger(DryverFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        dryver.setAvgRating(3.0);


        em.persist(dryver);
        em.flush();
        return dryver.getIdMember();
    }

    public Dryver findByDryverId(int dryverId) {
        return (Dryver) em.createNamedQuery("Dryver.findByIdMember").setParameter("idMember", dryverId).getSingleResult();
    }

    public Dryver findByAlias(String alias) {
        return (Dryver) em.createNamedQuery("Dryver.findByAlias").setParameter("alias", alias).getSingleResult();
    }

    public List<Dryver> findByFirstName(String firstName) {
        return em.createNamedQuery("Dryver.findByFirstName").setParameter("firstName", firstName).getResultList();
    }

    public List<Dryver> findByLastName(String lastName) {
        return em.createNamedQuery("Dryver.findByLastName").setParameter("lastName", lastName).getResultList();
    }

    public List<Dryver> findByEmail(String email) {
        return em.createNamedQuery("Dryver.findByEmail").setParameter("email", email).getResultList();
    }

    public List<Dryver> findByFirstNameLastName(String firstName, String lastName) {
        return em.createNamedQuery("Dryver.findByFirstNameLastName").setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
    }

    public List<Dryver> findByFirstNameEmail(String firstName, String email) {
        return em.createNamedQuery("Dryver.findByFirstNameEmail").setParameter("firstName", firstName).setParameter("email", email).getResultList();
    }

    public List<Dryver> findByLastNameEmail(String lastName, String email) {
        return em.createNamedQuery("Dryver.findByLastNameEmail").setParameter("lastName", lastName).setParameter("email", email).getResultList();
    }

    public List<Dryver> findByIsBlocked(String lastName, String email) {
        return em.createNamedQuery("Dryver.findByIsBlocked").getResultList();
    }
}
