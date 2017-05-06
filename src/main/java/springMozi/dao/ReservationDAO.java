package springMozi.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springMozi.entities.ReservationEntity;

@Repository
public class ReservationDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ReservationDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<ReservationEntity> getAllMovie() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(ReservationEntity.class);
		
		return cr.list();
	}
	
}
