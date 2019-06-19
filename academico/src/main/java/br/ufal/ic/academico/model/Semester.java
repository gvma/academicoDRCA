package br.ufal.ic.academico.model;


import javax.persistence.*;

import lombok.Getter;

@Entity
@Getter
public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int year;
	private int number;
	private int monthOfBeggining;
}
