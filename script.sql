

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pointland`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

create database pointland;
use pointland;

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_firstname` varchar(255) DEFAULT NULL,
  `admin_mail` varchar(255) DEFAULT NULL,
  `admin_pwd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_name`, `admin_firstname`, `admin_mail`, `admin_pwd`) VALUES
(1, 'Admin', 'Coucou', 'admin@mail.com', 'azerty');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `bill_price` float DEFAULT NULL,
  `bill_dte` date DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_cart` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `cart_price` float DEFAULT NULL,
  `cart_nb_item` int(11) DEFAULT NULL,
  `cart_point_l` int(11) DEFAULT NULL,
  `cart_point_w` int(11) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_firstname` varchar(255) DEFAULT NULL,
  `client_mail` varchar(255) DEFAULT NULL,
  `client_num` char(10) DEFAULT NULL,
  `client_password` varchar(255) DEFAULT NULL,
  `client_token` text,
  `client_gender` varchar(255) DEFAULT NULL,
  `client_country` varchar(255) DEFAULT NULL,
  `client_city` varchar(255) DEFAULT NULL,
  `client_street` varchar(255) DEFAULT NULL,
  `client_num_street` varchar(255) DEFAULT NULL,
  `client_date_inscription` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_entreprise` int(11) DEFAULT NULL,
  `client_verif` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `client_name`, `client_firstname`, `client_mail`, `client_num`, `client_password`, `client_token`, `client_gender`, `client_country`, `client_city`, `client_street`, `client_num_street`, `client_date_inscription`, `id_entreprise`, `client_verif`) VALUES
(1, 'CName1', 'CFirstname', 'client@mail.com', '0102030405', 'TEST123456', 'vebghicvpzn', 'Fils de lune', 'Lune', 'City', 'Street', '43', '2022-04-08 14:51:06', 1, 0),
(2, 'CName2', 'CFirstname', 'client@mail.com', '0102030405', 'TEST123456', 'vebghicvpzn', 'Fils de lune', 'Lune', 'City', 'Street', '43', '2022-04-08 14:51:17', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `contribution`
--

CREATE TABLE `contribution` (
  `contribution_id` int(11) NOT NULL,
  `contribution_prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contribution`
--

INSERT INTO `contribution` (`contribution_id`, `contribution_prix`) VALUES
(1, 200),
(2, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `credit_card`
--

CREATE TABLE `credit_card` (
  `credit_card_id` int(11) NOT NULL,
  `credit_card_num_card` int(11) DEFAULT NULL,
  `credit_card_date_end` date DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `entreprise`
--

CREATE TABLE `entreprise` (
  `entreprise_id` int(11) NOT NULL,
  `entreprise_siret` char(14) NOT NULL,
  `entreprise_name` varchar(255) NOT NULL,
  `entreprise_estate` varchar(255) NOT NULL,
  `entreprise_mail` varchar(255) NOT NULL,
  `entreprise_tel` char(10) NOT NULL,
  `entreprise_pwd` varchar(255) DEFAULT NULL,
  `entreprise_dte_registration` date NOT NULL,
  `entreprise_dte_end` date DEFAULT NULL,
  `entreprise_turnover` int(11) NOT NULL,
  `entreprise_logo` varchar(255) DEFAULT NULL,
  `id_contribution` int(11) NOT NULL,
  `entreprise_verif` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `entreprise`
--

INSERT INTO `entreprise` (`entreprise_id`, `entreprise_siret`, `entreprise_name`, `entreprise_estate`, `entreprise_mail`, `entreprise_tel`, `entreprise_pwd`, `entreprise_dte_registration`, `entreprise_dte_end`, `entreprise_turnover`, `entreprise_logo`, `id_contribution`, `entreprise_verif`) VALUES
(1, '01234567899874', 'name_entreprise', 'EFirstname', 'entreprise@mail.com', '0102030405', '915636eb73e35af3687f30614f53f53693494b2f21edc225c62243d802c73085', '2022-04-01', '2042-04-04', 12000, '', 2, 1),
(3, '14785203696587', 'Test', 'Photo', 'photo@gmail.com', '5039403294', NULL, '2022-04-03', '2022-04-15', 24534504, 'Capture_decran_2022-01-26_220219.png', 1, 0),
(4, '14785203691452', 'electgaame', 'JV', 'newgames@mail.com', '0102030405', '915636eb73e35af3687f30614f53f53693494b2f21edc225c62243d802c73085', '2017-04-04', '2022-04-30', 4000000, '', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `fidelity_card`
--

CREATE TABLE `fidelity_card` (
  `fidelity_card_id` int(11) NOT NULL,
  `fidelity_card_nb_point` int(11) DEFAULT NULL,
  `fidelity_card_code` char(20) DEFAULT NULL,
  `fidelity_card_num` int(11) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `include_pa`
--

CREATE TABLE `include_pa` (
  `include_pa_id` int(11) NOT NULL,
  `include_pa_quantite` int(11) DEFAULT NULL,
  `include_pa_dte` date DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `include_pp`
--

CREATE TABLE `include_pp` (
  `include_pp_id` int(11) NOT NULL,
  `include_pp_quantity` int(11) DEFAULT NULL,
  `include_pp_dte` date DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `id_cart` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `include_pw`
--

CREATE TABLE `include_pw` (
  `include_pw_id` int(11) NOT NULL,
  `include_pw_stock` int(11) DEFAULT NULL,
  `include_pw_dte` date DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `id_warehouse` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `include_pw`
--

INSERT INTO `include_pw` (`include_pw_id`, `include_pw_stock`, `include_pw_dte`, `id_item`, `id_warehouse`) VALUES
(1, 100, '2022-04-11', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `item_type` varchar(255) NOT NULL,
  `item_brand` varchar(200) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_description` varchar(255) NOT NULL,
  `item_price_buy` double(10,2) NOT NULL,
  `item_price_sell` double(10,2) NOT NULL,
  `item_image` varchar(255) NOT NULL,
  `item_ref` varchar(255) NOT NULL,
  `item_point_l` int(11) NOT NULL,
  `item_point_w` int(11) NOT NULL,
  `item_register` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_entreprise` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_type`, `item_brand`, `item_name`, `item_description`, `item_price_buy`, `item_price_sell`, `item_image`, `item_ref`, `item_point_l`, `item_point_w`, `item_register`, `id_entreprise`) VALUES
