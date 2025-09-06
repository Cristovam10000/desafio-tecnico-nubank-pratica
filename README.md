Desafio Técnico Nubank — Prática (Spring Boot)

API REST para cadastro de clientes e seus contatos, construída com Spring Boot, Spring Data JPA e PostgreSQL.

## Stack
- Java 24
- Spring Boot 3.5.5
- Spring Web, Spring Data JPA
- PostgreSQL
- Lombok
- Maven Wrapper (`mvnw`)

## Requisitos
- Java 24 instalado (`java -version`)
- PostgreSQL rodando localmente (padrão usado no projeto):
  - URL: `jdbc:postgresql://localhost:5432/cadastro`
  - Usuário: `postgres`
  - Senha: *****
- Maven (opcional — o projeto inclui `mvnw`)



## Configuração
Arquivo `src/main/resources/application.properties`:

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5432/cadastro
spring.datasource.username=postgres
spring.datasource.password=171209
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
```

Você também pode sobrescrever via variáveis de ambiente:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## Como rodar
- Windows: `mvnw.cmd spring-boot:run`
- Linux/macOS: `./mvnw spring-boot:run`

A aplicação sobe por padrão em `http://localhost:8080`.

## Endpoints
Base path das rotas: `http://localhost:8080`

- `POST /clientes`: cria um cliente (opcionalmente com contatos)
- `GET /clientes`: lista todos os clientes com seus contatos
- `GET /clientes/{id}/contatos`: lista os contatos de um cliente
- `POST /contatos`: cria um contato vinculado a um cliente existente

### Exemplos (curl)

Criar cliente (com contatos opcionais):
```
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "contatos": [
      {"telefone": "11999999999", "email": "joao@email.com"},
      {"telefone": "11888888888", "email": "jsilva@email.com"}
    ]
  }'
```

Listar clientes:
```
curl http://localhost:8080/clientes
```

Listar contatos de um cliente:
```
curl http://localhost:8080/clientes/1/contatos
```

Criar contato para um cliente existente:
```
curl -X POST http://localhost:8080/contatos \
  -H "Content-Type: application/json" \
  -d '{
    "telefone": "11912345678",
    "email": "contato@exemplo.com",
    "clienteId": 1
  }'
```

## Estrutura principal
```
src/main/java/spring/boot/desafio/nubankPratica
├── NubankPraticaApplication.java
├── controller
│   ├── ClienteController.java       # /clientes
│   └── ContatoController.java       # /contatos
├── dto
│   ├── ClientesDTO.java
│   ├── ClientesResponseDTO.java
│   ├── ContatoDTO.java
│   └── ContatoResponseDTO.java
├── model
│   ├── Clientes.java                # @OneToMany Contato
│   └── Contato.java                 # @ManyToOne Clientes
├── repository
│   ├── ClientesRepository.java
│   └── ContatoRepository.java
└── service
    └── ClientesService.java
```

## Notas importantes
- O mapeamento JSON usa `@JsonManagedReference` e `@JsonBackReference` para evitar ciclos.
- O endpoint `GET /clientes/{id}/contatos` retorna id, telefone e email dos contatos do cliente.
- Caso utilize outro banco/credencial, ajuste `application.properties` ou variáveis de ambiente.

## Melhorias futuras (sugestões)
- Validações com Bean Validation (ex.: `@NotBlank`, `@Email`).
- Tratamento de exceções com `@ControllerAdvice`/`@ExceptionHandler`.
- Documentação automática com SpringDoc OpenAPI/Swagger.
- Scripts de migração com Flyway.

---

Feito com ❤️ para estudo/prática do desafio.

