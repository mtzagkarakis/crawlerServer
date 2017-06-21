-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: crawler
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `cache`
--

DROP TABLE IF EXISTS `cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `query` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `json` longtext COLLATE utf8_unicode_ci NOT NULL,
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cache`
--

LOCK TABLES `cache` WRITE;
/*!40000 ALTER TABLE `cache` DISABLE KEYS */;
/*!40000 ALTER TABLE `cache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `category_url` varchar(330) COLLATE utf8_unicode_ci NOT NULL,
  `encoding` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'iso-8859-7',
  `pagination_selector` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `pagination_param` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `pagination_step` int(11) NOT NULL,
  `product_url` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `product_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `product_price` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `product_price_decimal` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `product_manufacturer` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `product_image` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `key_selector` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `value_selector` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `is_active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_url` (`category_url`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'e-shop','http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1','iso-8859-7','a.mobile_list_navigation_link','offset',10,'a.web-title-link','[itemprop=\'name\']','[itemprop=\'price\']','.','[itemprop=\'manufacturer\']','td.web-main-photo-box a','#mobile_desc table  tr td.details2','#mobile_desc table  tr td.details1',0),(2,'plaisio.gr','http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm','utf-8','#ctl00_BaseLayoutContentArea_pagingBottom_divPager a','page',1,'div.productTopWrap div.productDetailsWrap div.productTitleDescrWrap div.productTitle a','span.topAreaProductTitle','span.productPrice',',','','#productImagesLinksLarge a','li.characteristicsItem span.characteristicLabel','li.characteristicsItem span.characteristicValue',0),(4,'tokinito.gr','https://tokinito.gr/mobile-phones-tablets-accessories/mobiles.html','utf-8','.pages ol li a','p',1,'#products-list .item-image a','h2.product-name','.regular-price .price',',','','#yt_cloudzoom','.box-additional table.data-table tr th','.box-additional table.data-table tr td.data',0),(5,'kaizershop.gr','https://www.kaizershop.gr/cell-phones-smartphones','utf-8','ul.pagination li a','page',1,'#products .product-block a.img','h1.name','.special-price',',','inner-product-images img','#imageGallery li img','#technical-section .row.table-row .row div:first-child','#technical-section .row.table-row .row div:last-child',1);
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-21 15:30:18
