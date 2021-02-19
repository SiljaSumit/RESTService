package com.example.learning.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.learning.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
    // Indexed Query Parameters JPQL
	@Query("SELECT s FROM Student s WHERE s.firstName = ?1 and s.lastName = ?2")
	Student findStudentByFirstNameAndLastName(String firstName, String lastname);
	
	// Named Query Parameters JPQL
	@Query("SELECT s FROM Student s WHERE s.dateOfBirth =:dob")
	Student findStudentByDateOfBirth(@Param("dob") LocalDate dob);
	
	@Query("select s from Student s where s.lastName = ?1")
	List<Student> findStudentByLastNameAndSort(String lastName, Sort sort);
}
