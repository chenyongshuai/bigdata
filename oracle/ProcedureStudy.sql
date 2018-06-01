--û�б���
create or replace procedure sayHelloWorld
as
begin
  dbms_output.put_line('HelloWorld!');
end;
--��һ����
create or replace procedure raisesalary (empno in number,incr in number)
as
b_salary t_emp.e_salary%type;
begin
       select e_salary into b_salary from t_emp where e_id = empno;
       update t_emp set e_salary = e_salary +100 where e_id = empno;
       dbms_output.put_line('��ǰ'||b_salary||'�Ǻ�'||(b_salary+incr));
end;
SELECT * FROM T_EMP;
--����
create or replace function getIncome(e_id in number)
return number
as
  psal t_emp.e_salary%type;
  pcom t_emp.e_loc%type;
begin
  select e_salary,e_loc into psal,pcom from t_emp where e_id = e_id;
  return psal*12+nvl(pcom,0);
end;
--�洢����+����
begin
  dbms_output.put_line('HelloWorld!'||getIncome(1));
end;
/
--�洢���� in + out 
create or replace procedure queryEmpInfo(eno in number,ename out varchar2,sal out number , eloc out varchar2)
as 
begin
   select e_name,e_salary,e_loc into ename,sal,eloc from t_emp where e_id = eno;
end;
--�洢���� ����
declare
       eno number;
       ename varchar2(200);
       sal number;
       eloc varchar2(200);
begin
       eno :=1;
       queryEmpInfo(
           eno =>eno,
           ename =>ename,
           sal =>sal,
           eloc =>eloc
       );
       dbms_output.put_line(eno||ename||sal||eloc);
end;
/
--Merge into
--1.����
CREATE TABLE A_MERGE   
��  
  ID NUMBER  NOT null ��   
  NAME VARCHAR2��12��  NOT null ��   
  AGE NUMBER  
��;  
CREATE TABLE  B_MERGE   
��  
  ID NUMBER  NOT null ��   
  AID NUMBER  NOT null ��   
  NAME VARCHAR2��12��  NOT null ��   
  AGE NUMBER��  
  CITY VARCHAR2��12��  
��;  
CREATE TABLE  C_MERGE   
��  
  ID NUMBER  NOT null ��   
  NAME VARCHAR2��12��  NOT null ��   
  CITY VARCHAR2��12��  NOT null   
��;  
INSERT INTO A_MERGE  VALUES��1��'��ζ' ��20��;   
INSERT INTO A_MERGE  VALUES��2��'�ű�' ��21��;   
INSERT INTO A_MERGE  VALUES��3��'����' ��20��;   
commit;  
  
INSERT INTO B_MERGE  VALUES��1,2��'�ű�' ��30��'����' ��;   
INSERT INTO B_MERGE  VALUES��2,4��'��' ��33��'������' ��;   
INSERT INTO B_MERGE  VALUES��3,3��'����' ,null, 'ɽ��' ��;   
commit;  
Truncate Table A_MERGE;
Truncate Table B_MERGE;
Select * From A_MERGE;
Select * From B_MERGE;
--�洢����
MERGE INTO A_MERGE A USING (select B.AID,B.NAME,B.AGE from B_MERGE B) C ON (A.id=C.AID)  
WHEN MATCHED THEN  
  UPDATE SET A.AGE=C.AGE,A.NAME=C.NAME
WHEN NOT MATCHED THEN  
  INSERT(A.ID,A.NAME,A.AGE) VALUES(C.AID,C.NAME,C.AGE);  
commit;















