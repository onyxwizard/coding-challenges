/**
 * @author onyxwizard
 * @date 13-02-2026
 */

SELECT 
  name
FROM 
  Customer
WHERE 
  referee_id IS NULL 
  OR 
  referee_id != 2;