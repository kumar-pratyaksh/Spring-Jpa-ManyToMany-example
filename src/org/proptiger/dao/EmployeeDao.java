package org.proptiger.dao;

import java.util.List;

import org.proptiger.model.Employee;
import org.proptiger.model.Meeting;
import org.proptiger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeDao {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public Employee findOne(Long id) {
		return repository.findOne(id);
	}

	public void insert(Employee e) {
		repository.save(e);
	}

	public void update(Long id, Employee e) {
		Employee employee = repository.findOne(id);
		employee.setEmail(e.getEmail());
		employee.setName(e.getName());
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public void addMeeting(Long employeeId, Meeting meeting) {
		Employee employee = repository.getOne(employeeId);
		employee.getMeetings().add(meeting);
	}
}
