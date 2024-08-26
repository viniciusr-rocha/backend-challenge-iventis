
# Sobre o Projeto 📖
O Desafio Backend Iventis é um microsserviço desenvolvido para gerenciar compras,
identificar clientes leais e fornecer recomendações personalizadas de vinhos.  
Construído com Java, Spring Boot, e documentado com Swagger.

## Construído com 🛠️

* [![Java][Java]][Java-url]
* [![Gradle][Gradle]][Gradle-url]
* [![Swagger][Swagger]][Swagger-url]
* [![Spring][Spring]][Spring-url]

<!-- GETTING STARTED -->

## Começando 🚀

Este guia fornece instruções simples e diretas para rodar a aplicação. Siga os passos abaixo para configurar o ambiente
e iniciar o projeto em poucos minutos.

### Pré-requisitos 📋

* Docker
* Java versão >= 17 
* Gradle versão >= 7.3

### Executando ⚙️

1. Inicie a aplicação diretamente de uma imagem do Docker Hub
   ```sh
   docker run --name challenge-iventis-api -p 8080:8080 vrrochaa/challenge-iventis-api
   ```
   Ou se preferir, na raiz do projeto
    ```sh
   docker-compose up
   ```

## Utilizando a API ⚡

### Swagger para acessar os endpoints
http://localhost:8080/swagger-ui/index.html#/

### A aplicação possui 4 endpoints:

#### /purchases
   ```
   Retorna uma lista das compras ordenadas de forma crescente por valor.
   ```
#### /purchases/largest-purchase/{year}
   ```
   Retorna a maior compra do ano informando os dados da compra disponibilizados.
   ```

#### /customers/loyal
   ```
   Retorna o Top 3 clientes mais fiéis, que possuem mais compras recorrentes com maiores valores.
   ```
#### /recommendation/{cpf}/type
   ```
   Retorna uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.
   ```

[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/
[Spring]: https://img.shields.io/badge/spring_boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/projects/spring-boot
[Gradle]: https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white
[Gradle-url]: https://gradle.org/
[Swagger]: https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white
[Swagger-url]: https://swagger.io/
