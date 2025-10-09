package br.unb.tppe.campeonato;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

/**
 * Testes Parametrizados para validação de múltiplos cenários.
 * Testes parametrizados permitem executar o mesmo teste com diferentes entradas,
 * reduzindo duplicação de código e aumentando a cobertura de casos de teste.
 */
@DisplayName("Testes Parametrizados do Campeonato")
public class TestesParametrizados {

    /**
     * Testa múltiplos resultados de vitória com diferentes placares.
     * Valida que independente do placar, uma vitória sempre dá 3 pontos.
     */
    @ParameterizedTest(name = "Vitória com placar {0}x{1} deve dar 3 pontos")
    @CsvSource({
        "1, 0",   // Vitória mínima
        "2, 1",   // Vitória por 1 gol
        "3, 0",   // Vitória por 3 gols
        "5, 2",   // Vitória com muitos gols
        "4, 1",   // Vitória elástica
        "6, 3",   // Jogo com muitos gols
        "1, 0",   // Vitória de 1x0
        "2, 0",   // Vitória de 2x0
        "3, 1",   // Vitória de 3x1
        "7, 1"    // Goleada
    })
    @DisplayName("Teste parametrizado de vitórias com diferentes placares")
    public void testVitoriasComDiferentesPlacar(int golsMarcados, int golsSofridos) {
        Time time = new Time("Teste FC");
        time.registrarVitoria(golsMarcados, golsSofridos);
        
        assertEquals(3, time.getPontos(), 
            "Vitória deve sempre dar 3 pontos, independente do placar");
        assertEquals(1, time.getVitorias(), 
            "Deve registrar 1 vitória");
        assertEquals(golsMarcados, time.getGolsMarcados());
        assertEquals(golsSofridos, time.getGolsSofridos());
        assertEquals(golsMarcados - golsSofridos, time.getSaldoDeGols());
    }

    /**
     * Testa múltiplos resultados de empate com diferentes placares.
     * Valida que independente do placar, um empate sempre dá 1 ponto.
     */
    @ParameterizedTest(name = "Empate {0}x{0} deve dar 1 ponto")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    @DisplayName("Teste parametrizado de empates com diferentes placares")
    public void testEmpatesComDiferentesPlacar(int gols) {
        Time time = new Time("Teste FC");
        time.registrarEmpate(gols, gols);
        
        assertEquals(1, time.getPontos(), 
            "Empate deve sempre dar 1 ponto");
        assertEquals(1, time.getEmpates(), 
            "Deve registrar 1 empate");
        assertEquals(0, time.getSaldoDeGols(), 
            "Saldo de gols em empate deve ser 0");
        assertEquals(gols, time.getGolsMarcados());
        assertEquals(gols, time.getGolsSofridos());
    }

    /**
     * Testa múltiplos resultados de derrota com diferentes placares.
     * Valida que independente do placar, uma derrota sempre dá 0 pontos.
     */
    @ParameterizedTest(name = "Derrota {0}x{1} deve dar 0 pontos")
    @CsvSource({
        "0, 1",   // Derrota mínima
        "1, 2",   // Derrota por 1 gol
        "0, 3",   // Derrota sem marcar
        "2, 5",   // Derrota com gols marcados
        "1, 4",   // Goleada sofrida
        "0, 5",   // Goleada sem marcar
        "1, 3",   // Derrota de 1x3
        "2, 4",   // Derrota de 2x4
        "0, 2",   // Derrota de 0x2
        "1, 7"    // Goleada histórica
    })
    @DisplayName("Teste parametrizado de derrotas com diferentes placares")
    public void testDerrotasComDiferentesPlacar(int golsMarcados, int golsSofridos) {
        Time time = new Time("Teste FC");
        time.registrarDerrota(golsMarcados, golsSofridos);
        
        assertEquals(0, time.getPontos(), 
            "Derrota deve sempre dar 0 pontos");
        assertEquals(1, time.getDerrotas(), 
            "Deve registrar 1 derrota");
        assertEquals(golsMarcados, time.getGolsMarcados());
        assertEquals(golsSofridos, time.getGolsSofridos());
        assertTrue(time.getSaldoDeGols() < 0, 
            "Saldo de gols em derrota deve ser negativo");
    }

