package com.company;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.image.Image;



public class Main extends Application{
    String statusBarMessage = "";
    Functionalies func = new Functionalies();
    GuiEventHandler gui = new GuiEventHandler();
    AddWindows win = new AddWindows();

    Image[] image = {new Image(getClass().getResource("resources/small/cabandchasis.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/convertible.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/coupe.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/crossover.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/hatchback.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/minivan.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/pickuptruck.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/sedan.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/stationwagon.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/suv.png").toExternalForm())
            ,new Image(getClass().getResource("resources/small/van.png").toExternalForm())};
    ImageView[] imageViews = {new ImageView(image[0])
            ,new ImageView(image[1])
            ,new ImageView(image[2])
            ,new ImageView(image[3])
            ,new ImageView(image[4])
            ,new ImageView(image[5])
            ,new ImageView(image[6])
            ,new ImageView(image[7])
            ,new ImageView(image[8])
            ,new ImageView(image[9])
            ,new ImageView(image[10])};

    public void setStatusBarMessage(String statusBarMessage) {
        this.statusBarMessage = statusBarMessage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setStatusBarMessage("in the main scene");
        BorderPane mainBorderPane = new BorderPane();


        mainBorderPane.setTop(topPane());
        mainBorderPane.setCenter(middlePane());
        mainBorderPane.setRight(rightPane());
        mainBorderPane.setBottom(statusBar());


        Scene primaryScene = new Scene(mainBorderPane,1000,600);

        primaryStage.setTitle("Car Rental System");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }







    //the top menu
    public MenuBar topPane(){
        // file menu
        Menu file = new Menu("_File");

        MenuItem exit = new MenuItem("Exit");
        file.getItems().addAll(exit);
        exit.setOnAction(e->System.exit(0));
        //about menu
        Menu about = new Menu("_About");
        MenuItem aboutUs = new MenuItem("About Us !!!");
        about.getItems().add(aboutUs);
        //aboutUs.setOnAction(e->win.aboutPane());
        //help menu
        Menu help = new Menu("_Help");
        MenuItem help2 = new MenuItem("Help");
        //help2.setOnAction(e->win.helpPane());
        help.getItems().add(help2);
        // organizing the bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file,about,help);
        return menuBar;

    }
    TableView<Driver> driverTableView;
    TableView<Car> carTableView;
    TableView<Car> carTableInsert;
    TableView<Driver> driverTableDelete;
    TableView<Car> carTableDelete;
    TableView<Driver> driverTableUpdate;
    TableView<Car> carTableUpdate;

    public VBox rightPane(){
        VBox parent1 = new VBox(10);
        parent1.setMinWidth(250);
        parent1.setStyle("-fx-background-color: dimgray");


        VBox child1 = new VBox(10);
        child1.setAlignment(Pos.TOP_CENTER);
        TextField searchArea = new TextField();
        searchArea.setPromptText("search");
        Button search = new Button("Search");
        RadioButton driver = new RadioButton("Driver");
        driver.setSelected(true);
        RadioButton car = new RadioButton("Car");
        ToggleGroup group = new ToggleGroup();
        HBox bar = new HBox(20);

        bar.getChildren().addAll(searchArea,search);
        group.getToggles().addAll(driver,car);



        HBox bar2 = new HBox(20);
        bar2.setAlignment(Pos.CENTER);
        bar2.getChildren().addAll(driver,car);
        child1.getChildren().addAll(bar,bar2);
        child1.setStyle("-fx-background-color: darkgrey");
        child1.setPadding(new Insets(20,20,20,20));



        StackPane paneTop = new StackPane();
        paneTop.getChildren().add(child1);
        paneTop.setPadding(new Insets(10,10,10,10));



        Label item = new Label();
        GridPane.setConstraints(item,0,0);
        GridPane.setHalignment(item,HPos.RIGHT);
        Label item1 = new Label();
        GridPane.setConstraints(item1,1,0);
        GridPane.setHalignment(item1,HPos.RIGHT);
        Label item2 = new Label();
        GridPane.setConstraints(item2,0,1);
        GridPane.setHalignment(item2,HPos.RIGHT);
        Label item3 = new Label();
        GridPane.setConstraints(item3,1,1);
        GridPane.setHalignment(item3,HPos.RIGHT);
        Label item4 = new Label();
        GridPane.setConstraints(item4,0,2);
        GridPane.setHalignment(item4,HPos.RIGHT);
        Label item5 = new Label();
        GridPane.setConstraints(item5,1,2);
        GridPane.setHalignment(item5,HPos.RIGHT);
        search.setOnAction(e->gui.rightPaneSearch(driver,car,searchArea,item,item1,item2,item3,item4,item5));

        GridPane bar3 = new GridPane();
        bar3.getChildren().addAll(item,item1,item2,item3,item4,item5);

        StackPane child2 = new StackPane();
        child2.getChildren().addAll(bar3);
        child2.setStyle("-fx-background-color: darkgrey");
        child2.setPadding(new Insets(20,20,20,20));
        StackPane paneBottom = new StackPane();
        paneBottom.getChildren().add(child2);
        paneBottom.setPadding(new Insets(10,10,10,10));
        paneBottom.setMinHeight(440);

        parent1.getChildren().addAll(paneTop,paneBottom);


        return parent1;
    }


