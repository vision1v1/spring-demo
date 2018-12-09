create database if not exists Account default char set = utf8mb4;
use Account;

drop table if exists Users;
create table Users(
	UserId char(128),
    UserName nvarchar(256) not null,
    Password nvarchar(128) not null,
    CreatedTime datetime not null,
    UpdatedTime datetime not null,
    constraint Users_PK primary key (UserId)
)engine=InnoDB;

drop table if exists Roles;
create table Roles(
	RoleId char(128),
    RoleName nvarchar(64) not null,
    constraint Roles_PK primary key (RoleId)
)engine=InnoDB;

drop table if exists UserRoles;
create table UserRoles(
	UserId char(128),
    RoleId char(128),
    constraint UserRoles_PK primary key (UserId,RoleId),
    constraint Users_FK foreign key (UserId) references Users(UserId) on delete cascade,
    constraint Roles_FK foreign key (RoleId) references Roles(RoleId) on delete cascade
)engine=InnoDB;
