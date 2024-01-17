#!/bin/bash 

mongodb1=`getent hosts ${MONGODB01} | awk '{ print $1 }'`

port=${PORT:-27017}

echo "Waiting for startup.. DB: " ${mongodb1}
until mongosh admin --host ${mongodb1}:${port} --eval 'quit(db.runCommand({ ping: 1 }).ok ? 0 : 2)' &>/dev/null; do
  printf '.'
  sleep 1
done

echo "Started.."

echo setup.sh time now: `date +"%T" `
mongosh --host ${mongodb1}:${port} <<EOF
   var cfg = {
        "_id": "${RS}",
        "protocolVersion": 1,
        "members": [
            {
                "_id": 0,
                "host": "${mongodb1}:${port}",
                "priority": 10
            }
        ]
    };
    rs.initiate(cfg, { force: true });
    rs.reconfig(cfg, { force: true });
    rs.slaveOk(true);
EOF