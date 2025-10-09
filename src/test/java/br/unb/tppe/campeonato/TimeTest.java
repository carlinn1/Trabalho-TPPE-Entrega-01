package br.unb.tppe.campeonato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Time.
 * Valida o cálculo de pontuação, vitórias, empates, derrotas,
 * gols marcados, gols sofridos e saldo de gols.
 */
@DisplayName("Testes da classe Time")
public class TimeTest {

    private Time time;

    @BeforeEach
    public void setUp() {
        time = new Time("Flamengo");
    }

    @Test
    @DisplayName("Deve criar um time com nome correto")
    public void testCriarTime() {
        assertEquals("Flamengo", time.getNome());
        assertEquals(0, time.getPontos());
        assertEquals(0, time.getVitorias());
        assertEquals(0, time.getEmpates());
        assertEquals(0, time.getDerrotas());
        assertEquals(0, time.getGolsMarcados());
        assertEquals(0, time.getGolsSofridos());
        assertEquals(0, time.getSaldoDeGols());
    }

    @Test
    @DisplayName("Deve registrar vitória corretamente - 3 pontos")
    public void testRegistrarVitoria() {
        time.registrarVitoria(3, 1);
        
        assertEquals(3, time.getPontos());
        assertEquals(1, time.getVitorias());
        assertEquals(0, time.getEmpates());
        assertEquals(0, time.getDerrotas());
        assertEquals(3, time.getGolsMarcados());
        assertEquals(1, time.getGolsSofridos());
        assertEquals(2, time.getSaldoDeGols());
        assertEquals(1, time.getJogos());
    }

    @Test
    @DisplayName("Deve registrar empate corretamente - 1 ponto")
    public void testRegistrarEmpate() {
        time.registrarEmpate(2, 2);
        
        assertEquals(1, time.getPontos());
        assertEquals(0, time.getVitorias());
        assertEquals(1, time.getEmpates());
        assertEquals(0, time.getDerrotas());
        assertEquals(2, time.getGolsMarcados());
        assertEquals(2, time.getGolsSofridos());
        assertEquals(0, time.getSaldoDeGols());
        assertEquals(1, time.getJogos());
    }

    @Test
    @DisplayName("Deve registrar derrota corretamente - 0 pontos")
    public void testRegistrarDerrota() {
        time.registrarDerrota(1, 3);
        
        assertEquals(0, time.getPontos());
        assertEquals(0, time.getVitorias());
        assertEquals(0, time.getEmpates());
        assertEquals(1, time.getDerrotas());
        assertEquals(1, time.getGolsMarcados());
        assertEquals(3, time.getGolsSofridos());
        assertEquals(-2, time.getSaldoDeGols());
        assertEquals(1, time.getJogos());
    }

    @Test
    @DisplayName("Deve acumular pontos de múltiplas partidas")
    public void testAcumularResultados() {
        time.registrarVitoria(3, 1);  // 3 pontos
        time.registrarEmpate(1, 1);   // 1 ponto
        time.registrarDerrota(0, 2);  // 0 pontos
        time.registrarVitoria(2, 0);  // 3 pontos
        
        assertEquals(7, time.getPontos());
        assertEquals(2, time.getVitorias());
        assertEquals(1, time.getEmpates());
        assertEquals(1, time.getDerrotas());
        assertEquals(6, time.getGolsMarcados());
        assertEquals(4, time.getGolsSofridos());
        assertEquals(2, time.getSaldoDeGols());
        assertEquals(4, time.getJogos());
    }

    @Test
    @DisplayName("Deve calcular saldo de gols positivo corretamente")
    public void testSaldoDeGolsPositivo() {
        time.registrarVitoria(5, 1);
        time.registrarVitoria(3, 0);
        
        assertEquals(8, time.getGolsMarcados());
        assertEquals(1, time.getGolsSofridos());
        assertEquals(7, time.getSaldoDeGols());
    }

    @Test
    @DisplayName("Deve calcular saldo de gols negativo corretamente")
    public void testSaldoDeGolsNegativo() {
        time.registrarDerrota(1, 4);
        time.registrarDerrota(0, 3);
        
        assertEquals(1, time.getGolsMarcados());
        assertEquals(7, time.getGolsSofridos());
        assertEquals(-6, time.getSaldoDeGols());
    }

    @Test
    @DisplayName("Deve verificar igualdade entre times pelo nome")
    public void testIgualdadeTimes() {
        Time time1 = new Time("Palmeiras");
        Time time2 = new Time("Palmeiras");
        Time time3 = new Time("São Paulo");
        
        assertEquals(time1, time2);
        assertNotEquals(time1, time3);
    }

    @Test
    @DisplayName("Deve gerar toString formatado corretamente")
    public void testToString() {
        time.registrarVitoria(3, 1);
        String resultado = time.toString();
        
        assertTrue(resultado.contains("Flamengo"));
        assertTrue(resultado.contains("P:3"));
        assertTrue(resultado.contains("V:1"));
    }
}
