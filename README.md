-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sales_lp1
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `detailsale`
--

DROP TABLE IF EXISTS `detailsale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detailsale` (
  `idDetailSale` int NOT NULL AUTO_INCREMENT,
  `idsale` int NOT NULL,
  `idproduct` int NOT NULL,
  `amount` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`idDetailSale`),
  KEY `fk_detailSale_sale1_idx` (`idsale`),
  KEY `fk_detailSale_product1_idx` (`idproduct`),
  CONSTRAINT `fk_detailSale_product1` FOREIGN KEY (`idproduct`) REFERENCES `product` (`idproduct`),
  CONSTRAINT `fk_detailSale_sale1` FOREIGN KEY (`idsale`) REFERENCES `sale` (`idsale`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailsale`
--

LOCK TABLES `detailsale` WRITE;
/*!40000 ALTER TABLE `detailsale` DISABLE KEYS */;
INSERT INTO `detailsale` VALUES (16,15,6,3,15.5);
/*!40000 ALTER TABLE `detailsale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `idperson` int NOT NULL AUTO_INCREMENT,
  `num_document` varchar(45) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `idTypePerson` int NOT NULL,
  PRIMARY KEY (`idperson`),
  KEY `fk_person_typePerson_idx` (`idTypePerson`),
  CONSTRAINT `fk_person_typePerson` FOREIGN KEY (`idTypePerson`) REFERENCES `typeperson` (`idTypePerson`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (16,'46398275','Ulvia Vargas Salas','Jr. 8 de diciembre S/N','961267470',NULL,NULL,'1',2),(17,'91150709','Jarly',NULL,NULL,'jarly@gmail.com','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1',1),(18,'73472336','Jhan Arly',NULL,NULL,'jhan@gmail.com','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','1',1);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idproduct` int NOT NULL AUTO_INCREMENT,
  `name_product` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stocks` int DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (6,'Granola',15.5,36,'1');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `idsale` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NOT NULL,
  `serial_number` varchar(45) DEFAULT NULL,
  `sale_date` date DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `status` int DEFAULT NULL,
  `idUser` int DEFAULT NULL,
  PRIMARY KEY (`idsale`),
  KEY `fk_sale_person1_idx` (`idCustomer`),
  CONSTRAINT `fk_sale_person1` FOREIGN KEY (`idCustomer`) REFERENCES `person` (`idperson`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (15,16,'00000001','2023-01-01',46.5,1,1);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeperson`
--

DROP TABLE IF EXISTS `typeperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeperson` (
  `idTypePerson` int NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idTypePerson`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeperson`
--

LOCK TABLES `typeperson` WRITE;
/*!40000 ALTER TABLE `typeperson` DISABLE KEYS */;
INSERT INTO `typeperson` VALUES (1,'Empleado','1'),(2,'Cliente','1');
/*!40000 ALTER TABLE `typeperson` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-21  7:50:44
