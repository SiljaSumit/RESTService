package com.example.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning.entity.Student;
import com.example.learning.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired 
	private StudentRepository studentRepository;
	
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Optional<Student> findById(Integer id) {
		return studentRepository.findById(id);
	}
}
