package sobrecarga;

public class Pessoa {
    private String nome;
    private int idade;
    
    public void exibirDados(String nome){
        System.out.println("Exibe apenas o nome: " + nome);
    }
    
    public void exibirDados(String nome, int idade){
        System.out.println("Exibe o nome: " + nome + " e a idade: " + idade);
    }
}

/*
SOBRECARGA DE MÉTODOS -> metodos com o mesmo nome, mas com parametros diferentes
na main, é só passar os parametros correspondentes ao metodo que você quer chamar
*/
