CREATE TABLE transactions (
  transaction_id serial ,
  transaction_type int,
  price decimal(8,2),
  portfolio_id int,
  stock_symbol varchar,
  quantity int,
  
  constraint PK_transactions primary key(transaction_id),
  constraint FK_transactions_transaction_type foreign key (transaction_type) references transaction_types(transaction_type_id),
  constraint FK_transactions_portfolio_id foreign key (portfolio_id) references portfolio(portfolio_id),
  constraint FK_transactions_stock_symbol foreign key (stock_symbol) references stock(stock_symbol),
  constraint CK_transaction_quantity_gt_0 check ((quantity>0))
);
