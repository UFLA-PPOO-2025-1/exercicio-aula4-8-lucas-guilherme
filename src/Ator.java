import java.util.List;

public abstract class Ator {
    // A localização do animal.
    private Localizacao localizacao;
    // O campo ocupado.
    private Campo campo;

    public Ator(Campo campo, Localizacao localizacao){
        this.campo = campo;
        definirLocalizacao(localizacao);
    }
    //Cada sub classe age de um jeito
    public abstract void agir(List<Ator> atores);

    
    public abstract boolean estaAtivo();

    /**
     * Retorna a localização do animal.
     * @return A localização do animal.
     */
    public Localizacao obterLocalizacao()
    {
        return localizacao;
    }
    
    /**
     * Coloca o animal na nova localização no campo fornecido.
     * @param novaLocalizacao A nova localização do animal.
     */
    protected void definirLocalizacao(Localizacao novaLocalizacao)
    {
        if(localizacao != null) {
            campo.limpar(localizacao);
        }
        localizacao = novaLocalizacao;
        campo.colocar(this, novaLocalizacao);
    }

    public Campo obterCampo() {
        return campo;
    }

    protected void modificarLocalizacao(Localizacao localizacao)
    {
        this.localizacao = localizacao;
    }

    protected void modificarCampo(Campo campo)
    {
        this.campo = campo;
    }
}