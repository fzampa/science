# science

## Architectural overview, limitations and future implementations

The application uses Spring Boot as it's main framework.

This is a REST application to manipulate categories/attributes/items so categories behave like tables, attributes are columns and items are the data itself to be filled during scientific experimentations.

Given the time restriction my idea here was to create a functional application with limitations so you can expect to see here examples of good usage of the technical stack and architectural decisions, including:

* Database migration with liquibase
* REST api to add/manipulate data
* Data manipulation with JPA Repositories
* Transactional services
* Hibernate entities relation and database queries
* Unit and integration tests
* Data transformation so the application clients doesn't know about the data model. 
* Lombok library usage to reduce boilerplate code

My idea here was not to create a complete aplication with full tests coverage (or all rest methods) but to have a solid foundation so another developer could easily understand the solution and also be able to continue the development without huge efforts. If a developer is able to, from this code, understand how to add integration tests to an endpoint, for example, the goal was reached.

Having that said, as a future implementation for this project, I would add to the TO-DO list the following topics:

* Add more endpoints to manipulate data. For example `DELETE` and `PUT` to enable data deletion and edition;
* Full tests coverage for both service and api layers;
* Performance enhancement for repository layer with queries that consume less database relations and multiples reads.

## Design decisions

To meet the requirements the design is based on the relations 1-N between Category |-< Attributes and the same for Attribute |-< Item.

Every Item to be added to the application needs to have a type. This can be one of the following options:

```
    "TEXT",
    "INTEGER",
    "DOUBLE",
    "BOOLEAN",
    "DATETIME"
```

This allows the application the flexible. The data type for the items can also be extended just following the existing patterns.

So, one concrete example of data would be:

*Category:* "Symptom"

*Attributes:* 

* 1 - Days (integer); 
* 2 - Fever (boolean);
* 3 - Cough (boolean);
* 4 - Feeling description (text);
* 5 - Starting date (datetime)

And finally the items added would be represented as:

|Category  | Attribute  | Attribute type  |  Item  |
|----------|------------|-----------------|--------|
| 1-Symptom| Days | integer | 7 |
| 1-Symptom| Fever | boolean | false |
| 1-Symptom| Cough | boolean | true |
| 1-Symptom| Feeling desc. | text | Get tired going upstairs |


## Configuring the execution environment

The *science* application uses MySQL as database. To have it configured you need only to create a new database on the server. The following steps can be used as guide:

```
$ mysql -h localhost -u root -p

mysql> create database science;
mysql> create user 'scienceUser'@'localhost' identified with mysql_native_password by 'sciencePass1!';
mysql> grant all privileges on science.* to 'scienceUser'@'localhost';
```

Having the database setup, just run the application over the class `br.com.lab.ScienceApplication` and all the database tables will be added automatically with the migration script.

With your application correctly executed you should then have the following availables table on your science database schema:

```
mysql> show tables;
+-----------------------+
| Tables_in_science     |
+-----------------------+
| DATABASECHANGELOG     |
| DATABASECHANGELOGLOCK |
| attribute             |
| boolean_item_value    |
| category              |
| date_time_item_value  |
| double_item_value     |
| integer_item_value    |
| item                  |
| item_value            |
| textual_item_value    |
+-----------------------+
11 rows in set (0.01 sec)
```

## Running the application and sample data

The following endpoints and example of request data are provided:

### Add category and attributes to database

`POST` http://localhost:8080/categories/

payload:

```
{
    "name" : "symptoms",
    "attributes" : [
        {
            "name" : "days",
            "type": "INTEGER",
            "nullable":false
        },
        {
            "name" : "fever",
            "type": "BOOLEAN",
            "nullable":false
        },
        {
            "name" : "cough",
            "type": "BOOLEAN",
            "nullable":false
        },
        {
            "name" : "feeling",
            "type": "TEXT",
            "nullable":false
        }
    ]
}
```

## Retrieve category

`GET` http://localhost:8080/categories/{categoryId} 

## Retrieve attribute types

`GET` http://localhost:8080/attributes/types

## Add item for set category/attribute

`POST` http://localhost:8080/items/

payload:

```
{
    "attribute" : {
        "id" : 1,
        "type" : "INTEGER"
    },
    "value" : 7
}
```

payload #2:

```
{
    "attribute" : {
        "id" : 4,
        "type" : "TEXT"
    },
    "value" : "Feeling tired."
}
```
## Retrieve items for given category

`GET` http://localhost:8080/items/category/{categoryId}

## Troubleshooting

If you notice compilation problems on your IDE because of the `@Data` annotations, please refer to Lombok Documentation: https://projectlombok.org/setup/overview
