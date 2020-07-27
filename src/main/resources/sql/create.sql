create table board_table(
	id bigint auto_increment,
    category varchar(50) not null,
	title varchar(200) not null,
    content varchar(3000) not null,
    writer varchar(50) not null,
    user_id varchar(50) not null,
    created_at timestamp default CURRENT_TIMESTAMP(),
    updated_at timestamp default CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    reply_count bigint,
    constraint board_PK primary key(id)
);

create table reply_table (
  id bigint auto_increment,
  board_id bigint not null,
  user_id varchar(50) not null,
  content varchar(1000) not null,
  writer varchar(50) not null,
  created_at timestamp default CURRENT_TIMESTAMP(),
  updated_at timestamp default CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
  constraint reply_PK primary key (id)
);

create table user_table (
id  varchar(50),
password varchar(100) not null,
name varchar(100) not null,
created_at timestamp default CURRENT_TIMESTAMP(),
updated_at timestamp default CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
remember_id varchar(100),
constraint user_PK primary key (id)
);

create table message_table (
 id bigint auto_increment,
 recipient_id varchar(50) not null,
 sender_id varchar(50) not null,
 title varchar(200) not null,
 content varchar(3000) not null,
 created_at timestamp default CURRENT_TIMESTAMP(),
 updated_at timestamp default CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
 constraint message_PK primary key (id)
);


