/**
 * @author onyxwizard
 * @date 25-02-2026
 */

SELECT 
    category,
    SUM(cnt) AS accounts_count
FROM (
    SELECT 
        CASE 
            WHEN income < 20000 THEN 'Low Salary'
            WHEN income <= 50000 THEN 'Average Salary'
            ELSE 'High Salary'
        END AS category,
        1 AS cnt
    FROM Accounts
) AS categorized
GROUP BY category
UNION ALL
SELECT category, 0 FROM (
    SELECT 'Low Salary' AS category
    UNION ALL SELECT 'Average Salary'
    UNION ALL SELECT 'High Salary'
) AS all_categories
WHERE category NOT IN (SELECT DISTINCT category FROM categorized);