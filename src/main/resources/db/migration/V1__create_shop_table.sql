create schema if not exists shoppingapidb;

create table shoppingapidb.shop (
    id bigserial primary key,
    user_identifier varchar(100) not null,
    date timestamp not null,
    total float not null
);

create table shoppingapidb.item (
    shop_id bigserial REFERENCES shoppingapidb.shop(id),
    product_identifier varchar(100) not null,
    price float not null
);
