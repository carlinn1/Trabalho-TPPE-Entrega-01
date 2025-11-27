package br.unb.tppe.brasileirao;

/**
 * Classe que representa um time de futebol no campeonato.
 */
public class Time {
    private String nome;
    private Estatisticas estatisticas;

    public Time(String nome) {
        this.nome = nome;
        this.estatisticas = new Estatisticas();
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return estatisticas.getPontos();
    }

    public int getVitorias() {
        return estatisticas.getVitorias();
    }

    public int getEmpates() {
        return estatisticas.getEmpates();
    }

    public int getDerrotas() {
        return estatisticas.getDerrotas();
    }

    public int getGolsMarcados() {
        return estatisticas.getGolsMarcados();
    }

    public int getGolsSofridos() {
        return estatisticas.getGolsSofridos();
    }

    public int getSaldoGols() {
        return estatisticas.getSaldoGols();
    }

    public int getJogos() {
        return estatisticas.getJogos();
    }

    /**
     * Registra uma vitória para o time.
     */
    public void registrarVitoria(int golsMarcados, int golsSofridos) {
        estatisticas.registrarVitoria(golsMarcados, golsSofridos);
    }

    /**
     * Registra um empate para o time.
     */
    public void registrarEmpate(int golsMarcados, int golsSofridos) {
        estatisticas.registrarEmpate(golsMarcados, golsSofridos);
    }

    /**
     * Registra uma derrota para o time.
     */
    public void registrarDerrota(int golsMarcados, int golsSofridos) {
        estatisticas.registrarDerrota(golsMarcados, golsSofridos);
    }

    @Override
    public String toString() {
            return String.format("%s - P:%d V:%d E:%d D:%d GM:%d GS:%d SG:%d",
                nome, getPontos(), getVitorias(), getEmpates(), getDerrotas(), 
                getGolsMarcados(), getGolsSofridos(), getSaldoGols());
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
