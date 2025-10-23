# Trabalho Prático 1 - TPPE
## Sistema de Gerenciamento do Campeonato Brasileiro

Este trabalho implementa um sistema completo de gerenciamento do Campeonato Brasileiro Série A utilizando TDD (Test-Driven Development) com JUnit 4.

## Classes Principais

### 1. Time.java
Representa um time de futebol com suas estatísticas:
- Pontos, vitórias, empates, derrotas
- Gols marcados e sofridos
- Saldo de gols

### 2. Partida.java
Representa uma partida entre dois times:
- Time mandante e visitante
- Resultado da partida
- Atualização automática das estatísticas dos times

### 3. Rodada.java
Representa uma rodada do campeonato:
- Lista de partidas da rodada
- Validação de times duplicados
- Verificação de presença de todos os times

### 4. Campeonato.java
Classe principal que gerencia o campeonato:
- Sorteio de rodadas
- Validação de confrontos duplicados
- Geração da classificação com critérios de desempate

## Casos de Teste (JUnit 4)

### TestSorteioRodada
✓ Verifica se todos os times estão na rodada  
✓ Verifica se não há repetição de times  
✓ Verifica se cada time joga apenas uma vez  
✓ Verifica número correto de partidas  
✓ Testa múltiplas rodadas consecutivas  

### TestDuplicidadeConfrontos
✓ Verifica ausência de duplicidade em 5 rodadas  
✓ Verifica ausência de duplicidade em 10 rodadas  
✓ Verifica unicidade de confrontos  
✓ Verifica primeiro turno completo (19 rodadas)  

### TestCalculoPontuacao
✓ Vitória vale 3 pontos  
✓ Empate vale 1 ponto  
✓ Derrota vale 0 pontos  
✓ Pontuação acumulada  
✓ Pontuação em rodada completa  
✓ Pontuação em múltiplas rodadas  

### TestCalculoEstatisticas
✓ Contagem de vitórias, empates e derrotas  
✓ Contagem de gols marcados e sofridos  
✓ Cálculo de saldo de gols (positivo, negativo, zero)  
✓ Estatísticas completas  
✓ Estatísticas em rodada completa  

### TestDesempatePorVitorias
✓ Desempate por número de vitórias  
✓ Ordenação por pontos  
✓ Desempate por saldo de gols  
✓ Classificação completa  
✓ Cenários com múltiplos times empatados  

### AllTests.java
Suite que executa todos os testes de uma vez

## Como Executar

```bash
# Compilar o projeto
mvn clean compile

# Executar todos os testes
mvn test

# Executar apenas a suite AllTests
mvn test -Dtest=AllTests

# Executar um teste específico
mvn test -Dtest=TestSorteioRodada
```

## Critérios de Avaliação Atendidos

✅ Sorteios de rodadas funcionando corretamente  
✅ Sem repetição de times na rodada  
✅ Todos os times sorteados em cada rodada  
✅ Sem duplicidade de confrontos  
✅ Pontuação calculada corretamente (3-1-0)  
✅ Estatísticas calculadas corretamente  
✅ Desempate por número de vitórias implementado  

## Tecnologias Utilizadas

- Java 8
- JUnit 4.13.2
- Maven 3.x
- TDD (Test-Driven Development)

## Estrutura do Projeto

```
src/
├── main/java/br/unb/tppe/brasileirao/
│   ├── Time.java
│   ├── Partida.java
│   ├── Rodada.java
│   └── Campeonato.java
└── test/java/br/unb/tppe/brasileirao/
    ├── TestSorteioRodada.java
    ├── TestDuplicidadeConfrontos.java
    ├── TestCalculoPontuacao.java
    ├── TestCalculoEstatisticas.java
    ├── TestDesempatePorVitorias.java
    └── AllTests.java
```

## Observações

- Sistema configurado para 20 times (Série A)
- 10 partidas por rodada
- 38 rodadas no campeonato completo
- Todos os testes passando ✓
