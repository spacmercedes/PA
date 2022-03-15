package com.example.Compulsory;

public class Computer extends Node implements Storage,Identifiable  {
   private String ipAddress;
   private String storageCapacity;

   public Computer(){

   }

    public Computer(String name, String macAddress, String location )
    {
        this.setName(name);
        this.setMacAddress(macAddress);
        this.setLocation(location);


    }

    @Override
    public String getIPAddress() {
        return this.ipAddress;
    }

    @Override
    public String getStorageCapacity() {
        return this.storageCapacity;
    }

    public void setIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setStorageCapacity(String storageCapacity) {
        storageCapacity = storageCapacity;
    }
}
