/**
 * Class to Estabish the connection with existing website
 */
package com.yukon.Dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yukon.dao.ServiceDao;
import com.yukon.dao.impl.ServiceDaoImpl;
import com.yukon.vo.Service;

/**
 * @author Thavaa Hariharan
 *
 */
public class ServiceDaoTest {

	private ServiceDao serviceDao = new ServiceDaoImpl();

	// Success Test
	@Test
	public void getStatusSuccessTest() {
		String host = "localhost";
		int port = 8080;
		Service service = new Service(host, port);
		serviceDao.update(service);
		assertTrue(service.getStatus());
	}

	// Fail Test
	@Test
	public void getStatusFailTest() {
		String host = "localhost";
		int port = 80;
		Service service = new Service(host, port);
		serviceDao.update(service);
		assertTrue(!service.getStatus());
	}

}
