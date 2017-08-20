/**
 * Class to Estabish the connection with existing website
 */
package com.yukon.dao.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.yukon.dao.ServiceDao;
import com.yukon.vo.Service;

/**
 * @author Thavaa Hariharan
 *
 */
public class ServiceDaoImpl implements ServiceDao {

	// Try Establishing TCP connection
	public void update(Service service) {
		try {
			Socket client = new Socket(service.getHost(), service.getPort());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			
			client.close();

			service.setStatus(true);
		} catch (Exception e) {
			service.setStatus(false);
		}
		
	}

}
