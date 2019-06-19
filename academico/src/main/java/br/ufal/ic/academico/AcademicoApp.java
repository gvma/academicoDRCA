package br.ufal.ic.academico;

import br.ufal.ic.academico.model.*;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import br.ufal.ic.academico.exemplos.Database;
import br.ufal.ic.academico.exemplos.MyResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcademicoApp extends Application<ConfigApp> {

    public static void main(String[] args) throws Exception {
        new AcademicoApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<ConfigApp> bootstrap) {
        log.info("initialize");
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(ConfigApp config, Environment environment) {
        
    	SessionFactory sessionFactory = hibernate.getSessionFactory();
    	
        final Database db = new Database(sessionFactory);
        Course course = new Course("Ciencia da Computacao", "Graduacao");

        final MyResource resource = new MyResource();
        
//        sessionFactory.getCurrentSession().beginTransaction();
//        db.persist(Course.class, course);
//        sessionFactory.getCurrentSession().getTransaction().commit();
//        
//        sessionFactory.getCurrentSession().beginTransaction();
//        db.persist(Student.class, new Student("17210810", "Guilherme", course));
//        sessionFactory.getCurrentSession().getTransaction().commit();
//        
//        sessionFactory.getCurrentSession().beginTransaction();
//        db.persist(Student.class, new Student("17210810", "Mineirin", course));
//        sessionFactory.getCurrentSession().getTransaction().commit();
//        
//        sessionFactory.getCurrentSession().beginTransaction();
//        ArrayList<Student> arr = (ArrayList<Student>) db.listStudents(Course.class, course);
//        for (Student a : arr) {
//        	System.out.println(a.getId());
//        }
//        sessionFactory.getCurrentSession().beginTransaction();
//        db.persist(Course.class, course);
        Student s = new Student("Guilherme", course);
//        db.persist(Student.class, s);
//        System.out.println(s.getName());
//        sessionFactory.getCurrentSession().getTransaction().commit();
        s.registrationProcessing(sessionFactory, db, "17210812");
        environment.jersey().register(resource);
        sessionFactory.close();
    }

    private final HibernateBundle<ConfigApp> hibernate
            = new HibernateBundle<ConfigApp>(Course.class, Department.class,
            		Secretariat.class, Subject.class, Student.class, Offer.class,
            			Semester.class, Teacher.class) {
        
        @Override
        public DataSourceFactory getDataSourceFactory(ConfigApp configuration) {
            return configuration.getDatabase();
        }
    };
}
