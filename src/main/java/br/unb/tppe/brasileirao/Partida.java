package br.unb.tppe.brasileirao;

/**
 * Classe que representa uma partida entre dois times.
 */
public class Partida {
    private Time mandante;
    private Time visitante;
    private Integer golsMandante;
    private Integer golsVisitante;
    private boolean realizada;

    public Partida(Time mandante, Time visitante) {
        this.mandante = mandante;
        this.visitante = visitante;
        this.realizada = false;
    }

    public Time getMandante() {
        return mandante;
    }

    public Time getVisitante() {
        return visitante;
    }

    public Integer getGolsMandante() {
        return golsMandante;
    }

    public Integer getGolsVisitante() {
        return golsVisitante;
    }

    public boolean isRealizada() {
        return realizada;
    }

    /**
     * Registra o resultado da partida e atualiza as estatísticas dos times.
     */
    public void registrarResultado(int golsMandante, int golsVisitante) {
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.realizada = true;

        if (golsMandante > golsVisitante) {
            // Vitória do mandante
            mandante.registrarVitoria(golsMandante, golsVisitante);
            visitante.registrarDerrota(golsVisitante, golsMandante);
        } else if (golsMandante < golsVisitante) {
            // Vitória do visitante
            mandante.registrarDerrota(golsMandante, golsVisitante);
            visitante.registrarVitoria(golsVisitante, golsMandante);
        } else {
            // Empate
            mandante.registrarEmpate(golsMandante, golsVisitante);
            visitante.registrarEmpate(golsVisitante, golsMandante);
        }
    }

    @Override
    public String toString() {
        if (realizada) {
            return String.format("%s %d x %d %s", 
                    mandante.getNome(), golsMandante, golsVisitante, visitante.getNome());
        } else {
            return String.format("%s x %s", mandante.getNome(), visitante.getNome());
        }
    }

    /**
     * Verifica se esta partida é igual a outra (mesmo mandante e visitante).
     */
    public boolean mesmaPartida(Partida outra) {
        return this.mandante.equals(outra.mandante) && 
               this.visitante.equals(outra.visitante);
    }
}
