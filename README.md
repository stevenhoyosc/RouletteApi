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

## Rest Services Documentation.