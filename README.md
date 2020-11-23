# Roulette API REST SERVICE

This API is built in JAVA EE with SQL SERVER 2016 (Database motor). I've been working on five services which I m going to describe in this document.

> Installation

Compile the project with java 8, and use JBOSS or [Wildly 20](https://www.wildfly.org/news/2020/06/08/WildFly20-Final-Released/) application server to deploy the services.

> Database

I used the following query for database creation:

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

| Table           | Description                                                                                    |
| --------------- | ---------------------------------------------------------------------------------------------- |
| rlt_srv_usr     | All user information, including the money they can use to bet                                  |
| rlt_srv_usrbets | it has the data of each bet that it's going to be carried out                                  |
| rlt_srv_rlt     | It contains the information of the roulette, including if any of these is either open or close |

---

# Rest Services Documentation.

### Configuration for Connection to Database

I built a XML configuration document to manage all setup parameters of the Database. This document is set up by the application to run parameters of connection. The document's name is **dbconfig.xml**

### Endpoint: Creation of new Roulette.

Consume **newRoulette** service**.** this is a POST petition that returns the newly established roulette in a JSON format :

```json
{
  "idrlt": 1,
  "status": false
}
```

The petition does not need neither body nor header configurable parameter , only the client must carry out the POST petition to [http://localhost:8080/rouletteservice/query/roulette/newRoulette](http://localhost:8080/rouletteservice/query/roulette/newRoulette)

### Endpoint: List of created roulettes

Consume **allRoulete** service. This is a GET petition that returns all roulettes created in a JSON format as below:

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

The petition does not need neither body nor header configurable parameter , only the client must carry out the GET petition to [http://localhost:8080/rouletteservice/query/roulette/allRoulete](http://localhost:8080/rouletteservice/query/roulette/allRoulete)

### Endpoint: A roulette is open up

Consume **openRoulette** service. This is a PUT petition that update an specific roulette by using the id code.

The client must send the petition containing a header with the **id of the roulette** to be updated, for example:

```
Key: idRoulette
Value: 1
```

The service returns **TRUE** if the petition was successful or **FALSE** in the other case.

Put petition to: [http://localhost:8080/rouletteservice/query/roulette/openRoulette](http://localhost:8080/rouletteservice/query/roulette/openRoulette)

### Endpoint: Opening Bets

Consume **openBets** service. This is a POST petition that permits people to bet on a roulette. In addition, the service makes multiple validations:

- Validate if the user can bet.
- Validate if the roulette could be used to bet.
- Validate the maximum amount of money a roulette should have.

The service needs that the client sends a POST petition with a body as below:

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

- idUsr: id of the user making a bet
- idRoulette: id of the roulette to be bet
- moneyBet: Amount of money to be bet by the user
- numberBet: If the user bets for a number
- colorBet: if the user bets for a color
- betColor: true if the user bet to a color, otherwise false.

The response of the service is a message with success or fail.

```json
{
  "rouletteid": "1",
  "message": "Successful bet"
}
```

```json
{
  "rouletteid": "1",
  "message": "User's money or bet were wrong"
}
```

```json
{
  "rouletteid": "3",
  "message": "Roulette close to bet"
}
```

POST petition to : [http://localhost:8080/rouletteservice/query/roulette/openBets](http://localhost:8080/rouletteservice/query/roulette/openBets)

### Endpoint: resolving the bet winner

Consume **winnRoulette** service. This service returns the final state of each bet made on the roulette. The service calculates the winner with a random number between 0 and 36.

The service needs a header containing the **id of the roulette** as below in a example:

```
Key: idRoulette
Value: 1
```

The service response data is a JSON format containing winning number and all bets in the roulette as below,

```json
{
  "msg": ["Winner Number: ", 23],
  "data": [
    {
      "betmoney": 1800.0,
      "colorbeat": "black",
      "idrlt": 1,
      "idusr": 2,
      "numberbeat": 0,
      "winner": true
    },
    {
      "betmoney": 0.0,
      "colorbeat": "red",
      "idrlt": 1,
      "idusr": 1,
      "numberbeat": 0,
      "winner": false
    },
    {
      "betmoney": 0.0,
      "colorbeat": "",
      "idrlt": 1,
      "idusr": 3,
      "numberbeat": 3,
      "winner": false
    }
  ]
}
```

POST petition to : [http://localhost:8080/rouletteservice/query/roulette/winnRoulette](http://localhost:8080/rouletteservice/query/roulette/winnRoulette)
