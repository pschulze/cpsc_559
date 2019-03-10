-- Create the tables for our database. Assumes it is being run against a fresh DB with nothing in it's schema.
CREATE TABLE users (
  username VARCHAR PRIMARY KEY
);

CREATE TABLE dogs (
  id serial PRIMARY KEY,
  name VARCHAR NOT NULL,
  breed VARCHAR NOT NULL,
  age integer NOT NULL,
  owner_name integer REFERENCES users (username) NOT NULL
);

CREATE TABLE auctions (
  id serial PRIMARY KEY,
  expiration_time timestamp NOT NULL,
  dog_id integer references dogs (id) NOT NULL,
  start_price numeric NOT NULL,
  completed BOOLEAN DEFAULT FALSE
);

CREATE TABLE bids (
  id serial PRIMARY KEY,
  auction_id REFERENCES auctions (id) NOT NULL,
  amount numeric NOT NULL
);
