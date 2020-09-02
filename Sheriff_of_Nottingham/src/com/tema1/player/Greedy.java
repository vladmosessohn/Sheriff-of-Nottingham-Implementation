package com.tema1.player;

import com.tema1.goods.Goods;

public class Greedy extends Player   {
    public Greedy(){
        super.setName("GREEDY");
        super.setMita(0);
    }
    public void creareSac(Goods[] bunuri, int runda) {
        int[] frecv = new int[25];
        int[] carti = getCarti();
        int[] alese;
        int[] aux;
        for(int i = 0; i <=9; i++){
            ++frecv[carti[i]];
        }

        int max = -1;
        int max_id = -1;
        int max_profit = -1;

        for(int i = 0; i <= 9; i++){
            if(frecv[i] > max) max = frecv[i];
        }
        if(max > 8) max = 8;
        if(max > 0){
            alese = new int[max];
            aux = new int[max + 1];


            for(int i = 0; i <=9; i++)
                if(max == frecv[i])
                    if (max_profit < bunuri[i].getProfit())
                        max_profit = bunuri[i].getProfit();
            for(int i = 0; i <= 9; i++)
                if(max == frecv[i] && max_profit == bunuri[i].getProfit()) max_id = i;
            for(int i = 0; i < max; i++)
                alese[i] = max_id;
            for(int i = 0; i < max; i++)
                aux[i] = alese[i];
            super.setCarte_declarata(max_id);
            if(runda % 2 ==0) {
                for(int i = 20; i <= 24; i++)
                    if(frecv[i] > 0)
                        if(max_profit < bunuri[i-10].getProfit())
                            max_profit = bunuri[i-10].getProfit();
                for(int i = 20; i <= 24; i++)
                    if(max_profit == bunuri[i-10].getProfit())
                        max_id = i;
                aux[max] = max_id;
                alese = new int[max + 1];
                for(int k = 0 ; k < max + 1; k++)
                    alese[k] = aux[k];
            }
        } else {
            max = 1;
            alese = new int[1];
            for(int i = 20; i <= 24; i++)
                if(frecv[i] > 0)
                    if(max_profit < bunuri[i-10].getProfit())
                        max_profit = bunuri[i-10].getProfit();
            for(int i = 20; i <= 24; i++)
                if(max_profit == bunuri[i-10].getProfit())
                    max_id = i;
            alese[0] = max_id;
            super.setCarte_declarata(0);
        }
        super.setFrecventa(max);
        super.setCarti_alese(alese);
    }
    public void serif(Player p, int nr_juc) {
        if(super.getSuma() > 15) {
            if (p.getMita() > 0) {
                super.setSuma(getSuma() + p.getMita());
                p.setSuma(p.getSuma() - p.getMita());
                for (int i = 0; i < p.getCarti_alese().length; i++)
                    p.setTaraba(p.getCarti_alese()[i]);
            } else {
                int flag = 0;
                int penalty = 0;
                for (int i = 0; i < p.getCarti_alese().length; i++)
                    if (p.getCarti_alese()[i] != p.getCarte_declarata()) {
                        flag = 1;
                        if (p.getCarti_alese()[i] >= 20 && p.getCarti_alese()[i] <= 24) penalty = penalty + 4;
                        else penalty = penalty + 2;
                    }
                if (flag == 0) {
                    penalty = p.getFrecventa() * 2;
                    this.setSuma(getSuma() - penalty);
                    p.setSuma(p.getSuma() + penalty);
                    for (int i = 0; i < p.getCarti_alese().length; i++) {
                        p.setTaraba(p.getCarti_alese()[i]);
                        System.out.println("punem in taraba");
                    }
                } else {
                    this.setSuma(getSuma() + penalty);
                    p.setSuma(p.getSuma() - penalty);
                    for (int i = 0; i < p.getCarti_alese().length; i++)
                        if (p.getCarti_alese()[i] == p.getCarte_declarata())
                            p.setTaraba(p.getCarti_alese()[i]);
                }
            }
        } else {
            {
                for(int i = 0; i < p.getCarti_alese().length; i++)
                    p.setTaraba(p.getCarti_alese()[i]);
            }
        }
    }
}
