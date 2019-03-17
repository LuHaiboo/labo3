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
        synchronized (controleur){
            controleur.redacteur=false;
           if(--controleur.redacteurWaiting>0)
            this.notifyAll();
            else Lecteur.class.notifyAll();
        }
    }

    public void run() {

        while(controleur.lecteur||controleur.redacteur) {

        synchronized (controleur) {

            iswaiting=true;
           try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          }
        }
        controleur.redacteur=true;
        iswaiting=false;
    }
}
