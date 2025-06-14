# GamingHeaven - TFC

## *¿Cómo pruebo la web?*

## - Si la web está levantada en el servidor

Se debe acceder al siguiente enlace:

[gamingheaven.site](https://gamingheaven.site)

## - Si la web NO está levantada en el servidor

### Clonación y ejecución del proyecto:

### 1- Software

* **Java**: Hay que instalar el JDK 21 y añadir la variable de entorno de Java en la variable PATH para poder levantar el proyecto.
* **Maven**: Para las dependencias del proyecto se debe tener instalado el Maven y asignada su variable de entorno en la variable PATH.
* **XAMPP**: Se debe descargar XAMPP junto con el MySQL para poder ejecutar el servicio de MySQL y así poder utilizar la base de datos en local.
* **IDE**: Es necesario descargar un IDE que soporte Java. Recomiendo usar IntelliJ IDEA Community Edition en su última versión.
* **SGBD**: Para importar la base de datos es necesario un gestor. En mi caso estoy utilizando DBeaver.

### 2- BD

Una vez dentro del DBeaver y con el servicio de MySQL levantado (con XAMPP), hay que importar y ejecutar el script SQL "GamingHeavenTFC.sql"
que se encuentra en la raíz del proyecto. Este creará la base de datos, las tablas y hará los inserts necesarios.

Además, será necesario configurar el usuario y contraseña de MySQL para que contenga tus credenciales. El archivo de configuración está en src/main/resources dentro del proyecto en el archivo application.properties:
```
  spring.datasource.username=tu_user
  spring.datasource.password=tu_pass
```

### 3- Ejecución

Para ejecutar el proyecto se deberá tener git instalado para clonar el proyecto la ruta que se desee. A continuación, y con la BD importada, el MySQL levantado y el IDE abierto, se deberá comentar:

* server.use-forward-headers=true (src/main/resources/application.properties)
* server.forward-headers-strategy=native (src/main/resources/application.properties)
* .requiresChannel (comentar de principio a fin la declaración del método en src/main/java/com/ciroiencom/gamingheaventfc/config/SecurityConfig.java)

Los comentarios en el archivo properties se realizan colocando una almohadilla '#' delante de una línea y en un .java con __/*__ al comienzo del bloque que se desea comentar y __*/__ para cerrar dicho comentario.

Por último, queda ejecutar la clase que contiene el método "main" (en este caso es la clase "GamingHeavenTfcApplication.java", ubicada en src/main/java/com/ciroiencom/gamingheaventfc).

Para probar su funcionamiento se debe acceder al siguiente enlace:

[http://localhost:8080/](http://localhost:8080/)

