# API RESTful de creación de usuarios

## Descripción
Es un endpoint que recibe un usuario con los campos "nombre", "correo", "contraseña",
más un listado de objetos "teléfono".

## Usar la Api:
http://localhost:9095/gestion-usuarios-api/api/v1/usuarios/crear

### Documentación de la API
http://localhost:9095/gestion-usuarios-api/swagger-ui/index.html

![swagger](swagger.png "swagger")


### method:
POST

### requestBody
Parámetros de entrada

```json
{
  "name": "Abel Contreras",
  "email": "abel@gmail.com",
  "password": "C@ntrer0s",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
    
  ]
}
```

### Response 
Parámetros de salida 

```json
{
    "id": "0cad029f-5e9e-4bcd-948d-f8243ddb3494",
    "created": "2024-09-20T04:00:08.861+00:00",
    "modified": "2024-09-20T04:00:08.949+00:00",
    "last_login": "2024-09-20T04:00:08.861+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmVsQGdtYWlsLmNvbSIsImlhdCI6MTcyNjgwNDgwOCwiZXhwIjoxNzI2ODQwODA4fQ.IYegSKvyDyCK_kWHMG0QnziTrEu69UoTZdJXL389FjQ",
    "isactive": true
}
```

### Validando correo existente
```json
{
    "mensaje": "El correo ya está registrado"
}
```
### Validando formato de correo 
```json
{
    "mensaje": "El formato del correo electrónico no es válido."
}
```
### Validando formato de contraseña

```json
{
    "mensaje": "El formato de la password no es válido."
}
```

### Test con postman

![postman](postman.png "postman")

### Importar el archivo del postman realizar pruebas unitarias.

Evaluación- JAVA.postman_collection.json

### Base de datos

![bd](bd.png "bd")


### Diagrama de la API.

![diagrama](diagrama.png "diagrama")



