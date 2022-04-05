-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: 104.248.34.253    Database: irusuk
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `country_code` char(2) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `item_id` varchar(36) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `date` date NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `country_code` varchar(2) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL DEFAULT '0',
  `longitude` double NOT NULL DEFAULT '0',
  `requests` int DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip_aton` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`,`ip`,`date`),
  KEY `fk_event_1_idx` (`item_id`,`ip`,`date`,`country_code`,`latitude`,`longitude`),
  CONSTRAINT `fk_event_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`trilobiet`@`83.163.15.48`*/ /*!50003 TRIGGER `before_insert_event` BEFORE INSERT ON `event` FOR EACH ROW SET 
  new.ip_aton = if( is_ipv4(new.ip), inet_aton(new.ip), 0 ) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `funder`
--

DROP TABLE IF EXISTS `funder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funder` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ip_range`
--

DROP TABLE IF EXISTS `ip_range`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ip_range` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(36) DEFAULT NULL,
  `ip_start` varchar(40) NOT NULL,
  `ip_end` varchar(40) NOT NULL,
  `ip_start_aton` bigint NOT NULL,
  `ip_end_aton` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `ips_user_id_unique` (`user_id`,`ip_start`,`ip_end`),
  KEY `IDX` (`ip_start_aton`,`ip_end_aton`,`user_id`),
  CONSTRAINT `fk_ip_range_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`trilobiet`@`83.163.15.48`*/ /*!50003 TRIGGER `before_insert_library_ips` BEFORE INSERT ON `ip_range` FOR EACH ROW SET 
  new.ip_start_aton = inet_aton(new.ip_start),
  new.ip_end_aton = inet_aton(new.ip_end) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `iplocation`
--

DROP TABLE IF EXISTS `iplocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iplocation` (
  `IP_FROM` bigint NOT NULL,
  `IP_TO` bigint NOT NULL,
  `COUNTRY_CODE` char(2) DEFAULT NULL,
  `COUNTRY_NAME` varchar(64) DEFAULT NULL,
  `REGION` varchar(128) DEFAULT NULL,
  `CITY` varchar(128) DEFAULT NULL,
  `LATITUDE` double DEFAULT NULL,
  `LONGITUDE` double DEFAULT NULL,
  UNIQUE KEY `IP_FROM_UNIQUE` (`IP_FROM`),
  UNIQUE KEY `IP_TO_UNIQUE` (`IP_TO`),
  KEY `index1` (`IP_FROM`,`IP_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` varchar(36) NOT NULL,
  `title` varchar(300) DEFAULT NULL,
  `publisher_name` varchar(255) DEFAULT NULL,
  `publisher_id` char(36) DEFAULT NULL,
  `authors` varchar(300) DEFAULT NULL,
  `doi` varchar(255) DEFAULT NULL,
  `isbn` char(13) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `year` smallint DEFAULT NULL,
  `grant_number` varchar(255) DEFAULT NULL,
  `grant_program` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `irus_id` varchar(10) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_funder`
--

DROP TABLE IF EXISTS `item_funder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_funder` (
  `funder_id` varchar(36) NOT NULL,
  `item_id` varchar(36) NOT NULL,
  KEY `fk_item_funder_1_idx` (`item_id`),
  KEY `fk_item_funder_2_idx` (`funder_id`),
  CONSTRAINT `fk_item_funder_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_item_funder_2` FOREIGN KEY (`funder_id`) REFERENCES `funder` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` char(36) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `role` enum('funder','publisher','library','admin') NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `irus_id` varchar(369) DEFAULT NULL,
  `country_code` varchar(30) NOT NULL DEFAULT '',
  `lat` double NOT NULL DEFAULT '0',
  `lon` double NOT NULL DEFAULT '0',
  `editable` tinyint NOT NULL DEFAULT '1',
  `initial_radius` varchar(45) NOT NULL DEFAULT '50',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-05 16:21:06
