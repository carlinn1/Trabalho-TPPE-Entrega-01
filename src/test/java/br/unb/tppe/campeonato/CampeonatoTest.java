package br.unb.tppe.campeonato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Testes de integração do Campeonato.
 * Valida o fluxo completo: criar campeonato, adicionar times, sortear rodadas,
 * registrar resultados e calcular classificação.
 */
@DisplayName("Testes de Integração do Campeonato")
public class CampeonatoTest {

    private Campeonato campeonato;

    @BeforeEach
    public void setUp() {
        campeonato = new Campeonato();
    }

    @Test
    @DisplayName("Deve criar campeonato vazio")
    public void testCriarCampeonato() {
        assertEquals(0, campeonato.getTimes().size());
        assertEquals(0, campeonato.getRodadas().size());
    }

    @Test
    @DisplayName("Deve adicionar time ao campeonato")
    public void testAdicionarTime() {
        Time time = new Time("Flamengo");
        campeonato.adicionarTime(time);
        
        assertEquals(1, campeonato.getTimes().size());
        assertTrue(campeonato.getTimes().contains(time));
    }

    @Test
    @DisplayName("Não deve permitir adicionar time nulo")
    public void testAdicionarTimeNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            campeonato.adicionarTime(null);
        });
    }

    @Test
    @DisplayName("Não deve permitir adicionar mais de 20 times")
    public void testLimite20Times() {
        for (int i = 1; i <= 20; i++) {
            campeonato.adicionarTime(new Time("Time" + i));
        }
        
        assertThrows(IllegalStateException.class, () -> {
            campeonato.adicionarTime(new Time("Time21"));
        });
    }

    @Test
    @DisplayName("Não deve permitir adicionar time duplicado")
    public void testTimeDuplicado() {
        Time time = new Time("Flamengo");
        campeonato.adicionarTime(time);
        
        assertThrows(IllegalArgumentException.class, () -> {
            campeonato.adicionarTime(time);
        });
    }

    @Test
    @DisplayName("Deve realizar fluxo completo de um campeonato simplificado")
    public void testFluxoCompletoCampeonato() {
        // Criar 4 times para teste simplificado
        Time flamengo = new Time("Flamengo");
        Time palmeiras = new Time("Palmeiras");
        Time saoPaulo = new Time("São Paulo");
        Time corinthians = new Time("Corinthians");
        
        // Simular partidas manualmente
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(3, 1); // Flamengo vence
        
        Partida p2 = new Partida(saoPaulo, corinthians);
        p2.registrarResultado(2, 2); // Empate
        
        Partida p3 = new Partida(flamengo, saoPaulo);
        p3.registrarResultado(2, 0); // Flamengo vence
        
        Partida p4 = new Partida(palmeiras, corinthians);
        p4.registrarResultado(1, 0); // Palmeiras vence
        
        // Verificar pontuação
        assertEquals(6, flamengo.getPontos()); // 2 vitórias
        assertEquals(3, palmeiras.getPontos()); // 1 vitória, 1 derrota
        assertEquals(1, saoPaulo.getPontos()); // 1 empate, 1 derrota
        assertEquals(1, corinthians.getPontos()); // 1 empate, 1 derrota
        
        // Criar tabela e verificar classificação
        List<Time> times = List.of(flamengo, palmeiras, saoPaulo, corinthians);
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
    }

    @Test
    @DisplayName("Deve calcular pontuação correta após múltiplas rodadas")
    public void testCalculoPontuacaoMultiplasRodadas() {
        // Criar 4 times
        Time time1 = new Time("Time1");
        Time time2 = new Time("Time2");
        Time time3 = new Time("Time3");
        Time time4 = new Time("Time4");
        
        // Rodada 1
        Partida p1r1 = new Partida(time1, time2);
        p1r1.registrarResultado(3, 0); // Time1 vence
        
        Partida p2r1 = new Partida(time3, time4);
        p2r1.registrarResultado(1, 1); // Empate
        
        // Rodada 2
        Partida p1r2 = new Partida(time1, time3);
        p1r2.registrarResultado(2, 1); // Time1 vence
        
        Partida p2r2 = new Partida(time2, time4);
        p2r2.registrarResultado(2, 0); // Time2 vence
        
        // Verificações
        assertEquals(6, time1.getPontos()); // 2 vitórias = 6 pontos
        assertEquals(3, time2.getPontos()); // 1 vitória, 1 derrota = 3 pontos
        assertEquals(1, time3.getPontos()); // 1 empate, 1 derrota = 1 ponto
        assertEquals(1, time4.getPontos()); // 1 empate, 1 derrota = 1 ponto
        
        assertEquals(2, time1.getVitorias());
        assertEquals(5, time1.getGolsMarcados());
        assertEquals(1, time1.getGolsSofridos());
        assertEquals(4, time1.getSaldoDeGols());
    }

    @Test
    @DisplayName("Deve aplicar critérios de desempate corretamente")
    public void testCriteriosDesempateEmCenarioReal() {
        // Criar times com estatísticas específicas para testar desempate
        Time time1 = new Time("Time1");
        time1.registrarVitoria(3, 0);
        time1.registrarEmpate(1, 1);
        time1.registrarDerrota(0, 2);
        // 4 pontos, 1 vitória, saldo +1, 4 gols marcados
        
        Time time2 = new Time("Time2");
        time2.registrarVitoria(2, 1);
        time2.registrarEmpate(1, 1);
        time2.registrarDerrota(1, 2);
        // 4 pontos, 1 vitória, saldo -1, 4 gols marcados
        
        Time time3 = new Time("Time3");
        time3.registrarEmpate(2, 2);
        time3.registrarEmpate(1, 1);
        time3.registrarEmpate(0, 0);
        time3.registrarEmpate(1, 1);
        // 4 pontos, 0 vitórias
        
        List<Time> times = List.of(time1, time2, time3);
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        // Todos têm 4 pontos
        // time1 e time2 têm 1 vitória (time3 tem 0)
        // time1 tem saldo +1, time2 tem saldo -1
        assertEquals("Time1", classificacao.get(0).getNome());
        assertEquals("Time2", classificacao.get(1).getNome());
        assertEquals("Time3", classificacao.get(2).getNome());
    }

    @Test
    @DisplayName("Deve garantir que cada time seja único no campeonato")
    public void testTimesUnicos() {
        Time flamengo1 = new Time("Flamengo");
        Time flamengo2 = new Time("Flamengo");
        
        campeonato.adicionarTime(flamengo1);
        
        // Mesmo sendo objetos diferentes, têm o mesmo nome
        assertThrows(IllegalArgumentException.class, () -> {
            campeonato.adicionarTime(flamengo2);
        });
    }

    @Test
    @DisplayName("Deve retornar cópia da lista de times")
    public void testGetTimesRetornaCopia() {
        Time time = new Time("Flamengo");
        campeonato.adicionarTime(time);
        
        List<Time> times = campeonato.getTimes();
        times.clear(); // Tentar modificar
        
        // Lista interna não deve ser afetada
        assertEquals(1, campeonato.getTimes().size());
    }

    @Test
    @DisplayName("Deve retornar cópia da lista de rodadas")
    public void testGetRodadasRetornaCopia() {
        // Adicionar 20 times
        for (int i = 1; i <= 20; i++) {
            campeonato.adicionarTime(new Time("Time" + i));
        }
        
        campeonato.sortearRodadas();
        
        List<Rodada> rodadas = campeonato.getRodadas();
        rodadas.clear(); // Tentar modificar
        
        // Lista interna não deve ser afetada
        assertEquals(38, campeonato.getRodadas().size());
    }

    @Test
    @DisplayName("Deve validar que não existem partidas duplicadas após sorteio")
    public void testValidacaoPartidasDuplicadas() {
        // Adicionar 20 times
        for (int i = 1; i <= 20; i++) {
            campeonato.adicionarTime(new Time("Time" + i));
        }
        
        campeonato.sortearRodadas();
        
        // Método deve retornar false (não há duplicatas)
        assertFalse(campeonato.existemPartidasDuplicadas());
    }
}
