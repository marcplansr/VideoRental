-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 15, 2019 at 11:44 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `video_rental`
--
CREATE DATABASE IF NOT EXISTS `video_rental` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `video_rental`;

-- --------------------------------------------------------

--
-- Drop tables
--

DROP TABLE IF EXISTS `rental`;
DROP TABLE IF EXISTS `dvd`;
DROP TABLE IF EXISTS `film`;
DROP TABLE IF EXISTS `customer`;

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` binary(16) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surnames` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `bonus_points` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `surnames`, `email`, `phone`, `bonus_points`) VALUES
(0x02ef56bc9a4042979e71f68ce8ef5861, 'Aurora', 'Smith', 'aurorasmith@gmail.com', '934333558', 9),
(0x0cc8fe93a7f64ebfa03bb560d26adfe6, 'Ferran', 'Buch', 'ferranbuch@gmail.com', '475891184', 4),
(0x1a1006d51cb546fb8a3cc78bbbb70126, 'Enric', 'Negre', 'enricnegre@gmail.com', '786223726', 4),
(0x239e1b815dd34bd3afa00ea9a4c0181d, 'Joan', 'Garcia', 'joangarcia@gmail.com', '719468778', 6),
(0x2847c5320c474af9a95f597371eef165, 'Toni', 'Pérez', 'toniperez@gmail.com', '985793799', 4),
(0x473852ae3fc94aae8a7101599f9264ea, 'Maria', 'Pons', 'mariapons@gmail.com', '696849466', 6),
(0x4be98433df8449e6b03d088722bfe582, 'Laura', 'Martí', 'lauramarti@gmail.com', '715873458', 7),
(0x4c01bd6125794d55ad42258fbe6240f5, 'Antonio', 'Fernández', 'antoniofernandez@gmail.com', '876656454', 3),
(0x5c306c1e879345fc8adf2db347e8e28b, 'Mònica', 'Font', 'monicafont@gmail.com', '689519745', 6),
(0x6f2070306e78459c9af5d4237296e26a, 'Josep', 'López', 'joseplopez@gmail.com', '789742622', 6),
(0x779ca0502bed4b11a9475e10f7c8bce6, 'Marta', 'Roig', 'martaroig@gmail.com', '395612493', 3),
(0x8866adcfaf024e5ea514e9da1316dfc1, 'Jake', 'Hernández', 'jakehernandez@gmail.com', '234757546', 0),
(0x8921db60618f49f8bf237067e6176595, 'Susana', 'Zaragoza', 'susanazaragoza@gmail.com', '447457225', 5),
(0x8e713780ed6340b8839f11778dccb366, 'Guadalupe', 'Puente', 'guadalupepuente@gmail.com', '548261186', 3),
(0x929b6ba3a5924ea9bd6817ee46efbe2d, 'Jonathan', 'Sánchez', 'jonathansanchez@gmail.com', '987621762', 7),
(0xb1047afec0654672b1bb1f3ed7bd0064, 'Laura', 'Palmer', 'laurapalmer@gmail.com', '879657324', 0),
(0xbe9a4e321e3d42a2ba2e32a14497f0ed, 'Jordi', 'Santacana', 'jordisantacana@gmail.com', '939411196', 4),
(0xc1363ba0c4094dc8bde4a59e260411ee, 'Samantha', 'Fox', 'samanthafox@gmail.com', '672478966', 5),
(0xd0fe0b5427584535b4c661f661f69a97, 'Jesús', 'Moncada', 'jesusmoncada@gmail.com', '393578846', 4),
(0xeb9826f5f8fa4a0b9e06caa6bad632bb, 'Cristina', 'Fabregas', 'cristinafabregas@gmail.com', '666761252', 5),
(0xf1db8b010b504f70b57795b5d470df64, 'Jessica', 'Rosselló', 'jessicarossello@gmail.com', '693883825', 6),
(0xfd45f50f31994d54811ee03b1debe3ec, 'Alberto', 'Blanco', 'albertoblanco@gmail.com', '479542411', 5);

-- --------------------------------------------------------

--
-- Table structure for table `dvd`
--

