-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sty 11, 2024 at 03:17 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kolokwium`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `user` varchar(255) NOT NULL,
  `questionID` int(11) NOT NULL,
  `answer` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `user`, `questionID`, `answer`) VALUES
(1, 'Grzegorz Listwan', 1, 'b'),
(2, 'Grzegorz Listwan', 2, 'a'),
(3, 'Grzegorz Listwan', 3, 'b'),
(4, 'Grzegorz Listwan', 4, 'a'),
(5, 'Grzegorz Listwan', 5, 'b'),
(6, 'Grzegorz Listwan', 6, 'b'),
(7, 'Grzegorz Listwan', 7, 'a'),
(8, 'Grzegorz Listwan', 8, 'a'),
(9, 'Grzegorz Listwan', 9, 'a'),
(10, 'Grzegorz Listwan', 10, 'c'),
(11, 'Antoni Nowak', 1, 'b'),
(12, 'Antoni Nowak', 2, 'a'),
(13, 'Antoni Nowak', 3, 'c'),
(14, 'Antoni Nowak', 4, 'd'),
(15, 'Antoni Nowak', 5, 'a'),
(16, 'Antoni Nowak', 6, 'b'),
(17, 'Antoni Nowak', 7, 'c'),
(18, 'Antoni Nowak', 8, 'a'),
(19, 'Antoni Nowak', 9, 'd'),
(20, 'Antoni Nowak', 10, 'a'),
(21, 'AAA AAA', 1, 'a'),
(22, 'AAA AAA', 2, 'a'),
(23, 'AAA AAA', 3, 'a'),
(24, 'AAA AAA', 4, 'a'),
(25, 'AAA AAA', 5, 'a'),
(26, 'AAA AAA', 6, 'a'),
(27, 'AAA AAA', 7, 'a'),
(28, 'AAA AAA', 8, 'a'),
(29, 'AAA AAA', 9, 'a'),
(30, 'AAA AAA', 10, 'a');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answerA` varchar(255) NOT NULL,
  `answerB` varchar(255) NOT NULL,
  `answerC` varchar(255) NOT NULL,
  `answerD` varchar(255) NOT NULL,
  `correctAnswer` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question`, `answerA`, `answerB`, `answerC`, `answerD`, `correctAnswer`) VALUES
(1, '1. Jakie jest stolica Francji?', 'a. Berlin', 'b. Paryż', 'c. Londyn', 'd. Madryt', 'b'),
(2, '2. Który pierwiastek chemiczny reprezentowany jest symbolem \"O\"?', 'a. Tlen', 'b. Wodór', 'c. Azot', 'd. Sód', 'a'),
(3, '3. Kto napisał \"Romeo i Julia\"?', 'a. Charles Dickens', 'b. William Shakespeare', 'c. Jane Austen', 'd. Fiodor Dostojewski', 'b'),
(4, '4. Jaki kolor ma tradycyjnie skrzynka na listy?', 'a. Czerwony', 'b. Zielony', 'c. Niebieski', 'd. Żółty', 'a'),
(5, '5. Ile kontynentów jest na świecie?', 'a. 5', 'b. 6', 'c. 7', 'd. 8', 'b'),
(6, '6. Która planeta jest znana jako \"Czerwona Planeta\"?', 'a. Wenus', 'b. Mars', 'c. Jowisz', 'd. Saturn', 'b'),
(7, '7. Które zwierzę jest symbolem mądrości w wielu kulturach?', 'a. Sowa', 'b. Tygrys', 'c. Żyrafa', 'd. Wąż', 'a'),
(8, '8. Jak nazywa się proces, podczas którego roślina przetwarza światło na energię?', 'a. Fotosynteza', 'b. Respiracja', 'c. Fermentacja', 'd. Dekompozycja', 'a'),
(9, '9. Które państwo jest największe pod względem powierzchni?', 'a. Rosja', 'b. Stany Zjednoczone', 'c. Kanada', 'd. Australia', 'a'),
(10, '10. Co oznacza skrót HTML w kontekście informatyki?', 'a. HyperText Markup Language', 'b. HighTech Modern Language', 'c. Hotmail', 'd. HyperTransfer Machine Language', 'a');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `result`
--

CREATE TABLE `result` (
  `id` int(11) NOT NULL,
  `user` varchar(255) NOT NULL,
  `result` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`id`, `user`, `result`) VALUES
(1, 'Grzegorz Listwan ', 10),
(2, 'Grzegorz Listwan', 9),
(3, 'Antoni Nowak', 5),
(4, 'AAA AAA', 6);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `questionID` (`questionID`);

--
-- Indeksy dla tabeli `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`questionID`) REFERENCES `question` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
