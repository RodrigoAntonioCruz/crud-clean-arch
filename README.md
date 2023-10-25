<h3 align="center">
  
  CRUD CLEAN ARCHITECTURE


[//]: # (  <img align="center" alt="Logo"  height="320" src="https://helpdev.com.br/wp-content/uploads/2020/05/Screenshot-from-2020-05-20-23-29-10.png" /><br>)
</h3>

[//]: # ([![Build & test]&#40;https://github.com/RodrigoAntonioCruz/wishlist/actions/workflows/build.yml/badge.svg&#41;]&#40;https://github.com/RodrigoAntonioCruz/wishlist/actions/workflows/build.yml&#41; [![codecov]&#40;https://codecov.io/gh/RodrigoAntonioCruz/wishlist/branch/main/graph/badge.svg?token=MZC7NC3OZX&#41;]&#40;https://codecov.io/gh/RodrigoAntonioCruz/wishlist&#41;)

### Contexto

Uma `API REST` implementando um `CRUD` básico, seguindo o padrão Clean Architecture.

A expressão `Clean Architecture` é um padrão de desenvolvimento de software que busca separar as responsabilidades e dependências entre as diferentes camadas de uma aplicação. Ele promove a modularidade, a testabilidade e a manutenibilidade do código.

Nessa `API`, foram aplicados os princípios da Clean Architecture para estruturar o projeto de forma organizada e escalável. A arquitetura é dividida em camadas, como a camada de `entrypoints` (responsável por receber as requisições HTTP e retornar as respostas), a camada de `dataproviders` (responsável pela interação com o banco de dados ou outras fontes de dados externas) e a camada `core` (onde são definidas os casos de uso e as regras de negócio).

O objetivo principal dessa API é oferecer operações básicas de criação, leitura, atualização e exclusão de recursos. Ela segue as práticas e convenções da arquitetura REST, permitindo que os clientes se comuniquem com a API através de requisições `HTTP`, como `GET`, `POST`, `PUT` e `DELETE`.

Essa abordagem de desenvolvimento visa criar um código limpo, modular e de fácil manutenção, além de facilitar a integração com outras tecnologias e a evolução da aplicação ao longo do tempo.

<h4>Tecnologias</h4>
<ul>
  <li> Java 17
  <li> Spring Boot 
  <li> Maven
  <li> Lombok
  <li> Docker
  <li> MongoDB
  <li> Swagger
</ul>

### Requisitos

Para rodar esta aplicação, você deve ter instalado em seu computador:

<ul>  
   <li><a href="https://www.oracle.com/java/technologies/javase/jdk17-readme-downloads.html" target="_blank">Java 17</a>

   <li><a href="https://maven.apache.org/download.cgi" target="_blank">Maven 3.9.5</a>

   <li><a href="https://docs.docker.com/get-started/" target="_blank">Docker</a>
</ul>

### Executando o projeto

1. Clone ou baixe o projeto do repositório para o seu `Computador`.

2. Navegue até a raíz do diretório do projeto, abra o `terminal e execute o comando:`
<ul>
   <li> sudo mvn clean package
</ul>

   <img align="center" src="https://raw.githubusercontent.com/RodrigoAntonioCruz/assets/main/mvn-clean-pkg.png" />

<ul>
   <li> sudo docker-compose up -d
</ul>

<img align="center" src="https://raw.githubusercontent.com/RodrigoAntonioCruz/assets/main/docker-compose.png" />


3. Após a execução dos processos anteriores, estarão disponíveis para acesso em seu browser os seguintes `endpoints` para teste:

<a href="http://localhost:8887/v1/api/swagger-ui/index.html" target="_blank" title="Clique e navegue!">
<img align="center" src="https://raw.githubusercontent.com/RodrigoAntonioCruz/assets/main/users-api.png" /></a>

