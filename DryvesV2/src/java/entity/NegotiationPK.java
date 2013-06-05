/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hctung
 */
@Embeddable
public class NegotiationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRide")
    private int idRide;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMember")
    private int idMember;

    public NegotiationPK() {
    }

    public NegotiationPK(int idRide, int idMember) {
        this.idRide = idRide;
        this.idMember = idMember;
    }

    public int getIdRide() {
        return idRide;
    }

    public void setIdRide(int idRide) {
        this.idRide = idRide;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRide;
        hash += (int) idMember;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NegotiationPK)) {
            return false;
        }
        NegotiationPK other = (NegotiationPK) object;
        if (this.idRide != other.idRide) {
            return false;
        }
        if (this.idMember != other.idMember) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NegotiationPK[ idRide=" + idRide + ", idMember=" + idMember + " ]";
    }
    
}
