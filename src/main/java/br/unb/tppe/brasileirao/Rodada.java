package br.unb.tppe.brasileirao;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma rodada do campeonato.
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

    /**
     * Adiciona uma partida à rodada.
     */
    public void adicionarPartida(Partida partida) {
        partidas.add(partida);
    }

    /**
     * Verifica se a rodada contém um time específico.
     */
    public boolean contemTime(Time time) {
        for (Partida partida : partidas) {
            if (partida.getMandante().equals(time) || partida.getVisitante().equals(time)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Conta quantas vezes um time aparece na rodada.
     */
    public int contarAparicoes(Time time) {
        int contador = 0;
        for (Partida partida : partidas) {
            if (partida.getMandante().equals(time)) {
                contador++;
            }
            if (partida.getVisitante().equals(time)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Verifica se todos os times da lista estão presentes na rodada.
     */
    public boolean todosTimes(List<Time> times) {
        for (Time time : times) {
            if (!contemTime(time)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se há repetição de times na rodada.
     */
    public boolean temRepeticaoTimes() {
        List<Time> timesVistos = new ArrayList<>();
        for (Partida partida : partidas) {
            if (timesVistos.contains(partida.getMandante()) || 
                timesVistos.contains(partida.getVisitante())) {
                return true;
            }
            timesVistos.add(partida.getMandante());
            timesVistos.add(partida.getVisitante());
        }
        return false;
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
