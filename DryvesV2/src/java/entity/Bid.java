/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hctung
 */
@Entity
@Table(name = "bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b"),
    @NamedQuery(name = "Bid.findByIdBid", query = "SELECT b FROM Bid b WHERE b.idBid = :idBid"),
    @NamedQuery(name = "Bid.findByAmount", query = "SELECT b FROM Bid b WHERE b.amount = :amount"),
    @NamedQuery(name = "Bid.findByText", query = "SELECT b FROM Bid b WHERE b.text = :text"),
    @NamedQuery(name = "Bid.findByTimestamp", query = "SELECT b FROM Bid b WHERE b.timestamp = :timestamp")})
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBid")
    private Integer idBid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Size(max = 156)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumns({
        @JoinColumn(name = "idRide", referencedColumnName = "idRide"),
        @JoinColumn(name = "idMember", referencedColumnName = "idMember")})
    @ManyToOne(optional = false)
    private Negotiation negotiation;

    public Bid() {
    }

    public Bid(Integer idBid) {
        this.idBid = idBid;
    }

    public Bid(Integer idBid, double amount, Date timestamp) {
        this.idBid = idBid;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Integer getIdBid() {
        return idBid;
    }

    public void setIdBid(Integer idBid) {
        this.idBid = idBid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBid != null ? idBid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.idBid == null && other.idBid != null) || (this.idBid != null && !this.idBid.equals(other.idBid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Bid[ idBid=" + idBid + " ]";
    }
    
}
