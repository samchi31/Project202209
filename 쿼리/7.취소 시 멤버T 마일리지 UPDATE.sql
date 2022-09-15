* 마일리지 취소해서 삭제할 때

update MEMBER
set MEM_MILEAGE = MEM_MILEAGE - (SELECT SUM(COURSE_PRICE * 0.01) AS 거리비율마일리지
                                   FROM COURSE A, RESERVATION B
                                  WHERE A.COURSE_ID=B.COURSE_ID
                                    AND B.mem_id = 'A001'
                                  GROUP BY A.COURSE_PRICE)
WHERE MEM_ID = 'A001';     