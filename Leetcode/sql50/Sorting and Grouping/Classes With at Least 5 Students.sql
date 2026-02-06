/**
 * @author onyxwizard
 * @date 05-02-2026
 */
SELECT
  class
FROM
  Courses
GROUP BY
  Class
HAVING
  COUNT(*) >= 5;