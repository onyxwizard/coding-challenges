/**
 * @author onyxwizard
 * @date 13-02-2026
 */

SELECT 
  *
FROM 
  Cinema
WHERE id % 2 = 1
  AND description != 'boring'
ORDER BY rating DESC;