package br.ufal.ic.academico;

import br.ufal.ic.academico.model.*;

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
        Subject subject = new Subject("P1", "123123", 0, 1);
        course.setOneSubject(subject);
        Student s = new Student("Guilherme", course);
        final MyResource resource = new MyResource();
        
//        sessionFactory.getCurrentSession().beginTransaction();
        Department d = new Department("Departamento de Ciencia da Computacao", course);
//        db.persist(Course.class, course);
//        db.persist(Student.class, s);
//        db.persist(Department.class, d);
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
