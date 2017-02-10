package org.proptiger.dao;

import java.util.List;

import org.proptiger.model.Employee;
import org.proptiger.model.Meeting;
import org.proptiger.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MeetingDao {
	@Autowired
	private MeetingRepository repository;

	public List<Meeting> findAll() {
		return repository.findAll();
	}

	public Meeting findOne(Long id) {
		return repository.findOne(id);
	}

	public void insert(Meeting meeting) {
		System.out.println(meeting);
		repository.save(meeting);
	}

	public void update(Long id, Meeting meeting) {
		Meeting existing = repository.findOne(id);
		existing.setSubject(meeting.getSubject());
		existing.setMeetingDate(meeting.getMeetingDate());
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public void addEmployee(Long meetingId, Employee employee) {
		Meeting meeting = repository.findOne(meetingId);
		meeting.getEmployees().add(employee);
	}
}
