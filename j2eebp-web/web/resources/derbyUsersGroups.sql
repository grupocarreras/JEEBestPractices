create table users(username varchar(15),
                   password varchar(15));
create table groups(username varchar(15),
                   groupname varchar(15));
insert into users values('epelaez','epelaez');
insert into users values('guigra','guigra');
insert into users values('saulario','saulario');
insert into users values('beita','beita');
insert into groups values('epelaez','xtms');
insert into groups values('beita','xtms');
insert into groups values('guigra','itms');
insert into groups values('saulario','itms');
