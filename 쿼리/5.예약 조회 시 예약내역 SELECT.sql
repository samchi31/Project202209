* ���� ��ȸ �� ����T, �װ���T, �����T ���� select

SELECT  A.RESERV_ID     AS  �����ڵ�,
        A.SEAT_NO       AS  �¼���ȣ,
        A.PASS_NAME     AS  "ž���� �̸�",
        A.PASS_PHONE    AS  "ž���� ��ȭ��ȣ",
        A.PASS_REG      AS  "ž���� �������",
        B.COURSE_DL     AS  �����,
        B.COURSE_DD     AS  �����,
        B.COURSE_DT     AS  ��߽ð�,
        B.AIRPORT_ID    AS  ������,
        B.COURSE_DISTANCE  AS  �Ÿ�,
        B.AIRPLANE_ID      AS  "����� �ڵ�",
        B.COURSE_PRICE  AS  ����,
        C.MEM_ACCOUNT   AS  ���Ұ���,
        C.MEM_BANK      AS  ��������,
        C.MEM_MILEAGE   AS  ���ϸ���
FROM    RESERVATION A, COURSE B, MEMBER C
WHERE   A.RESERV_ID = ?
AND     A.COURSE_ID = B.COURSE_ID
AND     A.MEM_ID = C.MEM_ID
ORDER BY 1;


(�ڹ� �ڵ�� ����)
SELECT
    a.reserv_id AS �����ڵ�,
    a.seat_no AS �¼���ȣ,
    a.pass_name AS "ž���� �̸�",
    a.pass_phone AS "ž���� ��ȭ��ȣ",
    a.pass_reg AS "ž���� �������",
    b.course_dl AS �����,
    b.course_dd AS �����,
    b.course_dt AS ��߽ð�,
    b.airport_id AS ������,
    b.course_distance AS �Ÿ�,
    b.airplane_id AS "����� �ڵ�",
    b.course_price AS ����,
    c.mem_account AS ���Ұ���,
    c.mem_bank AS ��������,
    c.mem_mileage AS ���ϸ���
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
    
    
    
1) �����ڵ尡 3���� ����� ���೻���� ����������~
SELECT  A.RESERV_ID     AS  �����ڵ�,
        A.SEAT_NO       AS  �¼���ȣ,
        A.PASS_NAME     AS  "ž���� �̸�",
        A.PASS_PHONE    AS  "ž���� ��ȭ��ȣ",
        A.PASS_REG      AS  "ž���� �������",
        B.COURSE_DL     AS  �����,
        B.COURSE_DD     AS  �����,
        B.COURSE_DT     AS  ��߽ð�,
        B.AIRPORT_ID    AS  ������,
        B.COURSE_DISTANCE  AS  �Ÿ�,
        B.AIRPLANE_ID      AS  "����� �ڵ�",
        B.COURSE_PRICE  AS  ����,
        C.MEM_ACCOUNT   AS  ���Ұ���,
        C.MEM_BANK      AS  ��������,
        C.MEM_MILEAGE   AS  ���ϸ���
FROM    RESERVATION A, COURSE B, MEMBER C
WHERE   A.RESERV_ID = 3
AND     A.COURSE_ID = B.COURSE_ID
AND     A.MEM_ID = C.MEM_ID
ORDER BY 1;