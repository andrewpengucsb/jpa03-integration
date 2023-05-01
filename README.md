# STARTER-jpa03

# Getting Started on localhost

Before running the application for the first time on localhost, you must: 

* Set up Google OAuth as documented in [`docs/oauth.md`](docs/oauth.md) 

Otherwise, when you try to login for the first time, you 
will likely see an error such as:

<img src="https://user-images.githubusercontent.com/1119017/149858436-c9baa238-a4f7-4c52-b995-0ed8bee97487.png" alt="Authorization Error; Error 401: invalid_client; The OAuth client was not found." width="400"/>

Then:

* Open *two separate terminal windows*  
* In the first window, start up the backend with:
  ``` 
  mvn spring-boot:run
  ```
* In the second window:
  ```
  cd frontend
  npm install  # only on first run or when dependencies change
  npm start
  ```

Then, the app should be available on <http://localhost:8080>

If it doesn't work at first, e.g. you have a blank page on  <http://localhost:8080>, give it a minute and a few page refreshes.  Sometimes it takes a moment for everything to settle in.

If you see the following on localhost, make sure that you also have the frontend code running in a separate window.

```
Failed to connect to the frontend server... 

On Dokku, be sure that PRODUCTION is defined. 

On localhost, open a second terminal window, 
cd into frontend and type: npm install; npm start";
```

# Getting Started on Dokku

* Follow the steps here: <https://ucsb-cs156.github.io/topics/dokku/getting_started.html>
* Set up Google OAuth as documented in [`docs/oauth.md`](docs/oauth.md) 
* Set up Postgres, as documented in  [`docs/postgres-database.md`](docs/postgres-database.md)
* Set the config variable `PRODUCTION=true`

# Accessing swagger

Swagger is a tool that allows you to work directly with the RESTful API endpoints of the backend.
It is similar to the tool Postman, but more convenient because it is built directly into the application.

To access the Swagger API endpoints, use:

* <http://localhost:8080/swagger-ui/index.html>

You can also append `/swagger-ui/index.html` to the URL manually when running on Dokku.

# To run React Storybook

* cd into frontend
* use: npm run storybook
* This should put the storybook on http://localhost:6006
* Additional stories are added under frontend/src/stories

There are also Github Actions to publish the storybook on the Github Pages site associated with the repo;
see [/docs/github-pages.md](/docs/github-pages.md) for more info.

* For documentation on React Storybook, see: <https://storybook.js.org/>

# SQL Database access

On localhost:
* The SQL database is an H2 database and the data is stored in a file under `target`
* Each time you do `mvn clean` the database is completely rebuilt from scratch
* You can access the database console via a special route, <http://localhost:8080/h2-console>
* For more info, see [docs/h2-database.md](/docs/h2-database.md)

On Dokku:
* The SQL database is a postgres database provisioned automatically by Dokku
* More info and instructions for accessing the SQL prompt are at [docs/postgres-database.md](/docs/postgres-database.md)
