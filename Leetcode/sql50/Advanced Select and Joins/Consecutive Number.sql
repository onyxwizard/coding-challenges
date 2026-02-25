/**
 * @author onyxwizard
 * @date 25-02-2026
 */

SELECT DISTINCT num AS ConsecutiveNums
FROM (
    SELECT 
        num,
        LEAD(num, 1) OVER (ORDER BY id) AS next_num,
        LEAD(num, 2) OVER (ORDER BY id) AS next_next_num
    FROM Logs
) t
WHERE num = next_num AND num = next_next_num;