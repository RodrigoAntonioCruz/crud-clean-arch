<h3 align="center">
  
  CRUD CLEAN ARCHITECTURE


[//]: # (  <img align="center" alt="Logo"  height="320" src="https://helpdev.com.br/wp-content/uploads/2020/05/Screenshot-from-2020-05-20-23-29-10.png" /><br>)
</h3>

[//]: # ([![Build & test]&#40;https://github.com/RodrigoAntonioCruz/wishlist/actions/workflows/build.yml/badge.svg&#41;]&#40;https://github.com/RodrigoAntonioCruz/wishlist/actions/workflows/build.yml&#41; [![codecov]&#40;https://codecov.io/gh/RodrigoAntonioCruz/wishlist/branch/main/graph/badge.svg?token=MZC7NC3OZX&#41;]&#40;https://codecov.io/gh/RodrigoAntonioCruz/wishlist&#41;)

### Contexto

Uma `API REST` implementando um `CRUD` básico, seguindo o padrão Clean Architecture.

## Estrutura do Projeto

O projeto está dividido em diferentes módulos e pacotes, seguindo os princípios da arquitetura limpa. Aqui está uma visão geral da estrutura do projeto:

### Módulo "Core"

- **`core/domain`**: Este pacote contém as classes de domínio do projeto. São classes que representam as entidades e objetos de negócios do sistema.

- **`core/use-case`**: Neste pacote, você encontrará os casos de uso ou interações principais da aplicação. Os casos de uso definem as regras de negócios e a lógica da aplicação.

### Módulo "Adapter"

- **`adapter/input`**: Os adaptadores de entrada são responsáveis por receber as solicitações do mundo exterior. Isso pode incluir controladores REST, classes de serialização, entre outros.

- **`adapter/output`**: Os adaptadores de saída são responsáveis por fornecer saídas para o mundo exterior. Isso pode incluir repositórios de dados, serviços externos, armazenamento em banco de dados, entre outros.

### Módulo "App"

- **`app/spring-app`**: Este é o módulo de aplicação da sua arquitetura. Aqui, você pode encontrar a configuração e componentes específicos do Spring Boot, como a classe principal de inicialização.

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

