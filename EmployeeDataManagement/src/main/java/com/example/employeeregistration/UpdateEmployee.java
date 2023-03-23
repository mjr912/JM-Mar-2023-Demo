package com.example.employeeregistration;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class UpdateEmployee extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label employeeID=new Label("UserID");
        employeeID.setStyle("-fx-font-size:20px;-fx-text-fill:blue");
        TextField Id=new TextField();

        Button submit=new Button("Submit");
        submit.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        Button back=new Button("Back");
        back.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");

        HBox hBox=new HBox(employeeID,Id);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        HBox hBox1=new HBox(submit,back);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);

        Label notification=new Label();

        submit.setOnAction(event -> {
            DatabaseConnection connect=new DatabaseConnection();
            Connection con=connect.getConnection();
            if(!Id.getText().isEmpty()) {
                try {
                    String query = "select UserID from employeeDetails where UserID=" + Id.getText() + ";";
                    Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    if (rs.next()) {
                        stage.close();
                        RegistrationPage rp = new RegistrationPage();
                        rp.IdValue(Integer.parseInt(Id.getText()));
                    }
                    else
                        notification.setText("Enter a valid ID value");
                } catch (SQLException | IOException e) {
                    notification.setText("Enter a valid ID value");
                }
            }
            else
                notification.setText("Enter the Id value in the text field");
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

        FileInputStream input = new FileInputStream("src/main/java/com/example/employeeregistration/Image/desktop-wallpaper.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);

        VBox vBox=new VBox(hBox,hBox1,notification);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(background);

        Scene scene=new Scene(vBox,500,500);
        stage.setScene(scene);
        stage.setTitle("Update Employee Details");
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
