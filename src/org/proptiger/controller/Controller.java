package org.proptiger.controller;

import org.proptiger.dao.EmployeeDao;
import org.proptiger.dao.MeetingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
