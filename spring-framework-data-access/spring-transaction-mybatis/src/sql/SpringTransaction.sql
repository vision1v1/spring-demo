create table `user1`(
	`id` integer unsigned not null auto_increment,
    `name` varchar(45) not null default '',
    primary key(`id`)
)engine = InnoDB;


CREATE TABLE `user2` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY(`id`)
)ENGINE = InnoDB;



delete from user1;

delete from user2;

SET SQL_SAFE_UPDATES = 0;


select * from user1;

select * from user2;



