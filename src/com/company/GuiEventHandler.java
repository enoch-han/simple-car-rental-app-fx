package com.company;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class GuiEventHandler {
    Functionalies func = new Functionalies();


    public void insertPaneSubmit(TextField name, TextField address, TextField phonenum, TextField email, TableView<Car> table, TextField statusfield, TextField datefield,TextField result){
        Driver driver;
        String status;
        String tempname = name.getText();
        String tempaddress = address.getText();
        String tempphonenum = phonenum.getText();
        String tempemail = email.getText();
        ObservableList<Car> cars = table.getItems();

        Car tempcar = table.getSelectionModel().getSelectedItem();
        String day =datefield.getText();
        cars.remove(table.getSelectionModel().getSelectedItem());
        String tempplate = tempcar.getPlate();
        driver = new Driver(tempname,tempaddress,tempemail,tempphonenum,tempplate);
        if (driver.getConstructorStatus()){
            func.storeDriverRecord(driver);
            func.updateCarRecord(5,4,tempplate,day);
            //func.updateCarRecord(6,4,tempplate,"2");
            status = "Insertion successfull";
            statusfield.setText(status);
        }
        else status = "Insertion unsuccessfull enter correct value";

        name.clear();
        address.clear();
        phonenum.clear();
        email.clear();
        datefield.clear();
        result.clear();
        /**extract the data from the text fields and if records are valid records them in the file
         * if not sets status text to unsuccessfull*/

    }
    public void insertPaneCalculate( TableView<Car> table,TextField datefield,TextField result) {
        ObservableList<Car> cars = table.getItems();

        Car tempcar = table.getSelectionModel().getSelectedItem();
        int rentalDatePrice = Integer.parseInt(datefield.getText())*tempcar.getPrice();
        result.setText(""+rentalDatePrice);
        /**takes the cars price from the table and calculates the price*/
    }
    public void deletePaneDriverSearch(RadioButton name,RadioButton address,RadioButton phonenum,RadioButton email,TextField search,TableView<Driver> table){
        Main main = new Main();

        if (name.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(1,search.getText()));

        }
        else if (address.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(2,search.getText()));
        }
        else if (phonenum.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(3,search.getText()));
        }
        else if (email.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(4,search.getText()));
        }
        /**constructs the table for driver deletion*/
    }
    public void deletePaneSingleDriverDelete(RadioButton name,RadioButton address,RadioButton phonenum,RadioButton email,TextField search,TableView<Driver> table){
        Driver driver = table.getSelectionModel().getSelectedItem();
        Main main = new Main();
        ObservableList<Driver> drivers = table.getItems();
        if (name.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(6,4,plate,"1");
            func.deleteDriverRecord(1,1,search.getText());
            drivers.remove(table.getSelectionModel().getSelectedItem());
            search.clear();
        }
        else if (address.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(6,4,plate,"1");
            func.deleteDriverRecord(1,2,search.getText());
            search.clear();
            drivers.remove(table.getSelectionModel().getSelectedItem());
        }
        else if (phonenum.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(6,4,plate,"1");
            func.deleteDriverRecord(1,3,search.getText());
            search.clear();
            drivers.remove(table.getSelectionModel().getSelectedItem());
        }
        else if (email.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(6,4,plate,"1");
            func.deleteDriverRecord(1,4,search.getText());
            search.clear();
            drivers.remove(table.getSelectionModel().getSelectedItem());
        }
        /**takes the value form the table and deletes one driver obj from the file*/
    }
    public void deletePaneAllDriverDelete(){
        func.deleteDriverRecord(3);
        /**deletes all records from the file*/
    }
    public void insertPaneCarSubmit(TextField name, ComboBox<String> typebox, TextField model, TextField plate, TextField milage, TextField status){
        String[] carType = {"CAB_AND_CHASSIS_TRUCK","CONVERTIBLE","COUPE","CROSSOVER","HATCHBACK","MINIVANS","PICKUP_TRUCK","SEDAN","STATION_WAGON","SUV","VAN"};
        String tempname = name.getText();
        String temptype = typebox.getSelectionModel().getSelectedItem();

        int type=0;
        for(int i=0;i<carType.length;i++){
            if(temptype.equals(carType[i])){
                type = i;
            }
        }
        String tempmodel = model.getText();
        String tempplate = plate.getText();
        String tempmilage = milage.getText();
        Car car = new Car(tempname,type,tempmodel,tempplate,true,Integer.parseInt(tempmilage));
        if(car.getConstructorStatus()){
            func.storeCarRecord(car);
            name.clear();
            typebox.setPromptText("Select type here");
            model.clear();
            plate.clear();
            milage.clear();
            status.setText("recording successfull");
        }
        /**extracts the texts form the fields and stores them in the file*/
    }
    public void deletePaneCarSearch(RadioButton name,ComboBox<String> typebox,RadioButton model,RadioButton plate,RadioButton mileage,TextField search,TableView<Car> table){
        Main main = new Main();
        String type = typebox.getSelectionModel().getSelectedItem();
        if (name.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(1,search.getText()));
        }
        else if (model.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(3,search.getText()));
        }
        else if (plate.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(4,search.getText()));
        }
        else if (mileage.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(6,search.getText()));
        }
        if(name.isSelected()||model.isSelected()||plate.isSelected()||mileage.isSelected()&&typebox.getSelectionModel().getSelectedItem()!=null){
            table.getItems().addAll(main.getCarSearchTable(2,type));
        }
        /**constructs the search table*/
    }
    public void deletePaneSingleCarDelete(RadioButton name,ComboBox<String> typebox,RadioButton model,RadioButton plate,RadioButton mileage,TextField search,TableView<Car> table){
        Car car = table.getSelectionModel().getSelectedItem();
        Main main = new Main();
        ObservableList<Car> cars = table.getItems();
        if (name.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.deleteCarRecord(1,4,tempplate);
            cars.remove(table.getSelectionModel().getSelectedItem());
            search.clear();
        }
        else if (model.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.deleteCarRecord(1,4,tempplate);
            search.clear();
            cars.remove(table.getSelectionModel().getSelectedItem());
        }
        else if (plate.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.deleteCarRecord(1,4,tempplate);
            search.clear();
            cars.remove(table.getSelectionModel().getSelectedItem());
        }
        else if (mileage.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.deleteCarRecord(1,4,tempplate);
            search.clear();
            cars.remove(table.getSelectionModel().getSelectedItem());
        }
        else if (name.isSelected()||model.isSelected()||plate.isSelected()||mileage.isSelected()&&typebox.getSelectionModel().getSelectedItem()!=null){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.deleteCarRecord(1,4,tempplate);
            search.clear();
            cars.remove(table.getSelectionModel().getSelectedItem());
        }
        /**deletes the selected item from the table */
    }
    public void deletePaneAllCarDelete(){func.deleteCarRecord(2)/*deletes all records from the file*/;}
    public void updatePaneDriverSearch(RadioButton name,RadioButton address,RadioButton phonenum,RadioButton email,TextField search,TableView<Driver> table){
        Main main = new Main();
        System.out.println("ij fghj");

        if (name.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(1,search.getText()));

        }
        else if (address.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(2,search.getText()));
        }
        else if (phonenum.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(3,search.getText()));
        }
        else if (email.isSelected()){
            table.getItems().addAll(main.getDriverSearchTable(4,search.getText()));
        }
        /**constructors the update table*/
    }
    public void updatePaneDriverUpdate(RadioButton name,RadioButton address,RadioButton phonenum,RadioButton email,TextField search,TextField update,TableView<Driver> table){
        Driver driver = table.getSelectionModel().getSelectedItem();
        Main main = new Main();
        ObservableList<Driver> drivers = table.getItems();
        if (name.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            //func.updateCarRecord(6,4,plate,"1");
            driver.setName(update.getText());
            func.updateDriverRecord(1,5,plate,update.getText());
            drivers.remove(table.getSelectionModel().getSelectedItem());
            drivers.add(driver);
            search.clear();
            update.clear();
        }
        else if (address.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            //func.updateCarRecord(6,4,plate,"1");
            driver.setAddress(update.getText());
            func.updateDriverRecord(2,5,plate,update.getText());
            drivers.remove(table.getSelectionModel().getSelectedItem());
            drivers.add(driver);
            search.clear();
            update.clear();
        }
        else if (phonenum.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(6,4,plate,"1");
            driver.setPhoneNumber(update.getText());
            func.updateDriverRecord(3,5,plate,update.getText());
            drivers.remove(table.getSelectionModel().getSelectedItem());
            drivers.add(driver);
            search.clear();
            update.clear();
        }
        else if (email.isSelected()){
            String plate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(6,4,plate,"1");
            driver.setEmail(update.getText());
            func.updateDriverRecord(4,5,plate,update.getText());
            drivers.remove(table.getSelectionModel().getSelectedItem());
            drivers.add(driver);
            search.clear();
            update.clear();
        }
        /**updates the selected record from the file */
    }
    public void updatePaneCarSearch(RadioButton name,ComboBox<String> typebox,RadioButton model,RadioButton plate,RadioButton mileage,TextField search,TableView<Car> table){
        Main main = new Main();
        String type = typebox.getSelectionModel().getSelectedItem();
        if (name.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(1,search.getText()));
        }
        else if (model.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(3,search.getText()));
        }
        else if (plate.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(4,search.getText()));
        }
        else if (mileage.isSelected()){
            table.getItems().addAll(main.getCarSearchTable(6,search.getText()));
        }
        if(name.isSelected()||model.isSelected()||plate.isSelected()||mileage.isSelected()&&typebox.getSelectionModel().getSelectedItem()!=null){
            table.getItems().addAll(main.getCarSearchTable(2,type));
        }
        /**constructs the update car table*/
    }
    public void updatePaneCarUpdate(RadioButton name,ComboBox<String> typebox,RadioButton model,RadioButton plate,RadioButton mileage,TextField search,TextField update,TableView<Car> table){
        Car car = table.getSelectionModel().getSelectedItem();
        Main main = new Main();
        ObservableList<Car> cars = table.getItems();
        if (name.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(1,4,tempplate,update.getText());
            car.setName(update.getText());
            cars.remove(table.getSelectionModel().getSelectedItem());
            cars.add(car);
            search.clear();
            update.clear();
        }
        else if (model.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(3,4,tempplate,update.getText());
            car.setModel(update.getText());
            cars.remove(table.getSelectionModel().getSelectedItem());
            cars.add(car);
            search.clear();
            update.clear();
        }
        else if (plate.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(4,4,tempplate,update.getText());
            car.setPlate(update.getText());
            cars.remove(table.getSelectionModel().getSelectedItem());
            cars.add(car);
            search.clear();
            update.clear();
        }
        else if (mileage.isSelected()){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(7,4,tempplate,update.getText());
            car.setMileAge(Integer.parseInt(update.getText()));
            cars.remove(table.getSelectionModel().getSelectedItem());
            cars.add(car);
            search.clear();
            update.clear();
        }
        else if (name.isSelected()||model.isSelected()||plate.isSelected()||mileage.isSelected()&&typebox.getSelectionModel().getSelectedItem()!=null){
            String tempplate = table.getSelectionModel().getSelectedItem().getPlate();
            func.updateCarRecord(2,4,tempplate,update.getText());
            car.setName(update.getText());
            cars.remove(table.getSelectionModel().getSelectedItem());
            cars.add(car);
            search.clear();
            update.clear();
        }
        /**updates the selected record and stores then in the file*/
    }
    public void viewPaneDriverAscending(TextField status){
        func.sortDriverRecord(1);
        status.setText("sorted in ascending order");
        /**sorts the file in ascending order*/
    }
    public void viewPaneDriverDescending(TextField status){
        func.sortDriverRecord(2);
        status.setText("sorted in descending order");
        /**sorts all records in ascending order*/
    }
    public void viewPaneCarAscending(TextField status){
        func.sortCarRecord(1);
        status.setText("sorted in ascending order");
        /**sorts the file in ascending order*/
    }
    public void viewPaneCarDescending(TextField status){
        func.sortCarRecord(2);
        status.setText("sorted in descending order");
        /**sorts all records in ascending order*/
    }
    public void viewPaneDriverRefresh(TableView<Driver> table){
        Main main = new Main();
        ObservableList<Driver>drivers = table.getItems();
        drivers.removeAll(drivers);
        table.getItems().addAll(main.getDriverAllTable());
        /**refreshes the table*/
    }
    public void viewPaneCarRefresh(TableView<Car> table){
        Main main = new Main();
        ObservableList<Car> cars = table.getItems();
        cars.removeAll(cars);
        table.getItems().addAll(main.getCarAllTable());
        /**refreshes the table*/
    }
    public void insertPaneCarRefresh(TableView<Car> table){
        Main main = new Main();
        ObservableList<Car> cars = table.getItems();
        cars.removeAll(cars);
        table.getItems().addAll(main.getInsertTable());
        /**refreshes the table*/
    }
    public void rightPaneSearch(RadioButton driver,RadioButton car,TextField search,Label item,Label item1,Label item2,Label item3,Label item4,Label item5){
        Driver[] drivers = func.readDriverRecord();
        Car[] cars = func.readCarRecord();
        if (driver.isSelected()){
            boolean status = true;
            for(int i=1;i<7;i++){
                String[] result = func.searchDriverRecord(i,search.getText());
                if(result!=null){
                    item.setText("Name :>    ");
                    item1.setText(drivers[Integer.parseInt(result[0])].getName());
                    item2.setText("Phone num :>   ");
                    item3.setText(drivers[Integer.parseInt(result[0])].getPhoneNumber());
                    item4.setText("Plate :>   ");
                    item5.setText(drivers[Integer.parseInt(result[0])].getPlate());
                    search.clear();
                    break;
                }
            }
        }
        else if (car.isSelected()){
            for(int i=1;i<7;i++){
                String[] result = func.searchCarRecord(i,search.getText());
                if(result!=null){
                    item.setText("Name :>    ");
                    item1.setText(cars[Integer.parseInt(result[0])].getName());
                    item2.setText("Model num :>   ");
                    item3.setText(cars[Integer.parseInt(result[0])].getModel());
                    item4.setText("Plate :>   ");
                    item5.setText(cars[Integer.parseInt(result[0])].getPlate());
                    search.clear();
                    break;
                }
            }
        }
        /**quick search for the all record types and displays results main properties*/
    }
}
