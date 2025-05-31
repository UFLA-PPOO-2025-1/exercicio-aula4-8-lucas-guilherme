import java.util.Iterator;
import java.util.List;

public class Cacador extends Ator {

    public Cacador(Campo campo, Localizacao localizacao){
        super(campo,localizacao);
    }

    public void agir(List<Ator> atores){         
            // Move-se em direção a um Animal, se encontrada.
            Localizacao novaLocalizacao = cacar();
            if(novaLocalizacao == null) { 
                // Nenhuma comida encontrada - tenta se mover para uma localização livre.
                novaLocalizacao = obterCampo().localizacaoVizinhaLivre(obterLocalizacao());
            }
            // Verifica se foi possível se mover.
            if(novaLocalizacao != null) {
                definirLocalizacao(novaLocalizacao);
            }
        
     }

    
    public boolean estaAtivo(){
        return true;
    }

    protected Localizacao cacar(){

        List<Localizacao> vizinhas = obterCampo().localizacoesVizinhas(obterLocalizacao());
		Iterator<Localizacao> it = vizinhas.iterator();
		Localizacao localizacaoFinal = null;
		while(it.hasNext()) {
		    Localizacao onde = it.next();
		    Object ator = obterCampo().obterObjetoEm(onde);
		    if(ator instanceof Animal) {
                Animal animal = (Animal) ator;
		        if(animal.estaAtivo()) { 
		            animal.morrer();
		            localizacaoFinal = onde;
		        }
		    }
		}
		return localizacaoFinal;
    }
    


}
