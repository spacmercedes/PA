package com.example.Compulsory;

public class Router extends Node implements Identifiable{
private String ipAddress;

public Router(){

}

    public Router(String name, String macAddress, String location  ){
        this.setName(name);
        this.setMacAddress(macAddress);
        this.setLocation(location);


    }

    @Override
    public String getIPAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


}
