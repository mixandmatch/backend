Drop table if exists users;
Drop table if exists locations;
Drop table if exists appointments;
Drop table if exists matches;

create table users(id int primary key, name varchar(30));
create table locations(id int primary key, name varchar(30));
create table appointments(id int primary key, appointmentDate datetime, locationID int, userID int);
create table matches(id int primary key, userID int, appointmentID int);