package com.voxloud.provisioning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voxloud.provisioning.service.ProvisioningServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {

	@Autowired
    private ProvisioningServiceImpl provisioningServiceImpl;
	
	@GetMapping("/provisioning/{macAddress}")
	public String uploadFile(@PathVariable String macAddress) {
		String result = provisioningServiceImpl.getProvisioningFile(macAddress);
		return result;
	}
}