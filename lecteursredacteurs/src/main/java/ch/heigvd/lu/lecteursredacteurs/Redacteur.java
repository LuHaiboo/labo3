package ch.heigvd.lu.lecteursredacteurs;

public class Redacteur extends Thread {
    private boolean iswaiting=true;
    private  Controleur controleur;
    Redacteur(Controleur c){
        controleur=c;
    }

    public void startWrite(){
        controleur.redacteurWaiting++;
        this.start();
    }

    public boolean isWaiting(){
       return this.iswaiting;
    }

    public void stopWrite(){
        synchronized (this){
           if(--controleur.redacteurWaiting>0)
            this.notifyAll();
            else Lecteur.class.notifyAll();
        }
    }

    public void run() {
        synchronized (this) {
        while(controleur.lecteur) {
            iswaiting=true;
          /* try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
          }
        iswaiting=false;
        }
    }
}
