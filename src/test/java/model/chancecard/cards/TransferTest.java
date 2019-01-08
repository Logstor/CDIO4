package model.chancecard.cards;

import model.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransferTest {


    @Test
    public void getMoneyTransferred() {
        Transfer transfer = new Transfer("transfer","lol","lol",5);

        assertEquals(5,transfer.getMoneyTransferred());
    }

    @Test
    public void setMoneyTransferred() {
        Transfer transfer = new Transfer("transfer","lol","lol",2);
        transfer.setMoneyTransferred(400);
        assertEquals(400,transfer.getMoneyTransferred());
    }

    @Test
    public void action() {

        Player player = new Player("transfer","jens",200,1);
        int money = player.getAccount().getBalance();
        Transfer transer = new Transfer( "transfer","hej", "lol", 200);

        transer.action(player);
        assertEquals(400, player.getAccount().getBalance());


    }
}