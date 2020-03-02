# Spring Boot, Postgres and Docker based Shoe Service
* A shoe service which is build using springboot and postgres and containerized in docker. This service is a demo project

## Build

### 1. Build the app and Docker file:
* Clone this repository or download the code.
* Run the maven command to create the jar file(You will need to have java installed in your machine for this. Maven is not required to be installed as maven wrapper is provided)
```
./mvnw clean install -Dactive_profile=prd 
```
* Run the following command to create a docker image.

`sudo docker build -t app .`

### 2. Run docker-compose
* Run the docker compose command to start the Postgres and app in the container.

`sudo docker-compose up`

### 3. Test the deployment
* Once application is started test data for brands will be loaded.
* Navigate to url `http://localhost:8088/shoe/allBrands` and you should see following results 

`[{"brandId":1,"brandName":"adidas"},{"brandId":2,"brandName":"nike"}`

## Usage
The service provide following operations.

<B>Add New Brands</B>: this allows users to add new brand. 

*Syntax:*
```
http:\\localhost:8080/shoe/addBrand?brand={VALUE}
```
*Request Example*
```
http://localhost:8080/shoe/addBrand?brand=reebok
```


<B>Get All Brands</B>: this allows users to fetch a list of all the brands added in the system.

*Syntax:*
```
http://localhost:8080/shoe/allBrands
```
*Request Example*
```
http://localhost:8080/shoe/allBrands
```

<B>Add True size</B>: this allows users to add new true size for a given brand and shoe size.

*Syntax:*
```
http://localhost:8080/shoe/addTrueSize?brand={brandName}&shoeSize={ShoeSize}&trueSize={TrueSizeValue}
```
*Request Example*
```
http://localhost:8080/shoe/addTrueSize?brand=nike&shoeSize=8&trueSize=2
http://localhost:8080/shoe/addTrueSize?brand=nike&shoeSize=8&trueSize=3
http://localhost:8080/shoe/addTrueSize?brand=nike&shoeSize=8&trueSize=5

http://localhost:8080/shoe/addTrueSize?brand=adidas&shoeSize=9&trueSize=5
http://localhost:8080/shoe/addTrueSize?brand=adidas&shoeSize=9&trueSize=2
http://localhost:8080/shoe/addTrueSize?brand=adidas&shoeSize=9&trueSize=1
```

<B>Get True size</B>: this allows users to get the true size for a given brand and shoe size.

*Syntax:*
```
http://localhost:8080/shoe/getTrueSize?brand={brandName}&shoeSize={ShoeSize}
```
<b> Request Example With Respoonse</b>
```
http://localhost:8080/shoe/getTrueSize?brand=adidas&shoeSize=9

{
  "brandId": 2,
  "brandName": "adidas",
  "shoeSize": "9",
  "avgTrueSize": 2.66666666666667
}
```
```
http://localhost:8080/shoe/getTrueSize?brand=nike&shoeSize=8

{
  "brandId": 1,
  "brandName": "nike",
  "shoeSize": "8",
  "avgTrueSize": 3.33333333333333
}
```