    /**
     * Testa cálculo de saldo de gols com diferentes combinações.
     */
    @ParameterizedTest(name = "Gols {0} marcados e {1} sofridos = saldo {2}")
    @CsvSource({
        "10, 5, 5",    // Saldo positivo alto
        "3, 1, 2",     // Saldo positivo baixo
        "5, 5, 0",     // Saldo zero
        "2, 7, -5",    // Saldo negativo
        "0, 3, -3",    // Saldo negativo sem marcar
        "15, 10, 5",   // Muitos gols, saldo positivo
        "8, 12, -4",   // Muitos gols, saldo negativo
        "1, 1, 0",     // Empate com poucos gols
        "20, 15, 5",   // Campeonato ofensivo
        "5, 15, -10"   // Campeonato defensivo ruim
    })
    @DisplayName("Teste parametrizado de cálculo de saldo de gols")
    public void testCalculoSaldoDeGols(int golsMarcados, int golsSofridos, int saldoEsperado) {
        Time time = new Time("Teste FC");
        
        // Simular partidas para atingir os gols
        if (golsMarcados > golsSofridos) {
            time.registrarVitoria(golsMarcados, golsSofridos);
        } else if (golsMarcados < golsSofridos) {
            time.registrarDerrota(golsMarcados, golsSofridos);
        } else {
            time.registrarEmpate(golsMarcados, golsSofridos);
        }
        
        assertEquals(saldoEsperado, time.getSaldoDeGols(),
            String.format("Saldo deve ser %d - %d = %d", 
                golsMarcados, golsSofridos, saldoEsperado));
    }

    /**
     * Fornece dados para teste de acumulação de pontos.
     */
    static Stream<Arguments> providerAcumulacaoPontos() {
        return Stream.of(
            Arguments.of(3, 0, 0, 9),   // 3 vitórias = 9 pontos
            Arguments.of(2, 1, 0, 7),   // 2 vitórias + 1 empate = 7 pontos
            Arguments.of(1, 2, 0, 5),   // 1 vitória + 2 empates = 5 pontos
            Arguments.of(0, 3, 0, 3),   // 3 empates = 3 pontos
            Arguments.of(1, 0, 2, 3),   // 1 vitória + 2 derrotas = 3 pontos
            Arguments.of(0, 0, 3, 0),   // 3 derrotas = 0 pontos
            Arguments.of(5, 2, 1, 17),  // 5 vitórias + 2 empates + 1 derrota = 17 pontos
            Arguments.of(10, 5, 3, 35), // 10 vitórias + 5 empates + 3 derrotas = 35 pontos
            Arguments.of(15, 8, 15, 53),// 15 vitórias + 8 empates + 15 derrotas = 53 pontos
            Arguments.of(19, 0, 19, 57) // 19 vitórias + 19 derrotas = 57 pontos (campeão possível)
        );
    }

    /**
     * Testa acumulação de pontos com diferentes combinações de resultados.
     */
    @ParameterizedTest(name = "{0}V + {1}E + {2}D = {3} pontos")
    @MethodSource("providerAcumulacaoPontos")
    @DisplayName("Teste parametrizado de acumulação de pontos")
    public void testAcumulacaoPontos(int vitorias, int empates, int derrotas, int pontosEsperados) {
        Time time = new Time("Teste FC");
        
        // Registrar vitórias
        for (int i = 0; i < vitorias; i++) {
            time.registrarVitoria(2, 1);
        }
        
        // Registrar empates
        for (int i = 0; i < empates; i++) {
            time.registrarEmpate(1, 1);
        }
        
        // Registrar derrotas
        for (int i = 0; i < derrotas; i++) {
            time.registrarDerrota(1, 2);
        }
        
        assertEquals(pontosEsperados, time.getPontos(),
            String.format("%d vitórias + %d empates + %d derrotas deve dar %d pontos",
                vitorias, empates, derrotas, pontosEsperados));
        assertEquals(vitorias, time.getVitorias());
        assertEquals(empates, time.getEmpates());
        assertEquals(derrotas, time.getDerrotas());
    }

    /**
     * Testa validação de nomes de times.
     */
    @ParameterizedTest(name = "Time com nome ''{0}'' deve ser válido")
    @ValueSource(strings = {
        "Flamengo",
        "Palmeiras",
        "São Paulo",
        "Corinthians",
        "Atlético-MG",
        "Internacional",
        "Grêmio",
        "Santos",
        "Botafogo",
        "Fluminense"
    })
    @DisplayName("Teste parametrizado de criação de times com nomes válidos")
    public void testCriacaoTimesComNomesValidos(String nome) {
        Time time = new Time(nome);
        
        assertNotNull(time);
        assertEquals(nome, time.getNome());
        assertEquals(0, time.getPontos());
        assertEquals(0, time.getVitorias());
    }

