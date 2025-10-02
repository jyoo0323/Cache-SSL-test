package com.flab.cachewithssl.test;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class CacheTestController {
	private static final String RESPONSE = "Response from server"; // Initial ETag value

	@GetMapping
	public ResponseEntity<String> test() {
		System.out.println("요청이 도착함");

		return ResponseEntity.ok()
			.cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePublic())
			.eTag(Integer.toHexString(RESPONSE.hashCode()))
			.body(RESPONSE);
	}
}
