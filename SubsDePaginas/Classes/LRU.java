package Classes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRU {
    private int quantMolduras;
    private static int PageFaults = 0;
    private static Queue<String> memoria = new LinkedList<>();
    private int tempo = 0;
    private Map<String, Integer> ultimaReferencia = new HashMap<>();

    public LRU(int numQuadros) {
        this.quantMolduras = numQuadros;
    }

    public void inserir(String refString) {
        // Atualiza a última referência da página
        ultimaReferencia.put(refString, tempo);

        System.out.println("Entrando referencia " + refString + " no tempo " + tempo);

        // Verifica se a Memória está cheia
        if (memoria.size() != quantMolduras) {
            // Verifica se a Memória já possui a referência
            if (memoria.contains(refString)) {
                System.out.println("Já se encontra na memória!");
            } else {
                memoria.add(refString);
                PageFaults++;
            }
        }
        // Se cheia, remove a página menos recentemente usada e adiciona a nova página
        else {
            if (memoria.contains(refString)) {
                System.out.println("Já se encontra na memória!");
            } else {
                String paginaMenosRecente = encontrarPaginaMenosRecente();
                memoria.remove(paginaMenosRecente);
                memoria.add(refString);
                PageFaults++;
            }
        }

        tempo++;
        System.out.println(memoria);
    }

    private String encontrarPaginaMenosRecente() {
        int menorTempo = Integer.MAX_VALUE;
        String paginaMenosRecente = null;

        for (String pagina : memoria) {
            int ultimaRef = ultimaReferencia.getOrDefault(pagina, 0);
            if (ultimaRef < menorTempo) {
                menorTempo = ultimaRef;
                paginaMenosRecente = pagina;
            }
        }

        return paginaMenosRecente;
    }

    public int getPageFaultsCount() {
        return PageFaults;
    }
}