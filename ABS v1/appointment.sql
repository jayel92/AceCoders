-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2015 at 11:37 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `appointment`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointmentdental`
--

CREATE TABLE IF NOT EXISTS `appointmentdental` (
`AppointmentNo` int(20) NOT NULL,
  `PatientNRIC` varchar(9) NOT NULL,
  `Description` text NOT NULL,
  `Date` date NOT NULL,
  `Time` time(6) NOT NULL,
  `Type` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `appointmentent`
--

CREATE TABLE IF NOT EXISTS `appointmentent` (
`AppointmentNo` int(20) NOT NULL,
  `PatientNRIC` varchar(9) NOT NULL,
  `Description` text NOT NULL,
  `Date` date NOT NULL,
  `Time` time(6) NOT NULL,
  `Type` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `appointmentwomen`
--

CREATE TABLE IF NOT EXISTS `appointmentwomen` (
`AppointmentNo` int(20) NOT NULL,
  `PatientNRIC` varchar(9) NOT NULL,
  `Description` text NOT NULL,
  `Date` date NOT NULL,
  `Time` time(6) NOT NULL,
  `Type` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `NRIC` varchar(9) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Password` varchar(12) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Mailing Address` varchar(200) NOT NULL,
  `Race` int(2) NOT NULL,
  `ContactNumber` int(20) NOT NULL,
  `AppointmentNoDental` int(20) DEFAULT NULL,
  `AppointmentNoENT` int(20) DEFAULT NULL,
  `AppointmentNoWomen` int(20) DEFAULT NULL,
  `ModeOfCommunication` int(1) NOT NULL,
  `DateOfBirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointmentdental`
--
ALTER TABLE `appointmentdental`
 ADD PRIMARY KEY (`AppointmentNo`), ADD UNIQUE KEY `PatientNRIC` (`PatientNRIC`);

--
-- Indexes for table `appointmentent`
--
ALTER TABLE `appointmentent`
 ADD PRIMARY KEY (`AppointmentNo`), ADD UNIQUE KEY `PatientNRIC` (`PatientNRIC`);

--
-- Indexes for table `appointmentwomen`
--
ALTER TABLE `appointmentwomen`
 ADD PRIMARY KEY (`AppointmentNo`), ADD UNIQUE KEY `PatientNRIC` (`PatientNRIC`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`NRIC`), ADD UNIQUE KEY `NRIC` (`NRIC`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointmentdental`
--
ALTER TABLE `appointmentdental`
MODIFY `AppointmentNo` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `appointmentent`
--
ALTER TABLE `appointmentent`
MODIFY `AppointmentNo` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `appointmentwomen`
--
ALTER TABLE `appointmentwomen`
MODIFY `AppointmentNo` int(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
