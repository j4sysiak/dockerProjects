===================================================================================================
https://www.youtube.com/watch?v=PAQvxqocb6A

#docker pull mysql:latest

#docker images
REPOSITORY   TAG            IMAGE ID       CREATED       SIZE
mysql        latest         ff3b5098b416   2 days ago    447MB
openjdk      8-jdk-alpine   a3562aa0b991   3 years ago   105MB

Tworzenie container mysqldb (najpierw usuwamy, jeżeli jakiś taki jest)
#docker rm -f mysqldb
#docker run -d -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -v "/C/tmp/db":/var/lib/mysql mysql
UWAGA - to się łąduje pare minut, na końcu musi być coś takiego.
na koncu zawsze sprawdź, czy możesz zalogować się do bazy.   localhost:3307  login/hasło  root/root
#docker logs mysqldb
/.../
2022-09-02T11:38:44.644219Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.30'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.

budujemy aplikację na dokerze:
17:20' #docker build -t app .

#docker images
REPOSITORY   TAG            IMAGE ID       CREATED         SIZE
app          latest         88b2af70bd61   3 minutes ago   143MB
mysql        latest         ff3b5098b416   2 days ago      447MB
openjdk      8-jdk-alpine   a3562aa0b991   3 years ago     105MB

#docker rm app

tworzenie kontenera z tego image'a app (id=88b2af70bd61)
#docker run -p 9090:8080 --name app -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 app

ERROR - musimy poprawić
robimy network:

usuń jeżeli jest: #docker network rm spring-net
#docker network create spring-net
e04c6df27835063cbeb336fd1f597f8505ae8d5a25c2bc13bbf1d6fba409187f


#docker network ls
NETWORK ID     NAME         DRIVER    SCOPE
d862d4bfb9e8   bridge       bridge    local
d4712a93f8b9   host         host      local
8bae8e4c0a6d   none         null      local
e04c6df27835   spring-net   bridge    local


łaczymy nasz nowy network spring-net z kontenerem mysqldb:
#docker network connect spring-net mysqldb

sprawdzamy połączenie:
#docker container inspect mysqldb

                "spring-net": {
                    "IPAMConfig": {},
                    "Links": null,
                    "Aliases": [
                        "70ffedae7fa0"
                    ],
                    "NetworkID": "e04c6df27835063cbeb336fd1f597f8505ae8d5a25c2bc13bbf1d6fba409187f",
                    "EndpointID": "337bb86cdc06f209e71edccabec8fe0a3eb3800c1cd3ea1b6a637f71868541a9",
                    "Gateway": "172.18.0.1",
                    "IPAddress": "172.18.0.2",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:ac:12:00:02",
                    "DriverOpts": {}
                }




#docker rm app
#docker build -t app .
#docker run -p 9090:8080 --name app --net spring-net -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 app

lub trorzymy plik env
#docker run -p 9090:8080 --name app --net spring-net --env-file env app

odpalamy aplikację: http://localhost:9090/products

[
{
"id": 1,
"name": "aaaa",
"price": 11
},
{
"id": 2,
"name": "dddd",
"price": 234
},
]


tylko restarty przywracają dane