    public TabPane middlePane(){
        Tab insert = new Tab("Insert records");
        insert.setClosable(false);
        insert.setContent(insertPane());

        Tab delete = new Tab("Delete records");
        delete.setContent(deletePane());
        delete.setClosable(false);

        Tab update = new Tab("Update records");
        update.setContent(updatePane());
        update.setClosable(false);

        Tab view = new Tab("View all records");
        view.setContent(viewPane());
        view.setClosable(false);



        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(insert,delete,update,view);
        return tabPane;
    }


    public TabPane insertPane(){

        Tab insertDriver = new Tab("Driver");

        GridPane insertDGrid = new GridPane();
        insertDGrid.setVgap(10);
        insertDGrid.setHgap(10);
        //input panes
        Label name = new Label("Name:");
        name.setAlignment(Pos.CENTER_LEFT);
        GridPane.setConstraints(name,0,0);
        TextField namefield = new TextField();
        GridPane.setConstraints(namefield,1,0);
        namefield.setPromptText("Enter your full name here");
        namefield.setPrefColumnCount(17);

        Label address = new Label("Address:");
        //address.setAlignment(Pos.CENTER_LEFT);
        GridPane.setConstraints(address,0,1);
        TextField addressfield = new TextField();
        GridPane.setConstraints(addressfield,1,1);
        addressfield.setPromptText("Enter your address here");


        Label phonenum = new Label("Phone Number:");
        GridPane.setConstraints(phonenum,0,2);
        TextField phonenumfield = new TextField();
        phonenumfield.setPromptText("Enter phone number");
        phonenumfield.setPrefColumnCount(10);
        GridPane.setConstraints(phonenumfield,1,2);

        Label email = new Label("Email:");
        GridPane.setConstraints(email,0,3);
        TextField emailfield = new TextField();
        emailfield.setPrefColumnCount(12);
        emailfield.setPromptText("Enter your email");
        GridPane.setConstraints(emailfield,1,3);

        Label date = new Label(" Return date:");
        GridPane.setConstraints(date,0,4);
        TextField datefield = new TextField();
        GridPane.setConstraints(datefield,1,4);
        datefield.setPromptText("Date");
        datefield.setMaxWidth(120);
        GridPane.setHalignment(datefield,HPos.LEFT);

        Button submitDriverRec = new Button("Submit");
        GridPane.setHalignment(submitDriverRec,HPos.RIGHT);
        GridPane.setConstraints(submitDriverRec,1,5);


        insertDGrid.getChildren().addAll(name,namefield,address,addressfield,phonenum,phonenumfield,email,emailfield,date,datefield,submitDriverRec);

        //price calculator
        VBox priceCalculatorPane = new VBox(20);
        HBox priceCalculatorPane1 = new HBox();
        priceCalculatorPane.setPadding(new Insets(10,10,10,100));

        Text text = new Text("PRICE CALCULATOR");
        text.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));

        Button calculate = new Button("Calculate");
        calculate.setMinWidth(200);

        Label label = new Label("Price is: ");

        TextField result = new TextField();
        result.setEditable(false);


        Label status = new Label("Status");
        status.setAlignment(Pos.CENTER);
        TextField statusField = new TextField("Please enter values correctly");
        statusField.setEditable(false);
        statusField.setMaxHeight(100);
        HBox bar = new HBox(10);
        bar.getChildren().addAll(status,statusField);
        Button refresh = new Button("Refresh");

        priceCalculatorPane1.getChildren().addAll(label,result);
        priceCalculatorPane.getChildren().addAll(text,calculate,priceCalculatorPane1,bar,refresh);
        HBox inputbox = new HBox(20);
        inputbox.setPadding(new Insets(20,10,10,30));
        inputbox.getChildren().addAll(insertDGrid,priceCalculatorPane);



        //table construction
        TableColumn<Car,String> nameColumn = new TableColumn<Car,String>("NAME");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("name"));

        TableColumn<Car,String> typeColumn = new TableColumn<Car,String>("TYPE");
        typeColumn.setMinWidth(150);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("type"));

        TableColumn<Car,String> modelColumn = new TableColumn<Car,String>("MODEL");
        modelColumn.setMinWidth(100);
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("model"));

        TableColumn<Car,Integer> mileColumn = new TableColumn<Car,Integer>("MILE AGE");
        mileColumn.setMinWidth(120);
        mileColumn.setCellValueFactory(new PropertyValueFactory<Car,Integer>("mileAge"));

        TableColumn<Car,Integer> pricecol = new TableColumn<>("Price per day");
        pricecol.setMinWidth(80);
        pricecol.setCellValueFactory(new PropertyValueFactory<Car,Integer>("price"));


        carTableInsert = new TableView<>();
        carTableInsert.getColumns().addAll(nameColumn,typeColumn,modelColumn,mileColumn,pricecol);
        carTableInsert.getItems().addAll(getInsertTable());



        //vbox to hold the input elements
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20,10,10,20));
        vBox.getChildren().addAll(inputbox,carTableInsert);
        submitDriverRec.setOnAction(e->gui.insertPaneSubmit(namefield,addressfield,phonenumfield,emailfield,carTableInsert,statusField,datefield,result));
        calculate.setOnAction(e->gui.insertPaneCalculate(carTableInsert,datefield,result));
        refresh.setOnAction(e->gui.insertPaneCarRefresh(carTableInsert));
        insertDriver.setContent(vBox);
        insertDriver.setClosable(false);




        Label carname = new Label("Name:");
        GridPane.setConstraints(carname,0,0);
        TextField carnamefield = new TextField();
        carnamefield.setPromptText("Enter name here");
        GridPane.setConstraints(carnamefield,1,0);

        Label type = new Label("Type :");
        GridPane.setConstraints(type,0,1);
        String[] typename = {"CAB_AND_CHASSIS_TRUCK","CONVERTIBLE","COUPE","CROSSOVER","HATCHBACK","MINIVANS","PICKUP_TRUCK","SEDAN","STATION_WAGON","SUV","VAN"};
        ComboBox<String> typebox = new ComboBox<>();
        GridPane.setConstraints(typebox,1,1);
        typebox.getItems().addAll(typename);
        typebox.setPromptText("Select type here ");

        Label model = new Label("Model:");
        GridPane.setConstraints(model,0,2);
        TextField modelfield = new TextField();
        modelfield.setPromptText("Enter model here");
        GridPane.setConstraints(modelfield,1,2);

        Label plate = new Label("Plate:");
        GridPane.setConstraints(plate,0,3);
        TextField platefield = new TextField();
        platefield.setPromptText("Enter plate number here");
        GridPane.setConstraints(platefield,1,3);

        Label mileage = new Label("Mile Age:");
        GridPane.setConstraints(mileage,0,5);
        TextField milefield = new TextField();
        milefield.setMaxWidth(100);
        GridPane.setHalignment(milefield,HPos.LEFT);
        milefield.setPromptText("mile age");
        GridPane.setConstraints(milefield,1,5);

        Button submitcar = new Button("Submit");
        GridPane.setConstraints(submitcar,1,6);
        GridPane.setHalignment(submitcar,HPos.RIGHT);

        GridPane grandchild1 = new GridPane();
        grandchild1.setHgap(10);
        grandchild1.setVgap(20);
        grandchild1.getChildren().addAll(carname,carnamefield,type,typebox,model,modelfield,plate,platefield,mileage,milefield,submitcar);



        GridPane imageBox = new GridPane();
        imageBox.add(imageViews[0],0,0);
        imageBox.add(imageViews[1],1,0);
        imageBox.add(imageViews[2],2,0);
        imageBox.add(imageViews[3],0,1);
        imageBox.add(imageViews[4],1,1);
        imageBox.add(imageViews[5],2,1);
        imageBox.add(imageViews[6],0,2);
        imageBox.add(imageViews[7],1,2);
        imageBox.add(imageViews[8],2,2);
        imageBox.add(imageViews[9],0,3);
        imageBox.add(imageViews[10],1,3);

        imageBox.setVgap(5);
        imageBox.setHgap(5);
        HBox child1  = new HBox(20);
        child1.setPadding(new Insets(20,20,20,40));
        child1.getChildren().addAll(grandchild1,imageBox);

        Label recstatus = new Label("Status:");
        TextField statusfield = new TextField("please enter correct value");
        statusfield.setPromptText("");
        statusfield.setEditable(false);
        HBox child2 = new HBox();
        child2.setPadding(new Insets(20,30,10,20));
        child2.getChildren().addAll(recstatus,statusfield);

        VBox parent1 = new VBox(20);
        HBox imagePane = new HBox();

        parent1.getChildren().addAll(child1,imagePane,child2);

        submitcar.setOnAction(e->gui.insertPaneCarSubmit(carnamefield,typebox,modelfield,platefield,milefield,statusfield));
        //tab pane to hold the tabs
        Tab insertCar = new Tab("Car");
        insertCar.setContent(parent1);
        insertCar.setClosable(false);



        TabPane insertPane = new TabPane();
        insertPane.getTabs().addAll(insertDriver,insertCar);
        insertPane.setSide(Side.LEFT);

        return insertPane;
    }


    public VBox deletePane(){
        Text text1 = new Text("Driver deletion:");
        text1.setFont(Font.font("New Times Roman",FontWeight.BOLD,20));
        Label label = new Label("Search by:");

        RadioButton name = new RadioButton("Name");
        name.setSelected(true);
        RadioButton address = new RadioButton("address");
        RadioButton phonenum = new RadioButton("phone");
        RadioButton email = new RadioButton("email");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(name,address,phonenum,email);

        HBox bar = new HBox(10);
        bar.getChildren().addAll(label,name,address,phonenum,email);

        TextField field = new TextField();
        field.setPromptText("Search");
        Button deletionsearch = new Button("Search");

        HBox bar2 = new HBox(10);
        bar2.getChildren().addAll(field,deletionsearch);

        Button delete = new Button("Delete");
        Button deleteall = new Button("Delete all");

        VBox element = new VBox(20);
        element.getChildren().addAll(text1,bar,bar2,delete,deleteall);
        VBox child1 = new VBox(10);
        child1.getChildren().add(element);


        TableColumn<Driver,String> namecol = new TableColumn<>("Name");
        namecol.setMinWidth(100);
        namecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("name"));
        TableColumn<Driver,String> phonecol = new TableColumn<>("Phone");
        phonecol.setMinWidth(50);
        phonecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("phoneNumber"));
        TableColumn<Driver,String> emailcol = new TableColumn<>("Email");
        emailcol.setMinWidth(50);
        emailcol.setCellValueFactory(new PropertyValueFactory<Driver,String>("email"));
        TableColumn<Driver,String> platecol = new TableColumn<>("Plate");
        platecol.setMinWidth(50);
        platecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("plate"));

        driverTableDelete = new TableView<>();

        driverTableDelete.getColumns().addAll(namecol,phonecol,emailcol,platecol);

        deletionsearch.setOnAction(e->gui.deletePaneDriverSearch(name,address,phonenum,email,field,driverTableDelete));
        delete.setOnAction(e->gui.deletePaneSingleDriverDelete(name,address,phonenum,email,field,driverTableDelete));
        deleteall.setOnAction(e->gui.deletePaneAllDriverDelete());




        VBox child2 = new VBox(10);
        child2.getChildren().add(driverTableDelete);
        HBox parent1 = new HBox(10);
        parent1.setPadding(new Insets(10,10,10,20));

        parent1.getChildren().addAll(child1,child2);

        Text text2 = new Text("Car deletion:");
        text2.setFont(Font.font("New Times Roman",FontWeight.BOLD,20));

        RadioButton name2 = new RadioButton("Name");
        name2.setSelected(true);
        ComboBox<String> type = new ComboBox<>();
        String[] typename = {"CAB_AND_CHASSIS_TRUCK","CONVERTIBLE","COUPE","CROSSOVER","HATCHBACK","MINIVANS","PICKUP_TRUCK","SEDAN","STATION_WAGON","SUV","VAN"};
        type.getItems().addAll(typename);
        type.setPromptText("type");

        RadioButton model = new RadioButton("model");
        RadioButton plate = new RadioButton("plate");
        RadioButton mileage = new RadioButton("mileage");
        ToggleGroup group2 = new ToggleGroup();
        group2.getToggles().addAll(name2,model,plate,mileage);

        HBox bar3 = new HBox(10);
        bar3.getChildren().addAll(name2,model,plate,mileage);

        TextField field1 = new TextField();
        field1.setPromptText("Search");
        Button deletionsearch1 = new Button("Search");
        HBox bar4 = new HBox(10);
        bar4.getChildren().addAll(field1,deletionsearch1);


        Button delete1 = new Button("Delete");
        Button deleteall1 = new Button("Delete all");

        VBox element1 = new VBox(20);
        element1.getChildren().addAll(text2,bar3,type,bar4,delete1,deleteall1);
        VBox child12 = new VBox(10);
        child12.getChildren().add(element1);


        TableColumn<Car,String> namecarcol = new TableColumn<>("Name");
        namecarcol.setMinWidth(100);
        namecarcol.setCellValueFactory(new PropertyValueFactory<Car,String>("name"));
        TableColumn<Car,String> typecol = new TableColumn<>("Type");
        typecol.setMinWidth(50);
        typecol.setCellValueFactory(new PropertyValueFactory<Car,String>("type"));
        TableColumn<Car,String> modelcol = new TableColumn<>("Model");
        modelcol.setMinWidth(50);
        modelcol.setCellValueFactory(new PropertyValueFactory<Car,String>("model"));
        TableColumn<Car,String> platecarcol = new TableColumn<>("Plate");
        platecarcol.setMinWidth(50);
        platecarcol.setCellValueFactory(new PropertyValueFactory<Car,String>("plate"));
        TableColumn<Car,String> mileagecol = new TableColumn<>("Mile Age");
        mileagecol.setMinWidth(50);
        mileagecol.setCellValueFactory(new PropertyValueFactory<Car,String>("mileAge"));

        carTableDelete = new TableView<>();
        carTableDelete.getColumns().addAll(namecarcol,typecol,modelcol,platecarcol,mileagecol);


        deletionsearch1.setOnAction(e ->gui.deletePaneCarSearch(name2,type,model,plate,mileage,field1,carTableDelete) );
        delete1.setOnAction(e->gui.deletePaneSingleCarDelete(name2,type,model,plate,mileage,field1,carTableDelete));
        deleteall1.setOnAction(e->gui.deletePaneAllCarDelete());


        VBox child22 = new VBox(10);
        child22.getChildren().add(carTableDelete);
        HBox parent12 = new HBox(10);
        parent12.setPadding(new Insets(10,10,10,20));

        parent12.getChildren().addAll(child12,child22);



        VBox grandparent1 = new VBox();
        grandparent1.getChildren().addAll(parent1,parent12);
        return grandparent1;
    }


    public TabPane updatePane(){


        Label label = new Label("Search by:");

        RadioButton name = new RadioButton("Name");
        name.setSelected(true);
        RadioButton address = new RadioButton("address");
        RadioButton phonenum = new RadioButton("phone");
        RadioButton email = new RadioButton("email");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(name,address,phonenum,email);

        HBox bar = new HBox(10);
        bar.getChildren().addAll(label,name,address,phonenum,email);

        TextField field = new TextField();
        field.setPromptText("Search");
        Button updatesearch = new Button("Search");
        Label updatelabel = new Label("Update to >>");
        TextField updateField = new TextField();
        updateField.setPromptText("update to ...");
        HBox bar2 = new HBox(10);
        bar2.getChildren().addAll(field,updatesearch,updatelabel,updateField);

        Button update = new Button("Update");

        VBox element = new VBox(20);
        element.getChildren().addAll(bar,bar2,update);
        VBox child1 = new VBox(10);
        child1.getChildren().add(element);


        TableColumn<Driver,String> namecol = new TableColumn<>("Name");
        namecol.setMinWidth(150);
        namecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("name"));
        TableColumn<Driver,String> phonecol = new TableColumn<>("Phone");
        phonecol.setMinWidth(100);
        phonecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("phoneNumber"));
        TableColumn<Driver,String> emailcol = new TableColumn<>("Email");
        emailcol.setMinWidth(100);
        emailcol.setCellValueFactory(new PropertyValueFactory<Driver,String>("email"));
        TableColumn<Driver,String> addresscol = new TableColumn<>("Address");
        addresscol.setMinWidth(100);
        addresscol.setCellValueFactory(new PropertyValueFactory<Driver,String>("address"));
        TableColumn<Driver,String> platecol = new TableColumn<>("Plate");
        platecol.setMinWidth(80);
        platecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("plate"));

        driverTableUpdate = new TableView<>();
        driverTableUpdate.getColumns().addAll(namecol,phonecol,emailcol,addresscol,platecol);



        driverTableUpdate.getItems().addAll(getDriverSearchTable(1,"asaye"));
        updatesearch.setOnAction(e->gui.updatePaneDriverSearch(name,address,phonenum,email,field,driverTableUpdate));
        update.setOnAction(e->gui.updatePaneDriverUpdate(name,address,phonenum,email,field,updateField,driverTableUpdate));



        HBox child2 = new HBox(10);
        child2.getChildren().add(driverTableUpdate);
        VBox parent1 = new VBox(10);
        parent1.setPadding(new Insets(10,10,10,20));

        parent1.getChildren().addAll(child1,child2);

        Tab driver = new Tab("Driver");
        driver.setClosable(false);
        driver.setContent(parent1);





        RadioButton name2 = new RadioButton("Name");
        name2.setSelected(true);
        ComboBox<String> type = new ComboBox<>();
        String[] typename = {"CAB_AND_CHASSIS_TRUCK","CONVERTIBLE","COUPE","CROSSOVER","HATCHBACK","MINIVANS","PICKUP_TRUCK","SEDAN","STATION_WAGON","SUV","VAN"};
        type.getItems().addAll(typename);
        type.setPromptText("type");

        RadioButton model = new RadioButton("model");
        RadioButton plate = new RadioButton("plate");
        RadioButton mileage = new RadioButton("mileage");
        ToggleGroup group2 = new ToggleGroup();
        group2.getToggles().addAll(name2,model,plate,mileage);

        HBox bar3 = new HBox(10);
        bar3.getChildren().addAll(name2,model,plate,mileage);

        TextField field1 = new TextField();
        field1.setPromptText("Search");
        Button updatesearch1 = new Button("Search");
        TextField updateto = new TextField();
        Label updatelabel1 = new Label("Update to >>");
        updateto.setPromptText("update to ...");
        HBox bar4 = new HBox(10);
        bar4.getChildren().addAll(field1,updatesearch1,updatelabel1,updateto);

        Button updatecar = new Button("Update");

        VBox element1 = new VBox(20);
        element1.getChildren().addAll(bar3,type,bar4,updatecar);
        VBox child12 = new VBox(10);
        child12.getChildren().add(element1);


        TableColumn<Car,String> namecarcol = new TableColumn<>("Name");
        namecarcol.setMinWidth(150);
        namecarcol.setCellValueFactory(new PropertyValueFactory<Car,String>("name"));
        TableColumn<Car,String> typecol = new TableColumn<>("Type");
        typecol.setMinWidth(180);
        typecol.setCellValueFactory(new PropertyValueFactory<Car,String>("type"));
        TableColumn<Car,String> modelcol = new TableColumn<>("Model");
        modelcol.setMinWidth(90);
        modelcol.setCellValueFactory(new PropertyValueFactory<Car,String>("model"));
        TableColumn<Car,String> platecarcol = new TableColumn<>("Plate");
        platecarcol.setMinWidth(120);
        platecarcol.setCellValueFactory(new PropertyValueFactory<Car,String>("plate"));
        TableColumn<Car,String> mileagecol = new TableColumn<>("Mile Age");
        mileagecol.setMinWidth(120);
        mileagecol.setCellValueFactory(new PropertyValueFactory<Car,String>("mileAge"));

        carTableUpdate = new TableView<>();
        carTableUpdate.getColumns().addAll(namecarcol,typecol,modelcol,platecarcol,mileagecol);



        carTableUpdate.getItems().addAll(getCarSearchTable(1,"yaris"));
        updatesearch1.setOnAction(e->gui.updatePaneCarSearch(name2,type,model,plate,mileage,field1,carTableUpdate));
        updatecar.setOnAction(e->gui.updatePaneCarUpdate(name2,type,model,plate,mileage,field1,updateto,carTableUpdate));


        HBox child22 = new HBox(10);
        child22.getChildren().add(carTableUpdate);
        VBox parent12 = new VBox(10);
        parent12.setPadding(new Insets(10,10,10,20));

        parent12.getChildren().addAll(child12,child22);

        Tab car = new Tab("Car");
        car.setContent(parent12);
        car.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);
        tabPane.getTabs().addAll(driver,car);
        return tabPane;
    }

    public TabPane viewPane(){

        TableColumn<Driver,String> namecol = new TableColumn<>("Name");
        namecol.setMinWidth(150);
        namecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("name"));
        TableColumn<Driver,String> phonecol = new TableColumn<>("Phone");
        phonecol.setMinWidth(100);
        phonecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("phoneNumber"));
        TableColumn<Driver,String> emailcol = new TableColumn<>("Email");
        emailcol.setMinWidth(100);
        emailcol.setCellValueFactory(new PropertyValueFactory<Driver,String>("email"));
        TableColumn<Driver,String> addresscol = new TableColumn<>("Address");
        addresscol.setMinWidth(100);
        addresscol.setCellValueFactory(new PropertyValueFactory<Driver,String>("address"));
        TableColumn<Driver,String> platecol = new TableColumn<>("Plate");
        platecol.setMinWidth(80);
        platecol.setCellValueFactory(new PropertyValueFactory<Driver,String>("plate"));


        driverTableView = new TableView<>();


        driverTableView.getItems().addAll(getDriverAllTable());
        driverTableView.getColumns().addAll(namecol,phonecol,emailcol,addresscol,platecol);



        HBox bar1 = new HBox(10);
        bar1.getChildren().add(driverTableView);

        Label label = new Label("Order file:");
        Button ascending = new Button("Ascending");
        Button descending = new Button("descending");
        TextField status = new TextField("status");
        Button refresh = new Button("Refresh");
        refresh.setOnAction(e->gui.viewPaneDriverRefresh(driverTableView));
        status.setMinWidth(300);
        ascending.setOnAction(e->gui.viewPaneDriverAscending(status));
        descending.setOnAction(e->gui.viewPaneDriverDescending(status));
        status.setEditable(false);
        HBox bar2 = new HBox(10);
        bar2.getChildren().addAll(label,ascending,descending,status);
        bar2.setPadding(new Insets(20,40,10,10));
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20,30,10,10));
        vBox.getChildren().addAll(refresh,bar1,bar2);

        Tab driver = new Tab("Driver");
        driver.setContent(vBox);
        driver.setClosable(false);


        TableColumn<Car,String> namecarcol = new TableColumn<>("Name");
        namecarcol.setMinWidth(150);
        namecarcol.setCellValueFactory(new PropertyValueFactory<Car,String>("name"));
        TableColumn<Car,String> typecol = new TableColumn<>("Type");
        typecol.setMinWidth(180);
        typecol.setCellValueFactory(new PropertyValueFactory<Car,String>("type"));
        TableColumn<Car,String> modelcol = new TableColumn<>("Model");
        modelcol.setMinWidth(90);
        modelcol.setCellValueFactory(new PropertyValueFactory<Car,String>("model"));
        TableColumn<Car,String> platecarcol = new TableColumn<>("Plate");
        platecarcol.setMinWidth(120);
        platecarcol.setCellValueFactory(new PropertyValueFactory<Car,String>("plate"));
        TableColumn<Car,String> mileagecol = new TableColumn<>("Mile Age");
        mileagecol.setMinWidth(120);
        mileagecol.setCellValueFactory(new PropertyValueFactory<Car,String>("mileAge"));
        TableColumn<Car,Boolean> avacol = new TableColumn<>("Availability");
        avacol.setMinWidth(80);
        avacol.setCellValueFactory(new PropertyValueFactory<Car,Boolean>("availability"));
        TableColumn<Car,Integer> pricecol = new TableColumn<>("Price per day");
        pricecol.setMinWidth(80);
        pricecol.setCellValueFactory(new PropertyValueFactory<Car,Integer>("price"));
        TableColumn<Car,Integer> daycol = new TableColumn<>("rented for");
        daycol.setMinWidth(80);
        daycol.setCellValueFactory(new PropertyValueFactory<Car,Integer>("rentedDay"));


        carTableView = new TableView<>();
        carTableView.getColumns().addAll(namecarcol,typecol,modelcol,platecarcol,mileagecol,avacol,pricecol,daycol);
        carTableView.getItems().addAll(getCarAllTable());


        HBox bar3 = new HBox(10);
        bar3.getChildren().add(carTableView);

        Label label1 = new Label("Order file:");
        Button ascending1 = new Button("Ascending");
        Button descending1 = new Button("descending");
        Button refresh1 = new Button("Refresh");
        refresh1.setOnAction(e->gui.viewPaneCarRefresh(carTableView));
        TextField status1 = new TextField();
        status1.setText("Status");
        status1.setMinWidth(300);
        status1.setEditable(false);
        ascending1.setOnAction(e->gui.viewPaneCarAscending(status1));
        descending1.setOnAction(e->gui.viewPaneCarDescending(status1));
        HBox bar4 = new HBox(10);
        bar4.getChildren().addAll(label1,ascending1,descending1,status1);
        bar4.setPadding(new Insets(20,40,10,10));
        VBox vBox1 = new VBox(10);
        vBox1.setPadding(new Insets(20,30,10,10));
        vBox1.getChildren().addAll(refresh1,bar3,bar4);

        Tab car = new Tab("Car");
        car.setContent(vBox1);
        car.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(driver,car);
        return tabPane;
    }

    public ObservableList<Car> getInsertTable(){
        String[] indexes = func.searchCarRecord(7,"1");
        Car[] tempcar = func.readCarRecord();


        ObservableList<Car> cars = FXCollections.observableArrayList();
        for (int i=0;i<indexes.length;i++){
            System.out.println("niggg "+tempcar[i].getAvailability());
            cars.add(tempcar[Integer.parseInt(indexes[i])]);
            //System.out.println(tempcar[Integer.parseInt(indexes[i])].getName());
        }
        return cars;
    }

    public ObservableList<Driver> getDriverSearchTable(int choice,String item){
        String[] indexes = func.searchDriverRecord(choice,item);
        Driver[] tempdriver = func.readDriverRecord();

        ObservableList<Driver> drivers = FXCollections.observableArrayList();
        for (int i=0;i<indexes.length;i++){
            drivers.add(tempdriver[Integer.parseInt(indexes[i])]);
            System.out.println(tempdriver[Integer.parseInt(indexes[i])].getName());
        }
        return drivers;
    }

    public ObservableList<Driver> getDriverAllTable(){
        Driver[] tempdriver = func.readDriverRecord();

        ObservableList<Driver> drivers = FXCollections.observableArrayList();
        for (int i=0;i<tempdriver.length;i++){
            drivers.add(tempdriver[i]);
        }
        return drivers;
    }

    public ObservableList<Car> getCarAllTable(){
        Car[] tempcar = func.readCarRecord();

        ObservableList<Car> cars = FXCollections.observableArrayList();
        for (int i=0;i<tempcar.length;i++){
            cars.add(tempcar[i]);
        }
        return cars;
    }

    public ObservableList<Car> getCarSearchTable(int choice,String item){
        String[] indexes = func.searchCarRecord(choice,item);
        Car[] tempcar = func.readCarRecord();

        ObservableList<Car> cars = FXCollections.observableArrayList();
        for (int i=0;i<indexes.length;i++){
            cars.add(tempcar[Integer.parseInt(indexes[i])]);
            System.out.println(tempcar[Integer.parseInt(indexes[i])].getName());
        }
        return cars;
    }

    //status lower status label
    public HBox statusBar(){
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(10,10,10,10));
        Label label = new Label("Status: " + statusBarMessage);
        hBox.getChildren().add(label);
        hBox.setStyle("-fx-background-color: oldlace");
        return hBox;
    }

    public static void main(String[] args) {

        launch(args);
    }
}
