USE `FUN_TIME`;

/* Find movies in city */

/*
SELECT movie_id, MOVIE_TITLE, MOVIE_DESCRIPTION, DURATION, LANG, GENERE, COUNTRY 
 FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M
WHERE 
C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID
AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID
AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID
AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID */

/* Find halls with city and movie */
/*SELECT CINEMA_HALL_ID, CINEMA_HALL_SHORT_NAME, CINEMA_HALL_NAME, MOVIE_SHOW_TIME_STARTTIME, SCREEN_NAME
 FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M
WHERE 
C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID
AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID
AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID
AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND M.MOVIE_ID=1 */

/* Find halls with city and movie with tickets */
/*SELECT CINEMA_HALL_ID, CINEMA_HALL_SHORT_NAME, CINEMA_HALL_NAME, MOVIE_SHOW_TIME_STARTTIME,A.SCREEN_ID,A.MOVIE_SHOW_TIME_ID FROM 
(SELECT  CINEMA_HALL_ID, CINEMA_HALL_SHORT_NAME, CINEMA_HALL_NAME, MOVIE_SHOW_TIME_STARTTIME,SCREEN_ID,MOVIE_SHOW_TIME_ID
FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M 
WHERE 
C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID
AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID
AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID
AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND M.MOVIE_ID=1) A, SCREEN_SEAT SS, BOOKING  B, SHOW_SEAT SE
#OUTER JOIN
WHERE 
SS.SCREEN_CINEMA_HALL_SEAT_ID=A.SCREEN_ID AND SS.SCREEN_SEAT_ID=SE.SCREEN_SEAT_ID
AND B.BOOKING_ID=SE.BOOKING_ID AND B.BOOKING_MOVIE_SHOW_TIME_ID = A.MOVIE_SHOW_TIME_ID
GROUP BY A.MOVIE_SHOW_TIME_ID, A.SCREEN_ID #HAVING COUNT(SEAT_TYPE) */

#FIND ALL SEAT IN A SCREEN
/*SELECT SCREEN_SEAT_ID, SEAT_NUMBER, SEAT_TYPE
FROM CITY C, CINEMA_HALL CH, SCREEN S, MOVIE_SHOW_TIME MST, MOVIE M, SCREEN_SEAT SS
WHERE 
C.CITY_SHORT_NAME = 'KOLK' AND C.CITY_ID =  CH.CINEMA_HALL_CITY_ID
AND CH.CINEMA_HALL_ID = S.SCREEN_CINEMA_HALL_ID
AND MST.MOVIE_SHOW_TIME_SCREEN_ID = S.SCREEN_ID
AND M.MOVIE_ID = MST.MOVIE_SHOW_TIME_MOVIE_ID AND M.MOVIE_ID=1
AND SS.SCREEN_CINEMA_HALL_SEAT_ID = S.SCREEN_ID
AND S.SCREEN_ID=1 AND MST.MOVIE_SHOW_TIME_ID= 1
AND CH.CINEMA_HALL_ID=1 */

/*select c.FAV_CITY, ch.FAV_CINEMA_HALL, cnt.total_seats,
CASE WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 9.00 AND 12.00 THEN "Morning"
              WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 12.01 AND 18.00 THEN "Afternoon"
              WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 18.00 AND 24.00 THEN "Evening"
         END as period
 from 
(select b.BOOKING_MOVIE_SHOW_TIME_ID, count(1) total_seats from BOOKING b, show_seat s where b.booking_id=1 and b.booking_id=s.booking_Id) cnt,
MOVIE_SHOW_TIME mst,
screen s, CINEMA_HALL ch, city c
where cnt.BOOKING_MOVIE_SHOW_TIME_ID = mst.MOVIE_SHOW_TIME_ID
and mst.MOVIE_SHOW_TIME_SCREEN_ID = s.screen_id
and s.SCREEN_CINEMA_HALL_ID = ch.CINEMA_HALL_ID
and ch.CINEMA_HALL_CITY_ID = c.city_id */

select total_seats, a.BOOKING_MOVIE_SHOW_TIME_ID, a.booking_id, sum(ss.price) actualPrice from 
(select count(show_seat_id) as total_seats, b.BOOKING_MOVIE_SHOW_TIME_ID, b.booking_id from BOOKING b, show_seat s
where b.booking_id=1 and b.booking_id=s.booking_Id) a, show_seat s, screen_seat ss
where a.booking_id=s.booking_id
and s.screen_seat_id = ss.SCREEN_SEAT_ID

select c.FAV_CITY, ch.FAV_CINEMA_HALL, cnt.total_seats, actualPrice,
CASE WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 9.00 AND 12.00 THEN "Morning"
              WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 12.01 AND 18.00 THEN "Afternoon"
              WHEN TIME(mst.MOVIE_SHOW_TIME_STARTTIME) BETWEEN 18.00 AND 24.00 THEN "Evening"
         END as period
 from 
(
	select total_seats, a.BOOKING_MOVIE_SHOW_TIME_ID, a.booking_id, sum(ss.price) actualPrice from 
	(select count(show_seat_id) as total_seats, b.BOOKING_MOVIE_SHOW_TIME_ID, b.booking_id from BOOKING b, show_seat s
	where b.booking_id=1 and b.booking_id=s.booking_Id) a, show_seat s, screen_seat ss
	where a.booking_id=s.booking_id
	and s.screen_seat_id = ss.SCREEN_SEAT_ID
    
    ) cnt,
MOVIE_SHOW_TIME mst,
screen s, CINEMA_HALL ch, city c
where cnt.BOOKING_MOVIE_SHOW_TIME_ID = mst.MOVIE_SHOW_TIME_ID
and mst.MOVIE_SHOW_TIME_SCREEN_ID = s.screen_id
and s.SCREEN_CINEMA_HALL_ID = ch.CINEMA_HALL_ID
and ch.CINEMA_HALL_CITY_ID = c.city_id