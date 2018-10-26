-- Get all Game records ordered by time
SELECT *
  FROM Game
  ORDER BY time
  ;

-- Get all Game records for the past week
SELECT *
  FROM Game
  WHERE time > '2018-10-19 00:00:00'
  ;

-- Get at Player records with not NULL names
SELECT *
  FROM Player
  WHERE name is not NULL
  ;

-- Get all Player IDs who have a game score larger than 2000
SELECT playerID
  FROM PlayerGame
  WHERE score > 2000
  ;

-- Get all Player records for players with Gmail accounts
SELECT *
  FROM Player
  WHERE emailAddress LIKE '%gmail.com'
  ;

-- Get all of "The King"'s scores in decreasing order
SELECT score
  FROM Player, PlayerGame
  WHERE Player.ID = PlayerGame.playerID
  AND Player.name = 'The King'
  ORDER BY PlayerGame.score desc
  ;

-- Get the winner of the 2006-06-28 13:20:00 Game
SELECT name
  FROM Player, PlayerGame, Game
  WHERE Game.time = '2006-06-28 13:20:00'
  AND Game.ID = PlayerGame.gameID
  AND PlayerGame.playerID = Player.ID
  ORDER BY score desc
  LIMIT 1
  ;

--Answer to 8.2.c: The P1.ID < P2.ID makes sure that the two players who share names are not the same player, since the ID must be distinct

--Answer to 8.2.d: Maybe searching for how many time a certain player has lost to a different certain player