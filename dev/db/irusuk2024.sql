-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: 104.248.34.253    Database: irusuk
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.20.04.3

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
  `latitude` float NOT NULL DEFAULT '0',
  `longitude` float NOT NULL DEFAULT '0',
  `requests` int DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip_aton` bigint DEFAULT NULL,
  `ipsr16` smallint unsigned DEFAULT NULL,
  `intlon` smallint GENERATED ALWAYS AS (truncate(`longitude`,0)) STORED,
  `intlat` smallint GENERATED ALWAYS AS (truncate(`latitude`,0)) STORED,
  `year` smallint GENERATED ALWAYS AS (year(`date`)) STORED,
  `yearmonth` smallint GENERATED ALWAYS AS ((((year(`date`) - 2000) * 100) + month(`date`))) STORED,
  PRIMARY KEY (`item_id`,`ip`,`date`),
  KEY `idx_event_country_code` (`country_code`),
  KEY `idx_event_itm` (`item_id`),
  KEY `idx_event_lalo` (`intlat`,`intlon`),
  KEY `idx_event_ipsr16` (`ipsr16`),
  KEY `idx_event_yearmonth` (`yearmonth`),
  KEY `idx_event_date` (`date`),
  KEY `idx_event_intlat` (`intlat`),
  KEY `idx_event_intlon` (`intlon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`date`))
