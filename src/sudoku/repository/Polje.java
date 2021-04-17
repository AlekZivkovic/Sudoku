package sudoku.repository;

public class Polje {
    private int broj;
    private boolean posedujeBroj;
    private Koordinata koordinate;
    private Kvadrant kvadrant;

     public class Koordinata {
        private int i;
        private int j;

        public Koordinata(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Koordinata)) return false;
            Koordinata that = (Koordinata) o;
            return i == that.i &&
                    j == that.j;
        }
    }




    public Polje(int iKord,int jKord) {
        this.koordinate=new Koordinata(iKord,jKord);
        this.posedujeBroj=false;

    }

    public void dodajBroj(int broj){

        this.broj=broj;
        this.posedujeBroj=true;
        System.out.println("dodata vrednost " + broj + this);
    }

    public int getBroj() {
        return broj;
    }

    public boolean isPosedujeBroj() {
        return posedujeBroj;
    }

    public int getiKord() {
        return koordinate.getI();
    }

    public int getjKord() {
        return koordinate.getJ();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof  Polje){{
            Polje p=(Polje)o;
            return p.getiKord()==this.getiKord() && p.getjKord()==this.getjKord();
        }}
        else return  false;
    }

    public Koordinata getKoordinate() {
        return koordinate;
    }

    @Override
    public String toString() {
        return  "["+getiKord()+", "+getjKord()+"]";

    }


    public Kvadrant getKvadrant() {
        return kvadrant;
    }

    public void setPosedujeBroj(boolean posedujeBroj) {
        this.posedujeBroj = posedujeBroj;
    }

    public void setKvadrant(Kvadrant kvadrant) {
        this.kvadrant = kvadrant;
    }
}


