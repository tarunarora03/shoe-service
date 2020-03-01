#!/bin/bash

file="/docker-entrypoint-initdb.d/dump.pgdata"
dbname=asset_management_db

echo "Restoring DB using $file"
#psql -U postgres --set ON_ERROR_STOP=on -f < "$file" || exit 1
pg_restore -U postgres --dbname=$dbname --verbose --single-transaction < "$file" || exit 1
