Tworzenie obrazu bazy danych mysql - Ważne przejdź to jak montujesz bazę po raz pierwszy:

#docker run -p 3307:3306 --name my-mysql -e MYSQL_ROOT_PASSWORD=Warszawa5584 -d mysql/mysql-server:8.0.30

podłączenie się do bazy:
#docker exec -it my-mysql /bin/bash

bash-4.4# mysql -uroot -p -A
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 35
Server version: 8.0.30 MySQL Community Server - GPL

Copyright (c) 2000, 2022, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> select user, host from mysql.user;
+------------------+-----------+
| user             | host      |
+------------------+-----------+
| healthchecker    | localhost |
| mysql.infoschema | localhost |
| mysql.session    | localhost |
| mysql.sys        | localhost |
| root             | localhost |
+------------------+-----------+
5 rows in set (0.00 sec)


mysql> update mysql.user set host='%' where user='root';
Query OK, 1 row affected (1.18 sec)
Rows matched: 1  Changed: 1  Warnings: 0


mysql> flush privileges;
Query OK, 0 rows affected (0.46 sec)



mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| test               |
+--------------------+
5 rows in set (0.00 sec)

mysql> use test;
Database changed
mysql>

mysql> show tables in test;
Empty set (0.00 sec)

CREATE TABLE samochody(id int auto_increment, marka varchar(30), model varchar(30), rocznik int, pojemnosc float, przyspieszenie float, PRIMARY KEY(id));
INSERT INTO samochody VALUES(NULL, "Bugatti", "Veyron", 2018, 7993.0, 2.5);
INSERT INTO samochody VALUES(NULL, "Lamborghini", "Aventador", 2018, 6498.0, 2.9);
SELECT * FROM samochody;

SELECT * FROM product;


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



===================================================================================================



https://www.youtube.com/watch?v=zLgFYXMrXjQ  50:15''
docker run -p 9090:9090 prom/prometheus
docker run -p 3000:3000 grafana/grafana


uruchamiaj w gitBasza:  w lokalizacji projektu: ~/Documents/JAVA/SpringBoot/dockerProjects/01-hello-world-rest-api (master)
---------------------------------------------------------------------
1. Tworzymy Container - <d> detached    <it> - interactive shell:
docker run -dit openjdk:8-jdk-alpine

sprawdzamy images:
docker images

sprawdzamy jakie Containers pracują:
docker container ls

listowanie co siężńą€ęąąłó€tworzymy folder /tmp w kontenerze:
docker container exec intelligent_zhukovsky ls /tmp

wrzucamy fizyczny plik jar do Contenera do katalogu /tmp:
docker container cp target/hello-world-rest-api.jar intelligent_zhukovsky:/tmp

tworzymy image w Contenerze:
docker container commit intelligent_zhukovsky j4sysiak/hello-world-rest-api:manual1

usuwanie Image:
docker rmi <your-image-id>

Uruchamiamy image:  - ale musimy attach do niego coś jeszcze
docker run j4sysiak/hello-world-rest-api:manual1

sprawdzamy:  (na tym etapie ten image manual nie będzie diałać)
docker container ls

2. prawidłowe utworzenie obrazu
docker container commit --change='CMD ["java","-jar","/tmp/hello-world-rest-api.jar"]' intelligent_zhukovsky j4sysiak/hello-world-rest-api:manual2

2b.  i uruchomienie image w kontenerze:
docker run -p 8080:8080 j4sysiak/hello-world-rest-api:manual2

2c. Zatrzymanie:  Ctrl+Z  - lub z klienta wyłączamy image
lub
docker container stop [id]



===============================================================================
Bykowski
2.  https://www.youtube.com/watch?v=cqIu1h8FkMw&list=PLUtcRmGoaP24JStlolGq1wqjCC0YbMHKC

w cmd:

