/**
 * @author onyxwizard
 * @date 05-02-2026
 */
SELECT
  teacher_id,
  COUNT(DISTINCT subject_id) AS cnt
FROM
  Teacher
GROUP BY
  teacher_id;