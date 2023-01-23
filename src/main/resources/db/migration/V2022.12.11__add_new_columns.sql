alter table invoice_item drop column original_unit_price;

alter table "user" add column default_unit varchar(50);
alter table "user" add column default_unit_price decimal(19,2);

alter table subject add column default_unit_price decimal(19,2);
