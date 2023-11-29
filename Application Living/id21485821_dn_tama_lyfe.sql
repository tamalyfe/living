-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 29 Nov 2023 pada 08.39
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
(11, 'npa', 'tya', 'apa', 'Status Project Ready', 'lpa', 11, 11, 11, 'dpa', 1, 1, 1, 1, 1, 'fa', 'nda', '11', 'Bank Mandiri', 1),
(22, 'npc', 'tpc', 'Google Drive', 'Status Project Indent', 'lpc', 223, 223, 223, 'dpbc', 2, 2, 2, 2, 2, 'fbc', 'ndbc', '22', 'BTN', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_recruitment_team`
--

CREATE TABLE `tb_recruitment_team` (
  `identifier_recruitment_team` int(10) NOT NULL,
  `name_team` char(60) NOT NULL,
  `post_team` char(80) NOT NULL,
  `domicile_team` char(20) NOT NULL,
  `job_description` char(30) NOT NULL,
  `experience` varchar(160) NOT NULL,
  `certificate` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `tb_recruitment_team`
--

INSERT INTO `tb_recruitment_team` (`identifier_recruitment_team`, `name_team`, `post_team`, `domicile_team`, `job_description`, `experience`, `certificate`) VALUES
(11, 'NTAA', 'Babelan', 'Bekasi', 'Digital Marketing', 'ERTAA', 'CA'),
(22, 'NTBB', 'Cibitung', 'Jakarta', 'Contributor', 'ERTBB', 'CB');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_request_customer`
--

CREATE TABLE `tb_request_customer` (
  `identifier_request_customer` int(10) NOT NULL,
  `name_customer` char(60) NOT NULL,
  `contact_customer` char(30) NOT NULL,
  `domicile_customer` char(20) NOT NULL,
  `description_request_customer` varchar(160) NOT NULL,
  `location_project` char(80) NOT NULL,
  `price_list_project_cash` int(20) NOT NULL,
  `price_list_project_credit` int(10) NOT NULL,
  `status_project` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data untuk tabel `tb_request_customer`
--

INSERT INTO `tb_request_customer` (`identifier_request_customer`, `name_customer`, `contact_customer`, `domicile_customer`, `description_request_customer`, `location_project`, `price_list_project_cash`, `price_list_project_credit`, `status_project`) VALUES
(11, 'ncaabb', '9876543210', 'Depok', 'drcaabb', 'Cikarang Barat', 1111222, 1122, 'Status Project Indent'),
(22, 'ncbb', '08123456789', 'Tangerang', 'drcbb', 'Mustika Jaya', 2222, 22, 'Status Project Indent');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_project`
--
ALTER TABLE `tb_project`
  ADD PRIMARY KEY (`identifier_project`);

--
-- Indeks untuk tabel `tb_recruitment_team`
--
ALTER TABLE `tb_recruitment_team`
  ADD PRIMARY KEY (`identifier_recruitment_team`);

--
-- Indeks untuk tabel `tb_request_customer`
--
ALTER TABLE `tb_request_customer`
  ADD PRIMARY KEY (`identifier_request_customer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
