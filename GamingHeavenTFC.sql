-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: gamingheaventfc
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE IF EXISTS `GamingHeavenTFC`;
CREATE DATABASE `GamingHeavenTFC`;
USE `GamingHeavenTFC`;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentarios` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `texto` varchar(500) DEFAULT NULL,
  `fk_user_correo` varchar(320) NOT NULL,
  `fk_juego_id_api` smallint(5) unsigned NOT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comentarios_usuario_FK` (`fk_user_correo`),
  KEY `comentarios_juego_FK` (`fk_juego_id_api`),
  CONSTRAINT `comentarios_juego_FK` FOREIGN KEY (`fk_juego_id_api`) REFERENCES `juego` (`id_api`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comentarios_usuario_FK` FOREIGN KEY (`fk_user_correo`) REFERENCES `usuario` (`correo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fav`
--

DROP TABLE IF EXISTS `fav`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fav` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_user_correo` varchar(320) NOT NULL,
  `fk_juego_id_api` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fav_juego_FK` (`fk_juego_id_api`),
  KEY `fav_usuario_FK` (`fk_user_correo`),
  CONSTRAINT `fav_juego_FK` FOREIGN KEY (`fk_juego_id_api`) REFERENCES `juego` (`id_api`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fav_usuario_FK` FOREIGN KEY (`fk_user_correo`) REFERENCES `usuario` (`correo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fav`
--

LOCK TABLES `fav` WRITE;
/*!40000 ALTER TABLE `fav` DISABLE KEYS */;
/*!40000 ALTER TABLE `fav` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juego`
--

DROP TABLE IF EXISTS `juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juego` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_api` smallint(5) unsigned NOT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `juego_unique` (`id_api`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juego`
--

LOCK TABLES `juego` WRITE;
/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
/*!40000 ALTER TABLE `juego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_user_correo` varchar(320) NOT NULL,
  `fk_juego_id_api` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `like_juego_FK` (`fk_juego_id_api`),
  KEY `like_usuario_FK` (`fk_user_correo`),
  CONSTRAINT `like_juego_FK` FOREIGN KEY (`fk_juego_id_api`) REFERENCES `juego` (`id_api`) ON DELETE CASCADE,
  CONSTRAINT `like_usuario_FK` FOREIGN KEY (`fk_user_correo`) REFERENCES `usuario` (`correo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `correo` varchar(320) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `img` mediumblob DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_unique` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gamingheaventfc'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-29 14:15:09
