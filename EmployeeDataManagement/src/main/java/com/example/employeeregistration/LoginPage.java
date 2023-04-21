package com.example.employeeregistration;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        GridPane gridPane=new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Label loginID=new Label("UserName");
        loginID.setStyle("-fx-font-size:20px;-fx-text-fill:blue");
        TextField loginIDfield=new TextField();

        Label password=new Label("Password");
        password.setStyle("-fx-font-size:20px;-fx-text-fill:blue");
        PasswordField passwordfield=new PasswordField();

        gridPane.addRow(0,loginID,loginIDfield);
        gridPane.addRow(1,password,passwordfield);
        gridPane.setAlignment(Pos.CENTER);

        Button submit=new Button("Submit");
        submit.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        submit.setPrefSize(150,30);

        Hyperlink forgetPass=new Hyperlink("Forget Password");
        forgetPass.setStyle("-fx-font-size:14px; -fx-text-fill:blue");
        forgetPass.setOnAction(event -> {
            ForgotPassword fp=new ForgotPassword();
            stage.close();
            try{
                fp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Label notification=new Label();

        submit.setOnAction(event -> {
            DatabaseConnection connect=new DatabaseConnection();
            Connection connectDB=connect.getAdminConnection();
            if(!loginIDfield.getText().isEmpty() && !passwordfield.getText().isEmpty()) {
                String query = "select * from admin where username= '" + loginIDfield.getText() + "' and password='" + passwordfield.getText() + "';";
                try {
                    Statement statement = connectDB.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    if (rs.next()) {
                        MainPage mp = new MainPage();
                        stage.close();
                        try {
                            mp.start(stage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else
                        notification.setText("Enter the correct details");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                notification.setText("Enter the details");

        }
        });

        FileInputStream input = new FileInputStream("src/main/java/com/example/employeeregistration/Image/desktop-wallpaper.jpg");

        Image image = new Image(input);

        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);

        VBox vBox=new VBox(gridPane,submit,forgetPass,notification);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setBackground(background);

        Scene scene = new Scene(vBox, 500, 500);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();


        }
    public static void main(String[] args){
        launch();
    }

}
