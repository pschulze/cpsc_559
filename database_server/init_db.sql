-- Create the tables for our database. Assumes it is being run against a fresh DB with nothing in it's schema.
CREATE TABLE dogs (
  id serial PRIMARY KEY,
  name VARCHAR NOT NULL,
  breed VARCHAR NOT NULL,
  age integer NOT NULL
);
