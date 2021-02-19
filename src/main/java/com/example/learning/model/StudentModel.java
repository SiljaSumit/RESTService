package com.example.learning.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "student")
@Relation(collectionRelation = "students", itemRelation = "student")
public class StudentModel extends 
		RepresentationModel<StudentModel> {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	@JsonIgnoreProperties("students")
	private List<CourseModel> courses = new ArrayList<CourseModel>();

}