package com.voxloud.provisioning.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.repository.DeviceRepository;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

	@Autowired
	private DeviceRepository deviceRepository;

	private String username;

	private String password;

	@Value("${provisioning.domain}")
	private String domain;

	@Value("${provisioning.port}")
	private String port;

	@Value("${provisioning.codecs}")
	private String codecs;

	String result = "";

	public String getProvisioningFile(String macAddress) {

		Device device = deviceRepository.findById(macAddress).get();
		String model = device.getModel().toString();
		username = device.getUsername();
		password = device.getPassword();

		FileWriter file = null;
		try {
			if (model == "DESK") {

				file = new FileWriter("DESK.txt");
				System.out.println("content written");
				String message = "";
				if (device.getOverrideFragment() != null) {
					message = "username=" + username + System.lineSeparator() + "password=" + password
							+ System.lineSeparator() + "codecs=" + codecs + System.lineSeparator()
							+ device.getOverrideFragment();
				} else {
					message = "username=" + username + System.lineSeparator() + "password=" + password
							+ System.lineSeparator() + "domain=" + domain + System.lineSeparator() + "port=" + port
							+ System.lineSeparator() + "codecs=" + codecs + System.lineSeparator() + "timeout=" + 10;
				}
				file.write(message);
				file.flush();
				file.close();
				result = "DESK.txt file is Downloaded";
				return result;
				
			} else if (model == "CONFERENCE") {
				
				file = new FileWriter("CONFERENCE.json");


				JSONObject resultObject = new JSONObject();
				resultObject.put("username", username);
				resultObject.put("password", password);
				resultObject.put("codecs", codecs);
				if (device.getOverrideFragment() != null) {
					resultObject.put("OverrideFragment", device.getOverrideFragment());
				} else {
					resultObject.put("domain", domain);
					resultObject.put("port", port);
					resultObject.put("timeout", 10);
				}
				file.write(resultObject.toJSONString());
				file.flush();
				file.close();
				result = "CONFERENCE.json file is Downloaded";
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
