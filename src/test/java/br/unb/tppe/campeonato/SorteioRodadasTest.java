package br.unb.tppe.campeonato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Testes para o sorteio de rodadas do Campeonato.
 * Valida que o sorteio funciona corretamente e não gera partidas duplicadas.
 */
@DisplayName("Testes de Sorteio de Rodadas")
public class SorteioRodadasTest {

    private Campeonato campeonato;
    private List<Time> times;

    @BeforeEach
    public void setUp() {
        campeonato = new Campeonato();
        times = new ArrayList<>();
        
        // Adicionar 20 times conforme especificação
        String[] nomesTime = {
            "Flamengo", "Palmeiras", "Atlético-MG", "Fortaleza",
            "Fluminense", "Botafogo", "São Paulo", "Corinthians",
            "Internacional", "Grêmio", "Santos", "Athletico-PR",
            "Bragantino", "Cuiabá", "Ceará", "América-MG",
            "Atlético-GO", "Juventude", "Bahia", "Sport"
        };
        
        for (String nome : nomesTime) {
            Time time = new Time(nome);
            times.add(time);
            campeonato.adicionarTime(time);
        }
    }

    @Test
    @DisplayName("Deve sortear exatamente 38 rodadas")
    public void testNumeroDeRodadas() {
        campeonato.sortearRodadas();
        assertEquals(38, campeonato.getRodadas().size());
    }

    @Test
    @DisplayName("Cada rodada deve ter 10 partidas (20 times / 2)")
    public void testPartidasPorRodada() {
        campeonato.sortearRodadas();
        
        for (Rodada rodada : campeonato.getRodadas()) {
            assertEquals(10, rodada.getPartidas().size(), 
                "Rodada " + rodada.getNumero() + " deve ter 10 partidas");
        }
    }

    @Test
    @DisplayName("Não deve existir partidas duplicadas no campeonato")
    public void testNaoExistePartidasDuplicadas() {
        campeonato.sortearRodadas();
        assertFalse(campeonato.existemPartidasDuplicadas());
    }

    @Test
    @DisplayName("Deve ter exatamente 380 partidas no total (20 times * 19 rodadas * 2 turnos)")
    public void testTotalDePartidas() {
        campeonato.sortearRodadas();
        
        int totalPartidas = 0;
        for (Rodada rodada : campeonato.getRodadas()) {
            totalPartidas += rodada.getPartidas().size();
        }
        
        assertEquals(380, totalPartidas);
    }

    @Test
    @DisplayName("Cada time deve jogar 38 partidas (1 contra cada um dos outros 19 em turno e returno)")
    public void testCadaTimeJoga38Vezes() {
        campeonato.sortearRodadas();
        
        for (Time time : times) {
            int partidasDoTime = 0;
            for (Rodada rodada : campeonato.getRodadas()) {
                for (Partida partida : rodada.getPartidas()) {
                    if (partida.getMandante().equals(time) || partida.getVisitante().equals(time)) {
                        partidasDoTime++;
                    }
                }
            }
            assertEquals(38, partidasDoTime, 
                "Time " + time.getNome() + " deve jogar 38 partidas");
        }
    }

    @Test
    @DisplayName("Cada time deve enfrentar cada outro time exatamente 2 vezes")
    public void testCadaTimeEnfrentaOutro2Vezes() {
        campeonato.sortearRodadas();
        
        for (Time time1 : times) {
            for (Time time2 : times) {
                if (time1.equals(time2)) continue;
                
                int confrontos = 0;
                for (Rodada rodada : campeonato.getRodadas()) {
                    for (Partida partida : rodada.getPartidas()) {
                        if ((partida.getMandante().equals(time1) && partida.getVisitante().equals(time2)) ||
                            (partida.getMandante().equals(time2) && partida.getVisitante().equals(time1))) {
                            confrontos++;
                        }
                    }
                }
                
                assertEquals(2, confrontos, 
                    time1.getNome() + " deve enfrentar " + time2.getNome() + " exatamente 2 vezes");
            }
        }
    }

    @Test
    @DisplayName("Deve ter turno e returno (cada confronto uma vez como mandante)")
    public void testTurnoEReturno() {
        campeonato.sortearRodadas();
        
        for (Time time1 : times) {
            for (Time time2 : times) {
                if (time1.equals(time2)) continue;
                
                boolean jogouEmCasa = false;
                boolean jogouFora = false;
                
                for (Rodada rodada : campeonato.getRodadas()) {
                    for (Partida partida : rodada.getPartidas()) {
                        if (partida.getMandante().equals(time1) && partida.getVisitante().equals(time2)) {
                            jogouEmCasa = true;
                        }
                        if (partida.getMandante().equals(time2) && partida.getVisitante().equals(time1)) {
                            jogouFora = true;
                        }
                    }
                }
                
                assertTrue(jogouEmCasa, 
                    time1.getNome() + " deve jogar em casa contra " + time2.getNome());
                assertTrue(jogouFora, 
                    time1.getNome() + " deve jogar fora contra " + time2.getNome());
            }
        }
    }

    @Test
    @DisplayName("Não deve permitir sortear sem 20 times")
    public void testSortearSem20Times() {
        Campeonato campeonatoIncompleto = new Campeonato();
        campeonatoIncompleto.adicionarTime(new Time("Flamengo"));
        campeonatoIncompleto.adicionarTime(new Time("Palmeiras"));
        
        assertThrows(IllegalStateException.class, () -> {
            campeonatoIncompleto.sortearRodadas();
        });
    }

    @Test
    @DisplayName("Todas as partidas devem ser únicas (sem duplicação)")
    public void testPartidasUnicas() {
        campeonato.sortearRodadas();
        
        Set<Partida> partidasUnicas = new HashSet<>();
        for (Rodada rodada : campeonato.getRodadas()) {
            for (Partida partida : rodada.getPartidas()) {
                assertTrue(partidasUnicas.add(partida), 
                    "Partida " + partida + " está duplicada");
            }
        }
        
        assertEquals(380, partidasUnicas.size());
    }

    @Test
    @DisplayName("Nenhum time deve jogar contra si mesmo")
    public void testTimeNaoJogaContraSiMesmo() {
        campeonato.sortearRodadas();
        
        for (Rodada rodada : campeonato.getRodadas()) {
            for (Partida partida : rodada.getPartidas()) {
                assertNotEquals(partida.getMandante(), partida.getVisitante(),
                    "Time não pode jogar contra si mesmo");
            }
        }
    }

    @Test
    @DisplayName("Deve poder acessar rodada por número")
    public void testAcessarRodadaPorNumero() {
        campeonato.sortearRodadas();
        
        Rodada rodada1 = campeonato.getRodada(1);
        assertEquals(1, rodada1.getNumero());
        
        Rodada rodada38 = campeonato.getRodada(38);
        assertEquals(38, rodada38.getNumero());
    }

    @Test
    @DisplayName("Não deve permitir acessar rodada com número inválido")
    public void testAcessarRodadaInvalida() {
        campeonato.sortearRodadas();
        
        assertThrows(IllegalArgumentException.class, () -> {
            campeonato.getRodada(0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            campeonato.getRodada(39);
        });
    }
}
