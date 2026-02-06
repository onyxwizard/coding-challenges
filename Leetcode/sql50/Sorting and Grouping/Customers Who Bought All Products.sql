/**
 * @author onyxwizard
 * @date 05-02-2026
 */
WITH
  store_product_count AS (
    SELECT
      COUNT(*) AS total_products
    FROM
      Product
  )
SELECT
  customer_id
FROM
  Customer
GROUP BY
  customer_id
HAVING
  COUNT(DISTINCT product_key) = (
    SELECT
      total_products
    FROM
      store_product_count
  );