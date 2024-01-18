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

var permission1Id = ObjectId();
var permission2Id = ObjectId();
var permission3Id = ObjectId();

var role1Id = ObjectId();
var role2Id = ObjectId();
var role3Id = ObjectId();

var user1Id = ObjectId();
var user2Id = ObjectId();
var user3Id = ObjectId();

var permission1 = { _id: permission1Id, name: "VIEW" };
var permission2 = { _id: permission2Id, name: "UPDATE" };
var permission3 = { _id: permission3Id, name: "REMOVE" };

var user1= {};
var user2 = {};
var user3={};

db.createCollection("permissions");
db.permissions.insertMany([permission1, permission2, permission3]);


user1 = {
    _id: user1Id,
    firstName: "Azhar",
    lastName: "Hussnain",
    email: "miriiit60@gmail.com",
    caRole: null
};

user2 = {
    _id: user2Id,
    firstName: "Momin",
    lastName: "Azhar",
    email: "miriiit80@gmail.com",
    caRole: null
};

user3 = {
    _id: user3Id,
    firstName: "M Abdul",
    lastName: "Haq",
    email: "miriiit90@gmail.com",
    caRole: null
};
db.createCollection("users");
db.users.insertMany([user1, user2, user3]);


var role1 = {
    _id: role1Id,
    name: "USER",
    caPermissions: [permission1]
};
var role2 = {
    _id:  role2Id,
    name: "ADMIN",
    caPermissions: [permission1, permission2]
};
var role3 = {
    _id:  role3Id,
    name: "SUPER_ADMIN",
    caPermissions: [permission1, permission2, permission3]
};

db.createCollection("roles");
db.roles.insertMany([role1, role2, role3]);


var updateUser1 = db.users.updateOne(
    { "_id": user1Id }, // Filter criteria
    {
        $set: {
            "caRole": role1
            // other fields to update
        }
    }
)
var updateUser2 = db.users.updateOne(
    { "_id": user2Id }, // Filter criteria
    {
        $set: {
            "caRole": role2
            // other fields to update
        }
    }
)
var updateUser3 = db.users.updateOne(
    { "_id": user3Id }, // Filter criteria
    {
        $set: {
            "caRole": role3
            // other fields to update
        }
    }
)