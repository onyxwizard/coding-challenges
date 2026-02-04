/**
 * @author onyxwizard
 * @date 30-01-2026
 */

SELECT 
    eu.unique_id,
    e.name
FROM 
    Employees e
LEFT JOIN 
    EmployeeUNI eu ON e.id = eu.id;