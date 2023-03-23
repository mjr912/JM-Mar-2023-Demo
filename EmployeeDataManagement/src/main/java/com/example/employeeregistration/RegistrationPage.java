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
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;

public class RegistrationPage extends Application {
    static Double i=0D;
    private int Id=-1;
    EventHandler<ActionEvent> event;
    Label firstName,lastName,userId,gender,address,permanentAddress,contactAddress,dob,emailId,mobileNumber,ctc,experience,employmentType;
    TextField firstNameField,lastNameField,userIdField,emailField,numberField,ctcField,experienceField;
    RadioButton maleRb,femaleRb,othersRb;
    ToggleGroup bg;
    TextArea permAddArea,contAddArea;
    CheckBox checkBox1,agree;
    DatePicker datePicker;
    ComboBox<String > employeeTypeCbox;
    Button button;

    @Override
    public void start(Stage stage) throws IOException, SQLException {


        Label label = new Label("Registration Page");
        label.setStyle("-fx-font-size:30px;-fx-text-fill:blue;-fx-font-weight:bold");

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        firstName = new Label("First Name");
        firstName.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        firstNameField = new TextField();
        firstNameField.setOnAction(event);

        lastName = new Label("Last Name");
        lastName.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        lastNameField = new TextField();
        lastNameField.setPrefSize(220,30);
        lastNameField.setOnAction(event);

        userId = new Label("User ID");
        userId.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        userIdField = new TextField();
        userIdField.setOnAction(event);

        gender = new Label("Gender");
        gender.setStyle("-fx-font-size:18px;-fx-text-fill:blue");

        maleRb = new RadioButton("Male");
        maleRb.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        femaleRb = new RadioButton("Female");
        femaleRb.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        othersRb = new RadioButton("Others");
        othersRb.setStyle("-fx-font-size:14px;-fx-text-fill:blue");
        bg = new ToggleGroup();
        maleRb.setToggleGroup(bg);
        femaleRb.setToggleGroup(bg);
        othersRb.setToggleGroup(bg);
        bg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (bg.getSelectedToggle().getUserData() != null) {
                    System.out.println(bg.getSelectedToggle().getUserData().toString());
                    i += Double.parseDouble(String.valueOf(1 / 13));
                }
            }
        });


        HBox hBox1 = new HBox(maleRb, femaleRb, othersRb);
        hBox1.setSpacing(10);

        address = new Label("Address");
        address.setStyle("-fx-font-size:18px;-fx-text-fill:blue");

        permanentAddress = new Label("Permanent Address");
        permanentAddress.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        permAddArea = new TextArea();
        permAddArea.setPrefSize(200, 100);

        Label l = new Label();
        Label l1 = new Label();

        contactAddress = new Label("Contact Address");
        contactAddress.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        contAddArea = new TextArea();
        contAddArea.setPrefSize(200, 100);

        checkBox1 = new CheckBox("Contact Address same as permanent");
        checkBox1.setStyle("-fx-text-fill:blue");
        checkBox1.setOnAction(e -> {
            if (checkBox1.isSelected()) {
                i += Double.parseDouble(String.valueOf(1 / 13));
                contAddArea.setText(permAddArea.getText());
                contactAddress.setDisable(true);
                contAddArea.setDisable(true);
            } else {
                i -= Double.parseDouble(String.valueOf(1 / 13));
                contactAddress.setDisable(false);
                contAddArea.setDisable(false);
            }
        });

        dob = new Label("DOB");
        dob.setStyle("-fx-font-size:18px;-fx-text-fill:blue");

        datePicker = new DatePicker();
        datePicker.setPrefSize(220, 30);
        datePicker.setOnAction(event);


        emailId = new Label("Email ID");
        emailId.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        emailField = new TextField();
        emailField.setOnAction(event);

        mobileNumber = new Label("Mobile Number");
        mobileNumber.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        numberField = new TextField();
        numberField.setOnAction(event);

        ctc = new Label("CTC");
        ctc.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        ctcField = new TextField();
        ctcField.setOnAction(event);

        experience = new Label("Experience");
        experience.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        experienceField = new TextField();
        experienceField.setOnAction(event);


        employmentType = new Label("Employment Type");
        employmentType.setStyle("-fx-font-size:18px;-fx-text-fill:blue");
        String[] arr = {"Intern", "Developer", "Manager","Team Lead","Tester"};
        employeeTypeCbox = new ComboBox<String>(FXCollections.observableArrayList(arr));
        employeeTypeCbox.setPrefSize(220, 30);
        employeeTypeCbox.setOnAction(event);

        agree = new CheckBox("I agree with the Privacy Policy, Terms and Conditions");
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


        button = new Button("Submit");
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

        System.out.println("this is main class");

        Scene scene = new Scene(vBox, 850, 700);
        stage.setTitle("Add Employee");
        stage.setScene(scene);
        stage.show();
        if(Id!=-1){
            System.out.println("this is inside class");
            update(stage);
        }


    }

    public static void main(String[] args) {
        launch();
    }

    public void IdValue(int Id) throws IOException, SQLException {
        this.Id=Id;
        System.out.println(Id);
        Stage stage=new Stage();
        this.start(stage);
    }

    public void update(Stage stage) throws SQLException {
        DatabaseConnection db=new DatabaseConnection();
        Connection connectDb=db.getConnection();
        Statement statement = connectDb.createStatement();
        String query="select * from employeeDetails where UserID="+Id+";";
        System.out.println(query);

        ResultSet rs= statement.executeQuery(query);
        rs.next();
        System.out.println("first value is"+Integer.parseInt(rs.getString(1)));
        System.out.println("jasdbasjfliskfnksanfsk");
        userIdField.setText(rs.getString(1));
        String str=rs.getString(2);
        String[] name=str.split(" ");
        System.out.println("array is"+ Arrays.toString(name));
        firstNameField.setText(name[0]);
        lastNameField.setText(name[1]);
        System.out.println("This is secong");
        String gen=rs.getString(3);
        if(gen.equals("Male"))
            bg.selectToggle(maleRb);
        else if (gen.equals("Female")) {
            bg.selectToggle(femaleRb);
        }
        else
            bg.selectToggle(othersRb);
        permAddArea.setText(rs.getString(4));
        contAddArea.setText(rs.getString(5));
        numberField.setText(rs.getString(6));
        datePicker.setValue(LocalDate.parse(rs.getString(7)));
        ctcField.setText(rs.getString(8));
        experienceField.setText(rs.getString(9));
        employeeTypeCbox.setValue(rs.getString(10));
        emailField.setText(rs.getString(11));

        button.setOnAction(event->{
            String query1="update employeeDetails set UserId= ?, name= ?,gender= ?,PermanentAddress= ?,ContactAddress= ?,MobileNumber= ?,DOB= ?,CTC= ?,Experience= ?,EmployeeType= ?,email= ? where UserID="+Id+";";
            try {
                PreparedStatement ps=connectDb.prepareStatement(query1);
                ps.setString(1,userIdField.getText());
                ps.setString(2,firstNameField.getText()+lastNameField.getText());
                ps.setString(3, bg.getSelectedToggle().toString());
                ps.setString(4,permAddArea.getText());
                ps.setString(5,contAddArea.getText());
                ps.setString(6,numberField.getText());
                ps.setString(7, String.valueOf(datePicker.getValue()));
                ps.setString(8,ctcField.getText());
                ps.setString(9,experienceField.getText());
                ps.setString(10,employeeTypeCbox.getValue());
                ps.setString(11,emailField.getText());
                ps.executeUpdate();
                System.out.println(employeeTypeCbox.getValue());
                stage.close();
                MainPage mp=new MainPage();
                try {
                    mp.start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }
}