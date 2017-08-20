package com.yukon.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thavaa Hariharan
 *
 */
public class Caller {

	private String name;
	private List<Service> services;
	private long graceTime;

	public Caller(String name) {
		super();
		this.name = name;
		this.services = new ArrayList<Service>();
	}

	public void update(String message) {
		//Display mesaage will be here
		System.out.println("Caller " + name + " :: " + message);
	}

	public void addService(Service service) {
		if (service == null) {
			throw new NullPointerException("Null Service");
		} else {
			services.add(service);
		}
	}

	public void removeService(Service service) {
		this.services.remove(service);
	}

	public String getName() {
		return name;
	}

	public long getGraceTime() {
		return graceTime;
	}

	public void setGraceTime(long graceTime) {
		this.graceTime = graceTime;
	}
	
	
}
