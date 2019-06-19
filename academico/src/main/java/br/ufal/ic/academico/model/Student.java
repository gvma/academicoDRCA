package br.ufal.ic.academico.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.SessionFactory;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import br.ufal.ic.academico.exemplos.Database;
import br.ufal.ic.academico.model.Course;
import br.ufal.ic.academico.model.Subject;

@Slf4j
@Entity
@Getter
@ToString
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String registration;
	private String name;
	@OneToOne
	private Course course;
	@OneToMany
	private List<Subject> actualSubjects = new ArrayList<Subject>();
	@OneToMany
	private List<Subject> concludedSubjects = new ArrayList<Subject>();
	
	public Student() {
	}
	
	public Student(String name, Course course) {
		this.name = name;
		this.course = course;
	}
	
	public void setOneConcludedSubject (Subject subject) { this.concludedSubjects.add(subject); }
	
	public void registrationProcessing(SessionFactory session, Database database, String registration) {
		session.getCurrentSession().beginTransaction();
		ArrayList<Student> allUniversityStudents = (ArrayList<Student>) database.list(Student.class);
		if (allUniversityStudents.size() == 0) {
			System.out.println("No students yet registrated.");
			return;
		}
		for (Student s : allUniversityStudents) {
			log.info("- Student Registration: "+s.getRegistration());
			log.info("- Name: "+s.getName());
			log.info("- Course: "+s.getCourse().getName());
		}
	}
	
}
