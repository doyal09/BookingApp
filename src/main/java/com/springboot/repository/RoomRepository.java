package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.springboot.entity.RoomDetails;
/*
 * Repository class for RoomDetails
 */
public interface RoomRepository extends CrudRepository<RoomDetails, Integer>{	

}
