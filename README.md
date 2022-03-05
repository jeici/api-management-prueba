## API REST Prueba tecnica ingeneo <br>
participante : Juan Carlos Serrano Mendoza

------------

####Tecnologia utilizada

* Java (version 8)
* Proyecto Maven
* Base de datos: PostgreSQL.
* framewor: spring boot version 2.6.3
* documentacion de endpoint : swagger
* despliegue: Heroku url: https://api-management-prueba.herokuapp.com

#### Listado de EndPoint's
* /api/auth/signup ; creacion de usuarios con listado de roles: admin, usr y cliente
* /api/auth/login : autenticación de los usuarios (Bearer Token).
* /crud/customer : gestion de clientes, rol de acceso : ADMIN
* /crud/product: gestion de catalogos de productos, rol de acceso : ADMIN.
* /crud/warehouse: gestion de depositos (bodegas y puertos), rol de acceso : ADMIN.
* /crud/logistic: catálogo de tipo de logistica (1 : logistica terrestre, 2:logistica maritima ), rol de acceso ADMIN.
* /delivery, gestion para los planes de entrega.

Nota: para todos los endpoints crud, se crearon los mismos 5 tipos de operacion

* /all = Método HTTP: GET : returna un listado de los registros de la entidad
* / = Método HTTP: POST : crea registro de la entidad
* /{id} = Método HTTP: GET : devuelve un registro de la entidad en base a su id
* /{id} =Método HTTP: PUT: actualiza un registro de la entidad segun su id
* /{id} = Método HTTP: DELETE : elimina un registro de la entidad.


####Base de datos (Diagrama E-R)

![Diagrama ER](https://github.com/jeici/api-management-prueba/blob/master/ER%20logistic.png "Diagrama ER")

####Seguridad <br>

Autenticacion ejemplo:

curl --location --request POST 'https://api-management-prueba.herokuapp.com/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"username":"user.admin",
"password":"123456",
"email":"admin.logistica@gmail.com",
"password":"123456",
"role":["admin"]
}'

Ejemplo de uso de token:

curl --location --request POST 'https://api-management-prueba.herokuapp.com/crud/customer/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbi5sb2dpc3RpY2EiLCJpYXQiOjE2NDU0MTM3MjEsImV4cCI6MTY0NTUwMDEyMX0.0lCIUiDhp8q6DQv1sJOF2CKhBSSLhzoSC0E7GoLnA9Y8VIyiwrCEvTtFn-HFRAb7138tvnG1Jhd4bHJasLVjnA' \
--header 'Content-Type: application/json' \
--data-raw '{
"fullName":"Rafael Medrano",
"phone":"22555250",
"email":"correoprueba@gmail.com",
"address":"Colonia y avenida los girasoles"
}'


