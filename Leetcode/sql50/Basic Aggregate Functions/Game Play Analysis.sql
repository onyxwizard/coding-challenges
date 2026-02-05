/**
 * @author onyxwizard
 * @date 04-02-2026
 */
WITH
  first_login AS (
    SELECT
      player_id,
      MIN(event_date) AS first_login_date
    FROM
      Activity
    GROUP BY
      player_id
  )
SELECT
  ROUND(COUNT(DISTINCT a.player_id) / COUNT(*), 2) AS fraction
FROM
  Activity a
  RIGHT JOIN first_login f ON a.player_id = f.player_id AND
  DATEDIFF(a.event_date, f.first_login_date) = 1;

-- ---------------------------------------- OR APPROACH 2 ----------------------------------
WITH
  first_logins AS (
    SELECT
      A.player_id,
      MIN(A.event_date) AS first_login
    FROM
      Activity A
    GROUP BY
      A.player_id
  ),
  consec_logins AS (
    SELECT
      COUNT(A.player_id) AS num_logins
    FROM
      first_logins F
      INNER JOIN Activity A ON F.player_id = A.player_id AND
      F.first_login = DATE_SUB(A.event_date, INTERVAL 1 DAY)
  )
SELECT
  ROUND(
    (
      SELECT
        C.num_logins
      FROM
        consec_logins C
    ) / (
      SELECT
        COUNT(F.player_id)
      FROM
        first_logins F
    ),
    2
  ) AS fraction;