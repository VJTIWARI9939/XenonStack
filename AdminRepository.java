 package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.cms.model.Admin;

@Component
public interface AdminRepository extends JpaRepository<Admin,Integer>{

	@Query("select a from Admin a where a.email =:email")
	public Admin getAdminByUserName(@Param("email") String email);
}
