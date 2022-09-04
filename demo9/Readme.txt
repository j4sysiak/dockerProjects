===================================================================================================
https://www.youtube.com/watch?v=PAQvxqocb6A

#docker pull mysql:latest

#docker images
REPOSITORY   TAG            IMAGE ID       CREATED       SIZE
mysql        latest         ff3b5098b416   2 days ago    447MB
openjdk      8-jdk-alpine   a3562aa0b991   3 years ago   105MB

Tworzenie container mysqldb (najpierw usuwamy, jeżeli jakiś taki jest)
#docker rm -f mysqldb
#docker run -d -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test -v "C:/tmp/01-Data/mysqldb":/var/lib/mysql mysql
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

opcja zapamiętywania danych bazy po restartach:



tylko restarty przywracają dane


====================    docker-compose  ===============================
https://www.youtube.com/watch?v=-ekBqIvAGY4


aplikacja bez bazy (Spring-boot bez połączenia z bazą danych):
nie potrzebujemy na dockerze mysqldb	
docker-compose.yml:

version: '3'
services:
  springboot-app:
    container_name: spring-app
    image: springboot-app
    build: .
    ports:
      - 9090:8080
	


	  
#$ docker-compose up --build                                                                       
Building springboot-app
[+] Building 17.5s (7/7) FINISHED
 => [internal] load build definition from Dockerfile                                                                                                                                                     3.7s 
 => => transferring dockerfile: 31B                                                                                                                                                                      0.3s 
 => [internal] load .dockerignore                                                                                                                                                                        2.7s 
 => => transferring context: 2B                                                                                                                                                                          0.1s 
 => [internal] load metadata for docker.io/library/openjdk:8-jdk-alpine                                                                                                                                  0.0s 
 => [internal] load build context                                                                                                                                                                        5.3s 
 => => transferring context: 17.63MB                                                                                                                                                                     4.2s 
 => CACHED [1/2] FROM docker.io/library/openjdk:8-jdk-alpine                                                                                                                                             0.0s 
 => [2/2] ADD target/*.jar app.jar                                                                                                                                                                       3.5s 
 => exporting to image                                                                                                                                                                                   3.2s 
 => => exporting layers                                                                                                                                                                                  2.3s 
 => => writing image sha256:fa8e6ce83be1cf12aa1900c3b9fd3e912eeccc594ce2a1f514d1f1fc3c746b4e                                                                                                             0.3s 
 => => naming to docker.io/library/springboot-app                                                                                                                                                        0.2s 

Use 'docker scan' to run Snyk tests against images to find vulnerabilities and learn how to fix them
Creating springboot-docker-compose_springboot-app_1 ... done
Attaching to springboot-docker-compose_springboot-app_1
springboot-app_1  | 
springboot-app_1  |   .   ____          _            __ _ _
springboot-app_1  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
springboot-app_1  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
springboot-app_1  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
springboot-app_1  |   '  |____| .__|_| |_|_| |_\__, | / / / /
springboot-app_1  |  =========|_|==============|___/=/_/_/_/
springboot-app_1  |  :: Spring Boot ::                (v2.7.3)
springboot-app_1  |
springboot-app_1  | 2022-09-03 19:39:51.913  INFO 1 --- [           main] c.e.s.SpringbootDockerComposeApplication : Starting SpringbootDockerComposeApplication v0.0.1-SNAPSHOT using Java 1.8.0_212 on ba915
de6ad44 with PID 1 (/app.jar started by root in /)
springboot-app_1  | 2022-09-03 19:39:51.930  INFO 1 --- [           main] c.e.s.SpringbootDockerComposeApplication : No active profile set, falling back to 1 default profile: "default"
springboot-app_1  | 2022-09-03 19:39:54.788  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
springboot-app_1  | 2022-09-03 19:39:54.817  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
springboot-app_1  | 2022-09-03 19:39:54.817  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
springboot-app_1  | 2022-09-03 19:39:55.017  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
springboot-app_1  | 2022-09-03 19:39:55.018  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2777 ms
springboot-app_1  | 2022-09-03 19:39:55.777  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
springboot-app_1  | 2022-09-03 19:39:55.805  INFO 1 --- [           main] c.e.s.SpringbootDockerComposeApplication : Started SpringbootDockerComposeApplication in 4.899 seconds (JVM running for 5.794)
springboot-app_1  | 2022-09-03 19:40:40.189  INFO 1 --- [nio-8080-exec-3] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
springboot-app_1  | 2022-09-03 19:40:40.189  INFO 1 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
springboot-app_1  | 2022-09-03 19:40:40.191  INFO 1 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms



#$ docker ps
 

#$ docker-compose down