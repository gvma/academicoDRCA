package br.ufal.ic.academico.exemplos;

import io.dropwizard.hibernate.AbstractDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import br.ufal.ic.academico.model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;


@Slf4j
public class Database extends AbstractDAO<Object> {
    
    public Database(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public <T> T get(Class<T> clazz, Serializable id) throws HibernateException {
        log.info("getting {}: id={}", clazz.getSimpleName().toLowerCase(), id);
        return currentSession().get(clazz,  id);
    }
    
    @SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> clazz) throws HibernateException {
        log.info("getting {}", clazz.getSimpleName());
        
        return (List<T>) super.list(query("from " + clazz.getSimpleName()));
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<Student> listStudents(Class<T> clazz, T entity) throws HibernateException {
        log.debug("listStudents: {}", clazz.getSimpleName());
        
        List<Object> result = super.list(query("from Student where "+clazz.getSimpleName().toLowerCase()+" = ?")
        		.setParameter(0, entity));
        
        return new ArrayList(result);
    }
    
    @SuppressWarnings("unchecked")
	public <T> T persist(Class<T> clazz, T entity) throws HibernateException {
        return (T) super.persist(entity);
    }
}
