package br.unb.tppe.brasileirao;

/**
 * Classe que representa um time de futebol no campeonato.
 */
public class Time {
    private String nome;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    public Time(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
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

    public int getSaldoGols() {
        return golsMarcados - golsSofridos;
    }

    public int getJogos() {
        return vitorias + empates + derrotas;
    }

    /**
     * Registra uma vitória para o time.
     */
    public void registrarVitoria(int golsMarcados, int golsSofridos) {
        this.vitorias++;
        this.pontos += 3;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
    }

    /**
     * Registra um empate para o time.
     */
    public void registrarEmpate(int golsMarcados, int golsSofridos) {
        this.empates++;
        this.pontos += 1;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
    }

    /**
     * Registra uma derrota para o time.
     */
    public void registrarDerrota(int golsMarcados, int golsSofridos) {
        this.derrotas++;
        this.golsMarcados += golsMarcados;
        this.golsSofridos += golsSofridos;
    }

    @Override
    public String toString() {
        return String.format("%s - P:%d V:%d E:%d D:%d GM:%d GS:%d SG:%d",
                nome, pontos, vitorias, empates, derrotas, 
                golsMarcados, golsSofridos, getSaldoGols());
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
