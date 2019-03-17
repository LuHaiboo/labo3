package ch.heigvd.lu.lecteursredacteurs;

public class Lecteur extends Thread {
    private boolean iswaiting=true;
    private boolean stoped=false;
    private Controleur controleur;

    public Lecteur(Controleur controleur) {
    this.controleur=controleur;
    }

   public void startRead(){
       controleur.lecteur=true;
       iswaiting=true;
       stoped=false;
       this.start();
    }

   public boolean isWaiting(){
       return iswaiting;
   }

    public void stopRead(){
      synchronized (this){
        controleur.lecteur=false;
        stoped=true;
          //Redacteur.class.notifyAll();
          this.notifyAll();
          }
    }
    public void read() {
        synchronized (this){
            while (controleur.redacteur) {
            iswaiting = true;
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        iswaiting = false;
        controleur.lecteur = true;
    }
        while(!stoped);//plusieueurs lecture possible;

    }
    public void run() {
           read();
    }
}
