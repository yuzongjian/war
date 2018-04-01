/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.47 : Database - db_equipment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_equipment` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `db_equipment`;

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '车牌号码',
  `typeId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '车辆状态。1为在停车，2为已经结束停车',
  `fee` float DEFAULT NULL COMMENT '实时收费',
  `getin_time` datetime DEFAULT NULL COMMENT '入库时间',
  `getout_time` datetime DEFAULT NULL COMMENT '出库时间',
  PRIMARY KEY (`id`),
  KEY `FK_t_equipment` (`typeId`),
  CONSTRAINT `FK_t_equipment` FOREIGN KEY (`typeId`) REFERENCES `carplace` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `car` */

insert  into `car`(`id`,`name`,`typeId`,`state`,`fee`,`getin_time`,`getout_time`) values (4,'123123',1,2,123213,'2017-10-24 21:42:57','2017-10-24 23:52:57'),(5,'·12123213',2,1,51,'2017-10-25 00:29:41','2017-10-25 00:29:41'),(7,'123123',1,2,0,'2017-10-25 09:00:59','2017-10-26 23:24:07'),(8,'124134',1,2,0,'2017-10-25 09:33:56','2017-10-27 15:43:32'),(9,'121312',1,1,0,'2017-10-25 12:42:57','2017-10-25 12:42:57'),(10,'123123qq',2,1,0,'2017-10-25 14:02:09','2017-10-25 14:02:09'),(11,'123123qq',2,1,0,'2017-10-25 14:02:33','2017-10-25 14:02:33'),(12,'yue啊',2,1,0,'2017-10-25 14:05:35','2017-10-25 14:05:35'),(23,'啊啊啊啊1213',2,1,0,'2017-10-25 17:21:10','2017-10-25 17:21:10'),(24,'越啊',2,1,0,'2017-10-25 17:33:52','2017-10-25 17:33:52'),(25,'越啊',2,1,0,'2017-10-25 17:34:03','2017-10-25 17:34:03'),(26,'越1234',2,1,0,'2017-10-25 17:34:23','2017-10-25 17:34:23'),(27,'越12345',2,1,0,'2017-10-25 17:34:41','2017-10-25 17:34:41'),(28,'123124',2,1,0,'2017-10-26 08:02:26','2017-10-26 08:02:26'),(29,'213123142',2,1,0,'2017-10-26 08:02:59','2017-10-26 08:02:59'),(30,'312314',1,1,0,'2017-10-26 13:51:08','2017-10-26 13:51:08'),(32,'123123qq',2,1,0,'2017-10-26 18:18:36','2017-10-26 18:18:36'),(33,'13123',2,2,0,'2017-10-26 20:48:24','2017-10-26 20:48:24'),(34,'21312414',1,1,0,'2017-10-27 00:21:48','2017-10-27 00:21:48'),(35,'12312314',1,2,0,'2017-10-27 00:33:15','2017-10-27 12:17:08'),(36,'123214',1,1,0,'2017-10-27 00:33:57','2017-10-27 00:33:57'),(37,'213124124',1,1,0,'2017-10-27 00:46:13','2017-10-27 00:46:13'),(38,'123123414',1,1,0,'2017-10-27 00:46:39','2017-10-27 00:46:39'),(39,'1312e',1,1,0,'2017-10-27 00:54:53','2017-10-27 00:54:53'),(40,'132134124',2,1,0,'2017-10-27 10:08:49','2017-10-27 10:08:49'),(41,'1231234124',1,1,0,'2017-10-27 10:32:00','2017-10-27 10:32:00'),(42,'粤A12112412',2,1,0,'2017-10-27 12:19:36','2017-10-27 12:19:36'),(43,'粤B啊啊',2,1,0,'2017-10-27 12:20:50','2017-10-27 12:20:50'),(44,'1234123',1,1,0,'2017-10-27 12:56:02','2017-10-27 12:56:02'),(45,'京啊啊啊啊',2,2,0,'2017-10-27 12:56:52','2017-10-27 15:44:24');

/*Table structure for table `carplace` */

DROP TABLE IF EXISTS `carplace`;

CREATE TABLE `carplace` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  `shiyong` int(10) DEFAULT '0' COMMENT '车库使用的车位数',
  `number` int(11) DEFAULT NULL COMMENT '车库共有车位数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `carplace` */

insert  into `carplace`(`id`,`typeName`,`shiyong`,`number`) values (1,'A区',9,100),(2,'B区',12,120),(3,'C区',1234,1234);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `trueName` varchar(20) DEFAULT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`trueName`,`roleName`) values (3,'marry','123','玛丽','操作员'),(5,'钟嘉华1234','123456','钟嘉华','管理员');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
