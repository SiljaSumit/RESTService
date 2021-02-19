package com.example.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning.assembler.StudentModelAssembler;
import com.example.learning.entity.Student;
import com.example.learning.model.StudentModel;
import com.example.learning.service.StudentService;

@RequestMapping("/students")
public @RestController class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentModelAssembler studentModelAssembler;

	@GetMapping
	public CollectionModel<StudentModel> getAllStudents() {
		return studentModelAssembler.toCollectionModel(studentService.findAll());
	}

	@GetMapping("/{id}")
	public StudentModel findStudentById(@PathVariable Integer id) {
		Optional<Student> student= studentService.findById(id);
		if(!student.isPresent()) {
			throw new RuntimeException(String.format("No resource found for id (%s)", id));
		} 
		return studentModelAssembler.toModel(student.get());
	}
}
