package com.cursoandroid.cursoandroid;

public class Cursoandroid {

    public static void main(String[] args) {
        
        Funcionario funcionario = new Funcionario();
        funcionario.nome="AAA";
        funcionario.salario();
        System.out.println("nome " + funcionario.nome);
        
        Funcionario funcionario1 = new Funcionario();
        double salarioo = funcionario1.salario2();
        System.out.println("com retorno " + salarioo);
        
        Funcionario funcionario2 = new Funcionario();
        double salariooo = funcionario2.salario3(150, 10); // 150 e 10 sao parametros do metodo da classe
        System.out.println("com retorno e parametro " + salariooo);
        
        //exemplo casa
        /*Casa casa = new Casa(); //instancia do objeto
        casa.cor = "azul";
        casa.abrirPorta();//executa o metodo da classe instanciada
        System.out.println(casa.cor + "\n");
        
        Casa casa1 = new Casa();
        casa1.cor = "preta";
        System.out.println(casa1.cor + "\n");*/
    }
}
