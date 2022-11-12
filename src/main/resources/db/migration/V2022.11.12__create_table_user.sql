create table "user" (
	id char(16) for bit data not null,
	provider varchar(20),
	login varchar(255) not null,
	email varchar(255),
	name varchar(255),
	created_at timestamp not null,
	updated_at timestamp not null,
	deleted boolean,
	primary key (id)
);
