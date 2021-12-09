# Spring Boot on Kubernetes Demo Project [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

In this project I'm demonstrating different mechanisms of deploying application on Kubernetes. The example application application is simple Spring Boot app that exposes some HTTP endpoints for CRUD operations and connects to MongoDB on cluster.

## Getting Started 
Currently you may find here some examples of different techniques of deploying this application on Kubernetes. All the examples are described in a separated articles on my blog. Here's a full list of available examples:
1. Using [Okteto Cloud Platform](https://okteto.com/) - Kubernetes for Developers. A detailed guide may be find in the following article: [Development on Kubernetes with Okteto and Spring Boot](https://piotrminkowski.com/2020/06/15/development-on-kubernetes-with-okteto-and-spring-boot/)

Mongo on Docker:
```shell
$ docker run --name mongodb -d -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=springboot \
  -e MONGO_INITDB_ROOT_PASSWORD=springboot123 \
  -e MONGO_INITDB_DATABASE=springboot \
  mongo:latest
```

Mongo for OpenShift:
```shell
$ oc new-app https://raw.githubusercontent.com/openshift/origin/master/examples/db-templates/mongodb-ephemeral-template.json 
```