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

[Tables Description](https://www.notion.so/eb75a7712de940e5abaf4a751e340552)

---

## Rest Services Documentation.