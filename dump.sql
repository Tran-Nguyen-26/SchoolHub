-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: schoolhub_test
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL,
  `position` enum('ACADEMIC_ADMIN','FINANCE_ADMIN','HR_ADMIN','SUPPORT_ADMIN','SYSTEM_ADMIN') DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK1ja8rua032fgnk9jmq7du3b3a` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `total_students` int NOT NULL,
  `grade_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `teacher_id` bigint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6ate3aw7ls2unp61ui1c32n6s` (`name`),
  UNIQUE KEY `UKnj3jrpwitb693hpqanbne99nx` (`teacher_id`),
  KEY `FK8fm6qfqh2c9mc1p1o1ovdmmhc` (`grade_id`),
  CONSTRAINT `FK8fm6qfqh2c9mc1p1o1ovdmmhc` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `FKem6hymrr3cxa0ldasm7jxojrc` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (30,1,1,NULL,'10A1'),(39,1,2,NULL,'10A2'),(31,1,3,NULL,'10A3'),(0,1,4,NULL,'10A4'),(0,2,5,NULL,'11A1'),(0,2,6,NULL,'11A2'),(0,2,7,NULL,'11A3'),(0,2,8,NULL,'11A4'),(0,3,9,NULL,'12A1'),(0,3,10,NULL,'12A2'),(0,3,11,NULL,'12A3'),(0,3,12,NULL,'12A4');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_date` date DEFAULT NULL,
  `classroom_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `semester_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3qbr99ta1a12lms0hff5xxn7s` (`classroom_id`),
  KEY `FKt6mrv8r1rds4w6vs0ajqtgtd6` (`semester_id`),
  CONSTRAINT `FK3qbr99ta1a12lms0hff5xxn7s` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKt6mrv8r1rds4w6vs0ajqtgtd6` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
  `exam_date` date DEFAULT NULL,
  `classroom_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `semester_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `exam_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgxddgry2v0hvajhwaexahii0i` (`classroom_id`),
  KEY `FKp8njc4xlmts6sdw7p3ra2shkf` (`semester_id`),
  KEY `FKos7g6kn2748ll3ofc3w163gxh` (`subject_id`),
  CONSTRAINT `FKgxddgry2v0hvajhwaexahii0i` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKos7g6kn2748ll3ofc3w163gxh` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKp8njc4xlmts6sdw7p3ra2shkf` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'10'),(2,'11'),(3,'12');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent` (
  `id` bigint NOT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKor0no5wud03jeu63espilk64f` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent`
--

LOCK TABLES `parent` WRITE;
/*!40000 ALTER TABLE `parent` DISABLE KEYS */;
/*!40000 ALTER TABLE `parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `period` (
  `end_time` time(6) DEFAULT NULL,
  `period_number` int NOT NULL,
  `start_time` time(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES ('07:45:00.000000',1,'07:00:00.000000',1);
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_name` enum('CLASS_CREATE','CLASS_DELETE','CLASS_READ','CLASS_UPDATE','SCORE_CREATE','SCORE_DELETE','SCORE_READ','SCORE_UPDATE','STUDENT_CREATE','STUDENT_DELETE','STUDENT_READ','STUDENT_UPDATE','SUBJECT_CREATE','SUBJECT_DELETE','SUBJECT_READ','SUBJECT_UPDATE','TEACHER_CREATE','TEACHER_DELETE','TEACHER_READ','TEACHER_UPDATE','USER_CREATE','USER_DELETE','USER_READ','USER_UPDATE') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKl3pmqryh8vgle52647itattb9` (`permission_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (13,'CLASS_CREATE'),(16,'CLASS_DELETE'),(14,'CLASS_READ'),(15,'CLASS_UPDATE'),(17,'SCORE_CREATE'),(20,'SCORE_DELETE'),(18,'SCORE_READ'),(19,'SCORE_UPDATE'),(5,'STUDENT_CREATE'),(8,'STUDENT_DELETE'),(6,'STUDENT_READ'),(7,'STUDENT_UPDATE'),(21,'SUBJECT_CREATE'),(24,'SUBJECT_DELETE'),(22,'SUBJECT_READ'),(23,'SUBJECT_UPDATE'),(9,'TEACHER_CREATE'),(12,'TEACHER_DELETE'),(10,'TEACHER_READ'),(11,'TEACHER_UPDATE'),(1,'USER_CREATE'),(4,'USER_DELETE'),(2,'USER_READ'),(3,'USER_UPDATE');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` enum('ADMIN','PARENT','STUDENT','TEACHER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (4,'ADMIN'),(2,'PARENT'),(1,'STUDENT'),(3,'TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `permission_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_year`
--

DROP TABLE IF EXISTS `school_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_year` (
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `year_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsp3qrhfqlb13ac56yunetqc5t` (`year_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_year`
--

LOCK TABLES `school_year` WRITE;
/*!40000 ALTER TABLE `school_year` DISABLE KEYS */;
INSERT INTO `school_year` VALUES ('2021-06-20','2020-08-24',1,'2020-2021');
/*!40000 ALTER TABLE `school_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `score_value` double DEFAULT NULL,
  `exam_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhxhdt6y2k9hagtwdc4a3022uu` (`exam_id`),
  KEY `FKnap51mbove93yjb09idc9jic6` (`student_id`),
  CONSTRAINT `FKhxhdt6y2k9hagtwdc4a3022uu` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  CONSTRAINT `FKnap51mbove93yjb09idc9jic6` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `schoolyear_id` bigint DEFAULT NULL,
  `semester_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdo5c7sxgifqqqq26rak6rnadx` (`schoolyear_id`),
  CONSTRAINT `FKdo5c7sxgifqqqq26rak6rnadx` FOREIGN KEY (`schoolyear_id`) REFERENCES `school_year` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES ('2021-01-15','2020-08-24',1,1,'Hoc ky 1'),('2021-06-20','2021-01-25',2,1,'Hoc ky 2');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `enrollment_date` date DEFAULT NULL,
  `classroom_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1rs4md9whkjqy20v181d18kfy` (`classroom_id`),
  CONSTRAINT `FK1rs4md9whkjqy20v181d18kfy` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKqytew32213tbnj8u0er377k3q` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('2020-08-24',1,12),('2020-08-24',1,13),('2020-08-24',1,14),('2020-08-24',1,15),('2020-08-24',1,16),('2020-08-24',1,17),('2020-08-24',1,18),('2020-08-24',1,19),('2020-08-24',1,20),('2020-08-24',1,21),('2020-08-24',1,22),('2020-08-24',1,23),('2020-08-24',1,24),('2020-08-24',1,25),('2020-08-24',1,26),('2020-08-24',1,27),('2020-08-24',1,28),('2020-08-24',1,29),('2020-08-24',1,30),('2020-08-24',1,31),('2020-08-24',1,32),('2020-08-24',1,33),('2020-08-24',1,34),('2020-08-24',1,35),('2020-08-24',1,36),('2020-08-24',1,37),('2020-08-24',1,38),('2020-08-24',1,39),('2020-08-24',1,40),('2020-08-24',1,41),('2020-08-24',2,42),('2020-08-24',2,43),('2020-08-24',2,44),('2020-08-24',2,45),('2020-08-24',2,46),('2020-08-24',2,47),('2020-08-24',2,48),('2020-08-24',2,49),('2020-08-24',2,50),('2020-08-24',2,51),('2020-08-24',2,52),('2020-08-24',2,53),('2020-08-24',2,54),('2020-08-24',2,55),('2020-08-24',2,56),('2020-08-24',2,57),('2020-08-24',2,58),('2020-08-24',2,59),('2020-08-24',2,60),('2020-08-24',2,61),('2020-08-24',2,62),('2020-08-24',2,63),('2020-08-24',2,64),('2020-08-24',2,65),('2020-08-24',2,66),('2020-08-24',2,67),('2020-08-24',2,68),('2020-08-24',2,69),('2020-08-24',2,70),('2020-08-24',2,71),('2020-08-24',2,72),('2020-08-24',2,73),('2020-08-24',2,74),('2020-08-24',2,75),('2020-08-24',2,76),('2020-08-24',2,77),('2020-08-24',2,78),('2020-08-24',2,79),('2020-08-24',2,80),('2020-08-24',3,81),('2020-08-24',3,82),('2020-08-24',3,83),('2020-08-24',3,84),('2020-08-24',3,85),('2020-08-24',3,86),('2020-08-24',3,87),('2020-08-24',3,88),('2020-08-24',3,89),('2020-08-24',3,90),('2020-08-24',3,91),('2020-08-24',3,92),('2020-08-24',3,93),('2020-08-24',3,94),('2020-08-24',3,95),('2020-08-24',3,96),('2020-08-24',3,97),('2020-08-24',3,98),('2020-08-24',3,99),('2020-08-24',3,100),('2020-08-24',3,101),('2020-08-24',3,102),('2020-08-24',3,103),('2020-08-24',3,104),('2020-08-24',3,105),('2020-08-24',3,106),('2020-08-24',3,107),('2020-08-24',3,108),('2020-08-24',3,109),('2020-08-24',3,110),('2020-08-24',3,111);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_parent`
--

DROP TABLE IF EXISTS `student_parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_parent` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uqsk99lie7damnsh9osouodd` (`parent_id`),
  KEY `FK3nulmrwg4cubngtp7nq5lud86` (`student_id`),
  CONSTRAINT `FK1uqsk99lie7damnsh9osouodd` FOREIGN KEY (`parent_id`) REFERENCES `parent` (`id`),
  CONSTRAINT `FK3nulmrwg4cubngtp7nq5lud86` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_parent`
--

LOCK TABLES `student_parent` WRITE;
/*!40000 ALTER TABLE `student_parent` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `grade_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `syllabus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKam8igh6e0xp9r1tl7d8r1wneb` (`grade_id`),
  CONSTRAINT `FKam8igh6e0xp9r1tl7d8r1wneb` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,1,NULL,'Toán',NULL),(1,2,NULL,'Văn',NULL),(1,3,NULL,'Anh',NULL),(1,4,NULL,'Lí',NULL),(1,5,NULL,'Hóa',NULL),(1,6,NULL,'Sinh',NULL),(1,7,NULL,'Sử',NULL),(1,8,NULL,'Địa',NULL),(2,9,NULL,'Toán',NULL),(2,10,NULL,'Văn',NULL),(2,11,NULL,'Anh',NULL),(2,12,NULL,'Lí',NULL),(2,13,NULL,'Hóa',NULL),(2,14,NULL,'Sinh',NULL),(2,15,NULL,'Sử',NULL),(2,16,NULL,'Địa',NULL),(3,17,NULL,'Toán',NULL),(3,18,NULL,'Văn',NULL),(3,19,NULL,'Anh',NULL),(3,20,NULL,'Lí',NULL),(3,21,NULL,'Hóa',NULL),(3,22,NULL,'Sinh',NULL),(3,23,NULL,'Sử',NULL),(3,24,NULL,'Địa',NULL);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `hire_date` date DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKlicv62vmu1ydw117bbxqhkof1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('2019-08-20',2),('2019-08-20',3),('2019-08-20',4),('2019-08-20',5),('2019-08-20',6),('2019-08-20',7),('2019-08-20',8),('2019-08-20',9),('2019-08-20',10),('2019-08-20',11);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_subject`
--

DROP TABLE IF EXISTS `teacher_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_subject` (
  `subject_id` bigint NOT NULL,
  `teacher_id` bigint NOT NULL,
  KEY `FKdnhs9kxdlnrvhq5k111c87pna` (`subject_id`),
  KEY `FK625xnjha5rs0qqynxsthk646k` (`teacher_id`),
  CONSTRAINT `FK625xnjha5rs0qqynxsthk646k` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKdnhs9kxdlnrvhq5k111c87pna` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_subject`
--

LOCK TABLES `teacher_subject` WRITE;
/*!40000 ALTER TABLE `teacher_subject` DISABLE KEYS */;
INSERT INTO `teacher_subject` VALUES (1,2),(9,2),(17,2),(1,3),(4,3),(9,3),(12,3),(17,3),(20,3),(2,4),(10,4),(18,4),(3,5),(11,5),(19,5),(4,6),(12,6),(20,6),(5,7),(13,7),(21,7),(6,8),(14,8),(22,8),(7,9),(15,9),(23,9),(8,10),(16,10),(24,10),(7,11),(8,11),(15,11),(16,11),(23,11),(24,11);
/*!40000 ALTER TABLE `teacher_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetable` (
  `classroom_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `period_id` bigint DEFAULT NULL,
  `semester_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  `day_of_week` enum('FRIDAY','MONDAY','SATURDAY','SUNDAY','THURSDAY','TUESDAY','WEDNESDAY') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpka4rw7areqvljrvbwf6py7vw` (`classroom_id`),
  KEY `FKb4c4j9wko821amitn3a8gv7jh` (`period_id`),
  KEY `FKmkvrn4nnpjhpjxgqvd4dhfdbl` (`semester_id`),
  KEY `FKrh8c0l2hwidfd0hbp7ovnjpck` (`subject_id`),
  KEY `FKhd499vsb2ov3ecvj5suwxukc3` (`teacher_id`),
  CONSTRAINT `FKb4c4j9wko821amitn3a8gv7jh` FOREIGN KEY (`period_id`) REFERENCES `period` (`id`),
  CONSTRAINT `FKhd499vsb2ov3ecvj5suwxukc3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKmkvrn4nnpjhpjxgqvd4dhfdbl` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
  CONSTRAINT `FKpka4rw7areqvljrvbwf6py7vw` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKrh8c0l2hwidfd0hbp7ovnjpck` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable`
--

LOCK TABLES `timetable` WRITE;
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
INSERT INTO `timetable` VALUES (1,1,1,1,1,2,'MONDAY'),(1,2,1,1,2,4,'TUESDAY'),(1,3,1,1,7,9,'WEDNESDAY');
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `dob` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (NULL,1,4,NULL,'admin@gmail.com','$2a$10$/JWVlNK8ZPGS6fy7IIdt5.roJQxYmrk6olk2eA16Xv5SSecdfhMNy',NULL,NULL,NULL),('1885-07-01',2,3,'Liêm Hải','teacher1@gmail.com','$2a$10$a/GEHe7cI20E/YmojsLhMu78zZiMYSGCuRqGG3dvopuvbqrqli5ZW','1232131','Giáo viên 1','MALE'),('1881-11-16',3,3,'Nam Trực','teacher2@gmail.com','$2a$10$8pcdHlgDoQma3XWbnkH5LuulE8Rhdgec2/NIe8qir0otodmTfShiy','1232132','Giáo viên 2','MALE'),('1886-07-01',4,3,'Liêm Hải','teacher3@gmail.com','$2a$10$qcef7CovFNLppbkRRBymXO.xi7pT30gzzhV6aoGi2f.4s3z.5KmDi','1232133','Giáo viên 3','FEMALE'),('1882-11-16',5,3,'Nam Trực','teacher4@gmail.com','$2a$10$6UQwYlg.vYz6UoCMflQCd.TXdku.5s6yX3Q.NaTDnAZYzSoONiSw2','1232134','Giáo viên 4','FEMALE'),('1887-07-01',6,3,'Liêm Hải','teacher5@gmail.com','$2a$10$0cBt/TFJXcMl/JAzSQhC7OeRcsFs/yzfre.EJcy24WBvuhEVF8N9G','1232135','Giáo viên 5','FEMALE'),('1883-11-16',7,3,'Nam Trực','teacher6@gmail.com','$2a$10$UDK85.zepg4i8Wsnew.JZO7h5dA70W0b/738u1pJvs4ei0Wmm.KAO','1232136','Giáo viên 6','MALE'),('1888-07-01',8,3,'Liêm Hải','teacher7@gmal.com','$2a$10$i/gxZQbG3Lo8YdgbEVYFF.Su61i8u6VXsXiAeWbPQ2Os0skJQOHEK','1232137','Giáo viên 7','MALE'),('1884-11-16',9,3,'Nam Trực','teacher8@gmail.com','$2a$10$r54x38oWIbRAOfVW7/jkFO1yi6CL9sQrWe03Kd/ChcidDCDiNomKG','1232138','Giáo viên 8','FEMALE'),('1889-07-01',10,3,'Liêm Hải','teacher9@gmail.com','$2a$10$1z96g3XmjD5J849p5TfsA.jPbXkItWJU2a1ZSsr4bE67cM4KpQtEG','1232139','Giáo viên 9','FEMALE'),('1885-11-16',11,3,'Nam Trực','teacher10@gmail.com','$2a$10$HIWo90xdM6Xd//eSVki2zuc3T/aeGY4nTz4nc4dhunw45fCfvihiu','1232140','Giáo viên 10','FEMALE'),('2005-07-26',12,1,'Nam Hà, Cổ Lễ','student1@gmail.com','$2a$10$ywgIfrKI8T4IaOScbz1GwOHmP1gxM4p4T9K.q1nMaXzdJdvEikxEO','094324','Học sinh 1','MALE'),('2006-07-26',13,1,'Nam Hà, Cổ Lễ','student2@gmail.com','$2a$10$DqKHJleHYm5UH5851C3WaeNl/SVsS1xjSBPmNiBTaCoBsww.zdUVm','094325','Học sinh 2','MALE'),('2007-07-26',14,1,'Nam Hà, Cổ Lễ','student3@gmail.com','$2a$10$YgAJq7RuA0PKVY7JfOLmIu4cMSj5YxMZM4dmgWmZZ1UdLd9JmoADq','094326','Học sinh 3','FEMALE'),('2008-07-26',15,1,'Nam Hà, Cổ Lễ','student4@gmail.com','$2a$10$5xF1B1VCu0tZB4Q8zD9lguvH5LDSHrcJPs.zGKjUNvll2c/SlrZHW','094327','Học sinh 4','MALE'),('2009-07-26',16,1,'Nam Hà, Cổ Lễ','student5@gmail.com','$2a$10$eSYdIi9MEiK2WSwHc5eSKe3Efx4AjWrn5Lj2P7jQEJIIGfvv/0WA.','094328','Học sinh 5','MALE'),('2010-07-26',17,1,'Nam Hà, Cổ Lễ','student6@gmail.com','$2a$10$Rxf6msLC7.xPbzEqT8aOyuoN8Yf1hv3intg032jNzP4zCJgMietOe','094329','Học sinh 6','MALE'),('2011-07-26',18,1,'Nam Hà, Cổ Lễ','student7@gmail.com','$2a$10$1jxhT3MjYczchUDj3uJTrOuHn1YyIERs/60JL12JR42Z1KJXM1lZq','094330','Học sinh 7','FEMALE'),('2012-07-26',19,1,'Nam Hà, Cổ Lễ','student8@gmail.com','$2a$10$tZdYLmoaeN7ngYtcGGhJ2eLM4QcV/Qat/6AYavALeB4B8OhnCS7ju','094331','Học sinh 8','MALE'),('2013-07-26',20,1,'Nam Hà, Cổ Lễ','student9@gmail.com','$2a$10$8O.lyB2x7NgF3M1qly2NLexH4u.imEp4sfqM7FmOIIy2ofUaPLVRW','094332','Học sinh 9','MALE'),('2014-07-26',21,1,'Nam Hà, Cổ Lễ','student10@gmail.com','$2a$10$igIVk9kwhIPQuojItkBzleeQuafe9tNqMmk3HCpWJ22cU4JXx8xte','094333','Học sinh 10','MALE'),('2015-07-26',22,1,'Nam Hà, Cổ Lễ','student11@gmail.com','$2a$10$3P45XvuBGfTqv9xUM8ZKtOGwO4sILG6VjsywcS6PpbK6dUZraJrse','094334','Học sinh 11','FEMALE'),('2016-07-26',23,1,'Nam Hà, Cổ Lễ','student12@gmail.com','$2a$10$EIWxh7uqlptN.nNhyzy/N.hkrs3dV9UTMHFBH/NfJxyPj.0Lese2a','094335','Học sinh 12','MALE'),('2017-07-26',24,1,'Nam Hà, Cổ Lễ','student13@gmail.com','$2a$10$GsEHsCOzh7BtACVKyAyogePBPfZd9G7PtF2jOemZS5VV11VrV42k2','094336','Học sinh 13','MALE'),('2018-07-26',25,1,'Nam Hà, Cổ Lễ','student14@gmail.com','$2a$10$soKiny9z9X/mEnfUAz3Na.BHOrI5/1T9yG4qvuuHWq8VCo1TyefXy','094337','Học sinh 14','MALE'),('2019-07-26',26,1,'Nam Hà, Cổ Lễ','student15@gmail.com','$2a$10$33wH3l4rQjK.Zi.E659yAOO3MGZmxtonhcDn3e3/STp/n2r3H1IWy','094338','Học sinh 15','FEMALE'),('2020-07-26',27,1,'Nam Hà, Cổ Lễ','student16@gmail.com','$2a$10$VwQPsNUr1NW0jeLGXQfFpOfkr/osDlW3hszhRIouHlHmI4oGQe21m','094339','Học sinh 16','MALE'),('2021-07-26',28,1,'Nam Hà, Cổ Lễ','student17@gmail.com','$2a$10$WNjmeaLf8tXHOGSy9Q85buUEWsmWwlTMRu1ULy1iLNKehV6PrdN9C','094340','Học sinh 17','MALE'),('2022-07-26',29,1,'Nam Hà, Cổ Lễ','student18@gmail.com','$2a$10$aN6CRSiZZijCuhTkZDcyzeVgxjqde56jqbUJuh6BpAHw02pnqmQam','094341','Học sinh 18','MALE'),('2023-07-26',30,1,'Nam Hà, Cổ Lễ','student19@gmail.com','$2a$10$jzZHpgW4NE1w0q9NyMdCs.soZZxAFDHvMs.KbVLn4t7l21t8nWSb2','094342','Học sinh 19','FEMALE'),('2024-07-26',31,1,'Nam Hà, Cổ Lễ','student20@gmail.com','$2a$10$YXTnrrnRsdJbXHJWAKnIeennj0IzVUpxWG0039fuUIyrDCVLxGXXq','094343','Học sinh 20','MALE'),('2025-07-26',32,1,'Nam Hà, Cổ Lễ','student21@gmail.com','$2a$10$gZ.IYERBCE5SBs6mFpO85OjKWqkSghzJtgVRDBNcoBKOj05A9vxfO','094344','Học sinh 21','MALE'),('2026-07-26',33,1,'Nam Hà, Cổ Lễ','student22@gmail.com','$2a$10$v0aqZSohd2QoTbP1khtB8uGbgJn68Logv7/5mxYpoJDX6ZbJXvBYK','094345','Học sinh 22','MALE'),('2027-07-26',34,1,'Nam Hà, Cổ Lễ','student23@gmail.com','$2a$10$fno/htf5h76zCAJbyIGLu.fLEn637KSR9BveiZ6ey4BZiPDe/JHoa','094346','Học sinh 23','FEMALE'),('2028-07-26',35,1,'Nam Hà, Cổ Lễ','student24@gmail.com','$2a$10$kDr0tIOrl.Byenhm7DkgEe.pmEZZBW/ytsSCNltAGcuqwm1z4F1s2','094347','Học sinh 24','MALE'),('2029-07-26',36,1,'Nam Hà, Cổ Lễ','student25@gmail.com','$2a$10$Y5JjEQmGZIsIUb6lW3r9w.L9140LzL3HH25w0O422cFCqKCtRUoaW','094348','Học sinh 25','MALE'),('2030-07-26',37,1,'Nam Hà, Cổ Lễ','student26@gmail.com','$2a$10$SpxQCQXvIiD/zhulWJHNPuatUwQlLvFyTgk7yQ/A5j3NmmoQ0fIUq','094349','Học sinh 26','MALE'),('2031-07-26',38,1,'Nam Hà, Cổ Lễ','student27@gmail.com','$2a$10$JK1wV/hfWel2NfZPmzhcUO4DFT6OvirfjbNGHxqkgKt/CK3oENJW6','094350','Học sinh 27','FEMALE'),('2032-07-26',39,1,'Nam Hà, Cổ Lễ','student28@gmail.com','$2a$10$BEL7ZXETtEkmVqfT0bOweOFYZ.tXdwEbNzxzi70GPFsVyYf0Yqxje','094351','Học sinh 28','MALE'),('2033-07-26',40,1,'Nam Hà, Cổ Lễ','student29@gmail.com','$2a$10$ntZCggquuyJvwIXdxz051eGXc3wvWyu2BCyrPnbB2Ta4IOwftVLya','094352','Học sinh 29','MALE'),('2034-07-26',41,1,'Nam Hà, Cổ Lễ','student30@gmail.com','$2a$10$u7zY1LpdAh/gOJ5adJ11oelYogeJ8VZ4MN5cAWBBr5j5/41se1G9S','094353','Học sinh 30','MALE'),('2035-07-26',42,1,'Nam Hà, Cổ Lễ','student31@gmail.com','$2a$10$UGw.Ahsm4c0ZZDijbpg1ZePbhCL5Z7tzgjrBOrBwoDjbXIyWmar42','094354','Học sinh 31','FEMALE'),('2036-07-26',43,1,'Nam Hà, Cổ Lễ','student32@gmail.com','$2a$10$1pr2ef6LzMi7t7fV6YrKXOz3uih2rFPqy7Bqud552S27KMLj2nOsi','094355','Học sinh 32','MALE'),('2037-07-26',44,1,'Nam Hà, Cổ Lễ','student33@gmail.com','$2a$10$5gUIZ8ga5renvyoemffIhufzStB5qVjGUPKYE2xEw1Zr83tZgbOjy','094356','Học sinh 33','MALE'),('2038-07-26',45,1,'Nam Hà, Cổ Lễ','student34@gmail.com','$2a$10$vSvTeqe6/44dB96JolACEOpdZ0nY3hLyOslYdyEojQj4VkXTsm6Rq','094357','Học sinh 34','MALE'),('2039-07-26',46,1,'Nam Hà, Cổ Lễ','student35@gmail.com','$2a$10$7ryVd7CMiO60RadQUjmz/.oSc33ZvlnXxglm9el4xrWmnyhDUMovK','094358','Học sinh 35','FEMALE'),('2040-07-26',47,1,'Nam Hà, Cổ Lễ','student36@gmail.com','$2a$10$1zHkTSm9qz4ybca7gTR8gOSBsTAfQY5rcxaa2GbuF/AnCCj.MBw8a','094359','Học sinh 36','MALE'),('2041-07-26',48,1,'Nam Hà, Cổ Lễ','student37@gmail.com','$2a$10$ZIGi6qSWy2LGgx.5D4kd7uAt3bfiy3CzeWxO5e.ZbjR7QuvHM3aF.','094360','Học sinh 37','MALE'),('2042-07-26',49,1,'Nam Hà, Cổ Lễ','student38@gmail.com','$2a$10$.0BVdvWgp1S74rdHu1xnpOgU6gkmtYAoJlHvMXlXCFHQPMkzHupEC','094361','Học sinh 38','MALE'),('2043-07-26',50,1,'Nam Hà, Cổ Lễ','student39@gmail.com','$2a$10$b2SkQhvVYUJHwtTojIyoiuGbY5mqBxKU8.YYbpwOfWsegRGnoBBeu','094362','Học sinh 39','FEMALE'),('2044-07-26',51,1,'Nam Hà, Cổ Lễ','student40@gmail.com','$2a$10$TlG1uLPQSAN54IRMEbI7pOG6.QSSRtXYbDM.8htdg2qNl1iCH2Qt.','094363','Học sinh 40','MALE'),('2045-07-26',52,1,'Nam Hà, Cổ Lễ','student41@gmail.com','$2a$10$oTZ.nMK9268mJi7xlm6ILe7Jmu1rTjVe7F74gwpfOnYKWniebGOXG','094364','Học sinh 41','MALE'),('2046-07-26',53,1,'Nam Hà, Cổ Lễ','student42@gmail.com','$2a$10$JbCzUA4C9SE0mREpZbyTk.SCxrJIGjDLNlmdpUqrwVonUnYf6y//W','094365','Học sinh 42','MALE'),('2047-07-26',54,1,'Nam Hà, Cổ Lễ','student43@gmail.com','$2a$10$FwILSpCapO9XB1JuaI/y.um5nKALCH/nx92NoCuXdThYcbXhYWGue','094366','Học sinh 43','FEMALE'),('2048-07-26',55,1,'Nam Hà, Cổ Lễ','student44@gmail.com','$2a$10$z9DANQfxPzLo4qO0Qv8an./39Lb.EL063tw7Npv5BkxURIpv6.y4G','094367','Học sinh 44','MALE'),('2049-07-26',56,1,'Nam Hà, Cổ Lễ','student45@gmail.com','$2a$10$g9COoVkRAWIyxLrpiI9RYOs/uQLBLfsP26HhILCmthmx07YhAgB/e','094368','Học sinh 45','MALE'),('2050-07-26',57,1,'Nam Hà, Cổ Lễ','student46@gmail.com','$2a$10$YnaDMoXCzwfAuLnrFYjxv.Wrix4vzED/vIirvH2V5Pjzu.qv7BVjO','094369','Học sinh 46','MALE'),('2051-07-26',58,1,'Nam Hà, Cổ Lễ','student47@gmail.com','$2a$10$uL1KXZcIZcjt./qAEef4..66qV4ZcjKCMm6wy2YWm.cvWopHy8jhK','094370','Học sinh 47','FEMALE'),('2052-07-26',59,1,'Nam Hà, Cổ Lễ','student48@gmail.com','$2a$10$gmvlvPGUAr.7VY7l7vLrOuoroz8GsKK1RLE6wMpt7FgPhyuJZX4EO','094371','Học sinh 48','MALE'),('2053-07-26',60,1,'Nam Hà, Cổ Lễ','student49@gmail.com','$2a$10$pqrNI5gpLui/nn7Wv0b/TeAhwsb1RwtfwBtaGO/UxCEfh5L0sKLWu','094372','Học sinh 49','MALE'),('2054-07-26',61,1,'Nam Hà, Cổ Lễ','student50@gmail.com','$2a$10$WTcyDh6IM5eUB2hRbpNuGuczvRdJFoCJCV.ny4.h0b7EozGjNwExq','094373','Học sinh 50','MALE'),('2055-07-26',62,1,'Nam Hà, Cổ Lễ','student51@gmail.com','$2a$10$6sbxVneFRZSpNNbCj6f/ielTabZC4XRhBNscw5PeZqgziy6bcCKg6','094374','Học sinh 51','FEMALE'),('2056-07-26',63,1,'Nam Hà, Cổ Lễ','student52@gmail.com','$2a$10$IKCO3jBKQjDvLS4tEFjRGullHdy405Mpek1WrGkWlMyWX/kyDSIUS','094375','Học sinh 52','MALE'),('2057-07-26',64,1,'Nam Hà, Cổ Lễ','student53@gmail.com','$2a$10$eXMc9sJC6NKF0DnCcWoP7eLTOTxvXrq9yZeYBToxJJYJoj.rU/KNK','094376','Học sinh 53','MALE'),('2058-07-26',65,1,'Nam Hà, Cổ Lễ','student54@gmail.com','$2a$10$S1xhfKSJEVPJPl9nrToLiuXJMxR9oeOOT4tYRcZtLwCKuD0MXhASK','094377','Học sinh 54','MALE'),('2059-07-26',66,1,'Nam Hà, Cổ Lễ','student55@gmail.com','$2a$10$lpW0vhEUJgcMcn3qxX1ohuJozu1QtYg1eyzB3BbAjPKQxI0dlexBO','094378','Học sinh 55','FEMALE'),('2060-07-26',67,1,'Nam Hà, Cổ Lễ','student56@gmail.com','$2a$10$Grrmxzo.YU399ehjUhLSXOQQbpkvwOlxusDEBh/OKFajQwuGEwhXW','094379','Học sinh 56','MALE'),('2061-07-26',68,1,'Nam Hà, Cổ Lễ','student57@gmail.com','$2a$10$xsZWi77018uJKw0ddii9w.svBWx8G3pAZ2/4wd/t9T../y5OA4HyK','094380','Học sinh 57','MALE'),('2062-07-26',69,1,'Nam Hà, Cổ Lễ','student58@gmail.com','$2a$10$Q.uKaOd5qL6Uuxata2YM/.0CFfILKmWW6CXccgCdZp0ckY2/9mWJq','094381','Học sinh 58','MALE'),('2063-07-26',70,1,'Nam Hà, Cổ Lễ','student59@gmail.com','$2a$10$ksSo4ScmPfhD2gXDCfSB.u7DmZN43h8THY3VyR4dlF6cWOimHwQ5a','094382','Học sinh 59','FEMALE'),('2064-07-26',71,1,'Nam Hà, Cổ Lễ','student60@gmail.com','$2a$10$5B9YqTNuWoLk.s7Tjw1Kb.IJz4LXjQPzmYjg7Bo1WAYuW4rwN1aHy','094383','Học sinh 60','MALE'),('2065-07-26',72,1,'Nam Hà, Cổ Lễ','student61@gmail.com','$2a$10$Fz2awkB1NMFOVNHaWUFqZOOOJDxIVPoxwNLhgzBe0vjwtgc5dLm7O','094384','Học sinh 61','MALE'),('2066-07-26',73,1,'Nam Hà, Cổ Lễ','student62@gmail.com','$2a$10$A.KYWQOqGIk6ebjjdoKQAueae400MdVxgPR1GOYcqmS.qtc7b3hae','094385','Học sinh 62','MALE'),('2067-07-26',74,1,'Nam Hà, Cổ Lễ','student63@gmail.com','$2a$10$LBumq/Q4zWe53RJBzmkhYeDU6jZBtEmdu.O/VOT8DFDyo0twV0F3C','094386','Học sinh 63','FEMALE'),('2068-07-26',75,1,'Nam Hà, Cổ Lễ','student64@gmail.com','$2a$10$3mEiJjH0DrzSLPBc.UpC1eoyG2WvTdMw9mNxjt.3h0h50WmbW6XcC','094387','Học sinh 64','MALE'),('2069-07-26',76,1,'Nam Hà, Cổ Lễ','student65@gmail.com','$2a$10$crIr3AiyBDFJBHyTnKmc1OX8w9q/9W3I8NJBu60RNRlvF9.5.P86a','094388','Học sinh 65','MALE'),('2070-07-26',77,1,'Nam Hà, Cổ Lễ','student66@gmail.com','$2a$10$YzSTeoA8FOHDZ7JorQNiiOrHnWnoPMWRp5AWaSZRV3aXAry4B0NKu','094389','Học sinh 66','MALE'),('2071-07-26',78,1,'Nam Hà, Cổ Lễ','student67@gmail.com','$2a$10$RmDJdGp1Rts/gLkhLXHX6eXskSsF6olR57e9KaXt7uQ2scBHRFZ.q','094390','Học sinh 67','FEMALE'),('2072-07-26',79,1,'Nam Hà, Cổ Lễ','student68@gmail.com','$2a$10$zgGh./SbzK/UCrueo8a6Z..Tu0ynHgKxMqq7IiLT0O2uD3ILZzyp.','094391','Học sinh 68','MALE'),('2073-07-26',80,1,'Nam Hà, Cổ Lễ','student69@gmail.com','$2a$10$WnEHR9ZQ5tJXdU54uMbxI.3oCGYfA9snGdTPZK/wW28eDg5MFmmOS','094392','Học sinh 69','MALE'),('2074-07-26',81,1,'Nam Hà, Cổ Lễ','student70@gmail.com','$2a$10$Bmz5qVE76CMiRxsGwVNoyOV6Zmh6WYW84B864AEz6gT6k7zsWmhhO','094393','Học sinh 70','MALE'),('2075-07-26',82,1,'Nam Hà, Cổ Lễ','student71@gmail.com','$2a$10$l/K7s.69ecc4CR4dajm.zOIp0q.hZC5vPiCh91GWWAKtFY0xl9Ywy','094394','Học sinh 71','FEMALE'),('2076-07-26',83,1,'Nam Hà, Cổ Lễ','student72@gmail.com','$2a$10$IPuw4eYqPXr9Thqev4b4..jZZGScl6RKHm3xYQq9QRyUDBI9/hBrG','094395','Học sinh 72','MALE'),('2077-07-26',84,1,'Nam Hà, Cổ Lễ','student73@gmail.com','$2a$10$a.s1hbGMqu.bvcL0mTsjiuq0ZokYQH7pyZ6rsycip12E9lCAQfOpe','094396','Học sinh 73','MALE'),('2078-07-26',85,1,'Nam Hà, Cổ Lễ','student74@gmail.com','$2a$10$NTiQgK79pImg6EV3U.okjeQlilaBi4NgvBZQTLd5ST2afc7vr492G','094397','Học sinh 74','MALE'),('2079-07-26',86,1,'Nam Hà, Cổ Lễ','student75@gmail.com','$2a$10$jaYX8xJ6sduB9oafHIky8uYbJ7i0JKxHofHlS/aPJxLBgpG1wbxdO','094398','Học sinh 75','FEMALE'),('2080-07-26',87,1,'Nam Hà, Cổ Lễ','student76@gmail.com','$2a$10$NuokZ.HmDfRq.ChRlgz6juUQkkH8mD3IdGtCMqpljoYYuAcly28Ja','094399','Học sinh 76','MALE'),('2081-07-26',88,1,'Nam Hà, Cổ Lễ','student77@gmail.com','$2a$10$YREKV3Q3ioe/RN5tpqAjfuXLGySu2AHoQr1hahfyvwgTELObsR/Yy','094400','Học sinh 77','MALE'),('2082-07-26',89,1,'Nam Hà, Cổ Lễ','student78@gmail.com','$2a$10$T8yV1OygHXP5Fw2jvfDfSev.oKsqcEJS9ZpIYSWhoiaUGPFmS46l2','094401','Học sinh 78','MALE'),('2083-07-26',90,1,'Nam Hà, Cổ Lễ','student79@gmail.com','$2a$10$evxQPEl9IsUijImv8TWW1uEGegj0W9PwgnHRFD9XdtSB94gQfgC/q','094402','Học sinh 79','FEMALE'),('2084-07-26',91,1,'Nam Hà, Cổ Lễ','student80@gmail.com','$2a$10$a442HjsIFvVy3v/s.YcINeEgKZSJoL.oUlTueF.GbeR4j/DF4s8wy','094403','Học sinh 80','MALE'),('2085-07-26',92,1,'Nam Hà, Cổ Lễ','student81@gmail.com','$2a$10$H0viHxL9yXTIKgK3OP7UCu/oXsRQPO1OTIe2r2FntlyripCW10oFi','094404','Học sinh 81','MALE'),('2086-07-26',93,1,'Nam Hà, Cổ Lễ','student82@gmail.com','$2a$10$o9B9rf0vbFB1BoaND4wqEuWjOQCYmGgv00TzTE/ZOfXHo.xVA4p.i','094405','Học sinh 82','MALE'),('2087-07-26',94,1,'Nam Hà, Cổ Lễ','student83@gmail.com','$2a$10$zkAclze5fEQUcJtrqUaVguxGFGXpPvoNVXFDsRGeni73hMMrwsmnS','094406','Học sinh 83','FEMALE'),('2088-07-26',95,1,'Nam Hà, Cổ Lễ','student84@gmail.com','$2a$10$NDRsT6xIbBNgpyUmGObXv.K5KXSxX6I5E067Wv.VeG2s4NwmjAG5G','094407','Học sinh 84','MALE'),('2089-07-26',96,1,'Nam Hà, Cổ Lễ','student85@gmail.com','$2a$10$9dlD0zGS.qxLYjJsGhcPfukrvxxeANjJRFOvmkxiVXOBGlR25bsui','094408','Học sinh 85','MALE'),('2090-07-26',97,1,'Nam Hà, Cổ Lễ','student86@gmail.com','$2a$10$2NgjNSuMX7wtAz3KhU8iBuqnv5g/cAYrPgglzbfV1xosGBH63clJa','094409','Học sinh 86','MALE'),('2091-07-26',98,1,'Nam Hà, Cổ Lễ','student87@gmail.com','$2a$10$NuKfHxxf/20y7Be6q4QL9upeiVBFrHlf/YJwilaJ6msMFDoNwajt6','094410','Học sinh 87','FEMALE'),('2092-07-26',99,1,'Nam Hà, Cổ Lễ','student88@gmail.com','$2a$10$qHS0pR7SoQBOxdmlQ.VIdecl5VCSRvlHoJJGBn7HrG4QgiTY/TbQq','094411','Học sinh 88','MALE'),('2093-07-26',100,1,'Nam Hà, Cổ Lễ','student89@gmail.com','$2a$10$AgFYafR/LHasSifCvJfbIeTa2CQP65kW/ZGb/Mct4/Cnxm8XzNpe6','094412','Học sinh 89','MALE'),('2094-07-26',101,1,'Nam Hà, Cổ Lễ','student90@gmail.com','$2a$10$3xKU/l/K3eQ3D17XcjMMxeiMSdzV1I5HysXqrVIVayRLSfJeaianC','094413','Học sinh 90','MALE'),('2095-07-26',102,1,'Nam Hà, Cổ Lễ','student91@gmail.com','$2a$10$KctYXol8oZk5j4E/vmUQ7eViIfMhBqrEqdRpSWQ/ct4Dwqg8zn3ge','094414','Học sinh 91','FEMALE'),('2096-07-26',103,1,'Nam Hà, Cổ Lễ','student92@gmail.com','$2a$10$QS/bJ4nut2628OybQrhjw.C/zXRiTV0P7RgsGwTjHpg8kga1NrEgi','094415','Học sinh 92','MALE'),('2097-07-26',104,1,'Nam Hà, Cổ Lễ','student93@gmail.com','$2a$10$odwBceGLXL39Ww4T498/jOcTo/BRonulGhYvFEquO7pil9Bac1eL.','094416','Học sinh 93','MALE'),('2098-07-26',105,1,'Nam Hà, Cổ Lễ','student94@gmail.com','$2a$10$okzq6KPlVLGmVJgYo4DGze1aMcldNj7CoPq7QLEqbbsKuk0S/9vHq','094417','Học sinh 94','MALE'),('2099-07-26',106,1,'Nam Hà, Cổ Lễ','student95@gmail.com','$2a$10$m4AQO93E/wPquwOYJaHWC.FdapGWAG1Zqrl25EAp56DbSXf.7KsAa','094418','Học sinh 95','FEMALE'),('2100-07-26',107,1,'Nam Hà, Cổ Lễ','student96@gmail.com','$2a$10$mdIjxfAKoELvn/Fxr0c7M..KBCiN6tkp.lIPyFqOlo6qB96S.Ko62','094419','Học sinh 96','MALE'),('2101-07-26',108,1,'Nam Hà, Cổ Lễ','student97@gmail.com','$2a$10$7DxVq1WS1em60D7RLVeMou7cNmeqcf7Tavge8Seacn97Pu9HWy.Oa','094420','Học sinh 97','MALE'),('2102-07-26',109,1,'Nam Hà, Cổ Lễ','student98@gmail.com','$2a$10$mpUpYHMyRDf69xqNKohGNemmiwTTHOCuCS8hbAu78bFHRcft8WGma','094421','Học sinh 98','MALE'),('2103-07-26',110,1,'Nam Hà, Cổ Lễ','student99@gmail.com','$2a$10$ghcOp1XT8CP7IShZLfgRB.H84CkRAKmYyw2LHLoT6XF271j0fUVCm','094422','Học sinh 99','FEMALE'),('2104-07-26',111,1,'Nam Hà, Cổ Lễ','student100@gmail.com','$2a$10$aNmT82R0ZZAMXwURIj3vjesylksY3V2MOMfgnMXkNZi3IEX4Pp1SS','094423','Học sinh 100','MALE');
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

-- Dump completed on 2025-10-21 21:09:36
