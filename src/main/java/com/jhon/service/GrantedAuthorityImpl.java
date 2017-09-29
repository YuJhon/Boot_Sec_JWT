package com.jhon.service;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName GrantedAuthorityImpl
 * @date 2017/9/29 15:46
 */
@Setter
public class GrantedAuthorityImpl implements GrantedAuthority {

	private String authority;

	public GrantedAuthorityImpl(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return null;
	}
}
