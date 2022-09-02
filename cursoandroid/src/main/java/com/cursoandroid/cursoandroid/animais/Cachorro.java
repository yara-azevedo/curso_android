package com.cursoandroid.cursoandroid.animais;

 class Cachorro extends Animal{
     
    void latir(){
         System.out.println("latindo");
     }
    
    void dormir() { //sobrescrita de metodos
        super.dormir(); //chamando o metodo original da superclasse
        System.out.println(" como um doggo");
    }
    
    void correr() { //sobrescrita de metodos
        super.dormir();
        System.out.println("correndo como um doggo");
    }
}
