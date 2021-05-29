-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: btl_sqa
-- ------------------------------------------------------
-- Server version	8.0.21-cluster

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'PTIT','E17CN02'),(2,'PTIT','D17CN11');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer` (
  `id` int NOT NULL,
  `faculty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK4cl02lw2fq7at7h69uh4cjqim` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` VALUES (1,'CNTT');
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_report`
--

DROP TABLE IF EXISTS `lecturer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer_report` (
  `lecturer_id` int NOT NULL,
  `report_id` int NOT NULL,
  KEY `FKenc3jj31cacqjxnf2jwfkft2` (`report_id`),
  KEY `FKmm7bcyi5pbtn9b6mu860m7klu` (`lecturer_id`),
  CONSTRAINT `FKenc3jj31cacqjxnf2jwfkft2` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`),
  CONSTRAINT `FKmm7bcyi5pbtn9b6mu860m7klu` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_report`
--

LOCK TABLES `lecturer_report` WRITE;
/*!40000 ALTER TABLE `lecturer_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_subject`
--

DROP TABLE IF EXISTS `lecturer_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer_subject` (
  `lecturer_id` int NOT NULL,
  `subject_id` int NOT NULL,
  KEY `FK8f5mn8sms4w6ncw8sgm9goc4j` (`subject_id`),
  KEY `FK6hg4s0aqmop5bb58g62v76alk` (`lecturer_id`),
  CONSTRAINT `FK6hg4s0aqmop5bb58g62v76alk` FOREIGN KEY (`lecturer_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FK8f5mn8sms4w6ncw8sgm9goc4j` FOREIGN KEY (`subject_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_subject`
--

LOCK TABLES `lecturer_subject` WRITE;
/*!40000 ALTER TABLE `lecturer_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` int NOT NULL AUTO_INCREMENT,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK31laakchgoo3qohp4cqgcgada` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point`
--

DROP TABLE IF EXISTS `point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diem_btl` float NOT NULL,
  `diem_cc` float NOT NULL,
  `diem_cuoi_ky` float NOT NULL,
  `diem_kt` float NOT NULL,
  `diem_th` float NOT NULL,
  `manager_id` int NOT NULL,
  `student_id` int NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaus0c64dxx1fl671dxhj0malx` (`manager_id`),
  KEY `FKacw7s4svsbsnouuxbpydptctv` (`student_id`),
  KEY `FKprsbrkvtwpyms9kwv06dbmdt8` (`subject_id`),
  CONSTRAINT `FKacw7s4svsbsnouuxbpydptctv` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKaus0c64dxx1fl671dxhj0malx` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FKprsbrkvtwpyms9kwv06dbmdt8` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

LOCK TABLES `point` WRITE;
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
/*!40000 ALTER TABLE `point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'Ky 1','2017-2018'),(2,'Ky 2','2018-2019');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_point`
--

DROP TABLE IF EXISTS `semester_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester_point` (
  `semester_id` int NOT NULL,
  `point_id` int NOT NULL,
  KEY `FKldmg2804bb2u6k04pnq0p4kod` (`point_id`),
  KEY `FKi6tscmynake85x165u8oxdbej` (`semester_id`),
  CONSTRAINT `FKi6tscmynake85x165u8oxdbej` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
  CONSTRAINT `FKldmg2804bb2u6k04pnq0p4kod` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_point`
--

LOCK TABLES `semester_point` WRITE;
/*!40000 ALTER TABLE `semester_point` DISABLE KEYS */;
/*!40000 ALTER TABLE `semester_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_subject`
--

DROP TABLE IF EXISTS `semester_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester_subject` (
  `semester_id` int NOT NULL,
  `subject_id` int NOT NULL,
  KEY `FKf925epmdx5wqopayi27rgrlaw` (`subject_id`),
  KEY `FKpj0onsvnie5xat4142gx721kn` (`semester_id`),
  CONSTRAINT `FKf925epmdx5wqopayi27rgrlaw` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKpj0onsvnie5xat4142gx721kn` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_subject`
--

LOCK TABLES `semester_subject` WRITE;
/*!40000 ALTER TABLE `semester_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `semester_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `faculty` varchar(255) DEFAULT NULL,
  `gpa` double NOT NULL,
  `identify_card` varchar(255) NOT NULL,
  `class_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmujtew6jv32wikyd7nlxteay` (`identify_card`),
  KEY `FKdwhkib64u47wc4yo4hk0cub90` (`class_id`),
  CONSTRAINT `FKdwhkib64u47wc4yo4hk0cub90` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKqytew32213tbnj8u0er377k3q` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2,'CNTT',0,'018293894',1),(3,'CNTT',0,'0182938941',1),(4,'CNTT',0,'0182938942',2);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `percent_btl` float DEFAULT NULL,
  `percent_cc` float DEFAULT NULL,
  `percent_cuoi_ky` float DEFAULT NULL,
  `percent_kt` float DEFAULT NULL,
  `percent_th` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Java',0.2,0.1,0.5,0,0.2),(2,'C++',0.1,0.1,0.6,0.1,0.1);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_class`
--

DROP TABLE IF EXISTS `subject_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_class` (
  `subject_id` int NOT NULL,
  `class_id` int NOT NULL,
  KEY `FK4j9lymh0a2dxqfo2ktlnd6qp5` (`class_id`),
  KEY `FKiuy8yyhpqpr52bk049yrvxrxr` (`subject_id`),
  CONSTRAINT `FK4j9lymh0a2dxqfo2ktlnd6qp5` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKiuy8yyhpqpr52bk049yrvxrxr` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_class`
--

LOCK TABLES `subject_class` WRITE;
/*!40000 ALTER TABLE `subject_class` DISABLE KEYS */;
INSERT INTO `subject_class` VALUES (1,1),(2,1),(1,2);
/*!40000 ALTER TABLE `subject_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_knonaiqf2glys8p81dbyt8ipp` (`phone_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ha Noi','1999-12-20 00:00:00','abcxyz2@gmail.com','FEMALE','Nguyen Thi E','1234','098419845','LECTURER','giaovien1'),(2,'Ha Noi','1999-12-20 00:00:00','abcxyz23@gmail.com','MALE','Nguyen Thi A','1234','098419846','STUDENT','B17DCCN123'),(3,'Ha Noi','1999-12-20 00:00:00','abcxyz213@gmail.com','MALE','Nguyen Van A','1234','0984198467','STUDENT','B17DCCN125'),(4,'Ha Noi','1999-12-20 00:00:00','abcxyz13@gmail.com','MALE','Nguyen Van C','1234','0984198468','STUDENT','B17DCCN126');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-06 14:21:10
