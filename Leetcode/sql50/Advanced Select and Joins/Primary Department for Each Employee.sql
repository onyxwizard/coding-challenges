/**
 * @author onyxwizard
 * @date 09-02-2026
 */
-- Employees with explicit primary flag
SELECT
  employee_id,
  department_id
FROM
  Employee
WHERE
  primary_flag = 'Y'
UNION
-- Employees with only one department
SELECT
  employee_id,
  department_id
FROM
  Employee
WHERE
  employee_id IN (
    SELECT
      employee_id
    FROM
      Employee
    GROUP BY
      employee_id
    HAVING
      COUNT(*) = 1
  );