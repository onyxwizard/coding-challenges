/**
 * @author onyxwizard
 * @date 04-02-2026
 */
SELECT
  p.project_id,
  ROUND(
    SUM(e.experience_years) / COUNT(e.experience_years),
    2
  ) AS average_years
FROM
  Project p
  LEFT JOIN Employee e ON e.employee_id = p.employee_id
GROUP BY
  p.project_id
ORDER BY
  p.project_id;