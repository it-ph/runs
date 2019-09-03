-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.34-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for run_proud
CREATE DATABASE IF NOT EXISTS `run_proud` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `run_proud`;

-- Dumping structure for table run_proud.entitlements
CREATE TABLE IF NOT EXISTS `entitlements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `cost` float NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.entitlements: ~2 rows (approximately)
/*!40000 ALTER TABLE `entitlements` DISABLE KEYS */;
INSERT INTO `entitlements` (`id`, `name`, `cost`, `createdAt`, `updatedAt`) VALUES
	(1, 'STANDARD', 10, '2018-04-18 02:40:42', NULL),
	(2, 'PREMIUM', 20, '2018-04-18 02:40:42', NULL);
/*!40000 ALTER TABLE `entitlements` ENABLE KEYS */;

-- Dumping structure for table run_proud.event_categories
CREATE TABLE IF NOT EXISTS `event_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `categ_desc` varchar(255) DEFAULT NULL,
  `distance` float(10,2) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.event_categories: ~3 rows (approximately)
/*!40000 ALTER TABLE `event_categories` DISABLE KEYS */;
INSERT INTO `event_categories` (`id`, `category`, `createdAt`, `updatedAt`, `categ_desc`, `distance`) VALUES
	(1, 'RUN', '2018-04-18 02:54:10', NULL, '160 KM in 30 days', 0000160.00),
	(2, 'RIDE', '2018-04-18 02:54:10', NULL, 'Run 40 KM in 15 days, Run 160 KM and Bike 300 KM in 30 days', 0000300.00),
	(3, 'DUATHLON', '2018-04-18 02:54:10', NULL, '300 Km in 30 days', 0000460.00);
/*!40000 ALTER TABLE `event_categories` ENABLE KEYS */;