C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\01-hello-world-rest-api>docker build -f Dockerfile -t stream:v1 .
[+] Building 4.3s (7/7) FINISHED
 => [internal] load build definition from Dockerfile                                                                                                                0.7s
 => => transferring dockerfile: 156B                                                                                                                                0.0s
 => [internal] load .dockerignore                                                                                                                                   0.9s
 => => transferring context: 2B                                                                                                                                     0.0s
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                             0.0s
 => [internal] load build context                                                                                                                                   0.4s
 => => transferring context: 81B                                                                                                                                    0.0s
 => CACHED [1/2] FROM docker.io/library/openjdk:8-jdk-alpine                                                                                                        0.0s
 => [2/2] ADD target/hello-world-rest-api.jar .                                                                                                                     1.5s
 => exporting to image                                                                                                                                              0.8s
 => => exporting layers                                                                                                                                             0.5s
 => => writing image sha256:c959efd40db91f9f2cac4ccd40eac543ee595f8af0486ca27c9bd36a19c63210                                                                        0.1s
 => => naming to docker.io/library/stream:v2                                                                                                                        0.1s

Use 'docker scan' to run Snyk tests against images to find vulnerabilities and learn how to fix them


C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\01-hello-world-rest-api>docker images
REPOSITORY                      TAG              IMAGE ID       CREATED          SIZE
stream                          v2               c959efd40db9   2 minutes ago    122MB
helloworld                      latest           bc88cec09f98   22 minutes ago   122MB
01-hello-world-rest-api         0.0.1-SNAPSHOT   f60a46f853da   47 minutes ago   147MB
<none>                          <none>           139d0c9a9efe   51 minutes ago   147MB
ubuntu                          latest           df5de72bdb3b   3 weeks ago      77.8MB
grafana/grafana                 latest           c4b778290339   2 months ago     292MB
prom/prometheus                 latest           0c33456baaaa   2 months ago     211MB
<none>                          <none>           ccf9f10bc4a9   3 months ago     147MB
<none>                          <none>           e0eae122aa83   3 months ago     147MB
j4sysiak/hello-world-rest-api   dockerfile1      acfc108c7b3e   3 months ago     122MB
<none>                          <none>           dd8960418ca8   3 months ago     122MB
j4sysiak/hello-world-rest-api   0.0.1-SNAPSHOT   bebb537d4e70   3 months ago     122MB
<none>                          <none>           18ea67d1665f   3 months ago     122MB
<none>                          <none>           e3f4ddf615a2   3 months ago     122MB
<none>                          <none>           ac576d8ec56f   3 months ago     122MB
j4sysiak/hello-world-rest-api   manual2          2b89973d5761   3 months ago     122MB
j4sysiak/hello-world-rest-api   manual1          404033c6e81a   3 months ago     122MB
nginx                           latest           de2543b9436b   3 months ago     142MB
hello-world                     latest           feb5d9fea6a5   11 months ago    13.3kB
openjdk                         8-jdk-alpine     a3562aa0b991   3 years ago      105MB




C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\01-hello-world-rest-api>docker run -p 8000:8080 c959efd40db9
2022-08-28 15:37:13.929 DEBUG 1 --- [main] .c.l.ClasspathLoggingApplicationListener : Application started with classpath:
/.../
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.7.RELEASE)
/.../
2022-08-28 15:37:20.213  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-08-28 15:37:20.224  INFO 1 --- [           main] c.i.r.w.r.RestfulWebServicesApplication  : Started RestfulWebServicesApplication in 7.367 seconds (JVM running for 8.29)



C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\01-hello-world-rest-api>docker container ls
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                              NAMES
542ebc35bd77   c959efd40db9   "/bin/sh -c 'java -j…"   5 minutes ago   Up 5 minutes   8000/tcp, 0.0.0.0:8000->8080/tcp   thirsty_mccarthy



odpalenie:
http://localhost:8000/hello-world

=================================================================================
3. uruchomienie przy pomocy pliku Dockerfile:

dodajemy plik Dockerfile:
EXPOSE 8080
ADD target/hello-world-rest-api.jar hello-world-rest-api.jar
ENTRYPOINT ["sh", "-c", "java -jar /hello-world-rest-api.jar"]

