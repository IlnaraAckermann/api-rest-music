# API Kotlin com Spring 3 e PostgreSQL

Este é um projeto de API Kotlin que utiliza o framework Spring 3 para criar endpoints que recuperam dados de um JSON via URL, retornam a lista de álbuns existentes e as fotos de um álbum específico. O projeto inclui testes para as classes de serviços e controladores.



##  Endpoints:

| Método   | URL                          | Descrição                                                                         |
|----------|------------------------------|-----------------------------------------------------------------------------------|
| **POST** | `/api/music/url`             | Recebe uma URL com dados JSON de músicas e salva as músicas no banco de dados.    |
| **POST** | `/api/music/list`            | Recebe uma lista de músicas no formato JSON e salva as músicas no banco de dados. |
| **GET**  | `/api/music/albuns`          | Lista todos os álbuns disponíveis.                                                |
| **GET**  | `/api/music/album/{albumId}` | Busca todas as fotos disponíveis de um álbum específico com base no ID do álbum.  |



### Requisitos
* Certifique-se de ter o Docker instalado em sua máquina para executar este projeto.
* Tenha o comando docker-compose disponível (geralmente incluído com a instalação do Docker).

### Como usar
### 1. Clonando o repositório
   Clone este repositório em sua máquina local:

```
git clone https://github.com/IlnaraAckermann/api-rest-music.git
cd seu-repositorio
```

### 2. Configurando o ambiente
   Certifique-se de ter um arquivo .env na raiz do projeto com as seguintes variáveis:

```
DB_URL=jdbc:postgresql://db:5432/postgres
PG_USER=seu_usuario_postgres
PG_PASSWORD=sua_senha_postgres
```

### 3. Executando a aplicação

Use o Docker Compose para iniciar a aplicação e o banco de dados: 

Construa a Imagem Kotlinapp (se necessário):
    
```
    docker-compose build
```
Inicie os Contêineres:
```
docker-compose up -d
```
Isso criará e executará os contêineres necessários para a aplicação e o PostgreSQL.

Para verificar se seus containers estão listado como "up" basta usar o comando ``docker ps``

### 4. Acessando a API
   A API estará disponível em http://localhost:8080.


### 5. Executando testes
Para executar os testes das classes de serviço e controlador:

```
# Acessar o contêiner kotlinapp
docker exec -it kotlinapp bash
```

 Dentro do contêiner, execute os testes
```
./gradlew test
```
Isso executará todos os testes presentes no projeto.

### 6. Encerrando a aplicação
   Para encerrar a execução da aplicação e dos contêineres Docker, execute:

````
docker-compose down
````