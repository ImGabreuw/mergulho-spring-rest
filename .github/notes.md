# TODO

- [ ] padrão DTO no Cliente
- [ ] cancelamento de uma Entrega 


# Mergulho Spring REST

### O que é REST?

* Acrónimo para REpresentational State Transfer 
* Estilo arquitetural para o desenvolvimento de WEB APIs
* Não é um Framework / Biblioteca, mas sim uma especificação que defina a forma de comunicação entre componentes de software na Web

### Por que REST?

* Reparação entre cliente (consumidor) e servidor (provedor)
* Escalabilidade 
* Independência de linguagem 
* Mercado (Adotado em quase todos os sistemas no mercado)

### Constrains

* Cliente-servidor
* Stateless = aplicação não dever ter estado, ou seja, na requisição feita à API deve conter todos os dados necessários para que seja devidamente processada.
* Cache = API pode "guardar" as respostas das requisições -> + performance / escalabilidade
* Interface uniforme = desacoplamento estrutural da aplicação -> "cada parte" possa evoluir de forma independênte
* Sistema em camadas
* Código sob demanda (Exemplo: servidor fornece um código JS para montar um gráfico para o consumidor da API)

### Recursos REST

* Qualquer coisa exporta na WEB (documento / vídeo / imagem / etc)
* _Singleton Resource_ = um único produto é um recurso
* _Collection Resource_ = coleção de produtos (0 ou + produtos) é um recurso

* Como identificar um recurso?
  * URI (_Uniform Resource Identifier_) = conjunto de caractéres afim de dar um *endereço único* para esse recurso

* URI vs URL
  * URL (_Uniform Resource Locator_) = um tipo de URI que funciona como um localizador (onde está disponível) e identificador (mecanismo para chegar até esse recurso)
  * Exemplo: `https://api.algamarket.com.br/clientes`
    * `https://` = identificador
    * `api.algamarket.com.br/clientes` = localizador



## Protocolo HTTP (mais usado)

* requisição-resposta (relação cliente-servidor)

* composição da requisição
  * estrutura base
    ```
    [método] [URI] HTTP/[versão] 
    [cabeçalhos]

    [corpo / payload]
    ```

    > método = verbo HTTP (GET / POST / PUT / DELETE / etc)

  * exemplo
    ```
    POST /produtos HTTP/1.1
    Content-Type: application/json
    Accept: application/json

    {
      "nome": "Notebook i7",
      "preco": 2100.0
    }
    ```

* composição da resposta
  * estrutura base
    ```
    HTTP/[versão] [status] 
    [cabeçalhos]

    [corpo]
    ```

  * exemplo
    ```
    HTTP/1.1 201 Created
    Location: /produtos/331
    Content-Type: application/json

    {
      "codigo": 331,
      "nome": "Notebook i7",
      "preco": 2100.0
    }
    ```

### Método/verbo HTTP

* método GET = verbo **idempotente**, ou seja, requisições repetidas em sequência não geram efeitos colaterais (não serve para modificar um recurso)

### Códigos de Status HTTP

* Documentação: [clique aqui](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml)

