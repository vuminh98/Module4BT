//package com.example.demo.service;
//
//import com.example.demo.model.Customer;
//import org.hibernate.HibernateException;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//public class HibernateCustomerService implements CustomerService{
//
//    private static SessionFactory sessionFactory;
//
//    private static EntityManager entityManager;
//
//    static {
//        try {
//            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
//            entityManager = sessionFactory.createEntityManager();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public List<Customer> findAll() {
//        String queryStr = "SELECT c FROM Customer AS c";
//        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
//        return query.getResultList();
//    }
//
//    @Override
//    public Customer findOne(Long id) {
//        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
//        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class)
//    }
//
//    @Override
//    public Customer save(Customer customer) {
//        return null;
//    }
//
//    @Override
//    public List<Customer> save(List<Customer> customers) {
//        return null;
//    }
//
//    @Override
//    public boolean exists(Long id) {
//        return false;
//    }
//
//    @Override
//    public List<Customer> findAll(List<Long> ids) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//
//    @Override
//    public void delete(Customer customer) {
//
//    }
//
//    @Override
//    public void delete(List<Customer> customers) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
