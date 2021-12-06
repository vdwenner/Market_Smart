

CREATE TABLE invite_types (
  invite_type_id int,
  invite_type_des varchar(10),
  constraint PK_invite_types primary key (invite_type_id)
  
);

insert into invite_types(invite_type_id,invite_type_des)
values(1,'accepted'),(2,'rejected'),(3,'pending');