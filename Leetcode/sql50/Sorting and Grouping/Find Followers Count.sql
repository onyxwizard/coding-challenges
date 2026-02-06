/**
 * @author onyxwizard
 * @date 05-02-2026
 */
SELECT
  user_id,
  COUNT(*) AS followers_count
FROM
  Followers
GROUP BY
  user_id
ORDER BY
  user_id ASC;