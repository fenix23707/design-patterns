package by.vsu.kovzov.games;

import by.vsu.kovzov.desk.StandardCardDesk;
import by.vsu.kovzov.models.Card;

public class MonteCarlo {
    private StandardCardDesk cardDesk;

    private MonteCarloLayout layout;

    public MonteCarlo() {
        init();
    }

    public void init() {
        this.cardDesk = new StandardCardDesk();
        this.layout = new MonteCarloLayout();
    }



}
