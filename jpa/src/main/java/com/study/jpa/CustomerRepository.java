package com.study.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findById(long id);
	List<Customer> findByLastName(String lastName);
}
//public interface CustomerRepository extends JpaRepository<Customer, Long> {
//	List<Customer> findByLastName(String lastName);
//}

