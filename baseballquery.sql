USE baseball;
INSERT INTO manufacturers (`name`) VALUES ('Upper Deck');
select * from manufacturers;
INSERT INTO cards (card_number, players_name, `year`, manufactuer_id) VALUES (1, 'Heath', 1981, 1);
select * from cards;
select * from manufacturers m
INNER JOIN CARDS c ON c.manufactuer_id = m.manufactId
WHERE cardId = 1;
select * from cards where manufactuer_id = 1;