
package interfacee;

public class Pessoa extends Cidadao implements Presidente{

    @Override
    public void ganhar() {
        System.out.println("ganhar em rj");
    }
    
}