#mvn clean package

tworzymy image z pliku Dockerfile:
Jacek@BERLIN MINGW64 ~/Documents/JAVA/SpringBoot/dockerProjects/01-hello-world-rest-api (master)
$ docker build -t j4sysiak/hello-world-rest-api:dockerfile1 .
/.../
#7 DONE 0.4s

    możemy też uruchomić:  docker run -p j4sysiak/hello-world-rest-api:dockerfile1
 

sprawdzamy:  (na tym etapie ten image manual nie będzie diałać)
#docker container ls
CONTAINER ID   IMAGE                                   COMMAND                  CREATED       STATUS         PORTS                    NAMES
3820647876f5   j4sysiak/hello-world-rest-api:manual2   "java -jar /tmp/hell…"   3 hours ago   Up 4 minutes   0.0.0.0:8080->8080/tcp   naughty_hugle
b9073c13a00e   openjdk:8-jdk-alpine                    "/bin/sh"                4 hours ago   Up 4 hours                              intelligent_zhukovsky

jeżeli jakiś chodzi kontener to go zatrzymujemy


#docker container stop 3820647876f5
#docker container stop b9073c13a00e

$ docker container ls
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES


uruchamiamy image:
docker run -p 8080:8080 j4sysiak/hello-world-rest-api:dockerfile1

4. robimy jakąś modyfikację w kodzie aplikacji
i potem:
#mvn clean package

-- zatrzymuje jakieś chodzące kontenery:
docker container ls

patrzę na historię:
docker history j4sysiak/hello-world-rest-api:dockerfile1

