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


