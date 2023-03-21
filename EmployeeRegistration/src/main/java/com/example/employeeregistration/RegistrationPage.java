package com.example.employeeregistration;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationPage extends Application {
    static Double i=0D;
    ProgressBar progressBar;
    ProgressIndicator progressIndicator;
    EventHandler<ActionEvent> event;
    @Override
    public void start(Stage stage) throws IOException {


        progressBar = new ProgressBar();
        progressIndicator = new ProgressIndicator();

        Label label = new Label("Registration Page");
        label.setStyle("-fx-font-size:30px;-fx-text-fill:blue;-fx-font-weight:bold");

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        Label firstName = new Label("First Name");
        firstName.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField firstNameField = new TextField();
        firstNameField.setOnAction(event);

        Label lastName = new Label("Last Name");
        lastName.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField lastNameField = new TextField();
        lastNameField.setPrefSize(220,30);
        lastNameField.setOnAction(event);

        Label userId = new Label("User ID");
        userId.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField userIdField = new TextField();
        userIdField.setOnAction(event);

        Label gender = new Label("Gender");
        gender.setStyle("-fx-font-size:18px;-fx-text-fill:blue");

        RadioButton maleRb = new RadioButton("Male");
        maleRb.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        RadioButton femaleRb = new RadioButton("Female");
        femaleRb.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        RadioButton othersRb = new RadioButton("Others");
        othersRb.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        ToggleGroup bg = new ToggleGroup();
        maleRb.setToggleGroup(bg);
        femaleRb.setToggleGroup(bg);
        othersRb.setToggleGroup(bg);
        bg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (bg.getSelectedToggle().getUserData() != null) {
                    System.out.println(bg.getSelectedToggle().getUserData().toString());
                    i += Double.parseDouble(String.valueOf(1 / 13));
                    progressBar.setProgress(i);
                    progressIndicator.setProgress(i);
                }
            }
        });


        HBox hBox1 = new HBox(maleRb, femaleRb, othersRb);
        hBox1.setSpacing(10);

        Label address = new Label("Address");
        address.setStyle("-fx-font-size:18px;-fx-text-fill:blue");

        Label permanentAddress = new Label("Permanent Address");
        permanentAddress.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextArea permAddArea = new TextArea();
        permAddArea.setPrefSize(200, 100);

        Label l = new Label();
        Label l1 = new Label();

        Label contactAddress = new Label("Contact Address");
        contactAddress.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextArea contAddArea = new TextArea();
        contAddArea.setPrefSize(200, 100);

        CheckBox checkBox1 = new CheckBox("Contact Address same as permanent");
        checkBox1.setStyle("-fx-text-fill:blue");
        checkBox1.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                i += Double.parseDouble(String.valueOf(1 / 13));
                progressBar.setProgress(i);
                progressIndicator.setProgress(i);
                contAddArea.setText(permAddArea.getText());
                contactAddress.setDisable(true);
                contAddArea.setDisable(true);
            } else {
                i -= Double.parseDouble(String.valueOf(1 / 13));
                progressBar.setProgress(i);
                progressIndicator.setProgress(i);
                contactAddress.setDisable(false);
                contAddArea.setDisable(false);
            }
        });

        Label dob = new Label("DOB");
        dob.setStyle("-fx-font-size:18px;-fx-text-fill:blue");

        DatePicker datePicker = new DatePicker();
        datePicker.setPrefSize(220, 30);
        datePicker.setOnAction(event);


        Label emailId = new Label("Email ID");
        emailId.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField emailField = new TextField();
        emailField.setOnAction(event);

        Label mobileNumber = new Label("Mobile Number");
        mobileNumber.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField numberField = new TextField();
        numberField.setOnAction(event);

        Label ctc = new Label("CTC");
        ctc.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField ctcField = new TextField();
        ctcField.setOnAction(event);

        Label experience = new Label("Experience");
        experience.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        TextField experienceField = new TextField();
        experienceField.setOnAction(event);


        Label employmentType = new Label("Employment Type");
        employmentType.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        String[] arr = {"Intern", "Developer", "Manager","Team Lead","Tester"};
        ComboBox<String> employeeTypeCbox = new ComboBox<String>(FXCollections.observableArrayList(arr));
        employeeTypeCbox.setPrefSize(220, 30);
        employeeTypeCbox.setOnAction(event);

        CheckBox agree = new CheckBox("I agree with the Privacy Policy, Terms and Conditions");
        agree.setStyle("-fx-font-size:16px;-fx-text-fill:blue");
        agree.setOnAction(event);


        pane.addRow(0, firstName, firstNameField, lastName, lastNameField);
        pane.addRow(1, userId, userIdField);
        pane.addRow(3, gender, hBox1);
        pane.addRow(4, address, permanentAddress, contactAddress);
        pane.addRow(5, l, permAddArea, contAddArea);
        pane.addRow(6, l1, checkBox1);
        pane.addRow(7, dob, datePicker);
        pane.addRow(8, ctc, ctcField, experience, experienceField);
        pane.addRow(9, emailId, emailField, mobileNumber, numberField);
        pane.addRow(10, employmentType, employeeTypeCbox);

        pane.setAlignment(Pos.CENTER);


        Button button = new Button("Submit");
        button.setStyle("-fx-font-size:16px;-fx-background-color:#4CBB17");
        button.setPrefSize(100, 30);
        button.setOnAction(actionEvent -> {
            DatabaseConnection db=new DatabaseConnection();
            Connection connect=db.getConnection();
            String query="insert into employeeDetails values(?,?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement statement=connect.prepareStatement(query);
                statement.setString(1,userIdField.getText());
                statement.setString(2,firstNameField.getText()+" "+lastNameField.getText());

                RadioButton rbSelect= (RadioButton) bg.getSelectedToggle();

                statement.setString(3,rbSelect.getText());
                statement.setString(4,permAddArea.getText());
                statement.setString(5,contAddArea.getText());
                statement.setString(6,numberField.getText());
                statement.setString(7, String.valueOf(datePicker.getValue()));
                statement.setString(8,ctcField.getText());
                statement.setString(9,experienceField.getText());
                statement.setString(10,employeeTypeCbox.getValue());
                statement.setString(11,emailField.getText());
                statement.addBatch();
                statement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stage.close();
            MainPage mp=new MainPage();
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

        VBox vBox = new VBox(label, pane, agree, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setBackground(background);

        event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                // set progress to different level of progressbar
                i += Double.parseDouble(String.valueOf(1 / 13));
                progressBar.setProgress(i);
                progressIndicator.setProgress(i);
            }
        };

        Scene scene = new Scene(vBox, 850, 700);
        stage.setTitle("Add Employee");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}