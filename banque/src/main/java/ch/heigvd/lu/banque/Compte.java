package ch.heigvd.lu.banque;

public class Compte {
    private int numero;
    private int montant;

    public Compte(int numero, int montant) {
        this.numero = numero;
        this.montant = montant;
    }

    public int getNumero() {
        return numero;
    }

    public int getMontant() {
        return montant;
    }

    public boolean debit(int valeur) {
        //synchronized sur compte mais pas sur banque pendant debit
        synchronized (this) {
            if (montant - valeur >= 0) {
                this.montant -= valeur;
                return true;
            } else {
                return false;
            }
        }
    }

    public void credit(int valeur) {
        //synchronized sur compte mais pas sur banque pendant credit
        synchronized (this) {
            this.montant += valeur;
        }
    }

}