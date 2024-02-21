/*
SQLyog Community v8.71 
MySQL - 5.5.30 : Database - mjcc02_2023
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mjcc02_2023` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mjcc02_2023`;

/*Table structure for table `host1` */

DROP TABLE IF EXISTS `host1`;

CREATE TABLE `host1` (
  `HostId` varchar(250) DEFAULT NULL,
  `VirtualMachine` varchar(250) DEFAULT NULL,
  `Users` int(250) DEFAULT NULL,
  `Location` varchar(250) DEFAULT NULL,
  `Capacity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `host1` */

insert  into `host1`(`HostId`,`VirtualMachine`,`Users`,`Location`,`Capacity`) values ('hosthyd1','hydvm',3,'hyd',40000),('newhostjpg','jpgvm',2,'Jgp',40000);

/*Table structure for table `loc` */

DROP TABLE IF EXISTS `loc`;

CREATE TABLE `loc` (
  `Location` varchar(250) DEFAULT NULL,
  `Sent` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `loc` */

insert  into `loc`(`Location`,`Sent`) values ('hyd','hydsend');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `FirstName` varchar(250) DEFAULT NULL,
  `LastName` varchar(250) DEFAULT NULL,
  `Email` varchar(250) NOT NULL,
  `Password` varchar(250) DEFAULT NULL,
  `Mobile` varchar(250) DEFAULT NULL,
  `Location` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`FirstName`,`LastName`,`Email`,`Password`,`Mobile`,`Location`) values ('user1','user1','user1@gmail.com','user1','8877445522','hyd');

/*Table structure for table `virtualmachine` */

DROP TABLE IF EXISTS `virtualmachine`;

CREATE TABLE `virtualmachine` (
  `VirtualMachine` varchar(250) DEFAULT NULL,
  `UserId` varchar(250) DEFAULT NULL,
  `Location` varchar(250) DEFAULT NULL,
  `HostId` varchar(250) DEFAULT NULL,
  `Usage1` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `virtualmachine` */

insert  into `virtualmachine`(`VirtualMachine`,`UserId`,`Location`,`HostId`,`Usage1`) values ('hydvm','user1@gmail.com','hyd','hosthyd1',1157);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
