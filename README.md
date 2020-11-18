# Roulette API REST SERVICE

This API is build in JAVA EE with SQL SERVER 2016 (Database motor). I've been working in five services that I m going to describe in this document. 

> Installation

Compile the project with java 8, and use JBOSS or [Wildly 20](https://www.wildfly.org/news/2020/06/08/WildFly20-Final-Released/) application server to deploy the services. 

> Database

For database creation I used the next query: 

```sql
CREATE TABLE rlt_srv_usr(
    idusr int identity(1,1) NOT NULL primary key,
    name varchar(50) NOT NULL,
    lastname varchar(50) NOT NULL,
    usrmoney money NOT NULL,
);
CREATE TABLE rlt_srv_rlt(
    idrlt int identity(1,1) NOT NULL primary key,
    status bit DEFAULT 0 NOT NULL,
);
CREATE TABLE rlt_srv_usrbets(
    idbets int identity(1,1) NOT NULL primary key,
    idusr int NOT NULL,
    idrlt int NOT NULL,
    betmoney money NOT NULL,
    numberbeat int NULL,
    colorbeat varchar(5)  NULL,
    winner bit DEFAULT 0 NOT NULL
);

ALTER TABLE rlt_srv_usrbets
ADD FOREIGN KEY (idusr) REFERENCES rlt_srv_usr(idusr);

ALTER TABLE rlt_srv_usrbets
ADD FOREIGN KEY (idrlt) REFERENCES rlt_srv_rlt(idrlt);
```

| Table | Description |
| --- | ----------- |
| rlt_srv_usr | All information of the user, including the money that they can use to bet |
| rlt_srv_usrbets | Data of bets, have information of each beat that is do it |
| rlt_srv_rlt | Information of the roulette, if any of these is open or close |


---

# Rest Services Documentation.

### Configuration of Connection to Database

I built a configuration XML document to manage all configuration parameters of Database. This document is read by the application to set parameters of connection. The name of the document is **dbconfig.xml** 

### Endpoint:  Creation of new Roulette.

Consume  **newRoulette** service**.** this is a POST petition that return a the created roulette in a JSON format : 

```json
{
    "idrlt": 1,
    "status": false
}
```

The petition doesn't need any body or header configurable parameter , only that the client realize the POST petition to  [http://localhost:8080/rouletteservice/query/roulette/newRoulette](http://localhost:8080/rouletteservice/query/roulette/newRoulette)

### Endpoint: List of roulettes created

Consume **allRoulete** service. This is a GET petition that return all roulettes created in a JSON format as below: 

```json
[
    {
        "idrlt": 1,
        "status": true
    },
    {
        "idrlt": 2,
        "status": false
    }
}
```

The petition doesn't need any body or header configurable parameter , only that the client realize the GET petition to  [http://localhost:8080/rouletteservice/query/roulette/allRoulete](http://localhost:8080/rouletteservice/query/roulette/allRoulete)

### Endpoint: open some roulette

Consume **openRoulette** service. This is a PUT petition that update an specific roulette by the id.

The client should sent the petition with a header that must have the **id of the roulette** to update for example: 

```
Key: idRoulette
Value: 1
```

The service return **TRUE** if the petition was successful or **FALSE** in the other case.

Put petition to: [http://localhost:8080/rouletteservice/query/roulette/openRoulette](http://localhost:8080/rouletteservice/query/roulette/openRoulette)

### Endpoint: Open Bets

Consume **openBets** service. This is a POST petition that permit people bet to a roulette. Also, the service do multiple validations:

- Validate if the user can bet.
- Validate if the roulette can be used to bet.
- Validate the top money that a roulette should be.

The service need that the client sent a POST petition with a body as below: 

```json
{
    "idUsr": 2,
    "idRoulette": 1,
    "moneyBet": 5000,
    "numberBet": 0,
    "colorBet": "black",
    "betColor": true
}
```

                        

- idUsr: id of the user that do the bet
- idRoulette: id of the roulette to bet
- moneyBet: Quantity of money to bet of the user
- numberBet: If the user bet to a number
- colorBet: if the user bet to a color
- betColor: true if the user bet to a color, otherwise false.

The response of the service is a message with success or fail.

```json
{
    "rouletteid": "1",
    "message": "All ok, bet relized"
}
```

```json
{
    "rouletteid": "1",
    "message": "Problem With money in user or bet"
}
```

```json
{
    "rouletteid": "3",
    "message": "Close Roulette to bet"
}
```

POST petition to : [http://localhost:8080/rouletteservice/query/roulette/openBets](http://localhost:8080/rouletteservice/query/roulette/openBets)

### Endpoint: resolve the winner of the bet

Consume **winnRoulette** service. This service return the final state of each bet that got to the roulette.  The service calculate the winner with a random number between 0 and 36.

The service need a header that must have the **id of the roulette** as below in a example: 

```
Key: idRoulette
Value: 1
```

The service response data as a JSON with the winner number and all bets in the roulette as below, 

```json
{
    "msg": [
        "Winner Number: ",
        23
    ],
    "data": [
        {
            "betmoney": 1800.0000,
            "colorbeat": "black",
            "idrlt": 1,
            "idusr": 2,
            "numberbeat": 0,
            "winner": true
        },
        {
            "betmoney": 0.0000,
            "colorbeat": "red",
            "idrlt": 1,
            "idusr": 1,
            "numberbeat": 0,
            "winner": false
        },
        {
            "betmoney": 0.0000,
            "colorbeat": "",
            "idrlt": 1,
            "idusr": 3,
            "numberbeat": 3,
            "winner": false
        }
    ]
}
```