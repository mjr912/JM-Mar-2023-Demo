
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES `admin` WRITE;

INSERT INTO `admin` VALUES ('admin','12345');

UNLOCK TABLES;