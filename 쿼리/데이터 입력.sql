--비행기명, 총좌석, 남은좌석, 항공사
insert into airplane(AIRPLANE_ID, SEATTOTAL, SEATREMAIN, AIRLINE)
values ('', 20, 20 , '');

--공항아이디, 공항이름, 나라, 주소
INSERT INTO AIRPORT(AIRPORT_ID, AIRPORT_NAME, NATION, ADDRESS)
VALUES('','','','');

--항공편 코드, 출발지, 도착지, 출발일, 출발시간, 도착지, 비행기 코드, 가격, 거리
INSERT INTO COURSE (COURSE_ID, COURSE_DL, AIRPORT_ID, COURSE_DD, COURSE_DT, AIRPLANE_ID, COURSE_PRICE, COURSE_DISTANCE) 
VALUES ('P2022001', 'ICN', 'PEK', to_date('20220904'), 1400, 'a100', 'KE', 350000, 914);

--회원ID, 이름, 생년월일, 계좌번호, 은행, 마일리지
INSERT INTO MEMBER (MEM_ID, MEM_NAME, MEM_REG, MEM_ACCOUNT, MEM_BANK, MEM_MILEAGE)
VALUES ('', '', , , '', );

--예약코드, 회원ID, 좌석번호, 항공편코드, 탑승자 이름, 탑승자 전화번호, 탑승자 생년월일
INSERT INTO reservation (RESERV_ID, MEM_ID, SEAT_NO, COURSE_ID, PASS_NAME, PASS_PHONE, PASS_REG) 
VALUES ('1', 'B001', '류현진', '980222', 'P2022001', 'F10', '01012345678');
