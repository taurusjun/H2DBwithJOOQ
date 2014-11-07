drop table customer;
create table customer(id integer auto_increment primary key,firstname varchar,lastname varchar);
insert into customer(firstname,lastname) values('Wang1','Jun');
insert into customer(firstname,lastname) values('Wang2','Jun');
insert into customer(firstname,lastname) values('Wang3','Jun');
insert into customer(firstname,lastname) values('Wang4','Jun');
insert into customer(firstname,lastname) values('Wang5','Jun');
drop table student;
create table student(id integer auto_increment primary key,cust_id integer not null,name varchar);
insert into student(cust_id,name) values(2,'student');

