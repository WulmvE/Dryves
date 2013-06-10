/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hctung
 */
@Entity
@Table(name = "friend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friend.findAll", query = "SELECT f FROM Friend f"),
    @NamedQuery(name = "Friend.findByIdRequest", query = "SELECT f FROM Friend f WHERE f.idRequest = :idRequest"),
    @NamedQuery(name = "Friend.findByIdMember", query = "SELECT f FROM Friend f WHERE f.idMember = :idMember"),
    @NamedQuery(name = "Friend.findByStatus", query = "SELECT f FROM Friend f WHERE f.status = :status")})
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRequest")
    private Integer idRequest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "idMember", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idMember;
    @JoinColumn(name = "idFriend", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idFriend;

    public Friend() {
    }

    public Friend(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public Friend(Integer idRequest, boolean status) {
        this.idRequest = idRequest;
        this.status = status;
    }

    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Dryver getIdMember() {
        return idMember;
    }

    public void setIdMember(Dryver idMember) {
        this.idMember = idMember;
    }

    public Dryver getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Dryver idFriend) {
        this.idFriend = idFriend;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequest != null ? idRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friend)) {
            return false;
        }
        Friend other = (Friend) object;
        if ((this.idRequest == null && other.idRequest != null) || (this.idRequest != null && !this.idRequest.equals(other.idRequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Friend[ idRequest=" + idRequest + " ]";
    }
}
