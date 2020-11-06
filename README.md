# WorknHireAPI

This is the WorknHireAPI for a web application that functions as a freelance website.
You are able to create 2 kinds of users; Client and Worker. 
As a Client you will be able to browse through the job listings posted by Clients.

## Author
- [Daniel Wamuya](https://github.com/dannywamuya/)
- [Joseph Mulama]

## Live Link
https://worknhireapi.herokuapp.com

## Technologies Used
- Java JDK 11
- Gradle
- JUnit4
- Spark
- Maven
- PostgreSql

## Consume
To use this API, make your api calls as follows:

```
get: 

Clients
- https://worknhireapi.herokuapp.com/clients

Workers
- https://worknhireapi.herokuapp.com/workers

Listings
- https://worknhireapi.herokuapp.com/jobdetails

Client by ID
- https://worknhireapi.herokuapp.com/clients/:id

Worker by ID
- https://worknhireapi.herokuapp.com/workers/:id

Listing by ID
- https://worknhireapi.herokuapp.com/jobdetails/:id

Listing by Client
- https://worknhireapi.herokuapp.com/jobdetails/:clientId

post:

Add Client
- https://worknhireapi.herokuapp.com/clients/new

Add Worker
- https://worknhireapi.herokuapp.com/workers/new

Add JobDetail
- https://worknhireapi.herokuapp.com/jobdetails/new

Update Client
- https://worknhireapi.herokuapp.com/clients/update/:id

Update Worker
- https://worknhireapi.herokuapp.com/workers/update/:id

Listing Worker
- https://worknhireapi.herokuapp.com/jobdetails/update/:id
```
#####[View more routes and exceptions](https://github.com/dannywamuya/worknhireapi/blob/danny/src/main/java/App.java)

## Support and contact details
If you come across any issues, reach me at dwamuya@gmail.com or comment on the issue via GitHub. 

### License
*This project is licensed under the terms of the MIT license.*
Copyright (c) 2020 **Daniel Wamuya**

