package Classes;

import java.util.LinkedList;
import java.util.Queue;

public class Fifo {
    private int quantMolduras;
    private static int PageFaults = 0;
    private static Queue<String> Memoria = new LinkedList<>();
    private int tempo = 0;

    public Fifo(int numQuadros) {
        this.quantMolduras = numQuadros;
    }

    public void inserir(String refString) {
        System.out.println("Entrando referencia " + refString + " no tempo " + tempo);
        // Verifica se a Memoria está cheia
        if (Memoria.size() != quantMolduras) {
            // verifica se a Memoria ja possui a referencia
            if (Memoria.contains(refString)) {
                System.out.println("Já se encontra na memória!");
                tempo++;
                return;
            } else {
                Memoria.add(refString);
                PageFaults++;
                tempo++;
            }
        }
        // Se cheia, remove a tarefa mais antiga e adiciona a nova tarefa
        else {
            if (Memoria.contains(refString)) {
                System.out.println("Já se encontra na memória!");
                tempo++;
                return;
            } else {
                Memoria.poll();
                Memoria.add(refString);
                PageFaults++;
                tempo++;
            }
        }

        System.out.println(Memoria);

    }

    public int getPageFaultsCount() {
        return PageFaults;
    }
}