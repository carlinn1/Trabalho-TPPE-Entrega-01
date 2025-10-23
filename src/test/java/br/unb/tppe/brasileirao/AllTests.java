package br.unb.tppe.brasileirao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de testes que executa todos os casos de teste do sistema.
 * 
 * Executa os seguintes testes:
 * 1. TestSorteioRodada - Verifica sorteios de rodadas sem repetição e com todos os times
 * 2. TestDuplicidadeConfrontos - Verifica que não há confrontos duplicados
 * 3. TestCalculoPontuacao - Verifica cálculo correto de pontuação (3-1-0)
 * 4. TestCalculoEstatisticas - Verifica cálculo de vitórias, gols e saldos
 * 5. TestDesempatePorVitorias - Verifica critério de desempate por vitórias
 */
@RunWith(Suite.class)
@SuiteClasses({
    TestSorteioRodada.class,
    TestDuplicidadeConfrontos.class,
    TestCalculoPontuacao.class,
    TestCalculoEstatisticas.class,
    TestDesempatePorVitorias.class
})
public class AllTests {
    // Esta classe permanece vazia, é usada apenas como holder para as anotações
}
