-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 21 Nov 2023 pada 20.16
-- Versi server: 10.5.20-MariaDB
-- Versi PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id21485821_dn_tama_lyfe`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_project`
--

CREATE TABLE `tb_project` (
  `identifier_project` int(10) NOT NULL,
  `name_project` char(60) NOT NULL,
  `type_project` char(10) NOT NULL,
  `access_project` char(120) NOT NULL,
  `status_project` char(30) NOT NULL,
  `location_project` char(80) NOT NULL,
  `price_list_project_cash` int(20) NOT NULL,
  `price_list_project_credit` int(10) NOT NULL,
  `promo` int(20) NOT NULL,
  `description_project` varchar(160) NOT NULL,
  `bedroom` int(10) NOT NULL,
  `bathroom` int(10) NOT NULL,
  `carport` int(10) NOT NULL,
  `building_area` int(10) NOT NULL,
  `surface_area` int(10) NOT NULL,
  `facility` varchar(160) NOT NULL,
  `name_developer` char(60) NOT NULL,
  `contact_developer` char(30) NOT NULL,
  `loan_bank` char(20) NOT NULL,
  `handover` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `tb_project`
--

INSERT INTO `tb_project` (`identifier_project`, `name_project`, `type_project`, `access_project`, `status_project`, `location_project`, `price_list_project_cash`, `price_list_project_credit`, `promo`, `description_project`, `bedroom`, `bathroom`, `carport`, `building_area`, `surface_area`, `facility`, `name_developer`, `contact_developer`, `loan_bank`, `handover`) VALUES
(11, 'npa', 'tpa', 'https://docs.google.com/document/d/1OQz4AIfseF1cw0POH1ROWavzxcJpVQwGKMMdO1uHplU/edit?usp=sharing', 'Status Project Ready', 'lpa', 1111, 11, 111, 'dpa', 11, 11, 11, 11, 11, 'fa', 'nda', '1111111111', 'lba', 11),
(22, 'npb', 'tpb', 'acb', 'Status Project Indent', 'lpb', 2222, 22, 22, 'dpb', 22, 22, 22, 22, 22, 'fb', 'ndb', '222222222', 'lbb', 22),
(33, 'Mansion Hill Mekarwangi', 'Rumah 2', 'https://drive.google.com/drive/folders/1tz7ktXdHCvwJuMN3WGmsUbi9JzS0d6BB?usp=drive_link', 'Status Project Indent', 'Cikarang Barat', 4444, 44, 444, 'dpcd', 4, 4, 4, 4, 4, 'fd', 'nd', '444444', 'BJB', 4);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_project`
--
ALTER TABLE `tb_project`
  ADD PRIMARY KEY (`identifier_project`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
