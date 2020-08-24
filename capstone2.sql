//SELECT * FROM park WHERE name = 'Cuyahoga National Valley Park';

//Select * from park;

//Select name from park WHERE park_id <= 5;

//Select * from campground;

//SELECT * from site;

SELECT COUNT(reservation.site_id) as reservation_count, site_number, max_occupancy, accessible, max_rv_length, utilities from site
JOIN campground ON campground.campground_id = site.campground_id
JOIN reservation ON reservation.site_id = site.site_id
GROUP BY site_number
WHERE campground.name = 'Blackwoods'; 

SELECT site.site_id, 
site.campground_id,
site.max_occupancy,
site.site_number,
site.accessible,
site.max_rv_length,
site.utilities,
COUNT(reservation_id) as reservation_count FROM reservation 
JOIN site ON reservation.site_id = site.site_id
JOIN campground ON campground.campground_id = site.campground_id
WHERE campground.name = 'Blackwoods'
GROUP BY reservation.site_id, site.site_id, campground.name
ORDER BY reservation_count desc, site_id asc
LIMIT 5;

Select 
site_id,
site.site_number,
max_occupancy,
accessible,
max_rv_length,
utilities
from site
JOIN campground ON campground.campground_id = site.campground_id
WHERE campground.name = 'The Unnamed Primitive Campsites'
GROUP BY site_id
Limit 5;

SELECT * FROM site where campground_id = 1 AND site_id NOT IN 
(
SELECT site_id FROM reservation 
WHERE (from_date, to_date) 
OVERLAPS ( :dates ) 
);

SELECT campground_id FROM campground;

SELECT * from site;


SELECT campground_id FROM campground WHERE name = 'Blackwoods';
select daily_fee from campground WHERE name = 'Blackwoods';

SELECT reservation_id 
FROM reservation
where 
name = 'Kevin Phan' AND
site_id = '475' AND
from_date = '2020-08-15' AND
to_date = '2020-08-20' AND
create_date = '2020-08-21'
;

SELECT site.site_id,
site.site_number,
max_occupancy,
accessible,
max_rv_length,
utilities, 
COUNT(reservation_id) as reservation_count from site
JOIN reservation ON reservation.site_id = site.site_id
where site.site_id >= 1 AND site.site_id <= 276
GROUP BY site.site_id
ORDER BY reservation_count desc
limit 5;

SELECT 
site.site_id,
COUNT(reservation_id) as reservation_count
FROM site
JOIN reservation ON reservation.site_id = site.site_id
GROUP BY site.site_id
ORDER BY reservation_count desc
;

Select * from site
where site_id = '40';
