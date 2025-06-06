-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: javatosql
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL,
  `title` varchar(30) NOT NULL,
  `released` date NOT NULL,
  `genre` varchar(15) DEFAULT NULL,
  `earnings` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Fight Club','1999-10-15','Drama',100820947),(2,'Porco Rosso','1994-12-16','Animation',1450037),(3,'Oldboy','2003-11-21','Thriller',7968998),(4,'Mulholland Drive','2001-10-19','Drama',20779717),(5,'Face/Off','1997-06-27','Action',241199984),(6,'TRON: Legacy','2010-12-17','Action',399866199),(7,'Uncut Gems','2019-12-25','Thriller',50023780),(8,'The Life Aquatic','2004-12-25','Comedy',34806726),(9,'Blade Runner','1982-06-25','Thriller',39649582),(10,'Blade Runner 2049','2017-10-06','Drama',258157449),(11,'Titane','2021-10-01','Thriller',6554611),(12,'RRR','2022-03-24','Action',150511517),(13,'Kung Fu Yoga','2017-01-27','Comedy',257800000),(14,'Casino Royale','2006-11-17','Action',594420216),(15,'Her','2013-12-18','Romance',48038809),(16,'Kung Fu Hustle','2005-04-22','Comedy',102034104),(17,'Little Women','2019-12-25','Romance',218838386),(18,'White Noise','2022-11-25','Drama',79040),(19,'Adaptation.','2002-12-06','Drama',32531756),(20,'Airplane!','1980-07-02','Comedy',83454307),(21,'Wolf Children','2013-04-27','Animation',44092117),(22,'Pegasus','2019-02-05','Action',255863112),(23,'Ip Man','2010-10-01','Action',2211442),(24,'Hawa','2022-07-29','Drama',NULL),(25,'Barry Lyndon','1975-01-01','Drama',20221274),(26,'The Lighthouse','2019-10-18','Thriller',18237527),(27,'Shin Godzilla','2016-10-11','Action',77947607),(28,'Boyz n the Hood','1991-07-12','Drama',56215095),(30,'City of God','2002-08-30','Drama',30681033),(31,'Audition','2001-08-08','Drama',362963),(32,'Hundreds of Beavers','2022-09-29','Comedy',762871),(33,'Smoke Signals','1998-06-26','Drama',7756617),(34,'Footloose','1984-02-17','Romance',80009427),(35,'Vertigo','1958-05-28','Thriller',3296036),(36,'Swiss Army Man','2016-01-22','Comedy',5837111),(37,'Drive','2011-09-16','Thriller',81357930),(38,'Cast a Deadly Spell','1991-09-07','Comedy',NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-03  2:46:33
