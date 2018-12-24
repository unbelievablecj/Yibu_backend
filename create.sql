DROP DATABASE IF EXISTS Yibu;
CREATE DATABASE Yibu DEFAULT CHARACTER SET utf8;
USE Yibu;

create table user(
	Uid int auto_increment primary key,
	Uname varchar(50) unique not null,
	Upicadd varchar(50),
	Uemail varchar(50) not null,
	Upw varchar(50) not null,
	Utestma varchar(10), 
	Uon int
)ENGINE=InnoDB;

create table gonglue(
	Gid int auto_increment primary key,
	Gjing double, 
	Gwei double, 
	Gpoint varchar(10000), 
	Guser varchar(50), 
	Gcomment varchar(1000), 
	Gpicture varchar(1000), 
	GnumLikes varchar(10),
	Gpublish varchar(50),
	Gtitle varchar(100),
	GdoStrategy varchar (8000),
	Glabel varchar(1000)
)ENGINE=InnoDB;


