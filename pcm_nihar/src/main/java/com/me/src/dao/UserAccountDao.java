package com.me.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.UserAccount;

@Component
public class UserAccountDao extends MappedModelDao<UserAccount> {
	@Override
	public Class<UserAccount> getActualClass() {
		// TODO Auto-generated method stub
		return UserAccount.class;
	}		
	
	@SuppressWarnings("unchecked")
	public boolean isUsernameExist(String username) {
		
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("from UserAccount where username=:username");
		query.setParameter("username", username);		
		List<UserAccount> list = query.list();				 
		if(list.size() > 0) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public UserAccount getUserAccount(String username, String password) {
	
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("from UserAccount where username=:username and password=:password");
		query.setParameter("username", username);
		query.setParameter("password", password);		
		List<UserAccount> list = query.list();				 
		if(list.size() == 1) {
			return (UserAccount)list.get(0);
		}
		return null;
	}
}
