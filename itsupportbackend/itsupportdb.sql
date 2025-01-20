SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Database: `itsupportdb`

-- --------------------------------------------------------

-- Structure of the table `equipment`
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE IF NOT EXISTS `equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `isChecked` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data for table `equipment`
INSERT INTO `equipment` (`id`, `name`, `description`, `email`, `isChecked`) VALUES
(1, 'pc', 'pc not active', '', FALSE),
(2, 'windows', 'pc no active windows', '', FALSE),
(3, 'cable reseaux', 'n\'est pas active', '', FALSE),
(4, 'pc', 'pc', '', FALSE),
(5, 'pc', 'pc no active', 'reda@gmail.com', FALSE),
(6, 'pc', 'pc', 'pc', FALSE);

-- --------------------------------------------------------

-- Structure of the table `users`
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data for table `users`
INSERT INTO `users` (`id`, `email`, `password`) VALUES
(1, 'hajar@gmail.com', '3887409c99ee20b02291a77ac431ee8d'),
(2, 'reda@gmail.com', '7abe9ec9b03f6b1e33a880d0cd44261e');

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