IMAGE          CREATED          CREATED BY                                      SIZE      COMMENT
ac576d8ec56f   18 minutes ago   ENTRYPOINT ["sh" "-c" "java -jar /hello-worl…   0B        buildkit.dockerfile.v0
<missing>      18 minutes ago   ADD target/hello-world-rest-api.jar hello-wo…   16.8MB    buildkit.dockerfile.v0
<missing>      18 minutes ago   EXPOSE map[8080/tcp:{}]                         0B        buildkit.dockerfile.v0
<missing>      3 years ago      /bin/sh -c set -x  && apk add --no-cache   o…   99.3MB
<missing>      3 years ago      /bin/sh -c #(nop)  ENV JAVA_ALPINE_VERSION=8…   0B
<missing>      3 years ago      /bin/sh -c #(nop)  ENV JAVA_VERSION=8u212       0B
<missing>      3 years ago      /bin/sh -c #(nop)  ENV PATH=/usr/local/sbin:…   0B
<missing>      3 years ago      /bin/sh -c #(nop)  ENV JAVA_HOME=/usr/lib/jv…   0B
<missing>      3 years ago      /bin/sh -c {   echo '#!/bin/sh';   echo 'set…   87B
<missing>      3 years ago      /bin/sh -c #(nop)  ENV LANG=C.UTF-8             0B
<missing>      3 years ago      /bin/sh -c #(nop)  CMD ["/bin/sh"]              0B
<missing>      3 years ago      /bin/sh -c #(nop) ADD file:a86aea1f3a7d68f6a…   5.53MB


robię ponownie builda z dockerfile1:
docker build -t j4sysiak/hello-world-rest-api:dockerfile1 .

uruchamiamy image:
docker run -p 8080:8080 j4sysiak/hello-world-rest-api:dockerfile1

-- zatrzymuje jakieś chodzące kontenery:
docker container ls


#docker container stop 125fa475ea22

=================================================================================
4. Taki automat - korzystamy z pluginu:
- From Spotify
- https://github.com/spotify/dockerfile-maven

wystarczy zrobić zmianę w kodzie i potem:  (tworzy się image i robi się build)
#mvn clean package

-- zatrzymuje jakieś chodzące kontenery:
docker container ls
#docker container stop 5536ded19e26

teraz wystarczy tylko uruchomić image
#docker run -p 8081:8080 j4sysiak/hello-world-rest-api:0.0.1-SNAPSHOT

docker container ls
CONTAINER ID   IMAGE                                          COMMAND                  CREATED          STATUS          PORTS                              NAMES
d93cd7e6eb13   j4sysiak/hello-world-rest-api:0.0.1-SNAPSHOT   "sh -c 'java -jar /a…"   28 seconds ago   Up 25 seconds   8081/tcp, 0.0.0.0:8081->8080/tcp   vigorous_kepler



=============================================================================
5. Lepszy automatically  - cache całą aplikację

dodajemy do pom.xml

### Maven Dependency Plugin


<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-dependency-plugin</artifactId>
	<executions>
		<execution>
			<id>unpack</id>
			<phase>package</phase>
			<goals>
				<goal>unpack</goal>
			</goals>
			<configuration>
				<artifactItems>
					<artifactItem>
						<groupId>${project.groupId}</groupId>
						<artifactId>${project.artifactId}</artifactId>
						<version>${project.version}</version>
					</artifactItem>
				</artifactItems>
			</configuration>
		</execution>
	</executions>
</plugin>



podmieniamy dockerfile:

```
FROM openjdk:8-jdk-alpine
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication"]
```

wystarczy zrobić zmianę w kodzie i potem:  (tworzy się image i robi się build)
#mvn clean package

-- zatrzymuje jakieś chodzące kontenery:
docker container ls
#docker container stop 4769737cd53b

robię ponownie builda z dockerfile1:
docker build -t j4sysiak/hello-world-rest-api:dockerfile1 .

uruchamiamy image:
docker run -p 8080:8080 j4sysiak/hello-world-rest-api:dockerfile1


=====================================
6. JIB

usuwam plik Dockfile
dodaje do POM

			<plugin>
				<groupId>com.google.cloud.tools</groupId>
				<artifactId>jib-maven-plugin</artifactId>
				<version>1.6.1</version>
				<configuration>
					<container>
						<creationTime>USE_CURRENT_TIMESTAMP</creationTime>
					</container>
				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>dockerBuild</goal>
						</goals>
					</execution>
				</executions>
			</plugin>


wystarczy zrobić zmianę w kodzie i potem:  (tworzy się image i robi się build)
#mvn clean package

Jacek@BERLIN MINGW64 ~/Documents/JAVA/SpringBoot/dockerProjects/01-hello-world-rest-api (master)
$ docker images
REPOSITORY                      TAG              IMAGE ID       CREATED          SIZE
01-hello-world-rest-api         0.0.1-SNAPSHOT   ccf9f10bc4a9   7 minutes ago    147MB


patrzę na historię:
docker history 01-hello-world-rest-api:0.0.1-SNAPSHOT

IMAGE          CREATED         CREATED BY               SIZE      COMMENT
ccf9f10bc4a9   8 minutes ago   jib-maven-plugin:1.6.1   3.31kB    classes
<missing>      8 minutes ago   jib-maven-plugin:1.6.1   43B       resources
<missing>      8 minutes ago   jib-maven-plugin:1.6.1   16.9MB    dependencies


-- zatrzymuje jakieś chodzące kontenery:
docker container ls
#docker container stop 4769737cd53b

uruchamiamy image:
#docker run -p 8080:8080 01-hello-world-rest-api:0.0.1-SNAPSHOT


====================================================================
7. Using Dockerfile Mavin plugin.





===================================  docker compose =================
https://www.youtube.com/watch?v=6c4-XJkDqC0&t=987s

konfiguracja w pliku: docker-compose.yml

version: '3'
services:
  api:
    build: ./demo8
    ports:
    - "9091:9091"
  database:
   container_name: 'database'
   image: mysql
   environment:
     MYSQL_ROOT_PASSWORD: Warszawa5584
     MYSQL_PASSWORD: jacek
     MYSQL_DATABASE: animalsdb
     MYSQL_USER: jacek
   ports:
   - "3307:3306"


--------

#docker-compose up
   
   
   