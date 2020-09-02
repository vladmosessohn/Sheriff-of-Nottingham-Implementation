package com.tema1.player;

import com.tema1.goods.Goods;

public class Bribed extends Player {

    public Bribed(){
        super.setName("BRIBED");
    }
    public void creareSac(Goods[] bunuri, int runda){
        if(getSuma() <= 2) {
            int[] frecv = new int[25];
            int[] carti = getCarti();
            int[] alese;

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
                for(int i = 0; i <=9; i++)
                    if(max == frecv[i])
                        if (max_profit < bunuri[i].getProfit())
                            max_profit = bunuri[i].getProfit();
                for(int i = 0; i <= 9; i++)
                    if(max == frecv[i] && max_profit == bunuri[i].getProfit()) max_id = i;
                for(int i = 0; i < max; i++)
                    alese[i] = max_id;
                super.setCarte_declarata(max_id);
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
        } else {
            int[] alese = new int[8];
            int nr_alese = 0;
            int[] carti = getCarti();
            int aux_suma = getSuma();
            int nr_ilegale = 0;
            int nr_legale = 0;
            int cnt1 = 0;
            int cnt2 = 0;
            int[] legale;
            int[] ilegale;

            for (int i = 0; i < 8; i++)
                alese[i] = -1;

            for (int i = 0; i <= 9; i++)
                if (carti[i] >= 20) {
                    nr_ilegale++;
                } else {
                    nr_legale++;
                }

            legale = new int[nr_legale];
            ilegale = new int[nr_ilegale];

            for (int i = 0; i <= 9; i++)
                if (carti[i] >= 20) {
                    ilegale[cnt2] = carti[i];
                    cnt2++;
                } else {
                    legale[cnt1] = carti[i];
                    cnt1++;
                }
            System.out.println("cnt1 = " + cnt1 + " cnt2 = " + cnt2);
            for (int i = 0; i < nr_legale - 1; i++)
                for (int j = i + 1; j < nr_legale; j++)
                    if (bunuri[legale[i]].getProfit() < bunuri[legale[j]].getProfit()) {
                        int aux = legale[i];
                        legale[i] = legale[j];
                        legale[j] = aux;
                    } else
                        if(bunuri[legale[i]].getProfit() == bunuri[legale[j]].getProfit())
                            if(bunuri[legale[i]].getId() < bunuri[legale[j]].getId()) {
                                int aux = legale[i];
                                legale[i] = legale[j];
                                legale[j] = aux;
                            }


            for (int i = 0; i < nr_ilegale - 1; i++)
                for (int j = i + 1; j < nr_ilegale; j++)
                    if (bunuri[ilegale[i] - 10].getProfit() < bunuri[ilegale[j] - 10].getProfit()) {
                        int aux = ilegale[i];
                        ilegale[i] = ilegale[j];
                        ilegale[j] = aux;
                    }
            int cnt = 0;
            // bagam cat mai multe ilegale
            for (int i = 0; i < nr_ilegale; i++)
                if (aux_suma > 5 && cnt < 8) {
                    aux_suma = aux_suma - 4;
                    alese[cnt] = ilegale[i];
                    cnt++;
                }
            if (cnt > 0 && cnt <= 2)
                super.setMita(5);
            if (cnt > 2) super.setMita(10);
            // completam cu legale
            if (cnt < 8) {
                for (int i = 0; i < nr_legale; i++)
                    if (aux_suma > 2 && cnt < 8) {
                        alese[cnt] = legale[i];
                        cnt++;
                        aux_suma = aux_suma - 2;
                    }
            }

            super.setCarti_alese(alese);
            super.setCarte_declarata(0);
            super.setFrecventa(8);
        }
    }
    public void serif(Player p, int nr_juc) {
        if(nr_juc == 2) {
            if(super.getSuma() > 15) {
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
                    for(int i = 0; i < p.getCarti_alese().length; i++) {
                        p.setTaraba(p.getCarti_alese()[i]);
                        System.out.println("punem in taraba");
                    }
                } else {
                    this.setSuma(getSuma() + penalty);
                    p.setSuma(p.getSuma() - penalty);
                    for(int i = 0; i < p.getCarti_alese().length; i++)
                        if(p.getCarti_alese()[i] == p.getCarte_declarata())
                            p.setTaraba(p.getCarti_alese()[i]);
                }
            } else {
                for(int i = 0; i < p.getCarti_alese().length; i++)
                    p.setTaraba(p.getCarti_alese()[i]);
            }
        } else {
            if(super.getId() == 0) {
                if(p.getId() == 1 || p.getId() == nr_juc -1) {
                    if(super.getSuma() > 15) {
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
                            for(int i = 0; i < p.getCarti_alese().length; i++) {
                                p.setTaraba(p.getCarti_alese()[i]);
                                System.out.println("punem in taraba");
                            }
                        } else {
                            this.setSuma(getSuma() + penalty);
                            p.setSuma(p.getSuma() - penalty);
                            for(int i = 0; i < p.getCarti_alese().length; i++)
                                if(p.getCarti_alese()[i] == p.getCarte_declarata())
                                    p.setTaraba(p.getCarti_alese()[i]);
                        }
                    } else {
                        for(int i = 0; i < p.getCarti_alese().length; i++)
                            p.setTaraba(p.getCarti_alese()[i]);
                    }
                } else {
                    if(p.getMita() > 0) {
                        super.setSuma(super.getSuma() + p.getMita());
                        p.setSuma(p.getSuma() - p.getMita());
                    }
                    for(int i = 0; i < p.getCarti_alese().length; i++)
                        p.setTaraba(p.getCarti_alese()[i]);
                }
            }
            if(super.getId() > 0 && super.getId() < nr_juc - 1) {
                if(p.getId() == super.getId() + 1 || p.getId() + 1 == super.getId()) {
                    if(super.getSuma() > 15) {
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
                            for(int i = 0; i < p.getCarti_alese().length; i++) {
                                p.setTaraba(p.getCarti_alese()[i]);
                                System.out.println("punem in taraba");
                            }
                        } else {
                            this.setSuma(getSuma() + penalty);
                            p.setSuma(p.getSuma() - penalty);
                            for(int i = 0; i < p.getCarti_alese().length; i++)
                                if(p.getCarti_alese()[i] == p.getCarte_declarata())
                                    p.setTaraba(p.getCarti_alese()[i]);
                        }
                    } else {
                        for(int i = 0; i < p.getCarti_alese().length; i++)
                            p.setTaraba(p.getCarti_alese()[i]);
                    }
                } else {
                    if(p.getMita() > 0) {
                        super.setSuma(super.getSuma() + p.getMita());
                        p.setSuma(p.getSuma() - p.getMita());
                    }
                    for(int i = 0; i < p.getCarti_alese().length; i++)
                        p.setTaraba(p.getCarti_alese()[i]);
                }
            }
            if(super.getId() == nr_juc - 1) {
                if(p.getId() == 0 || p.getId() + 1 == super.getId()) {
                    if(super.getSuma() > 15) {
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
                            for(int i = 0; i < p.getCarti_alese().length; i++) {
                                p.setTaraba(p.getCarti_alese()[i]);
                                System.out.println("punem in taraba");
                            }
                        } else {
                            this.setSuma(getSuma() + penalty);
                            p.setSuma(p.getSuma() - penalty);
                            for(int i = 0; i < p.getCarti_alese().length; i++)
                                if(p.getCarti_alese()[i] == p.getCarte_declarata())
                                    p.setTaraba(p.getCarti_alese()[i]);
                        }
                    } else {
                        for(int i = 0; i < p.getCarti_alese().length; i++)
                            p.setTaraba(p.getCarti_alese()[i]);
                    }
                } else {
                    if(p.getMita() > 0) {
                        super.setSuma(super.getSuma() + p.getMita());
                        p.setSuma(p.getSuma() - p.getMita());
                    }
                    for(int i = 0; i < p.getCarti_alese().length; i++)
                        p.setTaraba(p.getCarti_alese()[i]);
                }

            }
        }

    }
}
