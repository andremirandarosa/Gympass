package gympass.vo;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * Value Object para Armazenamento dos Registros de Log de Input da Corrida
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class LogVO implements Comparator<LogVO>{
    
    private LocalTime hora;
    
    private Integer codigoPiloto;
    
    private String nomePiloto;
    
    private Integer numeroVolta;
    
    private Duration tempoVolta;
    
    private Float velocidadeMediaVolta;

    //==========================================================================

    @Override
    public int compare(LogVO l1, LogVO l2) {

        if((l1.getHora() != null) && (l1.getHora() != null))
            return l1.getHora().compareTo(l2.getHora());
        
        return 0;
    }

    /**
     * @return the hora
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * @return the codigoPiloto
     */
    public Integer getCodigoPiloto() {
        return codigoPiloto;
    }

    /**
     * @param codigoPiloto the numeroPiloto to set
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
     * @return the numeroVolta
     */
    public Integer getNumeroVolta() {
        return numeroVolta;
    }

    /**
     * @param numeroVolta the numeroVolta to set
     */
    public void setNumeroVolta(Integer numeroVolta) {
        this.numeroVolta = numeroVolta;
    }

    /**
     * @return the tempoVolta
     */
    public Duration getTempoVolta() {
        return tempoVolta;
    }

    /**
     * @param tempoVolta the tempoVolta to set
     */
    public void setTempoVolta(Duration tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    /**
     * @return the velocidadeMediaVolta
     */
    public Float getVelocidadeMediaVolta() {
        return velocidadeMediaVolta;
    }

    /**
     * @param velocidadeMediaVolta the velocidadeMediaVolta to set
     */
    public void setVelocidadeMediaVolta(Float velocidadeMediaVolta) {
        this.velocidadeMediaVolta = velocidadeMediaVolta;
    }
}
