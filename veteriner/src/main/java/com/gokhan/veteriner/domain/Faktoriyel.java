package com.gokhan.veteriner.domain;

import java.sql.SQLOutput;

public class Faktoriyel {

    public static void main(String[] args){
        Faktoriyel fak = new Faktoriyel();

        Integer result = fak.faktoriyel(4);

        System.out.println(result);

    }

    Integer faktoriyel = 1;

    public Integer faktoriyel(Integer x){

        for (Integer i=1; i<=x; i++){
            faktoriyel *= i;
            System.out.println(faktoriyel);

        }
        return faktoriyel;
    }

}
