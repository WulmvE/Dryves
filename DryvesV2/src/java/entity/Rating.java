/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hctung
 */
@Entity
@Table(name = "rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r"),
    @NamedQuery(name = "Rating.findByIdRating", query = "SELECT r FROM Rating r WHERE r.idRating = :idRating"),
    @NamedQuery(name = "Rating.findByComment", query = "SELECT r FROM Rating r WHERE r.comment = :comment"),
    @NamedQuery(name = "Rating.findByRatingType", query = "SELECT r FROM Rating r WHERE r.ratingType = :ratingType"),
    @NamedQuery(name = "Rating.findByScore", query = "SELECT r FROM Rating r WHERE r.score = :score")})
@Cacheable(false)
public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRating")
    private Integer idRating;
    @Size(max = 140)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ratingType")
    private String ratingType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    @JoinColumn(name = "idMember", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idMember;

    public Rating() {
    }

    public Rating(Integer idRating) {
        this.idRating = idRating;
    }

    public Rating(Integer idRating, String ratingType, int score) {
        this.idRating = idRating;
        this.ratingType = ratingType;
        this.score = score;
    }

    public Integer getIdRating() {
        return idRating;
    }

    public void setIdRating(Integer idRating) {
        this.idRating = idRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Dryver getIdMember() {
        return idMember;
    }

    public void setIdMember(Dryver idMember) {
        this.idMember = idMember;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRating != null ? idRating.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.idRating == null && other.idRating != null) || (this.idRating != null && !this.idRating.equals(other.idRating))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rating[ idRating=" + idRating + " ]";
    }
    
}
