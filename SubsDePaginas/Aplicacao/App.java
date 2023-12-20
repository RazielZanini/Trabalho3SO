package Aplicacao;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import java.util.List;
import Classes.Fifo;
import Classes.LRU;
import Classes.Otimo;
import Grafico.Grafico;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Numero de molduras: ");
        int numQuadros = in.nextInt();
        System.out.println("Referencia dos Processos: ");
        String ref = in.next();
        String[] stringRef = ref.split(";");

        System.out.println("Simulação Algoritmo FIFO");
        System.out.println(" ");
        // FIFO
        Fifo fifo = new Fifo(numQuadros);

        for (int i = 0; i <= stringRef.length - 1; i++) {
            fifo.inserir(stringRef[i]);
        }

        int fifoPageFaults = fifo.getPageFaultsCount();
        System.out.println(fifoPageFaults);

        System.out.println("Simulação Algoritmo LRU");
        System.out.println(" ");
        // LRU
        LRU lru = new LRU(numQuadros);

        for (int i = 0; i <= stringRef.length - 1; i++) {
            lru.inserir(stringRef[i]);
        }

        int lruPageFaults = lru.getPageFaultsCount();
        System.out.println(lruPageFaults);

        System.out.println("Simulação Algoritmo Otimo");
        System.out.println(" ");
        // Otimo
        List<String> listaOtimo = new ArrayList<>();

        for (int i = 0; i <= stringRef.length - 1; i++) {
            listaOtimo.add(stringRef[i]);
        }

        Otimo otimo = new Otimo(numQuadros, listaOtimo);
        otimo.inserir();

        int otimoPageFaults = otimo.getPageFaultsCount();
        System.out.println(otimoPageFaults);

        double total = fifoPageFaults + lruPageFaults + otimoPageFaults;
        double fifoPercent = (fifoPageFaults / total) * 100;
        double lruPercent = (lruPageFaults / total) * 100;
        double otimoPercent = (otimoPageFaults / total) * 100;

        System.out.println(fifoPercent);
        System.out.println(lruPercent);
        System.out.println(otimoPercent);

        Grafico grafico = new Grafico(fifoPercent, lruPercent, otimoPercent);
        grafico.setSize(800, 600);
        grafico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grafico.setVisible(true);

        in.close();
    }
}