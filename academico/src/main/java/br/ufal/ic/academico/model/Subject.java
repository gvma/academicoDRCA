package br.ufal.ic.academico.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String code;
	private int associatedCredits;
	@OneToMany
	private List<Subject> requirements = new ArrayList<Subject>();
	private int coursePeriod;
//	@ManyToOne
//	private Course course;
	
	public Subject() {	
	}
	
	public Subject(String name, String code, int associatedCredits, int coursePeriod) {
		this.name = name;
		this.code = code;
		this.associatedCredits = associatedCredits;
		this.coursePeriod = coursePeriod;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public void setOneRequirement(Subject requirement) {
		this.getRequirements().add(requirement);
	}
}
