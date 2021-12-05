create Table games (
  game_id serial NOT NULL,
  game_name varchar(200) NOT NUll,
  creator_id int NOT NULL,
  starting_amount int NOT NULL,
  end_date date NOT NULL,
  
  CONSTRAINT PK_games PRIMARY KEY (game_id),
  constraint FK_games_creator_id foreign key(creator_id) references users(user_id),
  constraint CK_games_starting_amount_gt_0 check ((starting_amount >0)) 

);
