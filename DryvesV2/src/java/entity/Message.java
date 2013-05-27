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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Willem
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Message.findByDateTime", query = "SELECT m FROM Message m WHERE m.dateTime = :dateTime"),
    @NamedQuery(name = "Message.findByText", query = "SELECT m FROM Message m WHERE m.text = :text"),
    @NamedQuery(name = "Message.findByReciever", query = "SELECT m FROM Message m WHERE m.idMemberReciever = :idMemberReciever ORDER BY m.dateTime DESC"),
    @NamedQuery(name = "Message.findBySender", query = "SELECT m FROM Message m WHERE m.idMemberReciever = :idMemberReciever AND m.idMemberSender = :idMemberSender"),
    @NamedQuery(name = "Message.getSingleMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage AND m.idMemberSender = :idMemberSender AND m.dateTime = :dateTime"),
    @NamedQuery(name = "Message.getSentMessage", query = "SELECT m FROM Message m WHERE m.idMemberReciever = :idSender AND m.idMemberSender = :idReciever")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMessage")
    private Integer idMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "dateTime")
    private String dateTime;
    @Size(max = 156)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "idMemberReciever", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idMemberReciever;
    @JoinColumn(name = "idMemberSender", referencedColumnName = "idMember")
    @ManyToOne(optional = false)
    private Dryver idMemberSender;

    public Message() {
    }

    public Message(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Message(Integer idMessage, String dateTime) {
        this.idMessage = idMessage;
        this.dateTime = dateTime;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Dryver getIdMemberReciever() {
        return idMemberReciever;
    }

    public void setIdMemberReciever(Dryver idMemberReciever) {
        this.idMemberReciever = idMemberReciever;
    }

    public Dryver getIdMemberSender() {
        return idMemberSender;
    }

    public void setIdMemberSender(Dryver idMemberSender) {
        this.idMemberSender = idMemberSender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Message[ idMessage=" + idMessage + " ]";
    }
}
