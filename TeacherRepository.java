package com.cms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cms.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

	 @Query("select t from Teacher t where t.email =:email") 
	 public Teacher getTeacherByUserName(@Param("email") String email);
	// Optional<Teacher> getTeacherByUserName(@Param("email") String email);
}
	 
	 
