/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import entity.Dryver;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hctung
 */
@Stateless
public class CarFacade extends AbstractFacade<Car> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarFacade() {
        super(Car.class);
    }
    
    public int createCar(String brand, int numSeats, Dryver dryver) {
        Car car = new Car();

        car.setBrand(brand);
        car.setNumSeats(numSeats);
        car.setIdMember(dryver);


        em.persist(car);
        em.flush();
        return car.getIdCar();
}
}
