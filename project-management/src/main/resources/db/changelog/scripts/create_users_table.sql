CREATE TABLE IF NOT EXISTS public.users
(
    id         serial       NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL UNIQUE,
    password   varchar(255) NOT NULL,
    primary key (id)
)