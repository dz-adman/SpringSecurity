package com.ad.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ad.app.Entity.Users;

@Repository
public interface UserDAO extends JpaRepository<Users, Long>
{
	public Users findByUserName(String userName);
}
