package iut.dam.powerhomme2;

import java.util.ArrayList;

public class Habitat {

    private String nom;
    private int etage;
    private ArrayList<String> equipement;

    public Habitat(String nom, int etage, ArrayList<String> equipement) {
        this.nom = nom;
        this.etage = etage;
        this.equipement = equipement;
    }

    public String getNom() {
        return nom;
    }

    public int getEtage() {
        return etage;
    }

    public ArrayList<String> getEquipement() {
        return equipement;
    }

    public int getNombreEquipement() {
        return equipement.size();
    }

    public void addEquipement(String nom) {
        equipement.add(nom);
    }
}

