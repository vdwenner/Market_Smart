create table game_users(
game_id int NOT NULL,
user_id int NOT NULL,
constraint FK_game_users_game_id foreign key (game_id) references games (game_id),
constraint FK_game_users_user_id foreign key (user_id) references users (user_id));