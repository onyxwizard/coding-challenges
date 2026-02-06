/**
 * @author onyxwizard
 * @date 05-02-2026
 */
SELECT
  MAX(num) AS num
FROM
  (
    SELECT
      num
    FROM
      MyNumbers
    GROUP BY
      num
    HAVING
      COUNT(*) = 1 AND
      num > (
        SELECT
          AVG(num)
        FROM
          MyNumbers
      ) -- Filter early
  ) AS singles;