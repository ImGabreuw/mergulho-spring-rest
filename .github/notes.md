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

