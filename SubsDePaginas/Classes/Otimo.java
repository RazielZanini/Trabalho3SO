package Classes;

import java.util.ArrayList;
import java.util.List;

public class Otimo {
    private int quantMolduras;
    private static int PageFaults = 0;
    private List<String> Memoria = new ArrayList<>();
    private List<String> refString;
    private int tempo = 0;

    public Otimo(int numQuadros, List<String> refString) {
        this.quantMolduras = numQuadros;
        this.refString = refString;
    }

    public void inserir() {
        for (int i = 0; i < refString.size(); i++) {
            System.out.println("Entrando referencia " + refString.get(i) + " no tempo " + tempo);
            if (!Memoria.contains(refString.get(i))) {
                if (Memoria.size() < quantMolduras) {
                    Memoria.add(refString.get(i));
                    PageFaults++;
                    tempo++;
                    System.out.println(Memoria);
                } else {
                    int farthestIndex = buscaMaisLonge(refString, i);
                    Memoria.remove(farthestIndex);
                    Memoria.add(refString.get(i));
                    PageFaults++;
                    tempo++;
                    System.out.println(Memoria);
                }
            } else {
                System.out.println("Já se encontra na memória!");
                tempo++;
            }
        }
    }

    private int buscaMaisLonge(List<String> refString, int currIndex) {
        int res = -1, farthest = currIndex;
        for (int i = 0; i < Memoria.size(); i++) {
            int j;
            for (j = currIndex; j < refString.size(); j++) {
                if (Memoria.get(i).equals(refString.get(j))) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }
            if (j == refString.size())
                return i;
        }
        return (res == -1) ? 0 : res;
    }

    public int getPageFaultsCount() {
        return PageFaults;
    }
}
