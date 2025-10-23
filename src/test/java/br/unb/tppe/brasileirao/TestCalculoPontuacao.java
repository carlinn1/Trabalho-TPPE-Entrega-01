package br.unb.tppe.brasileirao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Teste 3: Verifica se a pontuação é calculada adequadamente a cada rodada.
 * - Vitória = 3 pontos
 * - Empate = 1 ponto
 * - Derrota = 0 pontos
 */
public class TestCalculoPontuacao {
    
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
    public void testVitoriaValeTresPontos() {
        Partida partida = new Partida(flamengo, palmeiras);
        partida.registrarResultado(3, 1);
        
        assertEquals("Vencedor deveria ter 3 pontos", 3, flamengo.getPontos());
        assertEquals("Perdedor deveria ter 0 pontos", 0, palmeiras.getPontos());
    }
    
    @Test
    public void testEmpateValeUmPonto() {
        Partida partida = new Partida(flamengo, palmeiras);
        partida.registrarResultado(2, 2);
        
        assertEquals("Time mandante deveria ter 1 ponto no empate", 1, flamengo.getPontos());
        assertEquals("Time visitante deveria ter 1 ponto no empate", 1, palmeiras.getPontos());
    }
    
    @Test
    public void testDerrotaValeZeroPontos() {
        Partida partida = new Partida(flamengo, palmeiras);
        partida.registrarResultado(1, 3);
        
        assertEquals("Perdedor deveria ter 0 pontos", 0, flamengo.getPontos());
        assertEquals("Vencedor deveria ter 3 pontos", 3, palmeiras.getPontos());
    }
    
    @Test
    public void testPontuacaoAcumulada() {
        // Primeira partida - vitória
        Partida partida1 = new Partida(flamengo, palmeiras);
        partida1.registrarResultado(2, 0);
        
        // Segunda partida - empate
        Partida partida2 = new Partida(saopaulo, flamengo);
        partida2.registrarResultado(1, 1);
        
        // Terceira partida - vitória
        Partida partida3 = new Partida(flamengo, corinthians);
        partida3.registrarResultado(3, 2);
        
        assertEquals("Flamengo deveria ter 7 pontos (3+1+3)", 7, flamengo.getPontos());
    }
    
    @Test
    public void testPontuacaoEmRodadaCompleta() {
        List<Time> times = new ArrayList<>();
        times.add(flamengo);
        times.add(palmeiras);
        times.add(saopaulo);
        times.add(corinthians);
        
        Campeonato campeonato = new Campeonato(times);
        Rodada rodada = campeonato.sortearRodada();
        
        // Simula resultados
        List<Partida> partidas = rodada.getPartidas();
        partidas.get(0).registrarResultado(2, 1); // Vitória mandante
        partidas.get(1).registrarResultado(1, 1); // Empate
        
        // Verifica pontuação total
        int pontosTotais = 0;
        for (Time time : times) {
            pontosTotais += time.getPontos();
        }
        
        // Total deve ser 5 pontos (3 da vitória + 1+1 do empate)
        assertEquals("Total de pontos deveria ser 5", 5, pontosTotais);
    }
    
    @Test
    public void testPontuacaoMultiplasRodadas() {
        List<Time> times = new ArrayList<>();
        times.add(flamengo);
        times.add(palmeiras);
        times.add(saopaulo);
        times.add(corinthians);
        
        Campeonato campeonato = new Campeonato(times);
        
        // Rodada 1
        Rodada rodada1 = campeonato.sortearRodada();
        rodada1.getPartidas().get(0).registrarResultado(3, 0);
        rodada1.getPartidas().get(1).registrarResultado(2, 2);
        
        // Rodada 2
        Rodada rodada2 = campeonato.sortearRodada();
        rodada2.getPartidas().get(0).registrarResultado(1, 0);
        rodada2.getPartidas().get(1).registrarResultado(0, 0);
        
        // Verifica que todos os times têm alguma pontuação
        int timesComPontos = 0;
        for (Time time : times) {
            if (time.getPontos() > 0) {
                timesComPontos++;
            }
        }
        
        assertTrue("Pelo menos 3 times deveriam ter pontos", timesComPontos >= 3);
    }
}
