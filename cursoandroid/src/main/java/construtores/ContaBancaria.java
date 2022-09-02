package construtores;

public class ContaBancaria {
    private int numeroConta;
    
    public ContaBancaria(){ //construtor padrao
        System.out.println("olha o construtor sem parametro");
    }
    //sobrecarga dos construtores
    public ContaBancaria(int nConta){ //construtor com parametro
        this.numeroConta = nConta;
        System.out.println("olha o construtor com parametro " + nConta);
    }
    
}

/*
o tipo do construtor não precisa ser especificado porque ele e do tipo da classe
sempre o mesmo nome da classe
é chamado automaticamente quando a classe é instanciada
*/
