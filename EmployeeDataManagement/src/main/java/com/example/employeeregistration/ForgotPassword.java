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

public class ForgotPassword extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        Label userName=new Label("UserName");
        userName.setStyle("-fx-font-size:20px;-fx-text-fill:blue");
        TextField idValue=new TextField();

        HBox hBox=new HBox(userName,idValue);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Label password=new Label();
        Label notification=new Label();

        Button submit=new Button("Submit");
        submit.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        submit.setOnAction(event -> {
            DatabaseConnection connect=new DatabaseConnection();
            Connection connectDB=connect.getAdminConnection();
            if(!idValue.getText().isEmpty()){
                String query="select password from admin where "+userName.getText()+"='"+idValue.getText()+"';";
                try {
                    Statement statement = connectDB.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    if (rs.next()) {
                        password.setText(rs.getString(1));

                    }

                } catch (SQLException e) {
                    notification.setText("Enter the correct details");
                }
            }
            else{
                notification.setText("Enter the details");
            }
        });

        Button back=new Button("Login Page");
        back.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        back.setOnAction(event -> {
            LoginPage lp=new LoginPage();
            stage.close();
            try {
                lp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        HBox hBox1=new HBox(submit,back);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(20);

        FileInputStream input = new FileInputStream("src/main/java/com/example/employeeregistration/Image/desktop-wallpaper.jpg");

        Image image = new Image(input);

        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);

        VBox vBox=new VBox(hBox,hBox1,password,notification);
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(background);
        Scene scene=new Scene(vBox,500,500);
        stage.setScene(scene);
        stage.setTitle("Forget Password");
        stage.show();

    }
    public static void main(String[] args){
        launch();
    }
}
