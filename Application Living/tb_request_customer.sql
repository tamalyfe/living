-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 14 Nov 2023 pada 05.40
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
(11, 'NC1', '6281253394470', 'DC1', 'DRC1', 'LP1', 1111, 11, 'Status Project Ready');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_request_customer`
--
ALTER TABLE `tb_request_customer`
  ADD PRIMARY KEY (`identifier_request_customer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
