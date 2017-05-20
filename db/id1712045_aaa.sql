-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 20, 2017 at 09:30 AM
-- Server version: 10.1.20-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id1712045_aaa`
--

-- --------------------------------------------------------

--
-- Table structure for table `cources`
--

CREATE TABLE `cources` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `center_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `img` varchar(100) CHARACTER SET utf8 NOT NULL,
  `description` varchar(300) CHARACTER SET utf8 NOT NULL,
  `hour` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cources`
--

INSERT INTO `cources` (`ID`, `Name`, `center_id`, `type`, `img`, `description`, `hour`) VALUES
(1, 'java', 1, 1, 'java', 'java', 0);

-- --------------------------------------------------------

--
-- Table structure for table `course_topic`
--

CREATE TABLE `course_topic` (
  `ID` int(11) NOT NULL,
  `txt` varchar(200) CHARACTER SET utf8 NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_topic`
--

INSERT INTO `course_topic` (`ID`, `txt`, `course_id`) VALUES
(1, 'javajavajava', 1);

-- --------------------------------------------------------

--
-- Table structure for table `edu_centers`
--

CREATE TABLE `edu_centers` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Adrress` varchar(300) CHARACTER SET utf8 NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `edu_centers`
--

INSERT INTO `edu_centers` (`ID`, `Name`, `Adrress`, `latitude`, `longitude`, `type`) VALUES
(1, 'fdfdfd', 'fdfdfd', 100.5, 200.505, 1);

-- --------------------------------------------------------

--
-- Table structure for table `edu_center_type`
--

CREATE TABLE `edu_center_type` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `edu_center_type`
--

INSERT INTO `edu_center_type` (`ID`, `Name`) VALUES
(3, 'academic'),
(1, 'educational'),
(2, 'vocational');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `unique_id` varchar(23) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `age` int(11) NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `salt` varchar(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `unique_id`, `name`, `email`, `age`, `phone`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES
(1, '591f10caa00a45.05946725', 'ali', 'ali@gmail.com', 18, '123456', 'U0oVz+6dRth+AcS/jMipFeAhclEzYmQ3ZTEzNGVk', '3bd7e134ed', '2017-05-19 15:35:38', NULL),
(2, '591faa2d6d3827.09621035', 'fff', 'ff@fff.com', 18, '58585528', '38C8u+MEp5P/Sewa5SQ1sNyrKgswYWRiMTk3Y2Jh', '0adb197cba', '2017-05-20 02:30:05', NULL),
(3, '591fc4535a27b0.05218661', 'saleh', 's@hotmail.com', 50, '123456', '4FmGDO1GJOfUgOLM23PtkIhSlillMGRlZjU5MGU3', 'e0def590e7', '2017-05-20 04:21:39', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cources`
--
ALTER TABLE `cources`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `type` (`type`),
  ADD KEY `center_id` (`center_id`);

--
-- Indexes for table `course_topic`
--
ALTER TABLE `course_topic`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `edu_centers`
--
ALTER TABLE `edu_centers`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `type` (`type`);

--
-- Indexes for table `edu_center_type`
--
ALTER TABLE `edu_center_type`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cources`
--
ALTER TABLE `cources`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `course_topic`
--
ALTER TABLE `course_topic`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `edu_centers`
--
ALTER TABLE `edu_centers`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `edu_center_type`
--
ALTER TABLE `edu_center_type`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cources`
--
ALTER TABLE `cources`
  ADD CONSTRAINT `cources_ibfk_1` FOREIGN KEY (`center_id`) REFERENCES `edu_centers` (`ID`),
  ADD CONSTRAINT `cources_ibfk_2` FOREIGN KEY (`type`) REFERENCES `edu_center_type` (`ID`);

--
-- Constraints for table `course_topic`
--
ALTER TABLE `course_topic`
  ADD CONSTRAINT `course_topic_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `cources` (`ID`);

--
-- Constraints for table `edu_centers`
--
ALTER TABLE `edu_centers`
  ADD CONSTRAINT `edu_centers_ibfk_1` FOREIGN KEY (`type`) REFERENCES `edu_center_type` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
