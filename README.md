# API REST - Campeonato de Futebol

API REST desenvolvida em Java com Spring Boot para gerenciamento de um campeonato de futebol.

## Como executar

1. Abra o projeto no IntelliJ IDEA.
2. Aguarde o Maven carregar as dependências.
3. Execute a classe `CampeonatoApplication`.
4. A aplicação ficará disponível em:

`http://localhost:8080`

## Endpoints

### Jogadores

#### Criar jogador

`POST /jogadores`

Exemplo de body:

```json
{
  "nome": "Joao",
  "numero": 10,
  "posicao": "Atacante",
  "idade": 23
}
```

#### Listar jogadores

`GET /jogadores`

#### Buscar jogador

`GET /jogadores/{id}`

---

### Times

#### Criar time

`POST /times`

Exemplo de body:

```json
{
  "nome": "Palmeiras",
  "cidade": "Sao Paulo"
}
```

#### Listar times

`GET /times`

#### Buscar time

`GET /times/{id}`

#### Adicionar jogador ao time

`POST /times/{idTime}/jogadores/{idJogador}`

---

### Partidas

#### Criar partida

`POST /partidas`

#### Atualizar placar

`PATCH /partidas/{id}/placar`

Exemplo de body:

```json
{
  "golMandante": 2,
  "golVisitante": 1
}
```

#### Registrar cartão

`POST /partidas/{id}/cartoes`

Exemplo de body:

```json
{
  "idJogador": "ID_DO_JOGADOR",
  "tipo": "AMARELO",
  "minuto": 35
}
```

Os tipos permitidos são `AMARELO` e `VERMELHO`.

---

### Campeonatos

#### Criar campeonato

`POST /campeonatos`

Exemplo de body:

```json
{
  "nome": "Brasileirao",
  "ano": 2026
}
```

#### Consultar classificação

`GET /campeonatos/{id}/classificacao`

A classificação utiliza a seguinte pontuação:

- Vitória: 3 pontos
- Empate: 1 ponto
- Derrota: 0 pontos

Os times são retornados em ordem decrescente de pontuação.

## Tecnologias

- Java
- Spring Boot
- Spring Web
- Maven