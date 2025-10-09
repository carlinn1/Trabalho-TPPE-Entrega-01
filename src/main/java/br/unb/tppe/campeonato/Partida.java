package br.unb.tppe.campeonato;

/**
 * Representa uma partida entre dois times.
 * Contém informações sobre mandante, visitante e placar.
 */
public class Partida {
    private Time mandante;
    private Time visitante;
    private Integer golsMandante;
    private Integer golsVisitante;
    private boolean realizada;

    public Partida(Time mandante, Time visitante) {
        if (mandante == null || visitante == null) {
            throw new IllegalArgumentException("Times não podem ser nulos");
        }
        if (mandante.equals(visitante)) {
            throw new IllegalArgumentException("Um time não pode jogar contra si mesmo");
        }
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

    public void registrarResultado(int golsMandante, int golsVisitante) {
        if (golsMandante < 0 || golsVisitante < 0) {
            throw new IllegalArgumentException("Gols não podem ser negativos");
        }
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.realizada = true;

        // Atualizar estatísticas dos times
        if (golsMandante > golsVisitante) {
            mandante.registrarVitoria(golsMandante, golsVisitante);
            visitante.registrarDerrota(golsVisitante, golsMandante);
        } else if (golsMandante < golsVisitante) {
            mandante.registrarDerrota(golsMandante, golsVisitante);
            visitante.registrarVitoria(golsVisitante, golsMandante);
        } else {
            mandante.registrarEmpate(golsMandante, golsVisitante);
            visitante.registrarEmpate(golsVisitante, golsMandante);
        }
    }

    @Override
    public String toString() {
        if (!realizada) {
            return String.format("%s x %s (não realizada)", mandante.getNome(), visitante.getNome());
        }
        return String.format("%s %d x %d %s", mandante.getNome(), golsMandante, golsVisitante, visitante.getNome());
    }

    /**
     * Verifica se esta partida é igual a outra (mesmos times e mando de campo).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Partida partida = (Partida) obj;
        return mandante.equals(partida.mandante) && visitante.equals(partida.visitante);
    }

    @Override
    public int hashCode() {
        return mandante.hashCode() * 31 + visitante.hashCode();
    }
}
