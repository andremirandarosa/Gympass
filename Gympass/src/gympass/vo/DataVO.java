package gympass.vo;

import gympass.util.TimeUtil;
import java.time.Duration;
import java.util.LinkedList;

/**
 * Value Object para Armazenamento dos Dados da Corrida
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class DataVO {
    
    private Integer posicaoChegada;
    
    private Integer codigoPiloto;
    
    private String nomePiloto;
    
    private Integer quantidadeVoltasCompletadas;
    
    private Duration tempoTotalProva;
    
    private LinkedList<Duration> listaTempos = new LinkedList<>();
    
    private LinkedList<Float> listaVelocidadesMedias = new LinkedList<>();
    
    private Duration tempoChegadaAposVencedor;
    
    //==========================================================================
    
    /***
     * Verifica o Duracao de uma Volta
     * @param volta Volta a ser verificada
     * @return A Duracao verificada (Null: Erro)
     */
    public Duration getDuracaoVolta(int volta){
        
        if(volta <= listaTempos.size())
            return listaTempos.get(volta - 1);
        
        return null;
    }
    
    /***
     * Verifica a Melhor Volta do Piloto
     * @return A melhor volta
     */
    public int getMelhorVolta(){
        
        Duration menor_duracao = null;
        int melhor_volta = -1;
        
        for(int i=0; i < listaTempos.size(); i++){
            
            Duration d = listaTempos.get(i);
            
            if(menor_duracao == null){
                
                menor_duracao = d;
                melhor_volta = i+1;
                
            }else{
                
                if(d.compareTo(menor_duracao) < 0){
                    
                    menor_duracao = d;
                    melhor_volta = i+1;
                }
            }
        }
        
        return melhor_volta;
    }
    
    /***
     * Verifica a Duracao da Melhor Volta do Piloto
     * @return A duracao
     */
    public Duration getDuracaoMelhorVolta(){
        
        return getListaTempos().get(getMelhorVolta()-1);
    }
    
    /***
     * Verifica a Duracao da Melhor Volta do Piloto Formatada
     * @return A duracao formatada
     */
    public String getDuracaoMelhorVoltaFormatada(){
        
        return TimeUtil.formataDuration(getDuracaoMelhorVolta());
    }
    
    /***
     * Verifica a Velocidade Media da Corrida do Piloto
     * @return A velocidade Media
     */
    public Float getVelocidadeMediaCorrida(){
        
        int quantidade_voltas = this.getListaVelocidadesMedias().size();
        
        if(quantidade_voltas > 0){
            
            float somatorio_velocidades_medias = 0;

            for(float v : this.getListaVelocidadesMedias())
                somatorio_velocidades_medias += v;

            if(somatorio_velocidades_medias > 0)
                return somatorio_velocidades_medias/quantidade_voltas;
        }
        
        return 0F;
    }
    //==========================================================================

    /**
     * @return the posicaoChegada
     */
    public Integer getPosicaoChegada() {
        return posicaoChegada;
    }

    /**
     * @param posicaoChegada the posicaoChegada to set
     */
    public void setPosicaoChegada(Integer posicaoChegada) {
        this.posicaoChegada = posicaoChegada;
    }

    /**
     * @return the codigoPiloto
     */
    public Integer getCodigoPiloto() {
        return codigoPiloto;
    }

    /**
     * @param codigoPiloto the codigoPiloto to set
     */
    public void setCodigoPiloto(Integer codigoPiloto) {
        this.codigoPiloto = codigoPiloto;
    }

    /**
     * @return the nomePiloto
     */
    public String getNomePiloto() {
        return nomePiloto;
    }

    /**
     * @param nomePiloto the nomePiloto to set
     */
    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    /**
     * @return the quantidadeVoltasCompletadas
     */
    public Integer getQuantidadeVoltasCompletadas() {
        return quantidadeVoltasCompletadas;
    }

    /**
     * @param quantidadeVoltasCompletadas the quantidadeVoltasCompletadas to set
     */
    public void setQuantidadeVoltasCompletadas(Integer quantidadeVoltasCompletadas) {
        this.quantidadeVoltasCompletadas = quantidadeVoltasCompletadas;
    }
    
    /**
     * @return the tempoTotalProva
     */
    public Duration getTempoTotalProva() {
        return tempoTotalProva;
    }

    /**
     * @param tempoTotalProva the tempoTotalProva to set
     */
    public void setTempoTotalProva(Duration tempoTotalProva) {
        this.tempoTotalProva = tempoTotalProva;
    }

    /**
     * @return the listaTempos
     */
    public LinkedList<Duration> getListaTempos() {
        return listaTempos;
    }

    /**
     * @param listaTempos the listaTempos to set
     */
    public void setListaTempos(LinkedList<Duration> listaTempos) {
        this.listaTempos = listaTempos;
    }

    /**
     * @return the listaVelocidadesMedias
     */
    public LinkedList<Float> getListaVelocidadesMedias() {
        return listaVelocidadesMedias;
    }

    /**
     * @param listaVelocidadesMedias the listaVelocidadesMedias to set
     */
    public void setListaVelocidadesMedias(LinkedList<Float> listaVelocidadesMedias) {
        this.listaVelocidadesMedias = listaVelocidadesMedias;
    }

    /**
     * @return the tempoChegadaAposVencedor
     */
    public Duration getTempoChegadaAposVencedor() {
        return tempoChegadaAposVencedor;
    }

    /**
     * @param tempoChegadaAposVencedor the tempoChegadaAposVencedor to set
     */
    public void setTempoChegadaAposVencedor(Duration tempoChegadaAposVencedor) {
        this.tempoChegadaAposVencedor = tempoChegadaAposVencedor;
    }
}
