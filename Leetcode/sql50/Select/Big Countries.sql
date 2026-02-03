/**
 * @author onyxwizard
 * @date 30-01-2026
 */

SELECT 
    name,
    population,
    area
FROM 
    World
WHERE 
    area >= 3000000 
    OR population >= 25000000;