package com.yukon.Bo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yukon.bo.ServiceMonitor;
import com.yukon.bo.impl.ServiceMonitorImpl;
import com.yukon.vo.Service;

public class ServiceMonitorTest {

	private ServiceMonitor sm;

	// Success Test
	@Test
	public void getStatusSuccessTest() {
		String host = "localhost";
		int port = 8080;
		Service service = new Service(host, port);
		sm = new ServiceMonitorImpl(service);
		sm.update();
		assertTrue(service.getStatus());
	}

	// Fail Test
	@Test
	public void getStatusFailTest() {
		String host = "localhost";
		int port = 80;
		Service service = new Service(host, port);
		sm = new ServiceMonitorImpl(service);
		sm.update();
		assertTrue(!service.getStatus());
	}

}
