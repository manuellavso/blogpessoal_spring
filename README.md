# Projeto Blog Pessoal - Backend com Spring Boot



[![source: imgur.com](https://camo.githubusercontent.com/2c0346f0cc411c1785e05ee360102dc3cda9c7e75149e74b96ed16b769f5f766/68747470733a2f2f692e696d6775722e636f6d2f773874544f75542e706e67)](https://camo.githubusercontent.com/2c0346f0cc411c1785e05ee360102dc3cda9c7e75149e74b96ed16b769f5f766/68747470733a2f2f692e696d6775722e636f6d2f773874544f75542e706e67)

[![Top Language](https://img.shields.io/github/languages/top/manuellavso/blogpessoal_spring?style=flat-square)](https://github.com/manuellavso/blogpessoal_spring)![Repo Size](https://img.shields.io/github/repo-size/manuellavso/blogpessoal_spring?style=flat-square)[![Language Count](https://img.shields.io/github/languages/count/manuellavso/blogpessoal_spring?style=flat-square)](https://github.com/manuellavso/blogpessoal_spring)[![Last Commit](https://img.shields.io/github/last-commit/manuellavso/blogpessoal_spring?style=flat-square)](https://github.com/manuellavso/blogpessoal_spring)[![Issues](https://img.shields.io/github/issues/manuellavso/blogpessoal_spring?style=flat-square)](https://github.com/manuellavso/blogpessoal_spring/issues)[![Pull Requests](https://img.shields.io/github/issues-pr/manuellavso/blogpessoal_spring?style=flat-square)](https://github.com/manuellavso/blogpessoal_spring/pulls)[![Status](https://img.shields.io/badge/status-em%20construção-yellow?style=flat-square)](https://github.com/manuellavso/blogpessoal_spring)

![Java](https://img.shields.io/badge/Java-21-red?style=flat-square&logo=openjdk)![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=springboot)![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven)![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?style=flat-square)![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

## 1. Descrição



O **Blog Pessoal** é uma aplicação que permite que usuários publiquem, editem e visualizem postagens relacionadas a temas variados, de forma organizada e segura. Este projeto foi desenvolvido com fins educacionais, simulando uma aplicação real de blog para praticar conceitos de API REST com Java e Spring Boot.

Entre os principais recursos que um blog pessoal oferece, destacam-se:

1. Criação, edição e exclusão de postagens
2. Associação de postagens a temas específicos
3. Cadastro e autenticação de usuários
4. Visualização de postagens por tema ou usuário
5. Controle de acesso a operações sensíveis

## 2. Sobre esta API



A API do Blog Pessoal foi desenvolvida utilizando **Java** e o **framework Spring**, seguindo os princípios da Arquitetura MVC e REST. Ela oferece endpoints para o gerenciamento dos recursos **Usuário**, **Postagem** e **Tema**, permitindo a interação entre os usuários e os conteúdos publicados.

### 2.1. Principais funcionalidades da API:

1. Consulta, cadastro, login e atualização dos dados de usuários
2. Consulta, criação e gerenciamento de temas para classificar postagens
3. Criação, edição, listagem e remoção de postagens
4. Associação de postagens a temas e autores
5. Autenticação via token JWT para segurança nas requisições

## 3. Diagrama de Classes



O **Diagrama de Classes** é um modelo visual usado na programação orientada a objetos para representar a estrutura de um sistema. Ele exibe classes, atributos, métodos e os relacionamentos entre elas, como associações, heranças e dependências.

Esse diagrama ajuda a planejar e entender a arquitetura do sistema, mostrando como as entidades interagem e se conectam. É amplamente utilizado nas fases de design e documentação de projetos.

<p align="center">   <img src="https://i.imgur.com/Il7ha6o.png" alt="Diagrama de Classes" width="300"/> </p>



## 4. Diagrama Entidade-Relacionamento (DER)

O **DER (Diagrama Entidade-Relacionamento)** do projeto **Blog Pessoal** representa de forma visual como os dados estão organizados no banco de dados relacional e como as entidades se relacionam entre si.

<p align="center">   <img src="https://i.imgur.com/ibHrCgC.png" alt="Diagrama do Banco de Dados" width="500"/> </p>

## 5. Tecnologias utilizadas



| Item                          | Descrição       |
| ----------------------------- | --------------- |
| **Servidor**                  | Tomcat          |
| **Linguagem de programação**  | Java            |
| **Framework**                 | Spring Boot     |
| **ORM**                       | JPA + Hibernate |
| **Banco de dados Relacional** | MySQL           |
| **Segurança**                 | Spring Security |
| **Autenticação**              | JWT             |
| **Testes automatizados**      | JUnit           |
| **Documentação**              | SpringDoc       |

## 6. Requisitos



Para executar os códigos localmente, você precisará:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Banco de dados [MySQL](https://dev.mysql.com/downloads/)
- [STS](https://spring.io/tools)
- [Insomnia](https://insomnia.rest/download) ou [Postman](https://www.postman.com/)

## 7. Como Executar o projeto no STS



### 7.1. Importando o Projeto



1. Clone o repositório do Projeto [Blog Pessoal](https://github.com/manuellavso/blogpessoal_spring) dentro da pasta do *Workspace* do STS

```
git clone https://github.com/manuellavso/blogpessoal_spring.git
```



1. **Abra o STS** e selecione a pasta do *Workspace* onde você clonou o repositório do projeto
2. No menu superior do STS, clique na opção: **File 🡲 Import...**
3. Na janela **Import**, selecione a opção: **General 🡲 Existing Projects into Workspace** e clique no botão **Next**
4. Na janela **Import Projects**, no item **Select root directory**, clique no botão **Browse...** e selecione a pasta do Workspace onde você clonou o repositório do projeto
5. O STS reconhecerá o projeto automaticamente
6. Marque o Projeto Blog Pessoal no item **Projects** e clique no botão **Finish** para concluir a importação

### 7.2. Executando o projeto



1. Na Guia **Boot Dashboard**, localize o **Projeto Blog Pessoal**
2. Selecione o **Projeto Blog Pessoal**
3. Clique no botão **Start or Restart** [![source: imgur.com](https://camo.githubusercontent.com/55fb030baeae807a71d2234022da28a827faa55c1fd8c9a9e615e309c255a080/68747470733a2f2f692e696d6775722e636f6d2f77646f5a7157502e706e67)](https://camo.githubusercontent.com/55fb030baeae807a71d2234022da28a827faa55c1fd8c9a9e615e309c255a080/68747470733a2f2f692e696d6775722e636f6d2f77646f5a7157502e706e67) para iniciar a aplicação
4. Caso seja perguntado se você deseja autorizar o acesso ao projeto via rede, clique no botão **Permitir Acesso**
5. Acompanhe a inicialização do projeto no console do STS
6. Verifique se o banco de dados `db_blogpessoal` foi criado corretamente e se as tabelas foram geradas automaticamente.
7. Utilize o [Insomnia](https://insomnia.rest/) ou o [Postman](https://www.postman.com/) para testar os endpoints.

Tip

Ao acessar a URL `http://localhost:8080` em seu navegador, a interface do Swagger será carregada automaticamente, permitindo a visualização e a interação com os endpoints da API, bem como a consulta dos modelos de dados utilizados.

## 8. Como Executar os Testes no STS



### 8.1. **Localizando as Classes de Teste**



- Na **Package Explorer**, navegue até a Source Folder `src/test/java`
- Localize as classes que contém os testes (classes cujo nome terminam com a palavra **Test**)

### 8.2. **Executando os Testes**



Você pode executar os testes de duas formas:

#### 👉 Opção 1: Executar uma classe de teste específica



- Clique com o botão direito sobre a classe de teste
- Selecione a opção `Run As > JUnit Test`

#### 👉 Opção 2: Executar todos os testes do projeto



- Clique com o botão direito sobre a pasta do projeto
- Selecione: `Run As > JUnit test`

### 8.3. **Verificando os Resultados**



- Ao executar os testes, na **Package Explorer**, será exibida a guia **JUnit** mostrando os resultados dos testes
- Os testes que falharem serão destacados em vermelho, e os bem-sucedidos em verde
- Clique nos testes para visualizar os detalhes ou mensagens de erro no item **Failure Trace**

## 9. Contribuição



Este repositório é parte de um projeto educacional, mas contribuições são sempre bem-vindas! Caso tenha sugestões, correções ou melhorias, fique à vontade para:

- Criar uma **issue**
- Enviar um **pull request**
- Compartilhar com colegas que estejam aprendendo Java!

## 10. Contato



Desenvolvido por [**Manuella Oliveira**](https://github.com/manuellavso) Para dúvidas, sugestões ou colaborações, entre em contato via GitHub ou abra uma issue!

