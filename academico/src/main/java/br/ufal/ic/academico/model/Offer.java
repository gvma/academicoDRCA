package br.ufal.ic.academico.model;

import javax.persistence.*;
import lombok.Getter;
import java.util.*;

@Entity
@Getter
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Semester semester;
	@OneToMany
	private List<Subject> subjects = new ArrayList<Subject>();
	@OneToOne
	private Student student;
	@OneToOne
	private Teacher teacher;
	@ManyToOne
	private Department department;
}
