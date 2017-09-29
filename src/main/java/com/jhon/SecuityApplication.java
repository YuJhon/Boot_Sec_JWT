package com.jhon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SecuityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuityApplication.class, args);
	}


	@GetMapping("/")
	public Map<String, String> hello() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("content", "hello freewolf~");
		return map;
	}
}
