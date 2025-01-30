-- Fråga 1 (vem köpte specifik sko)
select kund.för_och_efternamn as Kund from kund
inner join beställning on kund.id = beställning.kundid
inner join skoval on beställning.id = skoval.beställningid
inner join sko_skoval on skoval.id = sko_skoval.skovalid
inner join sko on sko_skoval.skoid = sko.id
inner join skonamn on sko.skonamntitel = skonamn.titel
inner join märke on skonamn.märkeid = märke.id
inner join kategori_sko on sko.id = kategori_sko.skoid
inner join kategori on kategori_sko.kategoriid = kategori.id
where märke.namn = 'Ecco'
and sko.färg = 'Svart'
and sko.storlek = 38
and kategori.namn = 'Sandaler';

-- Fråga 2 (antalet produkter per kategori)
select kategori.namn as Kategori, sum(sko.antal_i_lager) as Lagerantal from kategori
left join kategori_sko on kategori.id = kategori_sko.kategoriid
left join sko on kategori_sko.skoid = sko.id
group by kategori.id;

-- Fråga 3 (Totalbelopp per kund)
select kund.för_och_efternamn as Kund, sum(sko.pris * skoval.antal) as Totalbelopp from kund
left join beställning on kund.id = beställning.kundID
left join skoval on beställning.id = skoval.beställningid
left join sko_skoval on skoval.id = sko_skoval.skovalid
left join sko on sko_skoval.skoid = sko.id
group by kund.id
order by Totalbelopp DESC;

-- Fråga 4 (Totalt beställningsvärde per ort)
select kund.ort as Ort, sum(sko.pris * skoval.antal) as Totalvärde from kund
left join beställning on kund.id = beställning.kundID
left join skoval on beställning.id = skoval.beställningid
left join sko_skoval on skoval.id = sko_skoval.skovalid
left join sko on sko_skoval.skoid = sko.id
group by kund.ort
having sum(sko.pris * skoval.antal) > 1000
order by Totalvärde DESC;

-- Fråga 5 (Topp 5 sålda produkter)
select sko.skonamntitel as Namn, sum(skoval.antal) as Antal from sko
left join sko_skoval on sko.id = sko_skoval.skoid
left join skoval on sko_skoval.skovalid = skoval.id
group by sko.skonamntitel
order by Antal DESC
limit 5;

-- Fråga 6 (Månad med störst försäljning)
select date_format (beställning.datum, '%Y-%M') as Månad, sum(sko.pris * skoval.antal) as Försäljningssumma from beställning
left join skoval on beställning.id = skoval.beställningid
left join sko_skoval on skoval.id = sko_skoval.skovalid
left join sko on sko_skoval.skoid = sko.id
group by Månad
order by Försäljningssumma DESC;