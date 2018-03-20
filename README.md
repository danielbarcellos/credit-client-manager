# Gerenciamento clientes e seus limites de crédito (CCM Backend API)
Api de para uso no sistema CCM.

## A solução
Esta solução visa disponibilizar uma api para consumo de serviços relativos ao sistema de gerenciamento de crédito de clientes (CCM). Para construir este projetos foram utilizados a plataforma Java e [Spring Boot 2.0.0-RELEASE](https://projects.spring.io/spring-boot/) orientado a uma arquitetura [RESTful](https://restfulapi.net/) de [Microserviço](https://www.martinfowler.com/articles/microservices.html) com um container [Tomcat](tomcat.apache.org/) embarcado. 

Para descrever todos os endpoint fo utilizado [Swagger 2](https://swagger.io/).

## Iniciando
Clone de [GIT Hub](https://github.com/danielbarcellos/credit-client-manager). É necessário ter instalado o [Java 8](www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html):

```
git clone https://github.com/danielbarcellos/credit-client-manager
cd credit-client-manager
mvnw spring-boot:run
```
O sistema estará disponível em [http://localhost:8080](http://localhost:8080)

## O que fazer quando estiver iniciado?

Você pode testar se está tudo ok tentando acesso à api de clientes.

> Os testes abaixos todos foram feitos utilizando [HTTPie](https://httpie.org/). Mas não impede de você usar uma ferramenta visual como [Postman](https://www.getpostman.com/) ou [Insomnia](https://insomnia.rest/)

Fazendo uma rquisição para a api de clientes:

```shell
http http://localhost:8080/ccm/api/clientes
```

Resposta do servidor será:

```json
{
    "content": [
        {
            "id": 1,
            "name": "Daniel",
            "limits": null,
            "address": "Rua A"
        },
        {
            "id": 2,
            "name": "Rachel",
            "limits": null,
            "address": "Rua B"
        }
    ],
    "pageable": {
        "sort": {
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "unpaged": false,
        "paged": true
    },
    "totalPages": 1,
    "totalElements": 2,
    "last": true,
    "size": 20,
    "number": 0,
    "sort": {
        "unsorted": true,
        "sorted": false
    },
    "first": true,
    "numberOfElements": 2
}
```
Toda a api está descrita e documentada usando [Swagger 2](https://swagger.io/). Após iniciar, acesso o endereço abaixo:

```
http://localhost:8080/ccm/swagger-ui.html
```

## Built With

* [Spring Boot 2](https://projects.spring.io/spring-boot/) - Plataforma de aplicações.
* [H2 Database Engine](http://h2database.com/html/main.html) - Banco de dados em memória.
* [Swagger 2](https://swagger.io/) - Documentação de API.
## Versionamento

We use [Github](https://github.com) for versioning. For the versions available, see the [tags on this repository](https://github.com/danielbarcellos/credit-client-manager). 

## Authors

* **Daniel Barcellos** - *Initial work* - [Daniel Barcellos](https://github.com/danielbarcellos)

## Agradecimentos

* My family
* Google
