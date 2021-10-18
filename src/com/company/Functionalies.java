package com.company;
import java.io.*;
import java.util.ArrayList;

public class Functionalies {

    public Functionalies(){

    }
    public void recordDriverData(String name,String address,String email,String phonenumber,String plate){
        Driver driver = new Driver(name,address,email,phonenumber,plate);
        storeDriverRecord(driver);
        /**takes 5 entries and stores the object in the file*/
    }


    public void recordCarData(String name,int type,String model,String plate,int rentedday,boolean availablity,int mileAge){
        Car car = new Car(name,type,model,plate,rentedday,availablity,mileAge);
        storeCarRecord(car);
        /**takes 7 entries and stores them in the file*/
    }



    public void storeDriverRecord(Driver driver){
        try {
            try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("driver.txt",true))){
                outputStream.writeUTF(driver.getName());
                outputStream.writeUTF(driver.getAddress());
                outputStream.writeUTF(driver.getPhoneNumber());
                outputStream.writeUTF(driver.getEmail());
                outputStream.writeUTF(driver.getPlate());
            }
        }
        catch(FileNotFoundException excep){
            System.out.println("driver.txt file not found");
        }
        catch (IOException excep){
            System.out.println("io exception found in store driver record method");
        }
        /**writes one object fields in the file*/
    }



    public void storeCarRecord(Car car){
        try {
            boolean ava = car.getAvailability();
            String availablity;
            if(car.getAvailability()){availablity="true";}
            else{availablity = "false";}
            try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("car.txt",true))){
                outputStream.writeUTF(car.getName());
                outputStream.writeUTF(car.getType());
                outputStream.writeUTF(car.getModel());
                outputStream.writeUTF(car.getPlate());
                outputStream.writeInt(car.getRentedDay());
                outputStream.writeUTF(availablity);
                outputStream.writeInt(car.getMileAge());
                System.out.println("writing");
                System.out.println("murrrhuj >>>>>"+car.getAvailability());
            }
        }
        catch(FileNotFoundException excep){
            System.out.println("car.txt file not found");
        }
        catch (IOException excep){
            System.out.println("io exception found in store car record method");
        }
        /**writes one object fields in the file*/
    }



    public Driver[] readDriverRecord(){
        ArrayList<Driver> drivers = new ArrayList<>();
        try {
            try(DataInputStream inputStream = new DataInputStream(new FileInputStream("driver.txt"))){
                boolean status = true;
                while(status){
                    try{
                        String tempname = inputStream.readUTF();
                        String tempaddress = inputStream.readUTF();
                        String tempphonenum = inputStream.readUTF();
                        String tempemail = inputStream.readUTF();
                        String tempplate= inputStream.readUTF();
                        Driver tempdriver = new Driver(tempname,tempaddress,tempemail,tempphonenum,tempplate);
                        drivers.add(tempdriver);
                    }
                    catch (EOFException excep){
                        System.out.println("all file read");
                        status = false;
                    }
                }
            }
        }
        catch(IOException ioexep){
            System.out.println("IOException found in read driver record method");
            ioexep.printStackTrace();
        }
        Driver[] tempdriver = new Driver[drivers.size()];
        return drivers.toArray(tempdriver);
        /**reads all Driver objects stored in the file and returns an array of the Driver object*/
    }




    public Car[] readCarRecord(){
        ArrayList<Car> cars = new ArrayList<>();
        try {
            try(DataInputStream inputStream = new DataInputStream(new FileInputStream("car.txt"))){
                boolean status = true;
                while(status){
                    try{
                        String[] carType = {"CAB_AND_CHASSIS_TRUCK","CONVERTIBLE","COUPE","CROSSOVER","HATCHBACK","MINIVANS","PICKUP_TRUCK","SEDAN","STATION_WAGON","SUV","VAN"};
                        String tempname = inputStream.readUTF();
                        String temptype = inputStream.readUTF();
                        String tempmodel = inputStream.readUTF();
                        String tempplate = inputStream.readUTF();
                        int temprenteddate = inputStream.readInt();
                        String availablity = inputStream.readUTF();
                        int tempmileage = inputStream.readInt();
                        boolean tempavailability;
                        tempavailability = availablity.equals("true");
                        int temptype1=0;
                        for(int i=0;i<11;i++){
                            if(temptype.equals(carType[i]))temptype1 = i;
                        }
                        Car tempcar = new Car(tempname,temptype1,tempmodel,tempplate,temprenteddate,tempavailability,tempmileage);
                        //System.out.println(tempcar.getAvailability());
                        cars.add(tempcar);
                    }
                    catch (EOFException excep){
                        System.out.println("all file read");
                        status = false;
                    }
                }
            }
        }
        catch(IOException ioexep){
            System.out.println("IOException found in read driver record method");
            ioexep.printStackTrace();
        }
        Car[] tempcar = new Car[cars.size()];
        cars.toArray(tempcar);
        return tempcar;
        /**reads all Car objects form the file and returns a Car object array*/
    }



    public String[] searchDriverRecord(int choice,String item){
        /**choice 1 represents name, choice 2 represents address, choice 3 represents phonenumber, choice 4 represents email*/
        boolean status = false;
        Driver[] tempdriver = readDriverRecord();
        ArrayList<String> returnedDriver = new ArrayList<>();
        switch (choice){
            case 1:
                for (int i=0;i<tempdriver.length;i++){
                    if(status = tempdriver[i].getName().equals(item)){returnedDriver.add(String.format("%d",i));status = true;}
                }break;
            case 2:
                for (int i=0;i<tempdriver.length;i++){
                    if (status= tempdriver[i].getAddress().equals(item)){returnedDriver.add(String.format("%d",i));status = true;}
                }break;
            case 3:
                for (int i =0;i<tempdriver.length;i++){
                    if (status = tempdriver[i].getPhoneNumber().equals(item)){returnedDriver.add(String.format("%d",i));status = true;}
                }break;
            case 4:
                for (int i=0;i<tempdriver.length;i++){
                    if (status = tempdriver[i].getEmail().equals(item)){returnedDriver.add(String.format("%d",i));status = true;}
                }break;
            case 5:
                for (int i=0;i<tempdriver.length;i++){
                    if (status = tempdriver[i].getPlate().equals(item)){returnedDriver.add(String.format("%d",i));status = true;}
                }break;
            default:
                System.out.println("driver item not found");
                break;
        }
        String[] driversIndex = new String[returnedDriver.size()];
        returnedDriver.toArray(driversIndex);
        if(!status){returnedDriver.add(null);return driversIndex; }
        else return driversIndex;
    }



    public String[] searchCarRecord(int choice,String item){
        /**choice 1 represents name, choice 2 represents type, choice 3 represents model, choice 4 represents plate,
         * choice 5 represents rented days, choice 6 represents mileage*/
        boolean status = false;
        Car[] tempcar = readCarRecord();
        ArrayList<String> returnedCar = new ArrayList<>();
        switch (choice){
            case 1:
                for (int i=0;i<tempcar.length;i++){
                    if(tempcar[i].getName().equals(item)){returnedCar.add(String.format("%d",i));status = true;}
                }break;
            case 2:
                for (int i=0;i<tempcar.length;i++){
                    if (tempcar[i].getType().equals(item)){returnedCar.add(String.format("%d",i));status = true;}
                }break;
            case 3:
                for (int i =0;i<tempcar.length;i++){
                    if (tempcar[i].getModel().equals(item)){returnedCar.add(String.format("%d",i));status = true;}
                }break;
            case 4:
                for (int i=0;i<tempcar.length;i++){
                    if (tempcar[i].getPlate().equals(item)){returnedCar.add(String.format("%d",i));status = true;}
                }break;
            case 5:
                for (int i=0;i<tempcar.length;i++){
                    if (tempcar[i].getRentedDay()==Integer.parseInt(item)){returnedCar.add(String.format("%d",i));status = true;}
                }break;
            case 6:
                for (int i=0;i<tempcar.length;i++){
                    if (tempcar[i].getMileAge()==Integer.parseInt(item)){returnedCar.add(String.format("%d",i));status = true;}
                }break;
            case 7:
                if (item.equals("1")){
                    for (int i=0;i<tempcar.length;i++){
                        if (tempcar[i].getAvailability()){returnedCar.add(String.format("%d",i));status = true;System.out.println("in search case availablity");System.out.println(tempcar[i].getAvailability());}
                    }break;
                }
                else
                    for (int i=0;i<tempcar.length;i++){
                        if (tempcar[i].getAvailability()== false){returnedCar.add(String.format("%d",i));status = true;}
                    }break;
            default:
                System.out.println("car item not found");
                break;
        }
        String[] carsIndex = new String[returnedCar.size()];
        returnedCar.toArray(carsIndex);
        if(!status){returnedCar.add(null);return carsIndex; }
        else return carsIndex;
    }


    public boolean swapDriverRecord(Driver[] drivers,int i,int j){
        Driver temp = drivers[i];
        drivers[i] = drivers[j];
        drivers[j] = temp;
        return true;
        /**swaps two driver objects*/
    }



    public boolean swapCarRecord(Car[] cars,int i,int j){
        Car temp = cars[i];
        cars[i] = cars[j];
        cars[j] = temp;
        return true;
        /**swaps two car objects*/
    }



    public void deleteDriverRecord(int choice,int index,String item){//1 to delete single record and 2 to delete all
        File file = new File("C:\\Users\\Enoch\\IdeaProjects\\car rental\\driver.txt");
        switch(choice){
            case 1:
                Driver[] drivers = readDriverRecord();
                String[] driversIndex = searchDriverRecord(index,item);
                deleteDriverRecord(2);
                for(int i=0;i<drivers.length;i++){
                    for (int j=0;j<driversIndex.length;j++){
                        if (i==j){
                            String tempplate = drivers[j].getPlate();
                            updateCarRecord(6,4,tempplate,"1");
                            continue;
                        }
                        storeDriverRecord(drivers[i]);
                    }
                }
                break;
            case 2:
                boolean status = file.delete();
                if (status){
                    System.out.println("deleted");
                }
                try {
                    FileOutputStream out = new FileOutputStream("driver.txt");
                    out.close();
                }
                catch (Exception e){
                    System.out.println("file cannot be created");
                }
                break;
            case 3:
                file.delete();
                try {
                    FileOutputStream out = new FileOutputStream("driver.txt");
                    out.close();
                }
                catch (Exception e){
                    System.out.println("file cannot be created");
                }
                setAllCarAvailable();
            default: System.out.println("driver data deletion unsuccessful");
                break;
        }
        /**case one search and delete one record case two deletes all record from the file case three deletes all records and sets all cars available*/
    }
    public void deleteDriverRecord(int choice){//same method with one parameter
        deleteDriverRecord(choice,0," ");
        /**overloaded method for delete method with one parameter the case choice*/
    }



    public void deleteCarRecord(int choice,int index,String item){//1 to delete single record and 2 to delete all
        File file = new File("C:\\Users\\Enoch\\IdeaProjects\\car rental\\car.txt");
        switch(choice){
            case 1:
                String[] carIndex = searchCarRecord(index,item);
                Car[] cars = readCarRecord();
                deleteCarRecord(2,0," ");
                for(int i=0;i<cars.length;i++){
                    for (int j=0;j<carIndex.length;j++){
                        if (i==j){
                            continue;
                        }
                        storeCarRecord(cars[i]);
                        System.out.println("storing after deleting");
                    }
                }
                break;
            case 2:
                boolean status = file.delete();
                try {
                    FileOutputStream out = new FileOutputStream("car.txt");
                    out.close();
                }
                catch (Exception e){
                    System.out.println("file cannot be created");
                }
                break;
            default: System.out.println("car data deletion unsuccessful");
                break;
        }
        /**case one search and delete one record case two deletes all record from the file */
    }
    public void deleteCarRecord(int choice){
        deleteCarRecord(choice,0," ");
        /**overloaded method for delete method with one parameter the case choice*/
    }



    public void sortDriverRecord(int choice ){
        //1 for ascending order and 2 for descending order
        Driver[] drivers = readDriverRecord();
        Driver[] tempdriver = new Driver[drivers.length];
        boolean status = false;
        switch(choice){
            case 1:
                for (int i=0;i<drivers.length;i++){
                    for (int j=0;j<drivers.length;j++){
                        if(drivers[i].getName().compareToIgnoreCase(drivers[j].getName())<0){
                            status = swapDriverRecord(drivers,i,j);
                            if(status)System.out.println("swapping done");
                            status = false;
                        }
                    }
                }
                deleteDriverRecord(2,0," ");
                for (int z=0;z<drivers.length;z++){
                    storeDriverRecord(drivers[z]);
                }
                break;
            case 2:
                for (int i=0;i<drivers.length;i++){
                    for (int j=0;j<drivers.length;j++){
                        if(drivers[i].getName().compareToIgnoreCase(drivers[j].getName())>0){
                            status = swapDriverRecord(drivers,i,j);
                        }
                    }
                }
                deleteDriverRecord(2,0," ");
                for (int z=0;z<drivers.length;z++){
                    storeDriverRecord(drivers[z]);
                }
                break;
            default:System.out.println("sorting driver data unsuccessful");
                break;
        }
        /**reads all objects form the file and sorts them in ascending or descending order*/
    }




    public void sortCarRecord(int choice){
        //1 for ascending order and 2 for descending order
        Car[] cars = readCarRecord();
        boolean status = false;
        switch(choice){
            case 1:
                for (int i=0;i<cars.length;i++){
                    for (int j=0;j<cars.length;j++){
                        if((cars[i].getName().compareToIgnoreCase(cars[j].getName()))<0){
                            status = swapCarRecord(cars,i,j);
                        }
                    }
                }
                deleteCarRecord(2,0," ");
                for (int z=0;z<cars.length;z++){
                    storeCarRecord(cars[z]);
                }
                break;
            case 2:
                for (int i=0;i<cars.length;i++){
                    for (int j=0;j<cars.length;j++){
                        if((cars[i].getName().compareToIgnoreCase(cars[j].getName()))>0){
                            status = swapCarRecord(cars,i,j);
                        }
                    }
                }
                deleteCarRecord(2,0," ");
                for (int z=0;z<cars.length;z++){
                    storeCarRecord(cars[z]);
                }
                break;
            default:System.out.println("sorting car data unsuccessful");
                break;
        }
        /**reads all objects form the file and sorts them in ascending or descending order*/
    }




    public void updateDriverRecord(int choice,int search,String item,String replacment){
        String[] result = searchDriverRecord(search,item);
        Driver[] drivers = readDriverRecord();
        switch(choice){
            case 1:
                drivers[Integer.parseInt(result[0])].setName(replacment);
                deleteDriverRecord(2);
                for(int i=0;i<drivers.length;i++){
                    storeDriverRecord(drivers[i]);
                }
                break;
            case 2:
                drivers[Integer.parseInt(result[0])].setAddress(replacment);
                deleteDriverRecord(2,0," ");
                for(int i=0;i<drivers.length;i++){
                    storeDriverRecord(drivers[i]);
                }
                break;
            case 3:
                drivers[Integer.parseInt(result[0])].setPhoneNumber(replacment);
                deleteDriverRecord(2,0," ");
                for(int i=0;i<drivers.length;i++){
                    storeDriverRecord(drivers[i]);
                }
                break;
            case 4:
                drivers[Integer.parseInt(result[0])].setEmail(replacment);
                deleteDriverRecord(2);
                for(int i=0;i<drivers.length;i++){
                    storeDriverRecord(drivers[i]);
                }
                break;
            default:System.out.println("driver data update unsuccessful");
        }
        /**searches the items and if found updates single driver object*/
    }



    public void updateCarRecord(int choice,int search,String item,String replacment) {
        //search choice to be searched: item to be searched: replacment value to be replaced
        String[] result = searchCarRecord(search, item);
        Car[] cars = readCarRecord();
        if (result!=null){
            switch (choice) {
                case 1:
                    cars[Integer.parseInt(result[0])].setName(replacment);
                    deleteCarRecord(2);
                    for (int i = 0; i < cars.length; i++) {
                        storeCarRecord(cars[i]);
                    }
                    break;
                case 2:
                    cars[Integer.parseInt(result[0])].setType(Integer.parseInt(replacment));
                    deleteCarRecord(2);
                    for (int i = 0; i < cars.length; i++) {
                        storeCarRecord(cars[i]);
                    }
                    break;
                case 3:
                    cars[Integer.parseInt(result[0])].setModel(replacment);
                    deleteCarRecord(2);
                    for (int i = 0; i < cars.length; i++) {
                        storeCarRecord(cars[i]);
                    }
                    break;
                case 4:
                    cars[Integer.parseInt(result[0])].setPlate(replacment);
                    deleteCarRecord(2);
                    for (int i = 0; i < cars.length; i++) {
                        storeCarRecord(cars[i]);
                    }
                    break;
                case 5:
                    cars[Integer.parseInt(result[0])].setRentedDay(Integer.parseInt(replacment));
                    System.out.println("availablity"+cars[Integer.parseInt(result[0])].getAvailability());
                    deleteCarRecord(2);
                    for (int i = 0; i < cars.length; i++) {
                        System.out.println("availablity>>>"+cars[i].getAvailability());
                        storeCarRecord(cars[i]);
                    }
                    break;
                case 6:
                    if(replacment.equals("1")){
                        cars[Integer.parseInt(result[0])].setAvailabilty(true);
                        deleteCarRecord(2);
                        for (int i = 0; i < cars.length; i++) {
                            storeCarRecord(cars[i]);
                        }
                    }
                    else {
                        System.out.println("gruuuuuuuuuuuuuuuuuuuuuuuuuuuuuut");
                        cars[Integer.parseInt(result[0])].setAvailabilty(false);
                        if(!cars[Integer.parseInt(result[0])].getAvailability())System.out.println("nigghgggggggggggggggggggggggggggg");
                        deleteCarRecord(2);
                        for (int i = 0; i < cars.length; i++) {
                            System.out.println(cars[i].getAvailability());
                            storeCarRecord(cars[i]);
                        }
                    }
                    break;
                case 7:
                    cars[Integer.parseInt(result[0])].setMileAge(Integer.parseInt(replacment));
                    deleteCarRecord(2);
                    for (int i = 0; i < cars.length; i++) {
                        storeCarRecord(cars[i]);
                    }
                    break;
                default:
                    System.out.println("car data update unsuccessful");
            }
        }
        else System.out.println("cannot update because file not found");
        /**searches the items and if found updates single car object*/
    }



    public boolean setAllCarAvailable(){
        Car[] cars = readCarRecord();
        deleteCarRecord(2);
        for (int i=0;i<cars.length;i++){
            cars[i].setAvailabilty(true);
            storeCarRecord(cars[i]);
        }
        return true;
        /**reads all car record form file and sets availablity true*/
    }

}
