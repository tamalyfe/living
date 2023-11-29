-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 19 Nov 2023 pada 07.37
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
(11, 'NTCC', 'Cikarang Barat', 'Bogor', 'Job Description Contributor', 'ERTCC', 'CCC'),
(22, 'NTBB', 'Babelan', 'Bekasi', 'Job Description Contributor', 'ERTBB', 'CBB');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_recruitment_team`
--
ALTER TABLE `tb_recruitment_team`
  ADD PRIMARY KEY (`identifier_recruitment_team`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
