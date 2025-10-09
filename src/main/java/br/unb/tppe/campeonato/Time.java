package br.unb.tppe.campeonato;

/**
 * Representa um time do Campeonato Brasileiro.
 * Armazena informações sobre pontuação, vitórias, empates, derrotas,
 * gols marcados, gols sofridos e saldo de gols.
 */
public class Time {
    private String nome;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;
    private int jogos;

    public Time(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
        this.jogos = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public int getSaldoDeGols() {
        return golsMarcados - golsSofridos;
    }

    public int getJogos() {
        return jogos;
    }

    public void registrarVitoria(int golsMarcados, int golsSofridos) {
        this.pontos += 3;
        this.vitorias++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
        this.jogos++;
    }

    public void registrarEmpate(int golsMarcados, int golsSofridos) {
        this.pontos += 1;
        this.empates++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
        this.jogos++;
    }

    public void registrarDerrota(int golsMarcados, int golsSofridos) {
        this.derrotas++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
        this.jogos++;
    }

    @Override
    public String toString() {
        return String.format("%s - P:%d V:%d E:%d D:%d GM:%d GS:%d SG:%d",
                nome, pontos, vitorias, empates, derrotas, golsMarcados, golsSofridos, getSaldoDeGols());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time time = (Time) obj;
        return nome.equals(time.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
