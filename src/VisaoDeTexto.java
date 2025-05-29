import java.awt.Color;

public class VisaoDeTexto implements VisaoSimulador{
    private EstatisticasCampo estatisticas;

    public VisaoDeTexto(){
        estatisticas = new EstatisticasCampo();


    }
    @Override
    public void mostrarStatus(int passo, Campo campo) {
        reiniciar();
        System.out.println("Passo " + passo +" "+ estatisticas.obterDetalhesPopulacao(campo));
        
    }

    @Override
    public void definirCor(Class<?> classeAnimal, Color cor) {
        // Não precisa de cor no terminal.
        
    }

    @Override
    public void reabilitarOpcoes() {
        // Não precisa reabilitar botões no terminal.
        
    }

    @Override
    public void reiniciar() {
        estatisticas.reiniciar();
    }

    @Override
    public boolean ehViavel(Campo campo) {
        return estatisticas.ehViavel(campo);
    }
}