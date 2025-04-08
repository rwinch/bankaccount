package example.bankaccount;

public interface BankAccountService {

	@PostReadBankAccount
	BankAccount findById(int id);

	@PostReadBankAccount
	default BankAccount findByOwner(String owner) {
		return new BankAccount(1, owner, "12345", 543.21);
	}

	@PreAuthorizeOwner(account = "#account")
	default void save(BankAccount account) {}
}
