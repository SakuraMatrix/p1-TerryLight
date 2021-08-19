CREATE KEYSPACE IF NOT EXISTS lightnfresh WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE lightnfresh;

CREATE TABLE IF NOT EXISTS orders (
    order_id int,
    customerName text,
    drinkName text,
    cupSize text,
    price double,
    PRIMARY KEY (order_id, customerName)
);


insert into orders (order_id, customerName, drinkName, cupSize, price) values (1,"Jane","Lemonade","Medium",5.99);

/*
CREATE TABLE IF NOT EXISTS ingredients (
food_id int,
foodName text,
quantity double,
PRIMARY KEY (food_id, name)
);
*/
