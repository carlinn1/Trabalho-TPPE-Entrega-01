# Trabalho Prático 1 - TPPE
## Sistema de Gerenciamento do Campeonato Brasileiro

### Alunos
- [Preencher com os nomes dos integrantes do grupo]

### Descrição
Este projeto implementa um sistema de gerenciamento do Campeonato Brasileiro Série A, desenvolvido utilizando a metodologia TDD (Test-Driven Development) com JUnit 4.

### Funcionalidades Implementadas

1. **Sorteio de Rodadas**
   - Realiza o sorteio de jogos para cada rodada
   - Garante que cada time joga apenas uma vez por rodada
   - Garante que todos os times estejam presentes em cada rodada

2. **Validação de Confrontos**
   - Verifica que não existem dois jogos iguais ao longo de todas as rodadas
   - Garante que cada confronto (mandante x visitante) ocorra apenas uma vez

3. **Cálculo de Pontuação**
   - Vitória: 3 pontos
   - Empate: 1 ponto
   - Derrota: 0 pontos

4. **Cálculo de Estatísticas**
   - Número de vitórias
   - Gols marcados
   - Gols sofridos
   - Saldo de gols

5. **Classificação e Desempate**
   - Ordenação por pontos
   - Desempate por número de vitórias
   - Desempate por saldo de gols
   - Desempate por gols marcados

### Estrutura do Projeto

```
src/
├── main/java/br/unb/tppe/brasileirao/
│   ├── Time.java           # Classe que representa um time
│   ├── Partida.java        # Classe que representa uma partida
│   ├── Rodada.java         # Classe que representa uma rodada
│   └── Campeonato.java     # Classe principal que gerencia o campeonato
│
└── test/java/br/unb/tppe/brasileirao/
    ├── TestSorteioRodada.java          # Testes de sorteio de rodadas
    ├── TestDuplicidadeConfrontos.java  # Testes de duplicidade de confrontos
    ├── TestCalculoPontuacao.java       # Testes de cálculo de pontuação
    ├── TestCalculoEstatisticas.java    # Testes de estatísticas
    ├── TestDesempatePorVitorias.java   # Testes de desempate
    └── AllTests.java                   # Suite que executa todos os testes
```

### Como Executar

#### Pré-requisitos
- Java 8 ou superior
- Maven 3.6 ou superior

#### Compilar o projeto
```bash
mvn clean compile
```

#### Executar todos os testes
```bash
mvn test
```

#### Executar apenas a suite AllTests
```bash
mvn test -Dtest=AllTests
```

#### Executar um teste específico
```bash
mvn test -Dtest=TestSorteioRodada
mvn test -Dtest=TestDuplicidadeConfrontos
mvn test -Dtest=TestCalculoPontuacao
mvn test -Dtest=TestCalculoEstatisticas
mvn test -Dtest=TestDesempatePorVitorias
```

### Casos de Teste

#### 1. TestSorteioRodada
Verifica se os sorteios de rodadas funcionam corretamente:
- `testRodadaTemTodosOsTimes()` - Verifica se todos os times estão na rodada
- `testRodadaSemRepeticaoTimes()` - Verifica se não há repetição de times
- `testCadaTimeJogaUmaVezPorRodada()` - Verifica se cada time joga apenas uma vez
- `testNumeroCorretoDePartidas()` - Verifica o número correto de partidas (10 para 20 times)
- `testMultiplasRodadas()` - Testa múltiplas rodadas consecutivas

#### 2. TestDuplicidadeConfrontos
Verifica se não há confrontos duplicados:
- `testSemDuplicidadeEm5Rodadas()` - Verifica em 5 rodadas
- `testSemDuplicidadeEm10Rodadas()` - Verifica em 10 rodadas
- `testConfrontosUnicos()` - Verifica unicidade de cada confronto
- `testCadaConfrontoApareceumaVez()` - Verifica o primeiro turno completo (19 rodadas)

