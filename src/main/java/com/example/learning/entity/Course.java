package com.example.learning.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy="course")
	@Setter(AccessLevel.NONE)
	private List<Review> reviews = new ArrayList<>();
	
	@JsonBackReference
	@ManyToMany(mappedBy="courses")
	@Setter(AccessLevel.NONE)
	private List<Student> students = new ArrayList<>();
	
	public void addReview(Review review) {
		reviews.add(review);
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	
	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}
}
