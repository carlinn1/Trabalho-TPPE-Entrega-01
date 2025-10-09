package br.unb.tppe.campeonato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Partida.
 * Valida a criação de partidas e registro de resultados.
 */
@DisplayName("Testes da classe Partida")
public class PartidaTest {

    private Time mandante;
    private Time visitante;
    private Partida partida;

    @BeforeEach
    public void setUp() {
        mandante = new Time("Flamengo");
        visitante = new Time("Palmeiras");
        partida = new Partida(mandante, visitante);
    }

    @Test
    @DisplayName("Deve criar partida com mandante e visitante corretos")
    public void testCriarPartida() {
        assertEquals(mandante, partida.getMandante());
        assertEquals(visitante, partida.getVisitante());
        assertFalse(partida.isRealizada());
        assertNull(partida.getGolsMandante());
        assertNull(partida.getGolsVisitante());
    }

    @Test
    @DisplayName("Não deve permitir criar partida com time nulo")
    public void testPartidaComTimeNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida(null, visitante);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida(mandante, null);
        });
    }

    @Test
    @DisplayName("Não deve permitir time jogar contra si mesmo")
    public void testTimeContraSiMesmo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Partida(mandante, mandante);
        });
    }

    @Test
    @DisplayName("Deve registrar resultado com vitória do mandante")
    public void testVitoriaMandante() {
        partida.registrarResultado(3, 1);
        
        assertTrue(partida.isRealizada());
        assertEquals(3, partida.getGolsMandante());
        assertEquals(1, partida.getGolsVisitante());
        
        // Verificar atualização do mandante
        assertEquals(3, mandante.getPontos());
        assertEquals(1, mandante.getVitorias());
        assertEquals(3, mandante.getGolsMarcados());
        assertEquals(1, mandante.getGolsSofridos());
        
        // Verificar atualização do visitante
        assertEquals(0, visitante.getPontos());
        assertEquals(1, visitante.getDerrotas());
        assertEquals(1, visitante.getGolsMarcados());
        assertEquals(3, visitante.getGolsSofridos());
    }

    @Test
    @DisplayName("Deve registrar resultado com vitória do visitante")
    public void testVitoriaVisitante() {
        partida.registrarResultado(1, 3);
        
        assertTrue(partida.isRealizada());
        assertEquals(1, partida.getGolsMandante());
        assertEquals(3, partida.getGolsVisitante());
        
        // Verificar atualização do mandante
        assertEquals(0, mandante.getPontos());
        assertEquals(1, mandante.getDerrotas());
        
        // Verificar atualização do visitante
        assertEquals(3, visitante.getPontos());
        assertEquals(1, visitante.getVitorias());
    }

    @Test
    @DisplayName("Deve registrar resultado de empate")
    public void testEmpate() {
        partida.registrarResultado(2, 2);
        
        assertTrue(partida.isRealizada());
        assertEquals(2, partida.getGolsMandante());
        assertEquals(2, partida.getGolsVisitante());
        
        // Ambos times devem ter 1 ponto
        assertEquals(1, mandante.getPontos());
        assertEquals(1, mandante.getEmpates());
        assertEquals(1, visitante.getPontos());
        assertEquals(1, visitante.getEmpates());
    }

    @Test
    @DisplayName("Não deve permitir gols negativos")
    public void testGolsNegativos() {
        assertThrows(IllegalArgumentException.class, () -> {
            partida.registrarResultado(-1, 2);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            partida.registrarResultado(2, -1);
        });
    }

    @Test
    @DisplayName("Deve registrar empate 0x0")
    public void testEmpate0x0() {
        partida.registrarResultado(0, 0);
        
        assertTrue(partida.isRealizada());
        assertEquals(1, mandante.getPontos());
        assertEquals(1, visitante.getPontos());
        assertEquals(0, mandante.getGolsMarcados());
        assertEquals(0, visitante.getGolsMarcados());
    }

    @Test
    @DisplayName("Deve verificar igualdade de partidas (mesmos times e mando)")
    public void testIgualdadePartidas() {
        Partida partida1 = new Partida(mandante, visitante);
        Partida partida2 = new Partida(mandante, visitante);
        Partida partida3 = new Partida(visitante, mandante); // Mando invertido
        
        assertEquals(partida1, partida2);
        assertNotEquals(partida1, partida3);
    }

    @Test
    @DisplayName("Deve gerar toString para partida não realizada")
    public void testToStringNaoRealizada() {
        String resultado = partida.toString();
        assertTrue(resultado.contains("Flamengo"));
        assertTrue(resultado.contains("Palmeiras"));
        assertTrue(resultado.contains("não realizada"));
    }

    @Test
    @DisplayName("Deve gerar toString para partida realizada")
    public void testToStringRealizada() {
        partida.registrarResultado(3, 1);
        String resultado = partida.toString();
        
        assertTrue(resultado.contains("Flamengo"));
        assertTrue(resultado.contains("Palmeiras"));
        assertTrue(resultado.contains("3"));
        assertTrue(resultado.contains("1"));
    }
}
