-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2017 at 05:30 PM
-- Server version: 5.7.11
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `meta_configuration`
--

-- --------------------------------------------------------

--
-- Table structure for table `cache`
--

CREATE TABLE `cache` (
  `id` int(11) NOT NULL,
  `query` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `json` longtext COLLATE utf8_unicode_ci NOT NULL,
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sources` int(11) NOT NULL,
  `elapsed_time` int(11) NOT NULL COMMENT 'seconds',
  `merged_products_found` int(11) NOT NULL,
  `total_products_found` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cache`
--

INSERT INTO `cache` (`id`, `query`, `json`, `insert_date`, `sources`, `elapsed_time`, `merged_products_found`, `total_products_found`) VALUES
(25, 'searchstring=&screensize-from=3&screensize-to=11&ram-from=1&ram-to=16&camera-from=2&camera-to=24&storage-from=2&storage-to=128&weight-from=50&weight-to=350&battery-from=1000&battery-to=6000&price-from=&price-to=&company=&network=&screenresolution=', '{ "status": 200, "total": 7, "data": [ { "productSet": [ { "name": "apple iphone 6 32gb space grey eu", "price": 400.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "ios", "batteryInMamp": -1, "manufacturer": "apple", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/apple-iphone-6-32gb-space-grey-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/147/detailed/14/iphone6p-gray-.jpg", "name": "Apple Iphone 6 32GB Space Grey EU", "manufacturer": "Apple", "price": "400.90", "attributes": { "Κατασκευαστής": "Apple", "Λειτουργικό Σύστημα": "iOS", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "8.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "4,7 ίντσες", "Εγγύηση": "1 Έτος κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Ανθρακί", "Μπαταρία": "Ναι" } } } ] }, { "productSet": [ { "name": "huawei p10 64gb graphite dazzling blue eu", "price": 439.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p10-64gb-graphite-dazzling-blue-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/280/detailed/14/huawei_p10_plus_64gb_dual_sim_dazzling_blue_3.jpg", "name": "Huawei P10 64GB Graphite Dazzling Blue EU", "manufacturer": "Huawei", "price": "439.90", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5.1 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μπλέ", "Μπαταρία": "Ναι" } } }, { "name": "huawei p10 64gb graphite black operator eu", "price": 427.0, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p10-64gb-graphite-black-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/280/detailed/14/huawei-p10-64gb-graphite-black.jpg", "name": "Huawei P10 64GB Graphite Black Operator EU", "manufacturer": "Huawei", "price": "427.00", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5.1 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } } ] }, { "productSet": [ { "name": "huawei p9 lite 16gb ram 3gb single sim white eu", "price": 199.0, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p9-lite-16gb-ram-3gb-single-sim-white-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/210/detailed/13/huawei_p9_lite_front.jpg", "name": "Huawei P9 Lite 16GB RAM 3GB SINGLE Sim White EU", "manufacturer": "Huawei", "price": "199.00", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "13.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,2 ιντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Λευκό", "Μπαταρία": "Ναι" } } }, { "name": "huawei p9 lite 16gb ram 3gb single sim black operator eu", "price": 199.0, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p9-lite-16gb-ram-3gb-single-sim-black-eu-unbranded.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/280/detailed/13/huawei_p9_lite_16gb.jpeg", "name": "Huawei P9 Lite 16GB RAM 3GB SINGLE Sim Black Operator EU", "manufacturer": "Huawei", "price": "199.00", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "13.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,2 ιντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } }, { "name": "huawei p10 lite 32gb ram 4gb single sim white operator eu", "price": 303.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p10-lite-32gb-single-sim-white-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/210/detailed/13/huawei-p10-lite-white.png", "name": "Huawei P10 Lite 32GB Ram 4GB Single Sim White Operator EU", "manufacturer": "Huawei", "price": "303.90", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,2 ιντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Λευκό", "Μπαταρία": "Ναι" } } }, { "name": "huawei p10 lite 32gb ram 4gb single sim black operator eu", "price": 303.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p10-lite-32gb-single-sim-black-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/132/detailed/13/huawei_p10_lite_black_01.jpg", "name": "Huawei P10 Lite 32GB Ram 4GB Single Sim Black Operator EU", "manufacturer": "Huawei", "price": "303.90", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,2 ιντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } } ] }, { "productSet": [ { "name": "huawei p8 lite 2017 16gb single sim black operator eu", "price": 196.0, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p8-lite-2017-16gb-black-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/243/detailed/13/huawei-p8-lite_2017_black.jpg", "name": "Huawei P8 Lite 2017 16GB Single Sim Black Operator EU", "manufacturer": "Huawei", "price": "196.00", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5.2 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } } ] }, { "productSet": [ { "name": "huawei p9 lite 2017 16gb dual sim black eu", "price": 196.0, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p9-lite-2017-16gb-dual-sim-black-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/280/detailed/14/Huawei-P9-lite-1.jpg", "name": "Huawei P9 Lite 2017 16GB Dual Sim Black EU", "manufacturer": "Huawei", "price": "196.00", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Ναι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5.2 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } }, { "name": "huawei p9 lite 2017 16gb dual sim white eu", "price": 196.0, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "huawei", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/huawei-p9-lite-2017-16gb-dual-sim-white-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/246/detailed/14/P9_LITE_2017.jpg", "name": "Huawei P9 Lite 2017 16GB Dual Sim White EU", "manufacturer": "Huawei", "price": "196.00", "attributes": { "Κατασκευαστής": "Huawei", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Ναι", "Φωτ. Μηχανή": "12.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5.2 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Λευκό", "Μπαταρία": "Ναι" } } } ] }, { "productSet": [ { "name": "samsung j320fn galaxy j3 (2016) white operator eu", "price": 105.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "samsung", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/samsung-j320fn-galaxy-j3-2016-white-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/280/detailed/13/J320_WHITE_p0pg1tg3_codm-tm.jpg", "name": "Samsung J320FN Galaxy J3 (2016) White Operator EU", "manufacturer": "Samsung", "price": "105.90", "attributes": { "Κατασκευαστής": "Samsung", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "8.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,0 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Λευκό", "Μπαταρία": "Ναι" } } }, { "name": "samsung j320fn galaxy j3 (2016) black operator eu", "price": 105.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "samsung", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/samsung-j320fn-galaxy-j3-2016-black-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/210/detailed/12/J320FN_Galaxy_J3_(2016)_Black.jpg", "name": "Samsung J320FN Galaxy J3 (2016) Black Operator EU", "manufacturer": "Samsung", "price": "105.90", "attributes": { "Κατασκευαστής": "Samsung", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "8.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,0 ίντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } } ] }, { "productSet": [ { "name": "samsung j710f galaxy j7(2016) lte 16gb white operator eu", "price": 179.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "samsung", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/samsung-j710f-galaxy-j7-2016-lte-16gb-white-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/326/detailed/12/J710_white.jpeg", "name": "Samsung J710F Galaxy J7(2016) LTE 16GB White Operator EU", "manufacturer": "Samsung", "price": "179.90", "attributes": { "Κατασκευαστής": "Samsung", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "13.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,5 ιντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Λευκό", "Μπαταρία": "Ναι" } } }, { "name": "samsung j710fn galaxy j7(2016) lte 16gb black operator eu", "price": 175.9, "screenSize": -1.0, "ram": -1.0, "screenResolution": "", "storageInGB": -1.0, "cameraResolutionInMP": -1.0, "operatingSystem": "android", "batteryInMamp": -1, "manufacturer": "samsung", "network": "", "weight": -1, "product": { "url": "http://www.open365.gr/samsung-j710f-galaxy-j7-2016-lte-16gb-black-eu.html", "imageUrl": "http://www.open365.gr/images/thumbnails/280/266/detailed/12/j710_black.jpg", "name": "Samsung J710FN Galaxy J7(2016) LTE 16GB Black Operator EU", "manufacturer": "Samsung", "price": "175.90", "attributes": { "Κατασκευαστής": "Samsung", "Λειτουργικό Σύστημα": "Google Android", "Dual Sim": "Όχι", "Φωτ. Μηχανή": "13.0 Mp", "Χώρα Προέλευσης": "Ευρωπαϊκής Αντιπροσωπείας", "Καλώδιο USB": "Ναι", "Μέγεθος Οθόνης": "5,5 ιντσες", "Εγγύηση": "2 Έτη κατασκευαστή", "Στερεοφωνικά ακουστικά": "Ναι", "Φορτιστής ρεύματος": "Ναι", "Μενού ": "Ελληνικό", "Χρώμα": "Μαύρο", "Μπαταρία": "Ναι" } } } ] } ]\n}', '2017-06-23 17:30:12', 1, 39, 7, 14);

-- --------------------------------------------------------

--
-- Table structure for table `resources`
--

CREATE TABLE `resources` (
  `id` int(11) NOT NULL,
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
  `source_order` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` (`id`, `name`, `category_url`, `encoding`, `pagination_selector`, `pagination_param`, `pagination_step`, `product_url`, `product_name`, `product_price`, `product_price_decimal`, `product_manufacturer`, `product_image`, `key_selector`, `value_selector`, `is_active`, `source_order`) VALUES
(1, 'e-shop', 'http://www.e-shop.gr/tilepikoinonies-kinita-smartphones-android-apple-blackberry-windows-list?table=TEL&category=%CA%C9%CD%C7%D4%CF+%D4%C7%CB%C5%D6%D9%CD%CF&filter-3619=1&filter-3618=1&filter-3621=1&filter-3617=1', 'iso-8859-7', 'a.mobile_list_navigation_link', 'offset', 10, 'a.web-title-link', '[itemprop=\'name\']', '[itemprop=\'price\']', '.', '[itemprop=\'manufacturer\']', 'td.web-main-photo-box a', '#mobile_desc table  tr td.details2', '#mobile_desc table  tr td.details1', 0, 1),
(2, 'plaisio.gr', 'http://www.plaisio.gr/tilefona/kinita-smartphones/smartphones.htm', 'utf-8', '#ctl00_BaseLayoutContentArea_pagingBottom_divPager a', 'page', 1, 'div.productTopWrap div.productDetailsWrap div.productTitleDescrWrap div.productTitle a', 'span.topAreaProductTitle', 'span.productPrice', ',', '', '#productImagesLinksLarge a', 'li.characteristicsItem span.characteristicLabel', 'li.characteristicsItem span.characteristicValue', 0, 1),
(4, 'tokinito.gr', 'https://tokinito.gr/mobile-phones-tablets-accessories/mobiles.html', 'utf-8', '.pages ol li a', 'p', 1, '#products-list .item-image a', 'h2.product-name', '.regular-price .price', ',', '#product-attribute-specs-table-1 ', '#yt_cloudzoom', '.box-additional table.data-table tr th', '.box-additional table.data-table tr td.data.last', 0, 1),
(5, 'kaizershop.gr', 'https://www.kaizershop.gr/cell-phones-smartphones', 'utf-8', 'ul.pagination li a', 'page', 1, '#products .product-block a.img', 'h1.name', '.special-price', ',', 'inner-product-images img', '#imageGallery li img', '#technical-section .row.table-row .row div:first-child', '#technical-section .row.table-row .row div:last-child', 0, 1),
(6, 'open365.gr', 'http://www.open365.gr/kinita-thlephona/', 'utf-8', '', '', 1, '#category_products_19 .title-price-wrapper a', 'h1.mainbox-title', '.price', ',', '#content_features > .control-group .feature-value', '.image-wrap img', '#content_features .control-group label', '#content_features .control-group .feature-value', 1, 2),
(7, 'electronet.gr', 'https://www.electronet.gr/tilefonia/smartphones', 'utf-8', 'ul.pager li a', 'page', 1, '.item-product .product-image a', 'h1[itemprop=\'name\']', '[itemprop=\'price\']', '.', '', '.gallery-slides ul li:first-child a img', '.field-spec-properties table tr td:first-child', '.field-spec-properties table tr td:last-child', 0, 0),
(8, 'you.gr', 'https://www.you.gr/telephony-networking/kinita-tilefona-axesouar/kinita-tilefona', 'utf-8', 'ul.pagination li a', 'page', 1, '.product-list .description-container h2.title a', 'h1[itemprop=\'name\']', '[itemprop=\'price\']', ',', '.technical-charact ul li:nth-child(2)', '[itemprop=\'image\']', '.technical-charact ul li:first-child', '.technical-charact ul li:last-child', 0, 0),
(11, 'playmobiles.gr', 'http://www.playmobiles.gr/index.php?route=product/category&path=59', 'utf-8', '', '', 1, '.product-list .name a', '.product-info .description .heading', '.product-info .price .price-tax', ',', '', '.product-info img#image', '', '', 0, 0),
(23, 'safesales.gr', 'http://safesales.gr/index.php?cPath=69&sort=2a&ppp=9999', 'utf-8', '', '', 1, '.prodList .pl.product-listing .pl-name a:not(.cat-name)', '.product-holder .prod-details h1:first', '.product-holder .price-holder .productprice:first', '.', '#tab2 table tr td.charact_tab_lines2', '.product-holder img.container', '#tab2 table tr td.charact_tab_lines1', '#tab2 table tr td.charact_tab_lines2', 0, 0),
(15, 'omgadget.gr', 'http://www.omgadget.gr/-', 'utf-8', '.pages ol li a', 'p', 1, '.category-products li.item h2.product-name a', '.product-name h1.medium', '.price', ',', '#product-attribute-specs-table tr:first-child td.data', '#image', '#product-attribute-specs-table tr th', '#product-attribute-specs-table tr td.data', 0, 2),
(16, 'e-tzampa.gr', 'https://www.e-tzampa.gr/50-mobile-phones', 'utf-8', 'ul.pagination li a', 'p', 1, 'a.product-name', 'h1[itemprop=\'name\']', '[itemprop=\'price\']', ',', '', '[itemprop=\'image\']', '#product-features-tab-content table tr td:first-child', '#product-features-tab-content table tr td:last-child', 0, 0),
(17, 'apothikiproios.gr', 'https://apothikiproios.gr/48-mobile-phones', 'utf-8', 'ul.pagination li a', 'p', 1, '.product-container h5.product-name-container a', 'h1[itemprop=\'name\']', '[itemprop=\'price\']', ',', '', '[itemprop=\'image\']', '.table-data-sheet tr td:first-child', '.table-data-sheet tr td:last-child', 0, 0),
(19, 'sevenspot.gr', 'https://www.sevenspot.gr/smartphones-5000342', 'utf-8', 'ul.pagination li a', 'p', 1, 'section.product-info a.product-name', 'h1[itemprop=\'name\']', '[itemprop=\'price\']', ',', '.table-data-sheet tr:first-child td:last-child', '[itemprop=\'image\']', '.table-data-sheet tr td:first-child', '.table-data-sheet tr td:last-child', 0, 0),
(20, 'top-store.gr', 'https://top-store.gr/katigories/kinita-tilefona/?paged=&count=9999', 'utf-8', '', '', 1, 'ul.products .product-details a.product-loop-title', 'h1[itemprop=\'name\']', '.product-essential .price', ',', '.shop_attributes tr:first-child td', '.product-essential img', '.shop_attributes tr th', '.shop_attributes tr td', 0, 0),
(21, 'multitec.gr', 'http://www.multitec.gr/68-cellphones.html', 'utf-8', 'ul.pagination li a', 'p', 1, '#content .product-meta .product-title h5 a.product-name', 'h1[itemprop=\'name\']', '.primary_block .price #our_price_display', ',', '.primary_block #manufacturer_custom a span', 'img#bigpic', '.table-data-sheet tr td:first-child', '.table-data-sheet tr td:last-child', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cache`
--
ALTER TABLE `cache`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `resources`
--
ALTER TABLE `resources`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `category_url` (`category_url`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cache`
--
ALTER TABLE `cache`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `resources`
--
ALTER TABLE `resources`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