CREATE TABLE `dvd` (
  `id` binary(16) NOT NULL,
  `film_id` binary(16) NOT NULL,
  `dvd_date` date NOT NULL,
  `status` enum('AVAILABLE','RENTED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dvd`
--

INSERT INTO `dvd` (`id`, `film_id`, `dvd_date`, `status`) VALUES
(0x077bf75df5fb47bf80e1b5bb0a12d56d, 0x3f91551bbd8f4b01a581cafb3095481a, '2011-06-23', 'AVAILABLE'),
(0x14a02800fd1f4d08bfd886e46c3f7b07, 0xe34d980ddf0b49bea33dce313917a832, '2019-06-28', 'RENTED'),
(0x1f696c01bb9446e2a347893971480c90, 0x35f9d53773ff4a1c915a8043cd442e8b, '2016-03-22', 'AVAILABLE'),
(0x2c054e467b58453481a7f6ddb8ea1ca3, 0x12853f53ce404573bb2889e985604d63, '2018-03-15', 'RENTED'),
(0x41e8b8ae0e864a9dbf770b0c1250bcdc, 0x12853f53ce404573bb2889e985604d63, '2018-03-15', 'RENTED'),
(0x41f91bd40f624c24ac344bf302dd70c4, 0x142f488369894cc5ae94bf7a862734d7, '2015-01-14', 'AVAILABLE'),
(0x43e1d83f5b9f497da23f1b6732aff653, 0x3e694a6e0bd64814bc14915cc03bc6c3, '2019-04-11', 'RENTED'),
(0x49ff9dc4f57b44628306e38cedabdc6c, 0x35f9d53773ff4a1c915a8043cd442e8b, '2016-03-22', 'AVAILABLE'),
(0x4a303926964e48128caacb048615c416, 0xa99062bb0f804fd4858beb5e77f3be25, '2002-05-25', 'AVAILABLE'),
(0x4fea8913a94f4c3a8e4cdb482581e8d4, 0xc4c6412de05b44969931d536c9669d44, '1999-12-06', 'AVAILABLE'),
(0x57621294fcbc4dec88ae9b6b86e42555, 0xa3bc4e8c018740dc8ffac1bf772f9f39, '2017-02-19', 'AVAILABLE'),
(0x5c3ab96afa784c81a17976249e1a4e3a, 0xa3bc4e8c018740dc8ffac1bf772f9f39, '2017-02-19', 'AVAILABLE'),
(0x6892e908311f4c1fb7d00867c493707f, 0x7ee179665d59414082ade7c878954c0c, '2019-07-27', 'RENTED'),
(0x69d4701d2623477db412c39a5f2c94c3, 0x52bf247af03b40fd84debf55d369d5c1, '2012-01-21', 'AVAILABLE'),
(0x6bfdeeffff2c4d2e9d9a76a2aa67fa3b, 0xe34d980ddf0b49bea33dce313917a832, '2019-06-28', 'RENTED'),
(0x6d31a2ab2ba04caca825853bfa512ff3, 0xb59038a1e10247fc897166881ff169db, '2005-11-02', 'AVAILABLE'),
(0x73f2d97d6dfd4b19bf22cd169ca153b2, 0x112d8072dfbb455d812788273404f692, '2019-01-07', 'RENTED'),
(0x785369310b5548ed9fa5c9240f4131ee, 0x5f15b1a062e8431d9ed748a9d92871c2, '2019-07-01', 'RENTED'),
(0x7b3e9115126d412e9a20f7b32fe3457c, 0x4e9f4cf63d834f16a9223d361652c5f8, '2007-07-07', 'AVAILABLE'),
(0x8d29f1d7ead54298a92e319cd0ca21f2, 0x52bf247af03b40fd84debf55d369d5c1, '2012-01-21', 'AVAILABLE'),
(0x9519d6ed76b446298db91e545caf3a6b, 0x5b33781746a94f2da2de4d042b5c74b0, '2015-09-27', 'AVAILABLE'),
(0x96ceb58ba5674b9d9478ca7fcf85dea1, 0x274e8326cc5e448db8ea92cefd20f457, '2003-01-17', 'AVAILABLE'),
(0x96df1f4c3eb54755bc2de90a7db22e79, 0xf05e4525d2204c7ebfdb95bb9c64df1a, '2019-02-12', 'RENTED'),
(0x9a70f5364ff0429d9a8803cf4b4077ed, 0x4e9f4cf63d834f16a9223d361652c5f8, '2009-12-10', 'AVAILABLE'),
(0x9b7565a960e949ae9c2d100fc87281b1, 0x5b33781746a94f2da2de4d042b5c74b0, '2015-09-27', 'AVAILABLE'),
(0xb08af9682a9e40df804875b9fdd50008, 0x3e694a6e0bd64814bc14915cc03bc6c3, '2019-04-11', 'RENTED'),
(0xb902d8a265aa49cb9b0685e257cf1f4d, 0xc9a135c28e5540128003b7ef15f50c0f, '2016-10-14', 'AVAILABLE'),
(0xc01c762a6b2d4560834f21fe5bcb0340, 0x112d8072dfbb455d812788273404f692, '2019-01-07', 'RENTED'),
(0xc0eea6b432424fcc9dae0fb917d8b4d7, 0xa3bc4e8c018740dc8ffac1bf772f9f39, '2017-02-19', 'RENTED'),
(0xe49ee2727bf84c229b003ff0204e4882, 0xf3d3d68faabd45418c4c19e00c6ff5c2, '2011-06-25', 'AVAILABLE'),
(0xf9477b95b906432b912aef35f3879746, 0x3f91551bbd8f4b01a581cafb3095481a, '2011-06-23', 'AVAILABLE'),
(0xfcc6a80a1b654552b3ddb77422a28dcc, 0x52bf247af03b40fd84debf55d369d5c1, '2007-07-07', 'AVAILABLE');

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `id` binary(16) NOT NULL,
  `title` varchar(45) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `director` varchar(45) NOT NULL,
  `type` enum('NEW_RELEASE','REGULAR_FILM','OLD_FILM','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`id`, `title`, `year`, `director`, `type`) VALUES
(0x112d8072dfbb455d812788273404f692, 'Annihilation', 2018, 'Alex Garland', 'NEW_RELEASE'),
(0x12853f53ce404573bb2889e985604d63, 'You Were Never Really Here', 2017, 'Lynne Ramsay', 'NEW_RELEASE'),
(0x142f488369894cc5ae94bf7a862734d7, 'Interstellar', 2014, 'Christopher Nolan', 'REGULAR_FILM'),
(0x274e8326cc5e448db8ea92cefd20f457, 'The Pianist', 2002, 'Roman Polanski', 'REGULAR_FILM'),
(0x35f9d53773ff4a1c915a8043cd442e8b, 'Gladiator', 2000, 'Ridley Scott', 'REGULAR_FILM'),
(0x3e694a6e0bd64814bc14915cc03bc6c3, 'The Ballad of Buster Scruggs', 2018, 'Ethan Coen, Joel Coen', 'NEW_RELEASE'),
(0x3f91551bbd8f4b01a581cafb3095481a, 'Midnight Cowboy', 1969, 'John Schlesinger', 'OLD_FILM'),
(0x4e9f4cf63d834f16a9223d361652c5f8, 'American Beauty', 1999, 'Sam Mendes', 'REGULAR_FILM'),
(0x52bf247af03b40fd84debf55d369d5c1, 'Fargo', 1996, 'Joel Coen, Ethan Coen', 'REGULAR_FILM'),
(0x5b33781746a94f2da2de4d042b5c74b0, 'Pulp Fiction', 1994, 'Quentin Tarantino', 'REGULAR_FILM'),
(0x5f15b1a062e8431d9ed748a9d92871c2, 'High Life', 2019, 'Claire Denis', 'NEW_RELEASE'),
(0x6abac0a11ef8459986ee943228cccac7, 'The Shining', 1980, 'Stanley Kubrick', 'OLD_FILM'),
(0x7ee179665d59414082ade7c878954c0c, 'Avengers: Endgame', 2019, 'Anthony Russo, Joe Russo', 'NEW_RELEASE'),
(0xa3bc4e8c018740dc8ffac1bf772f9f39, 'Metropolis', 1927, 'Fritz Lang', 'OLD_FILM'),
(0xa99062bb0f804fd4858beb5e77f3be25, 'Saving Private Ryan', 1998, 'Steven Spielberg', 'REGULAR_FILM'),
(0xb59038a1e10247fc897166881ff169db, 'City Lights', 1931, 'Charles Chaplin', 'OLD_FILM'),
(0xb8e684248bff4b73a0c39209f357ce00, 'Million Dollar Baby', 2004, 'Clint Esatwood', 'REGULAR_FILM'),
(0xc4c6412de05b44969931d536c9669d44, '2001: A Space Odyssey', 1968, 'Stanley Kubrick', 'OLD_FILM'),
(0xc9a135c28e5540128003b7ef15f50c0f, 'Lawrence of Arabia', 1962, 'David Lean', 'OLD_FILM'),
(0xe34d980ddf0b49bea33dce313917a832, 'Toy Story 4', 2019, ' Josh Cooley', 'NEW_RELEASE'),
(0xf05e4525d2204c7ebfdb95bb9c64df1a, 'Shoplifters', 2018, 'Hirokazu Koreeda', 'NEW_RELEASE'),
(0xf3d3d68faabd45418c4c19e00c6ff5c2, 'Citizen Kane', 1941, 'Orson Welles', 'OLD_FILM');

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `id` binary(16) NOT NULL,
  `dvd_id` binary(16) NOT NULL,
  `customer_id` binary(16) NOT NULL,
  `start_date` date NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `initial_payment` decimal(9,2) NOT NULL,
  `additional_payment` decimal(9,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`id`, `dvd_id`, `customer_id`, `start_date`, `due_date`, `return_date`, `initial_payment`, `additional_payment`) VALUES
(0x011729fc70104382bec97039bcb6c3e4, 0x41f91bd40f624c24ac344bf302dd70c4, 0x1a1006d51cb546fb8a3cc78bbbb70126, '2016-01-23', '2016-01-28', '2016-01-30', '5.00', '2.00'),
(0x04403aaa02e44803965ab269c7da2c43, 0x96ceb58ba5674b9d9478ca7fcf85dea1, 0x4be98433df8449e6b03d088722bfe582, '2012-03-21', '2012-03-26', '2012-03-30', '5.00', '4.00'),
(0x08320eadd1c447608b7dd681e4d1c5f4, 0x077bf75df5fb47bf80e1b5bb0a12d56d, 0x779ca0502bed4b11a9475e10f7c8bce6, '2017-12-04', '2017-12-09', '2017-12-10', '1.00', '1.00'),
(0x092345336732413a908eea6f2bd09f4c, 0x57621294fcbc4dec88ae9b6b86e42555, 0xbe9a4e321e3d42a2ba2e32a14497f0ed, '2017-11-21', '2017-11-26', '2017-11-26', '1.00', '0.00'),
(0x0e03b6031991496d830eca9e04512312, 0x43e1d83f5b9f497da23f1b6732aff653, 0x02ef56bc9a4042979e71f68ce8ef5861, '2019-06-08', '2019-06-11', '2019-06-12', '9.00', '3.00'),
(0x1508c6434efd4d84887e47a41d916d8a, 0x41e8b8ae0e864a9dbf770b0c1250bcdc, 0x6f2070306e78459c9af5d4237296e26a, '2019-08-12', '2019-08-17', NULL, '15.00', '0.00'),
(0x157b5759df5642188a39487e85e00460, 0xc0eea6b432424fcc9dae0fb917d8b4d7, 0x2847c5320c474af9a95f597371eef165, '2017-03-01', '2017-03-06', '2017-03-07', '1.00', '1.00'),
(0x15bf383293c74d01b7f1c665ab569171, 0x9b7565a960e949ae9c2d100fc87281b1, 0x6f2070306e78459c9af5d4237296e26a, '2019-05-23', '2019-05-28', '2019-05-28', '5.00', '0.00'),
(0x197510c89a274d46a908b6bc53cd8ed9, 0xb08af9682a9e40df804875b9fdd50008, 0x929b6ba3a5924ea9bd6817ee46efbe2d, '2019-08-10', '2019-08-15', NULL, '15.00', '0.00'),
(0x1ac5baa10e304e358bcb7b6cc08b9da4, 0x9a70f5364ff0429d9a8803cf4b4077ed, 0x0cc8fe93a7f64ebfa03bb560d26adfe6, '2018-08-22', '2018-08-25', '2018-08-27', '3.00', '2.00'),
(0x1f6fcc0f7f6144debd480c821d80107b, 0x9b7565a960e949ae9c2d100fc87281b1, 0x239e1b815dd34bd3afa00ea9a4c0181d, '2014-04-13', '2014-04-18', '2014-04-21', '5.00', '3.00'),
(0x203bd381d0e54571bf1332aba6fafa60, 0xc0eea6b432424fcc9dae0fb917d8b4d7, 0x929b6ba3a5924ea9bd6817ee46efbe2d, '2019-08-05', '2019-08-10', NULL, '1.00', '0.00'),
(0x22ddff85705f4b62914e19dc0cbd3cc6, 0xe49ee2727bf84c229b003ff0204e4882, 0xc1363ba0c4094dc8bde4a59e260411ee, '2018-04-05', '2018-04-10', '2018-04-10', '1.00', '0.00'),
(0x2cb91a8bc17244d8bfc13441ee9d8e31, 0x6892e908311f4c1fb7d00867c493707f, 0x929b6ba3a5924ea9bd6817ee46efbe2d, '2019-08-11', '2019-08-14', NULL, '9.00', '0.00'),
(0x2cdd1a831a6341af913465f0671cf556, 0x41f91bd40f624c24ac344bf302dd70c4, 0x779ca0502bed4b11a9475e10f7c8bce6, '2017-06-30', '2017-07-05', '2017-07-04', '5.00', '0.00'),
(0x2e0c89c831274b2e895762f19f9c385c, 0x5c3ab96afa784c81a17976249e1a4e3a, 0x6f2070306e78459c9af5d4237296e26a, '2017-04-15', '2017-04-21', '2017-04-23', '2.00', '2.00'),
(0x2f7f468e7d134954b50ff9aab95e80c5, 0x43e1d83f5b9f497da23f1b6732aff653, 0x239e1b815dd34bd3afa00ea9a4c0181d, '2019-08-11', '2019-08-14', NULL, '9.00', '0.00'),
(0x305441369acc41dcbc226729e13f626a, 0x077bf75df5fb47bf80e1b5bb0a12d56d, 0xeb9826f5f8fa4a0b9e06caa6bad632bb, '2014-12-12', '2014-12-17', '2014-12-19', '1.00', '2.00'),
(0x33132523a4194e8d8ec20e57694cf11b, 0x73f2d97d6dfd4b19bf22cd169ca153b2, 0xbe9a4e321e3d42a2ba2e32a14497f0ed, '2019-07-17', '2019-07-20', '2019-07-21', '9.00', '3.00'),
(0x38101fd0719e4a8295eac3fd620fae9e, 0x43e1d83f5b9f497da23f1b6732aff653, 0xf1db8b010b504f70b57795b5d470df64, '2019-07-01', '2019-07-05', '2019-07-04', '12.00', '0.00'),
(0x3d54cf92e92048679db93fc2aa87d75b, 0x49ff9dc4f57b44628306e38cedabdc6c, 0x929b6ba3a5924ea9bd6817ee46efbe2d, '2016-06-18', '2016-06-21', '2016-06-21', '3.00', '0.00'),
(0x3d6c44fd60e246a99966d020844dcb55, 0x6d31a2ab2ba04caca825853bfa512ff3, 0x8921db60618f49f8bf237067e6176595, '2015-07-14', '2015-07-19', '2015-07-21', '1.00', '2.00'),
(0x43dc4f5b29944b4ebc74c528d87d37aa, 0x785369310b5548ed9fa5c9240f4131ee, 0x1a1006d51cb546fb8a3cc78bbbb70126, '2019-07-17', '2019-07-21', '2019-07-21', '12.00', '0.00'),
(0x4846d44fce0e40518d0bbe200f2ab992, 0x96df1f4c3eb54755bc2de90a7db22e79, 0xd0fe0b5427584535b4c661f661f69a97, '2019-06-22', '2019-06-25', '2019-06-26', '9.00', '3.00'),
(0x499acc5b79c2473685a66fa66fca11e7, 0x4fea8913a94f4c3a8e4cdb482581e8d4, 0x473852ae3fc94aae8a7101599f9264ea, '2015-01-20', '2015-01-25', '2015-01-25', '1.00', '0.00'),
(0x501d0f9458e3470eb02a6d0ca9d4f4f2, 0x6d31a2ab2ba04caca825853bfa512ff3, 0xd0fe0b5427584535b4c661f661f69a97, '2016-01-12', '2016-01-17', '2016-01-19', '1.00', '2.00'),
(0x57ef0edbc47841978e99290133b85a3b, 0x2c054e467b58453481a7f6ddb8ea1ca3, 0x4be98433df8449e6b03d088722bfe582, '2018-11-22', '2018-11-25', '2018-11-27', '9.00', '6.00'),
(0x5e3c696ba26f498f8a701718d89cb677, 0x5c3ab96afa784c81a17976249e1a4e3a, 0x2847c5320c474af9a95f597371eef165, '2019-02-23', '2019-02-28', '2019-02-25', '1.00', '0.00'),
(0x62d303c9d335458da1c177a917b9fa04, 0x9a70f5364ff0429d9a8803cf4b4077ed, 0x8e713780ed6340b8839f11778dccb366, '2012-11-19', '2012-11-24', '2012-11-23', '5.00', '0.00'),
(0x6571ca24bff742ebbdb8d28027c37d56, 0x57621294fcbc4dec88ae9b6b86e42555, 0x779ca0502bed4b11a9475e10f7c8bce6, '2017-08-22', '2017-08-27', '2017-08-25', '1.00', '0.00'),
(0x65d78d98f04e494188ba5a78f901eee0, 0x6d31a2ab2ba04caca825853bfa512ff3, 0x5c306c1e879345fc8adf2db347e8e28b, '2018-04-25', '2018-04-30', '2018-04-29', '1.00', '0.00'),
(0x66c8d7d3986f4bf58c220f3252139e56, 0x41f91bd40f624c24ac344bf302dd70c4, 0xbe9a4e321e3d42a2ba2e32a14497f0ed, '2018-05-25', '2018-05-28', '2018-05-31', '3.00', '3.00'),
(0x67caf2bbf5cd4b618e4d1de82dbaff4e, 0x4fea8913a94f4c3a8e4cdb482581e8d4, 0x239e1b815dd34bd3afa00ea9a4c0181d, '2010-07-14', '2010-07-19', '2010-07-22', '1.00', '3.00'),
(0x690610f595ac43e38c7b8035f59a55f7, 0x4a303926964e48128caacb048615c416, 0x6f2070306e78459c9af5d4237296e26a, '2019-07-20', '2019-07-25', '2019-07-25', '5.00', '0.00'),
(0x6c7e15f8d45544268e88f4a976627f23, 0x9519d6ed76b446298db91e545caf3a6b, 0x5c306c1e879345fc8adf2db347e8e28b, '2019-02-23', '2019-02-27', '2019-02-25', '4.00', '0.00'),
(0x6f9805f5cec746e1aa2b92fc3d4ceea3, 0x6bfdeeffff2c4d2e9d9a76a2aa67fa3b, 0xeb9826f5f8fa4a0b9e06caa6bad632bb, '2019-07-05', '2019-07-08', '2019-07-10', '9.00', '6.00'),
(0x7042cb0fcaf24c2ba55f2498c91929e4, 0x9b7565a960e949ae9c2d100fc87281b1, 0x473852ae3fc94aae8a7101599f9264ea, '2015-05-27', '2015-05-30', '2015-06-02', '3.00', '3.00'),
(0x7a3b805d372849acb9931f58ca0fb8e5, 0x96ceb58ba5674b9d9478ca7fcf85dea1, 0x2847c5320c474af9a95f597371eef165, '2013-06-24', '2013-06-29', '2013-06-29', '5.00', '0.00'),
(0x7ca1c386762d4cfeb596fdbfa2031314, 0xf9477b95b906432b912aef35f3879746, 0x4c01bd6125794d55ad42258fbe6240f5, '2019-07-11', '2019-07-16', '2019-07-14', '1.00', '0.00'),
(0x8004ea89a0594b8c9999d9510128d69a, 0x96df1f4c3eb54755bc2de90a7db22e79, 0x5c306c1e879345fc8adf2db347e8e28b, '2019-07-03', '2019-07-07', '2019-07-08', '12.00', '3.00'),
(0x8083c0a075d44845b80e0ff392203a06, 0x49ff9dc4f57b44628306e38cedabdc6c, 0x8e713780ed6340b8839f11778dccb366, '2019-01-11', '2019-01-15', '2019-01-15', '4.00', '0.00'),
(0x853033bafcff48e38012320130544a6a, 0x69d4701d2623477db412c39a5f2c94c3, 0x4be98433df8449e6b03d088722bfe582, '2014-04-15', '2014-04-21', '2014-04-23', '6.00', '2.00'),
(0x8d34596e809d40fdb97c37d2110f6e17, 0x57621294fcbc4dec88ae9b6b86e42555, 0x8921db60618f49f8bf237067e6176595, '2019-07-11', '2019-07-16', '2019-07-14', '1.00', '0.00'),
(0x8d8c2dbb57a14b01996f7488026ad90c, 0xe49ee2727bf84c229b003ff0204e4882, 0xfd45f50f31994d54811ee03b1debe3ec, '2017-09-26', '2017-10-01', '2017-10-04', '1.00', '3.00'),
(0x94e6d9c3945d48d38253d6cbb4a68cb5, 0x4fea8913a94f4c3a8e4cdb482581e8d4, 0x6f2070306e78459c9af5d4237296e26a, '2018-01-20', '2018-01-25', '2018-01-26', '1.00', '1.00'),
(0x97b4907c7c7f42d58a5a0b2e26b20c1f, 0x96df1f4c3eb54755bc2de90a7db22e79, 0x02ef56bc9a4042979e71f68ce8ef5861, '2019-08-11', '2019-08-17', NULL, '18.00', '0.00'),
(0x9822a9afb67a4357b04154c175355a9d, 0x6bfdeeffff2c4d2e9d9a76a2aa67fa3b, 0x02ef56bc9a4042979e71f68ce8ef5861, '2019-08-12', '2019-08-16', NULL, '12.00', '0.00'),
(0x9bd6d50c45da42739ebf5655802c512e, 0xc01c762a6b2d4560834f21fe5bcb0340, 0xfd45f50f31994d54811ee03b1debe3ec, '2019-05-28', '2019-06-01', '2019-06-01', '12.00', '0.00'),
(0x9ff35bcb9f4b4173bf9b8543f3342851, 0x785369310b5548ed9fa5c9240f4131ee, 0x4be98433df8449e6b03d088722bfe582, '2019-08-05', '2019-08-10', NULL, '15.00', '0.00'),
(0xa31064d6ba5a46e794ea7b65a0b3a298, 0xc0eea6b432424fcc9dae0fb917d8b4d7, 0x02ef56bc9a4042979e71f68ce8ef5861, '2018-01-05', '2018-01-10', '2018-01-10', '1.00', '0.00'),
(0xa369607cf9f545a0a0cf3aec8934ca01, 0xc01c762a6b2d4560834f21fe5bcb0340, 0xf1db8b010b504f70b57795b5d470df64, '2019-08-12', '2019-08-14', NULL, '6.00', '0.00'),
(0xad38231a9911443fb5e26c7fbb53e828, 0x5c3ab96afa784c81a17976249e1a4e3a, 0x4be98433df8449e6b03d088722bfe582, '2018-09-07', '2018-09-12', '2018-09-14', '1.00', '2.00'),
(0xae6dc272d7ec41b2854194cf29e3bb9f, 0x96ceb58ba5674b9d9478ca7fcf85dea1, 0x02ef56bc9a4042979e71f68ce8ef5861, '2018-02-11', '2018-02-15', '2018-02-17', '4.00', '2.00'),
(0xb1bfd68941214ed4b2f7a05207f988fd, 0x41e8b8ae0e864a9dbf770b0c1250bcdc, 0x0cc8fe93a7f64ebfa03bb560d26adfe6, '2018-07-22', '2018-07-27', '2018-07-27', '15.00', '0.00'),
(0xb317a0e212f64e83bdc03be0eac6c0ca, 0xe49ee2727bf84c229b003ff0204e4882, 0x239e1b815dd34bd3afa00ea9a4c0181d, '2018-11-27', '2018-12-02', '2018-12-03', '1.00', '1.00'),
(0xb3fda2de4be2445e9fc46af29f501388, 0x9519d6ed76b446298db91e545caf3a6b, 0xd0fe0b5427584535b4c661f661f69a97, '2017-09-17', '2017-09-20', '2017-09-25', '3.00', '5.00'),
(0xb5017285742e4dc986ae78f85c2e67bb, 0x69d4701d2623477db412c39a5f2c94c3, 0x02ef56bc9a4042979e71f68ce8ef5861, '2019-02-23', '2019-02-27', '2019-02-25', '4.00', '0.00'),
(0xbb61f56cb98842bca60a178cc9aaad25, 0x8d29f1d7ead54298a92e319cd0ca21f2, 0x4c01bd6125794d55ad42258fbe6240f5, '2017-06-09', '2017-06-13', '2017-06-13', '4.00', '0.00'),
(0xc0546f0b801e42808c6260ad3b004bf1, 0x2c054e467b58453481a7f6ddb8ea1ca3, 0x473852ae3fc94aae8a7101599f9264ea, '2019-08-10', '2019-08-15', NULL, '15.00', '0.00'),
(0xc05cc376711f4335b7fb379e4960e4bb, 0xf9477b95b906432b912aef35f3879746, 0xf1db8b010b504f70b57795b5d470df64, '2011-08-17', '2011-08-22', '2011-08-20', '1.00', '0.00'),
(0xc14a88fdb9f04eedace700fefb6ea4a5, 0x1f696c01bb9446e2a347893971480c90, 0x8e713780ed6340b8839f11778dccb366, '2016-06-08', '2016-06-12', '2016-06-15', '4.00', '3.00'),
(0xc26baf40cc9540bdabd01d7325267225, 0x49ff9dc4f57b44628306e38cedabdc6c, 0x4c01bd6125794d55ad42258fbe6240f5, '2017-11-17', '2017-11-20', '2017-11-22', '3.00', '2.00'),
(0xc3dbd8bc39f5415b9c7b801abe7461ee, 0x14a02800fd1f4d08bfd886e46c3f7b07, 0xc1363ba0c4094dc8bde4a59e260411ee, '2019-07-15', '2019-07-18', '2019-07-18', '9.00', '0.00'),
(0xc8099fd40e3a4933b3b238bab7268ab2, 0xb902d8a265aa49cb9b0685e257cf1f4d, 0x239e1b815dd34bd3afa00ea9a4c0181d, '2018-07-08', '2018-07-13', '2018-07-12', '1.00', '0.00'),
(0xced9fea987184f7bb935097af256d355, 0xb08af9682a9e40df804875b9fdd50008, 0xc1363ba0c4094dc8bde4a59e260411ee, '2019-07-01', '2019-07-04', '2019-07-05', '9.00', '3.00'),
(0xcf641e4a5c1b448db801b0dd47cb296f, 0xb902d8a265aa49cb9b0685e257cf1f4d, 0x473852ae3fc94aae8a7101599f9264ea, '2019-07-20', '2019-07-25', '2019-07-25', '1.00', '0.00'),
(0xd75be1ca70264206880c14aea60ea7ee, 0x14a02800fd1f4d08bfd886e46c3f7b07, 0x5c306c1e879345fc8adf2db347e8e28b, '2019-08-10', '2019-08-14', NULL, '12.00', '0.00'),
(0xdac2c927e4724a8a9289edfe3ae20205, 0x41e8b8ae0e864a9dbf770b0c1250bcdc, 0xeb9826f5f8fa4a0b9e06caa6bad632bb, '2019-06-15', '2019-06-18', '2019-06-18', '9.00', '0.00'),
(0xe227f6a803744a81a6ed7025207e4865, 0x8d29f1d7ead54298a92e319cd0ca21f2, 0xf1db8b010b504f70b57795b5d470df64, '2012-08-11', '2012-08-14', '2012-08-16', '3.00', '2.00'),
(0xe33f181ed07d435bbc364af923e77f7f, 0x73f2d97d6dfd4b19bf22cd169ca153b2, 0x8921db60618f49f8bf237067e6176595, '2019-08-13', '2019-08-20', NULL, '21.00', '0.00'),
(0xe4b5d9c0dbae4369b83835a8fe5e2e82, 0xf9477b95b906432b912aef35f3879746, 0x929b6ba3a5924ea9bd6817ee46efbe2d, '2013-05-21', '2013-05-26', '2013-05-26', '1.00', '0.00'),
(0xe59775260c4741cc8aeb0e1ca5c106f4, 0x8d29f1d7ead54298a92e319cd0ca21f2, 0x929b6ba3a5924ea9bd6817ee46efbe2d, '2015-07-12', '2015-07-16', '2015-07-17', '4.00', '1.00'),
(0xe69780c8510e41ab81d981f7360965f7, 0x69d4701d2623477db412c39a5f2c94c3, 0x2847c5320c474af9a95f597371eef165, '2015-09-07', '2015-09-10', '2015-09-14', '3.00', '4.00'),
(0xe836016d51384d1ca7cf23fd9e3d3c48, 0x1f696c01bb9446e2a347893971480c90, 0x0cc8fe93a7f64ebfa03bb560d26adfe6, '2019-05-30', '2019-06-03', '2019-06-02', '4.00', '0.00'),
(0xe9cc702942bd41a5acaa9bec7c5e5082, 0x9519d6ed76b446298db91e545caf3a6b, 0x8921db60618f49f8bf237067e6176595, '2016-04-15', '2016-04-20', '2016-04-22', '5.00', '2.00'),
(0xead0ee934b76444c91677ae24149ed0a, 0x077bf75df5fb47bf80e1b5bb0a12d56d, 0x1a1006d51cb546fb8a3cc78bbbb70126, '2015-09-17', '2015-09-22', '2015-09-23', '1.00', '1.00'),
(0xefd194b47a1447698b464b4775e12369, 0x14a02800fd1f4d08bfd886e46c3f7b07, 0xfd45f50f31994d54811ee03b1debe3ec, '2019-07-01', '2019-07-05', '2019-07-06', '12.00', '3.00'),
(0xf2237634a7f04ca19545407fd79d9d2b, 0x4a303926964e48128caacb048615c416, 0x473852ae3fc94aae8a7101599f9264ea, '2018-07-08', '2018-07-12', '2018-07-12', '4.00', '0.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dvd`
--
ALTER TABLE `dvd`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_film_id` (`film_id`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_dvd_id` (`dvd_id`),
  ADD KEY `FK_customer_id` (`customer_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dvd`
--
ALTER TABLE `dvd`
  ADD CONSTRAINT `FK_film_id` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_dvd_id` FOREIGN KEY (`dvd_id`) REFERENCES `dvd` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
