package example.bankaccount;

import org.springframework.security.access.prepost.PostAuthorize;

public interface BankAccountService {

	@PostAuthorize("returnObject?.owner == authentication?.name")
	BankAccount findById(int id);

	@PostAuthorize("returnObject?.owner == authentication?.name")
	default BankAccount findByOwner(String owner) {
		return new BankAccount(1, owner, "12345", 543.21);
	}
}
