-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Трв 09 2025 р., 15:48
-- Версія сервера: 10.4.32-MariaDB
-- Версія PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `concertportal`
--

-- --------------------------------------------------------

--
-- Структура таблиці `administrator`
--

CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pass_word` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `city` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `concert`
--

CREATE TABLE `concert` (
  `id` int(11) NOT NULL,
  `concert_name` varchar(100) NOT NULL,
  `artist` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `fk_id_location` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `fk_id_region` int(11) NOT NULL,
  `fk_id_city` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Структура таблиці `region`
--

CREATE TABLE `region` (
  `id` int(11) NOT NULL,
  `region` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Індекси збережених таблиць
--

--
-- Індекси таблиці `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Індекси таблиці `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `city` (`city`);

--
-- Індекси таблиці `concert`
--
ALTER TABLE `concert`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_location` (`fk_id_location`);

--
-- Індекси таблиці `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_region` (`fk_id_region`),
  ADD KEY `fk_id_city` (`fk_id_city`);

--
-- Індекси таблиці `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `region` (`region`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `administrator`
--
ALTER TABLE `administrator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `concert`
--
ALTER TABLE `concert`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблиці `region`
--
ALTER TABLE `region`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `concert`
--
ALTER TABLE `concert`
  ADD CONSTRAINT `concert_ibfk_1` FOREIGN KEY (`fk_id_location`) REFERENCES `location` (`id`);

--
-- Обмеження зовнішнього ключа таблиці `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`fk_id_region`) REFERENCES `region` (`id`),
  ADD CONSTRAINT `location_ibfk_2` FOREIGN KEY (`fk_id_city`) REFERENCES `city` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
