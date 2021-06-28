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
