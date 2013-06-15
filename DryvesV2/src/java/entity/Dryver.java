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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Dryver.findByFirstNameLastName", query = "SELECT d FROM Dryver d WHERE d.firstName = :firstName AND d.lastName = :lastName"),
    @NamedQuery(name = "Dryver.findByFirstNameEmail", query = "SELECT d FROM Dryver d WHERE d.firstName = :firstName AND d.email = :email"),
    @NamedQuery(name = "Dryver.findByLastNameEmail", query = "SELECT d FROM Dryver d WHERE d.lastName = :lastName AND d.email = :email"),
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "memberSince", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date memberSince;
    @ManyToMany(mappedBy = "dryverList")
    private List<Groups> groupsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private List<Car> carList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMemberSender")
    private List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMemberReciever")
    private List<Message> messageList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private List<Ride> rideList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dryver")
    private Admin admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dryver")
    private List<Negotiation> negotiationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private List<Rating> ratingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMember")
    private List<Friend> friendList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFriend")
    private List<Friend> friendList1;

    public Dryver() {
    }

    public Dryver(Integer idMember) {
        this.idMember = idMember;
    }

    public Dryver(Integer idMember, String alias, String city, String email, String firstName, String lastName, String password, String gender, Date birthDate) {
        this.idMember = idMember;
        this.alias = alias;
        this.city = city;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    //MemberSince is read-only
    public Date getMemberSince() {
        return memberSince;
    }

    @XmlTransient
    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @XmlTransient
    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlTransient
    public List<Message> getMessageList1() {
        return messageList1;
    }

    public void setMessageList1(List<Message> messageList1) {
        this.messageList1 = messageList1;
    }

    @XmlTransient
    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @XmlTransient
    public List<Negotiation> getNegotiationList() {
        return negotiationList;
    }

    public void setNegotiationList(List<Negotiation> negotiationList) {
        this.negotiationList = negotiationList;
    }

    @XmlTransient
    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    @XmlTransient
    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    @XmlTransient
    public List<Friend> getFriendList1() {
        return friendList1;
    }

    public void setFriendList1(List<Friend> friendList1) {
        this.friendList1 = friendList1;
    }

    //Generates a timestamp just before a new (and only a new) Dryver is inserted into the database
    @PrePersist
    public void createTimeStamp() {
        if (memberSince == null) {
            memberSince = new Date();
        }
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
