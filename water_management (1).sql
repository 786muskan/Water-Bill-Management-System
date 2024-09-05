-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 30, 2024 at 01:23 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `water_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `bid` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `billing_date` date NOT NULL,
  `due_date` date NOT NULL,
  `status` varchar(6) NOT NULL DEFAULT 'Unpaid',
  `payment_date` date DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `b_to_m` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bid`, `mid`, `billing_date`, `due_date`, `status`, `payment_date`) VALUES
(1, 1, '2024-06-01', '2024-06-10', 'Unpaid', NULL),
(2, 1, '2024-07-01', '2024-07-10', 'Unpaid', NULL),
(3, 2, '2024-07-01', '2024-07-10', 'Unpaid', NULL),
(4, 1, '2024-08-01', '2024-08-10', 'Unpaid', NULL),
(5, 2, '2024-08-01', '2024-08-10', 'Unpaid', NULL),
(6, 3, '2024-08-01', '2024-08-10', 'Unpaid', NULL),
(7, 4, '2024-08-01', '2024-08-10', 'Unpaid', NULL),
(8, 5, '2024-08-01', '2024-08-10', 'Unpaid', NULL),
(9, 6, '2024-08-01', '2024-08-10', 'Unpaid', NULL),
(10, 7, '2024-08-01', '2024-08-10', 'Unpaid', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
CREATE TABLE IF NOT EXISTS `consumer` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `caddress` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cphone` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cemail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `caadharno` varchar(12) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `unique` (`caadharno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `consumer`
--

INSERT INTO `consumer` (`cid`, `cname`, `caddress`, `cphone`, `cemail`, `caadharno`) VALUES
(1, 'Varsha', 'Ajmer', '7678768765', 'vs@gmail.com', '345434567654'),
(2, 'Sanjana Sharma', 'Veshali Nagar, Ajmer', '8796079567', 'ss@gmail.com', '6567678909'),
(3, 'Muskan Loungani', 'Ajmer', '9867545676', 'ml@gmail.com', '234567869586'),
(4, 'Hema', 'Ajmer', '9438676594', 'hm@gmail.com', '334576865965'),
(5, 'Anjum', 'Ajmer', '8876978845', 'aj@gmail.com', '445345867545'),
(6, 'Sunny', 'Ajmer', '9667845768', 'sm@gmail.com', '845576758434'),
(7, 'Kapil', 'Ajmer', '9944384754', 'kb@gmail.com', '244543445665');

-- --------------------------------------------------------

--
-- Table structure for table `meter`
--

DROP TABLE IF EXISTS `meter`;
CREATE TABLE IF NOT EXISTS `meter` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `meter_type` int NOT NULL,
  `installation_date` date DEFAULT NULL,
  `ci_number` varchar(12) NOT NULL,
  `Status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Active',
  PRIMARY KEY (`mid`),
  KEY `m_to_c` (`cid`),
  KEY `m_to_mt` (`meter_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `meter`
--

INSERT INTO `meter` (`mid`, `cid`, `meter_type`, `installation_date`, `ci_number`, `Status`) VALUES
(1, 1, 1, '2024-05-08', '434586754565', 'Active'),
(2, 2, 3, '2024-06-02', '984345678987', 'Active'),
(3, 1, 2, '2024-07-09', '344335687965', 'Active'),
(4, 3, 3, '2024-07-12', '988876776548', 'Active'),
(5, 4, 1, '2024-07-12', '334385754857', 'Active'),
(6, 5, 2, '2024-07-22', '767787654568', 'Active'),
(7, 6, 3, '2024-07-25', '445545768656', 'Active'),
(8, 7, 3, '2024-08-03', '223232456567', 'Active'),
(9, 3, 1, '2024-08-03', '998899667456', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `meter_reading`
--

DROP TABLE IF EXISTS `meter_reading`;
CREATE TABLE IF NOT EXISTS `meter_reading` (
  `mrid` int NOT NULL AUTO_INCREMENT,
  `mid` int NOT NULL,
  `reading_date` date NOT NULL,
  `previous_reading` int NOT NULL,
  `current_reading` int NOT NULL,
  PRIMARY KEY (`mrid`),
  KEY `mr_to_m` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `meter_reading`
--

INSERT INTO `meter_reading` (`mrid`, `mid`, `reading_date`, `previous_reading`, `current_reading`) VALUES
(1, 1, '2024-06-01', 0, 20500),
(2, 1, '2024-07-01', 20500, 42000),
(3, 2, '2024-07-01', 0, 22000),
(4, 1, '2024-08-01', 42000, 60000),
(5, 2, '2024-08-01', 22000, 35000),
(6, 3, '2024-08-01', 0, 15000),
(7, 4, '2024-08-01', 0, 12000),
(8, 5, '2024-08-01', 0, 17000),
(9, 6, '2024-08-01', 0, 5000),
(10, 7, '2024-08-01', 0, 2000);

-- --------------------------------------------------------

--
-- Table structure for table `meter_type`
--

DROP TABLE IF EXISTS `meter_type`;
CREATE TABLE IF NOT EXISTS `meter_type` (
  `mtid` int NOT NULL AUTO_INCREMENT,
  `mtname` varchar(50) NOT NULL,
  `mtbase_price` float NOT NULL,
  PRIMARY KEY (`mtid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `meter_type`
--

INSERT INTO `meter_type` (`mtid`, `mtname`, `mtbase_price`) VALUES
(1, 'Basic Meter', 40),
(2, 'Commercial Meter', 140),
(3, 'Smart Meter', 100);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `b_to_m` FOREIGN KEY (`mid`) REFERENCES `meter` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `meter`
--
ALTER TABLE `meter`
  ADD CONSTRAINT `m_to_c` FOREIGN KEY (`cid`) REFERENCES `consumer` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `m_to_mt` FOREIGN KEY (`meter_type`) REFERENCES `meter_type` (`mtid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `meter_reading`
--
ALTER TABLE `meter_reading`
  ADD CONSTRAINT `mr_to_m` FOREIGN KEY (`mid`) REFERENCES `meter` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
