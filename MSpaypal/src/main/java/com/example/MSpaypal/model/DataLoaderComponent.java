package com.example.MSpaypal.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataLoaderComponent {

	
private String ip;

	
	public DataLoaderComponent() {
		
	}
	
	public String getIp() {
		
		InetAddress ip;
        String hostname;
        String ip1 = "";
        try {
        	ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            String ipp = ip.toString();
            ip1 = ipp.split("/")[1];
            
        } catch (UnknownHostException e) {
 
            e.printStackTrace();
        }
		
		
		return ip1;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
