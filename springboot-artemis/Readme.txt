------------------------------ 1 ----------------------------------
https://www.youtube.com/watch?v=aSDBE-Oe3Zs&t=3s

https://github.com/ekim197711/springboot-artemis

$ docker-compose up

C:\Users\Jacek>docker ps
CONTAINER ID   IMAGE                          COMMAND                  CREATED              STATUS              PORTS                                                                                                                                                                                                NAMES
9fbd14ada445   springboot-artemis-myartemis   "/artemis/mike/bin/a…"   About a minute ago   Up About a minute   0.0.0.0:1883->1883/tcp, 0.0.0.0:5445->5445/tcp, 0.0.0.0:5672->5672/tcp, 0.0.0.0:8161->8161/tcp, 0.0.0.0:9404->9404/tcp, 0.0.0.0:9876->9876/tcp, 0.0.0.0:61613->61613/tcp, 0.0.0.0:61616->61616/tcp   springboot-artemis-myartemis-1

C:\Users\Jacek>docker exec -it 9fbd
"docker exec" requires at least 2 arguments.
See 'docker exec --help'.

Usage:  docker exec [OPTIONS] CONTAINER COMMAND [ARG...]

Run a command in a running container

C:\Users\Jacek>docker exec -it 9fbd sh
/artemis # ls -ls
total 101288
     4 drwxr-xr-x    7 root     root          4096 Oct  7 17:50 apache-artemis-2.13.0
 56040 -rw-r--r--    1 root     root      57381985 May 18  2020 artemis.tar
     0 lrwxrwxrwx    1 root     root            31 Oct  7 17:50 current -> /artemis/apache-artemis-2.13.0/
 45232 -rw-r--r--    1 root     root      46314885 May  5  2020 jre14.tar
     8 drwxr-xr-x    1 root     root          4096 Oct  7 17:52 mike
     4 drwxr-sr-x    6 1000     1000          4096 Oct  7 17:50 zulu14.28.21-ca-jre14.0.1-linux_musl_x64

/artemis # cd mike
/artemis/mike #
/artemis/mike # ls
bin   data  etc   lib   lock  log   tmp
/artemis/mike # ls -ls
total 28
     4 drwxr-xr-x    2 root     root          4096 Oct  7 17:51 bin
     4 drwxr-xr-x    1 root     root          4096 Oct  7 17:52 data
     4 drwxr-xr-x    2 root     root          4096 Oct  7 17:51 etc
     4 drwxr-xr-x    2 root     root          4096 Oct  7 17:51 lib
     4 drwxr-xr-x    2 root     root          4096 Oct  7 17:52 lock
     4 drwxr-xr-x    1 root     root          4096 Oct  7 17:52 log
     4 drwxr-xr-x    1 root     root          4096 Oct  7 17:52 tmp


/artemis/mike # cd etc
/artemis/mike/etc # ls -al
total 56
drwxr-xr-x    2 root     root          4096 Oct  7 17:51 .
drwxr-xr-x    1 root     root          4096 Oct  7 17:52 ..
-rw-r--r--    1 root     root           960 Oct  7 17:51 artemis-roles.properties
-rw-r--r--    1 root     root          1160 Oct  7 17:51 artemis-users.properties
-rw-r--r--    1 root     root          3017 Oct  7 17:51 artemis.profile
-rw-r--r--    1 root     root          1514 Oct  7 17:51 bootstrap.xml
-rw-r--r--    1 root     root         11126 Oct  7 17:51 broker.xml
-rw-r--r--    1 root     root          1304 Oct  7 17:51 jolokia-access.xml
-rw-r--r--    1 root     root          3571 Oct  7 17:51 logging.properties
-rw-r--r--    1 root     root          1086 Oct  7 17:51 login.config
-rw-r--r--    1 root     root          2172 Oct  7 17:51 management.xml

/artemis/mike/etc # cat broker.xml





------------------------------ 2 ----------------------------------

https://www.youtube.com/watch?v=4iy5B72rPG4

