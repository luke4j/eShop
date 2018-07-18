-- mysqldump -u root -proot -t eshop2 >d.sql


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
-- Dumping data for table `tu_com`
--

LOCK TABLES `tu_com` WRITE;
/*!40000 ALTER TABLE `tu_com` DISABLE KEYS */;
INSERT INTO `tu_com` VALUES (1,0,'2018-07-18 14:49:35.517000','北京大六环',1,'cc',0,'\0','\0','2018-07-18 14:49:35.517000','MyShop',10,2,'18613806246',NULL,20,10,'2018-07-18 14:49:35.517000',10,1,'_end');
/*!40000 ALTER TABLE `tu_com` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tu_fun`
--

LOCK TABLES `tu_fun` WRITE;
/*!40000 ALTER TABLE `tu_fun` DISABLE KEYS */;
INSERT INTO `tu_fun` VALUES (1,0,'2018-06-14 17:00:47.000000','Root',0,'','','公司','',''),(2,0,'2018-06-14 17:00:47.000000','Root',1,'','','公司业务','','app/user/com.view.js'),(3,0,'2018-06-14 17:00:47.000000','Root',1,'','','程序功能','','app/user/fun.view.js'),(4,0,'2018-06-14 17:00:47.000000','user',0,'','','用户管理','',''),(5,0,'2018-06-14 17:00:47.000000','user',4,'','','用户','','app/user/user.view.js'),(6,0,'2018-06-14 17:00:47.000000','user',4,'','','角色','','app/user/role.view.js'),(7,0,'2018-06-14 17:00:47.000000','user',4,'','','站点','','app/user/store.view.js'),(8,0,'2018-06-14 17:00:47.000000','goods',0,'','','商品信息','',''),(9,0,'2018-06-14 17:00:47.000000','goods',8,'','','商品属性','','app/goods/goodsExtAttr.view.js'),(10,0,'2018-06-14 17:00:47.000000','goods',8,'','','商品目录','','app/goods/goodsTree.view.js'),(11,0,'2018-06-14 17:00:47.000000','goods',8,'','','商品价格','','app/goods/goodsPrice.view.js'),(12,0,'2018-06-14 17:00:47.000000','goods',8,'','','站点价格','','app/goods/goodsStorePrice.view.js');
/*!40000 ALTER TABLE `tu_fun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tu_role`
--

LOCK TABLES `tu_role` WRITE;
/*!40000 ALTER TABLE `tu_role` DISABLE KEYS */;
INSERT INTO `tu_role` VALUES (1,0,'2018-07-18 14:49:58.036000','Root',1);
/*!40000 ALTER TABLE `tu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tu_role_fun`
--

LOCK TABLES `tu_role_fun` WRITE;
/*!40000 ALTER TABLE `tu_role_fun` DISABLE KEYS */;
INSERT INTO `tu_role_fun` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12);
/*!40000 ALTER TABLE `tu_role_fun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tu_store`
--

LOCK TABLES `tu_store` WRITE;
/*!40000 ALTER TABLE `tu_store` DISABLE KEYS */;
INSERT INTO `tu_store` VALUES (1,0,'2018-07-18 14:49:50.189000','北京shop-1',1,'\0','','\0','ShopStore-1','186138061',NULL,'ShopStore-1','ShopStore-1',1),(2,0,'2018-07-18 14:49:50.295000','北京shop-2',1,'\0','','\0','ShopStore-2','186138062',NULL,'ShopStore-2','ShopStore-2',1),(3,0,'2018-07-18 14:49:50.295000','北京shop-3',1,'\0','','\0','ShopStore-3','186138063',NULL,'ShopStore-3','ShopStore-3',1),(4,0,'2018-07-18 14:49:50.296000','北京shop-4',1,'\0','','\0','ShopStore-4','186138064',NULL,'ShopStore-4','ShopStore-4',1),(5,0,'2018-07-18 14:49:50.296000','北京shop-5',1,'\0','','\0','ShopStore-5','186138065',NULL,'ShopStore-5','ShopStore-5',1),(6,0,'2018-07-18 14:49:50.296000','北京shop-6',1,'\0','','\0','ShopStore-6','186138066',NULL,'ShopStore-6','ShopStore-6',1),(7,0,'2018-07-18 14:49:50.296000','北京shop-7',1,'\0','','\0','ShopStore-7','186138067',NULL,'ShopStore-7','ShopStore-7',1),(8,0,'2018-07-18 14:49:50.296000','北京shop-8',1,'\0','','\0','ShopStore-8','186138068',NULL,'ShopStore-8','ShopStore-8',1),(9,0,'2018-07-18 14:49:50.296000','北京shop-9',1,'\0','','\0','ShopStore-9','186138069',NULL,'ShopStore-9','ShopStore-9',1),(10,0,'2018-07-18 14:49:50.296000','北京shop-10',1,'\0','','\0','ShopStore-10','1861380610',NULL,'ShopStore-10','ShopStore-10',1),(11,0,'2018-07-18 14:49:50.296000','北京shop-11',1,'\0','','\0','ShopStore-11','1861380611',NULL,'ShopStore-11','ShopStore-11',1),(12,0,'2018-07-18 14:49:50.296000','北京shop-12',1,'\0','','\0','ShopStore-12','1861380612',NULL,'ShopStore-12','ShopStore-12',1),(13,0,'2018-07-18 14:49:50.296000','北京shop-13',1,'\0','','\0','ShopStore-13','1861380613',NULL,'ShopStore-13','ShopStore-13',1),(14,0,'2018-07-18 14:49:50.296000','北京shop-14',1,'\0','','\0','ShopStore-14','1861380614',NULL,'ShopStore-14','ShopStore-14',1),(15,0,'2018-07-18 14:49:50.296000','北京shop-15',1,'\0','','\0','ShopStore-15','1861380615',NULL,'ShopStore-15','ShopStore-15',1),(16,0,'2018-07-18 14:49:50.296000','北京shop-16',1,'\0','','\0','ShopStore-16','1861380616',NULL,'ShopStore-16','ShopStore-16',1),(17,0,'2018-07-18 14:49:50.296000','北京shop-17',1,'\0','','\0','ShopStore-17','1861380617',NULL,'ShopStore-17','ShopStore-17',1),(18,0,'2018-07-18 14:49:50.296000','北京shop-18',1,'\0','','\0','ShopStore-18','1861380618',NULL,'ShopStore-18','ShopStore-18',1),(19,0,'2018-07-18 14:49:50.296000','北京shop-19',1,'\0','','\0','ShopStore-19','1861380619',NULL,'ShopStore-19','ShopStore-19',1),(20,0,'2018-07-18 14:49:50.296000','北京shop-20',1,'\0','','\0','ShopStore-20','1861380620',NULL,'ShopStore-20','ShopStore-20',1);
/*!40000 ALTER TABLE `tu_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tu_user`
--

LOCK TABLES `tu_user` WRITE;
/*!40000 ALTER TABLE `tu_user` DISABLE KEYS */;
INSERT INTO `tu_user` VALUES (1,0,'2018-07-18 14:50:08.813000','北京','2001-01-01 00:00:00.000000','cc','cc','E0323A9039ADD2978BF5B49550572C7C','18613806246',NULL,'normal','小学','CEO',1,1,1);
/*!40000 ALTER TABLE `tu_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-18 14:59:44