* Outros sites
  * [MDN](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
  * [httpstatuses](https://httpstatuses.com/)

### Cabeçalhos da requisição

> chave-valor

* `Accept` = recebe um MediaType (Exemplo: `Accept: application/json`)
  * Para que a API responda um requisição no formato XML, ou em outro formato (Content Negotiation), (por padrão é no formato JSON), é necessário adicionar uma dependência, no lado do servidor, mais especificamente no `pom.xml`:

    ```xml
    <dependencies>
      <dependency>
        <groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-xml</artifactId>
      </dependency>
    </dependencies>
    ```

## Spring

### O que é Spring?

> Spring não é nem um framework e nem uma biblioteca, mas sim um ecossistema.

* ecossistema de projetos afim de resolver vários problemas que um desenvolver passa de uma forma simples.

### Por que Spring?

* "Canivete suíço" para desenvolvedores Java, ou seja, resolve vários problemas de desenvolvimento de softwares
* Simplicidade
* Maturidade (muitas empresas grandes utilizam Spring)
* Modularidade = escolha dos módulos necessário para a aplicação
* Evolução constante
* Open source (contribuição da comunidade para sua evolução)
* Comunidade grande e forte (DOCS, eventos, fóruns, etc)
* Popularidade
* Empregabilidade

## fat JAR

### Como gerar o fat JAR? (Intellij IDEA)

* maven > nome do projeto > lifecycle > clean
* maven > nome do projeto > lifecycle > package

### Como rodar o fat JAR? (Terminal)

> Abra um terminal na pasta do projeto

* `cd target/`
* `java -jar nome-do-projeto.jar`
  > OBS: o fat JAR é o arquivo com o nome do projeto e sem a extensão `.jar.original`

## Banco de dados (MySQL)

### Configuração do bando de dados 

* URL: [documentação](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html) 

  ```
  spring.datasource.url=jdbc:mysql://localhost/nome_do_banco_de_dados
  ```

  > Se o IP for `localhost`, a porta pode ser omitida pois a porta padrão do MySQL é 3306

  * Algumas propriedades na URL
    * `createDatabaseIfNotExist=true` = o schema será criado caso ele não existir
    * `serverTimezone=UTC` = data e hora serão salvos na fuso horário GMT 0 (boa prática)

### Flyway

* Documentação: [clique aqui](https://flywaydb.org/documentation/)

* Vantagem: Facilita o gerenciamento das schemas da aplicação

* Criação de uma _migration_

  1. Nome do arquivo **precisa** começar com a letra `V`.
  
  2. Em seguida vem o versão da _migration_ e pode ser escrito das seguinte maneiras: 

      * `V001` = numeração em ordem crescente
      * `V20210519` = data invertida
      * `V202105191449` = data invertida + horário

  3. Depois colocar **2 underscore** (`__`).

  4. Por fim adicionar um nome descritivo e a extensão `.sql` ao arquivo

  5. Resultado:

      `V001__cria-tabela-cliente.sql`      
      ```sql
      create table cliente (
        id bigint not null auto_increment,
        nome varchar(60) not null,
        email varchar(255) not null,
        telefone varchar(20) not null,
        
        primary key (id)
      )
      ```

  > OBS: após o registro da _migration_ em uma schema chamado `flyway_schema_history` (histórico de todas as _migration_ da aplicação) o arquivo registrado não pode ser alterado. Caso for queira alterá-la, basta deletar essa _migration_ ou criar uma nova.

### Jakarta Persistence

* Documentação: [clique aqui](https://jakarta.ee/specifications/persistence/3.0/)

* É uma especificação que fornece uma API de objeto relacional
* Antigamente era chamado de JPA (Java Persistence API)
* A implementação do Jakarta Persistence mais conhecida é o Hibernate

* Mapeamento objeto relacional

  * Mapeia um objeto Java para uma coluna do banco da dados

  ```java
  @Entity // Responsável pelo mapeamento do objeto (Por padrão, o nome da tabela será o nome da classe)
  public class Cliente {

      @Id // Definir a chave primária da entidade
      @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração do Id (GenerationType.IDENTITY = define a forma de geração padrão do banco de dados, no caso no MySQL é auto_increment)
      private Long id;

      private String nome;
      private String email;
      @Column(name = "fone") // Indicar o nome da coluna. Isso só é necessário quando o nome do atributo e da coluna forem diferentes
      private String telefone;

      // getters e setters
  }
  ```

  * Mapeamento da **chave estrangeira** com `@ManyToOne`

  ```java
  @Entity
  public class Entrega {

      @Id
      @GeneratedValue(strategy = IDENTITY)
      private Long id;

      private BigDecimal taxa;
      private LocalDateTime dataPedido;
      private LocalDateTime dataFinalizacao;

      @ManyToOne // muitas entregas tem 1 cliente
      private Cliente cliente;

      private Destinatario destinatario;

      private StatusEntrega status;

      // getters e setters
  }
  ```

  * Abstração dos dados do Destinatário para um outra classe (POO)

  ```java
  @Entity
  public class Entrega {

      @Id
      @GeneratedValue(strategy = IDENTITY)
      private Long id;

      private BigDecimal taxa;
      private LocalDateTime dataPedido;
      private LocalDateTime dataFinalizacao;

      @ManyToOne // muitas entregas tem 1 cliente
      private Cliente cliente;

      @Embedded // mapeamento dos dados do destinatário para a mesma tabela (Entrega)
      private Destinatario destinatario;
      
      @Enumerated(EnumType.STRING) // normalização do valor da enum (Nome do status)
      private StatusEntrega status;

      // getters e setters
  }
  ```

  * Classe embeddable
  ```java
  @Embeddable // torna a classe passível de ser ou não embutida em uma tabela
  public class Destinatario {

    private String name;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

    // getters e setters
  }
  ```

  * Propriedades Json
  ```java
  @Entity
  public class Entrega {

      @Id
      @GeneratedValue(strategy = IDENTITY)
      private Long id;

      private BigDecimal taxa;

      @JsonProperty(access = READ_ONLY) // impedir o consumidor da API de especificar dados que é fornecido automaticamente pela API 
      private LocalDateTime dataPedido;

      @JsonProperty(access = READ_ONLY)
      private LocalDateTime dataFinalizacao;

      @ManyToOne
      private Cliente cliente;

      @Embedded
      private Destinatario destinatario;
      
      @JsonProperty(access = READ_ONLY)
      @Enumerated(EnumType.STRING)
      private StatusEntrega status;

      // getters e setters
  }
  ```

* `@Transient`
  ```java
  @Entity
  public class Entrega {

      @Id
      @GeneratedValue(strategy = IDENTITY)
      private Long id;

      private BigDecimal taxa;

      @JsonProperty(access = READ_ONLY) // impedir o consumidor da API de especificar dados que é fornecido automaticamente pela API 
      private LocalDateTime dataPedido;

      @JsonProperty(access = READ_ONLY)
      private LocalDateTime dataFinalizacao;

      @ManyToOne
      private Cliente cliente;

      @Embedded
      private Destinatario destinatario;
      
      @JsonProperty(access = READ_ONLY)
      @Enumerated(EnumType.STRING)
      private StatusEntrega status;

      @Transient //  o atributo não será mapeada para uma coluna da tabela **Entrega**
      private String senha;

      // getters e setters
  }
  ```

* `@OneToMany(mappedBy = "")`

  ```java
  @Entity
  public class Entrega {

      @Id
      @GeneratedValue(strategy = IDENTITY)
      private Long id;

      private BigDecimal taxa;
      private OffsetDateTime dataPedido;
      private OffsetDateTime dataFinalizacao;

      @ManyToOne
      private Cliente cliente;

      @Embedded
      private Destinatario destinatario;

      @OneToMany(mappedBy = "entrega") // nome da propriedade dona do relacionamento do lado inverso ("entrega")
      private List<Ocorrencia> ocorrencias = new ArrayList<>();

      @Enumerated(EnumType.STRING)
      private StatusEntrega status;

  }
  ```

## Bean Validation

* Documentação: [clique aqui](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html)

* Validação de dados no Spring

* Validação

  * `@NotBlank` = verificação se o atributo recebeu um valor `null` ou vazio (`""`)

  * `@Size`
    * `@Size(max = 60)` = limite para no máximo 60 caracteres

  * `@Email` = validação da sintaxe básica de um email (`@` e `.`)

* Validação em cascata e Validation Groups

  * Validation Groups são interfaces de marcações   
    ```java
    public interface ValidationGroups {

      interface ClienteId {}

    }
    ```

  * Conversão do Validation Group `Default.class` para o `ValidationGroups.ClienteId.class`
    ```java
    @Entity
    public class Entrega {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @NotNull
        private BigDecimal taxa;

        @JsonProperty(access = READ_ONLY)
        private LocalDateTime dataPedido;

        @JsonProperty(access = READ_ONLY)
        private LocalDateTime dataFinalizacao;

        @Valid // Validação dos atributos do Cliente baseado nas constrains desse objeto
        @ConvertGroup(to = ValidationGroups.ClienteId.class) // conversão do ValidationGroup Default (por padrão) para uma customizada
        @NotNull
        @ManyToOne
        private Cliente cliente;

        @Embedded
        private Destinatario destinatario;

        @JsonProperty(access = READ_ONLY)
        @Enumerated(EnumType.STRING)
        private StatusEntrega status;

    }
    ```

* OBS: Validações no modelo de domínio e no modelo de representação?

  * Se todas as operações do sistema forem feitas **somente** através da API, então a validação no model de domínio se torna desnecessária. 

  * Caso contrário, é importante manter essas validações, mesmo se forem duplicadas.

## Data e Hora

* O padrão de data e hora no Spring é ISO 8601

  > ISO 8601 é um padrão internacional para representar data e hora publicada pela ISO (International Organization for Standardization)

* Representação de data e/ou hora em ISO 8601 offset (offset = diferença de fuso horário local e UTC (GMT 0))

  * Formato: `ano-mês-diaThora-minuto-segundo`
    > **T** = separação entre data e hora

  * Exemplo: `2021-04-30T20:00:00`

## ModelMapper

* Documentação: [clique aqui](http://modelmapper.org/)

* Conversão de objetos do modelo de domínio para o modelo de representação (DTO)

* Configuração do ModelMapper
  ```java
  @Configuration
  public class ModelMapperConfig {

      @Bean // método que instancia um objeto e que será gerenciado pelo String na injeção de dependência
      public ModelMapper modelMapper() {
          return new ModelMapper();
      }

  }
  ```

## Sub recursos

* Exemplo de sub recurso de um recurso único

  * URI = `http://localhost:8080/entregas/1/ocorrencias`

  * Corpo da requisição

    ```json
    {
      "descricao": "Tentativa sem sucesso (não estava em casa)"
    }
    ```

    > EntregaId não é necessário uma vez que está presente na URI do recurso

  * Código