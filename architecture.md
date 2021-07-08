                 Problem Statement - Design an Expense Sharing App
-------------------------------------------------

Gather Requirements.
- features do we need
- actors do we have
- interactions
- system boundary (what things do we NOT care about)


What to do after getting requirements?
--------------------------------------

Draw the ER diagram

- Entities
    - Attributes
    - Behavior
- Relationships
    - Cardinality
    - Composition vs Aggregation (ownership)
    - attributes

Architecture
------------

Data
----

1. How are we persisting data?
2. SQL vs NoSQL?

Myth: SQL is for related data, whereas NoSql is for unrelated data.
In 99% of the cases, your default choice should be -> SQL
1. We understand it better. Relational - 1980s. NoSQL - 2000s
2. SQL has better community support
3. It is good enough - unless you're dealing with billions of rows - SQL is sufficient.
4. SQL gives you strong ACID guarantees

NoSQL - MongoDB - does not give you ACID guarantees

What services do we have in our app
-----------------------------------

1. Frontend app -> client
2. Backend server -> expose a REST API

We will focus on the backend server

-- --

For the purpose of the assignment / interview -> dealing with console input

-- --

1. Models (fat models -> have some domain logic in the models)
    - Expense
    - User
    - Group
      1.1 Fat models vs Anaemic models
        1. Their models are just POJOs -> Plain Old Java Object
           An object that just has data members and getters and setters
           A POJO should NOT have any methods (any other functionality)
        1. The models hold the data + do some domain specific tasks -> provide some functionalities
2. Persistence - Repositories (interface)
    - facilitate the basic CRUD operations for these model
3. Usecases - Controllers (concrete classes)
    - Have dependencies -> Repositories and services
    - expose the methods that allow you to interact with the models
-- --

How is the data persisted -> is an implementation detail
How is the user interacting with our service? -> implementation detail


3. For each persistence layer that we want to support we will create a
   new set of implementations for these repositories.


User wants to login.
class UserController {
IUserRepository userRepo;
IGroupRepository groupRepo;
OTPService otpService;

    public void login(AuthenticaionContext context) {
        // log the user in
        otpService.sendOTP(user);
    }
}

class OTPService {
}

class Splitwise {
static void main() {
// create the SQLRepos
// Create objects of UserController using these SQLRepos
// handle the console commands
}
}

class SplitwiseAPI {
static void main() {
SprinngBoot..run();
}
}

-- --

Single Responsibility Principle
-------------------------------

1. Your model has the SR of holding the data


-- --

1 / 0
UserController -> register -> new DuplicateUsernameException("username duplicate)

// client

try {
userController.register(details);
} catch (DuplicateUsernameException e) {
print(duplicate username)
} catch (NumericalError e) {
}

-- --

Clean Architecture by Uncle Bob
softwareengineering.stackexchange.com ->
sort the questions by votes ->
go through the first 5 pages of questions

-- --

In code, less is better
1. extensibility, maintanability, testing, performance

If you have 0 code



-- --

Persistence Layer
-----------------

- You don't need to use a database, you just store things in memory
- always use a SQL database



ORM - Object Relation Mapper
Java
Hibernate will implement Spring Data JPA
Spring Data JPA -> interface for ORMs

Typescript -> TypeORM

Python -> SQLAlchemy, DjangoORM

Php -> Eloquent ORM


you can simply connect to an in-memory Sqlite database
