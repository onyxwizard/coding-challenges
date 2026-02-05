/**
 * @author onyxwizard
 * @date 04-02-2026
 */
SELECT
  *
FROM
  Cinema c
WHERE
  MOD(c.id, 2) != 0 AND
  c.description != 'boring'
ORDER BY
  c.rating DESC;