(1, 'bien', 'Apple', 'IPhone 13', 'iphone derniére géneration', 150.50, 2000.99, '', 'FZVZIVNZNV', 5000, 802, '2022-04-10 11:17:25', 1),
(2, '', 'Nokia', '3310', 'Rework du 3310', 300.00, 500.00, '', 'VDZVNZNVZOP', 600, 250, '2022-04-10 11:18:11', 3),
(4, 'bien', 'Samsung', 'ppp', 'vieux samsung bonne qualité, reconditionner', 50.12, 75.26, '', 'REFTUIOPN', 123, 12, '2022-04-06 17:06:39', 1),
(13, 'cc', 'cc', 'vv', 'cc', 0.20, 255.00, 'null', 'cc', 95, 15, '2022-05-07 22:53:24', 4);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL,
  `notification_type` varchar(255) DEFAULT NULL,
  `notification_msg` varchar(255) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `warehouse_id` int(11) NOT NULL,
  `warehouse_nom` varchar(255) DEFAULT NULL,
  `warehouse_adresse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `warehouse`
--

INSERT INTO `warehouse` (`warehouse_id`, `warehouse_nom`, `warehouse_adresse`) VALUES
(1, 'Entrepot', '242 Rue du Faubourg Saint-Antoine'),
(2, 'Paris', '128 rue de la Tour Eiffel'),
(3, 'Rouen', '242 Rue de Rouen');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_cart` (`id_cart`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`),
  ADD KEY `id_entreprise` (`id_entreprise`);

--
-- Indexes for table `contribution`
--
ALTER TABLE `contribution`
  ADD PRIMARY KEY (`contribution_id`);

--
-- Indexes for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`credit_card_id`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`entreprise_id`),
  ADD KEY `id_contribution` (`id_contribution`);

--
-- Indexes for table `fidelity_card`
--
ALTER TABLE `fidelity_card`
  ADD PRIMARY KEY (`fidelity_card_id`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `include_pa`
--
ALTER TABLE `include_pa`
  ADD PRIMARY KEY (`include_pa_id`),
  ADD KEY `id_admin` (`id_admin`),
  ADD KEY `id_item` (`id_item`);

--
-- Indexes for table `include_pp`
--
ALTER TABLE `include_pp`
  ADD PRIMARY KEY (`include_pp_id`),
  ADD KEY `id_cart` (`id_cart`),
  ADD KEY `id_item` (`id_item`);

--
-- Indexes for table `include_pw`
--
ALTER TABLE `include_pw`
  ADD PRIMARY KEY (`include_pw_id`),
  ADD KEY `id_warehouse` (`id_warehouse`),
  ADD KEY `id_item` (`id_item`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `id_entreprise` (`id_entreprise`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`warehouse_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `contribution`
--
ALTER TABLE `contribution`
  MODIFY `contribution_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `credit_card`
--
ALTER TABLE `credit_card`
  MODIFY `credit_card_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `entreprise_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `fidelity_card`
--
ALTER TABLE `fidelity_card`
  MODIFY `fidelity_card_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `include_pa`
--
ALTER TABLE `include_pa`
  MODIFY `include_pa_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `include_pp`
--
ALTER TABLE `include_pp`
  MODIFY `include_pp_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `include_pw`
--
ALTER TABLE `include_pw`
  MODIFY `include_pw_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `warehouse`
--
ALTER TABLE `warehouse`
  MODIFY `warehouse_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`cart_id`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`client_id`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`id_entreprise`) REFERENCES `entreprise` (`entreprise_id`);

--
-- Constraints for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD CONSTRAINT `credit_card_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`client_id`);

--
-- Constraints for table `entreprise`
--
ALTER TABLE `entreprise`
  ADD CONSTRAINT `entreprise_ibfk_1` FOREIGN KEY (`id_contribution`) REFERENCES `contribution` (`contribution_id`);

--
-- Constraints for table `fidelity_card`
--
ALTER TABLE `fidelity_card`
  ADD CONSTRAINT `fidelity_card_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`client_id`);

--
-- Constraints for table `include_pa`
--
ALTER TABLE `include_pa`
  ADD CONSTRAINT `include_pa_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`admin_id`),
  ADD CONSTRAINT `include_pa_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `include_pp`
--
ALTER TABLE `include_pp`
  ADD CONSTRAINT `include_pp_ibfk_1` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`cart_id`),
  ADD CONSTRAINT `include_pp_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `include_pw`
--
ALTER TABLE `include_pw`
  ADD CONSTRAINT `include_pw_ibfk_1` FOREIGN KEY (`id_warehouse`) REFERENCES `warehouse` (`warehouse_id`),
  ADD CONSTRAINT `include_pw_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_entreprise`) REFERENCES `entreprise` (`entreprise_id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`client_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
