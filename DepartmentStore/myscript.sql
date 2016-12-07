drop table CardOwner;
drop table CreditCards;
drop table Customer;
drop table Supplier;
drop table Items;
drop table Administrative;
drop table Retail;
drop table Department;
drop table Employee;
create table Employee( 
  ENUM number(3) primary key,
  ENAME varchar2(20),
  SKILL varchar2(20),
  DEPT number(2)
);
insert into Employee values (101,'Enrique','Bagger',20);
insert into Employee values (102,'Austin','Cashier',20);
insert into Employee values (103,'Laura','Cashier',21);
insert into Employee values (104,'Monica J','Floor Personal',21);
insert into Employee values (105,'Simon','Fitting Room',21);
insert into Employee values (106,'Jose','Sales',21);
insert into Employee values (107,'Kevin','Sales',22);
insert into Employee values (108,'Steve','Sales',23);
insert into Employee values (109,'James','Cook',24);
insert into Employee values (110,'Antoine','Cook',24);
insert into Employee values (111,'Phillip','Cashier',24);
insert into Employee values (112,'Charles','Cashier',25);
insert into Employee values (113,'Rob','Technician',25);
insert into Employee values (115,'Stephanie','Technician',25);
insert into Employee values (116,'Nicole','Sales',26);
insert into Employee values (117,'Jerry','Sales',27);
insert into Employee values (118,'Camila','Sales',27);
insert into Employee values (119,'Rose','Cashier',27);
insert into Employee values (120,'Carly','Floor Personal',28);
insert into Employee values (121,'Andy','Sales',28);
insert into Employee values (122,'Caroline','Sales',28);
insert into Employee values (123,'Virginia','Sales',29);
insert into Employee values (114,'Taylor','Sales',30);
insert into Employee values (124,'Tiffany','Manager',31);
insert into Employee values (125,'Carol','Manager',32);
insert into Employee values (126,'Nicholas','Manager',33);
insert into Employee values (127,'Javier' ,'Accountant',31);
insert into Employee values (128,'Brian','Human Resources',32);
create table Department(
  DCODE number(2) primary key,
  DNAME varchar2(25)
);
insert into Department values (20,'groceries');
insert into Department values (21,'Clothes');
insert into Department values (22,'Bath and Kitchen');
insert into Department values (23,'Home Decor');
insert into Department values (24,'Food');
insert into Department values (25,'Technology');
insert into Department values (26,'Toys');
insert into Department values (27,'Tools');
insert into Department values (28,'Garden and Outdoor');
insert into Department values (29,'Jewelry');
insert into Department values (30,'Audio and movies');
insert into Department values (32,'Personnel');
insert into Department values (31,'Accounting');
insert into Department values (33,'Shipping');
create table Retail(
  DCODE number(2) references Department deferrable
);
set constraint all deferred;
insert into Retail values (20);
insert into Retail values (21);
insert into Retail values (22);
insert into Retail values (23);
insert into Retail values (24);
insert into Retail values (25);
insert into Retail values (26);
insert into Retail values (27);
insert into Retail values (29);
insert into Retail values (30);
set constraint all immediate;
create table Administrative(
  DCODE number(2) references Department deferrable,
  MANAGER number(3)references Employee deferrable
);
set constraint all deferred;
insert into Administrative values (31,124);
insert into Administrative values (32,125);
insert into Administrative values (33,126);
set constraint all immediate;
create table Items(
  ITEMNUM number(4) primary key,
  PRICE number(5),
  SHIPPINGCOST number(5),
  QUANTITY number(3),
  DESCRIPTION varchar2(50),
  DCODE number(2) references Department deferrable
    );
