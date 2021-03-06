# Embedded Keycloak on Spring Boot

Keycloak server is running on Spring Boot

Published in Docker Hub
- [shinyay/keycloak](https://hub.docker.com/repository/docker/shinyay/keycloak)

![keycloak-auth-flow](https://user-images.githubusercontent.com/3072734/125256072-09ad7080-e337-11eb-93d1-d192484b4120.png)

## Description

## Demo
### Prepare Environment
- Authorization Server
  - [shinyay/spring-keycloak-authz-server](https://github.com/shinyay/spring-keycloak-authz-server)
- Resource Server
  - [shinyay/spring-security-oauth2-resource-server-gs](https://github.com/shinyay/spring-security-oauth2-resource-server-gs)
- OAuth2.0 Client
  - [shinyay/spring-security-oauth2-client-for-keycloak](https://github.com/shinyay/spring-security-oauth2-client-for-keycloak)


### Build
```shell
$ mvn clean package
```

### Run
```shell
$ java -jar target/oauth-authorization-server-0.0.2-SNAPSHOT.jar
```

### Access
- http://localhost:8083/auth

![top](https://user-images.githubusercontent.com/3072734/123707769-2d87a580-d8a5-11eb-92ca-8620bf13764e.png)

![login](https://user-images.githubusercontent.com/3072734/123707820-45f7c000-d8a5-11eb-8bdb-05a8796629fe.png)

#### Configurations
![clients](https://user-images.githubusercontent.com/3072734/123708111-d46c4180-d8a5-11eb-880f-ead20c14ce09.png)

![authentication](https://user-images.githubusercontent.com/3072734/123708228-01205900-d8a6-11eb-9b54-99176b66913b.png)

### Run by Docker
```shell
$ docker run --rm -it -p 8083:8083 shinyay/keycloak:0.0.1
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

You can import the exported configuration at `realmImportFile`

![export](https://user-images.githubusercontent.com/3072734/123708343-36c54200-d8a6-11eb-82d0-1e9cb90e5c09.png)


## Installation

## References

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
