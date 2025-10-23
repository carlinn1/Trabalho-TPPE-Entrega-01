package br.unb.tppe.brasileirao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Teste 4: Verifica se os cálculos de vitórias, gols marcados, gols sofridos 
 * e saldo de gols são calculados adequadamente.
 */
public class TestCalculoEstatisticas {
    
    private Time flamengo;
    private Time palmeiras;
    private Time saopaulo;
    private Time corinthians;
    
    @Before
    public void setUp() {
        flamengo = new Time("Flamengo");
        palmeiras = new Time("Palmeiras");
        saopaulo = new Time("São Paulo");
        corinthians = new Time("Corinthians");
    }
    
    @Test
    public void testContagemVitorias() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(3, 1);
        
        Partida p2 = new Partida(saopaulo, flamengo);
        p2.registrarResultado(0, 2);
        
        assertEquals("Flamengo deveria ter 2 vitórias", 2, flamengo.getVitorias());
        assertEquals("Palmeiras deveria ter 0 vitórias", 0, palmeiras.getVitorias());
    }
    
    @Test
    public void testContagemEmpates() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(2, 2);
        
        Partida p2 = new Partida(flamengo, saopaulo);
        p2.registrarResultado(1, 1);
        
        assertEquals("Flamengo deveria ter 2 empates", 2, flamengo.getEmpates());
    }
    
    @Test
    public void testContagemDerrotas() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(0, 3);
        
        Partida p2 = new Partida(saopaulo, flamengo);
        p2.registrarResultado(2, 1);
        
        assertEquals("Flamengo deveria ter 2 derrotas", 2, flamengo.getDerrotas());
    }
    
    @Test
    public void testGolsMarcados() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(3, 1);
        
        Partida p2 = new Partida(saopaulo, flamengo);
        p2.registrarResultado(2, 4);
        
        assertEquals("Flamengo deveria ter 7 gols marcados", 7, flamengo.getGolsMarcados());
    }
    
    @Test
    public void testGolsSofridos() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(3, 1);
        
        Partida p2 = new Partida(saopaulo, flamengo);
        p2.registrarResultado(2, 0);
        
        assertEquals("Flamengo deveria ter 3 gols sofridos", 3, flamengo.getGolsSofridos());
    }
    
    @Test
    public void testSaldoGolsPositivo() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(5, 1);
        
        assertEquals("Flamengo deveria ter saldo de +4", 4, flamengo.getSaldoGols());
    }
    
    @Test
    public void testSaldoGolsNegativo() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(1, 5);
        
        assertEquals("Flamengo deveria ter saldo de -4", -4, flamengo.getSaldoGols());
    }
    
    @Test
    public void testSaldoGolsZero() {
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(2, 2);
        
        assertEquals("Flamengo deveria ter saldo de 0", 0, flamengo.getSaldoGols());
    }
    
    @Test
    public void testEstatisticasCompletas() {
        // Vitória
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(3, 1);
        
        // Empate
        Partida p2 = new Partida(saopaulo, flamengo);
        p2.registrarResultado(2, 2);
        
        // Derrota
        Partida p3 = new Partida(flamengo, corinthians);
        p3.registrarResultado(0, 1);
        
        assertEquals("Vitórias", 1, flamengo.getVitorias());
        assertEquals("Empates", 1, flamengo.getEmpates());
        assertEquals("Derrotas", 1, flamengo.getDerrotas());
        assertEquals("Gols marcados", 5, flamengo.getGolsMarcados());
        assertEquals("Gols sofridos", 4, flamengo.getGolsSofridos());
        assertEquals("Saldo de gols", 1, flamengo.getSaldoGols());
        assertEquals("Jogos", 3, flamengo.getJogos());
    }
    
    @Test
    public void testEstatisticasEmRodadaCompleta() {
        List<Time> times = new ArrayList<>();
        times.add(flamengo);
        times.add(palmeiras);
        times.add(saopaulo);
        times.add(corinthians);
        
        Campeonato campeonato = new Campeonato(times);
        Rodada rodada = campeonato.sortearRodada();
        
        // Registra resultados
        rodada.getPartidas().get(0).registrarResultado(3, 2);
        rodada.getPartidas().get(1).registrarResultado(1, 0);
        
        // Verifica que as estatísticas foram atualizadas
        int totalGols = 0;
        int totalVitorias = 0;
        
        for (Time time : times) {
            totalGols += time.getGolsMarcados();
            totalVitorias += time.getVitorias();
        }
        
        assertEquals("Total de gols marcados deve ser 6", 6, totalGols);
        assertEquals("Total de vitórias deve ser 2", 2, totalVitorias);
    }
}
