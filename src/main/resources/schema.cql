CREATE KEYSPACE IF NOT EXISTS limonstand WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE limonstand;

CREATE TABLE IF NOT EXISTS items (
    item_id int,
    name text,
    price double,
    PRIMARY KEY (item_id, name)
);