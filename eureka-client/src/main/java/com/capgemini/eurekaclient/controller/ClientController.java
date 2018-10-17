package com.capgemini.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RefreshScope
@RestController
public class ClientController {
	private static final RestTemplate REST_TEMPLATE=new RestTemplate();
	@Autowired
	private EurekaClient eurekaClient;
	
	
	@GetMapping("/message")
	public String getmessage() {
		Application application=eurekaClient.getApplication("configuration-client");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		 String url = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"message";
		return REST_TEMPLATE.getForObject(url,String.class);
		
	}
	

}