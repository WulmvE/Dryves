package entity;
// Generated 22-apr-2013 22:04:37 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Car generated by hbm2java
 */
@Entity
@Table(name="car"
    ,catalog="dryves"
)
public class Car  implements java.io.Serializable {


     private Integer idCar;
     private Member member;
     private String brand;
     private int numSeats;
     private Set<Ride> rides = new HashSet<Ride>(0);

    public Car() {
    }

	
    public Car(Member member, String brand, int numSeats) {
        this.member = member;
        this.brand = brand;
        this.numSeats = numSeats;
    }
    public Car(Member member, String brand, int numSeats, Set<Ride> rides) {
       this.member = member;
       this.brand = brand;
       this.numSeats = numSeats;
       this.rides = rides;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idCar", unique=true, nullable=false)
    public Integer getIdCar() {
        return this.idCar;
    }
    
    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idMember", nullable=false)
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    @Column(name="brand", nullable=false, length=45)
    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    @Column(name="numSeats", nullable=false)
    public int getNumSeats() {
        return this.numSeats;
    }
    
    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="car")
    public Set<Ride> getRides() {
        return this.rides;
    }
    
    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }




}


