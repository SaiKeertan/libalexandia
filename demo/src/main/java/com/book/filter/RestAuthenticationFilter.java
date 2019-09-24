package com.book.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;


@Configuration
@WebFilter(urlPatterns="/*")
public class RestAuthenticationFilter implements javax.servlet.Filter {
	public static final String AUTHENTICATION_HEADER = BillingEnumCodes.HEADER.getCode();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD,PUT,DELETE, OPTIONS");
		httpResponse.setHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Authorization, Access-Control-Request-Method, Access-Control-Request-Headers");
		httpResponse.setHeader("Access-Control-Expose-Headers", BillingEnumCodes.HEADER.getCode());
		if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
			filter.doFilter(request, response);
		} else {
			if (request instanceof HttpServletRequest) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

				AuthenticationService authenticationService = new AuthenticationService();
				boolean authenticationStatus = authenticationService.authenticate(authCredentials);
				if (authenticationStatus) {
					filter.doFilter(request, response);
				} else {
					if (response instanceof HttpServletResponse) {
						HttpServletResponse httpServletResponse = (HttpServletResponse) response;
						httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					}
				}
			}
		}
	}

	@Override
	public void destroy() {
		//Do Something..
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//Do Something..
	}
}
