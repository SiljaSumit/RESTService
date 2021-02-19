package com.example.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning.entity.Course;
import com.example.learning.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired 
	private CourseRepository courseRepository;
	
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Optional<Course> findById(Integer id) {
		return courseRepository.findById(id);
	}
}
