create table if not exists order_product_relationship (
	order_id bigint not null,
	product_id bigint not null,
	primary key (order_id, product_id)
	)