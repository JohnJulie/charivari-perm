package com.adi3000.charivariperm.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Account;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("accountDao")
public class AccountDao extends AbstractDAO<Account>  implements com.adi3000.charivariperm.model.dao.AccountDao{

	public Account getByLogin(String login) {
		Criteria req = getSession().createCriteria(Account.class)
    			.add(
	    				Restrictions.or(
							Restrictions.eq("login", login)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		Account account = (Account)req.uniqueResult();
		
		return account;
	}
	
	public Account getByLoginPassword(String login, String password) {
		Criteria req = getSession().createCriteria(Account.class)
    			.add(
	    				Restrictions.and(
							Restrictions.eq("login", login),
							Restrictions.eq("password", password)
	    				)
    				);
    	@SuppressWarnings("unchecked")
		Account account = (Account)req.uniqueResult();
    	
    	return account;
	}
	
}