#### 3. TestCalculoPontuacao
Verifica o cálculo correto de pontuação:
- `testVitoriaValeTresPontos()` - Verifica que vitória dá 3 pontos
- `testEmpateValeUmPonto()` - Verifica que empate dá 1 ponto
- `testDerrotaValeZeroPontos()` - Verifica que derrota dá 0 pontos
- `testPontuacaoAcumulada()` - Testa acumulação de pontos
- `testPontuacaoEmRodadaCompleta()` - Testa pontuação em rodada completa
- `testPontuacaoMultiplasRodadas()` - Testa em múltiplas rodadas

#### 4. TestCalculoEstatisticas
Verifica o cálculo de estatísticas:
- `testContagemVitorias()` - Verifica contagem de vitórias
- `testContagemEmpates()` - Verifica contagem de empates
- `testContagemDerrotas()` - Verifica contagem de derrotas
- `testGolsMarcados()` - Verifica contagem de gols marcados
- `testGolsSofridos()` - Verifica contagem de gols sofridos
- `testSaldoGolsPositivo()` - Verifica saldo de gols positivo
- `testSaldoGolsNegativo()` - Verifica saldo de gols negativo
- `testSaldoGolsZero()` - Verifica saldo de gols zero
- `testEstatisticasCompletas()` - Verifica todas as estatísticas juntas
- `testEstatisticasEmRodadaCompleta()` - Verifica estatísticas em rodada completa

#### 5. TestDesempatePorVitorias
Verifica os critérios de desempate:
- `testDesempateComMaisVitorias()` - Testa desempate por vitórias
- `testOrdenacaoPorPontos()` - Testa ordenação por pontos
- `testDesempateComMesmosPontosEVitorias()` - Testa desempate por saldo de gols
- `testClassificacaoCompleta()` - Testa classificação completa
- `testTresTimesEmpatados()` - Testa cenário com três times empatados

### Critérios de Avaliação Atendidos

**Os sorteios de cada rodada acontecem adequadamente?**
- Sim, implementado na classe `Campeonato.sortearRodada()`
- Testado em `TestSorteioRodada`

**Não há repetição de times na rodada?**
- Sim, verificado através do método `Rodada.temRepeticaoTimes()`
- Testado em `TestSorteioRodada`

**Todos os times estão sorteados na rodada?**
- Sim, verificado através dos métodos `Rodada.contemTime()` e `Rodada.todosTimes()`
- Testado em `TestSorteioRodada`

**Não há duplicidade de confrontos entre todas as rodadas do campeonato?**
- Sim, verificado através do método `Campeonato.semDuplicidadeConfrontos()`
- Testado em `TestDuplicidadeConfrontos`

**A pontuação é calculada adequadamente a cada rodada?**
- Sim, implementado nos métodos `Time.registrarVitoria()`, `Time.registrarEmpate()` e `Time.registrarDerrota()`
- Testado em `TestCalculoPontuacao`

**Os cálculos de vitórias, gols marcados e sofridos, saldo de gols são calculados adequadamente?**
- Sim, implementado na classe `Time`
- Testado em `TestCalculoEstatisticas`

**Dois (ou mais) times de mesma pontuação estão classificados de acordo com o número de vitórias?**
- Sim, implementado no método `Campeonato.getClassificacao()` com `Comparator`
- Testado em `TestDesempatePorVitorias`

### Metodologia TDD

O projeto foi desenvolvido seguindo a metodologia TDD (Test-Driven Development):
1. Primeiro foram escritos os testes para cada funcionalidade
2. Depois foi implementado o código para passar nos testes
3. Por fim, o código foi refatorado mantendo os testes passando

### Observações

- O sistema suporta 20 times (configuração padrão do Brasileirão Série A)
- Cada rodada tem 10 partidas
- O campeonato completo tem 38 rodadas (turno e returno)
- Os testes podem ser executados individualmente ou através da suite `AllTests`
- Todos os testes estão passando ✓
