package com.adi3000.charivariperm.model.service;

import com.adi3000.charivariperm.model.dataobject.Account;

public interface AccountService {
	
	long saveAccount(Account account);

	void deleteAccountById(Long id);
	
	Account findById(Long id);
	
	Account findByLogin(String login);
	
	Account connectByLogin(String login, String password);
	
	void updateAccount(Account account);
}
