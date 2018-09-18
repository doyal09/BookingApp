package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.springboot.entity.RoomDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
/*
 * Repository class for RoomDetails
 */
public interface RoomRepository extends CrudRepository<RoomDetails, Integer>{	
	@Modifying
	@Query("update RoomDetails a set a.availability='false' where a.id=?")
	public void updateRoomDetails(Integer id);

}
