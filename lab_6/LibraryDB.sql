use master  
go
create database LibraryDB
on primary
( name = N'LibraryDB_mdf', filename = N'D:\4 ���\��� � Internet 4 ���\��\2\LibraryDB_mdf.mdf', 
   size = 5120Kb, maxsize = 10240Kb, filegrowth = 1024Kb),
( name = N'LibraryDB_ndf', filename = N'D:\4 ���\��� � Internet 4 ���\��\2\LibraryDB_ndf.ndf', 
   size = 5120Kb, maxsize = 10240Kb, filegrowth = 10%)
go

--�������� �������, 
--����� ���������� �������� ������ � ���� ����,
--����� prepareStatement �������� ������ � ������ ����

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
values	('��. ������', '��������������'),
		('�. �������', '����������� ������'),
		('���� ����� ������', '��������'),
		('�. ���������', '���'),
		('�.�. ������', '���������� �������');

insert into Books ([Name], Author_Full_name, Release_year, Publishing_house)
values	('1984', '��. ������', 2021, '������-��������'),
		('����� � ���������', '�. �������', 2010, '���'),
		('������� ����', '��. ������', 2020, '������-��������'),
		('������������ ����', '���� ����� ������', 2020, '���'),
		('����������� �����', '�.�. ������', 1986, '������� ����'),
		('������', '�. �������', 2000, '������-��������'),		
		('������ � ����', '�. ���������', 2021, '�����'),
		('������ � �������', '�.�. ������', 2014, '�����');

--����� ��� �����, �������� � ������� � ������� ����		
select * from Books where Release_year = YEAR(GETDATE()) or Release_year = YEAR(GETDATE()) - 1;
--������� ���������� �� �������
select * from Authors
--������� ���������� �� �������, ���������� ��� ������� 2 �����
select * from Authors where Full_name in (select Full_name from (select Authors.Full_name, COUNT(Books.[Name]) [count_col] from Authors join Books on Books.Author_Full_name = Authors.Full_name
group by Authors.Full_name) as newTable where [count_col] = 2);

--������� ��� �����, ���������� ������� ���� ������� ��������� (2014) ����
delete from Books where Release_year > 2014;
select * from Books;
--�������� ����� �������
insert into Books ([Name], Author_Full_name, Release_year, Publishing_house)
values	('1984', '��. ������', 2021, '������-��������'),
		('������� ����', '��. ������', 2020, '������-��������'),
		('������������ ����', '���� ����� ������', 2020, '���'),
		('������ � ����', '�. ���������', 2021, '�����');
select * from Books;


--������, ���������� ��� ������� 2 �����
select Full_name from (select Authors.Full_name, COUNT(Books.[Name]) [count_col] from Authors join Books on Books.Author_Full_name = Authors.Full_name
group by Authors.Full_name) as newTable where [count_col] = 2;

--������� ���� � ������� ������
select Authors.Full_name, COUNT(Books.[Name]) [count_col] from Authors join Books on Books.Author_Full_name = Authors.Full_name
group by Authors.Full_name;

select * from Books
select * from Books where [Name] = '������� ������' or [Name] = '������';
delete from Books where [Name] = '������� ������';
delete from Books where [Name] = '������';

drop table Books;
drop table Authors;