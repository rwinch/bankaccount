package example.bankaccount;

import org.springframework.security.authorization.method.HandleAuthorizationDenied;

public class BankAccount {
	final int id;
	final String owner;
	final String accountNumber;
	final double balance;

	public BankAccount(int id, String owner, String accountNumber, double balance) {
		this.id = id;
		this.owner = owner;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	@PreAuthorizeOwner(account = "this")
	@HandleAuthorizationDenied(handlerClass = NullMethodAuthorizationDeniedHandler.class)
	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}
}
