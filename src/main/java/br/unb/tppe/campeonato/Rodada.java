package br.unb.tppe.campeonato;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma rodada do campeonato.
 * Contém a lista de partidas da rodada.
 */
public class Rodada {
    private int numero;
    private List<Partida> partidas;

    public Rodada(int numero) {
        this.numero = numero;
        this.partidas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public List<Partida> getPartidas() {
        return new ArrayList<>(partidas);
    }

    public void adicionarPartida(Partida partida) {
        if (partida == null) {
            throw new IllegalArgumentException("Partida não pode ser nula");
        }
        partidas.add(partida);
    }

    public boolean todasPartidasRealizadas() {
        return partidas.stream().allMatch(Partida::isRealizada);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rodada ").append(numero).append(":\n");
        for (Partida partida : partidas) {
            sb.append("  ").append(partida).append("\n");
        }
        return sb.toString();
    }
}
