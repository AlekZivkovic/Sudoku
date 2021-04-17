package sudoku.repository;

import sudoku.core.Repository;

public class RepositoryImpl implements Repository {
    private Teren teren;

    public RepositoryImpl() {
        this.teren=new Teren();
    }

    @Override
    public Teren getTeren() {
        return teren;
    }
}
