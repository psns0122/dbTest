create table users (
	userid   		varchar(50)	  	primary key, 
	username		varchar(50)	  	not null,
	userpassword	varchar(50)	  	not null,
	userage			numeric(3)		not null,
	useremail		varchar(50)	  	not null
);


drop table users_2;
create table users_2 (
    userid          varchar(50) primary key,
    username        varchar(50) not null,
    userpassword    varchar(50) not null,
    userphone        varchar(50) not null,
    useraddr        varchar(50) not null
);