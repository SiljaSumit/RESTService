package com.example.learning.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import com.example.learning.entity.Student;

@DataJpaTest
public class StudentRepositoryTest {
	@Autowired
	private StudentRepository studentRepository;

	@Test
	void findById() {
		Optional<Student> optStudent = studentRepository.findById(1);
		assertTrue(optStudent.isPresent());
	}

	@Test
	void findStudentByFirstNameAndLastName() {
		Student student = studentRepository.findStudentByFirstNameAndLastName("Amy", "Josephine");
		assertEquals(student.getEmail(), "josephine@darkjy.org");
	}

	@Test
	void findStudentByDateOfBirth() {
		Student student = studentRepository.findStudentByDateOfBirth(LocalDate.of(2005, 06, 10));
		assertEquals(student.getFirstName(), "Minna");
	}

	@Test
	void findStudentByLastNameAndSort() {
		List<Student> studentList = studentRepository.findStudentByLastNameAndSort("Rim",
				Sort.by("firstName").descending());
		assertEquals(studentList.size(), 3);
		System.out.println("StudentList: "+studentList);
		assertEquals(studentList.get(0).getFirstName(), "Yuki");
	}
}
