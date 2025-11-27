package br.unb.tppe.brasileirao;

/**
 * Classe que representa as estatísticas de um time.
 */
public class Estatisticas {
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    public Estatisticas() {
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
    }

    public int getPontos() { return pontos; }
    public int getVitorias() { return vitorias; }
    public int getEmpates() { return empates; }
    public int getDerrotas() { return derrotas; }
    public int getGolsMarcados() { return golsMarcados; }
    public int getGolsSofridos() { return golsSofridos; }
    public int getSaldoGols() { return golsMarcados - golsSofridos; }
    public int getJogos() { return vitorias + empates + derrotas; }

    public void registrarVitoria(int gm, int gs) {
        vitorias++;
        pontos += 3;
        golsMarcados += gm;
        golsSofridos += gs;
    }
    public void registrarEmpate(int gm, int gs) {
        empates++;
        pontos += 1;
        golsMarcados += gm;
        golsSofridos += gs;
    }
    public void registrarDerrota(int gm, int gs) {
        derrotas++;
        golsMarcados += gm;
        golsSofridos += gs;
    }
}