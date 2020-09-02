package com.tema1.main;

import java.util.List;

public class GameEngine {
    int nr_runde;
    int numar_jucatori;
    List<String> lista_jucatori;
    List<Integer> carti;
    int nr_carti;
    GameEngine(int nr_runde, int numar_jucatori, int nr_carti, List<String> lista_jucatori, List<Integer> carti){
        this.nr_runde = nr_runde;
        this.numar_jucatori = numar_jucatori;
        this.lista_jucatori = lista_jucatori;
        this.carti = carti;
        this.nr_carti = nr_carti;
    }

    void afisare(){
        System.out.println(nr_runde+" "+numar_jucatori);
        System.out.println(lista_jucatori);
    }


}