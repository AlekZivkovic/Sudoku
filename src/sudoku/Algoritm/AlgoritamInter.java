package sudoku.Algoritm;


public abstract class AlgoritamInter {
    private int posBrZaSol;


    public AlgoritamInter() {

    }

    public abstract Object pocni();

    public int getPosBrZaSol() {
        return posBrZaSol;
    }

    public void setPosBrZaSol(int posBrZaSol) {
        this.posBrZaSol = posBrZaSol;
    }
}

