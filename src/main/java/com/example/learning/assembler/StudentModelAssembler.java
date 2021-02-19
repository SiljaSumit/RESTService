package com.example.learning.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.learning.controller.CourseController;
import com.example.learning.controller.StudentController;
import com.example.learning.entity.Course;
import com.example.learning.entity.Student;
import com.example.learning.model.CourseModel;
import com.example.learning.model.StudentModel;

@Component
public class StudentModelAssembler extends RepresentationModelAssemblerSupport<Student, StudentModel> {

	public StudentModelAssembler() {
		super(StudentController.class, StudentModel.class);
	}

	@Override
	public StudentModel toModel(Student entity) {
		StudentModel studentModel = instantiateModel(entity);
		studentModel.add(linkTo(methodOn(StudentController.class).findStudentById(entity.getId())).withSelfRel());
		studentModel.setId(entity.getId());
		studentModel.setFirstName(entity.getFirstName());
		studentModel.setLastName(entity.getLastName());
		studentModel.setDateOfBirth(entity.getDateOfBirth());
		studentModel.setCourses(toCourseModel(entity.getCourses()));
		return studentModel;
	}

	@Override
	public CollectionModel<StudentModel> toCollectionModel(Iterable<? extends Student> entities) {
		CollectionModel<StudentModel> studentModels = super.toCollectionModel(entities);
		studentModels.add(linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel());
		return studentModels;
	}

	private List<CourseModel> toCourseModel(List<Course> courses) {
		if (courses.isEmpty()) {
			return Collections.emptyList();
		}
		return courses.stream()
				.map(course -> CourseModel.builder().id(course.getId()).name(course.getName()).build()
						.add(linkTo(methodOn(CourseController.class).findCourseById(course.getId())).withSelfRel()))
				.collect(Collectors.toList());
	}

}