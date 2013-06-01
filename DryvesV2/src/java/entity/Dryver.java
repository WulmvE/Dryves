/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.persistence.OneToOne;
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
@Table(name = "member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dryver.findAll", query = "SELECT d FROM Dryver d"),
    @NamedQuery(name = "Dryver.findByIdMember", query = "SELECT d FROM Dryver d WHERE d.idMember = :idMember"),
    @NamedQuery(name = "Dryver.findByAdjective", query = "SELECT d FROM Dryver d WHERE d.adjective = :adjective"),
    @NamedQuery(name = "Dryver.findByAlias", query = "SELECT d FROM Dryver d WHERE d.alias = :alias"),
    @NamedQuery(name = "Dryver.findByAvgRating", query = "SELECT d FROM Dryver d WHERE d.avgRating = :avgRating"),
    @NamedQuery(name = "Dryver.findByCity", query = "SELECT d FROM Dryver d WHERE d.city = :city"),
    @NamedQuery(name = "Dryver.findByEmail", query = "SELECT d FROM Dryver d WHERE d.email = :email"),
    @NamedQuery(name = "Dryver.findByFirstName", query = "SELECT d FROM Dryver d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "Dryver.findByLastName", query = "SELECT d FROM Dryver d WHERE d.lastName = :lastName"),
    @NamedQuery(name = "Dryver.findByPassword", query = "SELECT d FROM Dryver d WHERE d.password = :password")})

public class Dryver implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMember")
    private Integer idMember;
    @Size(max = 45)
    @Column(name = "adjective")
    private String adjective;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "alias")
    private String alias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "avgRating")
    private Double avgRating;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "city")
    private String city;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private Collection<Car> carCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFriend")
    private Collection<Dryver> dryverCollection;
    @JoinColumn(name = "idFriend", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idFriend;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMemberReciever")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMemberSender")
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private Collection<Ride> rideCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dryver")
    private Admin admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dryver")
    private Collection<Negotiation> negotiationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private Collection<Rating> ratingCollection;
    
    //private Stars sterren;

    public Dryver() {
    }

    public Dryver(Integer idMember) {
        this.idMember = idMember;
    }

    public Dryver(Integer idMember, String alias, String city, String email, String firstName, String lastName, String password) {
        this.idMember = idMember;
        this.alias = alias;
        this.city = city;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Integer getIdMember() {
        return idMember;
    }

    public void setIdMember(Integer idMember) {
        this.idMember = idMember;
    }

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Double getAvgRating() {
        return avgRating;
    }
    
    public Stars getStars() {
        Double rating = getAvgRating();
        if(rating < 1) {
            return Stars.EEN;
        } else if (rating < 2) {
            
        }
        //        <c:if test="${ride.idMember.avgRating > 0.00 && ride.idMember.avgRating <= 2.00}">
        //        <span class="rating_small text_green"></span>
        //        </c:if>
        //        <c:if test="${ride.idMember.avgRating > 2.00 && ride.idMember.avgRating <= 4.00}">
        //        <span class="rating_small text_green"></span>
        //        </c:if>
        //        <c:if test="${ride.idMember.avgRating > 4.00 && ride.idMember.avgRating <= 5.00}">
        //        <span class="rating_small text_green"></span>
        //        </c:if>
        return Stars.TWEE;
    }
    
    public enum Stars {
        EEN("\u2345"),
        TWEE("");
        private String unicode;
        private Stars(String uni) {
            unicode = uni;
        }

        @Override
        public String toString() {
            return unicode;
        }
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Car> getCarCollection() {
        return carCollection;
    }

    public void setCarCollection(Collection<Car> carCollection) {
        this.carCollection = carCollection;
    }

    @XmlTransient
    public Collection<Dryver> getDryverCollection() {
        return dryverCollection;
    }

    public void setDryverCollection(Collection<Dryver> dryverCollection) {
        this.dryverCollection = dryverCollection;
    }

    public Dryver getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Dryver idFriend) {
        this.idFriend = idFriend;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<Ride> getRideCollection() {
        return rideCollection;
    }

    public void setRideCollection(Collection<Ride> rideCollection) {
        this.rideCollection = rideCollection;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Negotiation> getNegotiationCollection() {
        return negotiationCollection;
    }

    public void setNegotiationCollection(Collection<Negotiation> negotiationCollection) {
        this.negotiationCollection = negotiationCollection;
    }

    @XmlTransient
    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMember != null ? idMember.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dryver)) {
            return false;
        }
        Dryver other = (Dryver) object;
        if ((this.idMember == null && other.idMember != null) || (this.idMember != null && !this.idMember.equals(other.idMember))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Dryver[ idMember=" + idMember + " ]";
    }
    
}
