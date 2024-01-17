#!/usr/bin/env bash

# Install the MongoDB client tools
apt-get update && apt-get install -y mongodb-tools

# Loading environment variables
source .env
echo 'Starting..'
# MONGO
echo 'Creating User in MongoDB.. '+ ${MONGO_INITDB_DATABASE}
docker exec -i mongodbca mongosh <<EOF
use ${MONGO_INITDB_DATABASE};
db.createUser({ user: '${MONGO_USERNAME}', pwd: '${MONGO_PASSWORD}', roles: [ { role: 'userAdmin', db: 'admin' }, { role: 'userAdmin', db: '${MONGO_INITDB_DATABASE}' } ] });
quit()
EOF

echo 'Importing Data in MongoDB..'
docker exec -it mongodbca mongosh localhost:27017/${MONGO_INITDB_DATABASE} /init/db/mongodb_schema.js