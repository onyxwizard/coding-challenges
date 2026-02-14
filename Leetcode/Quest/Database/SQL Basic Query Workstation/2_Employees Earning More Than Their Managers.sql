/**
 * @author onyxwizard
 * @date 13-02-2026
 */

SELECT 
    e.name AS Employee
FROM 
  Employee e
INNER JOIN Employee m 
  ON e.managerId = m.id
WHERE e.salary > m.salary;