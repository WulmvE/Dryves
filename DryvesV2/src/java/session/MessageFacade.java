/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Dryver;
import entity.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Willem
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> {
    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
    // retrieve all messages from the logged in idMember.
    public List<Message> searchMessageByIdReciever(Dryver idMember) {
        return em.createNamedQuery("Message.findByReciever").setParameter("idMemberReciever", idMember).getResultList();
    }
    
    // get all messages from a certain sender AND the logged in idMember.
    public List<Message> searchMessageByidSender(Dryver idMember, Dryver idSender){
        return em.createNamedQuery("Message.findBySender").setParameter("idMemberReciever", idMember).setParameter("idMemberSender", idSender).getResultList();
    }
    
    // retrieve a single message by messageId, SenderId and Date.
    public List<Message> getSingleMessage(int idMessage, Dryver idSender, String dateTime){
        return em.createNamedQuery("Message.getSingleMessage").setParameter("idMessage", idMessage).setParameter("idMemberSender", idSender).setParameter("dateTime", dateTime).getResultList();
    }
    
    // retrieve a single message by messageId.
    public List<Message> getSingleMessageOnID(int idMessage){
        return em.createNamedQuery("Message.findByIdMessage").setParameter("idMessage", idMessage).getResultList();
    }
    
    public List<Message> getSentMessage(Dryver idSender, Dryver idReciever){
        return em.createNamedQuery("Message.getSentMessage").setParameter("idMemberSender", idSender).setParameter("idMemberReciever", idReciever).getResultList();
    }
}
