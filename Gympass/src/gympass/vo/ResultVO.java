package gympass.vo;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Value Object para Armazenamento dos Resultados dos Calculos da Corrida
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class ResultVO {
    
    private LinkedList<Integer> lista_chegada;
    
    private HashMap<Integer, DataVO> mapa_resultados;
    
    private Integer codigo_piloto_melhor_volta_corrida;
    
    //==========================================================================

    public ResultVO(LinkedList<Integer> lista_chegada, HashMap<Integer, DataVO> mapa_resultados){
        
        this.lista_chegada = lista_chegada;
        this.mapa_resultados = mapa_resultados;
    }
    
    //==========================================================================
    
    /**
     * @return the lista_chegada
     */
    public LinkedList<Integer> getLista_chegada() {
        return lista_chegada;
    }

    /**
     * @param lista_chegada the lista_chegada to set
     */
    public void setLista_chegada(LinkedList<Integer> lista_chegada) {
        this.lista_chegada = lista_chegada;
    }

    /**
     * @return the mapa_resultados
     */
    public HashMap<Integer, DataVO> getMapa_resultados() {
        return mapa_resultados;
    }

    /**
     * @param mapa_resultados the mapa_resultados to set
     */
    public void setMapa_resultados(HashMap<Integer, DataVO> mapa_resultados) {
        this.mapa_resultados = mapa_resultados;
    }

    /**
     * @return the codigo_piloto_melhor_volta_corrida
     */
    public Integer getCodigo_piloto_melhor_volta_corrida() {
        return codigo_piloto_melhor_volta_corrida;
    }

    /**
     * @param codigo_piloto_melhor_volta_corrida the codigo_piloto_melhor_volta_corrida to set
     */
    public void setCodigo_piloto_melhor_volta_corrida(Integer codigo_piloto_melhor_volta_corrida) {
        this.codigo_piloto_melhor_volta_corrida = codigo_piloto_melhor_volta_corrida;
    }
}
