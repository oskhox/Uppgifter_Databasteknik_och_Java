-- DDL-script med tomma tabeller
drop database if exists skobutiken;
create database skobutiken;
use skobutiken;

create table Märke(
id int not null auto_increment primary key,
namn varchar(15) not null);

create table Skonamn(
titel varchar(25) not null primary key,
märkeID int not null,
foreign key (märkeID) references Märke(id) on delete cascade);
-- Här tillhör (1) märke alltid ett (1) skonamn. Därför kan vi köra cascade så att om märket försvinner så försvinner skonamnet helt.

create table Sko( 
id int not null auto_increment primary key,
skonamnTitel varchar(25) not null,
storlek int not null,
färg varchar(10) not null,
pris int not null,
antal_i_lager int not null,
foreign key (skonamnTitel) references Skonamn(titel));
-- Vi behåller default-beteendet för referensintegriteten för denna FK då inget okänt skonamn ska kunna skrivas in, eller nuvarande tas bort, när namnen alltid måste matcha helt. Den blir hårt bunden.

create table Kategori(
id int not null auto_increment primary key,
namn varchar(15) not null);

create table Kategori_Sko(
kategoriID int not null,
skoID int not null,
primary key (kategoriId, skoId),
foreign key (kategoriID) references Kategori(id) on delete cascade,
-- Om kategorin försvinner behövs det ingen kategori-referens till en viss sko, därför kan vi ta bort kategori-referensen i så fall.
foreign key (skoID) references Sko(id) on delete cascade);
-- Om hela skon försvinner finns det inget syfte att behålla en referens som kopplar ihop den skon med en kategori.

create table Kund(
id int not null auto_increment primary key,
för_och_efternamn varchar(20) not null,
ort varchar(10) not null,
epost varchar(25) not null);

create table Beställning(
id int not null auto_increment primary key,
datum date not null,
kundID int not null,
foreign key (kundID) references Kund(Id) on delete cascade);
-- Om kunden försvinner ur databasen finns det ingen anledning att ha kvar kundens beställning heller.

create table Skoval(
id int not null auto_increment primary key,
antal int not null,
beställningID int not null,
foreign key (beställningID) references Beställning(id) on delete cascade);
-- Om beställningen har tagits bort ur databasen behövs inte något val av sko(r) finnas heller.

create table Sko_Skoval(
skoID int not null,
skovalID int not null,
primary key (skoID, skovalID),
foreign key (skoID) references Sko(id) on delete cascade,
-- Om skon raderas ur databasen behöver vi ingen referens till den.
foreign key (skovalID) references Skoval(id) on delete cascade);
-- Om just det skovalet raderas eller inte görs från början behöver vi ingen referens till det skovalet.

-- Skapar ett index på kolumnen sko.storlek då den kolumnen inte är en primärnyckel och därmed inte är indexerad, och användare ofta (alltid) kan tänkas vilja söka på rätt storlek.
create index IX_storlek on sko(storlek);

-- DML-script med data
insert into Märke (namn) values
('Nike'), ('Adidas'), ('Ecco'), ('New Balance'), ('Timberland'), ('Vans'), ('Next');

insert into Skonamn (titel, märkeID) values
('Air Max 90', 1),
('Samba OG', 2),
('Offroad', 3),
('NB 530', 4),
('6" Basic Boot', 5),
('Old Skool', 6),
('Penny Regular Fit', 7);

insert into Sko (skonamnTitel, storlek, färg, pris, antal_i_lager) values
('Air Max 90', 42, 'Blå', 1799, 3),
('Air Max 90', 46, 'Svart', 1799, 1),
('Samba OG', 40, 'Grön', 2199, 2),
('Samba OG', 45, 'Blå', 2199, 3),
('Offroad', 38, 'Svart', 1300, 5),
('Offroad', 39, 'Svart', 995, 4),
('Offroad', 44, 'Svart', 1300, 5),
('NB 530', 44, 'Beige', 1395, 2),
('NB 530', 47, 'Beige', 1395, 2),
('6" Basic Boot', 41, 'Svart', 999, 3),
('6" Basic Boot', 38, 'Svart', 999, 3),
('Old Skool', 42, 'Vit', 899, 2),
('Old Skool', 45, 'Vit', 899, 2),
('Penny Regular Fit', 38, 'Svart', 995, 4),
('Penny Regular Fit', 41, 'Svart', 995, 5);

insert into Kategori (namn) values
('Herrsko'), ('Damsko'), ('Unisexsko'), ('Sneakers'), ('Kängor'), ('Sandaler'), ('Loafers'), ('Hiking');

insert into Kategori_Sko (kategoriID, skoID) values
(1, 1), (4, 1), (1, 2), (4, 2), (1, 3), (8, 3), (1, 4), (8, 4), (3, 5), (6, 5), (3, 6), (6, 6), (3, 7), (6, 7), (1, 8), 
(4, 8), (1, 9), (4, 9), (2, 10), (5, 10), (2, 11), (5, 11), (3, 12), (4, 12), (3, 13), (4, 13), (2, 14), (7, 14), (2, 15), (7, 15);

insert into Kund (för_och_efternamn, ort, epost) values
('Alice Olsson', 'Stockholm', 'a.olsson@gmail.com'),
('Lucas Holm', 'Sundsvall', 'l.holm@hotmail.com'),
('Alma Fransson', 'Stockholm', 'a.fransson@gmail.com'),
('Maja Eklund', 'Malmö', 'm.eklund@yahoo.com'),
('Elias Isaksson', 'Kiruna', 'e.isaksson@gmail.com'),
('Adam Nyberg', 'Linköping', 'a.nyberg@gmail.com'),
('Olivia Löfgren', 'Örebro', 'o.lofgren@hotmail.com'),
('Hugo Björk', 'Göteborg', 'h.bjork@hotmail.com'),
('Astrid Arvidsson', 'Göteborg', 'a.arvidsson@gmail.com'),
('Charlie Ström', 'Visby', 'c.strom@gmail.com');

insert into Beställning (datum, kundID) values
('2024-07-02', 3),
('2024-07-27', 1),
('2024-08-15', 2),
('2024-08-30', 4),
('2024-09-05', 3),
('2024-09-20', 3),
('2024-10-10', 5),
('2024-10-11', 5),
('2024-10-13', 6),
('2024-11-07', 7),
('2024-11-18', 8),
('2024-11-23', 9),
('2024-11-25', 8),
('2024-12-12', 10),
('2024-12-20', 2);

insert into Skoval (antal, beställningID) values
(2, 1), (1, 2), (1, 2), (1, 3), (2, 4), (1, 5), (1, 6), (1,6), (3, 7), (2, 8), (2, 8), (1, 8), (1, 9), (1, 10),
(1, 11), (2, 12), (2, 13), (2, 13), (1, 14), (1, 15);

insert into Sko_Skoval (skoID, skovalID) values
(2, 20), (1, 10), (3, 15), (14, 9), (13, 12), (4, 17), (5, 19), (7, 3), (8, 5), (10, 7), (15, 18), (12, 8),
(9, 13), (9, 1), (15, 2), (11, 4), (15, 16), (6, 14), (14, 11), (12, 6), (7, 2), (2, 10);