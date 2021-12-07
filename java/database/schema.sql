BEGIN TRANSACTION;

DROP TABLE IF EXISTS transactions;
DROP SEQUENCE IF EXISTS seq_transaction_id;
DROP TABLE IF EXISTS transaction_types;
DROP TABLE IF EXISTS game_invites;
DROP TABLE IF EXISTS invite_types;
DROP TABLE IF EXISTS stock CASCADE;
DROP TABLE IF EXISTS portfolio CASCADE;
DROP SEQUENCE IF EXISTS seq_portfolio_id CASCADE;
DROP TABLE IF EXISTS portfolio_stock CASCADE;
DROP TABLE IF EXISTS game_users CASCADE;
DROP TABLE IF EXISTS games CASCADE;
DROP SEQUENCE IF EXISTS seq_game_id CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id CASCADE;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_game_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_portfolio_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_transaction_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,

	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE games (
  game_id int DEFAULT nextval('seq_game_id'::regclass) NOT NULL,
  game_name varchar(200) NOT NUll,
  creator_id int NOT NULL,
  starting_amount int NOT NULL,
  end_date date NOT NULL,
  
  CONSTRAINT PK_games PRIMARY KEY (game_id),
  CONSTRAINT FK_games_creator_id FOREIGN KEY (creator_id) REFERENCES users(user_id),
  CONSTRAINT CK_games_starting_amount_gt_0 CHECK ((starting_amount > 0)) 
);

CREATE TABLE game_users(
game_id int NOT NULL,
user_id int NOT NULL,

CONSTRAINT FK_game_users_game_id FOREIGN KEY (game_id) REFERENCES games (game_id),
CONSTRAINT FK_game_users_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE portfolio (
  portfolio_id int DEFAULT nextval('seq_portfolio_id'::regclass) NOT NULL,
  user_id int  NOT NULL,
  game_id int NOT Null,
  cash_balance decimal (13, 2) NOT NULL,
  portfolio_value decimal (13, 2) NOT NULL,

  CONSTRAINT PK_portfolio PRIMARY KEY (portfolio_id),
  CONSTRAINT FK_portfolio_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
  CONSTRAINT FK_portfolio_game_id FOREIGN KEY (game_id) REFERENCES games(game_id),
  CONSTRAINT CK_portfolio_cash_balance_gt_0 CHECK ((cash_balance > 0)),
  CONSTRAINT CK_portfolio_portfolio_value_gt_0 CHECK ((portfolio_value > 0))
);

CREATE TABLE stock (
  stock_symbol varchar NOT NULL,
  stock_name varchar NOT NULL,

  CONSTRAINT PK_stock PRIMARY KEY (stock_symbol)
  
);

CREATE TABLE portfolio_stock (
  portfolio_id int NOT NULL,
  stock_symbol varchar(25) NOT NULL,
  quantity int NOT NULL,
  average_price decimal(8, 2) NOT NULL,
  
  CONSTRAINT FK_portfolio_stock_portfolio_id FOREIGN KEY (portfolio_id) REFERENCES portfolio (portfolio_id),
  CONSTRAINT FK_portfolio_stock_stock_symbol FOREIGN KEY (stock_symbol) REFERENCES stock (stock_symbol),
  CONSTRAINT CK_portfolio_stock_quantity_gt_0 CHECK ((quantity > 0)),
  CONSTRAINT CK_portfolio_stock_average_price_gt_0 CHECK ((average_price > 0))
  
);


CREATE TABLE invite_types (
  invite_type_id int NOT NULL,
  invite_type_des varchar(10) NOT NULL,

  CONSTRAINT PK_invite_types PRIMARY KEY (invite_type_id)
  
);

CREATE TABLE game_invites (
  sender_id int NOT NULL,
  receiver_id int NOT NULL,
  game_id int NOT NULL,
  game_invite_type_id int NOT NULL,
  
  CONSTRAINT FK_game_invites_sender_id FOREIGN KEY (sender_id) REFERENCES users (user_id),
  CONSTRAINT FK_game_invites_receiver_id FOREIGN KEY (receiver_id) REFERENCES users (user_id),
  CONSTRAINT FK_game_invites_game_id FOREIGN KEY (game_id) REFERENCES games (game_id),
  CONSTRAINT FK_game_invites_game_invite_type_id FOREIGN KEY (game_invite_type_id) REFERENCES invite_types (invite_type_id)
);

CREATE TABLE transaction_types (
 transaction_type_id int NOT NULL, 
 transaction_type_desc varchar(25) NOT NULL,

 CONSTRAINT PK_transaction_types PRIMARY KEY (transaction_type_id)
);

CREATE TABLE transactions (
  transaction_id int DEFAULT nextval('seq_transaction_id'::regclass) NOT NULL,
  transaction_type int NOT NULL,
  price decimal(8,2) NOT NULL,
  portfolio_id int NOT NULL,
  stock_symbol varchar NOT NULL,
  quantity int NOT NULL,
  
  CONSTRAINT PK_transactions PRIMARY KEY (transaction_id),
  CONSTRAINT FK_transactions_transaction_type FOREIGN KEY (transaction_type) REFERENCES transaction_types (transaction_type_id),
  CONSTRAINT FK_transactions_portfolio_id FOREIGN KEY (portfolio_id) REFERENCES portfolio (portfolio_id),
  CONSTRAINT FK_transactions_stock_symbol FOREIGN KEY (stock_symbol) REFERENCES stock (stock_symbol),
  CONSTRAINT CK_transaction_quantity_gt_0 CHECK ((quantity > 0))
);



INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO invite_types (invite_type_id, invite_type_des) VALUES (1, 'accepted'), (2, 'rejected'), (3, 'pending');
INSERT INTO transaction_types (transaction_type_id, transaction_type_desc) VALUES(1, 'buy'), (2, 'sell');

COMMIT TRANSACTION;
