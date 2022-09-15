* 예약 시 예약테이블에 정보 입력

CREATE TABLE reservation (
    reserv_id     VARCHAR2(50),
    mem_id        VARCHAR2(50),
    course_id     VARCHAR2(50),
    seat_no       VARCHAR2(50),
    accom_name    VARCHAR2(50),
    accom_phone   VARCHAR2(50),
    accom_reg     VARCHAR2(50)
);

ALTER TABLE RESERVATION RENAME COLUMN ACCOM_NAME TO PASS_NAME;
ALTER TABLE RESERVATION RENAME COLUMN ACCOM_PHONE TO PASS_PHONE;
ALTER TABLE RESERVATION RENAME COLUMN ACCOM_REG TO PASS_REG;

INSERT INTO RESERVATION (RESERV_ID, MEM_ID, COURSE_ID, SEAT_NO, ACCOM_NAME, ACCOM_PHONE, ACCOM_REG)
VALUES (
    '예약 코드',
    '회원ID',
    '항공편 코드',
    '좌석번호',
    '탑승자 이름',
    '탑승자 전화번호',
    '탑승자 생년월일'
);

(자바 코드용 서식)
INSERT INTO reservation (
    reserv_id,
    mem_id,
    course_id,
    seat_no,
    pass_name,
    pass_phone,
    pass_reg
) VALUES (
    '?',
    '?',
    '?',
    '?',
    '?',
    '?',
    '?'
);

1) (주) 해외도조의 첫 예약이 들어왔습니다. 얼렁 돈줄을 붙잡으십시오. 그는 A001로 로그인한 신우혁씨입니다.
1998년 02월 22일에 태어나셔서 아직 생일이 멀었군요. 생일마 일리지는 생각도 마세요! 그는 P2022001항공편을 이용합니다.
235-131-123456에서 돈이 날라올 것입니다 흐흐. 그들의 탑승자는 1명으로 혼자 가는군요. 그의 전화번호는 01012345678입니다.

INSERT INTO RESERVATION
VALUES ('2022091400001','A001','신우혁','P2022001', '좌석번호', '01012345678', '980222');

2)2022091400002, A001, 이동진, P2022002, , 01012345679, 960915

INSERT INTO RESERVATION
VALUES ('2022091400002','A001','이동진','P2022002', '좌석번호', '01012345679', '960915');



