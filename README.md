# 🧩 SCFV – Backend para Gestão de Usuários e Grupos

SCFV (Serviço de Convivência e Fortalecimento de Vínculos) é um **serviço** mantido por órgãos governamentais, voltado ao atendimento de crianças, adolescentes e famílias em situação de vulnerabilidade. Este repositório contém o **backend** desenvolvido em **Spring Boot**, que expõe uma API RESTful para operações de CRUD, associação de entidades e futuras integrações com frontends e com sistemas externos.

---

## 🧠 Sobre o Projeto

O sistema original foi implementado em **Laravel** durante meu estágio, focado no gerenciamento de usuários e grupos do SCFV. Esta versão está sendo **reconstruída do zero com Spring Boot** como parte de um esforço de evolução técnica e profissional.

**Objetivos principais**  
- Aplicar padrões de arquitetura limpa e modular no backend.  
- Consolidar conhecimentos em Java 17, Spring Boot e JPA.  
- Evoluir a estrutura de dados com versionamento via Flyway.  
- Fornecer documentação automática da API (Swagger/OpenAPI).  


## ⚙️ Tecnologias e Ferramentas

- **Java 17**
- **Spring Boot 3.5.4**
  - Spring Web (REST)
  - Spring Data JPA (ORM)
  - Spring Validation
- **PostgreSQL**
- **Flyway** – versionamento de banco de dados
- **Lombok** – redução de boilerplate
- **Swagger/OpenAPI** – documentação da API
- **Maven** – gerenciamento de dependências
- **Docker**
- **Spring Security** (futuramente)

---

## 🧱 Arquitetura

- Projeto **monolítico**, estruturado por camadas:
  - `controller` – exposição dos endpoints REST
  - `service` – regras de negócio
  - `repository` – persistência de dados via Spring Data JPA
  - `model/entity` – entidades JPA mapeadas para o banco
  - `dto` – transferência de dados entre camadas
  - `config` – configurações do projeto

---

## 🔌 Funcionalidades da API

- CRUD de usuários
- CRUD de grupos
- Associação de usuários a grupo
- Filtros por id, idade e grupo
- Documentação automatizada com Swagger

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    ├── java/
    │   └── br.com.brunoedubems.scfv_backend/
    │       ├── controller/
    │       ├── service/
    │       ├── repository/
    │       ├── entity/
    │       ├── dto/
    │       └── exception/
    └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-prod.properties
        └── db/migration/ (Flyway)
```

---

## 🚀 Como Executar Localmente

1. **Clone o repositório**
   ```bash
   git clone https://github.com/brunoedubems/scfv-backend.git
   cd scfv-backend
   ```

2. **Configure o `application.properties`**
   ```properties
    spring.application.name=scfv-backend
    spring.profiles.active=dev
   ```

3. **Execute o projeto com Maven**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse a documentação Swagger**
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 📌 Endpoints Principais (Exemplo)

| Método | Rota                    | Descrição                            |
|--------|-------------------------|--------------------------------------|
| GET    | `/usuarios`             | Listar todos os usuários             |
| POST   | `/usuarios`             | Criar um novo usuário                |
| PUT    | `/usuarios/{id}`        | Atualizar dados de um usuário        |
| DELETE | `/usuarios/{id}`        | Remover um usuário                   |
| GET    | `/grupos`               | Listar todos os grupos               |
| POST   | `/grupos`               | Criar novo grupo                     |

> A lista completa estará disponível na documentação Swagger.

---

## 🧪 Testes

Planeja-se incluir testes unitários e de integração utilizando:

- **JUnit 5**
- **Mockito**
- **Testcontainers** (para testes com PostgreSQL real)

---

## 🎯 Objetivos Técnicos

- Aplicar boas práticas com Spring Boot em projetos reais.
- Consolidar domínio sobre REST APIs, JPA, validações e versionamento de schema.
- Manter uma base de código limpa, modular e de fácil manutenção.
- Facilitar futura evolução para microsserviços.

---


## ✍️ Autor

**Bruno Eduardo**  
