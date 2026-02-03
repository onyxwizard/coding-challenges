/**
 * @author onyxwizard
 * @date 30-01-2026
 */

SELECT 
    name
FROM 
    Customer
WHERE 
    referee_id IS NULL 
    OR referee_id != 2;