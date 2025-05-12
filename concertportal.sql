-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Час створення: Трв 12 2025 р., 10:56
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

--
-- Дамп даних таблиці `administrator`
--

INSERT INTO `administrator` (`id`, `email`, `pass_word`) VALUES
(1, 'admin1@gmail.com', 'access!1');

-- --------------------------------------------------------

--
-- Структура таблиці `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `city`
--

INSERT INTO `city` (`id`, `name`) VALUES
(12, 'Ancona'),
(2, 'Aosta'),
(17, 'Bari'),
(9, 'Bologna'),
(5, 'Bolzano'),
(21, 'Cagliari'),
(15, 'Campobasso'),
(19, 'Catanzaro'),
(10, 'Firenze'),
(8, 'Genova'),
(14, 'L\'Aquila'),
(3, 'Milano'),
(16, 'Napoli'),
(20, 'Palermo'),
(11, 'Perugia'),
(18, 'Potenza'),
(13, 'Roma'),
(1, 'Torino'),
(4, 'Trento'),
(7, 'Trieste'),
(6, 'Venezia');

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

--
-- Дамп даних таблиці `concert`
--

INSERT INTO `concert` (`id`, `concert_name`, `artist`, `date`, `genre`, `fk_id_location`, `description`, `foto`) VALUES
(2, 'The Eras Tour', 'Taylor Swift', '2025-06-21 20:30:00', 'Pop', 1, 'Uno show epico che ripercorre tutte le ere musicali di Taylor Swift.', 'taylor_eras.jpg'),
(3, 'Justice World Tour', 'Justin Bieber', '2025-07-14 21:00:00', 'Pop', 2, 'Justin Bieber in tour con i successi di \"Justice\" e altri brani celebri.', 'bieber_justice.jpg'),
(4, 'Renaissance World Tour', 'Beyoncé', '2025-08-01 20:45:00', 'R&B', 3, 'Beyoncé porta sul palco uno spettacolo visuale e sonoro di grande impatto.', 'beyonce_renaissance.jpg'),
(5, 'Happier Than Ever Tour', 'Billie Eilish', '2025-06-27 21:30:00', 'Alternative', 4, 'Billie Eilish live con i brani dell\'album \"Happier Than Ever\".', 'billie_happier.jpg'),
(6, 'Divide Tour', 'Ed Sheeran', '2025-09-10 20:00:00', 'Pop-Folk', 5, 'Uno spettacolo intimo ed emozionante con Ed Sheeran.', 'edsheeran_divide.jpg'),
(7, 'Music of the Spheres', 'Coldplay', '2025-10-05 21:15:00', 'Alternative Rock', 6, 'Colori, energia e messaggi universali nel tour dei Coldplay.', 'coldplay_mots.jpg'),
(8, 'Rock Believer Tour', 'Scorpions', '2025-08-18 20:00:00', 'Rock', 7, 'I leggendari Scorpions dal vivo con i loro classici.', 'scorpions_rock.jpg'),
(9, 'End of the Road Tour', 'KISS', '2025-07-30 20:30:00', 'Hard Rock', 8, 'KISS saluta i fan con un tour d\'addio pieno di effetti speciali.', 'kiss_end.jpg'),
(10, 'After Hours Til Dawn Tour', 'The Weeknd', '2025-09-22 21:00:00', 'R&B/Pop', 9, 'The Weeknd presenta i suoi ultimi successi in un concerto spettacolare.', 'weeknd_afterhours.jpg'),
(11, 'Maestro in Concert', 'Ludovico Einaudi', '2025-11-01 19:00:00', 'Classica Contemporanea', 10, 'Pianoforte ed emozione pura con Ludovico Einaudi.', 'einaudi_maestro.jpg');

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

--
-- Дамп даних таблиці `location`
--

INSERT INTO `location` (`id`, `fk_id_region`, `fk_id_city`, `name`, `address`, `photo`) VALUES
(1, 19, 2, 'Teatro Romano di Aosta', 'Via Porta Pretoria, 11100', 'https://example.com/foto/aosta.jpg'),
(2, 12, 1, 'Pala Alpitour', 'Corso Sebastopoli, 123, 10134', 'https://example.com/foto/torino.jpg'),
(3, 8, 8, 'Arena del Mare', 'Calata Gadda, 16126', 'https://example.com/foto/genova.jpg'),
(4, 9, 3, 'Mediolanum Forum', 'Via Giuseppe di Vittorio, 6, 20090, Assago', 'https://example.com/foto/milano.jpg'),
(5, 17, 4, 'Auditorium Santa Chiara', 'Via Santa Croce, 67, 38122', 'https://example.com/foto/trento.jpg'),
(6, 20, 6, 'Parco San Giuliano', 'Via Orlanda, 30173, Mestre', 'https://example.com/foto/venezia.jpg'),
(7, 6, 7, 'Teatro Verdi', 'Riva Tre Novembre, 1, 34121', 'https://example.com/foto/trieste.jpg'),
(8, 5, 9, 'Arena Parco Nord', 'Via Stalingrado, 81, 40128', 'https://example.com/foto/bologna.jpg'),
(9, 16, 10, 'Visarno Arena', 'Piazzale delle Cascine, 50144', 'https://example.com/foto/firenze.jpg'),
(10, 18, 11, 'Arena Santa Giuliana', 'Via Campo di Marte, 06124', 'https://example.com/foto/perugia.jpg'),
(11, 10, 12, 'Mole Vanvitelliana', 'Banchina Giovanni da Chio, 60121', 'https://example.com/foto/ancona.jpg'),
(12, 7, 13, 'Circo Massimo', 'Via del Circo Massimo, 00186', 'https://example.com/foto/roma.jpg'),
(13, 1, 14, 'Teatro Comunale', 'Via Verdi, 1, 67100', 'https://example.com/foto/laquila.jpg'),
(14, 11, 15, 'Teatro Savoia', 'Piazza Gabriele Pepe, 86100', 'https://example.com/foto/campobasso.jpg'),
(15, 4, 16, 'Piazza del Plebiscito', 'Piazza del Plebiscito, 80132', 'https://example.com/foto/napoli.jpg'),
(16, 13, 17, 'Arena della Vittoria', 'Via Giuseppe Capruzzi, 70124', 'https://example.com/foto/bari.jpg'),
(17, 2, 18, 'Parco della Musica', 'Via Roma, 85100', 'https://example.com/foto/potenza.jpg'),
(18, 3, 19, 'Teatro Politeama', 'Via Giuseppe Jannoni, 88100', 'https://example.com/foto/catanzaro.jpg'),
(19, 15, 20, 'Teatro Massimo', 'Piazza Verdi, 90138', 'https://example.com/foto/palermo.jpg'),
(20, 14, 21, 'Arena Sant\'Elia', 'Via Rockfeller, 09126', 'https://example.com/foto/cagliari.jpg');

-- --------------------------------------------------------

--
-- Структура таблиці `region`
--

CREATE TABLE `region` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп даних таблиці `region`
--

INSERT INTO `region` (`id`, `name`) VALUES
(1, 'Abruzzo'),
(2, 'Basilicata'),
(3, 'Calabria'),
(4, 'Campania'),
(5, 'Emilia-Romagna'),
(6, 'Friuli-Venezia Giulia'),
(7, 'Lazio'),
(8, 'Liguria'),
(9, 'Lombardia'),
(10, 'Marche'),
(11, 'Molise'),
(12, 'Piemonte'),
(13, 'Puglia'),
(14, 'Sardegna'),
(15, 'Sicilia'),
(16, 'Toscana'),
(17, 'Trentino-Alto Adige'),
(18, 'Umbria'),
(19, 'Valle d\'Aosta'),
(20, 'Veneto');

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
  ADD UNIQUE KEY `name` (`name`);

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
  ADD UNIQUE KEY `region` (`name`);

--
-- AUTO_INCREMENT для збережених таблиць
--

--
-- AUTO_INCREMENT для таблиці `administrator`
--
ALTER TABLE `administrator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблиці `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблиці `concert`
--
ALTER TABLE `concert`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблиці `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT для таблиці `region`
--
ALTER TABLE `region`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
