import java.util.List;

public interface Ator {
    //Cada sub classe age de um jeito
    public void agir(List<Ator> atores);

    
    public boolean estaAtivo();
}