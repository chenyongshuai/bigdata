create table emp(
   id integer,
   name varchar2(50),
   salary number,
   dept varchar2(50)
);
Select * from emp;
insert into emp values(1,'张三',1000,'001');
insert into emp values(2,'lisi',2000,'001');
insert into emp values(3,'王五',3000,'002');
insert into emp values(4,'zl',4000,'002');
insert into emp values(5,'tq',5000,'003');
insert into emp values(6,'dd',6000,'003');
insert into emp values(7,'bbv',7000,'004');
insert into emp values(8,'fdsa',8000,'004');
insert into emp values(9,'dss',1000,'001');

CREATE TABLE dept_sal AS
SELECT dept, COUNT(id) total_emp, SUM(salary) total_sal
FROM emp
GROUP BY dept;
Select * from dept_sal
Select to_char(sysdate,'DY') from dual

--触发器
CREATE OR REPLACE TRIGGER T_EMP_INFO
 AFTER INSERT OR UPDATE OR DELETE ON EMP
DECLARE
 CURSOR CUR_EMP IS
  SELECT dept, COUNT(ID) AS TOTAL_EMP, SUM(SALARY) AS TOTAL_SAL FROM EMP GROUP BY dept;
BEGIN
 IF(to_char(sysdate,'DY')='星期六') THEN
 DELETE DEPT_SAL; --触发时首先删除映射表信息
 FOR V_EMP IN CUR_EMP LOOP
  DBMS_OUTPUT.PUT_LINE(v_emp.dept || v_emp.total_emp || v_emp.total_sal);
  --插入数据
  INSERT INTO DEPT_SAL
  VALUES
   (V_EMP.dept, V_EMP.TOTAL_EMP, V_EMP.TOTAL_SAL);
 END LOOP;
 END IF;
END;


--插入数据测试
Select * from emp where dept = '001'
INSERT INTO emp(id,name,salary,dept) VALUES(13,'we',10000,'001');
Select * from DEPT_SAL


--存储过程
CREATE OR REPLACE PROCEDURE P_EMP_INFO AS
 CURSOR CUR_EMP IS 
  SELECT DEPT, COUNT(ID) AS TOTAL_EMP, SUM(SALARY) AS TOTAL_SAL FROM EMP GROUP BY DEPT;
BEGIN
 DELETE DEPT_SAL;
 FOR V_EMP IN CUR_EMP LOOP
  INSERT INTO DEPT_SAL VALUES (V_EMP.dept, V_EMP.TOTAL_EMP, V_EMP.TOTAL_SAL);
 END LOOP;
END;

var myjob number; 
begin dbms_job.submit(1001,'P_EMP_INFO;',sysdate,'TRUNC(sysdate,’mi’)+1/(24*60)'); 
end;   

var v_sn number;
Begin
    Dbms_job.submit(:v_sn,
        'P_EMP_INFO',
        sysdate,
        'TRUNC(sysdate,’mi’)+1/(24*60)');
    commit;
end;
/
--job
DECLARE job NUMBER;   
begin
   Dbms_job.submit(job,'P_EMP_INFO;',sysdate,TRUNC(sysdate,'mi')+1/(24*60));
   commit;
end;











