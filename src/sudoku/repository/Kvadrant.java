package sudoku.repository;

import java.util.ArrayList;
import java.util.List;

public class Kvadrant implements  Comparable<Kvadrant>{
    private int gI;
    private int gJ;
    private int dI;
    private int dJ;
    private List<Polje>poljaUKvadrantu;

    public Kvadrant(int gI, int gJ, int dI, int dJ) {
        this.gI = gI;
        this.gJ = gJ;
        this.dI = dI;
        this.dJ = dJ;
        this.poljaUKvadrantu=new ArrayList<>();
    }

    public boolean isUKvadrantu(Polje polje){
        if(this.gI<=polje.getiKord()&& this.gJ<=polje.getjKord()
                && this.dI>=polje.getiKord() && this.dJ>=polje.getjKord())
            return  true;
        return  false;
    }

    public boolean isUKvadrantu(int i,int j){
        if(gI<=i && gJ<= j && dI>= i && dJ>= j)
            return  true;
        return  false;
    }

    public int getgI() {
        return gI;
    }

    public int getgJ() {
        return gJ;
    }

    public int getdI() {
        return dI;
    }

    public int getdJ() {
        return dJ;
    }

    @Override
    public int compareTo(Kvadrant o) {
        if(o instanceof Kvadrant){
            Kvadrant k=(Kvadrant)o;
            if(k.getgI()==this.gI && k.getgJ()==this.gJ && k.getdI()==this.dI && k.getdJ()==this.dJ)
                return  1;
            return -1;
        }

        return 0;
    }
    public  void dodajUkvadrant(Polje polje){
        poljaUKvadrantu.add(polje);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kvadrant)) return false;
        Kvadrant kvadrant = (Kvadrant) o;
        return gI == kvadrant.gI &&
                gJ == kvadrant.gJ &&
                dI == kvadrant.dI &&
                dJ == kvadrant.dJ;
    }

    public List<Polje> getPoljaUKvadrantu() {
        return poljaUKvadrantu;
    }

    @Override
    public String toString() {
        return "Kvadrant{" +
                "gI=" + gI +
                ", gJ=" + gJ +
                ", dI=" + dI +
                ", dJ=" + dJ +
                '}';
    }
}
