# Casa do Código

Uma aplicação web Java para uma livraria online, construída com Spring Framework.

## Stack Tecnológico

- **Java** 8
- **Spring Framework** 4.1.0.RELEASE
  - Spring WebMVC
  - Spring Security 4.0.4.RELEASE
  - Spring ORM
  - Spring Context Support
  - Spring OXM
- **Hibernate** 4.3.0.Final (JPA 2.1)
- **Banco de Dados**
  - MySQL 5.1.15
  - PostgreSQL 9.4
- **Servidor de Aplicação**: Apache Tomcat 7.0.30
- **Ferramenta de Build**: Maven
- **Tecnologia de View**: JSP com JSTL 1.2
- **Segurança**: Spring Security com taglibs
- **Logging**: SLF4J com Log4j
- **Armazenamento em Nuvem**: AWS SDK 1.9.11, S3Ninja

## Dependências Adicionais

- Jackson 2.5.1 (processamento JSON)
- XStream 1.4.8 (processamento XML)
- Hibernate Validator 4.1.0.Final (Bean Validation)
- JavaMail 1.4.7 (suporte a e-mail)
- Google Guava 18.0 (utilitários de cache)
- JUnit 4.12 (testes)

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/casadocodigo/loja/
│   │   ├── conf/          # Classes de configuração
│   │   ├── models/        # Modelos de domínio
│   │   ├── infra/         # Componentes de infraestrutura
│   │   ├── cache/         # Implementação de cache
│   │   └── integration/   # Integrações externas
│   └── resources/
│       └── log4j.xml      # Configuração de logging
└── test/                  # Classes de teste
```

## Construindo o Projeto

### Pré-requisitos

- JDK 8 ou superior
- Maven 3.x

### Comandos de Build

```bash
# Compilar o projeto
mvn compile

# Rodar testes
mvn test

# Empacotar a aplicação
mvn package

# Limpar e empacotar
mvn clean package
```

Isso irá gerar um arquivo WAR no diretório `target/`.

## Rodando a Aplicação

### Desenvolvimento Local

Faça deploy do arquivo WAR gerado em um servidor Tomcat:

```bash
# Copiar WAR para o diretório webapps do Tomcat
cp target/casadocodigo-0.0.1-SNAPSHOT.war $TOMCAT_HOME/webapps/

# Iniciar o Tomcat
$TOMCAT_HOME/bin/startup.sh
```

### Deploy em Produção

A aplicação inclui um Procfile para deploy em plataformas como Heroku:

```yaml
web: java $JAVA_OPTS -jar -Dspring.profiles.active=prod target/dependency/webapp-runner.jar --port $PORT target/*.war
```

## Configuração

### Configuração do Banco de Dados

A aplicação suporta bancos de dados MySQL e PostgreSQL. Configure sua conexão com o banco de dados nos arquivos de configuração do Spring localizados em `src/main/java/br/com/casadocodigo/loja/conf/`.

### Spring Security

A configuração de segurança é gerenciada através de:
- `SecurityConfiguration.java` - Configurações principais de segurança
- `SpringSecurityFilterConfiguration.java` - Cadeia de filtros de segurança

### Profiles

A aplicação suporta diferentes profiles para configuração específica de ambiente:
- `prod` - Profile de produção

Ative um profile usando:
```bash
-Dspring.profiles.active=prod
```

## Funcionalidades

- Catálogo de produtos
- Carrinho de compras
- Autenticação e autorização de usuários
- Integração com gateway de pagamento
- Upload de arquivos
- Notificações por e-mail
- Integração com AWS S3 para armazenamento de arquivos
- Suporte a cache

## Testes

Rode todos os testes com:

```bash
mvn test
```

## Licença

Este projeto é um software proprietário desenvolvido para a livraria Casa do Código.

## Observações
Futuramente irei migrar esse projeto para SpringBoot
