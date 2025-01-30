-- Tar bort en elf för att aktivera trigger
delete from elf where name = 'Santa';
select * from elf;
-- Skriver ut blacklist
select * from blacklist;