-- Dumping structure for table run_proud.notifications
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `body` varchar(255) NOT NULL,
  `click_action` varchar(255) NOT NULL,
  `viewed` tinyint(4) NOT NULL DEFAULT '0',
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `recipient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `recipient` (`recipient`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`recipient`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.notifications: ~196 rows (approximately)
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` (`id`, `title`, `body`, `click_action`, `viewed`, `createdAt`, `updatedAt`, `recipient`) VALUES
	(1, 'Christian Paul', 'RUN registration', 'run_registrations', 1, '2019-08-02 05:28:24', '2019-08-02 05:31:51', 641),
	(2, 'Christian Paul', 'RUN registration', 'run_registrations', 1, '2019-08-02 05:28:24', '2019-08-02 21:32:02', 642),
	(3, 'Christian Paul', 'RUN registration', 'run_registrations', 1, '2019-08-02 05:28:24', '2019-08-06 15:41:11', 643),
	(5, 'Paul', 'RUN registration', 'run_registrations', 1, '2019-08-02 06:00:08', '2019-08-02 07:44:40', 641),
	(6, 'Paul', 'RUN registration', 'run_registrations', 1, '2019-08-02 06:00:08', '2019-08-02 21:32:03', 642),
	(7, 'Paul', 'RUN registration', 'run_registrations', 1, '2019-08-02 06:00:08', '2019-08-06 15:41:11', 643),
	(8, 'Paul', 'RUN registration', 'run_registrations', 0, '2019-08-02 06:00:08', NULL, 644),
	(13, 'Suresh Jr M. Jesswani', 'RUN registration', 'run_registrations', 1, '2019-08-02 21:44:51', '2019-08-02 21:49:41', 641),
	(14, 'Suresh Jr M. Jesswani', 'RUN registration', 'run_registrations', 1, '2019-08-02 21:44:51', '2019-08-02 21:47:09', 642),
	(15, 'Suresh Jr M. Jesswani', 'RUN registration', 'run_registrations', 1, '2019-08-02 21:44:51', '2019-08-06 15:41:11', 643),
	(16, 'Suresh Jr M. Jesswani', 'RUN registration', 'run_registrations', 0, '2019-08-02 21:44:51', NULL, 644),
	(20, 'Registration request', 'Your registration for RUN has been approved', 'home', 0, '2019-08-02 22:06:42', NULL, 648),
	(21, 'Darwin Dogayo II', 'DUATHLON registration', 'run_registrations', 1, '2019-08-02 22:18:50', '2019-08-02 22:37:31', 641),
	(22, 'Darwin Dogayo II', 'DUATHLON registration', 'run_registrations', 1, '2019-08-02 22:18:50', '2019-08-02 22:19:34', 642),
	(23, 'Darwin Dogayo II', 'DUATHLON registration', 'run_registrations', 1, '2019-08-02 22:18:50', '2019-08-06 15:41:11', 643),
	(24, 'Darwin Dogayo II', 'DUATHLON registration', 'run_registrations', 0, '2019-08-02 22:18:50', NULL, 644),
	(29, 'duumy', 'RUN registration', 'run_registrations', 1, '2019-08-02 22:38:37', '2019-08-02 22:54:36', 641),
	(30, 'duumy', 'RUN registration', 'run_registrations', 1, '2019-08-02 22:38:37', '2019-08-02 22:46:54', 642),
	(31, 'duumy', 'RUN registration', 'run_registrations', 1, '2019-08-02 22:38:37', '2019-08-06 15:41:11', 643),
	(32, 'duumy', 'RUN registration', 'run_registrations', 0, '2019-08-02 22:38:37', NULL, 644),
	(36, 'duumy', 'RIDE registration', 'run_registrations', 1, '2019-08-02 22:39:21', '2019-08-02 23:27:08', 641),
	(37, 'duumy', 'RIDE registration', 'run_registrations', 1, '2019-08-02 22:39:21', '2019-08-02 22:46:54', 642),
	(38, 'duumy', 'RIDE registration', 'run_registrations', 1, '2019-08-02 22:39:21', '2019-08-06 15:41:11', 643),
	(39, 'duumy', 'RIDE registration', 'run_registrations', 0, '2019-08-02 22:39:21', NULL, 644),
	(45, 'Srinivasa Rajan AR', 'RUN registration', 'run_registrations', 1, '2019-08-02 22:50:20', '2019-08-02 23:27:06', 641),
	(46, 'Srinivasa Rajan AR', 'RUN registration', 'run_registrations', 1, '2019-08-02 22:50:20', '2019-08-02 22:50:40', 642),
	(47, 'Srinivasa Rajan AR', 'RUN registration', 'run_registrations', 1, '2019-08-02 22:50:20', '2019-08-06 15:41:11', 643),
	(48, 'Srinivasa Rajan AR', 'RUN registration', 'run_registrations', 0, '2019-08-02 22:50:20', NULL, 644),
	(54, 'Registration request', 'Your registration for RUN has been approved', 'home', 0, '2019-08-02 22:50:53', NULL, 647),
	(55, 'Registration request', 'Your registration for DUATHLON has been approved', 'home', 1, '2019-08-02 23:53:28', '2019-08-02 23:54:27', 642),
	(56, 'Registration request', 'Your registration for RUN has been approved', 'home', 1, '2019-08-02 23:58:29', '2019-08-03 02:11:17', 641),
	(57, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-03 02:01:29', '2019-08-03 02:11:21', 641),
	(58, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-03 02:01:29', '2019-08-03 02:04:09', 642),
	(59, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-03 02:01:29', '2019-08-06 15:41:11', 643),
	(60, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-03 02:01:29', NULL, 644),
	(64, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-03 02:03:28', '2019-08-03 02:11:20', 641),
	(65, 'Katherine Anne Ricafort', 'RUN registration', 'run_registrations', 1, '2019-08-03 03:15:28', '2019-08-03 03:16:44', 641),
	(66, 'Katherine Anne Ricafort', 'RUN registration', 'run_registrations', 0, '2019-08-03 03:15:28', NULL, 642),
	(67, 'Katherine Anne Ricafort', 'RUN registration', 'run_registrations', 1, '2019-08-03 03:15:28', '2019-08-06 15:41:11', 643),
	(68, 'Katherine Anne Ricafort', 'RUN registration', 'run_registrations', 0, '2019-08-03 03:15:28', NULL, 644),
	(72, 'Registration request', 'Your registration for RUN has been approved', 'home', 1, '2019-08-03 03:15:55', '2019-08-03 04:44:22', 653),
	(73, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-05 16:41:57', '2019-08-05 16:42:29', 641),
	(74, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-05 16:41:57', NULL, 642),
	(75, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-05 16:41:57', '2019-08-06 15:41:11', 643),
	(76, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-05 16:41:57', NULL, 644),
	(80, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-05 16:42:24', '2019-08-05 17:24:00', 641),
	(81, 'Joe Joe', 'DUATHLON registration', 'run_registrations', 1, '2019-08-05 17:26:06', '2019-08-05 17:26:20', 641),
	(82, 'Joe Joe', 'DUATHLON registration', 'run_registrations', 0, '2019-08-05 17:26:06', NULL, 642),
	(83, 'Joe Joe', 'DUATHLON registration', 'run_registrations', 1, '2019-08-05 17:26:06', '2019-08-06 15:41:11', 643),
	(84, 'Joe Joe', 'DUATHLON registration', 'run_registrations', 0, '2019-08-05 17:26:06', NULL, 644),
	(88, 'Registration request', 'Your registration for DUATHLON has been approved', 'home', 1, '2019-08-05 17:26:17', '2019-08-05 17:44:57', 654),
	(89, 'Record Submition', 'Joe Joe has submitted a record', 'records', 1, '2019-08-05 17:26:38', '2019-08-05 17:50:20', 641),
	(90, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-05 17:26:38', NULL, 642),
	(91, 'Record Submition', 'Joe Joe has submitted a record', 'records', 1, '2019-08-05 17:26:38', '2019-08-06 15:41:11', 643),
	(92, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-05 17:26:38', NULL, 644),
	(96, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-05 17:26:58', '2019-08-05 17:44:58', 654),
	(97, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-05 17:47:04', '2019-08-06 17:18:02', 641),
	(98, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-05 17:47:04', NULL, 642),
	(99, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-05 17:47:04', '2019-08-06 15:41:11', 643),
	(100, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-05 17:47:04', NULL, 644),
	(104, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-05 17:47:14', '2019-08-06 17:18:02', 641),
	(105, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-05 17:54:53', '2019-08-06 17:18:02', 641),
	(106, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-05 17:54:53', NULL, 642),
	(107, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-05 17:54:53', '2019-08-06 15:41:11', 643),
	(108, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-05 17:54:53', NULL, 644),
	(109, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-06 11:04:45', '2019-08-06 17:18:02', 641),
	(110, 'Registration request', 'Your registration for RIDE has been approved', 'home', 1, '2019-08-06 11:49:05', '2019-08-06 15:41:11', 643),
	(111, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 1, '2019-08-06 15:36:09', '2019-08-06 17:18:02', 641),
	(112, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 0, '2019-08-06 15:36:09', NULL, 642),
	(113, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 1, '2019-08-06 15:36:09', '2019-08-06 15:41:14', 643),
	(114, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 0, '2019-08-06 15:36:09', NULL, 644),
	(115, 'Joe Joe', 'RIDE registration', 'run_registrations', 1, '2019-08-07 10:11:08', '2019-08-08 11:41:27', 641),
	(116, 'Joe Joe', 'RIDE registration', 'run_registrations', 0, '2019-08-07 10:11:08', NULL, 642),
	(117, 'Joe Joe', 'RIDE registration', 'run_registrations', 0, '2019-08-07 10:11:08', NULL, 643),
	(118, 'Joe Joe', 'RIDE registration', 'run_registrations', 0, '2019-08-07 10:11:08', NULL, 644),
	(122, 'Registration request', 'Your registration for RIDE has been approved', 'home', 1, '2019-08-07 10:11:22', '2019-08-08 15:26:04', 654),
	(123, 'Record Submition', 'Joe Joe has submitted a record', 'records', 1, '2019-08-07 10:13:44', '2019-08-08 11:41:27', 641),
	(124, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-07 10:13:44', NULL, 642),
	(125, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-07 10:13:44', NULL, 643),
	(126, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-07 10:13:44', NULL, 644),
	(130, 'Record submission', 'Your record submission has been approved', 'home', 0, '2019-08-07 10:13:57', NULL, 654),
	(131, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 1, '2019-08-07 10:20:12', '2019-08-08 11:41:27', 641),
	(132, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 0, '2019-08-07 10:20:12', NULL, 642),
	(133, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 0, '2019-08-07 10:20:12', NULL, 643),
	(134, 'Jhai Talingdan', 'RUN registration', 'run_registrations', 0, '2019-08-07 10:20:12', NULL, 644),
	(138, 'Registration request', 'Your registration for RUN has been approved', 'home', 0, '2019-08-07 10:20:19', NULL, 643),
	(139, 'Record Submition', 'Jhai Talingdan has submitted a record', 'records', 1, '2019-08-07 10:20:33', '2019-08-08 11:41:27', 641),
	(140, 'Record Submition', 'Jhai Talingdan has submitted a record', 'records', 0, '2019-08-07 10:20:33', NULL, 642),
	(141, 'Record Submition', 'Jhai Talingdan has submitted a record', 'records', 0, '2019-08-07 10:20:33', NULL, 643),
	(142, 'Record Submition', 'Jhai Talingdan has submitted a record', 'records', 0, '2019-08-07 10:20:33', NULL, 644),
	(146, 'Record submission', 'Your record submission has been approved', 'home', 0, '2019-08-07 10:20:37', NULL, 643),
	(147, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-08 10:16:22', '2019-08-08 11:41:27', 641),
	(148, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-08 10:16:22', NULL, 642),
	(149, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-08 10:16:22', NULL, 643),
	(150, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-08 10:16:22', NULL, 644),
	(154, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-08 10:16:41', '2019-08-08 11:41:27', 641),
	(155, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-08 14:29:26', '2019-08-13 11:48:18', 641),
	(156, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-08 14:29:26', NULL, 642),
	(157, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-08 14:29:26', NULL, 643),
	(158, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-08 14:29:26', NULL, 644),
	(162, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-08 14:43:14', '2019-08-13 11:48:18', 641),
	(163, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-13 12:16:34', '2019-08-15 09:53:10', 641),
	(164, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-13 12:16:34', NULL, 642),
	(165, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-13 12:16:34', NULL, 643),
	(166, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-13 12:16:34', NULL, 644),
	(170, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-13 12:16:50', '2019-08-22 11:21:43', 641),
	(171, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-13 14:40:02', '2019-08-22 11:21:44', 641),
	(172, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-13 14:40:02', NULL, 642),
	(173, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-13 14:40:02', NULL, 643),
	(174, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-13 14:40:02', NULL, 644),
	(178, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-13 14:40:17', '2019-08-22 11:21:44', 641),
	(179, 'Paul Christian Argao', 'RUN registration', 'run_registrations', 1, '2019-08-14 12:13:36', '2019-08-22 11:21:44', 641),
	(180, 'Paul Christian Argao', 'RUN registration', 'run_registrations', 0, '2019-08-14 12:13:36', NULL, 642),
	(181, 'Paul Christian Argao', 'RUN registration', 'run_registrations', 0, '2019-08-14 12:13:36', NULL, 643),
	(182, 'Paul Christian Argao', 'RUN registration', 'run_registrations', 0, '2019-08-14 12:13:36', NULL, 644),
	(186, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-14 12:23:11', '2019-08-22 11:21:44', 641),
	(187, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-14 12:23:11', NULL, 642),
	(188, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-14 12:23:11', NULL, 643),
	(189, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-14 12:23:11', NULL, 644),
	(193, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-14 12:23:17', '2019-08-22 11:21:44', 641),
	(194, 'Record Submition', 'Joe Joe has submitted a record', 'records', 1, '2019-08-14 15:00:13', '2019-08-22 11:21:44', 641),
	(195, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-14 15:00:13', NULL, 642),
	(196, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-14 15:00:13', NULL, 643),
	(197, 'Record Submition', 'Joe Joe has submitted a record', 'records', 0, '2019-08-14 15:00:13', NULL, 644),
	(201, 'Record submission', 'Your record submission has been approved', 'home', 0, '2019-08-14 15:00:34', NULL, 654),
	(202, 'jerico.grijaldo@personiv.com', 'RUN registration', 'run_registrations', 1, '2019-08-15 09:53:53', '2019-08-22 11:21:44', 641),
	(203, 'jerico.grijaldo@personiv.com', 'RUN registration', 'run_registrations', 0, '2019-08-15 09:53:53', NULL, 642),
	(204, 'jerico.grijaldo@personiv.com', 'RUN registration', 'run_registrations', 0, '2019-08-15 09:53:53', NULL, 643),
	(205, 'jerico.grijaldo@personiv.com', 'RUN registration', 'run_registrations', 0, '2019-08-15 09:53:53', NULL, 644),
	(206, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-19 12:47:46', '2019-08-22 11:21:44', 641),
	(207, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 12:47:46', NULL, 642),
	(208, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 12:47:46', NULL, 643),
	(209, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 12:47:46', NULL, 644),
	(213, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-19 12:48:01', '2019-08-22 11:21:44', 641),
	(214, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-19 14:21:00', '2019-08-22 11:21:44', 641),
	(215, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 14:21:00', NULL, 642),
	(216, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 14:21:00', NULL, 643),
	(217, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 14:21:00', NULL, 644),
	(221, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-19 14:21:04', '2019-08-22 11:21:44', 641),
	(222, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-19 20:58:51', '2019-08-22 11:21:44', 641),
	(223, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 20:58:51', NULL, 642),
	(224, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 20:58:51', NULL, 643),
	(225, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-19 20:58:51', NULL, 644),
	(226, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 10:54:39', '2019-08-22 11:21:44', 641),
	(227, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 10:54:39', NULL, 642),
	(228, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 10:54:39', NULL, 643),
	(229, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 10:54:39', NULL, 644),
	(233, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 10:59:06', '2019-08-22 11:21:44', 641),
	(234, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 10:59:06', NULL, 642),
	(235, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 10:59:06', NULL, 643),
	(236, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 10:59:06', NULL, 644),
	(240, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 11:06:29', '2019-08-22 11:21:44', 641),
	(241, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:06:29', NULL, 642),
	(242, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:06:29', NULL, 643),
	(243, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:06:29', NULL, 644),
	(247, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 11:09:44', '2019-08-22 11:21:44', 641),
	(248, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:09:44', NULL, 642),
	(249, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:09:44', NULL, 643),
	(250, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:09:44', NULL, 644),
	(254, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 11:10:14', '2019-08-22 11:21:44', 641),
	(255, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:10:14', NULL, 642),
	(256, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:10:14', NULL, 643),
	(257, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:10:14', NULL, 644),
	(261, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 11:17:15', '2019-08-22 11:21:44', 641),
	(262, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:17:15', NULL, 642),
	(263, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:17:15', NULL, 643),
	(264, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:17:15', NULL, 644),
	(268, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 11:21:58', '2019-08-22 11:21:44', 641),
	(269, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:21:58', NULL, 642),
	(270, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:21:58', NULL, 643),
	(271, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:21:58', NULL, 644),
	(275, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-20 11:29:36', '2019-08-22 11:21:44', 641),
	(276, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:29:36', NULL, 642),
	(277, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:29:36', NULL, 643),
	(278, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-20 11:29:36', NULL, 644),
	(282, 'Registration request', 'Your registration for RUN has been approved', 'home', 0, '2019-08-20 11:31:57', NULL, 655),
	(283, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-20 11:34:01', '2019-08-22 11:21:44', 641),
	(284, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-22 10:12:13', '2019-08-22 11:21:44', 641),
	(285, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:12:13', NULL, 642),
	(286, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:12:13', NULL, 643),
	(287, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:12:13', NULL, 644),
	(291, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-22 10:12:19', '2019-08-22 11:21:44', 641),
	(292, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-22 10:14:16', '2019-08-22 11:21:44', 641),
	(293, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:14:16', NULL, 642),
	(294, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:14:16', NULL, 643),
	(295, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:14:16', NULL, 644),
	(299, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-22 10:26:31', '2019-08-22 11:21:44', 641),
	(300, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:26:31', NULL, 642),
	(301, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:26:31', NULL, 643),
	(302, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 10:26:31', NULL, 644),
	(306, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-22 10:26:36', '2019-08-22 11:21:44', 641),
	(307, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-22 14:02:15', '2019-08-22 16:02:16', 641),
	(308, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 14:02:15', NULL, 642),
	(309, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 14:02:15', NULL, 643),
	(310, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 14:02:15', NULL, 644),
	(314, 'Record submission', 'Your record submission has been approved', 'home', 1, '2019-08-22 14:02:22', '2019-08-22 16:02:16', 641),
	(315, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 1, '2019-08-22 15:59:11', '2019-08-22 16:02:16', 641),
	(316, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 15:59:11', NULL, 642),
	(317, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 15:59:11', NULL, 643),
	(318, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-22 15:59:11', NULL, 644),
	(319, 'Record submission', 'Your record submission has been approved', 'home', 0, '2019-08-23 13:38:57', NULL, 641),
	(320, 'Record submission', 'Your record submission has been disapproved', 'home', 0, '2019-08-23 15:26:38', NULL, 641),
	(321, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-28 00:44:46', NULL, 641),
	(322, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-28 00:44:46', NULL, 642),
	(323, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-28 00:44:46', NULL, 643),
	(324, 'Record Submition', 'Paul Christian Argao has submitted a record', 'records', 0, '2019-08-28 00:44:46', NULL, 644),
	(328, 'Record submission', 'Your record submission has been approved', 'home', 0, '2019-08-28 00:44:54', NULL, 641);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;

-- Dumping structure for table run_proud.registrations
CREATE TABLE IF NOT EXISTS `registrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `reg_status` tinyint(4) NOT NULL DEFAULT '0',
  `facility` enum('AUSTIN','COIMBATORE','GURUGRAM','MANILA') NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.registrations: ~8 rows (approximately)
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
INSERT INTO `registrations` (`id`, `email`, `fullname`, `gender`, `password`, `reg_status`, `facility`, `createdAt`, `updatedAt`) VALUES
	(6, 'sureshjr.jesswani@gmail.com', 'Suresh Jr M. Jesswani', 'MALE', '$2a$10$xeFwhn970eqb624GUZ/vWuP0YVoWdLb5rhpian8v9jREZRHsuQeVG', 1, 'AUSTIN', '2019-08-02 16:03:21', '2019-08-02 21:42:54'),
	(7, 'srinivas@personiv.com', 'Srinivasa Rajan AR', 'MALE', '$2a$10$t5.d3lU6VBF4u3KoIVmJ2.qeuh78BCVDHwXh7yqCeXRqkUmcfrrgC', 1, 'AUSTIN', '2019-08-02 17:23:50', '2019-08-02 21:42:44'),
	(9, 'kim.remigio@personiv.com', 'Michael Xavier Remigio', 'MALE', '$2a$10$d7r5K6Y8osC9rJHMOSjsB.xY5WtzFjLb0JSKrMSbhpeAkuUWM3gzm', 1, 'AUSTIN', '2019-08-02 21:46:27', '2019-08-02 21:46:57'),
	(12, 'robbierobles91@gmail.com', 'Robbie L Robles', 'MALE', '$2a$10$m8b3Y6P8FLrkyJRG0qbj5eDEYCAqTNcOttVGZBu6D2L2npnn82una', 1, 'AUSTIN', '2019-08-02 23:23:27', '2019-08-02 23:36:21'),
	(13, 'kate_aguirre@yahoo.com', 'Katherine Anne Ricafort', 'FEMALE', '$2a$10$d7r5K6Y8osC9rJHMOSjsB.xY5WtzFjLb0JSKrMSbhpeAkuUWM3gzm', 1, 'AUSTIN', '2019-08-03 00:01:31', '2019-08-03 02:41:51'),
	(14, 'yohanceargao@gmail.com', 'Paul Christian Argao', 'MALE', '$2a$10$d7r5K6Y8osC9rJHMOSjsB.xY5WtzFjLb0JSKrMSbhpeAkuUWM3gzm', 1, 'MANILA', '2019-08-02 16:03:21', '2019-08-02 21:42:54'),
	(15, 'jhai.talingdan@personiv.com', 'Jhai Talingdan', 'MALE', '$2a$10$d7r5K6Y8osC9rJHMOSjsB.xY5WtzFjLb0JSKrMSbhpeAkuUWM3gzm', 1, 'AUSTIN', '2019-08-05 15:48:42', '2019-08-02 21:42:54'),
	(16, 'try@personiv.com', 'Joe Joe', 'MALE', '$2a$10$Udf9MskO/4SQeFeLiWhGlOsNh/u8TpE5Go7xrsDSYZYscNmTmxSli', 1, 'MANILA', '2019-08-05 17:25:34', '2019-08-05 17:25:45'),
	(17, 'argaop01@thryv.com', 'jerico.grijaldo@personiv.com', 'MALE', '$2a$10$ueCclVxJVsZWer8DMNHVj.JIozAFRJuftyY6EO6MeoFM8InQBffEy', 1, 'MANILA', '2019-08-15 09:52:45', '2019-08-15 09:53:31'),
	(18, 'team.renell@gmail.com', 'dsw', 'MALE', '$2a$10$WcrSTSpU1uKwKuyoaqddC.t9DbMYJcJ4AQuLABhr8xP9Ns5l4PMa6', 1, 'MANILA', '2019-08-28 01:00:44', '2019-08-28 01:01:30');
/*!40000 ALTER TABLE `registrations` ENABLE KEYS */;

-- Dumping structure for table run_proud.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_desc` (`role_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.roles: ~0 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table run_proud.run_events
CREATE TABLE IF NOT EXISTS `run_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reg_start` datetime NOT NULL,
  `reg_end` datetime NOT NULL,
  `run_start` datetime NOT NULL,
  `run_end` datetime DEFAULT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.run_events: ~1 rows (approximately)
/*!40000 ALTER TABLE `run_events` DISABLE KEYS */;
INSERT INTO `run_events` (`id`, `reg_start`, `reg_end`, `run_start`, `run_end`, `createdAt`, `updatedAt`) VALUES
	(2, '2019-08-01 00:00:00', '2019-08-31 00:00:00', '2019-09-01 00:00:00', '2019-09-30 00:00:00', '2019-07-15 00:00:00', NULL);
/*!40000 ALTER TABLE `run_events` ENABLE KEYS */;

-- Dumping structure for table run_proud.run_event_categories
CREATE TABLE IF NOT EXISTS `run_event_categories` (
  `event_id` int(11) NOT NULL,
  `categ_id` int(11) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`event_id`,`categ_id`),
  KEY `categ_id` (`categ_id`),
  CONSTRAINT `run_event_categories_ibfk_1` FOREIGN KEY (`categ_id`) REFERENCES `event_categories` (`id`),
  CONSTRAINT `run_event_categories_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `run_events` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.run_event_categories: ~0 rows (approximately)
/*!40000 ALTER TABLE `run_event_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `run_event_categories` ENABLE KEYS */;

-- Dumping structure for table run_proud.run_event_participants
CREATE TABLE IF NOT EXISTS `run_event_participants` (
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`event_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `run_event_participants_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `run_events` (`id`),
  CONSTRAINT `run_event_participants_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.run_event_participants: ~0 rows (approximately)
/*!40000 ALTER TABLE `run_event_participants` DISABLE KEYS */;
/*!40000 ALTER TABLE `run_event_participants` ENABLE KEYS */;

-- Dumping structure for table run_proud.run_event_reg
CREATE TABLE IF NOT EXISTS `run_event_reg` (
  `event_id` int(11) NOT NULL,
  `categ_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ent_id` int(11) NOT NULL,
  `approved` tinyint(4) NOT NULL DEFAULT '0',
  `shirt_size` enum('XS','S','M','L','XL','XXL') DEFAULT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  `forhim` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`categ_id`,`user_id`),
  KEY `categ_id` (`categ_id`),
  KEY `user_id` (`user_id`),
  KEY `ent_id` (`ent_id`),
  CONSTRAINT `run_event_reg_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `run_events` (`id`),
  CONSTRAINT `run_event_reg_ibfk_2` FOREIGN KEY (`categ_id`) REFERENCES `event_categories` (`id`),
  CONSTRAINT `run_event_reg_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `run_event_reg_ibfk_4` FOREIGN KEY (`ent_id`) REFERENCES `entitlements` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.run_event_reg: ~4 rows (approximately)
/*!40000 ALTER TABLE `run_event_reg` DISABLE KEYS */;
INSERT INTO `run_event_reg` (`event_id`, `categ_id`, `user_id`, `ent_id`, `approved`, `shirt_size`, `createdAt`, `updatedAt`, `forhim`) VALUES
	(2, 1, 641, 2, 1, 'XS', '2019-08-02 22:44:51', NULL, 1),
	(2, 1, 643, 2, 1, 'XL', '2019-08-07 10:20:12', NULL, NULL),
	(2, 2, 654, 2, 1, 'XS', '2019-08-07 10:11:08', NULL, NULL),
	(2, 2, 655, 2, 1, 'XS', '2019-08-15 09:53:53', NULL, NULL);
/*!40000 ALTER TABLE `run_event_reg` ENABLE KEYS */;

-- Dumping structure for table run_proud.run_progress
CREATE TABLE IF NOT EXISTS `run_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_path2` varchar(255) NOT NULL,
  `file_name2` varchar(255) NOT NULL,
  `run_time` time NOT NULL,
  `run_distance` float NOT NULL,
  `categ` varchar(255) NOT NULL DEFAULT '',
  `verified` enum('NO','VERIFIED','INVALID') DEFAULT 'NO',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  `categ_id` int(11) NOT NULL,
  `gallery` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `file_name` (`file_name`),
  KEY `user_id` (`user_id`),
  KEY `event_id` (`event_id`),
  KEY `categ_id` (`categ_id`),
  CONSTRAINT `run_progress_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `run_progress_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `run_events` (`id`),
  CONSTRAINT `run_progress_ibfk_3` FOREIGN KEY (`categ_id`) REFERENCES `event_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.run_progress: ~18 rows (approximately)
/*!40000 ALTER TABLE `run_progress` DISABLE KEYS */;
INSERT INTO `run_progress` (`id`, `user_id`, `event_id`, `file_path`, `file_name`, `file_path2`, `file_name2`, `run_time`, `run_distance`, `categ`, `verified`, `createdAt`, `updatedAt`, `categ_id`, `gallery`) VALUES
	(1, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_13_12_16_33_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '2019_08_13_12_16_33_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_13_12_16_33_renditionDownload.jpg', '2019_08_13_12_16_33_renditionDownload.jpg', '02:00:54', 2, 'RUN', 'VERIFIED', '2019-08-13 12:16:33', NULL, 1, '0'),
	(2, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_13_14_40_02_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '2019_08_13_14_40_02_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_13_14_40_02_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '2019_08_13_14_40_02_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '02:00:52', 2, 'RUN', 'VERIFIED', '2019-08-13 14:40:02', NULL, 1, '0'),
	(3, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_14_12_23_11_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '2019_08_14_12_23_11_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_14_12_23_11_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '2019_08_14_12_23_11_9be9c4b1-29f7-4986-98d3-d13ed718b151.jpg', '02:00:55', 3, 'RIDE', 'VERIFIED', '2019-08-14 12:23:11', NULL, 1, '0'),
	(4, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_19_12_47_46_Desert.jpg', '2019_08_19_12_47_46_Desert.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_19_12_47_46_Lighthouse.jpg', '2019_08_19_12_47_46_Lighthouse.jpg', '02:00:16', 10, 'RUN', 'VERIFIED', '2019-08-19 12:47:46', NULL, 1, '0'),
	(5, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_19_14_21_00_Tulips.jpg', '2019_08_19_14_21_00_Tulips.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_19_14_21_00_Koala.jpg', '2019_08_19_14_21_00_Koala.jpg', '02:00:46', 32, 'RUN', 'VERIFIED', '2019-08-19 14:21:00', NULL, 1, '0'),
	(6, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_19_20_58_50_Lighthouse.jpg', '2019_08_19_20_58_50_Lighthouse.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_19_20_58_50_Jellyfish.jpg', '2019_08_19_20_58_50_Jellyfish.jpg', '02:00:51', 2, 'RUN', 'VERIFIED', '2019-08-19 20:58:50', NULL, 1, '0'),
	(7, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_10_54_38_Tulips.jpg', '2019_08_20_10_54_38_Tulips.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_10_54_38_Jellyfish.jpg', '2019_08_20_10_54_38_Jellyfish.jpg', '02:00:12', 2, 'RUN', 'NO', '2019-08-20 10:54:39', NULL, 1, '0'),
	(8, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_10_59_06_Lighthouse.jpg', '2019_08_20_10_59_06_Lighthouse.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_10_59_06_Koala.jpg', '2019_08_20_10_59_06_Koala.jpg', '02:00:12', 2, 'RUN', 'NO', '2019-08-20 10:59:06', NULL, 1, '0'),
	(9, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_11_06_29_Jellyfish.jpg', '2019_08_20_11_06_29_Jellyfish.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_11_06_29_Lighthouse.jpg', '2019_08_20_11_06_29_Lighthouse.jpg', '02:00:17', 2, 'RUN', 'NO', '2019-08-20 11:06:29', NULL, 1, '0'),
	(10, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_11_09_43_Desert.jpg', '2019_08_20_11_09_43_Desert.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_11_09_43_Lighthouse.jpg', '2019_08_20_11_09_43_Lighthouse.jpg', '02:00:18', 2, 'RUN', 'NO', '2019-08-20 11:09:43', NULL, 1, '0'),
	(11, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_11_10_14_Desert.jpg', '2019_08_20_11_10_14_Desert.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_11_10_14_Tulips.jpg', '2019_08_20_11_10_14_Tulips.jpg', '01:59:59', 2, 'RUN', 'NO', '2019-08-20 11:10:14', NULL, 1, '0'),
	(12, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_11_17_15_Desert.jpg', '2019_08_20_11_17_15_Desert.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_11_17_15_Lighthouse.jpg', '2019_08_20_11_17_15_Lighthouse.jpg', '02:00:02', 2, 'RUN', 'NO', '2019-08-20 11:17:15', NULL, 1, '0'),
	(13, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_11_21_58_Desert.jpg', '2019_08_20_11_21_58_Desert.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_11_21_58_Lighthouse.jpg', '2019_08_20_11_21_58_Lighthouse.jpg', '02:00:45', 3, 'RUN', 'VERIFIED', '2019-08-20 11:21:58', NULL, 1, '0'),
	(14, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_20_11_29_36_Penguins.jpg', '2019_08_20_11_29_36_Penguins.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_20_11_29_36_Tulips.jpg', '2019_08_20_11_29_36_Tulips.jpg', '02:00:21', 2, 'RUN', 'NO', '2019-08-20 11:29:36', NULL, 1, '0'),
	(15, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_22_10_12_13_Hydrangeas.jpg', '2019_08_22_10_12_13_Hydrangeas.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_22_10_12_13_Tulips.jpg', '2019_08_22_10_12_13_Tulips.jpg', '02:00:55', 2, 'RUN', 'VERIFIED', '2019-08-22 10:12:13', NULL, 1, '1'),
	(16, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_22_10_14_16_Tulips.jpg', '2019_08_22_10_14_16_Tulips.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_22_10_14_16_Penguins.jpg', '2019_08_22_10_14_16_Penguins.jpg', '02:00:05', 2, 'RUN', 'NO', '2019-08-22 10:14:16', NULL, 1, '1'),
	(17, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_22_10_26_31_Tulips.jpg', '2019_08_22_10_26_31_Tulips.jpg', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_22_10_26_31_Penguins.jpg', '2019_08_22_10_26_31_Penguins.jpg', '02:00:17', 22, 'RIDE', 'VERIFIED', '2019-08-22 10:26:31', NULL, 1, '1'),
	(18, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_22_14_02_15_Capture4.PNG', '2019_08_22_14_02_15_Capture4.PNG', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_22_14_02_15_Capture3.PNG', '2019_08_22_14_02_15_Capture3.PNG', '02:00:40', 22, 'RIDE', 'VERIFIED', '2019-08-22 14:02:15', NULL, 1, '1'),
	(19, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_22_15_59_11_Capture3.PNG', '2019_08_22_15_59_11_Capture3.PNG', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_22_15_59_11_Capture5.PNG', '2019_08_22_15_59_11_Capture5.PNG', '02:00:22', 2, 'RUN', 'INVALID', '2019-08-22 15:59:11', NULL, 1, '1'),
	(20, 641, 2, '/opt/tomcat/run_upload/Paul Christian Argao/records/2019_08_28_00_44_45_Capture.PNG', '2019_08_28_00_44_45_Capture.PNG', '/opt/tomcat/run_uploads/Paul Christian Argao/records/2019_08_28_00_44_45_Capture5.PNG', '2019_08_28_00_44_45_Capture5.PNG', '01:43:27', 2, 'RUN', 'VERIFIED', '2019-08-28 00:44:45', NULL, 1, '1');
/*!40000 ALTER TABLE `run_progress` ENABLE KEYS */;

-- Dumping structure for table run_proud.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `accountNonLocked` tinyint(4) NOT NULL DEFAULT '1',
  `accountNonExpired` tinyint(4) NOT NULL DEFAULT '1',
  `credentialsNonExpired` tinyint(4) NOT NULL DEFAULT '1',
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `role` enum('ADMIN','USER') DEFAULT 'USER',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=657 DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.users: ~10 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `email`, `fullname`, `gender`, `password`, `accountNonLocked`, `accountNonExpired`, `credentialsNonExpired`, `enabled`, `role`, `createdAt`, `updatedAt`) VALUES
	(641, 'yohanceargao@gmail.com', 'Paul Christian Argao', 'MALE', '$2a$10$7e6aDBzVx4YgC9iV.3HZBOUkBcGWzT8JH29cdpebl3TwyK.T4U5XG', 1, 1, 1, 1, 'ADMIN', '2019-08-02 03:53:47', NULL),
	(642, 'darwin.dogayo@personiv.com', 'Darwin Dogayo', 'MALE', '$2a$10$VBgiCkFYB80wkMUEXF.XQ.7ayI5.ZcxZT07Hip5zz.YdytlA4diCC', 1, 1, 1, 1, 'ADMIN', '2019-08-02 03:57:18', NULL),
	(643, 'jhai.talingdan@personiv.com', 'Jhai Talingdan', 'MALE', '$2a$10$d7r5K6Y8osC9rJHMOSjsB.xY5WtzFjLb0JSKrMSbhpeAkuUWM3gzm', 1, 1, 1, 1, 'ADMIN', '2019-08-02 03:57:31', NULL),
	(644, 'mark.lapastora@personiv.com', 'Mark Lapastora', 'MALE', '$2a$10$bH1CZK6O5AGLdzyT.oXvzum8gUSs7cY.9/4r2xConDOIKUS1JJO0a', 1, 1, 1, 1, 'ADMIN', '2019-08-02 04:10:08', NULL),
	(647, 'srinivas@personiv.com', 'Srinivasa Rajan AR', 'MALE', '$2a$10$t5.d3lU6VBF4u3KoIVmJ2.qeuh78BCVDHwXh7yqCeXRqkUmcfrrgC', 1, 1, 1, 1, 'USER', '2019-08-02 21:42:44', NULL),
	(648, 'sureshjr.jesswani@gmail.com', 'Suresh Jr M. Jesswani', 'MALE', '$2a$10$xeFwhn970eqb624GUZ/vWuP0YVoWdLb5rhpian8v9jREZRHsuQeVG', 1, 1, 1, 1, 'USER', '2019-08-02 21:42:54', NULL),
	(649, 'kim.remigio@personiv.com', 'Michael Xavier Remigio', 'MALE', '$2a$10$Tm/2moynr4CCOwPuCY0nV.XsMKLTo5oUVAA9bbLW0WJP/rnXSXS5W', 1, 1, 1, 1, 'USER', '2019-08-02 21:46:57', NULL),
	(652, 'robbierobles91@gmail.com', 'Robbie L Robles', 'MALE', '$2a$10$m8b3Y6P8FLrkyJRG0qbj5eDEYCAqTNcOttVGZBu6D2L2npnn82una', 1, 1, 1, 1, 'USER', '2019-08-02 23:36:21', NULL),
	(653, 'kate_aguirre@yahoo.com', 'Katherine Anne Ricafort', 'FEMALE', '$2a$10$d7r5K6Y8osC9rJHMOSjsB.xY5WtzFjLb0JSKrMSbhpeAkuUWM3gzm', 1, 1, 1, 1, 'USER', '2019-08-03 02:41:51', NULL),
	(654, 'try@personiv.com', 'Joe Joe', 'MALE', '$2a$10$CWTldh9iUr5cUbfOMSukv.Rqj5ttjDjxF1XPtnt6cs0q5pGKbVo1O', 1, 1, 1, 1, 'USER', '2019-08-05 17:25:45', NULL),
	(655, 'argaop01@thryv.com', 'Joe Joe', 'MALE', '$2a$10$QVvNKUGEWxiZDnXPuEKTAuwM2DCtp4DPo7lqFKXpzUzOFhZAoAXUG', 1, 1, 1, 1, 'USER', '2019-08-15 09:53:31', NULL),
	(656, 'team.renell@gmail.com', 'dsw', 'MALE', '$2a$10$/y3gLVaZPc2fgb7w6nc.denwqlq1ouHtoM8zzdfSlMXOYN7qeRlp2', 1, 1, 1, 1, 'USER', '2019-08-28 01:01:30', NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table run_proud.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table run_proud.user_roles: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
