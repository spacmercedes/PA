drop sequence country_id_auto;
/
drop sequence continent_id_auto;
/
drop sequence city_id_auto;
/
drop table countries;
drop table continents;
drop table cities;
create table countries(
id int not null,
name varchar(100) not null,
code int,
continent int,
primary key (id)
);
create table continents(
    id int not null ,
    name varchar(100) not null,
    primary key (id)
);
create table cities(
id int not null,
country varchar(100),
name varchar(100) not null,
capital int,
latitude float,
longitude float,
primary key (id)
);
/
create sequence country_id_auto minvalue 1 start with 1 increment by 1;
/
create sequence continent_id_auto minvalue 1 start with 1 increment by 1;
/
create sequence city_id_auto minvalue 1 start with 1 increment by 1;

select * from continents;
select * from countries;
select * from cities;

delete from continents;
delete from cities;