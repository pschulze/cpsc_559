#!/bin/bash
sudo apt-get update -y
sudo apt-get install -y postgresql-10
sudo apt-get install -y etcd
sudo apt-get install -y python python-pip
sudo apt-get install -y haproxy

sudo pip install --upgrade setuptools
sudo pip install patroni

sudo mkdir /data/pg-1 -p
sudo chown postgres:postgres /data/pg-1
sudo chmod 700 /data/pg-1
sudo mkdir /data/pg-2 -p
sudo chown postgres:postgres /data/pg-2
sudo chmod 700 /data/pg-2

sudo systemctl stop postgresql

# # Install needed things
# sudo apt-get update -y
# sudo apt-get install -y postgresql-10
# sudo apt-get install -y python-pip

# wget https://github.com/coreos/etcd/releases/download/v3.3.2/etcd-v3.3.2-linux-amd64.tar.gz
# tar xvf etcd-v3.3.2-linux-amd64.tar.gz
# cd etcd-v3.3.2-linux-amd64
# ./etcd

# sudo pip install patroni[etcd]

# sudo update-rc.d postgresql enable

# # Begin setting up replication
# ## Create our replica cluster
# sudo pg_createcluster 10 replica_1

# ## Set up user for replication
# sudo -u postgres createuser repuser -c 5 --replication

# ## Create db and load schema
# sudo -u postgres createdb cpsc_559
# sudo -u postgres psql -d cpsc_559 -a -f init_db.sql
