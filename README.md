# Embedded Keycloak on Spring Boot

Keycloak server is running on Spring Boot

## Description

## Demo
### Build
```shell
$ mvn clean package
```

### Run
```shell
$ java -jar target/oauth-authorization-server-0.0.1-SNAPSHOT.jar
```

### Access
- http://localhost:8083/auth

![top](https://user-images.githubusercontent.com/3072734/123707769-2d87a580-d8a5-11eb-92ca-8620bf13764e.png)

![login](https://user-images.githubusercontent.com/3072734/123707820-45f7c000-d8a5-11eb-8bdb-05a8796629fe.png)

#### Configurations
![clients](https://user-images.githubusercontent.com/3072734/123708111-d46c4180-d8a5-11eb-880f-ead20c14ce09.png)

![authentication](https://user-images.githubusercontent.com/3072734/123708228-01205900-d8a6-11eb-9b54-99176b66913b.png)


## Features

- feature:1
- feature:2

## Requirement

## Usage
### Keycloak configuration
You can configure the followings:
- Context Path: `keycloak.server.contextPath`
- adminUserName: `keycloak.server.adminUser.username`
- adminUserPassword: `keycloak.server.adminUser.password`
- Keycloak configurations: `keycloak.server.realmImportFile`

application.yml
```yaml
keycloak:
  server:
    contextPath: /auth
    adminUser:
      username: admin
      password: passw0rd
    realmImportFile: keycloak-realm.json
```


## Installation

## References

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
