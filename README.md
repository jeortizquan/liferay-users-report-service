# Table of Contents

# About the Project

# Built With
* Java 1.8
* Spring boot 2.3.5

# Getting Started
# Prerequisites
Invoke a GET endpoint with the following response format
```json
{
  "records": [
    {
      "login": "alice",
      "firstName": "Alice",
      "lastName": "Liferay",
      "emailAddress": "alice@liferay.com",
      "fullName": "Alice Liferay",
      "status": "active",
      "lastLogin": "2020-11-13 16:25:45",
      "userGroups": [
        "All Employees",
        "Administration"
      ]
    },
    {
      "login": "bob",
      "firstName": "Bob",
      "lastName": "Liferay",
      "emailAddress": "bob@liferay.com",
      "fullName": "Bob Liferay",
      "status": "active",
      "lastLogin": "2020-11-13 16:25:45",
      "userGroups": [
        "All Employees"
      ]
    }
  ],
  "usergroups": [
    "All Employees",
    "Administration"
  ]
}
```
# Installation

```bash
mvn spring-boot:run
```
# Usage
```bash
http://localhost:8084/generate
```
# Roadmap
# Contributing
# License
# Contact
# Acknowledgements
