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


/artemis/mike # cd /etc
/etc # ls -la
total 168
drwxr-xr-x    1 root     root          4096 Oct  7 17:52 .
drwxr-xr-x    1 root     root          4096 Oct  7 17:52 ..
-rw-r--r--    1 root     root             7 Aug  9 08:46 alpine-release
drwxr-xr-x    1 root     root          4096 Oct  7 17:49 apk
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 conf.d
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 crontabs
-rw-r--r--    1 root     root            89 Jun 13 15:39 fstab
-rw-r--r--    1 root     root           697 Jun 13 15:39 group
-rw-r--r--    1 root     root            13 Oct  7 17:52 hostname
-rw-r--r--    1 root     root           174 Oct  7 17:52 hosts
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 init.d
-rw-r--r--    1 root     root           570 Jun 13 15:39 inittab
-rw-r--r--    1 root     root            54 Aug  9 08:46 issue
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 logrotate.d
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 modprobe.d
-rw-r--r--    1 root     root            15 Jun 13 15:39 modules
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 modules-load.d
-rw-r--r--    1 root     root           283 Jun 13 15:39 motd
lrwxrwxrwx    1 root     root            12 Oct  7 17:52 mtab -> /proc/mounts
drwxr-xr-x    8 root     root          4096 Aug  9 08:47 network
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 opt
-rw-r--r--    1 root     root           188 Aug  9 08:46 os-release
-rw-r--r--    1 root     root          1172 Jun 13 15:39 passwd
drwxr-xr-x    7 root     root          4096 Aug  9 08:47 periodic
-rw-r--r--    1 root     root           857 Jun 13 15:39 profile
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 profile.d
-rw-r--r--    1 root     root          2932 Jun 13 15:39 protocols
-rw-r--r--    1 root     root            38 Oct  7 17:52 resolv.conf
drwxr-xr-x    2 root     root          4096 Aug  9 08:46 secfixes.d
-rw-r--r--    1 root     root            98 Aug  1 15:14 securetty
-rw-r--r--    1 root     root         12966 Jun 13 15:39 services
-rw-r-----    1 root     shadow         422 Aug  9 08:47 shadow
-rw-r--r--    1 root     root            38 Jun 13 15:39 shells
drwxr-xr-x    5 root     root          4096 Aug  9 08:47 ssl
-rw-r--r--    1 root     root            53 Jun 13 15:39 sysctl.conf
drwxr-xr-x    2 root     root          4096 Aug  9 08:47 sysctl.d
-rw-r--r--    1 root     root          5636 Aug  1 15:14 udhcpd.conf
-rw-r--r--    1 root     root          4945 Apr  1  2022 wgetrc



------------------------------ 2 ----------------------------------

https://www.youtube.com/watch?v=4iy5B72rPG4

