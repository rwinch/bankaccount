package example.bankaccount;

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.authorization.method.MethodAuthorizationDeniedHandler;
import org.springframework.stereotype.Service;

@Service
public class NullMethodAuthorizationDeniedHandler
		implements MethodAuthorizationDeniedHandler {
	@Override
	public Object handleDeniedInvocation(MethodInvocation methodInvocation,
			AuthorizationResult authorizationResult) {
		return null;
	}
}
