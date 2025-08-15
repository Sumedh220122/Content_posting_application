# Content Posting application

This repository provides a detailed implementation for a Full Stack Content-Posting Application(on the likes of Quora and Reddit), created by making use of the Spring-MVC framework. Details are persisted in a MYSQL database. 

## What's Included?

## Folders

- **[`controllers/`](./src/main/java/com/example/content_posting/controllers/)**  
  This folder contains the .java controller files for the project repository
- **[`dto/`](./src/main/java/com/example/content_posting/dto/)** 
  This folder contains the .java request model files/dto files for the project repository
- **[`repository/`](./src/main/java/com/example/content_posting/repository/)** 
  This folder contains the .java repository files for the project repository. JPA has been used as ORM across these files.
- **[`services/`](./src/main/java/com/example/content_posting/services/)** 
  This folder contains the .java service files for the project repository
- **[`models/`](./src/main/java/com/example/content_posting/models/)** 
  This folder contains the .java model files for the project repository
- **[`utils/`](./src/main/java/com/example/content_posting/utils/)** 
  This folder contains SessionUtils.java file that is used to persist User details for the entirety of the session.
- **[`templates/`](./src/main/resources/templates/)** 
  This folder contains the .html frontend files for server side rendering.

## Quick Setup Guide

### Step 1: Clone the repository and set up some prerequisites 

First, make sure you have Java and Maven installed. Then:

```bash
git clone https://github.com/Sumedh220122/Content_posting_application.git
```

### Step 2: Set Up Environment Variables

Create a `secrets.properties` file in the resources folder:

Then add the following details in your secrets.properties file:

```env
db.url=your_database_url(usually of the form jdbc:mysql://localhost:3306/your_db_name?useSSL=false&serverTimezone=UTC)
db.username=your_database_username
db.password=your_database_password
```

### Step 3: Set up your database

Run the **[`create_schema.sql`](create_schema.sql)** file in the project root to set up your database

### Step 4: Run the application

Open the project folder in an IDE like IntelliJ or VSCode(preferably IntellIJ) and run the main file using the run button.
After that you can access the server at localhost:8080
