package br.unb.tppe.brasileirao;

import java.util.*;

/**
 * Classe principal que gerencia o campeonato brasileiro.
 */
public class Campeonato {
    private List<Time> times;
    private List<Rodada> rodadas;
    private int rodadaAtual;

    public Campeonato(List<Time> times) {
        this.times = new ArrayList<>(times);
        this.rodadas = new ArrayList<>();
        this.rodadaAtual = 0;
    }

    public List<Time> getTimes() {
        return new ArrayList<>(times);
    }

    public List<Rodada> getRodadas() {
        return new ArrayList<>(rodadas);
    }

    public int getRodadaAtual() {
        return rodadaAtual;
    }

    /**
     * Sorteia as partidas de uma rodada garantindo que:
     * - Cada time joga apenas uma vez na rodada
     * - Todos os times jogam
     */
    public Rodada sortearRodada() {
        rodadaAtual++;
        Rodada rodada = new Rodada(rodadaAtual);
        
        List<Time> timesDisponiveis = new ArrayList<>(times);
        Collections.shuffle(timesDisponiveis);

        // Cria partidas emparelhando os times
        for (int i = 0; i < timesDisponiveis.size(); i += 2) {
            Time mandante = timesDisponiveis.get(i);
            Time visitante = timesDisponiveis.get(i + 1);
            
            Partida partida = new Partida(mandante, visitante);
            rodada.adicionarPartida(partida);
        }

        rodadas.add(rodada);
        return rodada;
    }

    /**
     * Verifica se existe duplicidade de confrontos entre todas as rodadas.
     * Retorna true se NÃO houver duplicidade.
     */
    public boolean semDuplicidadeConfrontos() {
        List<Partida> todasPartidas = new ArrayList<>();
        
        for (Rodada rodada : rodadas) {
            for (Partida partida : rodada.getPartidas()) {
                // Verifica se já existe uma partida igual
                for (Partida partidaExistente : todasPartidas) {
                    if (partida.mesmaPartida(partidaExistente)) {
                        return false;
                    }
                }
                todasPartidas.add(partida);
            }
        }
        return true;
    }

    /**
     * Obtém a classificação atual dos times.
     * Critérios de desempate:
     * 1. Número de pontos
     * 2. Número de vitórias
     * 3. Saldo de gols
     * 4. Gols marcados
     */
    public List<Time> getClassificacao() {
        List<Time> classificacao = new ArrayList<>(times);
        
        Collections.sort(classificacao, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                // 1. Pontos (decrescente)
                if (t1.getPontos() != t2.getPontos()) {
                    return Integer.compare(t2.getPontos(), t1.getPontos());
                }
                
                // 2. Vitórias (decrescente)
                if (t1.getVitorias() != t2.getVitorias()) {
                    return Integer.compare(t2.getVitorias(), t1.getVitorias());
                }
                
                // 3. Saldo de gols (decrescente)
                if (t1.getSaldoGols() != t2.getSaldoGols()) {
                    return Integer.compare(t2.getSaldoGols(), t1.getSaldoGols());
                }
                
                // 4. Gols marcados (decrescente)
                if (t1.getGolsMarcados() != t2.getGolsMarcados()) {
                    return Integer.compare(t2.getGolsMarcados(), t1.getGolsMarcados());
                }
                
                // Se ainda empatar, mantém ordem alfabética
                return t1.getNome().compareTo(t2.getNome());
            }
        });
        
        return classificacao;
    }

    /**
     * Exibe a tabela de classificação.
     */
    public void exibirClassificacao() {
        List<Time> classificacao = getClassificacao();
        System.out.println("\n===== CLASSIFICAÇÃO =====");
        System.out.println("Pos | Time                | P  | V  | E  | D  | GM | GS | SG");
        System.out.println("--------------------------------------------------------------------");
        
        for (int i = 0; i < classificacao.size(); i++) {
            Time time = classificacao.get(i);
            System.out.printf("%2d  | %-19s | %2d | %2d | %2d | %2d | %2d | %2d | %+3d\n",
                    i + 1, time.getNome(), time.getPontos(), time.getVitorias(),
                    time.getEmpates(), time.getDerrotas(), time.getGolsMarcados(),
                    time.getGolsSofridos(), time.getSaldoGols());
        }
        System.out.println();
    }

    /**
     * Busca um time pelo nome.
     */
    public Time buscarTime(String nome) {
        for (Time time : times) {
            if (time.getNome().equals(nome)) {
                return time;
            }
        }
        return null;
    }
}
