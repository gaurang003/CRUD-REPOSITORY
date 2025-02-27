package com.gaurangsharma003.practice.dao;

import com.gaurangsharma003.practice.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{


    // define field for entity manager

    private EntityManager entityManager;

    // set up constructor for injection

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public List<Employee> findAll() {

        // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //get employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        //return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // save the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // return the saved employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // find the employee By Id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //remove the employee
        entityManager.remove(theEmployee);

    }
}
