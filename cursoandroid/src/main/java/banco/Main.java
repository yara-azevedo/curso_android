package banco;


public class Main {

    public static void main(String[] args) {
        
        Conta conta = new Conta();
        conta.deposito(100); //total: 200
        conta.saque(50); //total: 50
        System.out.println(conta.recuperar());
        
    }
}
