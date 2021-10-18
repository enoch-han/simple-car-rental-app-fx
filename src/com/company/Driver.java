package com.company;

import java.io.Serializable;

public class Driver implements Serializable {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String plate;
    boolean constructorStatus = false;
    public boolean setName(String name){
        /**sets the name field*/
        this.name = name;
        return true;
    }
    public boolean setAddress(String address){
        /**sets the address field*/
        this.address = address;
        return true;
    }
    public boolean setPhoneNumber(String phoneNumber){
        /**sets the phone field*/
        if (phoneNumber.charAt(0)=='+' && phoneNumber.charAt(1)=='2' && phoneNumber.charAt(2)=='5'&& phoneNumber.charAt(3)=='1' && phoneNumber.length()==13) {
            String tempPhoneNumber = phoneNumber.substring(3,phoneNumber.length());
            tempPhoneNumber.replaceFirst("1","0");
            this.phoneNumber = tempPhoneNumber;
            return true;
        }
        else if (phoneNumber.charAt(0)=='0' && phoneNumber.length()==10){
            this.phoneNumber = phoneNumber;
            return true;
        }
        else return false;
    }
    public boolean setEmail(String email){
        /**sets the email field*/
        this.email = email;
        return true;
    }
    public boolean setPlate(String plate){
        /**sets the plate field*/
        if (plate.length()==5) {
            this.plate = plate;
            return true;
        }
        else return false;
    }

    public String getName(){
        return this.name;
        /**name getter*/
    }
    public String getAddress(){
        return  this.address;
        /**address getter*/
    }
    public String getEmail(){
        return this.email;
        /**email getter*/
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
        /**phone number getter*/
    }
    public boolean getConstructorStatus(){
        return this.constructorStatus;
        /**constructor status getter*/
    }
    public String getPlate() {
        return plate;
        /**plate getter*/
    }
    public Driver(String name,String address,String email,String phoneNumber,String plate){
        /**constructor with 5 entry*/
        boolean nameStatus = setName(name);
        boolean addressStatus = setAddress(address);
        boolean emailStatus = setEmail(email);
        boolean phoneStatus = setPhoneNumber(phoneNumber);
        boolean plateStatus = setPlate(plate);
        if(nameStatus==true&&addressStatus==true&&emailStatus==true&&phoneStatus==true) this.constructorStatus = true;
    }

}
