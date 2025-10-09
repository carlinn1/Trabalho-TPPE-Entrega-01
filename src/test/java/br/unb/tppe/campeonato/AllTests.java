package br.unb.tppe.campeonato;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Suíte de testes que executa todos os casos de teste do projeto.
 * 
 * Esta classe agrupa todos os testes do sistema de Campeonato Brasileiro,
 * permitindo executar todos os testes de uma só vez.
 * 
 * Funcionalidades testadas:
 * 1. TimeTest - Testes de pontuação, vitórias, empates, derrotas, gols e saldo
 * 2. PartidaTest - Testes de criação e registro de resultados de partidas
 * 3. RodadaTest - Testes de agrupamento de partidas em rodadas
 * 4. SorteioRodadasTest - Testes de sorteio e garantia de não duplicação
 * 5. TabelaClassificacaoTest - Testes de ordenação e critérios de desempate
 * 6. CampeonatoTest - Testes de integração do sistema completo
 * 7. TestesParametrizados - Testes parametrizados com múltiplos cenários
 * 
 * Para executar todos os testes:
 * - Via Maven: mvn test
 * - Via IDE: Executar esta classe
 * - Via linha de comando: mvn -Dtest=AllTests test
 */
@Suite
@SuiteDisplayName("Todos os Testes do Campeonato Brasileiro")
@SelectClasses({
    TimeTest.class,
    PartidaTest.class,
    RodadaTest.class,
    SorteioRodadasTest.class,
    TabelaClassificacaoTest.class,
    CampeonatoTest.class,
    TestesParametrizados.class
})
public class AllTests {
    // Esta classe serve apenas como holder para as anotações da suíte
    // Não é necessário adicionar código aqui
}
