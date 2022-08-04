use master
go

if db_id('mockproject_t6_2022') is not null
drop database mockproject_t6_2022
go

create database mockproject_t6_2022
go

use mockproject_t6_2022
go

create table roles
(
	id				tinyint			primary key identity,  -- auto_increment
	[description]	nvarchar(20)	not null unique
)
go

create table users
(
	id				bigint			primary key identity,
	username		varchar(20)		not null unique,
	fullname		nvarchar(50)	null,
	hashPassword	varchar(255)	not null,
	email			varchar(100)	not null unique,
	createdDate		datetime		not null default getdate(), -- now()
	imgUrl			varchar(255)	null,
	roleId			tinyint			foreign key references roles(id),
	isDeleted		bit				not null default 0 -- 0: false, 1: true
)
go

create table product_types
(
	id				tinyint			primary key identity,
	[description]	ntext			null,
	slug			varchar(255)	not null,
	isDeleted		bit				not null default 0
)
go

create table unit_types
(
	id				tinyint			primary key identity,
	[description]	ntext			null,
	isDeleted		bit				not null default 0
)

create table products
(
	id				bigint			primary key identity,
	[name]			nvarchar(255)	not null,
	typeId			tinyint			foreign key references product_types(id),
	quantity		int				not null,
	price			decimal(12,3)	not null,
	unitId			tinyint			foreign key references unit_types(id),
	imgUrl			varchar(255)	null,
	[description]	ntext			null,
	slug			varchar(255)	not null unique,
	isDeleted		bit				not null default 0
)
go

create table orders
(
	id				bigint			primary key identity,
	userId			bigint			foreign key references users(id),
	[address]		nvarchar(255)	not null,
	phone			varchar(11)		not null,
	createdDate		datetime		not null default getdate()
)
go

create table order_details
(
	id				bigint			primary key identity,
	orderId			bigint			foreign key references orders(id),
	productId		bigint			foreign key references products(id),
	price			decimal(12,3)	not null,
	quantity		int				not null
)
go

insert into roles([description]) values
('admin'),
('user')
go

-- duynt: pass 111
insert into users(username, fullname, hashPassword, email, imgUrl, roleId) values
('duynt', N'Nguyễn Trần Duy', '$2a$10$bC3fIu4WyB/FGwlbOPlZt.3IRzkM34vZNt1Kbe5ZDcq7r/XZFWNrO', 'duynt@abc.com', 'default.png', 1)
go

insert into product_types([description], slug) values
(N'Điện thoại', 'dien-thoai'),
(N'Laptop',		'laptop')
go

insert into unit_types([description]) values
(N'Chiếc'),
(N'Bộ')
go

insert into products([name],  typeId, quantity, price, unitId, imgUrl, [description], slug) values
(N'Iphone 11 64GB',					1, 5,  10000000, 1, 'iphone-11.jpg', N'Điện thoại Iphone 11 bản 64GB', 'iphone-11-64gb'),
(N'Samsung A95',					1, 12, 7500000,  1, 'samsung-a95.jpg', N'<b>Điện thoại samsung A95</b> là mẫu điện thoại mới nhất của Samsung với nhiều tính năng vượt trội', 'samsung-a95'),
(N'Laptop HP Pavilion i7 10th',		2, 1,  19250000, 1, 'laptop-hp-pavilion.jpg', N'Laptop HP Pavilion core i7 10th', 'laptop-hp-pavilion-i7-10th'),
(N'Laptop DELL Inspirion i5 gen 8', 2, 3,  13450000, 1, 'laptop-dell-inspirion.jpg', N'Laptop DELL Inspirion', 'laptop-dell-inspirion-i5-gen-8'),
(N'Iphone 12 Pro max 256GB',		1, 2,  37000000, 1, 'iphone-12-pro-max.jpg', N'Iphone xịn', 'iphone-12-pro-max-256gb'),
(N'Oppo Reno 4',					1, 15, 13270000, 1, 'oppo-reno-4.png', N'Điện thoại Oppo', 'oppo-reno-4'),
(N'San pham demo 1',				1, 15, 13270000, 1, 'oppo-reno-4.png', N'Điện thoại Oppo', 'san-pham-demo-1'),
(N'San pham demo 2',				1, 15, 13270000, 1, 'oppo-reno-4.png', N'Điện thoại Oppo', 'san-pham-demo-2'),
(N'San pham demo 3',				1, 15, 13270000, 1, 'oppo-reno-4.png', N'Điện thoại Oppo', 'san-pham-demo-3')
go