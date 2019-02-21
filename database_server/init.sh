#!/bin/bash
sudo apt-get update -y
sudo apt-get install -y postgresql-10
sudo update-rc.d postgresql enable

# Begin setting up replication
## Create our replica cluster
sudo pg_createcluster 10 replica_1

## Set up user for replication
sudo -u postgres createuser repuser -c 5 --replication

## Create db and load schema
sudo -u postgres createdb cpsc_559
sudo -u postgres psql -d cpsc_559 -a -f init_db.sql
