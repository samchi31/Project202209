-- 잔여좌석 = TOTAL - 예약 수
-- 예약 수 = COURSE_ID 별로
-- COURSE의 AIRPLANE_ID 와 AIRPLANE의 AIRPLANE_ID
UPDATE airplane a
    SET
        ( a.seat_remain ) = (
            SELECT
                a.seat_total - b.bsum
            FROM
                (
                    SELECT
                        COUNT(c.reserv_id) AS bsum,
                        d.airplane_id AS bid
                    FROM
                        reservation c,
                        course d
                    WHERE
                        c.course_id = d.course_id
                    GROUP BY
                        d.airplane_id
                ) b
            WHERE
                b.bid = a.airplane_id
        );