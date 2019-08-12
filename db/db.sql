CREATE DATABASE  IF NOT EXISTS `booking_hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `booking_hotel`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: booking_hotel
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `applications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `name` varchar(5) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `amount_people` int(2) NOT NULL,
  `amount_children` int(2) NOT NULL,
  `type_of_number` varchar(45) NOT NULL,
  `note` text,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  `date_of_booking` timestamp NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_application_users1_idx` (`users_id`),
  CONSTRAINT `fk_application_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (46,4,'Eva','Bricket',2,1,'Double + child',NULL,'2019-08-11','2019-08-15','2019-08-11 13:14:43',1),(49,2,'Dima','Dem',1,0,'Single',NULL,'2019-08-13','2019-08-17','2019-08-12 10:20:15',1),(50,5,'Kate','Karry',3,0,'Triple(luxe)','Reply from Admin: Sorry, we have not empty room in this period od time','2019-08-15','2019-08-20','2019-08-12 10:33:17',1),(51,2,'Dima','Dem',2,0,'Double',NULL,'2019-08-20','2019-08-23','2019-08-12 12:10:24',1),(52,2,'Dima','Dem',1,0,'Single',NULL,'2019-08-13','2019-08-14','2019-08-12 12:48:46',1);
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1344945322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (1122367925,140000,1),(1248400227,280000,1),(1344945321,180000,0);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_numbers`
--

DROP TABLE IF EXISTS `hotel_numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel_numbers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `short_type` varchar(45) NOT NULL,
  `amount` int(2) NOT NULL,
  `price` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `type_ukr` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_numbers`
--

LOCK TABLES `hotel_numbers` WRITE;
/*!40000 ALTER TABLE `hotel_numbers` DISABLE KEYS */;
INSERT INTO `hotel_numbers` VALUES (1,'SGL',4,35000,'Single ','одномісний'),(2,'DBL',6,60000,'Double ','двомісний'),(3,'DBL+CHD',2,70000,'Double + child','двомісний + дитина'),(4,'DBL+2CHD',2,80000,'Double + 2 child','двомісний + 2 дитини'),(5,'DL',2,120000,'De luxe','Люкс'),(6,'Trpl(l)',1,160000,'Triple(luxe)','тримісний люкс');
/*!40000 ALTER TABLE `hotel_numbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserved_rooms`
--

DROP TABLE IF EXISTS `reserved_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reserved_rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `room_id` int(3) NOT NULL,
  `bills_id` int(11) NOT NULL,
  `applications_id` int(11) NOT NULL,
  `date_from` date NOT NULL,
  `date_before` date NOT NULL,
  `date_of_booking` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idboked_rooms_UNIQUE` (`id`),
  KEY `fk_boked_rooms_users1_idx` (`user_id`),
  KEY `fk_boked_rooms_rooms_translate1_idx` (`room_id`),
  KEY `fk_reserved_rooms_bills1_idx` (`bills_id`),
  KEY `fk_reserved_rooms_applications1_idx` (`applications_id`),
  CONSTRAINT `fk_boked_rooms_rooms_translate1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `fk_boked_rooms_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_reserved_rooms_applications1` FOREIGN KEY (`applications_id`) REFERENCES `applications` (`id`),
  CONSTRAINT `fk_reserved_rooms_bills1` FOREIGN KEY (`bills_id`) REFERENCES `bills` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserved_rooms`
--

LOCK TABLES `reserved_rooms` WRITE;
/*!40000 ALTER TABLE `reserved_rooms` DISABLE KEYS */;
INSERT INTO `reserved_rooms` VALUES (12,4,11,1248400227,46,'2019-08-11','2019-08-15','2019-08-11 13:14:43'),(13,2,1,1122367925,49,'2019-08-13','2019-08-17','2019-08-12 10:20:15'),(14,2,5,1344945321,51,'2019-08-20','2019-08-23','2019-08-12 12:10:24');
/*!40000 ALTER TABLE `reserved_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rooms` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `hotel_number_id` int(11) NOT NULL,
  `short_type` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_rooms_hotel_numbers1_idx` (`hotel_number_id`),
  CONSTRAINT `fk_rooms_hotel_numbers1` FOREIGN KEY (`hotel_number_id`) REFERENCES `hotel_numbers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,1,'SGL'),(2,1,'SGL'),(3,1,'SGL'),(4,1,'SGL'),(5,2,'DBL'),(6,2,'DBL'),(7,2,'DBL'),(8,2,'DBL'),(9,2,'DBL'),(10,2,'DBL'),(11,3,'DBL+CHD'),(12,3,'DBL+CHD'),(13,4,'DBL+2CHD'),(14,4,'DBL+2CHD'),(15,5,'DL'),(16,5,'DL'),(17,6,'Trpl(l)');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `users_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin@gmail.com','admin'),(2,'dima','password','dimas@gmail.com','user'),(4,'eva','evaeva','eva@gmail.com','user'),(5,'Kate','katekate','kate@gmail.com','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-12 22:04:06
