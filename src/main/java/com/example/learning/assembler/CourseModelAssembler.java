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
public class CourseModelAssembler extends RepresentationModelAssemblerSupport<Course, CourseModel> {

	public CourseModelAssembler() {
		super(CourseController.class, CourseModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CourseModel toModel(Course course) {
		CourseModel courseModel = instantiateModel(course);
		courseModel.add(linkTo(methodOn(CourseController.class).findCourseById(course.getId())).withSelfRel());

		courseModel.setId(course.getId());
		courseModel.setName(course.getName());
		courseModel.setStudents(toStudentModel(course.getStudents()));
		return courseModel;
	}

	@Override
	public CollectionModel<CourseModel> toCollectionModel(Iterable<? extends Course> courses) {
		CollectionModel<CourseModel> courseModels = super.toCollectionModel(courses);
		courseModels.add(linkTo(methodOn(CourseController.class).getAllCourses()).withSelfRel());
		return courseModels;
	}

	private List<StudentModel> toStudentModel(List<Student> students) {
		if (students.isEmpty()) {
			return Collections.emptyList();
		}
		return students.stream()
				.map(student -> StudentModel.builder().id(student.getId()).firstName(student.getFirstName())
						.lastName(student.getLastName()).dateOfBirth(student.getDateOfBirth()).build()
						.add(linkTo(methodOn(StudentController.class).findStudentById(student.getId())).withSelfRel()))
				.collect(Collectors.toList());
	}

}
