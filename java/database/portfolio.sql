CREATE TABLE portfolio (
  portfolio_id serial,
  user_id int  NOT NULL,
  game_id int Not Null,
  cash_balance decimal(13,2) not null,
  portfolio_value decimal(13,2) not null,
  constraint PK_portfolio primary key (portfolio_id),
  constraint FK_portfolio_user_id foreign key (user_id) references users(user_id),
  constraint FK_portfolio_game_id foreign key (game_id) references games(game_id),
  constraint CK_portfolio_cash_balance_gt_0 check ((cash_balance>0)),
  constraint CK_portfolio_portfolio_value_gt_0 check ((portfolio_value>0))
);