package com.java.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.springboot.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	// define field for entity manager
	private EntityManager entityManager;
	
	//set up Constructor Injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		// get the current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		
		// create a Query
		
		Query<Employee> theQuery=
				currentSession.createQuery("from Employee",Employee.class);
		
		// execute query and get result list
		List<Employee> employee=theQuery.getResultList();
		//return the results
		
		
		
		return employee;
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		
		Session currentSession=entityManager.unwrap(Session.class);
		
		Employee theEmployee=
				currentSession.get(Employee.class, theId);
		
		
		
		return theEmployee;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		
		Session currentSession=entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee);
		
		
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		
		Session currentSession=entityManager.unwrap(Session.class);
		
		Query theQuery=
				currentSession.createQuery(
						"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
		
	}

	

}
