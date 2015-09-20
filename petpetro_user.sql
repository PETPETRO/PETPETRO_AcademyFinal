-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: petpetro
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `registration_date` datetime NOT NULL,
  `login_count` int(10) unsigned NOT NULL,
  `last_login` datetime NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `manager_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'freiova','aA1@aa','freiova@mail.com','Lucia','Freiova','2015-09-20 21:28:58',0,'2015-09-20 21:28:58',0,0),(2,'krizov','aA1@aa','krizovensky@mail.com','Peter','Krizovensky','2015-09-20 21:30:34',0,'2015-09-20 21:30:34',0,1),(3,'barcansky','aA1@aa','barcansky@mail.com','Martin','Barcansky','2015-09-20 21:32:17',0,'2015-09-20 21:32:17',0,1),(4,'kovacik','aA1@aa','kovacik@mail.com','Michal','Kovacik','2015-09-20 21:33:16',0,'2015-09-20 21:33:16',0,1),(5,'plavka','aA1@aa','plavka@mail.com','Janko','Plavka','2015-09-20 21:34:11',0,'2015-09-20 21:34:11',0,3),(6,'hrasko','aA1@aa','hrasko@mail.com','Janko','Hrasko','2015-09-20 21:35:56',0,'2015-09-20 21:35:56',0,4),(7,'klingacik','aA1@aa','klingacik@mail.com','Martinko','Klingacik','2015-09-20 21:39:08',0,'2015-09-20 21:39:08',0,4),(8,'hraskova','aA1@aa','hraskova@mail.com','Marienka','Hraskova','2015-09-20 21:39:55',0,'2015-09-20 21:39:55',0,5),(9,'straka','aA1@aa','straka@mail.com','Pavol','Straka','2015-09-20 21:41:17',0,'2015-09-20 21:41:17',0,5),(10,'novak','aA1@aa','novak@mail.com','Jan','Novak','2015-09-20 21:42:08',0,'2015-09-20 21:42:08',0,5),(11,'jaga','aA1@aa','jaga@mail.com','Baba','Jaga','2015-09-20 21:42:55',0,'2015-09-20 21:42:55',0,10);
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

-- Dump completed on 2015-09-20 23:07:18
