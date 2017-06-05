package com.collab.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

//import javax.transaction.Transaction;
//import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collab.dao.UserDao;
import com.collab.domain.User;

@Repository
@Transactional
@Component
public class UserDaoImpl implements UserDao {
	@Autowired
	User user;
	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(User user) {

		try {

			Session session = sessionFactory.openSession();
			Transaction tx = (Transaction) session.beginTransaction();
			// user.setRole("ROLE_USER");
			session.save(user);
			tx.commit();
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(User user) {

		try

		{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			// User user1 = (User) session.get(User.class, userId);
			System.out.println("getting user********");
			session.update(user);
			tx.commit();
			System.out.println("updating user***********");
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Session session = (Session) sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> list = session.createQuery("from User").list();
		tx.commit();
		session.flush();
		session.close();
		return list;

	}

	public User getUserById(int UserID) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			User user = (User) session.get(User.class, UserID);
			tx.commit();
			session.flush();
			session.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean delete(int userId) {
		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			User user = (User) session.get(User.class, userId);

			session.delete(user);
			tx.commit();
			session.flush();
			session.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
