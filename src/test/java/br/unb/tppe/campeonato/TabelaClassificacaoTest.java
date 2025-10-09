package br.unb.tppe.campeonato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Testes para a classe TabelaClassificacao.
 * Valida os critérios de desempate: pontos, vitórias, saldo de gols e gols marcados.
 */
@DisplayName("Testes da Tabela de Classificação")
public class TabelaClassificacaoTest {

    private List<Time> times;

    @BeforeEach
    public void setUp() {
        times = new ArrayList<>();
    }

    @Test
    @DisplayName("Deve ordenar por pontos (critério principal)")
    public void testOrdenacaoPorPontos() {
        Time time1 = new Time("Flamengo");
        time1.registrarVitoria(2, 0); // 3 pontos
        
        Time time2 = new Time("Palmeiras");
        time2.registrarVitoria(1, 0); // 3 pontos
        time2.registrarVitoria(2, 1); // 6 pontos
        
        Time time3 = new Time("São Paulo");
        time3.registrarEmpate(1, 1); // 1 ponto
        
        times.add(time3);
        times.add(time1);
        times.add(time2);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        assertEquals("Palmeiras", classificacao.get(0).getNome());
        assertEquals("Flamengo", classificacao.get(1).getNome());
        assertEquals("São Paulo", classificacao.get(2).getNome());
    }

    @Test
    @DisplayName("Deve desempatar por número de vitórias quando pontos iguais")
    public void testDesempatePorVitorias() {
        Time time1 = new Time("Flamengo");
        time1.registrarVitoria(2, 0); // 3 pontos, 1 vitória
        
        Time time2 = new Time("Palmeiras");
        time2.registrarEmpate(1, 1); // 1 ponto
        time2.registrarEmpate(2, 2); // 2 pontos
        time2.registrarEmpate(0, 0); // 3 pontos, 0 vitórias
        
        times.add(time2);
        times.add(time1);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        // Ambos com 3 pontos, mas Flamengo tem mais vitórias
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
    }

    @Test
    @DisplayName("Deve desempatar por saldo de gols quando pontos e vitórias iguais")
    public void testDesempatePorSaldoDeGols() {
        Time time1 = new Time("Flamengo");
        time1.registrarVitoria(5, 1); // 3 pontos, 1 vitória, saldo +4
        
        Time time2 = new Time("Palmeiras");
        time2.registrarVitoria(2, 1); // 3 pontos, 1 vitória, saldo +1
        
        times.add(time2);
        times.add(time1);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals(4, classificacao.get(0).getSaldoDeGols());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
        assertEquals(1, classificacao.get(1).getSaldoDeGols());
    }

    @Test
    @DisplayName("Deve desempatar por gols marcados quando pontos, vitórias e saldo iguais")
    public void testDesempatePorGolsMarcados() {
        Time time1 = new Time("Flamengo");
        time1.registrarVitoria(3, 1); // 3 pontos, 1 vitória, saldo +2, 3 gols marcados
        
        Time time2 = new Time("Palmeiras");
        time2.registrarVitoria(2, 0); // 3 pontos, 1 vitória, saldo +2, 2 gols marcados
        
        times.add(time2);
        times.add(time1);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals(3, classificacao.get(0).getGolsMarcados());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
        assertEquals(2, classificacao.get(1).getGolsMarcados());
    }

    @Test
    @DisplayName("Deve aplicar todos os critérios de desempate em ordem")
    public void testTodosCriteriosDesempate() {
        // Time com mais pontos
        Time lider = new Time("Líder");
        lider.registrarVitoria(3, 0); // 3 pontos
        lider.registrarVitoria(2, 0); // 6 pontos
        
        // Times empatados em 3 pontos
        Time segundo = new Time("Segundo");
        segundo.registrarVitoria(5, 1); // 3 pontos, 1 vitória, saldo +4, 5 gols
        
        Time terceiro = new Time("Terceiro");
        terceiro.registrarVitoria(3, 1); // 3 pontos, 1 vitória, saldo +2, 3 gols
        
        Time quarto = new Time("Quarto");
        quarto.registrarEmpate(1, 1); // 1 ponto
        quarto.registrarEmpate(2, 2); // 2 pontos
        quarto.registrarEmpate(0, 0); // 3 pontos, 0 vitórias
        
        times.add(quarto);
        times.add(terceiro);
        times.add(lider);
        times.add(segundo);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> classificacao = tabela.getClassificacao();
        
        assertEquals("Líder", classificacao.get(0).getNome());
        assertEquals("Segundo", classificacao.get(1).getNome());
        assertEquals("Terceiro", classificacao.get(2).getNome());
        assertEquals("Quarto", classificacao.get(3).getNome());
    }

