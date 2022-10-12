Play With Docker:
- https://labs.play-with-docker.com
- Ngrok

https://www.youtube.com/watch?v=PhaBPxx8k5k&list=PLF5X0J2bZ_k44MMIJvncXgJkzy0KxBGMB&index=28

https://github.com/ekim197711/springboot-mysql/blob/main/docker/docker-compose.yml


---------------------------------------------------------------------------------------------

uruchamiamy:
https://labs.play-with-docker.com
i Start ...

krok 1:  dodanie pliku
$ vi docker-compose.yml

wklejamy zawartość:

version: '3.1'

services:

  db:
    image: mysql
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: test1234

  adminer:
    image: adminer
    restart: always
    ports:
      - 8080:8080


krok 2: uruchomienie
$ docker-compose up -d
$ docker ps
$ docker images

na stronie otwarej sesji https://labs.play-with-docker.com - klikamy na port "8080" -  otwiera się strona:  http://ip172-18-0-13-cd33k8n91rrg00djegrg-8080.direct.labs.play-with-docker.com

logowanie do bazy:
login: root
hasło: test1234      (takie wpisaliśmy w docker-compose.yml)
baza: db

Wersja MySQL: 8.0.31 za pomocą MySQLi
Zalogowany jako: root@172.20.0.3

niby się zalogowałem do bazy ale schematu db nie ma jeszcze.


krok 3: uruchomienie ngrok
https://gist.github.com/SalahHamza/799cac56b8c2cd20e6bfeb8886f18455

tworzymy w katalogu root plik kaka.sh

#! /bin/bash
echo pppppppppppppppp
wget https://bin.equinox.io/c/4VmDzA7iaHb/ngrok-stable-linux-amd64.zip
unzip ngrok-stable-linux-amd64.zip
sudo apt install unzip

$ chmod 777 kaka
$ ./kaka
$ ./gkrok



----------------------------  testy
ssh ip172-18-0-13-cd33k8n91rrg00djegrg@direct.labs.play-with-docker.com

ngrok-stable-linux-amd64.zip

#! /bin/bash
echo pppppppppppppppp

