/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hctung
 */
@Entity
@Table(name = "car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByIdCar", query = "SELECT c FROM Car c WHERE c.idCar = :idCar"),
    @NamedQuery(name = "Car.findByBrand", query = "SELECT c FROM Car c WHERE c.brand = :brand"),
    @NamedQuery(name = "Car.findByNumSeats", query = "SELECT c FROM Car c WHERE c.numSeats = :numSeats")})
@Cacheable(false)
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCar")
    private Integer idCar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numSeats")
    private int numSeats;
    @JoinColumn(name = "idMember", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idMember;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCar")
    private List<Ride> rideList;

    public Car() {
    }

    public Car(Integer idCar) {
        this.idCar = idCar;
    }

    public Car(Integer idCar, String brand, int numSeats) {
        this.idCar = idCar;
        this.brand = brand;
        this.numSeats = numSeats;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public Dryver getIdMember() {
        return idMember;
    }

    public void setIdMember(Dryver idMember) {
        this.idMember = idMember;
    }

    @XmlTransient
    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCar != null ? idCar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.idCar == null && other.idCar != null) || (this.idCar != null && !this.idCar.equals(other.idCar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Car[ idCar=" + idCar + " ]";
    }
    
}