    @Test
    @DisplayName("Deve retornar posição correta do time")
    public void testGetPosicao() {
        Time time1 = new Time("Primeiro");
        time1.registrarVitoria(3, 0);
        
        Time time2 = new Time("Segundo");
        time2.registrarEmpate(1, 1);
        
        times.add(time2);
        times.add(time1);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        
        assertEquals(1, tabela.getPosicao(time1));
        assertEquals(2, tabela.getPosicao(time2));
    }

    @Test
    @DisplayName("Deve retornar top 6 classificados para Libertadores")
    public void testClassificadosLibertadores() {
        for (int i = 1; i <= 8; i++) {
            Time time = new Time("Time" + i);
            for (int j = 0; j < (9 - i); j++) {
                time.registrarVitoria(2, 0);
            }
            times.add(time);
        }
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> libertadores = tabela.getClassificadosLibertadores();
        
        assertEquals(6, libertadores.size());
        assertEquals("Time1", libertadores.get(0).getNome());
        assertEquals("Time6", libertadores.get(5).getNome());
    }

    @Test
    @DisplayName("Deve retornar times da 7ª à 12ª posição para Sul-Americana")
    public void testClassificadosSulAmericana() {
        for (int i = 1; i <= 15; i++) {
            Time time = new Time("Time" + i);
            for (int j = 0; j < (16 - i); j++) {
                time.registrarVitoria(2, 0);
            }
            times.add(time);
        }
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> sulAmericana = tabela.getClassificadosSulAmericana();
        
        assertEquals(6, sulAmericana.size());
        assertEquals("Time7", sulAmericana.get(0).getNome());
        assertEquals("Time12", sulAmericana.get(5).getNome());
    }

    @Test
    @DisplayName("Deve retornar últimos 4 times rebaixados")
    public void testRebaixados() {
        for (int i = 1; i <= 20; i++) {
            Time time = new Time("Time" + i);
            for (int j = 0; j < (21 - i); j++) {
                time.registrarVitoria(2, 0);
            }
            times.add(time);
        }
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        List<Time> rebaixados = tabela.getRebaixados();
        
        assertEquals(4, rebaixados.size());
        assertEquals("Time17", rebaixados.get(0).getNome());
        assertEquals("Time20", rebaixados.get(3).getNome());
    }

    @Test
    @DisplayName("Deve lidar com tabela com menos de 20 times")
    public void testTabelaComMenosTimes() {
        Time time1 = new Time("Time1");
        time1.registrarVitoria(3, 0);
        
        Time time2 = new Time("Time2");
        time2.registrarEmpate(1, 1);
        
        times.add(time1);
        times.add(time2);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        
        assertEquals(2, tabela.getClassificadosLibertadores().size());
        assertEquals(0, tabela.getClassificadosSulAmericana().size());
        assertEquals(0, tabela.getRebaixados().size());
    }

    @Test
    @DisplayName("Deve gerar toString formatado com cabeçalho")
    public void testToString() {
        Time time = new Time("Flamengo");
        time.registrarVitoria(3, 1);
        times.add(time);
        
        TabelaClassificacao tabela = new TabelaClassificacao(times);
        String resultado = tabela.toString();
        
        assertTrue(resultado.contains("TABELA DE CLASSIFICAÇÃO"));
        assertTrue(resultado.contains("Flamengo"));
        assertTrue(resultado.contains("Pos"));
    }
}
