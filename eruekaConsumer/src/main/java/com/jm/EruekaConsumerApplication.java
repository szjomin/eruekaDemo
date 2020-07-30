package com.jm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@RestController
@EnableEurekaClient
@SpringBootApplication
public class EruekaConsumerApplication {

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(EruekaConsumerApplication.class, args);
	}

	/**

	 * 实例化RestTemplate

	 * @return

	 */

	@LoadBalanced
	@Bean
	public RestTemplate rest() {

		return new RestTemplate();

	}


	@GetMapping(value = "/gotoUser")
	@ResponseBody
	public Map<String,Object> getUser(@RequestParam Integer id){

		Map<String,Object> data = new HashMap<>();
	    data = restTemplate.getForObject("http://service-provider/getUser?id="+id,Map.class);

        return data;
	}

}
