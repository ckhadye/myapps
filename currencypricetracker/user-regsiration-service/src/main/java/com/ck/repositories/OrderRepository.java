package com.ck.repositories;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ck.entities.Order;


//@Repository("orderRepo")
//@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer>{

	
}
