========================== cz.1. tworzenie projektu Vue =================
https://www.youtube.com/watch?v=PcdaxF452b0&list=PLF5X0J2bZ_k44MMIJvncXgJkzy0KxBGMB&index=15

https://github.com/ekim197711/springboot-vuejs-gradle/blob/master/Dockerfile


Jacek@BERLIN MINGW64 ~/Documents/JAVA/SpringBoot/dockerProjects/springboot-vuejs/src/myvuejs (master)
$ npm run build

Jacek@BERLIN MINGW64 ~/Documents/JAVA/SpringBoot/dockerProjects/springboot-vuejs/src/myvuejs (master)
$ npm run serve

> myvuejs@0.1.0 serve
> vue-cli-service serve
  App running at:
  - Local:   http://localhost:8080/
  - Network: http://192.168.1.104:8080/



narzędzie do zarządzania projektami Vue. Można lokalny projekt budować uruchamiać etc
Jacek@BERLIN MINGW64 ~/Documents/JAVA/SpringBoot/dockerProjects/springboot-vuejs/src/myvuejs (master)
$ vue ui    (odpaloamy localhost:8000)


========================== cz.2. łączenie SpringBoota restem z  Vue i jeszcze do tego na Dockerze :) =================

https://www.youtube.com/watch?v=PcdaxF452b0&list=PLF5X0J2bZ_k44MMIJvncXgJkzy0KxBGMB&index=18

https://github.com/ekim197711/springboot-vuejs-gradle

uruchamiamy: docker-compose up
odpalenie backendu:   http://localhost:9000/api/test
odpalenie frontu (Vue):   http://localhost:9000