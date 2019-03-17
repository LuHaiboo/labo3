package ch.heigvd.lu.lecteursredacteurs;

public class Lecteur extends Thread {
    private boolean iswaiting=true;
    private Controleur controleur;

    public Lecteur(Controleur controleur) {
    this.controleur=controleur;
    }

   public void startRead(){
       controleur.lecteur=true;
       iswaiting=true;
        this.start();
    }

   public boolean isWaiting(){
       return iswaiting;
   }

    public void stopRead(){
       synchronized (controleur){
        controleur.lecteur=false;
           if(controleur.redacteurWaiting>0)
              Redacteur.class.notifyAll();
           else this.notifyAll();
           }
    }

    public void run() {

            while(controleur.redacteurWaiting>0||controleur.redacteur) {
                synchronized (controleur) {
                    iswaiting = true;
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            controleur.lecteur=true;
            iswaiting =false;
    }
}
