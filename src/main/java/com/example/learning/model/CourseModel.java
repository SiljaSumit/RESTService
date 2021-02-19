package com.example.learning.model;

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
@JsonRootName(value = "course")
@Relation(collectionRelation = "courses", itemRelation = "course")
public class CourseModel extends
			RepresentationModel<CourseModel>{
	private Integer id;
	private String name;
	@JsonIgnoreProperties("courses")
	private List<StudentModel> students = new ArrayList<StudentModel>();
}
