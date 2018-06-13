package com.adi3000.charivariperm.model.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.model.dataobject.Account;
import com.adi3000.charivariperm.model.enumeration.AccountType;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
@Transactional
public class AccountServiceTest {

	@Inject
	private transient AccountService accountService;
	private Account myParentAccount;
	private Account myAdminAccount;
	private long accountId;
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Before
	public void setUp () {
		System.out.print("---@Before---");
		this.myParentAccount = new Account();
		this.myParentAccount.setLogin("JohnJulie");
		this.myParentAccount.setMail("julie@gmail.com");
		this.myParentAccount.setPassword("julie123");
		this.myParentAccount.setType(AccountType.ADMIN);
		this.accountId = this.accountService.saveAccount(this.myParentAccount);
		
		this.myAdminAccount = new Account();
		this.myAdminAccount.setLogin("CrecheCharivari");
		this.myAdminAccount.setMail("charivari@gmail.com");
		this.myAdminAccount.setPassword("charivari123");
		this.myAdminAccount.setType(AccountType.ADMIN);
		this.accountService.saveAccount(this.myAdminAccount);
	}
	
	@Test
	public void testSavePermanence() {
		System.out.println("---testSaveAccount---");

		Account adminAccount = new Account();
		adminAccount.setLogin("Oph");
		adminAccount.setMail("oph@gmail.com");
		adminAccount.setPassword("oph123");
		adminAccount.setType(AccountType.ADMIN);
		
		
		long idAccount = this.accountService.saveAccount(adminAccount);
		assertNotNull(idAccount);
	}
	
	@Test
	public void testFindAccount() {
		System.out.println("---testFindAccount---");
		Account account = this.accountService.findById(this.accountId);
		assertNotNull(account);
	}
	
	@Test
	public void testFindAccountByLogin() {
		System.out.println("---testFindAccountByLogin---");
		Account account = this.accountService.findByLogin(this.myParentAccount.getLogin());
		assertNotNull(account);
	}
	
	@Test
	public void testFindAccountByLoginAndPassword() {
		System.out.println("---testFindAccountByLoginAndPassword---");
		Account account = this.accountService.connectByLogin("JohnJulie", "julie123");
		assertNotNull(account);
		
		System.out.println("---no account---");
		Account noAccount = this.accountService.connectByLogin("JohnJulie", "pwdTest");
		assertNotEquals(account, noAccount);
	}
	
	@Test
	public void testUpdateAccount() {
		System.out.println("---testUpdateAccount---");
		this.myParentAccount.setType(AccountType.PARENT);
		this.accountService.updateAccount(this.myParentAccount);
		Account account = this.accountService.findById(this.accountId);
		assertEquals(this.myParentAccount, account);
	}
}
