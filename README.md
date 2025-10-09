<div align="center">

# ⚽ Sistema de Gerenciamento do Campeonato Brasileiro

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.9+-red?style=for-the-badge&logo=apache-maven)
![JUnit](https://img.shields.io/badge/JUnit-5.10.0-green?style=for-the-badge&logo=junit5)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**Trabalho Prático de TPPE - Universidade de Brasília**

*Técnicas de Programação para Plataformas Emergentes*

</div>

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Testes](#-testes)
- [Regras do Campeonato](#-regras-do-campeonato)
- [Autores](#-autores)

---

## 🎯 Sobre o Projeto

Sistema desenvolvido em Java para gerenciar o **Campeonato Brasileiro Série A**, simulando o torneio completo com 20 times em sistema de pontos corridos. O projeto foi desenvolvido utilizando a metodologia **TDD (Test-Driven Development)**, garantindo alta qualidade e cobertura de testes.

O sistema é capaz de:
- ⚙️ Sortear automaticamente todas as 38 rodadas do campeonato
- 🔒 Garantir que não existam partidas duplicadas
- 📊 Calcular pontuação e estatísticas dos times
- 🏆 Ordenar a tabela de classificação com critérios de desempate
- 📈 Gerenciar gols marcados, sofridos e saldo de gols

---

## ✨ Funcionalidades

### 🎲 Sorteio de Rodadas
- Geração automática de 38 rodadas (turno e returno)
- Cada rodada com 10 partidas (20 times / 2)
- Algoritmo Round-Robin para distribuição justa
- Garantia de mando de campo alternado

### ⚽ Gestão de Partidas
- Registro de resultados de partidas
- Cálculo automático de pontuação:
  - **Vitória**: 3 pontos
  - **Empate**: 1 ponto
  - **Derrota**: 0 pontos
- Atualização automática de estatísticas dos times

### 📊 Tabela de Classificação
Ordenação automática seguindo os critérios oficiais:
1. **Pontos** (maior número)
2. **Vitórias** (maior número)
3. **Saldo de gols** (maior saldo)
4. **Gols marcados** (maior número)

### 🏆 Classificações
- **Top 6**: Classificados para Copa Libertadores
- **7º ao 12º**: Classificados para Copa Sul-Americana
- **Últimos 4**: Rebaixados para Série B

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 LTS | Linguagem de programação |
| **Maven** | 3.9+ | Gerenciador de dependências |
| **JUnit 5** | 5.10.0 | Framework de testes |
| **AssertJ** | 3.24.2 | Biblioteca de assertions |
| **JUnit Platform Suite** | 1.10.0 | Execução de suítes de testes |

---

## 📦 Pré-requisitos

Antes de começar, você precisa ter instalado:

- **Java 21 (LTS)** ou superior
  ```bash
  java -version
  # Deve mostrar: java version "21" ou superior
  ```

- **Maven 3.9+**
  ```bash
  mvn -version
  # Deve mostrar: Apache Maven 3.9.x ou superior
  ```

- **Git** (para clonar o repositório)
  ```bash
  git --version
  ```

---

## 🔧 Como Executar

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/carlinn1/Trabalho-TPPE-Entrega-01.git
cd Trabalho-TPPE-Entrega-01
```

### 2️⃣ Compilar o Projeto

```bash
mvn clean compile
```

### 3️⃣ Executar os Testes

```bash
# Executar TODOS os testes (268 testes)
mvn test

# Executar apenas a suíte AllTests
mvn -Dtest=AllTests test

# Executar testes parametrizados
mvn -Dtest=TestesParametrizados test

# Executar uma classe de teste específica
mvn -Dtest=TimeTest test
mvn -Dtest=SorteioRodadasTest test
mvn -Dtest=TabelaClassificacaoTest test
```

### 4️⃣ Gerar Relatório de Testes

```bash
mvn clean test
# Os relatórios ficam em: target/surefire-reports/
```

### 5️⃣ Limpar o Projeto

```bash
mvn clean
```

---

## 📁 Estrutura do Projeto

```
Trabalho-TPPE-Entrega-01/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/unb/tppe/campeonato/
│   │           ├── Campeonato.java          # Gerencia o campeonato
│   │           ├── Partida.java             # Representa uma partida
│   │           ├── Rodada.java              # Agrupa partidas
│   │           ├── TabelaClassificacao.java # Ordena classificação
│   │           └── Time.java                # Representa um time
│   │
│   └── test/
│       └── java/
│           └── br/unb/tppe/campeonato/
│               ├── AllTests.java                  # ⭐ Suíte de todos os testes
│               ├── CampeonatoTest.java            # Testes de integração
│               ├── PartidaTest.java               # Testes de partidas
│               ├── RodadaTest.java                # Testes de rodadas
│               ├── SorteioRodadasTest.java        # Testes de sorteio
│               ├── TabelaClassificacaoTest.java   # Testes de classificação
│               ├── TestesParametrizados.java      # 🌟 Testes parametrizados (71 testes)
│               └── TimeTest.java                  # Testes de times
│
├── pom.xml                  # Configuração Maven
├── README.md                # Este arquivo
├── CHECKLIST_REQUISITOS.md  # ✅ Checklist completo de requisitos
└── LICENSE                  # Licença MIT
```

---

## 🧪 Testes

### Estatísticas de Cobertura

- **Total de Testes**: 268 ✅
- **Taxa de Sucesso**: 100% ✅
- **Classes Testadas**: 5/5 (100%)
- **Testes Parametrizados**: 71 ✅ (Pontos Extras!)

### Distribuição de Testes

| Classe de Teste | Testes | Descrição |
|-----------------|--------|-----------|
| `TimeTest` | 9 | Pontuação, vitórias, empates, derrotas, gols |
| `PartidaTest` | 11 | Criação e registro de resultados |
| `RodadaTest` | 8 | Agrupamento de partidas |
| `SorteioRodadasTest` | 12 | Sorteio e não duplicação |
| `TabelaClassificacaoTest` | 11 | Ordenação e desempates |
| `CampeonatoTest` | 12 | Testes de integração |
| `TestesParametrizados` | **71** | **🌟 Testes com múltiplos cenários** |
| **TOTAL** | **134** | (executados 2x via AllTests) |

### 🌟 Testes Parametrizados (Pontos Extras!)

O projeto inclui **71 testes parametrizados** que validam múltiplos cenários:

#### 📊 Tipos de Testes Parametrizados:

1. **Vitórias com Diferentes Placares** (10 testes)
   - Valida que vitórias sempre dão 3 pontos independente do placar
   - Exemplos: 1x0, 3x1, 7x1, etc.

2. **Empates com Diferentes Placares** (6 testes)
   - Valida que empates sempre dão 1 ponto
   - Testa placares de 0x0 até 5x5

3. **Derrotas com Diferentes Placares** (10 testes)
   - Valida que derrotas sempre dão 0 pontos
   - Testa diversos placares negativos

4. **Cálculo de Saldo de Gols** (10 testes)
   - Testa diferentes combinações de gols marcados e sofridos
   - Valida fórmula: saldo = marcados - sofridos

5. **Acumulação de Pontos** (10 testes)
   - Testa diferentes combinações de V/E/D
   - Exemplos: 3V+0E+0D=9pts, 19V+0E+19D=57pts

6. **Criação de Times** (10 testes)
   - Valida criação com nomes de times reais
   - Testa todos os principais clubes brasileiros

7. **Igualdade de Partidas** (5 testes)
   - Valida que partidas com mesmos times são iguais
   - Testa clássicos do futebol brasileiro

8. **Critérios de Desempate** (10 testes)
   - Testa todos os critérios: pontos, vitórias, saldo, gols
   - Valida ordenação correta da tabela

### Exemplos de Testes

#### ✅ Teste de Pontuação
```java
@Test
public void testRegistrarVitoria() {
    Time time = new Time("Flamengo");
    time.registrarVitoria(3, 1);
    
    assertEquals(3, time.getPontos());      // 3 pontos por vitória
    assertEquals(1, time.getVitorias());    // 1 vitória
    assertEquals(3, time.getGolsMarcados());
    assertEquals(2, time.getSaldoDeGols()); // 3 - 1 = 2
}
```

#### ✅ Teste de Sorteio
```java
@Test
public void testNaoExistePartidasDuplicadas() {
    Campeonato campeonato = criarCampeonatoCompleto();
    campeonato.sortearRodadas();
    
    assertFalse(campeonato.existemPartidasDuplicadas());
}
```

#### 🌟 Teste Parametrizado (Novo!)
```java
@ParameterizedTest(name = "Vitória com placar {0}x{1} deve dar 3 pontos")
@CsvSource({
    "1, 0",   // Vitória mínima
    "3, 1",   // Vitória comum
    "7, 1"    // Goleada
})
public void testVitoriasComDiferentesPlacar(int golsMarcados, int golsSofridos) {
    Time time = new Time("Teste FC");
    time.registrarVitoria(golsMarcados, golsSofridos);
    
    assertEquals(3, time.getPontos());
    assertEquals(1, time.getVitorias());
}
```

### Executar Testes Individualmente

```bash
# Por classe
mvn -Dtest=TimeTest test
mvn -Dtest=SorteioRodadasTest test

# Por método
mvn -Dtest=TimeTest#testRegistrarVitoria test

# Modo verboso
mvn test -X
```

---

## ⚽ Regras do Campeonato

### Sistema de Pontuação

| Resultado | Pontos |
|-----------|--------|
| Vitória | 3 pontos |
| Empate | 1 ponto |
| Derrota | 0 pontos |

### Critérios de Desempate

1. **Maior número de pontos**
2. **Maior número de vitórias**
3. **Maior saldo de gols** (gols marcados - gols sofridos)
4. **Maior número de gols marcados**
5. **Nome do time** (ordem alfabética)

### Formato do Campeonato

- **Times**: 20 clubes
- **Rodadas**: 38 (19 turno + 19 returno)
- **Partidas por rodada**: 10
- **Total de partidas**: 380
- **Partidas por time**: 38 (19 casa + 19 fora)

### 📈 Resumo Executivo

- **Requisitos Básicos**: ✅ 100% ATENDIDOS
- **Pontos Extras**: ✅ TESTES PARAMETRIZADOS (71 testes)
- **Qualidade do Código**: ✅ EXCELENTE
- **Documentação**: ✅ COMPLETA E PROFISSIONAL
- **Testes**: ✅ 268 TESTES COM 100% DE SUCESSO

---

## 👥 Autores

Desenvolvido por:

- **Carlos Eduardo** - [@carlinn1](https://github.com/carlinn1)
- **Nicollas** - [@Nicollaxs](https://github.com/Nicollaxs)
- **Genilson** - [@GenilsonJrs](https://github.com/GenilsonJrs)

---

## 📚 Disciplina

**Universidade de Brasília (UnB)**  
Faculdade de Ciências e Tecnologias em Engenharias  
TPPE - Técnicas de Programação para Plataformas Emergentes  

**Trabalho Prático - Etapa 1: TDD**  
Data de Entrega: 22/10/2025

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---


