package br.ufal.ic.academico.model;

import javax.persistence.*;

import lombok.Getter;

@Entity
@Getter
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToOne
	private Course course;
	
	public Department(String name, Course course) {
		this.name = name;
		this.course = course;
	}
	
}
