package com.voxloud.provisioning.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Device {

    @Id
    @Column(name = "mac_address")
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceModel model;

    @Column(name = "override_fragment")
    private String overrideFragment;

    private String username;

    private String password;

    public enum DeviceModel {
        CONFERENCE,
        DESK
    }
    
    // getters & setters

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public DeviceModel getModel() {
		return model;
	}

	public void setModel(DeviceModel model) {
		this.model = model;
	}

	public String getOverrideFragment() {
		return overrideFragment;
	}

	public void setOverrideFragment(String overrideFragment) {
		this.overrideFragment = overrideFragment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Device [macAddress=" + macAddress + ", model=" + model + ", overrideFragment=" + overrideFragment
				+ ", username=" + username + ", password=" + password + "]";
	}
    
	
    
}