package com.jhon.controller;

import com.jhon.util.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName UserController
 * @date 2017/9/29 15:26
 */
@RestController
public class UserController {

	@GetMapping("/users")
	public String userList(){
		ArrayList<String> users = new ArrayList<String>(){{
			add("jhon");
			add("xzl");
			add("zcc");
		}};
		return JSONResult.fillResultString(0,"",users);
	}


	@GetMapping("/hello")
	public String hello(){
		ArrayList<String> users = new ArrayList<String>(){{
			add("hello");
		}};
		return JSONResult.fillResultString(0,"",users);
	}

	@GetMapping("/world")
	public String world(){
		ArrayList<String> users = new ArrayList<String>(){{
			add("world");
		}};
		return JSONResult.fillResultString(0,"",users);
	}

}
