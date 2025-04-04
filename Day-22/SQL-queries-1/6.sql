/* Retrieve the names and salaries of employees who earn between 1000 and 3000 (inclusive).

Expected Output Columns:
+--------+----------+
| ename  | sal      |
+--------+----------+

*/
USE test;
SELECT ename , sal FROM emp 
WHERE sal BETWEEN 1000 AND 3000;