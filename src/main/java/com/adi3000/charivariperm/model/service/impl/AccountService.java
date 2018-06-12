package com.adi3000.charivariperm.model.service.impl;

import javax.inject.Inject;

import com.adi3000.charivariperm.model.dao.impl.AccountDao;
import com.adi3000.charivariperm.model.dataobject.Account;
import com.adi3000.common.orm.dao.DAOException;
import com.adi3000.common.orm.spring.TransactionalReadOnly;
import com.adi3000.common.orm.spring.TransactionalUpdate;

import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountService implements com.adi3000.charivariperm.model.service.AccountService {

	@Inject
	private AccountDao dao;
	
	@TransactionalUpdate
    public long saveAccount(Account account) {
    	long id = Long.MAX_VALUE;
        try {
			id = (long) dao.save(account);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return id;
    }
	
	@TransactionalUpdate
    public void deleteAccountById(Long id) {
        try {
			dao.delete(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@TransactionalReadOnly
    public Account findById(Long id) {
        return dao.find(id);
    }
	
	@TransactionalReadOnly
    public Account findByLogin(String login) {
        return dao.getByLogin(login);
    }
	
	@TransactionalReadOnly
	public Account connectByLogin(String login, String password) {
		return dao.getByLoginPassword(login, password);
	}
	
	@TransactionalUpdate
	public void updateAccount(Account account) {
		try {
			dao.update(account);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
