package br.unb.tppe.brasileirao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Teste 2: Verifica se não há duplicidade de confrontos entre todas as rodadas.
 * Garante que não existam dois jogos iguais ao longo de todas as rodadas
 * (mesmos times como mandantes e visitantes).
 */
public class TestDuplicidadeConfrontos {
    
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
    public void testSemDuplicidadeEm5Rodadas() {
        // Sorteia 5 rodadas
        for (int i = 0; i < 5; i++) {
            campeonato.sortearRodada();
        }
        
        // Verifica se não há duplicidade
        assertTrue("Não deveria haver confrontos duplicados nas rodadas",
                  campeonato.semDuplicidadeConfrontos());
    }
    
    @Test
    public void testSemDuplicidadeEm10Rodadas() {
        // Sorteia 10 rodadas
        for (int i = 0; i < 10; i++) {
            campeonato.sortearRodada();
        }
        
        // Verifica se não há duplicidade
        assertTrue("Não deveria haver confrontos duplicados em 10 rodadas",
                  campeonato.semDuplicidadeConfrontos());
    }
    
    @Test
    public void testConfrontosUnicos() {
        // Sorteia algumas rodadas
        for (int i = 0; i < 3; i++) {
            campeonato.sortearRodada();
        }
        
        List<Rodada> rodadas = campeonato.getRodadas();
        
        // Coleta todas as partidas
        List<Partida> todasPartidas = new ArrayList<>();
        for (Rodada rodada : rodadas) {
            todasPartidas.addAll(rodada.getPartidas());
        }
        
        // Verifica se cada partida é única
        for (int i = 0; i < todasPartidas.size(); i++) {
            for (int j = i + 1; j < todasPartidas.size(); j++) {
                assertFalse("Partidas " + todasPartidas.get(i) + " e " + todasPartidas.get(j) + 
                           " não deveriam ser iguais",
                           todasPartidas.get(i).mesmaPartida(todasPartidas.get(j)));
            }
        }
    }
    
    @Test
    public void testCadaConfrontoApareceumaVez() {
        // Sorteia 19 rodadas (primeiro turno)
        for (int i = 0; i < 19; i++) {
            campeonato.sortearRodada();
        }
        
        // Deve ter 190 partidas únicas (10 partidas por rodada x 19 rodadas)
        List<Rodada> rodadas = campeonato.getRodadas();
        int totalPartidas = 0;
        for (Rodada rodada : rodadas) {
            totalPartidas += rodada.getPartidas().size();
        }
        
        assertEquals("Deveria haver 190 partidas em 19 rodadas", 190, totalPartidas);
        assertTrue("Não deveria haver confrontos duplicados",
                  campeonato.semDuplicidadeConfrontos());
    }
}
