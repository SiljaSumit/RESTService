package com.example.learning.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = { "firstname", "lastname", "email" }))
public class Student {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;

	@Column(name = "dob")
	private LocalDate dateOfBirth;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	//@NotNull
	//@Email
	@Column(unique = true)
	private String email;

	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), 
	inverseJoinColumns = @JoinColumn(name = "course_id"))
	@Setter(AccessLevel.NONE)
	private List<Course> courses = new ArrayList<Course>(); 
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	@Override
	public String toString() {
		return String.format("Student[%s,%s,%s]", firstName, lastName, email);
	}
}
