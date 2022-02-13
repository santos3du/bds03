package br.com.eduardo.bds03.services;

import br.com.eduardo.bds03.entities.Department;
import br.com.eduardo.bds03.entities.Employee;
import br.com.eduardo.bds03.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eduardo.bds03.dto.EmployeeDTO;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAll(Pageable pageable) {
		Page<Employee> page = repository.findAll(pageable);
		return page.map(x -> new EmployeeDTO(x));
	}

	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDepartment(new Department(dto.getDepartmentId(), null));
		entity = repository.save(entity);
		return new EmployeeDTO(entity);
	}
}
