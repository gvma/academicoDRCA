package br.ufal.ic.academico.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;

@Entity
@Getter
@Slf4j
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String kindOfCourse;
	@OneToMany
	private List<Subject> courseSubjects = new ArrayList<Subject>();
	
	public Course(String name, String kindOfCourse) {
		this.name = name;
		this.kindOfCourse = kindOfCourse;
	}

	public Course() {
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public void setOneSubject(Subject subject) {
		if (!courseSubjects.contains(subject)) {
			this.courseSubjects.add(subject);
		} else {
			log.debug("The subject you tried to add is already in this course!");
		}
	}
}
