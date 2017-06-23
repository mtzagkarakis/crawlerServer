-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: crawler
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
  `sources` int(11) NOT NULL,
  `elapsed_time` int(11) NOT NULL COMMENT 'seconds',
  `merged_products_found` int(11) NOT NULL,
  `total_products_found` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `source_order` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_url` (`category_url`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'e-shop','http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1','iso-8859-7','a.mobile_list_navigation_link','offset',10,'a.web-title-link','[itemprop=\'name\']','[itemprop=\'price\']','.','[itemprop=\'manufacturer\']','td.web-main-photo-box a','#mobile_desc table  tr td.details2','#mobile_desc table  tr td.details1',0,1),(2,'plaisio.gr','http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm','utf-8','#ctl00_BaseLayoutContentArea_pagingBottom_divPager a','page',1,'div.productTopWrap div.productDetailsWrap div.productTitleDescrWrap div.productTitle a','span.topAreaProductTitle','span.productPrice',',','','#productImagesLinksLarge a','li.characteristicsItem span.characteristicLabel','li.characteristicsItem span.characteristicValue',1,1),(4,'tokinito.gr','https://tokinito.gr/mobile-phones-tablets-accessories/mobiles.html','utf-8','.pages ol li a','p',1,'#products-list .item-image a','h2.product-name','.regular-price .price',',','#product-attribute-specs-table-1 ','#yt_cloudzoom','.box-additional table.data-table tr th','.box-additional table.data-table tr td.data.last',0,1),(5,'kaizershop.gr','https://www.kaizershop.gr/cell-phones-smartphones','utf-8','ul.pagination li a','page',1,'#products .product-block a.img','h1.name','.special-price',',','inner-product-images img','#imageGallery li img','#technical-section .row.table-row .row div:first-child','#technical-section .row.table-row .row div:last-child',0,1),(6,'open365.gr','http://www.open365.gr/kinita-thlephona/','utf-8','','',1,'#category_products_19 .title-price-wrapper a','h1.mainbox-title','.price',',','#content_features > .control-group .feature-value','.image-wrap img','#content_features .control-group label','#content_features .control-group .feature-value',0,2),(7,'electronet.gr','https://www.electronet.gr/tilefonia/smartphones','utf-8','ul.pager li a','page',1,'.item-product .product-image a','h1[itemprop=\'name\']','[itemprop=\'price\']','.','','.gallery-slides ul li:first-child a img','.field-spec-properties table tr td:first-child','.field-spec-properties table tr td:last-child',0,0),(8,'you.gr','https://www.you.gr/telephony-networking/kinita-tilefona-axesouar/kinita-tilefona','utf-8','div.pagination li a','page',1,'.product-list .description-container h2.title a','h1[itemprop=\'name\']','[itemprop=\'price\']',',','','[itemprop=\'image\']','ul.col-lg-20.no-gutter li div.title-container span.title','ul.col-lg-20.no-gutter li div.title-container span.value',1,0),(11,'playmobiles.gr','http://www.playmobiles.gr/index.php?route=product/category&path=59','utf-8','','',1,'.product-list .name a','.product-info .description .heading','.product-info .price .price-tax',',','','.product-info img#image','','',0,0),(23,'safesales.gr','http://safesales.gr/index.php?cPath=69&sort=2a&ppp=9999','utf-8','','',1,'.prodList .pl.product-listing .pl-name a:not(.cat-name)','.product-holder .prod-details h1:first','.product-holder .price-holder .productprice:first','.','#tab2 table tr td.charact_tab_lines2','.product-holder img.container','#tab2 table tr td.charact_tab_lines1','#tab2 table tr td.charact_tab_lines2',0,0),(15,'omgadget.gr','http://www.omgadget.gr/-','utf-8','.pages ol li a','p',1,'.category-products li.item h2.product-name a','.product-name h1.medium','.price',',','#product-attribute-specs-table tr:first-child td.data','#image','#product-attribute-specs-table tr th','#product-attribute-specs-table tr td.data',0,2),(16,'e-tzampa.gr','https://www.e-tzampa.gr/50-mobile-phones','utf-8','ul.pagination li a','p',1,'a.product-name','h1[itemprop=\'name\']','[itemprop=\'price\']',',','','[itemprop=\'image\']','#product-features-tab-content table tr td:first-child','#product-features-tab-content table tr td:last-child',0,0),(17,'apothikiproios.gr','https://apothikiproios.gr/48-mobile-phones','utf-8','ul.pagination li a','p',1,'.product-container h5.product-name-container a','h1[itemprop=\'name\']','[itemprop=\'price\']',',','','[itemprop=\'image\']','.table-data-sheet tr td:first-child','.table-data-sheet tr td:last-child',0,0),(19,'sevenspot.gr','https://www.sevenspot.gr/smartphones-5000342','utf-8','ul.pagination li a','p',1,'section.product-info a.product-name','h1[itemprop=\'name\']','[itemprop=\'price\']',',','.table-data-sheet tr:first-child td:last-child','[itemprop=\'image\']','.table-data-sheet tr td:first-child','.table-data-sheet tr td:last-child',0,0),(20,'top-store.gr','https://top-store.gr/katigories/kinita-tilefona/?paged=&count=9999','utf-8','','',1,'ul.products .product-details a.product-loop-title','h1[itemprop=\'name\']','.product-essential .price',',','.shop_attributes tr:first-child td','.product-essential img','.shop_attributes tr th','.shop_attributes tr td',0,0),(21,'multitec.gr','http://www.multitec.gr/68-cellphones.html','utf-8','ul.pagination li a','p',1,'#content .product-meta .product-title h5 a.product-name','h1[itemprop=\'name\']','.primary_block .price #our_price_display',',','.primary_block #manufacturer_custom a span','img#bigpic','.table-data-sheet tr td:first-child','.table-data-sheet tr td:last-child',0,0);
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

-- Dump completed on 2017-06-23 23:47:39
