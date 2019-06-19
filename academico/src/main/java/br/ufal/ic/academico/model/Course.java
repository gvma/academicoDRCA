package br.ufal.ic.academico.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String kindOfCourse;

	public Course(String name, String kindOfCourse) {
		this.name = name;
		this.kindOfCourse = kindOfCourse;
	}

	public Course() {
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
}
