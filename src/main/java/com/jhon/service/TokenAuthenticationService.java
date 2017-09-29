package com.jhon.service;

import com.jhon.util.JSONResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName TokenAuthenticationService
 * @date 2017/9/29 15:48
 */
public class TokenAuthenticationService {

	static final long EXPLRATIONTIME = 432_000_000; // 5天
	static final String SECRET = "P@ssw02d"; // JWT密码
	static final String TOKEN_PREFIX = "Bearer"; // Token前缀
	static final String HEADER_STRING = "Authorization"; // 存放Token的Header key

	/**
	 * 生成JWT
	 * @param response
	 * @param username
	 */
	public static void addAuthentication(HttpServletResponse response, String username) {
		// 生成JWT
		String JWT = Jwts.builder()
				.claim("authorities", "ROLE_ADMIN,AUTH_WRITE") // 保存权限（角色）
				.setSubject(username) // 用户名写入标题
				.setExpiration(new Date(System.currentTimeMillis() + EXPLRATIONTIME)) // 有效期设置
				.signWith(SignatureAlgorithm.HS512, SECRET) // 签名设置
				.compact();


		// 将JWT写入body
		try {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getOutputStream().println(JSONResult.fillResultString(0, "", JWT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证JWT
	 * @param request
	 * @return
	 */
	public static Authentication getAuthentication(HttpServletRequest request){
		// 从Header中获取到Token
		String token = request.getHeader(HEADER_STRING);

		if(token != null){
			// 解析Token
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX,""))
					.getBody();

			/** 获取用户名 **/
			String user = claims.getSubject();

			/** 获取到权限角色 **/
			List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

			/** 返回验证令牌 **/
			return user != null ? new UsernamePasswordAuthenticationToken(user,null,authorities):null;
		}
		return null;
	}

}
