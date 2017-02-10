package org.proptiger.dao;

import org.proptiger.model.Employee;
import org.proptiger.repository.EmployeeRepository;
import org.proptiger.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeMeeting {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private MeetingRepository meetingRepository;

	public void addEmployeeToMeeting(Long employeeId, Long meetingId) {
		Employee employee = employeeRepository.findOne(employeeId);
		employee.getMeetings().add(meetingRepository.findOne(meetingId));
	}
}
