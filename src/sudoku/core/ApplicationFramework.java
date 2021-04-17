package sudoku.core;

public abstract  class ApplicationFramework {
    protected Gui gui;
    protected Repository repository;

    public ApplicationFramework() {
    }

    public abstract void run();
    public void initialise(Gui gui, Repository repository){
        this.gui = gui;
        this.repository = repository;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
