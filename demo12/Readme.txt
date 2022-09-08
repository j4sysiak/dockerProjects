https://www.youtube.com/watch?v=FhsAsUVhCRY&t=230s

------------- 1 --------------------
uruchamiamy z polecenia:
#docker build -t demo12 .
#docker run -p 9000:8080 -e user=Luck 7ec522b13318
odpalamy:
http://localhost:9000/test

------------- 2 --------------------
lub budujemy i uruchamiamy wprost z intelijeja - uruchamiakjąc zieloną strałką >> w pliku Dockerfile
prawy przycisk myszy i edit :Dockerfile"
w polu Image tag:  jsysiak/docker-demo12:v4
Apply i Run



C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\demo12>docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED              STATUS              PORTS      NAMES
85241682819b   d5842d03772b   "java -jar /opt/app/…"   About a minute ago   Up About a minute   8080/tcp   condescending_ganguly

C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\demo12>docker images
REPOSITORY              TAG             IMAGE ID       CREATED         SIZE
jsysiak/docker-demo12   v4              d5842d03772b   2 minutes ago   186MB
<none>                  <none>          a345baaa2484   3 minutes ago   481MB
mysql                   latest          ff3b5098b416   8 days ago      447MB
eclipse-temurin         17-jre-alpine   cabdba77eafb   2 weeks ago     168MB
eclipse-temurin         17-jdk-alpine   7360943d2e07   2 weeks ago     356MB
phpmyadmin/phpmyadmin   latest          4a4023c7e22a   3 months ago    510MB
openjdk                 8-jdk-alpine    a3562aa0b991   3 years ago     105MB

uruchamiamy:
C:\Users\Jacek\Documents\JAVA\SpringBoot\dockerProjects\demo12>docker run -d -p 9000:8080 -e user=Luck d5842d03772b
5a08765b9b1227ee9dc28d415060a4b314fa95a938c0a21bf92630157ec5b57c

odpalamy:
http://localhost:9000/test


