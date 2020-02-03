CREATE DATABASE  IF NOT EXISTS `podcast_bd` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `podcast_bd`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: dbalumnos.sanclemente.local    Database: podcast_bd
-- ------------------------------------------------------
-- Server version	5.5.59-0+deb8u1-log

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
-- Table structure for table `Autor`
--

DROP TABLE IF EXISTS `Autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Autor` (
  `idAutor` int(11) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAutor`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Autor`
--

LOCK TABLES `Autor` WRITE;
/*!40000 ALTER TABLE `Autor` DISABLE KEYS */;
INSERT INTO `Autor` VALUES (1,'43454674T','Ochoa Martinez','Marcos'),(2,'5467893W','Airas Pavón','Jesus');
/*!40000 ALTER TABLE `Autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Generos`
--

DROP TABLE IF EXISTS `Generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Generos` (
  `idGeneros` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idGeneros`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Generos`
--

LOCK TABLES `Generos` WRITE;
/*!40000 ALTER TABLE `Generos` DISABLE KEYS */;
INSERT INTO `Generos` VALUES (1,'Arte'),(2,'Videojuegos'),(3,'Política'),(4,'Corazón'),(5,'Música'),(6,'Ficción');
/*!40000 ALTER TABLE `Generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Podcast`
--

DROP TABLE IF EXISTS `Podcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Podcast` (
  `idPodcast` int(11) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `calidad` varchar(45) DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL,
  `periocidad` varchar(45) DEFAULT NULL,
  `formato_video` varchar(45) DEFAULT NULL,
  `autor` int(11) NOT NULL,
  PRIMARY KEY (`idPodcast`),
  KEY `fk_autor_podcat_idx` (`autor`),
  CONSTRAINT `fk_autor_podcat` FOREIGN KEY (`autor`) REFERENCES `Autor` (`idAutor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Podcast`
--

LOCK TABLES `Podcast` WRITE;
/*!40000 ALTER TABLE `Podcast` DISABLE KEYS */;
INSERT INTO `Podcast` VALUES (1,' El podcast más corto de la historia',0,'320 Kbps',60,'semanal',NULL,1),(2,'La politica del futuro',1,NULL,70,'mensual','mp4',2);
/*!40000 ALTER TABLE `Podcast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_pod`
--

DROP TABLE IF EXISTS `gen_pod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_pod` (
  `idPodcat` int(11) NOT NULL,
  `idGenero` int(11) NOT NULL,
  PRIMARY KEY (`idPodcat`,`idGenero`),
  KEY `fk_gen_idx` (`idGenero`),
  CONSTRAINT `fk_pod` FOREIGN KEY (`idPodcat`) REFERENCES `Podcast` (`idPodcast`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gen` FOREIGN KEY (`idGenero`) REFERENCES `Generos` (`idGeneros`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_pod`
--

LOCK TABLES `gen_pod` WRITE;
/*!40000 ALTER TABLE `gen_pod` DISABLE KEYS */;
INSERT INTO `gen_pod` VALUES (2,1),(2,3),(1,5),(1,6);
/*!40000 ALTER TABLE `gen_pod` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-14 19:23:43
