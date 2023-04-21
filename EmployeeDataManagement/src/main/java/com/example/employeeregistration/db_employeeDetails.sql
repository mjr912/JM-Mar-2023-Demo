DROP TABLE IF EXISTS `employeeDetails`;

CREATE TABLE `employeeDetails` (
  `UserID` int(10) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `PermanentAddress` varchar(100) DEFAULT NULL,
  `ContactAddress` varchar(100) DEFAULT NULL,
  `MobileNumber` varchar(15) DEFAULT NULL,
  `DOB` DATE DEFAULT NULL,
  `CTC` int(10) DEFAULT NULL,
  `Experience` varchar(10) DEFAULT NULL,
  `EmployeeType` varchar(20) DEFAULT NULL,
  `Email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES `employee` WRITE;

INSERT INTO `employee` VALUES (101,"first last","male","location","location","9876543210","2016-03-02",10,1,"Developer","example@gmail.com");

UNLOCK TABLES;