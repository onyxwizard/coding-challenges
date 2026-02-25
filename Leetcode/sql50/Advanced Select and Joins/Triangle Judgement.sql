/**
 * @author onyxwizard
 * @date 25-02-2026
 */

SELECT 
    x, y, z,
    CASE 
        WHEN GREATEST(x, y, z) < (x + y + z - GREATEST(x, y, z)) 
        THEN 'Yes' 
        ELSE 'No' 
    END AS triangle
FROM Triangle;