package org.proptiger.controller;

import java.util.List;

import org.proptiger.dao.EmployeeDao;
import org.proptiger.dao.MeetingDao;
import org.proptiger.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private EmployeeDao employeeService;
	@Autowired
	private MeetingDao meetingService;

	@RequestMapping(value = "/test")
	public ResponseEntity<String> testMapping() {
		return new ResponseEntity<String>("Hey buddy u dont need to test me. I am working fine.", HttpStatus.OK);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> list = employeeService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getOne(@PathVariable Long id) {
		Employee employee = employeeService.findOne(id);
		if (employee == null)
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}
