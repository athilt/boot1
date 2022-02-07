package com.hiltuprog.boot1.service;

import java.util.List;
import java.util.Optional;

//import io.github.jhipster.security.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiltuprog.boot1.domain.Course;
import com.hiltuprog.boot1.domain.Curriculum;
import com.hiltuprog.boot1.dto.CourseDTO;
import com.hiltuprog.boot1.dto.CurriculumDTO;
import com.hiltuprog.boot1.repository.CurriculumRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class CurriculumService {

	private final Logger log = LoggerFactory.getLogger(CurriculumService.class);

	private final CurriculumRepository curriculumRepository;
	
	@Autowired
	private CourseService courseService;

	public CurriculumService(CurriculumRepository curriculumRepository) {
		this.curriculumRepository = curriculumRepository;
	}

	public Curriculum create(Curriculum curriculum) {
		return curriculumRepository.save(curriculum);
	}

	public List<Curriculum> findAll() {
		return curriculumRepository.findAll();
	}

	public Optional<Curriculum> findById(Long id) {
		return curriculumRepository.findById(id);
	}
	
	public void addCourse(Long curriculumId, Long courseId)
	{
		//TODO: Some error checking, please!
		curriculumRepository.findById(curriculumId).get()
		.getCourses().add(courseService.findById(courseId).get());
	}

}