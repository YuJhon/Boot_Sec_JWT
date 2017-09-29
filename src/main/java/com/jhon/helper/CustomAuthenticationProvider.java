package com.jhon.helper;

import com.jhon.service.GrantedAuthorityImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

/**
 * <p>功能描述</br> 自定义身份验证组件 </p>
 * <p>提供密码验证功能，在实际使用时换成自己相应的验证逻辑，从数据库中取出、比对、赋予用户相应权限</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName CustomAuthenticationProvider
 * @date 2017/9/29 16:04
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		/** 获取认证的用户名 & 密码 **/
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		/** 校验逻辑 **/
		if ("admin".equals(name) && "123456".equals(password)) {
			/** 设置权限和角色 **/
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			authorities.add(new GrantedAuthorityImpl("AUTH_WRITE"));

			/** 生成令牌 **/
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
			return auth;
		} else {
			throw new BadCredentialsException("Password Is Error!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
