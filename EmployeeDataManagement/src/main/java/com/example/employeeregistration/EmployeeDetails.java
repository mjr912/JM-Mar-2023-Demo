package com.example.employeeregistration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDetails extends Application {
    private String field, val;
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),1200,600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Employee Details");
        EmployeeDetailsController controller = fxmlLoader.getController();
        controller.connection(field,val);
        stage.show();
        }
        public static void main(String[] args){
        launch();
        }
        public void control(String field,String val) throws IOException {
            this.field=field;
            this.val=val;
            Stage stage=new Stage();
            this.start(stage);
        }
    }


