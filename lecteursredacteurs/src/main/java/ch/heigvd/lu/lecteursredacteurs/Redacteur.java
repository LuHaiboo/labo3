package ch.heigvd.lu.lecteursredacteurs;

public class Redacteur extends Thread {
    private boolean iswaiting=true;
    private  boolean stoped =false;
    private  Controleur controleur;
    Redacteur(Controleur c){
        controleur=c;
    }

    public void startWrite(){
        stoped =false;
        this.start();
    }

    public boolean isWaiting(){
       return this.iswaiting;
    }

    public void stopWrite(){
        synchronized (this){
            controleur.redacteur=false;
            stoped =true;
            this.notifyAll();
            Lecteur.class.notifyAll();
        }
    }
    public void write(){
        synchronized (this){

            while(controleur.lecteur||controleur.redacteur) {

                iswaiting=true;
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            controleur.redacteur=true;
            iswaiting=false;
            while(!stoped);//un seul redacture possible;
        }


    }
    public void run() {
    write();

    }
}
