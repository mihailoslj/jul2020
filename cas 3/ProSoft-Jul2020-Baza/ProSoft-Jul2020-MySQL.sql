/*
SQLyog Ultimate v9.50 
MySQL - 5.6.20 : Database - prosoftjul20
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftjul20` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftjul20`;

/*Table structure for table `meteorolog` */

DROP TABLE IF EXISTS `meteorolog`;

CREATE TABLE `meteorolog` (
  `MeteorologID` int(11) NOT NULL,
  `Ime` varchar(255) DEFAULT NULL,
  `Prezime` varchar(255) DEFAULT NULL,
  `KorisnickoIme` varchar(50) DEFAULT NULL,
  `Lozinka` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MeteorologID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `meteorolog` */

insert  into `meteorolog`(`MeteorologID`,`Ime`,`Prezime`,`KorisnickoIme`,`Lozinka`) values (1,'Ivana','Ivanovic','ivana','ivana123'),(2,'Dragan','Draganic','dragan','dragan123'),(3,'Jovana','Jovanic','jovana','jovana123'),(4,'Petar','Petrovic','petar','petar123');

/*Table structure for table `prognoza` */

DROP TABLE IF EXISTS `prognoza`;

CREATE TABLE `prognoza` (
  `PrognozaID` int(11) NOT NULL,
  `Dan` date DEFAULT NULL,
  `Opis` varchar(255) DEFAULT NULL,
  `MeteorologID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PrognozaID`),
  KEY `MeteorologID` (`MeteorologID`),
  CONSTRAINT `prognoza_ibfk_1` FOREIGN KEY (`MeteorologID`) REFERENCES `meteorolog` (`MeteorologID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prognoza` */

/*Table structure for table `prognozaregion` */

DROP TABLE IF EXISTS `prognozaregion`;

CREATE TABLE `prognozaregion` (
  `PrognozaID` int(11) NOT NULL,
  `RB` int(11) NOT NULL,
  `Temperatura` decimal(10,2) DEFAULT NULL,
  `MeteoAlarm` varchar(255) DEFAULT NULL,
  `Pojava` varchar(255) DEFAULT NULL,
  `RegionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PrognozaID`,`RB`),
  KEY `RegionID` (`RegionID`),
  CONSTRAINT `prognozaregion_ibfk_1` FOREIGN KEY (`PrognozaID`) REFERENCES `prognoza` (`PrognozaID`),
  CONSTRAINT `prognozaregion_ibfk_2` FOREIGN KEY (`RegionID`) REFERENCES `region` (`RegionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `prognozaregion` */

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `RegionID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `Opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RegionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `region` */

insert  into `region`(`RegionID`,`Naziv`,`Opis`) values (1,'Severna Srbija','Obuhvata Backu, Banat i Srem'),(2,'Beograd','Region koji obuhvata grad Beograd i centralnu Srbiju'),(3,'Istocna Srbija','Obuhvata Istocnu Srbiju'),(4,'Zapadna Srbija','Obuhvata Zapadnu Srbiju'),(5,'Juzna Srbija','Obuhvata Juznu Srbiju');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
