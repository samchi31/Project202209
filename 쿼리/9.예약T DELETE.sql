* ���� ��� �� �������̺� ����
DELETE FROM RESERVATION
WHERE RESERVE_ID = '�����ȣ';

(�ڹ� �ڵ�� ����)
DELETE FROM reservation WHERE
    reserv_id = ?;

1) �ſ��� �� �ڽ� ���� ��Ҹ� �Ѵٰ�?! �������̤̹̾��ؤ̤̳��� �߸��߾�..
����ư 2022091400001�̴�.

DELETE FROM RESERVATION
WHERE RESERV_ID = '2022091400001';

2) ������ 2022091400002

DELETE FROM RESERVATION
WHERE RESERV_ID = '2022091400002';

ROLLBACK;

DROP TABLE RESERVATION;