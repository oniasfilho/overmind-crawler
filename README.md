# 1) Aplicação Proposta: 

## Teste: Webcrawler

A prova consiste em construir um crawler em Java, utilizando qualquer tecnologia nativa ou biblioteca aberta, que visite
o site do [IMDB]( https://www.imdb.com) (The Internet Movie Database) e procure
pelos [piores filmes da história]( https://www.imdb.com/chart/bottom). O resultado da execução do io.oniasfilho.overmindcrawlerapi.crawler deve ser:

- A Lista dos 10 filmes com pior nota no site. A lista pode ser exibida onde preferir (console, arquivo externo, página
  web), em ordem decrescente de melhor para pior nota, e deve conter as seguintes informações estruturadas:
- Nome do Filme (deve estar em inglês)
- Nota (com uma casa decimal)
- Diretor(es)
- Elenco principal
- Ao menos um comentário POSITIVO sobre o filme (comentário que deu uma nota >= 5)

Serão avaliados a completude dos requisitos, organização do código (independente da tecnologia utilizada), limpeza e
facilidade de entendimento.

Nossa sugestão é a entrega da resolução até 12/08, para este mesmo e-mail.

Mãos à obra!


# 2) Solução Desenvolvida: 


#### Informacoes para acesso da aplicacao em nuvem:

- URL: https://overmind-crawler.herokuapp.com/api/IMDB


Tecnologias/Bibliotecas Utilizadas:

  - Spring Framework
  - Jsoup


### Checklist de Requisitos

- [x] 1) A Lista dos 10 filmes com pior nota no site. A lista pode ser exibida onde preferir (console, arquivo externo, página
  web), em ordem decrescente de melhor para pior nota
- [x] 2) Nome do Filme (deve estar em inglês)
- [x] 3) Nota (com uma casa decimal)
- [x] 4) Diretor(es)
- [x] 5) Elenco principal
- [x] 6) Ao menos um comentário POSITIVO sobre o filme (comentário que deu uma nota >= 5)


### Instalacao

Esse projeto precisa de [Maven](https://maven.apache.org/download.cgi) e [Java](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) instalados em sua máquina para funcionar.
  
Caso não seja possível instalar as ferramentas previamente informadas, [um deploy foi feito no heroku](https://overmind-crawler.herokuapp.com/api/IMDB) onde é possível obter a resposta do web scraper pelo próprio navegador. [Json Formatter](https://chrome.google.com/webstore/detail/json-formatter/bcjindcccaagfpapjjmafapmmgkkhgoa?hl=pt-BR) é recomendado para visualizar via navegador porém não é necessário.
  
Por se tratar de um deploy feito gratuitamente, é possível que possa demorar alguns segundos para obter uma resposta. O resultado é obtido no seguinte formato:
  
  ![image](https://user-images.githubusercontent.com/19842185/128972142-62b14ea6-81ef-4e66-8f3a-2ddb08372e2c.png)



Setup da API:
Na pasta principal do projeto, executar:

```sh
$ mvn clean package install & mvn clean spring-boot:run
```
Após a execução, a API estará disponível em http://localhost/8080 onde haverá dois endpoinds, um de [teste](http://localhost/8080/ping) e o [principal](http://localhost:8080/api/IMDB).

