package com.yukon.bo.impl;

import java.util.TimerTask;

import com.yukon.bo.ServiceMonitor;
import com.yukon.dao.ServiceDao;
import com.yukon.dao.impl.ServiceDaoImpl;
import com.yukon.vo.Caller;
import com.yukon.vo.Service;

/**
 * @author Thavaa Hariharan
 *
 */
public class ServiceMonitorImpl extends TimerTask implements ServiceMonitor{
	
	private Service service;
	//assuming it takes 20 secs to complete the task
	private long graceTime = 20000;
	private ServiceDao serviceDao =  new ServiceDaoImpl();
	
	

	public ServiceMonitorImpl(Service service) {
		super();
		this.service = service;
	}
	
	@Override
    public void run() {
      update(); 
      //If a service is not responding,
      if(!service.getStatus()){
    	//the monitor will wait for the grace time to expire before notifying any clients
    	  notifyCallers();	
      }
    		
    }
	
	private void notifyCallers() {
        try {
            Thread.sleep(graceTime);
            update();
            if(!service.getStatus()){
            	this.service.notifyCallers();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	

	// get the current status of a service
	public void update() {
		serviceDao.update(this.service);
	}

	// methods to register a caller to a Service
	public void register(Caller caller, long poll, long graceTime) {
		this.graceTime = graceTime;
		if( service != null && caller != null){
			service.register(caller, poll);
		}
		
	}

	// methods to unregister a caller from a Service
	public void unregister(Caller caller) {
		if( service != null && caller != null){
			this.service.unregister(caller);
		}
		
	}

}
