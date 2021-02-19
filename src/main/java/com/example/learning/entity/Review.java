package com.example.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private ReviewRating rating;

	private String description;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Override
	public String toString() {
		return String.format("Review[%s %s]", rating, description);
	}
}
