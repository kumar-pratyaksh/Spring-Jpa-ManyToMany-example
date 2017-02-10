package org.proptiger.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.proptiger.dao.EmployeeDao;
import org.proptiger.dao.EmployeeMeeting;
import org.proptiger.dao.MeetingDao;
import org.proptiger.model.Employee;
import org.proptiger.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private EmployeeDao employeeService;
	@Autowired
	private MeetingDao meetingService;
	@Autowired
	private EmployeeMeeting employeeMeeting;

	@RequestMapping(value = "/test")
	public ResponseEntity<String> testMapping() {
		return new ResponseEntity<String>("Hey buddy u dont need to test me. I am working fine.", HttpStatus.OK);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = employeeService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		Employee employee = employeeService.findOne(id);
		if (employee == null)
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/meeting", method = RequestMethod.GET)
	public ResponseEntity<List<Meeting>> getAllMeetings() {
		List<Meeting> list = meetingService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/meeting/{id}", method = RequestMethod.GET)
	public ResponseEntity<Meeting> getMeeting(@PathVariable Long id) {
		Meeting meeting = meetingService.findOne(id);
		if (meeting == null)
			return new ResponseEntity<Meeting>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Void> insertEmployee(@RequestBody Employee employee) {
		try {
			employeeService.insert(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/meeting", method = RequestMethod.POST)
	public ResponseEntity<Void> insertMeeting(@RequestBody Meeting meeting) {
		try {
			System.out.println(meeting);
			meetingService.insert(meeting);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		try {
			employeeService.update(id, employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/meeting/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMeeting(@RequestBody Meeting meeting, @PathVariable Long id) {
		try {
			meetingService.update(id, meeting);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meeting/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
		meetingService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meeting/{meetingId}/employee/{employeeId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addEmployee(@PathVariable Long employeeId, @PathVariable Long meetingId) {
		employeeMeeting.addEmployeeToMeeting(employeeId, meetingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meeting/byDate", method = RequestMethod.GET)
	public ResponseEntity<List<Meeting>> findMeetingsByDate(
			@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		if (date == null)
			date = new Date();
		return new ResponseEntity<List<Meeting>>(meetingService.findByMeetingDate(date), HttpStatus.OK);
	}

	@RequestMapping(value = "/meeting/employees/{meetingId}", method = RequestMethod.GET)
	public ResponseEntity<Set<Employee>> employeesInAMeeting(@PathVariable Long meetingId) {
		Meeting meeting = meetingService.findOne(meetingId);
		if (meeting == null)
			return new ResponseEntity<Set<Employee>>(HttpStatus.NOT_FOUND);
		Set<Employee> employees = meeting.getEmployees();
		if (employees.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

}
