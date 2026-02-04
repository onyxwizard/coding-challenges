/**
 * @author onyxwizard
 * @date 03-02-2026
 */
SELECT 
    m.name
FROM 
    Employee e
INNER JOIN 
    Employee m ON e.managerId = m.id
WHERE 
    e.managerId IS NOT NULL
GROUP BY 
    m.id, m.name
HAVING 
    COUNT(*) >= 5;