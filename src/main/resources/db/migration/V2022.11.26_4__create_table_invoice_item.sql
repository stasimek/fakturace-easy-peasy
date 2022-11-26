create table invoice_item (
	id char(16) for bit data not null,
	invoice_id char(16) for bit data not null,
	quantity decimal(19,2),
	unit varchar(20),
	description varchar(255) not null,
	original_unit_price varchar(100) not null,
	unit_price decimal(19,2) not null,
	vat_rate decimal(19,2) not null,
	created_at timestamp not null,
	updated_at timestamp not null,
	deleted boolean,
	primary key (id),
	constraint FK_invoice_item_invoice_id foreign key (invoice_id) references invoice
);
