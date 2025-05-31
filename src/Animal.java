import java.util.List;
import java.util.Random;

public abstract class Animal extends Ator{

    // Características compartilhadas por todos os coelhos (atributos estáticos, da classe).

    // Um gerador de números aleatórios compartilhado para controlar a reprodução.
    private static final Random rand = Randomizador.obterRandom();

    // Características individuais (atributos comuns, de instância).
    
    // A idade do animal.
    private int idade;
    // Indica se o animal está vivo ou não.
    private boolean vivo;

    public Animal(boolean idadeAleatoria, Campo campo, Localizacao localizacao)
    {
        super(campo, localizacao);
        vivo = true;
        idade = 0;
        if(idadeAleatoria) {
            idade = rand.nextInt(obterIdadeMaxima());
        }
    }

    public int obterIdade() {
        return idade;
    }
    
    /**
     * Aumenta a idade.
     * Isso pode resultar na morte do coelho.
     */
    protected void incrementarIdade()
    {
        idade++;
        if(idade > obterIdadeMaxima()) {
            morrer();
        }
    }
    
    /**
     * Verifica se o animal está vivo ou não.
     * @return verdadeiro se o animal ainda estiver vivo.
     */
    @Override
    public boolean estaAtivo(){
        return vivo;
    }

    /**
     * Define que o animal não está mais vivo.
     * Ele é removido do campo.
     */
    protected void morrer()
    {
        vivo = false;
        if(obterLocalizacao() != null) {
            obterCampo().limpar(obterLocalizacao());
            modificarLocalizacao(null);
            modificarCampo(null);
        }
    }
    
    /**
     * Gera um número representando o número de nascimentos,
     * se puder procriar.
     * @return O número de nascimentos (pode ser zero).
     */
    protected int procriar()
    {
        int nascimentos = 0;
        if(podeProcriar() && rand.nextDouble() <= obterProbabilidadeReproducao()) {
            nascimentos = rand.nextInt(obterTamanhoMaximoNinhada()) + 1;
        }
        return nascimentos;
    }
 
    /**
     * Verifica se este animal deve dar à luz neste passo.
     * Novos nascimentos serão feitos em locais vizinhos livres.
     * @param novosAnimais Uma lista para retornar os animais recém-nascidos.
     */
    protected void reproduzir(List<Ator> novosAnimais)
    {
        // Novos animais nascem em locais vizinhos.
        // Obtém uma lista de locais vizinhos livres.
        List<Localizacao> locaisLivres = obterCampo().localizacoesVizinhasLivres(obterLocalizacao());
        int nascimentos = procriar();
        for(int n = 0; n < nascimentos && locaisLivres.size() > 0; n++) {
            Localizacao local = locaisLivres.remove(0);
            Animal filhote = criarNovoFilhote(false, obterCampo(), local);
            novosAnimais.add(filhote);
        }
    }


    /**
     * Uma raposa pode procriar se tiver atingido a idade de reprodução.
     */
    private boolean podeProcriar()
    {
        return obterIdade() >= obterIdadeReproducao();
    }

    

    protected abstract int obterIdadeMaxima();
    
    protected abstract Animal criarNovoFilhote(boolean idadeAleatoria, Campo campo, Localizacao localizacao);
    
    protected abstract int obterIdadeReproducao();
    
    protected abstract double obterProbabilidadeReproducao();
    
    protected abstract int obterTamanhoMaximoNinhada();
}