set constraint all deferred;
insert into Items values (3100,0.99,2,120,'Apples',20);
insert into Items values (3101,2,5,60,'Pineapples',20);
insert into Items values (3102,1.5,5,100,'Oranges',20);
insert into Items values (3103,1.00,2,80,'Carrots',20);
insert into Items values (3104,5,5,20,'Potatoes',20);
insert into Items values (3200,9.99,5,50,'Plain White T-shirts',21);
insert into Items values (3201,4.99,5,80,'Socks',21);
insert into Items values (3202,35.00,10,40,'blue jeans',21);
insert into Items values (3203,15.00,5,10,'hats',21);
insert into Items values (3300,9.99,5,40,'Towels',22);
insert into Items values (3301,2.99,2,60,'Hand Soap',22);
insert into Items values (3302,15.00,5,40,'Picture Frame',23);
insert into Items values (3100,0.99,0,20,'Apples',24);
insert into Items values (3304,4.99,0,10,'Pizza',24);
insert into Items values (3400,300.00,10,20,'TV',25);
insert into Items values (3401,250.00,10,20,'PS4',25);
insert into Items values (3600,20.00,5,15,'Hammer',27);
insert into Items values (3700,15.00,2,30,'Hose',28);
insert into Items values (3800,50.00,10,25,'Watch',29);
insert into Items values (3900,30,5,25,'Speakers',30);
set constraint all immediate;
create table Supplier(
SUPPLIERID number(3) primary key, 
LOCATION varchar2(25),
SNAME	varchar2(25)
);
insert into Supplier values (400,'Atlanta','Amazon');
insert into Supplier values (401,'Baltimore','Best Buy');
insert into Supplier values (402,'New York','Ebay');
insert into Supplier values (403,'Philadelphia','AnR Clothes');
insert into Supplier values (404,'Washington DC','Fresh Foods');
insert into Supplier values (405,'Santa Monica CA','West Coast Sounds');
insert into Supplier values (406,'Seattle','Microsoft');
insert into Supplier values (407,'Cupertino','Apple');
insert into Supplier values (408,'London England','Fine Jewelers');
insert into Supplier values (409,'Pittsburg','Home Depot');
insert into Supplier values (410,'Miami','Urban Fashion');
create table Customer(
CUSTOMERID number(3) primary key, 
CNAME varchar2(25),
Address	varchar2(60)
);
insert into Customer values (600,'Chris','2503 Blake wood Ct.');
insert into Customer values (601,'James','2322 longtree St.');
insert into Customer values (602,'Shaq','4643 first St.');
insert into Customer values (603,'Bobby','6533 Hummer rd.');
insert into Customer values (604,'Carl','5334 Pennsylvania Ave.');
insert into Customer values (605,'Shanon','2311 S. Queen St.');
insert into Customer values (606,'Beatrice','7832 Euclid blvd.');
insert into Customer values (607,'whitney','9322 University Dr.');
insert into Customer values (608,'Ben','9342 University Dr.');
insert into Customer values (609,'Carlos','1232 Annandale Rd.');
insert into Customer values (610,'Caroline','8434 Graham Rd.');
insert into Customer values (611,'Carla','1922 joyce st.');
insert into Customer values (612,'Flora','7832 Industrial Dr.');
insert into Customer values (613,'Nancy','3222 grover Rd.');
insert into Customer values (614,'Edward','8111 Belvedere st.');
insert into Customer values (615,'Maite','9211 Mason ave.');
insert into Customer values (616,'Jhoseline','4334 Madrid ave.');
insert into Customer values (617,'John','2323 Jhordan st.');
insert into Customer values (618,'Amanda','1113 picket st.');
insert into Customer values (619,'Adriana','6112 rale ave.');
insert into Customer values (620,'Jordan','8433 S. Fairfax st.');

create table CreditCards(
CARDNUM number(16) primary key,
CARDTYPE varchar2(30),
EXPIRATION number(4),
CARDLIMIT number(7)
);
insert into CreditCards values (4485353535179930,'visa',1709,10000);
insert into CreditCards values (4521132567861268,'visa',1710,15000);
insert into CreditCards values (4716027476244926,'visa',1707,50000);
insert into CreditCards values (4485758855885231,'visa',1612,5000);
insert into CreditCards values (4556756356962123,'visa',1611,30000);
insert into CreditCards values (4532822177709249,'visa',1802,100000);
insert into CreditCards values (5104212628067378,'MasterCard',1611,5000);
insert into CreditCards values (5409938920462980,'MasterCard',1705,12000);
insert into CreditCards values (6011865787104306,'Discover',1802,20000);
insert into CreditCards values (6011600184629179,'Discover',1703,1000);
insert into CreditCards values (6011881371585743,'Discover',1709,200000);
insert into CreditCards values (343524285384942,'American Express',1611,12000);
insert into CreditCards values (347464435442009,'American Express',1708,50000);

create table CardOwner(
	CARDNUM number(16) ,
	CUSTOMERID number(3) ,
	primary key (CARDNUM,CUSTOMERID)
);
describe CardOwner;
describe CreditCards;
describe Customer;
describe Supplier;
describe Items;
describe Administrative;
describe Retail;
describe Department;

SELECT * FROM CardOwner;
SELECT * FROM CreditCards;
SELECT * FROM Customer;
SELECT * FROM Supplier;
SELECT * FROM Items;
SELECT * FROM Administrative;
SELECT * FROM Retail;
SELECT * FROM Department;
SELECT * FROM Employee;