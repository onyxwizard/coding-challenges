/**
 * @author onyxwizard
 * @date 13-02-2026
 */

SELECT 
    p.firstName,
    p.lastName,
    a.city,
    a.state
FROM Person p
LEFT JOIN Address a ON p.personId = a.personId;