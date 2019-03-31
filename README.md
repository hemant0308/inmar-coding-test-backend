# Store Management - Backend

## Project setup

- Run `mvn install` in root directory to install dependencies.
- Change database credentials in **src/main/resources/secret.properties** file.
- Run `` mvn package`` to create war file.
-  Run migration file with ``mysql  < migration.sql`` command.
- Move the war file from **target/** to your tomcat webapps directory.
- Restart the tomcat.


