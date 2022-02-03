# DataAccess con MongoDB

![Portada MongoDB](https://www.arsys.es/blog/file/uploads/2016/10/MongoDB.jpg)

### Proyecto de MarioGonzalezGomez y AndreaGomez2122

---


## :rocket: Sobre la aplicación

En este ejercicio crearemos una **base de datos noSQL** con **MongoDb**. Para administrarla, hemos utilizado **JPA** con soporte de **Hibernate**.
Al igual que en el caso anterior con SQL, crearemos el sistema de seguimiento de Proyectos informáticos de la empresa "DataAccessSL".

Este es [el esquema](https://github.com/AndreaGomez2122/DataAccess_MongoDB/blob/master/Diagrama%20y%20Explicaci%C3%B3n/UML.pdf) que seguimos en su implementación, que sigue una arquitectura de 3 niveles, con controladores, servicios y repositorios. 
Como añadido al enunciado anterior, en este caso probaremos los test usando mocks, específicamente, a través de la herramienta "Mockito".

---
## :robot: ¿Cómo iniciar la aplicación?

**IMPORTANTE**:

Antes de iniciarlo, deberemos abrir nuestro docker y ejecutar los siguientes comandos:

Para desplazarnos a la carpeta con el archivo.yml:

~~~
$ cd docker_mongoDB
~~~
Y lo ejecutamos con:
~~~
$ docker-compose up -d
~~~


Tras esto, nuestro programa se llamará con un JAR de la siguiente manera:

~~~
java -jar DataAccess_MongoDB.jar
~~~

Una vez que el programa se haya ejecutado se habrá creado un volumen con dos imágenes en docker, si todo ha ido bien también se han iniciado.
Sólo se debe introducir localhost:8081 en el navegador y tendrás una vista de la base de datos.

![bdcompass](https://user-images.githubusercontent.com/80425131/152405619-8c615bfe-6959-4eb5-bb00-415a99bba77e.png)
