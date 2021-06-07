use master  
go
create database LibraryDB
on primary
( name = N'LibraryDB_mdf', filename = N'D:\4 сем\СТП в Internet 4 сем\ЛР\2\LibraryDB_mdf.mdf', 
   size = 5120Kb, maxsize = 10240Kb, filegrowth = 1024Kb),
( name = N'LibraryDB_ndf', filename = N'D:\4 сем\СТП в Internet 4 сем\ЛР\2\LibraryDB_ndf.ndf', 
   size = 5120Kb, maxsize = 10240Kb, filegrowth = 10%)
go

--очистить таблицы, 
--через транзакцию вставить данные в одну табл,
--через prepareStatement вставить данные в другую табл

use LibraryDB;

create table Authors
(
	Full_name varchar(50) primary key,
	Country varchar(50)
);


create table Books
(
	[Name] varchar(50) primary key,
	Author_Full_name varchar(50),
	Release_year int,
	Publishing_house varchar(50),
	foreign key (Author_Full_name) references Authors (Full_name) on delete cascade
);

insert into Authors (Full_name, Country)
values	('Дж. Оруэлл', 'Великобритания'),
		('У. Шекспир', 'Королевство Англия'),
		('Эрих Мария Ремарк', 'Германия'),
		('Э. Хемингуэй', 'США'),
		('А.С. Пушкин', 'Российская империя');

insert into Books ([Name], Author_Full_name, Release_year, Publishing_house)
values	('1984', 'Дж. Оруэлл', 2021, 'Азбука-классика'),
		('Ромео и Джульетта', 'У. Шекспир', 2010, 'АСТ'),
		('Скотный двор', 'Дж. Оруэлл', 2020, 'Азбука-классика'),
		('Триумфальная арка', 'Эрих Мария Ремарк', 2020, 'АСТ'),
		('Капитанская дочка', 'А.С. Пушкин', 1986, 'Русский язык'),
		('Гамлет', 'У. Шекспир', 2000, 'Азбука-классика'),		
		('Старик и море', 'Э. Хемингуэй', 2021, 'Эксмо'),
		('Руслан и Людмила', 'А.С. Пушкин', 2014, 'Эксмо');

--Найти все книги, вышедшие в текущем и прошлом году		
select * from Books where Release_year = YEAR(GETDATE()) or Release_year = YEAR(GETDATE()) - 1;
--Вывести информацию об авторах
select * from Authors
--Вывести информацию об авторах, написавших как минимум 2 книги
select * from Authors where Full_name in (select Full_name from (select Authors.Full_name, COUNT(Books.[Name]) [count_col] from Authors join Books on Books.Author_Full_name = Authors.Full_name
group by Authors.Full_name) as newTable where [count_col] = 2);

--Удалить все книги, публикация которых была позднее заданного (2014) года
delete from Books where Release_year > 2014;
select * from Books;
--добавить книги обратно
insert into Books ([Name], Author_Full_name, Release_year, Publishing_house)
values	('1984', 'Дж. Оруэлл', 2021, 'Азбука-классика'),
		('Скотный двор', 'Дж. Оруэлл', 2020, 'Азбука-классика'),
		('Триумфальная арка', 'Эрих Мария Ремарк', 2020, 'АСТ'),
		('Старик и море', 'Э. Хемингуэй', 2021, 'Эксмо');
select * from Books;


--авторы, написавшие как минимум 2 книги
select Full_name from (select Authors.Full_name, COUNT(Books.[Name]) [count_col] from Authors join Books on Books.Author_Full_name = Authors.Full_name
group by Authors.Full_name) as newTable where [count_col] = 2;

--подсчёт книг у каждого автора
select Authors.Full_name, COUNT(Books.[Name]) [count_col] from Authors join Books on Books.Author_Full_name = Authors.Full_name
group by Authors.Full_name;

select * from Books
select * from Books where [Name] = 'Сборник сказок' or [Name] = 'Макбет';
delete from Books where [Name] = 'Сборник сказок';
delete from Books where [Name] = 'Макбет';

drop table Books;
drop table Authors;