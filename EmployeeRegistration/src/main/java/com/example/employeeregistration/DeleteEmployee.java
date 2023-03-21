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
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmployee extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Label employeeId=new Label("EmployeeID");
        employeeId.setStyle("-fx-font-size:20px;-fx-text-fill:blue");
        TextField idValue=new TextField();

        HBox hBox=new HBox(employeeId,idValue);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        Button submit=new Button("Submit");
        submit.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        submit.setOnAction(event -> {
            DatabaseConnection db=new DatabaseConnection();
            Connection connect=db.getConnection();
            String query="delete from employeeDetails where UserID='"+idValue.getText()+"';";
            try {
                PreparedStatement statement=connect.prepareStatement(query);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MainPage mp=new MainPage();
            stage.close();
            try {
                mp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Button back=new Button("Back");
        back.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        back.setOnAction(event -> {
            MainPage mp=new MainPage();
            stage.close();
            try {
                mp.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        HBox hBox1=new HBox(submit,back);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);

        FileInputStream input = new FileInputStream("src/main/java/com/example/employeeregistration/Image/desktop-wallpaper.jpg");

        Image image = new Image(input);

        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);

        VBox vBox=new VBox(hBox,hBox1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setBackground(background);

        Scene scene = new Scene(vBox, 500, 500);
        stage.setTitle("Delete Employee");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
