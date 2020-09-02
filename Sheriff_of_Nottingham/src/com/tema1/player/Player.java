package com.tema1.player;

import com.tema1.goods.Goods;

import java.util.List;

public abstract class Player {
    private int suma;
    private int[] carti;
    private String name;
    private int id;
    private int[] carti_alese;
    private int carte_declarata;
    private int frecventa;
    private int[] taraba;
    private int mita;


    public Player() {
    suma = 80;
    int[] a = new int[10];
    for(int i = 0; i < 10; i++)
        a[i] = 0;
    carti = a;
    carte_declarata = -1;
    int[] b = new int[8];
    for(int i = 0; i < 8; i++)
        b[i] = -1;
    carti_alese = b;
    frecventa = -1;
    id = -1;
    taraba = new int[25];
    mita = 0;
    }

    public int getMita(){
        return mita;
    }

    public void setMita(int x) {
        mita = x;
    }

    public String getName(){
        return name;
    }
    public int[] getTaraba(){ return taraba; }

    public void setTaraba(int index) { taraba[index]++; }

    public void setId(int id) {this.id = id;}

    public int getId(){ return id; }

    public int[] getCarti_alese(){ return carti_alese; }

    public void setCarti_alese(int[] a){ carti_alese = a; }

    public void setCarte_declarata(int c) { carte_declarata = c; }

    public int getCarte_declarata(){ return carte_declarata; }

    public void setFrecventa(int f){ frecventa = f; }

    public int getFrecventa(){ return frecventa; }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarti(int[] carti) {
        this.carti = carti;
    }

    public void setSuma(int suma){
        this.suma = suma;
    }

    public int getSuma(){
        return suma;
    }

    public int[] getCarti(){
        return carti;
    }

    public void trageCarti(List<Integer> c, int index) {
        for(int i = index; i < index+10; i++){
            carti[i%10] = c.get(i);
        }
    }

    public void afisare(){
        System.out.println("afisam lista de carti a jucatorului:");
        for(int i = 0 ; i <=9 ; i++)
            System.out.println(carti[i]);
    }
    public void afisareCarti(){
        for(int i = 0; i < 10; i++)
            System.out.print(carti[i]+" ");
        System.out.println();
    }
    public abstract void creareSac(Goods[] bunuri,int runda);

    public abstract void serif(Player p, int nr_juc);

    public void afisareJucator(){
        System.out.println("jucatorul "+ getId() + " are suma "+ getSuma());
    }

    public void adaugareProfit(Goods[] bunuri){
        for(int i = 0; i <= 9; i++)
            suma = suma + getTaraba()[i]*bunuri[i].getProfit();
        for(int i = 20; i <= 24; i++)
            suma = suma + getTaraba()[i]*bunuri[i-10].getProfit();
    }

}
