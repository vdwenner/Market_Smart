CREATE TABLE portfolio_stock (
  portfolio_id int,
  stock_symbol varchar(25),
  quantity int,
  average_price decimal(8,2),
  
  constraint FK_portfolio_stock_portfolio_id foreign key (portfolio_id) references portfolio (portfolio_id),
  constraint FK_portfolio_stock_stock_symbol foreign key (stock_symbol) references stock (stock_symbol),
  constraint CK_portfolio_stock_quantity_gt_0 check ((quantity>0)),
  constraint CK_portfolio_stock_average_price_gt_0 check ((average_price>0))
  
);