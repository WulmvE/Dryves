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
import java.util.Date;
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

    public int persistNewDryver(String alias, String city, String email, String firstName, String lastName, String password, String gender, String birthDate) {

        Dryver dryver = new Dryver();
        DateFormat format = new SimpleDateFormat("MM-DD-yyyy");


        dryver.setAlias(alias);
        dryver.setCity(city);
        dryver.setEmail(email);
        dryver.setFirstName(firstName);
        dryver.setLastName(lastName);
        dryver.setPassword(password);
        dryver.setGender(gender);
        
        //parse the birthDate string to an actual date
        try {
            dryver.setBirthDate(format.parse(birthDate));
        }
        catch (ParseException ex) {
            Logger.getLogger(DryverFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        em.persist(dryver);
        em.flush();
        return dryver.getIdMember();
    }
}
