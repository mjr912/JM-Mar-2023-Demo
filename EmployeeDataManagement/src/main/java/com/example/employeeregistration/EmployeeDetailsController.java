package com.example.employeeregistration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {
    @FXML
    Button back;
    @FXML
    private TableView<EmployeeDetailsData> EmployeeTable = new TableView<EmployeeDetailsData>();
    @FXML
    private TableColumn<EmployeeDetailsData, Integer> userIdColumn = new TableColumn<EmployeeDetailsData, Integer>("UserID");
    @FXML
    private TableColumn<EmployeeDetailsData, String> nameColumn = new TableColumn<EmployeeDetailsData, String>("Name");
    @FXML
    private TableColumn<EmployeeDetailsData, String> genderColumn = new TableColumn<EmployeeDetailsData, String>("Gender");
    @FXML
    private TableColumn<EmployeeDetailsData, String> permanentAddressColumn = new TableColumn<EmployeeDetailsData, String>("PermanentAddress");
    @FXML
    private TableColumn<EmployeeDetailsData, String> contactAddressColumn = new TableColumn<EmployeeDetailsData, String>("ContactAddress");
    @FXML
    private TableColumn<EmployeeDetailsData, Date> dobColumn = new TableColumn<EmployeeDetailsData, Date>("MobileNumber");
    @FXML
    private TableColumn<EmployeeDetailsData, Integer> ctcColumn = new TableColumn<EmployeeDetailsData, Integer>("DOB");
    @FXML
    private TableColumn<EmployeeDetailsData, Integer> experienceColumn = new TableColumn<EmployeeDetailsData, Integer>("CTC");
    @FXML
    private TableColumn<EmployeeDetailsData, String> emailIdColumn = new TableColumn<EmployeeDetailsData, String>("Experience");
    @FXML
    private TableColumn<EmployeeDetailsData, String> mobileNumberColumn = new TableColumn<EmployeeDetailsData, String>("EmployeeType");
    @FXML
    private TableColumn<EmployeeDetailsData, String> employeeTypeColumn = new TableColumn<EmployeeDetailsData, String>("Email");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userIdColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData,Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("gender"));
        permanentAddressColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("permanentAddress"));
        contactAddressColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("contactAddress"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("mobileNumber"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, Date>("date"));
        ctcColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, Integer>("ctc"));
        experienceColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, Integer>("experience"));
        employeeTypeColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("employeeType"));
        emailIdColumn.setCellValueFactory(new PropertyValueFactory<EmployeeDetailsData, String>("email"));

        back = new Button("Back");
    }

    @FXML
    public void backButtonClick(ActionEvent event) throws Exception {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        ViewEmployee ve = new ViewEmployee();
        stage.close();
        ve.start(stage);
    }

    public void connection(String field, String value) {
        DatabaseConnection connect=new DatabaseConnection();
        Connection connectDB=connect.getConnection();
        ObservableList<EmployeeDetailsData> data = FXCollections.observableArrayList();
        String query="";
        if(field.equals("UserID"))
            query="select * from employeeDetails where "+field+" = "+value+";";
        else
            query="select * from employeeDetails where "+field+" = '"+value+"';";
        try{
            Statement statement=connectDB.createStatement();
            ResultSet rs= statement.executeQuery(query);

            while(rs.next()){
                EmployeeTable.getItems().add(new EmployeeDetailsData(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), java.sql.Date.valueOf(rs.getString(7)),Integer.parseInt(rs.getString(8)),Integer.parseInt(rs.getString(9)),rs.getString(10),rs.getString(11)));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
    }
}}