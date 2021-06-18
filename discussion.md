# Identify the Entities and Relations

## Entities

- User: name, username, password, phone
- Expense: description, date
- Group: name

## Relationships

- User m--- participates in ---n Expenses
- User m--- member of ---n Group
- User 1--- admin of ---n Group
- Expense n--- belongs to ---0/1 Group

-- --

# DB Schema?
- How important is "how you're persisting the data"?
- this is a detail - talk about this later

-- --

# Services / Interfaces


-- --

# Other topics to explore

1. Domain Driven Design
2. Clean Architecture

-- --

# Limits on groups

- Why does Whatsapp have a limit on number of group members
- Why does Facebook allow max 3000 friends
- Why does Linkedin allow max 30,000 connections


# Choosing b/w SQL vs NoSQL

1. Your default choice should be relational databases - SQL
- Relational databases are a mature technology - 1970
- string ACID guarantees
- they're fast enough for most purposes.
- unless you're Google / unless you're dealing with billions of users
- 10s of millions of users / 100 million users - SQL is good enough!
- SQL is slow -> myth
- SQL is slow if nd only if you're dealing with big data, or a lot of concurrency

2. Whenever you choose NoSQL, you have to justify it!
- Recent technology
- A lot of misconceptions around these technologies
- If you commit something to Mongodb, it does NOT actually get written immideately
- It is in the memory, which might crash!

# When would you use a document style DB - MongoDB / CouchDB / CouchBase ?
- you don't care about consistency!
- 1% of the likes fail to be persisted, is that a problem?
- 1% of your money transactions get lost, is that a problem!?
- when your data is unstructured / semistructured
- or your team is moving very fast - agile - constantly changing the db schema

# When would you use a wide-column database - Cassandra 
- data is stored column-wise rather than row-wise
- in most analytics applications, you want to run a query on a column
- get the average age of users
- get the average rating by users


# Cloud dbs
aws amplify
PouchDB (sits on top of Couchdb)
Rxdb (sits of top of Pouchdb)
- are reactive
- are offline first
- they sync with the cloud service

S3 storage - blob/file storage
Hbase

At the top level - CTO / Tech head - you will have to decide the tech stack
- the more you know the better!


NoSQL database - does NOT lack relations
Mongodb - have the id of another document in a document

Relations are very important to you, and you're doing nested queries - social network
GraphDB - neo4j

CAP theorem


SQL dbs also have a lot of tooling and a huge community
MongoDB -> tomorrow, you run into a problem -> post it in stackoverflow and noone replies
db is normalized, and joins are costly - add a redis based cache layer to your db -> do the denormalization


Group 0/1 ---- * Expense


pragy@scaler.com

