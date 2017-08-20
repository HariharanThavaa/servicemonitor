package com.yukon.bo;

import com.yukon.vo.Caller;
import com.yukon.vo.Service;

public interface ServiceMonitor {

	// to get current status of given service
	public void update();

	// methods to register a caller to a Service
	public void register(Caller caller, long poll, long graceTime);

	// methods to unregister a caller from a service
	public void unregister(Caller caller);

}
