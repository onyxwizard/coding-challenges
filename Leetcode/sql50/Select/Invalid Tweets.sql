/**
 * @author onyxwizard
 * @date 30-01-2026
 */

SELECT 
    tweet_id
FROM 
    Tweets
WHERE 
    LENGTH(content) > 15;