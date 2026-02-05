/**
 * @author onyxwizard
 * @date 04-02-2026
 */
SELECT
  DATE_FORMAT(trans_date, '%Y-%m')       AS MONTH,
  country,
  COUNT(state)                           AS trans_count,
  SUM(state = 'approved')                AS approved_count,
  SUM(amount)                            AS trans_total_amount,
  SUM(IF(state = 'approved', amount, 0)) AS approved_total_amount
FROM
  Transactions
GROUP BY
  DATE_FORMAT(trans_date, '%Y-%m'),
  country