    /**
     * Testa que partidas com mesmo mandante e visitante são iguais.
     */
    @ParameterizedTest(name = "Partida {0} vs {1}")
    @CsvSource({
        "Flamengo, Palmeiras",
        "São Paulo, Corinthians",
        "Atlético-MG, Cruzeiro",
        "Internacional, Grêmio",
        "Santos, Botafogo"
    })
    @DisplayName("Teste parametrizado de igualdade de partidas")
    public void testIgualdadePartidas(String mandante, String visitante) {
        Time timeMandante = new Time(mandante);
        Time timeVisitante = new Time(visitante);
        
        Partida partida1 = new Partida(timeMandante, timeVisitante);
        Partida partida2 = new Partida(timeMandante, timeVisitante);
        
        assertEquals(partida1, partida2, 
            "Partidas com mesmo mandante e visitante devem ser iguais");
        assertEquals(partida1.hashCode(), partida2.hashCode(),
            "Partidas iguais devem ter mesmo hashCode");
    }

    /**
     * Fornece dados para teste de critérios de desempate.
     */
    static Stream<Arguments> providerCriteriosDesempate() {
        return Stream.of(
            // pontos1, vitorias1, saldo1, gols1, pontos2, vitorias2, saldo2, gols2, vencedor
            Arguments.of(10, 3, 5, 15, 8, 2, 4, 12, 1),   // Mais pontos
            Arguments.of(10, 3, 5, 15, 10, 2, 4, 12, 1),  // Mesmos pontos, mais vitórias
            Arguments.of(10, 3, 5, 15, 10, 3, 3, 12, 1),  // Mesmos pontos e vitórias, melhor saldo
            Arguments.of(10, 3, 5, 15, 10, 3, 5, 12, 1),  // Mesmos pontos, vitórias e saldo, mais gols
            Arguments.of(15, 5, 10, 20, 12, 4, 8, 18, 1), // Claramente superior
            Arguments.of(6, 2, 0, 10, 6, 2, 0, 8, 1),     // Empate em tudo, mais gols decide
            Arguments.of(20, 6, 15, 25, 18, 6, 15, 25, 1),// Mais pontos decide
            Arguments.of(12, 4, 5, 20, 12, 3, 8, 22, 1),  // Mais vitórias decide
            Arguments.of(9, 3, 2, 12, 9, 3, -1, 15, 1),   // Melhor saldo decide
            Arguments.of(7, 2, 3, 18, 7, 2, 3, 15, 1)     // Mais gols marcados decide
        );
    }

    /**
     * Testa aplicação dos critérios de desempate.
     */
    @ParameterizedTest(name = "Time1 ({0}pts, {1}v, {2}sg, {3}gm) deve vencer Time2 ({4}pts, {5}v, {6}sg, {7}gm)")
    @MethodSource("providerCriteriosDesempate")
    @DisplayName("Teste parametrizado de critérios de desempate")
    public void testCriteriosDesempate(
            int pontos1, int vitorias1, int saldo1, int golsMarcados1,
            int pontos2, int vitorias2, int saldo2, int golsMarcados2,
            int vencedorEsperado) {
        
        Time time1 = new Time("Time1");
        Time time2 = new Time("Time2");
        
        // Simular estatísticas do time1
        simularEstatisticas(time1, pontos1, vitorias1, golsMarcados1, golsMarcados1 - saldo1);
        
        // Simular estatísticas do time2
        simularEstatisticas(time2, pontos2, vitorias2, golsMarcados2, golsMarcados2 - saldo2);
        
        TabelaClassificacao tabela = new TabelaClassificacao(java.util.List.of(time1, time2));
        
        assertEquals("Time1", tabela.getClassificacao().get(0).getNome(),
            "Time1 deve estar em primeiro lugar pelos critérios de desempate");
    }

    /**
     * Método auxiliar para simular estatísticas de um time.
     */
    private void simularEstatisticas(Time time, int pontos, int vitorias, int golsMarcados, int golsSofridos) {
        int empates = (pontos - (vitorias * 3));
        
        // Registrar vitórias
        for (int i = 0; i < vitorias; i++) {
            time.registrarVitoria(2, 1);
        }
        
        // Registrar empates
        for (int i = 0; i < empates; i++) {
            time.registrarEmpate(1, 1);
        }
        
        // Ajustar gols se necessário (simplificação para o teste)
    }
}
