* ���� �� �������̺� ���� �Է�

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
    '���� �ڵ�',
    'ȸ��ID',
    '�װ��� �ڵ�',
    '�¼���ȣ',
    'ž���� �̸�',
    'ž���� ��ȭ��ȣ',
    'ž���� �������'
);

(�ڹ� �ڵ�� ����)
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

1) (��) �ؿܵ����� ù ������ ���Խ��ϴ�. �� ������ �������ʽÿ�. �״� A001�� �α����� �ſ������Դϴ�.
1998�� 02�� 22�Ͽ� �¾�ż� ���� ������ �־�����. ���ϸ� �ϸ����� ������ ������! �״� P2022001�װ����� �̿��մϴ�.
235-131-123456���� ���� ����� ���Դϴ� ����. �׵��� ž���ڴ� 1������ ȥ�� ���±���. ���� ��ȭ��ȣ�� 01012345678�Դϴ�.

INSERT INTO RESERVATION
VALUES ('2022091400001','A001','�ſ���','P2022001', '�¼���ȣ', '01012345678', '980222');

2)2022091400002, A001, �̵���, P2022002, , 01012345679, 960915

INSERT INTO RESERVATION
VALUES ('2022091400002','A001','�̵���','P2022002', '�¼���ȣ', '01012345679', '960915');



