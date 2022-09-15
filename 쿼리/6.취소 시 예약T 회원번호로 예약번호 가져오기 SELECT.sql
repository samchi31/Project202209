* 취소 시 예약테이블 회원번호로 예약번호 가져오기

select reserv_id
  from resevation a, member b
 where a.mem_id = b.mem_id
   and mem_id = 'A001';