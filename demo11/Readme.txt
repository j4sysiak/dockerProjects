https://www.youtube.com/watch?v=1xpgfwA5l3Q&t=188s


# $docker-compose up
UWAGA: odczekaj kilka minut zanom mysql wstanie ...
2022-09-06T05:59:57.525422Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.30'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
2022-09-06T05:59:57.530146Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060, socket: /var/run/mysqld/mysqlx.sock

... i dopiero później odpali się Springboot:c  (ale musibyć w pliku docker-compose.yml zapis:  restart)

easy-eat-service-app | 2022-09-06 06:00:13.145  WARN 1 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be perf
ormed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
easy-eat-service-app | 2022-09-06 06:00:15.890  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
easy-eat-service-app | 2022-09-06 06:00:15.914  INFO 1 --- [           main] com.example.demo11.Demo11Application     : Started Demo11Application in 20.97 seconds (JVM running for 21.945)
