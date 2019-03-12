# API Server

## To Run

- Build the project
- Run `Main.main()`
- Visit `localhost:7000/dogs`

## Configuring EC2 Instance to Run `api_server`
```bash
# On local machine
./gradlew fatJar
scp -i /path/to/.pem/file build/libs/api_server-1.0-SNAPSHOT.jar ubuntu@ec2-instance-publi-DNS:~/api_server-1.0-SNAPSHOT.jar

# On EC2 Instance
sudo apt-get update
sudo apt-get install default-jre

```

## Helpful Links

- [Javalin Web Framework](https://javalin.io/)
- [Installing the Google Style Guide for Java in Intellij](https://github.com/HPI-Information-Systems/Metanome/wiki/Installing-the-google-styleguide-settings-in-intellij-and-eclipse)
- [JSON APIs](https://jsonapi.org/)
- [Postman](https://www.getpostman.com/)
  - Highly recommend, makes interacting with APIs super nice.

