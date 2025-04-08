package example.bankaccount;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {
	BankAccountService accounts = new SecureBankAccountService();

	@Test
	void findByIdWhenGranted() {
		login("rob");
		this.accounts.findById(1);
	}

	@Test
	void findByIdWhenDenied() {
		login("josh");
		assertThatExceptionOfType(AuthorizationDeniedException.class)
			.isThrownBy(() -> this.accounts.findById(1));
	}

	void login(String user) {
		Authentication auth = new TestingAuthenticationToken(user, "password", "ROLE_USER");
		SecurityContextHolder.setContext(new SecurityContextImpl(auth));
	}

	@AfterEach
	void logout() {
		SecurityContextHolder.clearContext();
	}

}