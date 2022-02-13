package br.com.eduardo.bds03.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.eduardo.bds03.entities.Department;
import br.com.eduardo.bds03.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.eduardo.bds03.dto.DepartmentDTO;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	public List<DepartmentDTO> findAll() {
		List<Department> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
}
