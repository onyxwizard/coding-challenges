/**
 * @author onyxwizard
 * @date 25-02-2026
 */

WITH latest_changes AS (
    SELECT 
        product_id,
        MAX(change_date) AS latest_date
    FROM Products
    WHERE change_date <= '2019-08-16'
    GROUP BY product_id
)
SELECT 
    lc.product_id,
    p.new_price AS price
FROM latest_changes lc
JOIN Products p ON lc.product_id = p.product_id AND lc.latest_date = p.change_date

UNION

SELECT 
    product_id,
    10 AS price
FROM Products
WHERE product_id NOT IN (
    SELECT product_id
    FROM Products
    WHERE change_date <= '2019-08-16'
);