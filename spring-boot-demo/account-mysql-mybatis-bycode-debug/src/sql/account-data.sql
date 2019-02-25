use Account;

start transaction;

#清空
delete from Users where Users.UserId != ''; 
delete from Roles where Roles.RoleId != '';

set @rootUserId = uuid();
set @rootRoleId = uuid();

#用户
insert Users value(@rootUserId,'root','root@123',now(),now());
insert Users value(uuid(),'admin','admin@123',now(),now());
insert Users value(uuid(),'ssy','ssy@123',now(),now());
insert Users value(uuid(),'xyx','xyx@123',now(),now());

#角色
insert Roles value(@rootRoleId,'root');
insert Roles value(uuid(),'系统管理员');
insert Roles value(uuid(),'老师');
insert Roles value(uuid(),'学生');

#分配 root 角色
insert UserRoles value(@rootUserId,@rootRoleId);

select * from Users;
select * from Roles;
select * from UserRoles;

commit;




