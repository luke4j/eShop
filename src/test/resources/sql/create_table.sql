-- mysqldump -u root -proot -d eshop2 >t.sql






-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: eshop2
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `tu_com`
--

DROP TABLE IF EXISTS `tu_com`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tu_com` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_isDel` tinyint(1) NOT NULL DEFAULT '0',
  `b_wtime` datetime(6) NOT NULL,
  `addr` varchar(200) NOT NULL,
  `adminId` bigint(20) DEFAULT NULL,
  `adminPassword` varchar(36) DEFAULT NULL,
  `cz` bigint(20) DEFAULT NULL,
  `isJy` bit(1) DEFAULT NULL,
  `isYueJie` bit(1) DEFAULT NULL,
  `maxUseDay` datetime(6) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `padNum` int(11) DEFAULT NULL,
  `padPrice` int(11) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `servicePrice` int(11) DEFAULT NULL,
  `storeNum` int(11) DEFAULT NULL,
  `useDate` datetime(6) DEFAULT NULL,
  `webNum` int(11) DEFAULT NULL,
  `webPrice` int(11) DEFAULT NULL,
  `yueJieRQ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iyyiyf1bhnq96o15r5fb76img` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tu_fun`
--

DROP TABLE IF EXISTS `tu_fun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tu_fun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_isDel` tinyint(1) NOT NULL DEFAULT '0',
  `b_wtime` datetime(6) NOT NULL,
  `c_group` varchar(18) NOT NULL,
  `fid` bigint(20) NOT NULL,
  `htmlPath` varchar(255) DEFAULT NULL,
  `iconPath` varchar(255) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `studyPath` varchar(200) DEFAULT NULL,
  `viewPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tu_role`
--

DROP TABLE IF EXISTS `tu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_isDel` tinyint(1) NOT NULL DEFAULT '0',
  `b_wtime` datetime(6) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `comId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlp4fhosl7ic83n32t1ev3tiwx` (`comId`),
  CONSTRAINT `FKlp4fhosl7ic83n32t1ev3tiwx` FOREIGN KEY (`comId`) REFERENCES `tu_com` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tu_role_fun`
--

DROP TABLE IF EXISTS `tu_role_fun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tu_role_fun` (
  `roleId` bigint(20) NOT NULL,
  `funId` bigint(20) NOT NULL,
  KEY `FKhwoxp81brunus78kyjuvr1skn` (`funId`),
  KEY `FKbedjt3kl2jnmg56bqxqnjnuyt` (`roleId`),
  CONSTRAINT `FKbedjt3kl2jnmg56bqxqnjnuyt` FOREIGN KEY (`roleId`) REFERENCES `tu_role` (`id`),
  CONSTRAINT `FKhwoxp81brunus78kyjuvr1skn` FOREIGN KEY (`funId`) REFERENCES `tu_fun` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tu_store`
--

DROP TABLE IF EXISTS `tu_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tu_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_isDel` tinyint(1) NOT NULL DEFAULT '0',
  `b_wtime` datetime(6) NOT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `fid` bigint(20) DEFAULT NULL,
  `isCenter` bit(1) DEFAULT NULL,
  `isZhanDianJia` bit(1) DEFAULT NULL,
  `isZhiYing` bit(1) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `phote` varchar(100) DEFAULT NULL,
  `pinYin` varchar(90) NOT NULL,
  `py` varchar(15) NOT NULL,
  `comId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjr4r20p5g24jjoftl0s8hm5s5` (`comId`),
  CONSTRAINT `FKjr4r20p5g24jjoftl0s8hm5s5` FOREIGN KEY (`comId`) REFERENCES `tu_com` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tu_user`
--

DROP TABLE IF EXISTS `tu_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tu_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_isDel` tinyint(1) NOT NULL DEFAULT '0',
  `b_wtime` datetime(6) NOT NULL,
  `addr` varchar(120) DEFAULT NULL,
  `brithday` datetime(6) DEFAULT NULL,
  `loginName` varchar(40) NOT NULL,
  `name` varchar(80) NOT NULL,
  `password` varchar(36) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `photo` varchar(120) DEFAULT NULL,
  `userType` varchar(12) NOT NULL,
  `xue_li` varchar(20) DEFAULT NULL,
  `zhi_wu` varchar(20) DEFAULT NULL,
  `comId` bigint(20) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `storeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oghx8qukywemjm4825aao05w2` (`loginName`),
  UNIQUE KEY `idx_user_loginName` (`loginName`),
  KEY `FKsve9bh9rxy8h4seqjkdlcu4v` (`comId`),
  KEY `FKc3gikf36qaa916d1262vgko7a` (`roleId`),
  KEY `FK55fnp89bsew4dycdj77cvtxxs` (`storeId`),
  CONSTRAINT `FK55fnp89bsew4dycdj77cvtxxs` FOREIGN KEY (`storeId`) REFERENCES `tu_store` (`id`),
  CONSTRAINT `FKc3gikf36qaa916d1262vgko7a` FOREIGN KEY (`roleId`) REFERENCES `tu_role` (`id`),
  CONSTRAINT `FKsve9bh9rxy8h4seqjkdlcu4v` FOREIGN KEY (`comId`) REFERENCES `tu_com` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-18 14:56:48
