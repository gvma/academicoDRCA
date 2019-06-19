package br.ufal.ic.academico.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Secretariat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@Getter @Setter private Department department;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
}
