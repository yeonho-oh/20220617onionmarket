
/* SPRING SECURITY 를 적용할 경우
 *  비밀번호 암호화 및 권한 부여를 위해  
 * 회원 가입을 통한 서비스(service layer)에서  등록하도록 한다 ( 회원 및 권한 테이블에 모두 등록되어야 하므로 트랜잭션 처리 필요!)
 */
drop table Authority;
drop table security_member;
-- enabled : 탈퇴여부(0은 탈퇴, 1은 가입상태) 
create table security_member(
	member_id varchar2(100) primary key,
	member_password varchar2(100) not null,
	member_name varchar2(100) not null,
	member_address varchar2(100) not null,
	enabled number default 1 not null 
)
insert into security_member values('java', 'a','아이유', '오리', 1)

delete from security_member;
commit

/*  
     한명의 회원은 여러개의 권한(1:n)을 보유할 수 있어야 하므로 별도로 권한authorities 테이블을 생성한다 
 */
drop table Authority;

create table Authority(
	username varchar2(100) not null,
	authority varchar2(30) not null,
	constraint fk_authorities foreign key(username) references security_member(member_id),
	constraint member_authorities primary key(username,authority)
)
delete from Authority;

-- 비밀번호 암호화해서 등록해야 하므로 어플리케이션에서 회원가입을 이용해 등록해서 확인한다 
select * from security_member;
select * from Authority;

-- 어플리케이션에서 회원가입을 하면 ROLE_MEMBER가 등록된다 
-----------------------------------------------------------
--  인가 테스트를 위해 관리자 권한을 추가한 후
--  AdminController의 @Secured("ROLE_ADMIN") 메서드를 브라우저로 테스트 해본다
insert into  security_member(member_id , member_password , member_name , member_address , enabled)
values ('king' , 'a' , '왕' , '한국' , 1);
insert into Authority(username,authority) values('king','ROLE_ADMIN');
commit





