package example.bankaccount;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

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
}