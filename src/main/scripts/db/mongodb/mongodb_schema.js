/*
schema for mongodb - v1.86.0 Release
==================
*/


db.getCollection("permissions").drop();
// db.getCollection("permissions.next_id").drop();
db.getCollection("roles").drop();
// db.getCollection("roles.next_id").drop();
db.getCollection("users").drop();
// db.getCollection("users.next_id").drop();


db.createCollection("users");
// db.createCollection("users.next_id");
// db.users.next_id.insertOne({
//         "_id":"users",
//         "next_id": NumberLong(1)
//     }
// );

// db.users.createIndex({"email":1}, {"unique": true});
db.users.insertOne(
    {
        // "_id" : NumberLong(db.users.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "firstName" : "Azhar",
        "lastName" : "Hussnain",
        "email" : "miriiit60@gmail.com",
        "roles" : null
    });
// db.users.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});

db.users.insertOne(
    {
        // "_id" : NumberLong(db.users.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "firstName" : "Abdul Haq",
        "lastName" : "Azhar",
        "email" : "miriiit20@gmail.com",
        "roles" : null
    });
// db.users.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});


db.users.insertOne(
    {
        // "_id" : NumberLong(db.users.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "firstName" : "Muhammad Safiullah",
        "lastName" : "Azhar",
        "email" : "miriiit30@gmail.com",
        "roles": null
    });
// db.users.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});


db.createCollection("permissions");
// db.createCollection("permissions.next_id");
// db.permissions.next_id.insertOne({
//         "_id":"permissions",
//         "next_id": NumberLong(1)
//     }
// );

db.permissions.insertOne(
    {
        // "_id" : NumberLong(db.permissions.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "name" : "read"
    });
// db.permissions.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});
db.permissions.insertOne(
    {
        // "_id" : NumberLong(db.permissions.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "name" : "update"
    });

db.permissions.insertOne(
    {
        // "_id" : NumberLong(db.permissions.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "name" : "remove"
    });


db.createCollection("roles");
// db.createCollection("roles.next_id");
// db.roles.next_id.insertOne({
//         "_id":"roles",
//         "next_id": NumberLong(1)
//     }
// );
db.roles.insertOne(
    {
        // "_id" : NumberLong(db.roles.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "name" : "user",
        "user" :  db.users.findOne({"email" : "miriiit60@gmail.com"}),
        "permissions": db.permissions.find({"name":"read"}).toArray()
    });
// db.roles.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});
db.roles.insertOne(
    {
        // "_id" : NumberLong(db.roles.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "name" : "admin",
        "user" : db.users.findOne({"email" : "miriiit20@gmail.com"}),
        "permissions": db.permissions.find({"name": { $in: ["read", "update"]}}).toArray()
    });
// db.roles.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});

db.roles.insertOne(
    {
        // "_id" : NumberLong(db.roles.next_id.findOne({}, { next_id: 1, _id: 0 }).next_id),
        "name" : "super_admin",
        "user" : db.users.findOne({"email" : "miriiit30@gmail.com"}),
        "permissions": db.permissions.find({"name": { $in: ["read", "update", "remove"] }}).toArray()
    });
// db.roles.next_id.updateOne({},{$inc:{next_id: NumberLong(1)}});

db.users.updateOne(
    { "email": "miriiit60@gmail.com" }, // Filter criteria
    {
        $set: {
            "roles": db.roles.find({"name":"user" }).toArray()
            // other fields to update
        }
    }
)
db.users.updateOne(
    { "email": "miriiit30@gmail.com" }, // Filter criteria
    {
        $set: {
            "roles": db.roles.find({"name":"super_admin" }).toArray()
            // other fields to update
        }
    }
)
db.users.updateOne(
    { "email": "miriiit20@gmail.com" }, // Filter criteria
    {
        $set: {
            "roles": db.roles.find({"name":"admin"}).toArray()
            // other fields to update
        }
    }
)