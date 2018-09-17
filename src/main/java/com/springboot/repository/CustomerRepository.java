package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.springboot.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{	


}
