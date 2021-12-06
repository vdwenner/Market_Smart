CREATE TABLE transaction_types (
 transaction_type_id int, 
 transaction_type_desc varchar(25),
 constraint PK_transaction_types primary key(transaction_type_id)
);

insert into transaction_types (transaction_type_id, transaction_type_desc)
values(1,'buy'),(2,'sell');