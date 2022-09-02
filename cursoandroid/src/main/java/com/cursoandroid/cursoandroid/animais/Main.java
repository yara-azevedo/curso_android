package com.cursoandroid.cursoandroid.animais;

public class Main {

    public static void main(String[] args) {

        Cachorro cao = new Cachorro();
        System.out.println("---Cachorro");
        cao.dormir();
        cao.latir();
        cao.correr();
        cao.setCor("preto");
        System.out.println(cao.getCor());
        System.out.println("\n");

        Passaro passaro = new Passaro();
        System.out.println("---Passaro");
        passaro.dormir();
        passaro.piar();
        System.out.println("\n");

        Animal animal = new Animal();
        System.out.println("---Animal");
        animal.dormir();
        System.out.println("\n");

    }
}
