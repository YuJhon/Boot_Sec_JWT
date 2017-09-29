package com.jhon.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhon.entity.AccountCredentials;
import com.jhon.service.TokenAuthenticationService;
import com.jhon.util.JSONResult;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName JWTLoginFilter
 * @date 2017/9/29 16:12
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		/** JSON 反序列化成 AccountCredentials **/
		AccountCredentials creds = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);

		/** 返回一个验证令牌 **/
		return getAuthenticationManager()
				.authenticate(
						new UsernamePasswordAuthenticationToken(
								creds.getUsername(), creds.getPassword()
						));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(response, authResult.getName());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getOutputStream().println(JSONResult.fillResultString(500, "Internal Server Error!!!", JSONObject.NULL));
	}
}
