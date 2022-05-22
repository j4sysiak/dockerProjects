https://www.udemy.com/course/docker-course-with-java-and-spring-boot-for-beginners/learn/lecture/16381804#overview


Uruchamiaj w gitBaszu:
---------------------------------------------------------------------
docker container exec crazy_wu ls /tmp
docker container cp target/hello-world-rest-api.jar crazy_wu:/tmp
docker container commit crazy_wu j4sysiak/hello-world-rest-api:manual1
docker run j4sysiak/hello-world-rest-api:manual1
docker container ls
docker container commit --change='CMD ["java","-jar","/tmp/hello-world-rest-api.jar"]' crazy_wu j4sysiak/hello-world-rest-api:manual2
docker run -p 8080:8080 j4sysiak/hello-world-rest-api:manual2