package banco;

 public class Conta {
    protected int numero;
    private double saldo = 100;
    
    public void deposito(double valorDeposito){
        this.saldo = this.saldo + valorDeposito;
    }
    
    public void saque(double valorSacado){
        this.saldo = this.saldo - valorSacado;
    }
    
    public double recuperar(){
        return this.saldo;
    }
    
}
/*
MODIFICADORES DE ACESSO
--PUBLIC: todas as classes tem acesso, mesmo de outros pacotes
--PRIVATE: acesso apenas dentro da classe
--PROTECTED: acesso no mesmo pacote - HERANÇA   
--DEFAULT: apenas dentro do pacote
*/