# -----------------------------------------

use test;

SET GLOBAL log_bin_trust_function_creators = 1;

DROP function IF EXISTS getSalGrade;
DELIMITER $$
 
CREATE function getSalGrade(p_empNo int) returns varchar(10)
	DETERMINISTIC
BEGIN
    DECLARE empSal double;
    DECLARE empSalGrade varchar(10);
 
    SELECT sal INTO empSal
    FROM emp
    WHERE empno = p_empNo;
 
    IF empSal > 3000 THEN
      SET empSalGrade = 'HIGH';
    ELSEIF (empSal > 1000 && empSal <= 3000 ) THEN
      SET empSalGrade = 'MEDIUM';
    ELSEIF (empSal <= 1000) THEN
      SET empSalGrade = 'LOW';
    END IF;

    return empSalGrade; 
END$$


DELIMITER ;

select getSalGrade(7839) from emp where empno = 7839;
select sal, getSalGrade(7369) from emp where empno = 7369;
select sal, getSalGrade(7698) from emp where empno = 7698;

SHOW FUNCTION STATUS WHERE Db = 'test';


DROP function IF EXISTS getDeptSal;
DELIMITER $$
 
CREATE function getDeptSal(dno int) returns double
BEGIN
    DECLARE empSal double;
 
	select sum(sal) INTO empSal from emp where deptno = dno;
	return empSal; 
END$$

DELIMITER ;

select getDeptSal(20);


USE test;

SET GLOBAL log_bin_trust_function_creators = 1;

DROP FUNCTION IF EXISTS getEmpDetails;
DELIMITER $$

CREATE FUNCTION getEmpDetails(p_empNo INT) RETURNS VARCHAR(255)
BEGIN
    DECLARE empDetails VARCHAR(255);

    SELECT CONCAT('empno: ', empno, ', ename: ', ename, ', job: ', job, ', mgr: ', IFNULL(mgr, 'NULL'),  ', sal: ', sal, ', comm: ', IFNULL(comm, 'NULL'), ', deptno: ', deptno)
    INTO empDetails
    FROM emp
    WHERE empno = p_empNo;

    RETURN empDetails;
END$$

DELIMITER ;

-- Example usage:
SELECT getEmpDetails(7839);
SELECT getEmpDetails(7369);
SELECT getEmpDetails(7698);