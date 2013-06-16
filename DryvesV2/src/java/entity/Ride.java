/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hctung
 */
@Entity
@Table(name = "ride")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ride.findAll", query = "SELECT r FROM Ride r"),
    @NamedQuery(name = "Ride.findByAll", query = "SELECT r FROM Ride r WHERE r.startLocation = :startLocation AND r.endLocation = :endLocation AND r.departureDate = :departureDate"),
    @NamedQuery(name = "Ride.findByStartLocation", query = "SELECT r FROM Ride r WHERE r.startLocation = :startLocation"),
    @NamedQuery(name = "Ride.findByEndLocation", query = "SELECT r FROM Ride r WHERE r.endLocation = :endLocation"),
    @NamedQuery(name = "Ride.findByDepartureDate", query = "SELECT r FROM Ride r WHERE r.departureDate = :departureDate"),
    @NamedQuery(name = "Ride.findByStartEnd", query = "SELECT r FROM Ride r WHERE r.startLocation = :startLocation AND r.endLocation = :endLocation"),
    @NamedQuery(name = "Ride.findByStartDate", query = "SELECT r FROM Ride r WHERE r.startLocation = :startLocation AND r.departureDate = :departureDate"),
    @NamedQuery(name = "Ride.findByEndDate", query = "SELECT r FROM Ride r WHERE r.endLocation = :endLocation AND r.departureDate = :departureDate"),
    @NamedQuery(name = "Ride.findByIdRide", query = "SELECT r FROM Ride r WHERE r.idRide = :idRide"),
    @NamedQuery(name = "Ride.findByAskingPrice", query = "SELECT r FROM Ride r WHERE r.askingPrice = :askingPrice"),
    @NamedQuery(name = "Ride.findByDepartureDate", query = "SELECT r FROM Ride r WHERE r.departureDate = :departureDate"),
    @NamedQuery(name = "Ride.findByDepartureTime", query = "SELECT r FROM Ride r WHERE r.departureTime = :departureTime"),
    @NamedQuery(name = "Ride.findByEndLocation", query = "SELECT r FROM Ride r WHERE r.endLocation = :endLocation"),
    @NamedQuery(name = "Ride.findBySeatsAvailable", query = "SELECT r FROM Ride r WHERE r.seatsAvailable = :seatsAvailable"),
    @NamedQuery(name = "Ride.findByStartLocation", query = "SELECT r FROM Ride r WHERE r.startLocation = :startLocation"),
    @NamedQuery(name = "Ride.findByStatus", query = "SELECT r FROM Ride r WHERE r.status = :status"),
    @NamedQuery(name = "Ride.findByIdMember", query = "SELECT r FROM Ride r WHERE r.idMember = :idMember"),
    @NamedQuery(name = "Ride.findByNegotiationIdMember", query = "SELECT r FROM Ride r WHERE r.idRide IN (SELECT n.ride.idRide FROM Negotiation n WHERE n.dryver.idMember = :idMember)")})
@Cacheable(false)


public class Ride implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRide")
    private Integer idRide;
    @Basic(optional = false)
    @NotNull
    @Column(name = "askingPrice")
    private double askingPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departureDate")
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departureTime")
    @Temporal(TemporalType.TIME)
    private Date departureTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "endLocation")
    private String endLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seatsAvailable")
    private int seatsAvailable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "startLocation")
    private String startLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private double distance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdOn", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @JoinColumn(name = "idMember", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idMember;
    @JoinColumn(name = "idCar", referencedColumnName = "idCar")
    @ManyToOne(optional = false)
    private Car idCar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ride")
    private List<Negotiation> negotiationList;

    public Ride() {
    }

    public Ride(Integer idRide) {
        this.idRide = idRide;
    }

    public Ride(Integer idRide, double askingPrice, Date departureDate, Date departureTime, String endLocation, int seatsAvailable, String startLocation, double distance, boolean status) {
        this.idRide = idRide;
        this.askingPrice = askingPrice;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.endLocation = endLocation;
        this.seatsAvailable = seatsAvailable;
        this.startLocation = startLocation;
        this.distance = distance;
        this.status = status;
    }

    public Integer getIdRide() {
        return idRide;
    }

    public void setIdRide(Integer idRide) {
        this.idRide = idRide;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //createdOn is read-only
    public Date getCreatedOn() {
        return createdOn;
    }

    public Dryver getIdMember() {
        return idMember;
    }

    public void setIdMember(Dryver idMember) {
        this.idMember = idMember;
    }

    public Car getIdCar() {
        return idCar;
    }

    public void setIdCar(Car idCar) {
        this.idCar = idCar;
    }

    @XmlTransient
    public List<Negotiation> getNegotiationList() {
        return negotiationList;
    }

    public void setNegotiationList(List<Negotiation> negotiationList) {
        this.negotiationList = negotiationList;
    }

    //Generates a timestamp just before a new (and only a new) Ride is inserted into the database
    @PrePersist
    public void createTimeStamp() {

        if (createdOn == null) {
            createdOn = new Date();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRide != null ? idRide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ride)) {
            return false;
        }
        Ride other = (Ride) object;
        if ((this.idRide == null && other.idRide != null) || (this.idRide != null && !this.idRide.equals(other.idRide))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ride[ idRide=" + idRide + " ]";
    }
}
