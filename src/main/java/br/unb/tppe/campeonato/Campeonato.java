package br.unb.tppe.campeonato;

import java.util.*;

/**
 * Representa o Campeonato Brasileiro.
 * Gerencia times, rodadas e sorteios de partidas.
 */
public class Campeonato {
    private static final int NUM_TIMES = 20;
    private static final int NUM_RODADAS = 38;
    
    private List<Time> times;
    private List<Rodada> rodadas;
    private Set<Partida> partidasJaSorteadas;

    public Campeonato() {
        this.times = new ArrayList<>();
        this.rodadas = new ArrayList<>();
        this.partidasJaSorteadas = new HashSet<>();
    }

    public void adicionarTime(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("Time não pode ser nulo");
        }
        if (times.size() >= NUM_TIMES) {
            throw new IllegalStateException("Campeonato já possui 20 times");
        }
        if (times.contains(time)) {
            throw new IllegalArgumentException("Time já está no campeonato");
        }
        times.add(time);
    }

    public List<Time> getTimes() {
        return new ArrayList<>(times);
    }

    public List<Rodada> getRodadas() {
        return new ArrayList<>(rodadas);
    }

    public Rodada getRodada(int numero) {
        if (numero < 1 || numero > rodadas.size()) {
            throw new IllegalArgumentException("Número de rodada inválido");
        }
        return rodadas.get(numero - 1);
    }

    /**
     * Sorteia todas as rodadas do campeonato.
     * Garante que cada time jogue contra todos os outros em turno e returno.
     */
    public void sortearRodadas() {
        if (times.size() != NUM_TIMES) {
            throw new IllegalStateException("Campeonato deve ter exatamente 20 times para sortear rodadas");
        }

        partidasJaSorteadas.clear();
        rodadas.clear();

        // Algoritmo Round-Robin para sorteio
        List<Time> timesSorteio = new ArrayList<>(times);
        Collections.shuffle(timesSorteio, new Random());

        // Primeiro turno (rodadas 1-19)
        for (int rodada = 0; rodada < NUM_TIMES - 1; rodada++) {
            Rodada r = new Rodada(rodada + 1);
            for (int i = 0; i < NUM_TIMES / 2; i++) {
                Time mandante = timesSorteio.get(i);
                Time visitante = timesSorteio.get(NUM_TIMES - 1 - i);
                
                Partida partida = new Partida(mandante, visitante);
                if (!partidasJaSorteadas.contains(partida)) {
                    r.adicionarPartida(partida);
                    partidasJaSorteadas.add(partida);
                }
            }
            rodadas.add(r);
            
            // Rotacionar times (mantém o primeiro fixo)
            Time ultimo = timesSorteio.remove(NUM_TIMES - 1);
            timesSorteio.add(1, ultimo);
        }

        // Segundo turno (rodadas 20-38) - inverte mando de campo
        for (int rodada = 0; rodada < NUM_TIMES - 1; rodada++) {
            Rodada rOriginal = rodadas.get(rodada);
            Rodada rReturno = new Rodada(rodada + NUM_TIMES);
            
            for (Partida partidaIda : rOriginal.getPartidas()) {
                Partida partidaVolta = new Partida(partidaIda.getVisitante(), partidaIda.getMandante());
                if (!partidasJaSorteadas.contains(partidaVolta)) {
                    rReturno.adicionarPartida(partidaVolta);
                    partidasJaSorteadas.add(partidaVolta);
                }
            }
            rodadas.add(rReturno);
        }
    }

    /**
     * Verifica se existem partidas duplicadas no campeonato.
     */
    public boolean existemPartidasDuplicadas() {
        Set<Partida> partidas = new HashSet<>();
        for (Rodada rodada : rodadas) {
            for (Partida partida : rodada.getPartidas()) {
                if (!partidas.add(partida)) {
                    return true;
                }
            }
        }
        return false;
    }
}
