package sudoku;

import sudoku.core.ApplicationFramework;
import sudoku.core.Gui;
import sudoku.core.Repository;
import sudoku.gui.MainFrame;
import sudoku.repository.RepositoryImpl;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private AppCore(){

    }

    public static AppCore getInstance(){
        if(instance==null){
            instance = new AppCore();
        }
        return instance;
    }


    public void run(){

        this.gui.started();
    }

    public static void main(String[] args) {
        Repository repository = new RepositoryImpl();
        Gui gui = new MainFrame();
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialise(gui, repository);
        appCore.run();

    }




}

