-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 23, 2023 at 10:23 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `capstone`
--

-- --------------------------------------------------------

--
-- Table structure for table `recruitment_team_marketing`
--

CREATE TABLE `recruitment_team_marketing` (
  `id_requitment_team_marketing` int(11) NOT NULL,
  `name_project` char(20) NOT NULL,
  `type_project` char(20) NOT NULL,
  `lokasi_project` char(30) NOT NULL,
  `description_project` varchar(200) NOT NULL,
  `price_list_cash` int(11) NOT NULL,
  `price_list_credit` int(11) NOT NULL,
  `status` enum('ready','indent') NOT NULL,
  `building_area` int(11) NOT NULL,
  `surface_area` int(11) NOT NULL,
  `bedroom` int(11) NOT NULL,
  `bathroom` int(11) NOT NULL,
  `carport` int(11) NOT NULL,
  `promo` int(11) NOT NULL,
  `facility` char(100) NOT NULL,
  `contact` text NOT NULL,
  `name_developer` char(30) NOT NULL,
  `loan_bank` char(30) NOT NULL,
  `handover` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recruitment_team_marketing`
--

INSERT INTO `recruitment_team_marketing` (`id_requitment_team_marketing`, `name_project`, `type_project`, `lokasi_project`, `description_project`, `price_list_cash`, `price_list_credit`, `status`, `building_area`, `surface_area`, `bedroom`, `bathroom`, `carport`, `promo`, `facility`, `contact`, `name_developer`, `loan_bank`, `handover`) VALUES
(1, 'Michelia Randu', 'Rumah 1', 'Babelan', 'Rumah Baru Paling Laris di Babelan', 345000, 465000, 'indent', 36, 60, 2, 1, 1, 65000, 'Listrik : 1300W \r\nAir : Sumur Bor\r\nFree : AC', 'wa.me/p/5612756985505341/6288976846898', 'PT. MALAIKA SANTARA ABADI', 'BTN', 3),
(3, 'a', 'a', 'a', 'a', 1, 1, 'ready', 1, 1, 1, 1, 1, 1, 'a', 'a', 'a', 'a', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` char(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `alamat` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `alamat`) VALUES
(1, 'yunus', 'yunus', 'yunus');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `recruitment_team_marketing`
--
ALTER TABLE `recruitment_team_marketing`
  ADD PRIMARY KEY (`id_requitment_team_marketing`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `recruitment_team_marketing`
--
ALTER TABLE `recruitment_team_marketing`
  MODIFY `id_requitment_team_marketing` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
