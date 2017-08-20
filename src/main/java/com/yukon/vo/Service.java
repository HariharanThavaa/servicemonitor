/**
 * 
 */
package com.yukon.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Thavaa Hariharan
 *
 */
public class Service {

	private String host;
	private int port;
	private boolean status;
	private Map<Caller, Long> callers;
	private final Object MUTEX = new Object();

	// A service is defined as a host/port combination
	public Service(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		this.callers = new HashMap<Caller, Long>();
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	// To check whether service is up or not
	public boolean getStatus() {
		return status;
	}

	// Service Dao to update the status
	public void setStatus(boolean status) {
		this.status = status;
	}

	public void register(Caller caller, long poll) {
		if (caller == null)
			throw new NullPointerException("Null Observer");
		if (poll < 1000)
			try {
				throw new Exception("Caller should not poll any service more frequently than once a second");
			} catch (Exception e) {
				e.printStackTrace();
			}
		synchronized (MUTEX) {
			if (!callers.containsKey(caller))
				callers.put(caller, poll);
		}
	}

	public void unregister(Caller caller) {
		synchronized (MUTEX) {
			callers.remove(caller);
		}
	}

	public void notifyCallers() {
		Set<Caller> callersToNotify = this.callers.keySet();
		if (status) {
			for (Caller caller : callersToNotify) {
				caller.update("Service host :: " + host + " portnumber " + port + " is up now");
			}
		} else {
			for (Caller caller : callersToNotify) {
				caller.update("Service host :: " + host + " portnumber " + port + " is down now");
			}
		}

	}
	
	

}
