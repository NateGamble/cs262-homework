-- Get the number of Game records.
SELECT *
  FROM Game
  ;

SELECT *
  FROM Property
  ;

-- Get the player records.
SELECT * 
  FROM Player
  ;

SELECT *
  FROM PlayerPropertyGame
  ;

-- Get all the users with Calvin email addresses.
SELECT *
  FROM Player
 WHERE emailAddress LIKE '%calvin%'
 ;

-- Get the highest score ever recorded.
  SELECT score
    FROM PlayerGame
ORDER BY score DESC
   LIMIT 1
   ;

-- Get the cross-product of all the tables.
SELECT *
  FROM Player, PlayerGame, Game, Property, PlayerPropertyGame
  ;


