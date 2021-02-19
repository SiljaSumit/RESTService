package com.example.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.learning.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Query("SELECT c FROM Course c WHERE c.name = ?1")
	Course findByName(String name);
	
	@Query("SELECT c FROM Course c WHERE SIZE(c.students) >= ?1")
	List<Course> findCoursesByStudentsCount(int count);
}
