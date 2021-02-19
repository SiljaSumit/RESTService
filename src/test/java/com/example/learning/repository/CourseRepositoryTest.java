package com.example.learning.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.learning.entity.Course;
import com.example.learning.repository.CourseRepository;

@DataJpaTest
public class CourseRepositoryTest {
	@Autowired
	CourseRepository courseRepository;

	@Test
	void findByName() {
		Course course = courseRepository.findByName("Physics");
		assertNotNull(course);
	}

	@Test
	void findCoursesByStudentsCount() {
		List<Course> coursesList = courseRepository.findCoursesByStudentsCount(7);
		assertEquals(coursesList.size(), 2);
		assertAll("course", () -> assertEquals("Chemistry", coursesList.get(0).getName()),
				() -> assertEquals("Economics", coursesList.get(1).getName()));
	}
}
