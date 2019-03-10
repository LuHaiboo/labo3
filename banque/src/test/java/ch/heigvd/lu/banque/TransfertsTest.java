package ch.heigvd.lu.banque;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransfertsTest {
    @Test
    public void test1() {
       for(int i=0;i<1000;i++){
           Transferts tf=new Transferts();
           tf.start();
           for(int j=0;j<1000;j++){
               tf.run();
           }
       }
        assertTrue(Transferts.getConsistent());
    }
}
