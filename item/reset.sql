pager less -n -i -S;

create user if not exists "user1"@"localhost" identified by "";

create database if not exists sandbox;
grant all privileges on sandbox.* to "user1"@"localhost";
use sandbox;
create table if not exists 01_free (
	id int,
	name varchar(10)
);
truncate table 01_free;
show tables;
select * from 01_free;
insert into 01_free value ( 1, "default01" );
insert into 01_free value ( 2, "default02" );
insert into 01_free value ( 3, "default03" );
select * from 01_free;
