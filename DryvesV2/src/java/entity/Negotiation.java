/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hctung
 */
@Entity
@Table(name = "negotiation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Negotiation.findAll", query = "SELECT n FROM Negotiation n"),
    @NamedQuery(name = "Negotiation.findByAcceptedDriver", query = "SELECT n FROM Negotiation n WHERE n.acceptedDriver = :acceptedDriver"),
    @NamedQuery(name = "Negotiation.findByAcceptedPassenger", query = "SELECT n FROM Negotiation n WHERE n.acceptedPassenger = :acceptedPassenger"),
    @NamedQuery(name = "Negotiation.findByIdRide", query = "SELECT n FROM Negotiation n WHERE n.negotiationPK.idRide = :idRide"),
    @NamedQuery(name = "Negotiation.findByIdMember", query = "SELECT n FROM Negotiation n WHERE n.negotiationPK.idMember = :idMember")})
public class Negotiation implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NegotiationPK negotiationPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acceptedDriver")
    private int acceptedDriver;
    @Column(name = "acceptedPassenger")
    private Integer acceptedPassenger;
    @JoinColumn(name = "idRide", referencedColumnName = "idRide", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ride ride;
    @JoinColumn(name = "idMember", referencedColumnName = "idMember", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dryver dryver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "negotiation")
    private List<Bid> bidList;

    public Negotiation() {
    }

    public Negotiation(NegotiationPK negotiationPK) {
        this.negotiationPK = negotiationPK;
    }

    public Negotiation(NegotiationPK negotiationPK, int acceptedDriver) {
        this.negotiationPK = negotiationPK;
        this.acceptedDriver = acceptedDriver;
    }

    public Negotiation(int idRide, int idMember) {
        this.negotiationPK = new NegotiationPK(idRide, idMember);
    }

    public NegotiationPK getNegotiationPK() {
        return negotiationPK;
    }

    public void setNegotiationPK(NegotiationPK negotiationPK) {
        this.negotiationPK = negotiationPK;
    }

    public int getAcceptedDriver() {
        return acceptedDriver;
    }

    public void setAcceptedDriver(int acceptedDriver) {
        this.acceptedDriver = acceptedDriver;
    }

    public Integer getAcceptedPassenger() {
        return acceptedPassenger;
    }

    public void setAcceptedPassenger(Integer acceptedPassenger) {
        this.acceptedPassenger = acceptedPassenger;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Dryver getDryver() {
        return dryver;
    }

    public void setDryver(Dryver dryver) {
        this.dryver = dryver;
    }

    @XmlTransient
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (negotiationPK != null ? negotiationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Negotiation)) {
            return false;
        }
        Negotiation other = (Negotiation) object;
        if ((this.negotiationPK == null && other.negotiationPK != null) || (this.negotiationPK != null && !this.negotiationPK.equals(other.negotiationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Negotiation[ negotiationPK=" + negotiationPK + " ]";
    }
    
}
