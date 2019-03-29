-- Create the tables for our database. Assumes it is being run against a fresh DB with nothing in it's schema.
CREATE TABLE users (
  id serial PRIMARY KEY,
  username VARCHAR UNIQUE NOT NULL
);

CREATE TABLE dogs (
  id serial PRIMARY KEY,
  name VARCHAR NOT NULL,
  breed VARCHAR NOT NULL,
  age integer NOT NULL,
  owner_id integer REFERENCES users (id) NOT NULL,
  image_url VARCHAR
);

CREATE TABLE auctions (
  id serial PRIMARY KEY,
  expiration_time timestamp NOT NULL,
  dog_id integer REFERENCES dogs (id) NOT NULL,
  start_price numeric NOT NULL,
  completed BOOLEAN DEFAULT FALSE,
  name VARCHAR NOT NULL
);

CREATE TABLE bids (
  id serial PRIMARY KEY,
  auction_id integer REFERENCES auctions (id) NOT NULL,
  bidder_id integer REFERENCES users (id) NOT NULL,
  amount numeric NOT NULL
);
