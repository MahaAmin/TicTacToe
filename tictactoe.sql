-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 16, 2020 at 09:52 AM
-- Server version: 8.0.17
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tictactoe`
--

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `id` int(11) NOT NULL,
  `from_player` int(11) NOT NULL,
  `to_player` int(11) NOT NULL,
  `winner` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('REQUEST','COMPLETE','INPROGRESS','FAIL','PAUSE','') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'REQUEST',
  `board` text CHARACTER SET utf8 COLLATE utf8_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`id`, `from_player`, `to_player`, `winner`, `created_at`, `status`, `board`) VALUES
(19, 1, 2, NULL, '2020-01-14 04:58:11', 'REQUEST', NULL),
(20, 1, 2, NULL, '2020-01-14 05:19:36', 'REQUEST', NULL),
(21, 1, 2, NULL, '2020-01-14 05:24:04', 'REQUEST', NULL),
(22, 1, 2, NULL, '2020-01-14 05:25:54', 'REQUEST', NULL),
(23, 1, 2, NULL, '2020-01-14 05:28:20', 'REQUEST', NULL),
(24, 2, 2, NULL, '2020-01-14 05:28:31', 'REQUEST', NULL),
(25, 1, 2, NULL, '2020-01-14 05:32:46', 'REQUEST', NULL),
(26, 1, 2, NULL, '2020-01-14 05:33:18', 'REQUEST', NULL),
(27, 1, 2, NULL, '2020-01-14 05:33:59', 'REQUEST', NULL),
(28, 1, 2, NULL, '2020-01-14 05:35:48', 'REQUEST', NULL),
(29, 1, 2, NULL, '2020-01-14 13:08:57', 'FAIL', NULL),
(30, 1, 2, NULL, '2020-01-14 13:09:46', 'FAIL', NULL),
(31, 1, 2, NULL, '2020-01-14 13:10:19', 'FAIL', NULL),
(33, 1, 2, NULL, '2020-01-14 13:15:32', 'FAIL', NULL),
(34, 1, 2, NULL, '2020-01-14 13:19:31', 'FAIL', NULL),
(35, 1, 2, NULL, '2020-01-14 13:28:54', 'REQUEST', NULL),
(36, 1, 2, NULL, '2020-01-14 13:29:53', 'REQUEST', NULL),
(37, 1, 2, NULL, '2020-01-14 13:32:08', 'REQUEST', NULL),
(38, 1, 2, NULL, '2020-01-14 13:33:56', 'REQUEST', NULL),
(39, 1, 2, NULL, '2020-01-14 14:13:46', 'FAIL', NULL),
(40, 1, 2, NULL, '2020-01-14 14:14:32', 'FAIL', NULL),
(41, 2, 2, NULL, '2020-01-14 14:28:05', 'FAIL', NULL),
(43, 1, 2, NULL, '2020-01-14 15:08:26', 'REQUEST', NULL),
(44, 3, 2, NULL, '2020-01-14 15:13:51', 'REQUEST', NULL),
(45, 3, 2, NULL, '2020-01-14 15:15:26', 'REQUEST', NULL),
(46, 3, 2, NULL, '2020-01-14 15:16:12', 'REQUEST', NULL),
(47, 3, 2, NULL, '2020-01-14 15:16:13', 'REQUEST', NULL),
(48, 3, 2, NULL, '2020-01-14 15:16:16', 'REQUEST', NULL),
(49, 3, 2, NULL, '2020-01-14 15:16:16', 'REQUEST', NULL),
(50, 3, 2, NULL, '2020-01-14 15:16:17', 'REQUEST', NULL),
(51, 3, 2, NULL, '2020-01-14 15:16:17', 'REQUEST', NULL),
(52, 3, 2, NULL, '2020-01-14 15:16:17', 'REQUEST', NULL),
(53, 3, 2, NULL, '2020-01-14 15:16:18', 'REQUEST', NULL),
(54, 3, 2, NULL, '2020-01-14 15:16:18', 'REQUEST', NULL),
(55, 3, 2, NULL, '2020-01-14 15:16:18', 'REQUEST', NULL),
(56, 3, 2, NULL, '2020-01-14 15:16:18', 'REQUEST', NULL),
(57, 3, 2, NULL, '2020-01-14 15:16:19', 'REQUEST', NULL),
(58, 3, 2, NULL, '2020-01-14 15:16:19', 'REQUEST', NULL),
(59, 3, 2, NULL, '2020-01-14 15:16:21', 'REQUEST', NULL),
(60, 2, 2, NULL, '2020-01-14 15:16:21', 'REQUEST', NULL),
(61, 3, 2, NULL, '2020-01-14 15:16:21', 'REQUEST', NULL),
(62, 3, 2, NULL, '2020-01-14 15:16:21', 'REQUEST', NULL),
(63, 3, 2, NULL, '2020-01-14 15:16:21', 'REQUEST', NULL),
(64, 2, 2, NULL, '2020-01-14 15:16:24', 'REQUEST', NULL),
(65, 2, 2, NULL, '2020-01-14 15:16:24', 'REQUEST', NULL),
(66, 2, 2, NULL, '2020-01-14 15:16:25', 'REQUEST', NULL),
(67, 2, 2, NULL, '2020-01-14 15:16:25', 'REQUEST', NULL),
(68, 2, 2, NULL, '2020-01-14 15:16:27', 'REQUEST', NULL),
(69, 2, 2, NULL, '2020-01-14 15:16:28', 'REQUEST', NULL),
(70, 2, 2, NULL, '2020-01-14 15:16:35', 'REQUEST', NULL),
(71, 2, 2, NULL, '2020-01-14 15:16:35', 'REQUEST', NULL),
(72, 2, 2, NULL, '2020-01-14 15:16:36', 'REQUEST', NULL),
(73, 2, 2, NULL, '2020-01-14 15:16:36', 'REQUEST', NULL),
(74, 2, 2, NULL, '2020-01-14 15:31:53', 'REQUEST', NULL),
(75, 1, 2, NULL, '2020-01-14 15:31:54', 'REQUEST', NULL),
(76, 1, 2, NULL, '2020-01-14 15:34:41', 'REQUEST', NULL),
(77, 1, 2, NULL, '2020-01-14 16:20:57', 'REQUEST', NULL),
(78, 1, 2, NULL, '2020-01-14 16:21:49', 'REQUEST', NULL),
(79, 1, 2, NULL, '2020-01-16 00:08:38', 'REQUEST', NULL),
(80, 2, 1, NULL, '2020-01-16 00:27:44', 'FAIL', NULL),
(81, 1, 2, NULL, '2020-01-16 00:30:12', 'REQUEST', NULL),
(82, 1, 2, NULL, '2020-01-16 00:46:56', 'REQUEST', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int(11) DEFAULT '0',
  `status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `name`, `password`, `email`, `score`, `status`, `avatar`) VALUES
(1, 'ahmed', '1', 'a', 250, '1', NULL),
(2, 'halim', '1', 'h', 0, '1', NULL),
(3, 'salah', '123', 'salah@gmail.com', 300, '1', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`),
  ADD KEY `player1` (`from_player`),
  ADD KEY `player2` (`to_player`),
  ADD KEY `winner` (`winner`);

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `games_ibfk_1` FOREIGN KEY (`from_player`) REFERENCES `players` (`id`),
  ADD CONSTRAINT `games_ibfk_2` FOREIGN KEY (`to_player`) REFERENCES `players` (`id`),
  ADD CONSTRAINT `games_ibfk_3` FOREIGN KEY (`winner`) REFERENCES `players` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
