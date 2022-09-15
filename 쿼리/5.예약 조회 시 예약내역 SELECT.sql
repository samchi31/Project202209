* 예약 조회 시 예약T, 항공편T, 비행기T 정보 select

SELECT  A.RESERV_ID     AS  예약코드,
        A.SEAT_NO       AS  좌석번호,
        A.PASS_NAME     AS  "탑승자 이름",
        A.PASS_PHONE    AS  "탑승자 전화번호",
        A.PASS_REG      AS  "탑승자 생년월일",
        B.COURSE_DL     AS  출발지,
        B.COURSE_DD     AS  출발일,
        B.COURSE_DT     AS  출발시간,
        B.AIRPORT_ID    AS  도착지,
        B.COURSE_DISTANCE  AS  거리,
        B.AIRPLANE_ID      AS  "비행기 코드",
        B.COURSE_PRICE  AS  가격,
        C.MEM_ACCOUNT   AS  지불계좌,
        C.MEM_BANK      AS  지불은행,
        C.MEM_MILEAGE   AS  마일리지
FROM    RESERVATION A, COURSE B, MEMBER C
WHERE   A.RESERV_ID = ?
AND     A.COURSE_ID = B.COURSE_ID
AND     A.MEM_ID = C.MEM_ID
ORDER BY 1;


(자바 코드용 서식)
SELECT
    a.reserv_id AS 예약코드,
    a.seat_no AS 좌석번호,
    a.pass_name AS "탑승자 이름",
    a.pass_phone AS "탑승자 전화번호",
    a.pass_reg AS "탑승자 생년월일",
    b.course_dl AS 출발지,
    b.course_dd AS 출발일,
    b.course_dt AS 출발시간,
    b.airport_id AS 도착지,
    b.course_distance AS 거리,
    b.airplane_id AS "비행기 코드",
    b.course_price AS 가격,
    c.mem_account AS 지불계좌,
    c.mem_bank AS 지불은행,
    c.mem_mileage AS 마일리지
FROM
    reservation a,
    course b,
    member c
WHERE
    a.reserv_id =?
    AND   a.course_id = b.course_id
    AND   a.mem_id = c.mem_id
ORDER BY
    1;
    
    
    
1) 예약코드가 3번인 사람의 예약내역을 가져오세용~
SELECT  A.RESERV_ID     AS  예약코드,
        A.SEAT_NO       AS  좌석번호,
        A.PASS_NAME     AS  "탑승자 이름",
        A.PASS_PHONE    AS  "탑승자 전화번호",
        A.PASS_REG      AS  "탑승자 생년월일",
        B.COURSE_DL     AS  출발지,
        B.COURSE_DD     AS  출발일,
        B.COURSE_DT     AS  출발시간,
        B.AIRPORT_ID    AS  도착지,
        B.COURSE_DISTANCE  AS  거리,
        B.AIRPLANE_ID      AS  "비행기 코드",
        B.COURSE_PRICE  AS  가격,
        C.MEM_ACCOUNT   AS  지불계좌,
        C.MEM_BANK      AS  지불은행,
        C.MEM_MILEAGE   AS  마일리지
FROM    RESERVATION A, COURSE B, MEMBER C
WHERE   A.RESERV_ID = 3
AND     A.COURSE_ID = B.COURSE_ID
AND     A.MEM_ID = C.MEM_ID
ORDER BY 1;