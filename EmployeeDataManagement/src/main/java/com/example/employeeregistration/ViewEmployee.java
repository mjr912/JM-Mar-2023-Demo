package com.example.employeeregistration;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewEmployee extends Application {
    private Parent root;
    @Override
    public void start(Stage stage) throws IOException {
        Label employeeId=new Label("Field");
        employeeId.setStyle("-fx-font-size:20px;-fx-text-fill:blue");

        String[] fields={"UserID","Name","EmailID"};
        ComboBox<String> comboBox=new ComboBox<>(FXCollections.observableArrayList(fields));
        comboBox.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        HBox hBox=new HBox(employeeId,comboBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Label fieldLabel=new Label("FieldLabel");
        fieldLabel.setStyle("-fx-font-size:20px;-fx-text-fill:blue");
        TextField fieldValue=new TextField();
        HBox hBox1=new HBox(fieldLabel,fieldValue);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(20);

        comboBox.setOnAction(event -> {
            fieldLabel.setText(comboBox.getValue());
        });

        Button submit=new Button("Submit");
        submit.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        submit.setPrefSize(100,40);

        Button back=new Button("Back");
        back.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        back.setPrefSize(100,40);

        Label notification=new Label();

        submit.setOnAction(event -> {
            EmployeeDetails ed = new EmployeeDetails();
            DatabaseConnection connect=new DatabaseConnection();
            Connection connectDB=connect.getConnection();

            if (comboBox.getValue()!=null && !fieldValue.getText().isEmpty()){
                String query="";
                if(comboBox.getValue().equals("UserID"))
                    query="select "+comboBox.getValue()+ " from employeeDetails where "+comboBox.getValue()+"="+fieldValue.getText()+";";
                else
                    query="select "+comboBox.getValue()+ " from employeeDetails where "+comboBox.getValue()+"='"+fieldValue.getText()+"';";

                try {
                    Statement statement=connectDB.createStatement();
                    ResultSet rs= statement.executeQuery(query);
                    if(rs.next()){
                        stage.close();
                        try {
                            ed.control(comboBox.getValue(), fieldValue.getText());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else
                        notification.setText("Enter the correct details");

                } catch (SQLException e) {
                    notification.setText("Enter the correct details");
                }
            }
            else{
                notification.setText("Enter the fields");
            }
        });
        back.setOnAction(event -> {
            MainPage mp=new MainPage();
            stage.close();
            try {
                mp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        HBox hBox2=new HBox(submit,back);
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);

        FileInputStream input = new FileInputStream("src/main/java/com/example/employeeregistration/Image/desktop-wallpaper.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);

        VBox vBox=new VBox(hBox,hBox1,hBox2,notification);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(background);

        Scene scene = new Scene(vBox, 500, 500);
        stage.setTitle("View Employee");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
