package br.unb.tppe.campeonato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Rodada.
 * Valida a criação de rodadas e adição de partidas.
 */
@DisplayName("Testes da classe Rodada")
public class RodadaTest {

    private Rodada rodada;
    private Time time1;
    private Time time2;

    @BeforeEach
    public void setUp() {
        rodada = new Rodada(1);
        time1 = new Time("Flamengo");
        time2 = new Time("Palmeiras");
    }

    @Test
    @DisplayName("Deve criar rodada com número correto")
    public void testCriarRodada() {
        assertEquals(1, rodada.getNumero());
        assertEquals(0, rodada.getPartidas().size());
    }

    @Test
    @DisplayName("Deve adicionar partida à rodada")
    public void testAdicionarPartida() {
        Partida partida = new Partida(time1, time2);
        rodada.adicionarPartida(partida);
        
        assertEquals(1, rodada.getPartidas().size());
        assertTrue(rodada.getPartidas().contains(partida));
    }

    @Test
    @DisplayName("Não deve permitir adicionar partida nula")
    public void testAdicionarPartidaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            rodada.adicionarPartida(null);
        });
    }

    @Test
    @DisplayName("Deve adicionar múltiplas partidas")
    public void testAdicionarMultiplasPartidas() {
        Time time3 = new Time("São Paulo");
        Time time4 = new Time("Corinthians");
        
        Partida partida1 = new Partida(time1, time2);
        Partida partida2 = new Partida(time3, time4);
        
        rodada.adicionarPartida(partida1);
        rodada.adicionarPartida(partida2);
        
        assertEquals(2, rodada.getPartidas().size());
    }

    @Test
    @DisplayName("Deve verificar se todas partidas foram realizadas")
    public void testTodasPartidasRealizadas() {
        Partida partida1 = new Partida(time1, time2);
        Time time3 = new Time("São Paulo");
        Time time4 = new Time("Corinthians");
        Partida partida2 = new Partida(time3, time4);
        
        rodada.adicionarPartida(partida1);
        rodada.adicionarPartida(partida2);
        
        assertFalse(rodada.todasPartidasRealizadas());
        
        partida1.registrarResultado(2, 1);
        assertFalse(rodada.todasPartidasRealizadas());
        
        partida2.registrarResultado(1, 1);
        assertTrue(rodada.todasPartidasRealizadas());
    }

    @Test
    @DisplayName("Rodada sem partidas deve retornar true para todasPartidasRealizadas")
    public void testRodadaVaziaTodasRealizadas() {
        assertTrue(rodada.todasPartidasRealizadas());
    }

    @Test
    @DisplayName("Deve retornar cópia da lista de partidas")
    public void testGetPartidasRetornaCopia() {
        Partida partida = new Partida(time1, time2);
        rodada.adicionarPartida(partida);
        
        var partidas = rodada.getPartidas();
        partidas.clear(); // Tentar modificar a lista retornada
        
        // Lista interna não deve ser afetada
        assertEquals(1, rodada.getPartidas().size());
    }

    @Test
    @DisplayName("Deve gerar toString formatado")
    public void testToString() {
        Partida partida = new Partida(time1, time2);
        rodada.adicionarPartida(partida);
        
        String resultado = rodada.toString();
        assertTrue(resultado.contains("Rodada 1"));
    }
}
