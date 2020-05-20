package com.ad.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ad.app.DAO.UserDAO;
import com.ad.app.Entity.Users;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	UserDAO userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		Users users = userDao.findByUserName(userName);
		if(users == null)	throw new UsernameNotFoundException("User 404!");
		
		return new UserPrincipal(users);
	}

}
