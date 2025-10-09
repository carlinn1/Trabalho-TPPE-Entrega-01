package br.unb.tppe.campeonato;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa a tabela de classificação do campeonato.
 * Ordena os times de acordo com os critérios de desempate.
 */
public class TabelaClassificacao {
    private List<Time> times;

    public TabelaClassificacao(List<Time> times) {
        this.times = new ArrayList<>(times);
        ordenarTabela();
    }

    /**
     * Ordena a tabela de classificação seguindo os critérios:
     * 1. Pontos (maior)
     * 2. Vitórias (maior)
     * 3. Saldo de gols (maior)
     * 4. Gols marcados (maior)
     */
    private void ordenarTabela() {
        times.sort(Comparator
                .comparing(Time::getPontos)
                .thenComparing(Time::getVitorias)
                .thenComparing(Time::getSaldoDeGols)
                .thenComparing(Time::getGolsMarcados)
                .reversed()
                .thenComparing(Time::getNome));
    }

    public List<Time> getClassificacao() {
        return new ArrayList<>(times);
    }

    public int getPosicao(Time time) {
        return times.indexOf(time) + 1;
    }

    public List<Time> getClassificadosLibertadores() {
        return times.subList(0, Math.min(6, times.size()));
    }

    public List<Time> getClassificadosSulAmericana() {
        if (times.size() < 7) return new ArrayList<>();
        return times.subList(6, Math.min(12, times.size()));
    }

    public List<Time> getRebaixados() {
        if (times.size() < 4) return new ArrayList<>();
        return times.subList(Math.max(0, times.size() - 4), times.size());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("      TABELA DE CLASSIFICAÇÃO\n");
        sb.append("========================================\n");
        sb.append(String.format("%-3s %-20s %3s %3s %3s %3s %3s %3s %4s\n",
                "Pos", "Time", "P", "J", "V", "E", "D", "GP", "GC", "SG"));
        sb.append("========================================\n");

        for (int i = 0; i < times.size(); i++) {
            Time time = times.get(i);
            sb.append(String.format("%-3d %-20s %3d %3d %3d %3d %3d %3d %4d %4d\n",
                    i + 1,
                    time.getNome(),
                    time.getPontos(),
                    time.getJogos(),
                    time.getVitorias(),
                    time.getEmpates(),
                    time.getDerrotas(),
                    time.getGolsMarcados(),
                    time.getGolsSofridos(),
                    time.getSaldoDeGols()));
        }
        sb.append("========================================\n");
        return sb.toString();
    }
}
