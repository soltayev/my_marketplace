create table goods
(
    id         bigint generated by default as identity,
    name       varchar(255),
    category   varchar(255),
    city       varchar(255),
    info       text,
    created_at timestamp(6),
    price      bigint,
    quantity   bigint,
    primary key (id)
);
