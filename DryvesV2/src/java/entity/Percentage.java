/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrick
 */
@Entity
@Table(name = "percentage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Percentage.findAll", query = "SELECT p FROM Percentage p"),
    @NamedQuery(name = "Percentage.findByIdPercentage", query = "SELECT p FROM Percentage p WHERE p.idPercentage = :idPercentage"),
    @NamedQuery(name = "Percentage.findBySize", query = "SELECT p FROM Percentage p WHERE p.size = :size"),
    @NamedQuery(name = "Percentage.findByDateFrom", query = "SELECT p FROM Percentage p WHERE p.dateFrom = :dateFrom"),
    @NamedQuery(name = "Percentage.findByDateTo", query = "SELECT p FROM Percentage p WHERE p.dateTo = :dateTo"),
    @NamedQuery(name = "Percentage.findByDateToIsNull", query = "SELECT p FROM Percentage p WHERE p.dateTo IS NULL")})
public class Percentage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPercentage")
    private Integer idPercentage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"size\"")
    private double size;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;
    @Column(name = "dateTo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;

    public Percentage() {
    }

    public Percentage(Integer idPercentage) {
        this.idPercentage = idPercentage;
    }

    public Percentage(double size, Date dateFrom) {
        //this.idPercentage = idPercentage;
        this.size = size;
        this.dateFrom = dateFrom;
    }

    public Integer getIdPercentage() {
        return idPercentage;
    }

    public void setIdPercentage(Integer idPercentage) {
        this.idPercentage = idPercentage;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPercentage != null ? idPercentage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Percentage)) {
            return false;
        }
        Percentage other = (Percentage) object;
        if ((this.idPercentage == null && other.idPercentage != null) || (this.idPercentage != null && !this.idPercentage.equals(other.idPercentage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Percentage[ idPercentage=" + idPercentage + " ]";
    }
}
