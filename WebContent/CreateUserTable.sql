--테이블 삭제
drop table users;

--시퀀스 삭제
drop sequence seq_users_no;

--테이블 생성
create table users(
    no          number,
    id          varchar2(20) unique not null,
    password    varchar2(20) not null,
    name        varchar2(20),
    gender      varchar2(10),
    primary key(no)
);

--시퀀스 생성
create sequence seq_users_no
increment by 1 start with 1;

--insert문 2개 male, female
insert into users
values (seq_users_no.nextval, 'hiy', '1234', '황일영', 'male');
insert into users
values (seq_users_no.nextval, 'lhl', '1234', '이효리', 'female');

--commit
commit;

--테이블보기
select *
from users;