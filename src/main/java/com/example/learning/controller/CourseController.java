package com.example.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning.assembler.CourseModelAssembler;
import com.example.learning.entity.Course;
import com.example.learning.model.CourseModel;
import com.example.learning.service.CourseService;

@RequestMapping("/courses")
public @RestController class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseModelAssembler courseModelAssembler;
	
	@GetMapping
	public List<Course> getAllCourses() {
		return courseService.findAll();
	}

	@GetMapping("/{id}")
	public CourseModel findCourseById(@PathVariable Integer id) {
		Optional<Course> course = courseService.findById(id);
		if (!course.isPresent()) {
			throw new RuntimeException(String.format("No resource found for id (%s)", id));
		}
		return courseModelAssembler.toModel(course.get());
	}
}
