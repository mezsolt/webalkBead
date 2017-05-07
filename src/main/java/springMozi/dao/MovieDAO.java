package springMozi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springMozi.entities.MovieEntity;

@Repository
public class MovieDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public MovieDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<MovieEntity> getMovieAfterDate(Date date) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(MovieEntity.class);
		
		cr.add(Restrictions.ge("movieReleaseDate",date));
		
		return cr.list();
	}

	
	@Transactional
	public List<MovieEntity> getAllMovie() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(MovieEntity.class);
		
		return cr.list();
	}
}
