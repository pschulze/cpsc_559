# Database Server

## Configuring a PostgreSQL Cluster for Replication, Failover, and Load-Balancing

Drawn heavily from tutorials from this tutorial on [linode.com](https://www.linode.com/docs/databases/postgresql/create-a-highly-available-postgresql-cluster-using-patroni-and-haproxy/)

1. Update packages and install what you need.

    ```bash
    sudo apt-get update
    sudo apt-get install postgresql-10
    sudo apt-get install etcd
    sudo apt-get install python python-pip
    sudo apt-get install haproxy

    sudo pip install --upgrade setuptools
    sudo pip install patroni[etcd]
    ```

2. Make sure the PostgreSQL service is stopped, we don't need it running.

    ```bash
    sudo systemctl stop postgresql
    ```

3. Make directories for our PostgreSQL nodes, and assign give the postgres user permissions for them.

    ```bash
    sudo mkdir /data/pg-1 -p
    sudo chown postgres:postgres /data/pg-1
    sudo chmod 700 /data/pg-1
    sudo mkdir /data/pg-2 -p
    sudo chown postgres:postgres /data/pg-2
    sudo chmod 700 /data/pg-2
    ```

4. Make sure all the configuration files are in the right place. (Commands are assumed to be run from this folder, `database_server`)

    ```bash
    sudo cp patroni/pg-1.yml /etc/
    sudo cp patroni/pg-2.yml /etc/
    sudo cp patroni/patroni_pg-1.service /etc/systemd/system
    sudo cp patroni/patroni_pg-2.service /etc/systemd/system
    sudo cp haproxy.cfh /etc/haproxy
    ```

5. Start the Patroni services.

    ```bash
    sudo systemctl start patroni_pg-1
    sudo systemctl start patroni_pg-2
    ```

6. Restart HAProxy to apply the configuration file

    ```bash
    sudo systemctl restart haproxy
    ```

7. Hope it works.
