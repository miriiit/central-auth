#!/bin/bash
set -e

# Install the MongoDB client tools
#apk update \
#    && apk add mongodb-tools

# Function to wait for MongoDB to be ready
wait_for_mongodb() {
    until nc -z $ME_CONFIG_MONGODB_SERVER $ME_CONFIG_MONGODB_PORT; do
        echo "MongoDB is not ready yet. Retrying..."
        sleep 1
    done
    echo "MongoDB is ready."
}

# Wait for MongoDB to be ready
echo "Waiting for MongoDB to start..."
wait_for_mongodb

# Start mongo-express
echo "Starting mongo-express..."
exec node app