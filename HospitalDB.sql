-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitaldb
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `DoctorID` char(10) NOT NULL,
  `SSN` char(9) NOT NULL,
  `FullName` varchar(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `DOB` date NOT NULL,
  `PhoneNum` char(10) NOT NULL,
  `YrsExperience` int NOT NULL,
  `DeptName` varchar(15) NOT NULL,
  `PatientID` char(19) NOT NULL,
  PRIMARY KEY (`DoctorID`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `PhoneNum_UNIQUE` (`PhoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES ('1000100001','000101000','Layken Cohen','F','cohenl@hospital.org','1983-02-20','6149572626',9,'Outpatient','0000000000'),('1111111111','000111000','Charlie Wynwood','F','wynwoodc@hospital.org','1992-05-30','7394756298',5,'Inpatient','0000000001'),('2222222222','000222000','Lily Bloom','F','blooml@hospital.org','1977-06-21','2148273547',3,'Radiology','0000000002'),('3333333333','000333000','Fallon ONeill','F','oneillf@hospital.org','1990-11-11','4696568365',14,'Inpatient','0000000003'),('4444444444','000444000','Atlas Corrigan','M','corrigan@hospital.org','1989-03-01','6148563527',0,'Rehabilitation','0000000004'),('5555555555','000555000','Sky Davis','F','daviss@hospital.org','1975-12-25','9726593836',2,'Pharmacy','0000000005'),('6666666666','000666000','Megan Andrews','F','andrewsm@hospital.org','1983-09-19','9726481100',8,'Operation','0000000006'),('7777777777','000777000','Lowen Ashleigh','F','ashleighl@hospital.org','1994-10-31','8386797000',4,'Paramedical','0000000007'),('8888888888','000888000','Tate Collins','F','collinst@hospital.org','1981-01-12','8269164747',11,'Rehabilitation','0000000008');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergencycontact`
--

DROP TABLE IF EXISTS `emergencycontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergencycontact` (
  `PatientID` char(10) NOT NULL,
  `FullName` varchar(20) DEFAULT NULL,
  `Email` varchar(25) DEFAULT NULL,
  `PhoneNum` char(10) DEFAULT NULL,
  PRIMARY KEY (`PatientID`),
  CONSTRAINT `ptID` FOREIGN KEY (`PatientID`) REFERENCES `patients` (`PatientID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergencycontact`
--

LOCK TABLES `emergencycontact` WRITE;
/*!40000 ALTER TABLE `emergencycontact` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergencycontact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalrecord`
--

DROP TABLE IF EXISTS `medicalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalrecord` (
  `PatientID` char(10) NOT NULL,
  `BloodType` char(3) DEFAULT NULL,
  `CurrentDiagnosis` varchar(20) DEFAULT NULL,
  `Allergies` varchar(20) DEFAULT NULL,
  `Medications` varchar(30) DEFAULT NULL,
  `PreviousIllnesses` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PatientID`),
  CONSTRAINT `PatientID` FOREIGN KEY (`PatientID`) REFERENCES `patients` (`PatientID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecord`
--

LOCK TABLES `medicalrecord` WRITE;
/*!40000 ALTER TABLE `medicalrecord` DISABLE KEYS */;
INSERT INTO `medicalrecord` VALUES ('0000000000','O+','Pneuomonia','Sulfonamides','Fever reducer','Pneumonia'),('0000000001','AB-','Osteoarthritis','N/A','Analgesic','N/A'),('0000000002','B-','Throat Cancer','Aspirin, Ibuprofen','Chemotherapy','N/A'),('0000000003','O+','Speticemia','Latex','N/A','N/A'),('0000000004','A+','Cardiac dysrhythmias','N/A','Vasopressors','Blood clots'),('0000000005','B+','Anemia','N/A','N/A','N/A'),('0000000006','O-','Pancreatitis','N/A','N/A','Gallstones'),('0000000007','AB+','Stroke','Penicillin','Anticoagulants','N/A'),('0000000008','A+','Asthma Exacerbation','Pollen, Mold','Bronchodilator','N/A');
/*!40000 ALTER TABLE `medicalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurses`
--

DROP TABLE IF EXISTS `nurses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurses` (
  `NurseID` char(10) NOT NULL,
  `SSN` char(9) NOT NULL,
  `FullName` varchar(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `DOB` date NOT NULL,
  `PhoneNum` char(10) NOT NULL,
  `DeptName` varchar(15) NOT NULL,
  `PatientID` char(10) NOT NULL,
  PRIMARY KEY (`NurseID`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`),
  UNIQUE KEY `PhoneNum_UNIQUE` (`PhoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurses`
--

LOCK TABLES `nurses` WRITE;
/*!40000 ALTER TABLE `nurses` DISABLE KEYS */;
INSERT INTO `nurses` VALUES ('100000001','001000100','Ryle Kincaid','M','kincaidr@hospital.org','2022-11-12','2145831486','Rehabilitation','0000000000'),('100111001','001010100','Miles Archer','M','archerm@hospital.org','2022-11-12','4698271515','Pharmacy','0000000001'),('200222002','002020200','Ben Kessler','M','kesslerb@hospital.org','2022-11-12','6141486200','Radiology','0000000002'),('300333003','003030300','Dean Holder','M','holderd@hospital.org','2022-11-12','4690208822','Inpatient','0000000003'),('400444004','004040400','Graham Wells','M','grahamw@hospital.org','2022-11-12','9721203447','Inpatient','0000000004'),('500555005','005050500','Will Cooper','M','cooperw@hospital.org','2022-11-12','9723248755','Outpatient','0000000005'),('600666006','006060600','Silas Nash','M','nashs@hospital.org','2022-11-12','6145980421','Operation','0000000006'),('700777007','007070700','Leeds Gabriel','M','gabriell@hospital.org','2022-11-12','6142178201','Inpatient','0000000007'),('800888008','008080800','Kel Cohen','M','cohenk@hospital.org','2022-11-12','8171820000','Rehabilitation','0000000008');
/*!40000 ALTER TABLE `nurses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `PatientID` char(10) NOT NULL,
  `SSN` char(9) NOT NULL,
  `PatientName` varchar(20) NOT NULL,
  `DOB` date NOT NULL,
  PRIMARY KEY (`PatientID`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES ('0000000000','000000000','John Doe','2021-01-01'),('0000000001','111111111','Jane Doe','2008-10-31'),('0000000002','222222222','Mickey Mouse','1972-11-17'),('0000000003','333333333','Donald Duck','1995-03-09'),('0000000004','444444444','Colleen Hoover','2020-11-28'),('0000000005','555555555','Verity Crawford','1957-09-21'),('0000000006','666666666','Crew Crawford','2015-02-13'),('0000000007','777777777','Edward Scissorhands','1966-04-24'),('0000000008','888888888','Maggie Rogers','1983-12-04');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `DoctorID` char(10) NOT NULL,
  `NurseID` char(10) NOT NULL,
  `Date` date NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  PRIMARY KEY (`DoctorID`,`NurseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('1000100001','100000001','2022-11-13','04:00:00','10:00:00'),('1111111111','100111001','2022-11-13','10:00:00','16:00:00'),('2222222222','200222002','2022-11-13','16:00:00','22:00:00'),('3333333333','300333003','2022-11-14','22:00:00','04:00:00'),('4444444444','400444004','2022-11-14','04:00:00','10:00:00'),('5555555555','500555005','2022-11-14','10:00:00','16:00:00'),('6666666666','600666006','2022-11-14','16:00:00','22:00:00'),('7777777777','700777007','2022-11-15','22:00:00','04:00:00'),('8888888888','800888008','2022-11-15','04:00:00','10:00:00');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialization` (
  `DoctorID` char(10) NOT NULL,
  `Specialization` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`DoctorID`),
  CONSTRAINT `DoctorID` FOREIGN KEY (`DoctorID`) REFERENCES `doctors` (`DoctorID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES ('1000100001','Pulmonology'),('1111111111','Rheumatology'),('2222222222','Otolaryngology'),('3333333333','Intensive Care'),('4444444444','Cardiology'),('5555555555','Hematology'),('6666666666','Pancreatology'),('7777777777','Vascular'),('8888888888','Immunology');
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-04 23:12:25
