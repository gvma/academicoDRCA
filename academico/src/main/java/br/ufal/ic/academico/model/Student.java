package br.ufal.ic.academico.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		for (int i = 0; i < allUniversityStudents.size(); i++) {
			log.info("("+(i+1)+") - "+allUniversityStudents.get(i).getName());
		}
		Student student = new Student();
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int studentArrayPosition = scanner.nextInt();
			scanner.nextLine();
			student = allUniversityStudents.get(studentArrayPosition - 1);
		} catch(ArrayIndexOutOfBoundsException e) {
			log.error("Can't access this student, try again next time.");
		}
		log.info("Student got with success!");
		Course course = student.getCourse();
		ArrayList<Subject> subjectsFromCourse = (ArrayList<Subject>) course.getCourseSubjects();
		for (int i = 0; i < subjectsFromCourse.size(); i++) {
			log.info("("+(i+1)+") - "+subjectsFromCourse.get(i).getName());
		}
		Subject subject = new Subject();
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int subjectArrayPosition = scanner.nextInt();
			scanner.nextLine();
			subject = subjectsFromCourse.get(subjectArrayPosition - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			log.error("Can't access this subject, try again next time.");
		}
		log.info("Subject got with success!");
		if (student.getActualSubjects().contains(subject)) {
			log.error("This student is already registered in this subject.");
			return;
		} else {
			student.getActualSubjects().add(subject);
			log.error("Subject registration completed with success!");
		}
	}
	
}
