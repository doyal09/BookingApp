package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.springboot.entity.RoomDetails;

public interface RoomRepository extends CrudRepository<RoomDetails, Integer>{	


}
