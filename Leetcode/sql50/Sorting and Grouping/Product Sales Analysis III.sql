/**
 * @author onyxwizard
 * @date 05-02-2026
 */
SELECT
  s.product_id,
  s.year       AS first_year,
  s.quantity,
  s.price
FROM
  Sales s
WHERE
  (s.product_id, s.year) IN (
    SELECT
      product_id,
      MIN(YEAR)
    FROM
      Sales
    GROUP BY
      product_id
  );