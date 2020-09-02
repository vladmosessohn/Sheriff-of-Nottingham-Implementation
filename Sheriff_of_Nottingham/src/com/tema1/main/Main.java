package com.tema1.main;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.player.Basic;
import com.tema1.player.Bribed;
import com.tema1.player.Greedy;
import com.tema1.player.Player;

import java.util.List;
import java.util.Map;

public final class Main {

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        int nr_runde = gameInput.getRounds();
        List<Integer> carti = gameInput.getAssetIds();
        List<String> lista_jucatori = gameInput.getPlayerNames();
        int nr_jucatori = lista_jucatori.size();
        int nr_carti = carti.size();
        GameEngine g = new GameEngine(nr_runde, nr_jucatori, nr_carti, lista_jucatori, carti);

        GoodsFactory goods = GoodsFactory.getInstance();
        Map<Integer, Goods> allgoods = goods.getAllGoods();
        Goods[] iteme = new Goods[15];
        System.out.println();

        for(int i = 0; i <= 9; i++) {
            iteme[i] = goods.getGoodsById(i);
        }




        for(int i = 20; i <= 24; i++) {
            iteme[i-10] = goods.getGoodsById(i);
        }


       /* Set<Map.Entry<Integer, Goods>> st = allgoods.entrySet();
        for(Map.Entry<Integer, Goods> me : st) {
            System.out.print(me.getKey() + ":");
            System.out.println(me.getValue().getProfit());
        }*/




        Player[] p = new Player[nr_jucatori];
        int index, i, j , k;
        // vedem ce tip e fiecare jucator
        for(i = 0; i < nr_jucatori; i++) {
            if (lista_jucatori.get(i).equals("bribed"))
                p[i] = new Bribed();
            if(lista_jucatori.get(i).equals("basic"))
                p[i] = new Basic();
            if(lista_jucatori.get(i).equals("greedy"))
                p[i] = new Greedy();
            p[i].setId(i);
        }

        index = 0;
        for(i = 0 ; i < nr_runde; i++)
            for(j = 0 ; j < nr_jucatori; j ++) {

                System.out.println("jucatorul "+ j + " este serif");
                // fiecare jucator trage carti

                for(k = j + 1; k < nr_jucatori; k++) {
                    System.out.print("jucatorul: " + k + " trage cartile: ");
                    p[k].trageCarti(carti, index);
                    index = index + 10;
                    p[k].creareSac(iteme, i + 1);
                    p[k].afisareCarti();
                    System.out.println(p[k].getId()+" "+p[k].getFrecventa()+" "+p[k].getCarte_declarata());
                    System.out.println("carti alese:");
                    for(int x = 0; x < p[k].getCarti_alese().length; x++)
                        System.out.print(p[k].getCarti_alese()[x]+" ");


                    p[j].serif(p[k], lista_jucatori.size());
                    System.out.println("jucatorul 0:");
                    p[0].afisareJucator();
                    System.out.println("jucatorul 1:");
                    p[1].afisareJucator();
                    System.out.println();
                    System.out.println("trecem la jucatorul urmator");
                    System.out.println();
                }

                for(k = 0;  k < j; k++){
                    System.out.print("jucatorul: " + k + " trage cartile: ");
                    p[k].trageCarti(carti, index);
                    index = index + 10;
                    p[k].creareSac(iteme, i + 1);
                    p[k].afisareCarti();
                    System.out.println(p[k].getId()+" "+p[k].getFrecventa()+" "+p[k].getCarte_declarata());
                    System.out.println("carti alese:");
                    for(int x = 0; x < p[k].getCarti_alese().length; x++) {
                        System.out.print(p[k].getCarti_alese()[x] + " ");
                    }
                    p[j].serif(p[k],lista_jucatori.size());
                    System.out.println("jucatorul 0:");
                    p[0].afisareJucator();
                    System.out.println("jucatorul 1:");
                    p[1].afisareJucator();
                    System.out.println();
                    System.out.println("trecem la jucatorul urmator");
                    System.out.println();
                }
            }
        System.out.println("printam tarabele:");
        for(Player juc : p){
            System.out.println(juc.getId());
            for(int ind = 0; ind <=24; ind++)
                System.out.print(juc.getTaraba()[ind]+" ");
            System.out.println();
        }
        for(i = 20; i <= 24; i++) {
            for (Player aux : p) {
                int freq = aux.getTaraba()[i];
                if (freq > 0) {
                    if (i == 20){
                        for(int l = 0; l < freq;l++) {
                            aux.setTaraba(1);
                            aux.setTaraba(1);
                            aux.setTaraba(1);
                        }
                    }
                    if(i == 21) {
                        for(int l = 0; l < freq;l++) {
                            aux.setTaraba(3);
                            aux.setTaraba(3);
                        }
                    }
                    if(i == 22) {
                        for(int l = 0; l < freq;l++) {
                            aux.setTaraba(2);
                            aux.setTaraba(2);
                        }
                    }
                    if(i == 23) {
                        for(int l = 0; l < freq;l++) {
                            aux.setTaraba(7);
                            aux.setTaraba(7);
                            aux.setTaraba(7);
                            aux.setTaraba(7);
                        }
                    }
                    if(i == 24) {
                        for(int l = 0; l < freq;l++) {
                            aux.setTaraba(4);
                            aux.setTaraba(4);
                            aux.setTaraba(6);
                            aux.setTaraba(6);
                            aux.setTaraba(6);
                            aux.setTaraba(3);
                        }
                    }
                }
            }
        }
        for(i = 0; i <= 9; i++)
        {
            int king = 0;
            int queen = 0;
            for(Player aux : p) {
                if(aux.getTaraba()[i] > king) {
                    king = aux.getTaraba()[i];
                } else if(aux.getTaraba()[i] > queen) {
                            queen = aux.getTaraba()[i];
                }
            }
            if(king != 0) {
                for (Player aux : p) {
                    if (aux.getTaraba()[i] == king) {
                        aux.setSuma(aux.getSuma() + iteme[i].getKingBonus());
                        break;
                    }
                }
            }
            System.out.println(king + " "+queen);
            if(king == queen && king !=0) {
                int cnt = 0;
                System.out.println(cnt);
                System.out.println();
                for(Player aux : p)
                    if (aux.getTaraba()[i] == king) {
                        cnt++;
                        if(cnt == 2) {
                            aux.setSuma(aux.getSuma() + iteme[i].getQueenBonus());
                            System.out.println("alabalaaaa");
                            break;
                        }
                    }
            }
            else if(queen != 0) {
                for (Player aux : p) {

                    if (aux.getTaraba()[i] == queen) aux.setSuma(aux.getSuma() + iteme[i].getQueenBonus());
                }
            }
        }
        for(Player aux : p) {
            aux.adaugareProfit(iteme);
        }

        for(Player aux : p) {
            System.out.print(aux.getId() + " " + aux.getSuma() + " " + aux.getName());
            System.out.println();
        }
    }
}