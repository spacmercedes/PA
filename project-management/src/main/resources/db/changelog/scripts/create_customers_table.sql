create table if not exists customer (
	id bigint generated by default as identity,
	name varchar(255),
	tier integer,
	primary key (id)
)