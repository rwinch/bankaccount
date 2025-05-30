package example.bankaccount;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class BankAccountServiceTest {
	@Autowired
	BankAccountService accounts;

	@Test
	@WithMockRob
	void findByIdWhenGranted() {
		this.accounts.findById(1);
	}

	@Test
	@WithMockJosh
	void findByIdWhenDenied() {
		assertThatExceptionOfType(AuthorizationDeniedException.class)
			.isThrownBy(() -> this.accounts.findById(1));
	}


	@Test
	@WithMockRob
	void findByOwnerWhenGranted() {
		this.accounts.findByOwner("rob");
	}

	@Test
	@WithMockJosh
	void findByOwnerWhenDenied() {
		assertThatExceptionOfType(AuthorizationDeniedException.class)
				.isThrownBy(() -> this.accounts.findByOwner("rob"));
	}

	@Test
	@WithMockAccountant
	void findByIdWhenAccountant() {
		this.accounts.findById(1);
	}


	@Test
	@WithMockAccountant
	void findByOwnerWhenAccountant() {
		this.accounts.findByOwner("rob");
	}

	@Test
	@WithMockAccountant
	void findByIdAccountNumberWhenAccountant() {
		BankAccount account = this.accounts.findById(1);
		assertThat(account.getAccountNumber()).isNull();
	}

	@Test
	@WithMockAccountant
	void findByOwnerAccountNumberWhenAccountant() {
		BankAccount account = this.accounts.findByOwner("rob");
		assertThat(account.getAccountNumber()).isNull();
	}


	@Test
	@WithMockRob
	void saveWhenGranted() {
		BankAccount account = new BankAccount(1, "rob", "12345", 543.21);
		this.accounts.save(account);
	}


	@Test
	@WithMockJosh
	void saveWhenDenied() {
		BankAccount account = new BankAccount(1, "rob", "12345", 543.21);
		assertThatExceptionOfType(AuthorizationDeniedException.class)
			.isThrownBy(() -> this.accounts.save(account));
	}
}