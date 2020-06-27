insert into user (userName, passWord, realName) select 'admin', '123456', 'admin' from dual where not exists(
    select * from user where userName='admin'
    );
insert into user (userName, passWord, realName) select 'root', 'root', 'root' from dual where not exists (
    select * from user where userName='root'
);
