package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {
	
	Session session;

    public MeetingService() {
    	session = (Session) DatabaseConnector.getInstance().getSession();
    }

    public Collection<Meeting> getAll() {
        String hql = "FROM Meeting";
        Query query = session.createQuery(hql);
        return query.list();
    }
    
	public Meeting add(Meeting meeting) {
		Transaction transaction = this.session.beginTransaction();
		session.save(meeting);
		transaction.commit();
		return meeting;
	}
	
	public Meeting findById(long id) {
		return (Meeting) session.get(Meeting.class, id);
	}
	
	public void delete(Meeting meeting) {
		Transaction transaction = this.session.beginTransaction();
		session.delete(meeting);
		transaction.commit();
	}
	
	public Participant addParticipant(Meeting meeting, Participant participant) {
		Transaction transaction = this.session.beginTransaction();
		meeting.addParticipant(participant);
		session.merge(meeting);
		transaction.commit();
		return participant;
	}
	
	public Meeting deleteParticipant(Meeting meeting, Participant participant) {
		Transaction transaction = this.session.beginTransaction();
		meeting.getParticipants().remove(participant);
		session.save(meeting);
		transaction.commit();
		return meeting;
	}
}
