#!/bin/bash

# Restart the main cluster to apply our configuration changes.
sudo systemctl restart postgresql@10-main

# Clear out any exisiting database files from our replica
sudo -u postgres rm -rf /var/lib/postgresql/10/replica_1/

# Sync our replica with our main cluster
sudo -u postgres pg_basebackup -D /var/lib/postgresql/10/replica_1 -U repuser -w -P -R

# Add some config to our our replica_1's recovery.conf file
sudo sh -c "echo \"restore_command = 'cp /var/lib/postgresql/pg_log_archive/replica_1/%f %p'\" >> /var/lib/postgresql/10/replica_1/recovery.conf"
sudo sh -c "echo \"recovery_target_timeline = 'latest'\" >> /var/lib/postgresql/10/replica_1/recovery.conf"

# Start our replica cluster
sudo pg_ctlcluster 10 replica_1 start
