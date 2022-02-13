package br.com.eduardo.bds03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.bds03.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
