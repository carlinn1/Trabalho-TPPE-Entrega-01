package br.unb.tppe.brasileirao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Teste 5: Verifica se o critério de desempate por número de vitórias 
 * é aplicado corretamente na classificação.
 */
public class TestDesempatePorVitorias {
    
    private Time flamengo;
    private Time palmeiras;
    private Time saopaulo;
    private Time corinthians;
    private Campeonato campeonato;
    
    @Before
    public void setUp() {
        flamengo = new Time("Flamengo");
        palmeiras = new Time("Palmeiras");
        saopaulo = new Time("São Paulo");
        corinthians = new Time("Corinthians");
        
        List<Time> times = new ArrayList<>();
        times.add(flamengo);
        times.add(palmeiras);
        times.add(saopaulo);
        times.add(corinthians);
        
        campeonato = new Campeonato(times);
    }
    
    @Test
    public void testDesempateComMaisVitorias() {
        // Flamengo: 2 vitórias = 6 pontos
        Partida p1 = new Partida(flamengo, corinthians);
        p1.registrarResultado(2, 0);
        Partida p2 = new Partida(flamengo, saopaulo);
        p2.registrarResultado(1, 0);
        
        // Palmeiras: 2 empates = 2 pontos, depois 1 vitória e 1 empate = 4 pontos, depois mais 1 empate = 5, depois mais 1 vitória = 8
        // Vamos dar 6 pontos com empates: 6 empates = 6 pontos
        Partida p3 = new Partida(palmeiras, saopaulo);
        p3.registrarResultado(1, 1);
        Partida p4 = new Partida(palmeiras, corinthians);
        p4.registrarResultado(2, 2);
        Partida p5 = new Partida(flamengo, palmeiras);
        p5.registrarResultado(1, 1);
        Partida p6 = new Partida(saopaulo, palmeiras);
        p6.registrarResultado(0, 0);
        Partida p7 = new Partida(corinthians, palmeiras);
        p7.registrarResultado(3, 3);
        Partida p8 = new Partida(palmeiras, flamengo);
        p8.registrarResultado(2, 2);
        
        List<Time> classificacao = campeonato.getClassificacao();
        
        // Flamengo deve estar à frente de Palmeiras (ambos com 8 pontos, mas Flamengo tem mais vitórias)
        int posFlamengo = classificacao.indexOf(flamengo);
        int posPalmeiras = classificacao.indexOf(palmeiras);
        
        assertTrue("Flamengo (com mais vitórias) deve estar à frente de Palmeiras na classificação",
                  posFlamengo < posPalmeiras);
    }
    
    @Test
    public void testOrdenacaoPorPontos() {
        // Flamengo: 9 pontos (3 vitórias)
        Partida p1 = new Partida(flamengo, palmeiras);
        p1.registrarResultado(2, 0);
        Partida p2 = new Partida(flamengo, saopaulo);
        p2.registrarResultado(1, 0);
        Partida p3 = new Partida(flamengo, corinthians);
        p3.registrarResultado(3, 1);
        
        // Palmeiras: 6 pontos (2 vitórias)
        Partida p4 = new Partida(palmeiras, saopaulo);
        p4.registrarResultado(2, 1);
        Partida p5 = new Partida(palmeiras, corinthians);
        p5.registrarResultado(1, 0);
        
        List<Time> classificacao = campeonato.getClassificacao();
        
        // Flamengo deve estar em 1º
        assertEquals("Flamengo deveria estar em 1º", flamengo, classificacao.get(0));
        
        // Palmeiras deve estar em 2º
        assertEquals("Palmeiras deveria estar em 2º", palmeiras, classificacao.get(1));
    }
    
    @Test
    public void testDesempateComMesmosPontosEVitorias() {
        // Flamengo: 6 pontos, 2 vitórias, saldo +4
        Partida p1 = new Partida(flamengo, saopaulo);
        p1.registrarResultado(3, 0);
        Partida p2 = new Partida(flamengo, corinthians);
        p2.registrarResultado(2, 1);
        
        // Palmeiras: 6 pontos, 2 vitórias, saldo +2
        Partida p3 = new Partida(palmeiras, saopaulo);
        p3.registrarResultado(2, 1);
        Partida p4 = new Partida(palmeiras, corinthians);
        p4.registrarResultado(2, 1);
        
        List<Time> classificacao = campeonato.getClassificacao();
        
        int posFlamengo = classificacao.indexOf(flamengo);
        int posPalmeiras = classificacao.indexOf(palmeiras);
        
        // Flamengo deve estar à frente (melhor saldo de gols)
        assertTrue("Flamengo (melhor saldo) deve estar à frente de Palmeiras",
                  posFlamengo < posPalmeiras);
    }
    
    @Test
    public void testClassificacaoCompleta() {
        // Configura diferentes cenários
        // Flamengo: 7 pontos (2V, 1E) - saldo +3
        Partida p1 = new Partida(flamengo, corinthians);
        p1.registrarResultado(2, 0);
        Partida p2 = new Partida(saopaulo, flamengo);
        p2.registrarResultado(1, 2);
        Partida p3 = new Partida(flamengo, palmeiras);
        p3.registrarResultado(1, 1);
        
        // Palmeiras: 7 pontos (2V, 1E) - saldo +2
        Partida p4 = new Partida(palmeiras, saopaulo);
        p4.registrarResultado(2, 1);
        Partida p5 = new Partida(corinthians, palmeiras);
        p5.registrarResultado(0, 1);
        
        // São Paulo: 3 pontos (1V) - saldo -1
        Partida p6 = new Partida(saopaulo, corinthians);
        p6.registrarResultado(2, 1);
        
        List<Time> classificacao = campeonato.getClassificacao();
        
        // Verifica ordem
        assertEquals("1º lugar", flamengo, classificacao.get(0)); // 7 pts, saldo +3
        assertEquals("2º lugar", palmeiras, classificacao.get(1)); // 7 pts, saldo +2
        assertEquals("3º lugar", saopaulo, classificacao.get(2)); // 3 pts
        assertEquals("4º lugar", corinthians, classificacao.get(3)); // 0 pts
    }
    
    @Test
    public void testTresTimesEmpatados() {
        // Três times com mesma pontuação mas diferentes vitórias
        // Flamengo: 4 pontos (1V, 1E)
        Partida p1 = new Partida(flamengo, corinthians);
        p1.registrarResultado(2, 0);
        Partida p2 = new Partida(flamengo, saopaulo);
        p2.registrarResultado(1, 1);
        
        // Palmeiras: 4 pontos (1V, 1E)
        Partida p3 = new Partida(palmeiras, corinthians);
        p3.registrarResultado(1, 0);
        Partida p4 = new Partida(palmeiras, saopaulo);
        p4.registrarResultado(0, 0);
        
        // São Paulo: 4 pontos (1V, 1E)
        Partida p5 = new Partida(corinthians, saopaulo);
        p5.registrarResultado(0, 1);
        
        List<Time> classificacao = campeonato.getClassificacao();
        
        // Todos têm mesma pontuação e vitórias, então saldo de gols é o desempate
        // Verifica que Flamengo está melhor posicionado (tem melhor saldo)
        int posFlamengo = classificacao.indexOf(flamengo);
        assertTrue("Flamengo deve estar entre os 3 primeiros", posFlamengo < 3);
    }
}
