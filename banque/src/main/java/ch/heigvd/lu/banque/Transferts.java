package ch.heigvd.lu.banque;

import java.util.Random;

public class Transferts extends Thread{
    private static  Banque banque=new Banque(10);
    private static Random random=new Random();
    public static boolean getConsistent(){
    return banque.consistent();
}
    public static void main(String[] args) {
        Transferts t1 = new Transferts();
        t1.start();
    }
    public void run() {
        // Thread.sleep(10); //suspension de 1 ms

        //chaque thread realise 1000 transferts
        for(int i =0; i< 1000;i++) {
            int debit = random.nextInt(banque.getNbComptes());
            int credit = debit;
            while (credit == debit)//pas autoriseé de trnsferer entre la même compte
                  credit = random.nextInt(banque.getNbComptes());
             int montant = random.nextInt();
             //synchronized () se trouve  en class <<compte>>,pas ici
            banque.transfert(debit, credit, montant);
        }
    }
}