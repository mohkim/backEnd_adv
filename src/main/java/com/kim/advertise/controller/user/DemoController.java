package com.kim.advertise.controller.user;

import org.springframework.web.bind.annotation.GetMapping;

public class DemoController {

	@GetMapping("/")
	public  String  getHello() {
		
		return "hi Kemal";
	}
}
