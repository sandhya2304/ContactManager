package com.smart.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.Contact;
import com.smart.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer>
{
        //pagination
	
	 @Query("from Contact as c where c.user.userId =:userid")
	  public Page<Contact> findContactsByUser(@Param("userid")int userid,Pageable pageable);
	 
	 
	 //coz of user contact show only thos contact that is particular in that user
	 @Query("from Contact as c where c.cName =:cName and c.user =:user")
	 public List<Contact> findByNameContainingAndUser(@Param("cName")String name,@Param("user")User user);
	 
	  
	  
}
