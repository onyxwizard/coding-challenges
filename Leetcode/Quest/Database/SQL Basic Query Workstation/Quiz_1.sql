/**
 * @author onyxwizard
 * @date 13-02-2026
 */

SELECT 
    e.employee_id
FROM
    Employees e
WHERE e.salary < 30000
    AND e.manager_id NOT IN (
        SELECT
            m.employee_id
        FROM 
            Employees m
    )
ORDER BY e.employee_id;

-- OR --

# Write your MySQL query statement below
with manager AS (
    SELECT
        m.employee_id
    FROM 
        Employees m
)
SELECT 
    e.employee_id
FROM
    Employees e
WHERE e.salary < 30000
    AND e.manager_id IS NOT NULL
    AND e.manager_id NOT IN (
      SELECT 
        * 
        FROM 
          manager
          )
ORDER BY e.employee_id;