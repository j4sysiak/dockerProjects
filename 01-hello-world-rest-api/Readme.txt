uruchamiaj w gitBaszu:  w lokalizacji projektu: ~/Documents/JAVA/SpringBoot/dockerProjects/01-hello-world-rest-api (master)
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
7. Using  Using XML Configuration: io.fabric8/docker-maven-plugin


<!-- To build the image - "mvn clean package" -->
<!-- TAG - 01-hello-world-rest-api:latest -->
<!-- docker run -p 8080:8080 01-hello-world-rest-api:latest -->
<plugin>
   <groupId>io.fabric8</groupId>
   <artifactId>docker-maven-plugin</artifactId>
   <version>0.26.0</version>
   <extensions>true</extensions>
   <configuration>
      <verbose>true</verbose>
      <images>
         <image>
            <name>${project.artifactId}</name>
            <build>
               <from>java:8-jdk-alpine</from>
               <entryPoint>
                  <exec>
                     <args>java</args>
                     <args>-jar</args>
                     <args>/maven/${project.build.finalName}.jar</args>
                  </exec>
               </entryPoint>
               <assembly>
                  <descriptorRef>artifact</descriptorRef>
               </assembly>
            </build>
         </image>
      </images>
   </configuration>
   <executions>
	<execution>
		<id>docker-build</id>
		<phase>package</phase>
		<goals>
			<goal>build</goal>
		</goals>
	</execution>
   </executions>
</plugin>
