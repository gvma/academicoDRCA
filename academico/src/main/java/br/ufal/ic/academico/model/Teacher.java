package br.ufal.ic.academico.model;

import java.util.*;
import javax.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToOne
	private Department department;
	@OneToMany
	private List<Subject> teachingSubjects = new ArrayList<Subject>();
	@OneToMany
	private List<Offer> teacherOffers = new ArrayList<Offer>();
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }	
}
