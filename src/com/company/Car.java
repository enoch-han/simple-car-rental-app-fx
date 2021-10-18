package com.company;

import java.io.Serializable;

public class Car implements Serializable {
    private  String name,type,model,plate;
    private int rentedDay =0;
    private int price,mileAge;
    private boolean availablity=true;
    private int[] priceByType = {300,300,200,350,300,400,300,200,250,300,400};
    private boolean constructorStatus = false;

    public boolean isConstructorStatus() {
        return constructorStatus;
    }

    public void setConstructorStatus(boolean constructorStatus) {
        this.constructorStatus = constructorStatus;
    }

    final String[] carType = {"CAB_AND_CHASSIS_TRUCK","CONVERTIBLE","COUPE","CROSSOVER","HATCHBACK","MINIVANS","PICKUP_TRUCK","SEDAN","STATION_WAGON","SUV","VAN"};

    public boolean setName(String name) {
        this.name = name;
        return true;
        /**sets the name field*/
    }

    public boolean setType(int type) {
        this.type = carType[type];
        return true;
        /**set the type field*/
    }
    public boolean setModel(String model){
        if (model.length()==4&&Integer.parseInt(model)<=2019){
            this.model = model;
            return true;
        }
        else return false;
        /**sets the model field*/
    }
    public boolean setPlate(String plate){
        if (plate.length()==5) {
            this.plate = plate;
            return true;
        }
        else return false;
        /**sets the plate field*/
    }
    public boolean setRentedDay(int rentedDay){
        if(rentedDay>0){
            setAvailabilty(false);
            this.rentedDay = rentedDay;
        }
        else this.rentedDay = 0;
        return true;
        /**sets the rented day field*/
    }
    public boolean setAvailabilty(Boolean availablity){
        if(availablity){setRentedDay(0);this.availablity = true;}
        else this.availablity = false;
        return true;
        /**sets the availablity field*/
    }
    public boolean setPrice(){
        for(int i=0;i<11;i++){
            if (type.equals(carType[i]))
                price = priceByType[i];
        }
        return true;
        /**set the price field*/
    }
    public boolean setMileAge(int mileAge){
        this.mileAge = mileAge;
        return true;
        /**sets the mile age field*/
    }
    public String getName() {
        return name;/**name getter*/
    }

    public String getType() {
        return type;/**type getter*/
    }

    public String getModel() {
        return model;/**model getter*/
    }

    public String getPlate() {
        return plate;/**plate getter*/
    }

    public int getRentedDay() {
        return rentedDay;/**rented day getter*/
    }

    public boolean getAvailability(){
        return availablity;
        /**availability getter*/
    }

    public int getMileAge(){
        return mileAge;
        /**milage getter*/
    }

    public int getPrice() {
        return price;
        /**prive getter*/
    }

    public boolean getConstructorStatus(){
        return this.constructorStatus;
        /**constructor status getter*/
    }

    public Car(String name,int type,String model,String plate,boolean availability,int mileAge){
        /**constructor with 6 intry type*/
        boolean nameStatus = setName(name);
        boolean typeStatus = setType(type);
        boolean modelStatus = setModel(model);
        boolean plateStatus = setPlate(plate);
        boolean avaStatus = setAvailabilty(availability);
        boolean mileAgeStatus = setMileAge(mileAge);
        setPrice();
        if (nameStatus==true&&typeStatus==true&&modelStatus==true&&plateStatus==true&&avaStatus==true&&mileAgeStatus==true)setConstructorStatus(true);
    }
    public Car(String name,int type,String model,String plate,int rentedDay,int mileAge){
        /**constructr with 6 intry type*/
        boolean nameStatus = setName(name);
        boolean typeStatus = setType(type);
        boolean modelStatus = setModel(model);
        boolean plateStatus = setPlate(plate);
        boolean rentstatus = setRentedDay(rentedDay);
        boolean mileAgeStatus = setMileAge(mileAge);
        setPrice();
        if (nameStatus==true&&typeStatus==true&&modelStatus==true&&plateStatus==true&&rentstatus==true&&mileAgeStatus==true)setConstructorStatus(true);
    }
    public Car(String name,int type,String model,String plate,int rentedDay,boolean availablity,int mileAge){
        boolean nameStatus = setName(name);
        boolean typeStatus = setType(type);
        boolean modelStatus = setModel(model);
        boolean plateStatus = setPlate(plate);
        boolean rentstatus = setRentedDay(rentedDay);
        boolean mileAgeStatus = setMileAge(mileAge);
        setPrice();
        if (nameStatus==true&&typeStatus==true&&modelStatus==true&&plateStatus==true&&rentstatus==true&&mileAgeStatus==true)setConstructorStatus(true);
    }
}
