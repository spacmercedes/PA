alter table order_product_relationship drop constraint if exists fk_order_product_relationship_product;
alter table order_product_relationship drop constraint if exists fk_order_product_relationship_order;
alter table product_order drop constraint if exists fk_product_order_customer;

alter table order_product_relationship add constraint fk_order_product_relationship_product
foreign key (product_id) references product;

alter table order_product_relationship add constraint fk_order_product_relationship_order
foreign key (order_id) references product_order;

alter table product_order add constraint fk_product_order_customer
foreign key (customer_id) references customer;