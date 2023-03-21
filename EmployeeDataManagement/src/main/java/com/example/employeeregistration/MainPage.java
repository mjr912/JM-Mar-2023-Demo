package com.example.employeeregistration;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Button addEmployee=new Button("Add Employee");
        addEmployee.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        addEmployee.setPrefSize(200,40);
        addEmployee.setOnAction(event -> {
            RegistrationPage rp=new RegistrationPage();
            stage.close();
            try {
                rp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button viewEmployee=new Button("View Employee");
        viewEmployee.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        viewEmployee.setPrefSize(200,40);
        viewEmployee.setOnAction(event -> {
            ViewEmployee ve=new ViewEmployee();
            stage.close();
            try {
                ve.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Button updateEmployee=new Button("Update Employee");
        updateEmployee.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        updateEmployee.setPrefSize(200,40);
        //updateEmployee.setOnAction();

        Button deleteEmployee=new Button("Delete Employee");
        deleteEmployee.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        deleteEmployee.setPrefSize(200,40);
        deleteEmployee.setOnAction(event -> {
            DeleteEmployee de=new DeleteEmployee();
            stage.close();
            try {
                de.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button logout=new Button("Logout");
        logout.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        logout.setPrefSize(200,40);
        logout.setOnAction(event -> {
            LoginPage lp=new LoginPage();
            stage.close();
            try {
                lp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        FileInputStream input = new FileInputStream("src/main/java/com/example/employeeregistration/Image/desktop-wallpaper.jpg");

        Image image = new Image(input);

        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);

        VBox vBox=new VBox(addEmployee,viewEmployee,deleteEmployee,logout);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setBackground(background);

        Scene scene = new Scene(vBox, 500, 500);
        scene.setFill(new RadialGradient(
                0, 0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#81c483")),
                new Stop(1, Color.web("#fcc200")))
        );
        stage.setTitle("Main Page");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
