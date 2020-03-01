# Spring Boot, Postgres and Docker based Shoe Service
* A shoe service which is build using springboot and postgres and containerized in docker. This service is a demo project

## Build

### 1. Build the app and Docker file:
* Run the following command to create a docker image.
`sudo docker build -t app .`

### 2. Run docker-compose
* Run the docker compose command to start the Postgres and app in the container.
`sudo docker-compose up`

### 3. Test the deployment
* Once application is started test data for brands will be loaded.
* Navigate to url `http://localhost:8088/shoe/allBrands` and you should see following results `[{"brandId":1,"brandName":"adidas"},{"brandId":2,"brandName":"nike"}`

## Usage

### Features

### Content

### Requirements

### Limitations




