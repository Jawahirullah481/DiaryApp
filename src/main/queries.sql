-- Creating diaryapp database--
create database diaryapp;


-- selecting the database--
use diaryapp;


-- Creating diaryappuser table --
create table diaryappuser(
userId Int NOT NULL AUTO_INCREMENT,
Username varchar(25) NOT NULL,
EmailId varchar(25) NOT NULL,
Password varchar(20) NOT NULL,
PRIMARY KEY (userId)
);


-- Creating diaryappinbox table --
create table diaryappinbox(
Id int NOT NULL AUTO_INCREMENT,
Heading varchar(35) NOT NULL,
Date date,
Content varchar(1500),
userId int,
PRIMARY KEY(id),
FOREIGN KEY(UserId) references diaryappuser(userId)
);


-- Creating stored procedure for deleteUser --
DELIMITER $$
CREATE PROCEDURE `deleteUser`(IN i INT)
BEGIN
SET FOREIGN_KEY_CHECKS=0;
delete from diaryappuser where userid = i;
delete from diaryappinbox where userid = i;
SET FOREIGN_KEY_CHECKS=1;
END$$
DELIMITER ;


-- Creating stored procedure for edit diary in inbox --
DELIMITER $$
CREATE PROCEDURE `editInbox`(IN newheading VARCHAR(25), IN newcontent VARCHAR(1500), IN newdate DATE, INOUT inboxid INT, IN userid INT)
BEGIN
delete from diaryappinbox where id = inboxid;
insert into diaryappinbox (heading, date, content, userid) values(newheading, newdate, newcontent, userId);
select inbox.id into inboxid from diaryappinbox as inbox where inbox.userId = userid order by inbox.id desc limit 1;
END$$
DELIMITER ;