(PARTITION p0 VALUES LESS THAN (2021) ENGINE = InnoDB,
 PARTITION p1 VALUES LESS THAN (2022) ENGINE = InnoDB,
 PARTITION p2 VALUES LESS THAN (2023) ENGINE = InnoDB,
 PARTITION p3 VALUES LESS THAN (2024) ENGINE = InnoDB,
 PARTITION p4 VALUES LESS THAN (2025) ENGINE = InnoDB,
 PARTITION p5 VALUES LESS THAN (2026) ENGINE = InnoDB,
 PARTITION p6 VALUES LESS THAN (2027) ENGINE = InnoDB,
 PARTITION p7 VALUES LESS THAN (2028) ENGINE = InnoDB,
 PARTITION p8 VALUES LESS THAN (2029) ENGINE = InnoDB,
 PARTITION p9 VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`trilobiet`@`localhost`*/ /*!50003 TRIGGER `before_insert_event` BEFORE INSERT ON `event` FOR EACH ROW SET 
	new.ip_aton = if( is_ipv4(new.ip), inet_aton(new.ip), 0 ),
    new.ipsr16 = if( is_ipv4(new.ip), inet_aton(new.ip) >> 16, 0 ) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `events_all_data`
--

DROP TABLE IF EXISTS `events_all_data`;
/*!50001 DROP VIEW IF EXISTS `events_all_data`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `events_all_data` AS SELECT 
 1 AS `ip`,
 1 AS `date`,
 1 AS `country`,
 1 AS `country_code`,
 1 AS `city`,
 1 AS `longitude`,
 1 AS `latitude`,
 1 AS `requests`,
 1 AS `ip_aton`,
 1 AS `item_id`,
 1 AS `title`,
 1 AS `publisher_id`,
 1 AS `publisher_name`,
 1 AS `authors`,
 1 AS `doi`,
 1 AS `isbn`,
 1 AS `type`,
 1 AS `year`,
 1 AS `grant_number`,
 1 AS `grant_program`,
 1 AS `funder_id`,
 1 AS `funder_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `events_all_data_coarse_coords`
--

DROP TABLE IF EXISTS `events_all_data_coarse_coords`;
/*!50001 DROP VIEW IF EXISTS `events_all_data_coarse_coords`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `events_all_data_coarse_coords` AS SELECT 
 1 AS `ip`,
 1 AS `date`,
 1 AS `country`,
 1 AS `country_code`,
 1 AS `city`,
 1 AS `longitude`,
 1 AS `latitude`,
 1 AS `requests`,
 1 AS `ip_aton`,
 1 AS `item_id`,
 1 AS `title`,
 1 AS `publisher_id`,
 1 AS `publisher_name`,
 1 AS `authors`,
 1 AS `doi`,
 1 AS `isbn`,
 1 AS `type`,
 1 AS `year`,
 1 AS `grant_number`,
 1 AS `grant_program`,
 1 AS `funder_id`,
 1 AS `funder_name`*/;
SET character_set_client = @saved_cs_client;

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
  `ip_startsr16` smallint unsigned DEFAULT NULL,
  `ip_endsr16` smallint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `ips_user_id_unique` (`user_id`,`ip_start`,`ip_end`),
  KEY `IDX` (`ip_start_aton`,`ip_end_aton`,`user_id`),
  KEY `idx_iprange_user_id` (`user_id`),
  CONSTRAINT `fk_ip_range_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6609 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`trilobiet`@`localhost`*/ /*!50003 TRIGGER `before_insert_library_ips` BEFORE INSERT ON `ip_range` FOR EACH ROW SET 
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
-- Table structure for table `iplocation.bak_202111`
--

DROP TABLE IF EXISTS `iplocation.bak_202111`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iplocation.bak_202111` (
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
-- Table structure for table `iplocation.bak_202204`
--

DROP TABLE IF EXISTS `iplocation.bak_202204`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iplocation.bak_202204` (
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
-- Table structure for table `iplocation.bak_202209`
--

DROP TABLE IF EXISTS `iplocation.bak_202209`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iplocation.bak_202209` (
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
-- Table structure for table `iplocation.bak_202312`
--

DROP TABLE IF EXISTS `iplocation.bak_202312`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iplocation.bak_202312` (
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
  PRIMARY KEY (`id`) KEY_BLOCK_SIZE=16,
  UNIQUE KEY `id_UNIQUE` (`id`) KEY_BLOCK_SIZE=16,
  KEY `idx_item_id` (`id`) KEY_BLOCK_SIZE=16
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci KEY_BLOCK_SIZE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `item_complete`
--

DROP TABLE IF EXISTS `item_complete`;
/*!50001 DROP VIEW IF EXISTS `item_complete`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `item_complete` AS SELECT 
 1 AS `id`,
 1 AS `title`,
 1 AS `publisher_name`,
 1 AS `publisher_id`,
 1 AS `authors`,
 1 AS `doi`,
 1 AS `isbn`,
 1 AS `type`,
 1 AS `year`,
 1 AS `grant_number`,
 1 AS `grant_program`,
 1 AS `platform`,
 1 AS `irus_id`,
 1 AS `updated_at`,
 1 AS `created_at`,
 1 AS `funder_ids`*/;
SET character_set_client = @saved_cs_client;

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
  `irus_id` varchar(2048) DEFAULT NULL,
  `country_code` varchar(30) NOT NULL DEFAULT '',
  `lat` double NOT NULL DEFAULT '0',
  `lon` double NOT NULL DEFAULT '0',
  `editable` tinyint NOT NULL DEFAULT '1',
  `initial_radius` varchar(45) NOT NULL DEFAULT '50',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'irusuk'
--

--
-- Dumping routines for database 'irusuk'
--
/*!50003 DROP FUNCTION IF EXISTS `intersect` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`45.80.169.103` FUNCTION `intersect`(
	set1 VARCHAR(1024),
    set2 VARCHAR(1024)
) RETURNS tinyint(1)
    NO SQL
    DETERMINISTIC
BEGIN
	RETURN false;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `event_count_per_country` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `event_count_per_country`(

    in startDate DATE,	
    in endDate DATE,

    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    in itemType VARCHAR(15),		# query only for a specific type (book/chapter)
	in itemId VARCHAR(36)			# query only for a specific title
)
BEGIN
	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare endMonth, startMonth DATE;
    
    set endDate = ifnull(endDate, curdate());
	set endMonth = date_sub(endDate, INTERVAL dayofmonth(endDate)-1 DAY);
    set startMonth = date_sub(startDate, INTERVAL dayofmonth(startDate)-1 DAY);

	select  
		country_code, latitude, longitude, requests
    from (
		select e.country_code, c.latitude, c.longitude, sum(requests) as requests
		from item join event e on item.id = e.item_id
        join countries c on c.country_code = e.country_code
		left join (
			item_funder inner join funder on item_funder.funder_id = funder.id    
		) on item_funder.item_id = item.id 
		where date >= startMonth and date <= endMonth
			and if( itemId is null, true, item.id = itemId )
            and if( itemType is null, true, item.type = itemType )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
            and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
		group by e.country_code, c.latitude, c.longitude
	) tmp
    order by country_code;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `event_count_per_region` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `event_count_per_region`(

    in startDate DATE,	
    in endDate DATE,

    in lat DOUBLE,					# query in an area defined by a geo location ... 
    in lon DOUBLE,					# ... 
    in radius int,					# ... and radius (in km)
    in countryCode VARCHAR(20),		# query only for specific countries

    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    in itemType VARCHAR(15),		# query only for a specific type (book/chapter)
	in itemId VARCHAR(36)			# query only for a specific title
)
BEGIN
	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare endMonth, startMonth DATE;
    declare mradius, crudeSquare int;
    
    set endDate = ifnull(endDate, curdate());
	set endMonth = date_sub(endDate, INTERVAL dayofmonth(endDate)-1 DAY);
    set startMonth = date_sub(startDate, INTERVAL dayofmonth(startDate)-1 DAY);
    
    set mradius = radius * 1000; # radius in meter
    # rough square area to limit exact searches 
    # (chosen large enough to account for longitude scaling towards the equator) 
    set crudeSquare = greatest(2, radius / 25); 

	select  
		city, country_code, latitude, longitude, requests
    from (
		select 
			round(avg(latitude),5) as latitude, 
			round(avg(longitude),5) as longitude, 
            any_value(country_code) as country_code,
            city,
            sum(requests) as requests
		from item join event on item.id = event.item_id
		left join (
			item_funder inner join funder on item_funder.funder_id = funder.id    
		) on item_funder.item_id = item.id 
		where 
			date >= startMonth and date <= endMonth
            and if( countryCode is null, true, FIND_IN_SET(event.country_code, countryCode) )
            and abs(latitude-lat) < crudeSquare # These 2 clauses prevent too far out of range locations 
			and	abs(longitude-lon) < crudeSquare # from being sent to (expensive) ST_Distance_Sphere
			and	ST_Distance_Sphere( point(longitude,latitude), point(lon,lat) ) < mradius
			and if( itemId is null, true, item.id = itemId )
            and if( itemType is null, true, item.type = itemType )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
            and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
            
		group by city
	) tmp
    order by city
	;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_country` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_country`(
	in fromDate DATE,				# query for this month (and all 11 months before) 
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    in itemType VARCHAR(15),			# query only for a specific type (book/chapter)
    in itemId VARCHAR(36)			# query only for a specific title
)
BEGIN
	# THIS VERSION UPDATED 2024

	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    
    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 12 MONTH);
    
	select 
		  tmp.country_code, any_value(tmp.country) as country, latitude, longitude, date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (
		/* Choose any_value since we do not want to group on country */
		select date, country_code, any_value(country) as country, sum(requests) as mtot
        
		from ( #  funder join AND where clause is handled here, to prevent multiplying items for the number of funders they have
			select item.*, any_value(funder_id) as funder_ids
			from (item left join item_funder on item_funder.item_id = item.id)
			where if( funderIds is null or length(trim(funderIds)) = 0, true, FIND_IN_SET(funder_id, funderIds) )
			group by item.id
		) item
        join event on item.id = event.item_id
        
		where 
			date > yearBefore
			and if( itemId is null or length(trim(itemId)) = 0, true, item.id = itemId )
			and if( publisherIds is null or length(trim(publisherIds)) = 0, true, FIND_IN_SET(publisher_id, publisherIds) )
            and if( itemType is null or length(trim(itemType)) = 0, true, item.type = itemType )
		group by date, country_code
	) tmp
    join countries on tmp.country_code = countries.country_code # add country latlon
	group by country_code
    having total is not null
	order by total desc, country_code;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_country_OLD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_country_OLD`(
	in fromDate DATE,				# query for this month (and all 11 months before) 
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    in itemType VARCHAR(15),			# query only for a specific type (book/chapter)
    in itemId VARCHAR(36)			# query only for a specific title
)
BEGIN
	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    
    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 12 MONTH);
    
	select 
		  tmp.country_code, any_value(tmp.country) as country, latitude, longitude, date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (
		/* Choose any_value since we do not want to group on country */
		select date, country_code, any_value(country) as country, sum(requests) as mtot
		from item join event on item.id = event.item_id
		left join (
			item_funder inner join funder on item_funder.funder_id = funder.id    
		) on item_funder.item_id = item.id 
		where 
			date > yearBefore
			and if( itemId is null or length(trim(itemId)) = 0, true, item.id = itemId )
			and if( publisherIds is null or length(trim(publisherIds)) = 0, true, FIND_IN_SET(publisher_id, publisherIds) )
            and if( funderIds is null or length(trim(funderIds)) = 0, true, FIND_IN_SET(funder_id, funderIds) )
            and if( itemType is null or length(trim(itemType)) = 0, true, item.type = itemType )
		group by date, country_code
	) tmp
    join countries on tmp.country_code = countries.country_code # add country latlon
	group by country_code
    having total is not null
	order by total desc, country_code;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_item_for_library` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_item_for_library`(
    
    in fromDate DATE,				# query for this month (and all 11 months before) 
	in libraryId VARCHAR(36),		# library id (from user table)
    
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    
    in itemType VARCHAR(15),		# query only for a specific type (book/chapter)
    in itemIds VARCHAR(1024)		# query only for specific titles (comma sep) since we limit to 1000 rows
)
BEGIN
	# THIS VERSION UPDATED 2024

	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    declare countryCode CHAR(2); # TODO maybe allow for comma list then match with FIND_IN_SET
    declare liblat, liblon, latfrom, latto, lonfrom, lonto int;
    
    set countryCode = 'XX'; # get library's country code from user table (this narrows the rows to be searched)
    set countryCode = (select country_code from user where id = libraryId limit 1); 

	set liblat  = (select lat from user where id = libraryId limit 1);
    set liblon  = (select lon from user where id = libraryId limit 1);
    set latfrom = truncate(liblat,0) - 1;
    set latto   = truncate(liblat,0) + 1;
    set lonfrom = truncate(liblon,0) - 2;
    set lonto   = truncate(liblon,0) + 2;

    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 11 MONTH); # BETWEEN is inclusive!

	select 
		  q.id, isbn, title, publisher_name, doi, type, 
          group_concat(distinct funder.name) as funders,
          date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (

		select 
			ev.item_id as id,
            isbn, title, publisher_name, doi, type,
			date, sum(requests) as mtot 
        
			FROM event ev # force index (idx_event_lalo)
			join ip_range r on (
				#ev.ipsr16 between r.ip_startsr16 and r.ip_endsr16 and # too slow
				ev.ip_aton between r.ip_start_aton and r.ip_end_aton and r.user_id = libraryId
            )
            
            #join (select * from ip_range where user_id = libraryId order by ip_start_aton) r
            #on (
			#	#ev.ipsr16 between r.ip_startsr16 and r.ip_endsr16 and # too slow
			#	ev.ip_aton between r.ip_start_aton and r.ip_end_aton
            #) 
            
            join item on ev.item_id = item.id 
            
			where ev.country_code = countryCode
            # Though they make the query much faster, these lines have been commented out 
            # because we may have ip addresses not located in a continuous area in case 
            # of a consortium of libraries...
			#and intlat between latfrom AND latto # These 2 clauses prevent too far out of range locations 
			#and intlon between lonfrom AND lonto 
			and ev.date between yearBefore and fromDate
            
			and if( itemIds is null, true, FIND_IN_SET(item.id, itemIds ) )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
            and if( itemType is null, true, item.type = itemType )
		
		group by date, item.id

	) q
    
	left join (
		item_funder inner join funder on item_funder.funder_id = funder.id    
			and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
	) on item_funder.item_id = q.id 
    
	group by q.id
	having total is not null
	order by total desc
    limit 2500;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_item_for_library_OLD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_item_for_library_OLD`(
    
    in fromDate DATE,				# query for this month (and all 11 months before) 
	in libraryId VARCHAR(36),		# library id (from user table)
    
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    
    in itemType VARCHAR(15),		# query only for a specific type (book/chapter)
    in itemIds VARCHAR(1024)		# query only for specific titles (comma sep) since we limit to 1000 rows
)
BEGIN
	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    declare countryCode CHAR(2); # TODO maybe allow for comma list then match with FIND_IN_SET
    
    set countryCode = 'XX'; # get library's country code from user table (this narrows the rows to be searched)
    set countryCode = (select country_code from user where id = libraryId limit 1); 

    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 12 MONTH);

	select 
		  id, isbn, title, publisher_name, doi, type, 
          group_concat(distinct funders) as funders,
          date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (

		select 
			item.id as id,
            isbn, title, publisher_name, doi, type,
            group_concat(distinct funder.name) as funders,  
			date, sum(requests) as mtot 
			FROM event ev 
				join ip_range l on 
					ip_aton >= ip_start_aton AND ip_aton <= l.ip_end_aton
                join item on item_id = item.id 
                left join (
					item_funder inner join funder on item_funder.funder_id = funder.id    
				) on item_funder.item_id = item.id 
			where country_code = countryCode  
            and user_id = libraryId
            and date > yearBefore
			and date <= fromMonth

			and if( itemIds is null, true, FIND_IN_SET(item.id, itemIds ) )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
			and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
            and if( itemType is null, true, item.type = itemType )
        
			group by date, ev.item_id

	) q
	group by id
	having total is not null
	order by total desc
    limit 2500;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_item_for_pubfun` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_item_for_pubfun`(
	in fromDate DATE,				# query for this month (and all 11 months before) 
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    in countryCode CHAR(2),			# query only for a specific country
    in itemType VARCHAR(15),		# query only for a specific type
    in itemId VARCHAR(36)			# query only for a specific title
)
BEGIN
	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    
    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 12 MONTH);
    
	select 
		  id, isbn, title, publisher_name, doi, type
        , group_concat(distinct funders) as funders 
        #, nullif(countryCode,(select country from event where country_code=countryCode limit 1)) as country
		, date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (
		select 
			  date, item.id as id, isbn, title, publisher_name, doi, type
            , group_concat(distinct funder.name) as funders  
            , sum(requests) as mtot
		from item inner join event on item.id = event.item_id
		left join (
			item_funder inner join funder on item_funder.funder_id = funder.id    
		) on item_funder.item_id = item.id 
		where 
			date > yearBefore 
			and if( itemId is null, true, item.id = itemId )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
            and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
            and if( itemType is null, true, item.type = itemType )
            and if( countryCode is null, true, event.country_code = countryCode )
		group by date, item.id
	) tmp
	group by id
    having total is not null
	order by total desc
    ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_item_for_region` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_item_for_region`(
	
    in fromDate DATE,				# query for this month (and all 11 months before) 
    in lat DOUBLE,					# query in an area defined by a geo location ... 
    in lon DOUBLE,					# ... 
    in radius int,					# ... and radius (in km)
    
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    
    in itemType VARCHAR(15),		# query only for a specific type (book/chapter)
    in itemIds VARCHAR(1024)		# query only for specific titles (comma sep) since we limit to 1000 rows
)
BEGIN
	# THIS VERSION UPDATED 2024

	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    declare meterRadius, latfrom, latto, lonfrom, lonto, lonsize int;
    
    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 12 MONTH);
    set meterRadius = radius * 1000; # radius in meter
    
    # rough square area to limit exact searches 
    # (chosen large enough to account for longitude narrowing towards the poles)
    # distance between 2 lat integers = 111 km
    # distance between 2 lon integers = 111 at equator and 50 at 63
    # see https://www.omnicalculator.com/other/latitude-longitude-distance
    # this way lonfrom - lonto are always at least 200 km apart
    # but do not zoom out more than 200 km!
    set lonsize = if (truncate(lat,0) > 63, 3, 2); 
    
    set latfrom = truncate(lat,0)-1;
    set latto = truncate(lat,0)+1;
    set lonfrom = truncate(lon,0)-lonsize;
    set lonto = truncate(lon,0)+lonsize;
    
	select 
		  id, isbn, title, publisher_name, doi, type
        , group_concat(distinct funders) as funders 
        #, nullif(countryCode,(select country from event where country_code=countryCode limit 1)) as country
		, date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (
        
		select 
			date, item.id as id, isbn, title, publisher_name, doi, type,
            group_concat(distinct funder.name) as funders,  
			sum(requests) as mtot
		from 
			event ev force index(idx_event_lalo) 
			join item on ev.item_id = item.id 
			left join (
				item_funder inner join funder on item_funder.funder_id = funder.id    
			) on item_funder.item_id = item.id 
		where 
			date > yearBefore and date <= fromMonth
			and intlat between latfrom and latto  # These 2 clauses prevent too far out of range locations 
			and intlon between lonfrom and lonto  # These 2 clauses prevent too far out of range locations 
			and ST_Distance_Sphere( point(longitude,latitude), point(lon,lat) ) < meterRadius

			and if( itemIds is null, true, FIND_IN_SET(item.id, itemIds ) )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
			and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
            and if( itemType is null, true, item.type = itemType )
        
        group by date, item.id
        
	) tmp
	group by id
    having total is not null
	order by total desc
    limit 2500
    ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `month_totals_per_item_for_region_OLD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`trilobiet`@`localhost` PROCEDURE `month_totals_per_item_for_region_OLD`(
	
    in fromDate DATE,				# query for this month (and all 11 months before) 
    in lat DOUBLE,					# query in an area defined by a geo location ... 
    in lon DOUBLE,					# ... 
    in radius int,					# ... and radius (in km)
    
    in publisherIds VARCHAR(1024),	# one or more comma separated publisher id's
    in funderIds VARCHAR(1024),		# one or more comma separated funder id's
    
    in itemType VARCHAR(15),		# query only for a specific type (book/chapter)
    in itemIds VARCHAR(1024)		# query only for specific titles (comma sep) since we limit to 1000 rows
)
BEGIN
	# Only year and month are available, so dates are stored as the first day of a month.
	# fromMonth converts any date to the first day of the supplied month. 
    declare fromMonth DATE;
    declare yearBefore DATE;
    declare meterRadius, crudeSquare int;
    
    set fromDate = ifnull(fromDate, curdate());
	set fromMonth = date_sub(fromDate, INTERVAL dayofmonth(fromDate)-1 DAY);
    set yearBefore = date_sub(fromMonth, INTERVAL 12 MONTH);
    set meterRadius = radius * 1000; # radius in meter
    # rough square area to limit exact searches 
    # (chosen large enough to account for longitude scaling towards the equator) 
    set crudeSquare = greatest(2, radius/25); 
    
	select 
		  id, isbn, title, publisher_name, doi, type
        , group_concat(distinct funders) as funders 
        #, nullif(countryCode,(select country from event where country_code=countryCode limit 1)) as country
		, date_format(fromMonth,"%Y-%m") as month
		, sum( case when date <= date_sub(fromMonth, INTERVAL 0 MONTH) and date >= date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else null end ) total
		, sum( case when date=date_sub(fromMonth, INTERVAL 0 MONTH) then mtot else 0 end ) month_0
		, sum( case when date=date_sub(fromMonth, INTERVAL 1 MONTH) then mtot else 0 end ) month_1
		, sum( case when date=date_sub(fromMonth, INTERVAL 2 MONTH) then mtot else 0 end ) month_2
		, sum( case when date=date_sub(fromMonth, INTERVAL 3 MONTH) then mtot else 0 end ) month_3
		, sum( case when date=date_sub(fromMonth, INTERVAL 4 MONTH) then mtot else 0 end ) month_4
		, sum( case when date=date_sub(fromMonth, INTERVAL 5 MONTH) then mtot else 0 end ) month_5
		, sum( case when date=date_sub(fromMonth, INTERVAL 6 MONTH) then mtot else 0 end ) month_6
		, sum( case when date=date_sub(fromMonth, INTERVAL 7 MONTH) then mtot else 0 end ) month_7
		, sum( case when date=date_sub(fromMonth, INTERVAL 8 MONTH) then mtot else 0 end ) month_8
		, sum( case when date=date_sub(fromMonth, INTERVAL 9 MONTH) then mtot else 0 end ) month_9
		, sum( case when date=date_sub(fromMonth, INTERVAL 10 MONTH) then mtot else 0 end ) month_10
		, sum( case when date=date_sub(fromMonth, INTERVAL 11 MONTH) then mtot else 0 end ) month_11
	from (
        
		select 
			date, item.id as id, isbn, title, publisher_name, doi, type,
            group_concat(distinct funder.name) as funders,  
			mtot 
		from (
			select item_id, date, sum(requests) as mtot
			from event 
            where 
				date > yearBefore and date <= fromMonth
				and abs(latitude-lat) < crudeSquare  # These 2 clauses prevent too far out of range locations 
				and	abs(longitude-lon) < crudeSquare # from being sent to (expensive) ST_Distance_Sphere
				and ST_Distance_Sphere( point(longitude,latitude), point(lon,lat) ) < meterRadius
			group by date, item_id
		) p
		join item on item.id = p.item_id 
        left join (
			item_funder inner join funder on item_funder.funder_id = funder.id    
		) on item_funder.item_id = item.id 

        where true 
			and if( itemIds is null, true, FIND_IN_SET(item.id, itemIds ) )
			and if( publisherIds is null, true, FIND_IN_SET(publisher_id, publisherIds) )
			and if( funderIds is null, true, FIND_IN_SET(funder_id, funderIds) )
            and if( itemType is null, true, item.type = itemType )
        
        group by date, item.id
        
	) tmp
	group by id
    having total is not null
	order by total desc
    limit 2500
    ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `events_all_data`
--

/*!50001 DROP VIEW IF EXISTS `events_all_data`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`trilobiet`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `events_all_data` AS select `event`.`ip` AS `ip`,`event`.`date` AS `date`,`event`.`country` AS `country`,`event`.`country_code` AS `country_code`,`event`.`city` AS `city`,`event`.`longitude` AS `longitude`,`event`.`latitude` AS `latitude`,`event`.`requests` AS `requests`,`event`.`ip_aton` AS `ip_aton`,`item`.`id` AS `item_id`,`item`.`title` AS `title`,`item`.`publisher_id` AS `publisher_id`,`item`.`publisher_name` AS `publisher_name`,`item`.`authors` AS `authors`,`item`.`doi` AS `doi`,`item`.`isbn` AS `isbn`,`item`.`type` AS `type`,`item`.`year` AS `year`,`item`.`grant_number` AS `grant_number`,`item`.`grant_program` AS `grant_program`,`funder`.`id` AS `funder_id`,`funder`.`name` AS `funder_name` from ((`event` join `item` on((`event`.`item_id` = `item`.`id`))) left join (`item_funder` join `funder` on((`item_funder`.`funder_id` = `funder`.`id`))) on((`item_funder`.`item_id` = `item`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `events_all_data_coarse_coords`
--

/*!50001 DROP VIEW IF EXISTS `events_all_data_coarse_coords`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`trilobiet`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `events_all_data_coarse_coords` AS select `event`.`ip` AS `ip`,`event`.`date` AS `date`,`event`.`country` AS `country`,`event`.`country_code` AS `country_code`,`event`.`city` AS `city`,round(`event`.`longitude`,1) AS `longitude`,round(`event`.`latitude`,1) AS `latitude`,`event`.`requests` AS `requests`,`event`.`ip_aton` AS `ip_aton`,`item`.`id` AS `item_id`,`item`.`title` AS `title`,`item`.`publisher_id` AS `publisher_id`,`item`.`publisher_name` AS `publisher_name`,`item`.`authors` AS `authors`,`item`.`doi` AS `doi`,`item`.`isbn` AS `isbn`,`item`.`type` AS `type`,`item`.`year` AS `year`,`item`.`grant_number` AS `grant_number`,`item`.`grant_program` AS `grant_program`,`funder`.`id` AS `funder_id`,`funder`.`name` AS `funder_name` from ((`event` join `item` on((`event`.`item_id` = `item`.`id`))) left join (`item_funder` join `funder` on((`item_funder`.`funder_id` = `funder`.`id`))) on((`item_funder`.`item_id` = `item`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `item_complete`
--

/*!50001 DROP VIEW IF EXISTS `item_complete`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`trilobiet`@`45.80.169.103` SQL SECURITY DEFINER */
/*!50001 VIEW `item_complete` AS select `item`.`id` AS `id`,`item`.`title` AS `title`,`item`.`publisher_name` AS `publisher_name`,`item`.`publisher_id` AS `publisher_id`,`item`.`authors` AS `authors`,`item`.`doi` AS `doi`,`item`.`isbn` AS `isbn`,`item`.`type` AS `type`,`item`.`year` AS `year`,`item`.`grant_number` AS `grant_number`,`item`.`grant_program` AS `grant_program`,`item`.`platform` AS `platform`,`item`.`irus_id` AS `irus_id`,`item`.`updated_at` AS `updated_at`,`item`.`created_at` AS `created_at`,group_concat(`item_funder`.`funder_id` separator ',') AS `funder_ids` from (`item` left join `item_funder` on((`item_funder`.`item_id` = `item`.`id`))) group by `item`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-13 22:45:07
