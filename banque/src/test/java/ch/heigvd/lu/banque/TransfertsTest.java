package ch.heigvd.lu.banque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransfertsTest {
    @Test
    public void test1() {
        //1000 exécutions de la class Transfert
       for(int i=0;i<1000;i++){
           Transferts tf=new Transferts();
           tf.start();//1000 transfert realisé pour chaque thread
       }
        assertTrue(Transferts.getConsistent());
    }
}
