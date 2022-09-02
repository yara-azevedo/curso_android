package com.cursoandroid.cursoandroid;

 class Funcionario {
     String nome;
     double salario=100;
     
     void salario(){ //sem retorno
         this.salario = this.salario - (this.salario * 0.1);
         System.out.println("Sem retorno " + this.salario);//this referencia o que tem na classe, dados, metodos e et
     }
     
     double salario2(){ //com retorno
         this.salario = this.salario - (this.salario * 0.1);
         return this.salario;
     }
     
     double salario3(double bonus, double desconto){ //com retorno com parametro
         this.salario = this.salario - (this.salario * 0.1);
         return this.salario + bonus;
     }
    
}
