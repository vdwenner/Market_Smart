CREATE TABLE game_invites (
  sender_id int,
  receiver_id int,
  game_id int,
  game_invite_type_id int,
  
  constraint FK_game_invites_sender_id foreign key(sender_id) references users(user_id),
  constraint FK_game_invites_receiver_id foreign key(receiver_id) references users(user_id),
  constraint FK_game_invites_game_id foreign key(game_id) references games(game_id),
  constraint FK_game_invites_game_invite_type_id foreign key(game_invite_type_id) references invite_types(invite_type_id)
);
