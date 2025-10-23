package br.unb.tppe.brasileirao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Teste 1: Verifica se os sorteios de cada rodada acontecem adequadamente.
 * - Não há repetição de times na rodada
 * - Todos os times estão sorteados na rodada
 */
public class TestSorteioRodada {
    
    private Campeonato campeonato;
    private List<Time> times;
    
    @Before
    public void setUp() {
        times = new ArrayList<>();
        times.add(new Time("Flamengo"));
        times.add(new Time("Palmeiras"));
        times.add(new Time("São Paulo"));
        times.add(new Time("Corinthians"));
        times.add(new Time("Atlético-MG"));
        times.add(new Time("Grêmio"));
        times.add(new Time("Internacional"));
        times.add(new Time("Santos"));
        times.add(new Time("Fluminense"));
        times.add(new Time("Botafogo"));
        times.add(new Time("Vasco"));
        times.add(new Time("Cruzeiro"));
        times.add(new Time("Bahia"));
        times.add(new Time("Fortaleza"));
        times.add(new Time("Athletico-PR"));
        times.add(new Time("Red Bull Bragantino"));
        times.add(new Time("Cuiabá"));
        times.add(new Time("Goiás"));
        times.add(new Time("Coritiba"));
        times.add(new Time("América-MG"));
        
        campeonato = new Campeonato(times);
    }
    
    @Test
    public void testRodadaTemTodosOsTimes() {
        Rodada rodada = campeonato.sortearRodada();
        
        // Verifica se todos os times estão na rodada
        for (Time time : times) {
            assertTrue("O time " + time.getNome() + " deveria estar na rodada", 
                      rodada.contemTime(time));
        }
    }
    
    @Test
    public void testRodadaSemRepeticaoTimes() {
        Rodada rodada = campeonato.sortearRodada();
        
        // Verifica se não há repetição de times
        assertFalse("Não deveria haver repetição de times na rodada", 
                   rodada.temRepeticaoTimes());
    }
    
    @Test
    public void testCadaTimeJogaUmaVezPorRodada() {
        Rodada rodada = campeonato.sortearRodada();
        
        // Cada time deve aparecer exatamente uma vez
        for (Time time : times) {
            assertEquals("O time " + time.getNome() + " deve aparecer exatamente uma vez",
                        1, rodada.contarAparicoes(time));
        }
    }
    
    @Test
    public void testNumeroCorretoDePartidas() {
        Rodada rodada = campeonato.sortearRodada();
        
        // Com 20 times, deve haver 10 partidas por rodada
        assertEquals("Deveria haver 10 partidas na rodada", 
                    10, rodada.getPartidas().size());
    }
    
    @Test
    public void testMultiplasRodadas() {
        // Sorteia várias rodadas
        for (int i = 0; i < 5; i++) {
            Rodada rodada = campeonato.sortearRodada();
            
            // Verifica cada rodada
            assertFalse("Rodada " + (i+1) + " não deveria ter repetição de times",
                       rodada.temRepeticaoTimes());
            assertTrue("Rodada " + (i+1) + " deveria ter todos os times",
                      rodada.todosTimes(times));
        }
    }
}
