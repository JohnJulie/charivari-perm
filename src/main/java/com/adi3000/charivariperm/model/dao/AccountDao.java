package com.adi3000.charivariperm.model.dao;

import com.adi3000.charivariperm.model.dataobject.Account;
import com.adi3000.common.orm.dao.DAO;

public interface AccountDao extends DAO<Account>{

	public Account getByLogin(String login);
	
	public Account getByLoginPassword(String login, String password);
	
}
