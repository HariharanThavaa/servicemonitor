package com.yukon.main;

import java.util.Timer;
import java.util.TimerTask;

import com.yukon.bo.impl.ServiceMonitorImpl;
import com.yukon.vo.Caller;
import com.yukon.vo.Service;

public class ServiceMonitor {

	public static void main(String[] args) {
		Caller caller = new Caller("test");
				
		String host = "localhost";
		int port = 8080;
		Service service = new Service(host, port);
		ServiceMonitorImpl serviceMonitor = new ServiceMonitorImpl(service);
		
		serviceMonitor.register(caller, 1000, 2000);
		
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(serviceMonitor, 0, 1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

	}

}
