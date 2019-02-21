# Database Server

## Configuring a PostgreSQL Cluster for Replication

1. Run `./init.sh` to update packages, install PostgreSQL, and perform some configuration steps.
2. Edit the PostgreSQL configuration file for both the `main` and `replica_1` clusters.
    - **Make sure to replace `CLUSTER_NAME` with the name of whatever cluster you are currently configuring**
    - May need to uncomment some settings.
    - Configuration file found in `/etc/postgresql/10/CLUSTER_NAME/postgresql.conf`
    - Change the following settings:

      ```bash
      wal_level = replica # Need at least a level of replica
      wal_log_hints = on # Important for using pg_rewind
      archive_mode = on # Enables archiving
      archive_command = 'test ! -f /var/lib/postgresql/pg_log_archive/CLUSTER_NAME/%f && cp %p /var/lib/postgresql/pg_log_archive/CLUSTER_NAME/%f' # Specifies where WAL files are archived
      max_wal_senders = 10 # How many processes can be used to stream WAL files
      wall_keep_segments = 64
      hot_standby = on
      ```

3. Edit host based access file for both the `main` and `replica_1` clusters to allow our replication user (`repuser`) to perform replication.
    - File can be found under `/etc/postgresql/10/CLUSTER_NAME/pg_hba.conf`
    - Add the following line immediately under the comment `# Allow replication connections from localhost, by a user with the replication privilege.`
    - Note: Not a secure way of doing this, but easy for our academic purposes.

    ```bash
    # Allow replication connections from localhost, by a user with the
    # replication privilege.
    local   replication     repuser                                 trust
    ```

4. Run `./configure_pg_replication.sh` to finish configuring the clusters for replication.