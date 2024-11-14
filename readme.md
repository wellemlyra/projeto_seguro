# Projeto de Cadastro e Simulação de Seguros

<p>Este projeto é um sistema de cadastro de clientes e simulação/contratação de seguros, construído com foco em boas práticas e padrões modernos de desenvolvimento. Ele foi desenvolvido utilizando Java 17 e princípios de Clean Architecture e arquitetura Hexagonal para garantir flexibilidade, testabilidade e facilidade de manutenção.</p>

### Descrição do Desafio

<p>O objetivo foi desenvolver uma solução que atendesse à jornada de cadastro de clientes e contratação de seguros em um cenário de atendimento em lojas. Para contratar um seguro, o cliente precisa ser cadastrado no sistema com dados como CPF, nome, data de nascimento, telefone e endereço completo. Os tipos de seguros disponíveis são: Bronze, Prata e Ouro.</p>

### Tecnologias e Práticas Utilizadas

O projeto utiliza tecnologias modernas e segue boas práticas de código, incluindo:

- Java 17 e Maven 3.9.9 para construção do projeto.
- Swagger 3 para documentação interativa das APIs.
- PostgreSQL como banco de dados.
- JUnit 5 e Mockito para testes unitários.
- Clean Architecture e Arquitetura Hexagonal: Essas arquiteturas foram escolhidas para isolar as regras de negócio das implementações técnicas e permitir que o sistema seja facilmente expansível e testável.

## Arquitetura do Projeto
O projeto foi dividido em duas APIs independentes, seguindo o conceito de arquitetura hexagonal:

- API de Cadastro: Responsável pelo CRUD (criação, leitura, atualização e exclusão) de clientes.
- API de Seguros: Permite simular e contratar seguros. Ela integra com a API de Cadastro para validar os clientes antes de permitir a contratação de um seguro.


### Como Executar o Projeto

#### Pré-requisitos

- Docker e Docker Compose: Este projeto utiliza Docker para facilitar a execução. Verifique se você tem Docker e Docker Compose instalados em sua máquina.
- Clone o repositório e navegue até o diretório do projeto.


### Passo a Passo

1. Inicie os Serviços: Execute o comando abaixo para construir as imagens Docker e iniciar o projeto:
```
docker compose up -d --build
```
1.1. Fique a vontade para rodar em sua maquina atraves de suas IDEs. (no final da doc destaco as ferramentas para rodar, caso nao funcione em seu docker.)


### Acesse o CRUD das APIs, apos rodar as aplicacoes

- [API de Cadastro (Clientes)](http://localhost:8080/swagger-ui/index.html)
- [API de Seguros](http://localhost:8081/swagger-ui/index.html)


### Estrutura das APIs
## 1. API de Cadastro (http://localhost:8080/api/clientes)
Endpoints para gerenciar o cadastro de clientes:

- Criar Cliente: POST /api/clientes
- Listar Clientes: GET /api/clientes
- Consultar Cliente por CPF: GET /api/clientes/{cpf}
- Atualizar Cliente: PUT /api/clientes/{cpf}
- Excluir Cliente: DELETE /api/clientes/{cpf}

## 2. API de Seguros (http://localhost:8081/api/seguros)

Endpoints para simulação e contratação de seguros:

- Simular Seguro: GET /api/seguros/simular/{cpf}?tipo={tipo}
- Contratar Seguro: POST /api/seguros/contratar/{cpf}?tipo={tipo}

## Exemplos de Requisições
Abaixo, fornecemos uma collection do Postman com exemplos de requisições para testar os endpoints. Essa collection inclui chamadas para cadastrar, listar, atualizar e excluir clientes, além de simular e contratar seguros.

Baixe a collection Postman do projeto em anexo para poder .


```
{
	"info": {
		"_postman_id": "f9896248-fa33-4ed4-bba4-74fa5358c9fa",
		"name": "Clientes e Seguros API",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "18742861"
	},
	"item": [
		{
			"name": "Criar Cliente",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"nome\": \"João Silva\",\n  \"data_nascimento\": \"10/10/1990\",\n  \"cpf\": \"12345678901\",\n  \"telefone\": \"1234567890\",\n  \"endereco\": {\n    \"cep\": \"12345678\",\n    \"nomeRua\": \"Rua das Flores\"\n  }\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/clientes",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"clientes"
					]
				}
			},
			"response": []
		},
		{
			"name": "Listar Clientes",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/clientes",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"clientes"
					]
				}
			},
			"response": []
		},
		{
			"name": "Listar Cliente por CPF",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/clientes",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"clientes"
					]
				}
			},
			"response": []
		},
		{
			"name": "Atualizar Cliente por CPF",
			"request": {
				"method": "PUT",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"nome\": \"João da Silva\",\n  \"data_nascimento\": \"10/10/1990\",\n  \"cpf\": \"12345678901\",\n  \"telefone\": \"0987654321\",\n  \"endereco\": {\n    \"cep\": \"12345678\",\n    \"nomeRua\": \"Avenida Principal\"\n  }\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/clientes/12345678901",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"clientes",
						"12345678901"
					]
				}
			},
			"response": []
		},
		{
			"name": "Deletar Cliente por ID",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/clientes/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"clientes",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Simular Seguro",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8081/api/seguros/simular/12345678901?tipo=BRONZE",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"api",
						"seguros",
						"simular",
						"12345678901"
					],
					"query": [
						{
							"key": "tipo",
							"value": "BRONZE"
						}
					]
				},
				"description": "Simula o preço de um seguro Bronze para o cliente com CPF 12345678901"
			},
			"response": []
		},
		{
			"name": "Contratar Seguro",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8081/api/seguros/contratar/12345678901?tipo=PRATA",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8081",
					"path": [
						"api",
						"seguros",
						"contratar",
						"12345678901"
					],
					"query": [
						{
							"key": "tipo",
							"value": "PRATA"
						}
					]
				},
				"description": "Contrata um seguro Prata para o cliente com CPF 12345678901"
			},
			"response": []
		}
	]
}
```

### Uso do banco de dados

Segue as informacoes necessarias que o PostgreSQL precisa ter

```
 postgres-db:
    image: postgres:13
    container_name: postgres-db
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
      POSTGRES_DB: cadastrodb
    ports:
      - "5432:5432"
    networks:
      - app-network
    volumes:
      - pgdata:/var/lib/postgresql/data

```

## Tecnologias e Dependências
- Java 17: Linguagem de programação principal.
- Maven 3.9.9: Gerenciador de dependências.
- Swagger 3: Documentação interativa das APIs.
- JUnit 5 e Mockito: Testes unitários e simulações.
- PostgreSQL: Banco de dados relacional.



## Contato
Para dúvidas ou sugestões, sinta-se à vontade para entrar em contato. Este projeto foi desenvolvido com o objetivo de demonstrar habilidades em arquitetura de software e boas práticas de desenvolvimento, como princípios SOLID e Clean Code.

