package gympass.service;

import gympass.util.TextUtil;
import gympass.util.TimeUtil;
import gympass.util.Util;
import gympass.vo.DataVO;
import gympass.vo.LogVO;
import gympass.vo.ResultVO;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Servicos para o App
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class KartService{
    
    /***
     * Processamento do Log
     * @param log Conteudo do Log
     * @return Lista de VOs processados (Null: Vazio ou Erro) 
     */
    static public LinkedList<LogVO> processaLogs(String log){
        
        LinkedList<LogVO> retorno = new LinkedList<>();
        
        LinkedList<String> linhas = TextUtil.separaStringTokens(log, "\n");
         
         if((linhas != null) && (linhas.size() > 0)){
             
             for(int l=1; l< linhas.size(); l++){
         
                String linha = linhas.get(l);
                
                linha = TextUtil.substituiTokenString(linha, " ", "_");
                linha = TextUtil.substituiTokenString(linha, "\t", "_");
                
                LinkedList<String> valores = TextUtil.separaStringTokens(linha, "_"); 

                if((valores != null) && (valores.size() > 0)){

                    LogVO vo = new LogVO();
                    vo.setHora(TimeUtil.getLocalTime(valores.get(0)));
                    vo.setCodigoPiloto(Integer.parseInt(valores.get(1)));
                    vo.setNomePiloto(valores.get(3));
                    vo.setNumeroVolta(Integer.parseInt(valores.get(4)));
                    vo.setTempoVolta(TimeUtil.getDuration(valores.get(5)));
                    vo.setVelocidadeMediaVolta(Util.converteVelocidade(valores.get(6)));
                    
                    retorno.add(vo);
                }
            }
         }
         
         if(retorno.size() > 0) return retorno;
         else return null;
    }
    
     /***
     * Efetua os Calculos da Prova
     * @param log_list Lista de Logs da prova
     * @return VO do resultado da corrida (Null: Erro)
     */
    static public ResultVO efetuaCalculosProva(LinkedList<LogVO> log_list){
        
        LinkedList<Integer> lista_chegada = new LinkedList<>();
        int posicao_piloto = 1;
        
        HashMap<Integer, DataVO> mapa_resultados = new HashMap<>();
        
        if((log_list != null) && (log_list.size() > 0)){
            
            //Ordena Logs pelo Tempo
            Collections.sort(log_list, new LogVO());
            
            for(LogVO log : log_list){
                
                //Verifica os Pilotos que completaram a prova
                if(log.getNumeroVolta() == 4){
                    
                    DataVO vo = mapa_resultados.get(log.getCodigoPiloto());
                    vo.setPosicaoChegada(posicao_piloto);
                    lista_chegada.add(vo.getCodigoPiloto());
                    
                    posicao_piloto++;
                }                
               
                if(!mapa_resultados.containsKey(log.getCodigoPiloto())){
                    
                    DataVO vo = new DataVO();
                    vo.setCodigoPiloto(log.getCodigoPiloto());
                    vo.setNomePiloto(log.getNomePiloto());
                    vo.setPosicaoChegada(0);
                    
                    vo.setQuantidadeVoltasCompletadas(log.getNumeroVolta());
                    
                    LinkedList<Duration> lista_tempos = new LinkedList<>();
                    lista_tempos.add(log.getTempoVolta());   
                    vo.setListaTempos(lista_tempos);
                    
                    LinkedList<Float> lista_velocidades_medias = new LinkedList<>();
                    lista_velocidades_medias.add(log.getVelocidadeMediaVolta());
                    vo.setListaVelocidadesMedias(lista_velocidades_medias);
                    
                    vo.setTempoTotalProva(log.getTempoVolta());
                    
                    mapa_resultados.put(log.getCodigoPiloto(), vo);
                    
                }else{
                    
                    DataVO vo = mapa_resultados.get(log.getCodigoPiloto());
                    
                    Duration tempo_log = log.getTempoVolta();
                   
                    vo.setQuantidadeVoltasCompletadas(log.getNumeroVolta());                  
                    
                    LinkedList<Duration> lista_tempos = vo.getListaTempos();
                    lista_tempos.add(tempo_log);                    
                    vo.setListaTempos(lista_tempos);
                    
                    LinkedList<Float> lista_velocidades_medias = vo.getListaVelocidadesMedias();
                    lista_velocidades_medias.add(log.getVelocidadeMediaVolta());
                    vo.setListaVelocidadesMedias(lista_velocidades_medias);
                    
                    vo.setTempoTotalProva(vo.getTempoTotalProva().plus(tempo_log));
                    
                    mapa_resultados.put(log.getCodigoPiloto(), vo);
                }
            }
            
            //Verifica os pilotos que Nao completaram a prova
            for(int volta = 3; volta > 0; volta--){

                int cod_piloto_menor_tempo = -1;
                Duration menor_tempo = null;
                
                for(Integer codPiloto : mapa_resultados.keySet()){
                    
                    DataVO vo = mapa_resultados.get(codPiloto);
                    
                    if(vo.getQuantidadeVoltasCompletadas() == volta){
                        
                        if(menor_tempo == null){
                            
                            menor_tempo = vo.getDuracaoVolta(volta);                            
                            cod_piloto_menor_tempo = codPiloto;
                            
                        }else{
                            
                            if(vo.getDuracaoVolta(volta).compareTo(menor_tempo) < 0){ 
                            
                                menor_tempo = vo.getDuracaoVolta(volta);                                
                                cod_piloto_menor_tempo = codPiloto;
                             }
                        }
                    }
                }
                
                if(cod_piloto_menor_tempo >= 0){

                    DataVO vo = mapa_resultados.get(cod_piloto_menor_tempo);
                    vo.setPosicaoChegada(posicao_piloto);
                    lista_chegada.add(cod_piloto_menor_tempo);
                    
                    posicao_piloto++;
                }
            }
            
            //Tempo de Chegada Apos Vencedor
            verificaTempoChegadaAposVencedor(lista_chegada, mapa_resultados);
            
            //Melhor Volta da Corrida
            ResultVO retorno =  new ResultVO(lista_chegada, mapa_resultados);
            
            verificaPilotoMelhorVoltaCorrida(retorno);
            
            return retorno;
        }
        
        return null;
    }
    
    /***
     * Verifica o Tempo de Chegada Apos o Vencedor
     * @param lista_chegada Lista de Chegada com Codigo dos Pilotos
     * @param mapa_resultados Mapa dos Resultados da Corrida
     */
    static private void verificaTempoChegadaAposVencedor(LinkedList<Integer> lista_chegada, HashMap<Integer, DataVO> mapa_resultados){
        
        DataVO vo_campeao = mapa_resultados.get(lista_chegada.get(0));
        
        for(int l=0; l < lista_chegada.size(); l++){
         
            DataVO vo = mapa_resultados.get(lista_chegada.get(l));
                    
            vo.setTempoChegadaAposVencedor(vo.getTempoTotalProva().minus(vo_campeao.getTempoTotalProva()));
            
            mapa_resultados.put(vo.getCodigoPiloto(), vo);
        }
    }
    
    /***
     * Verifica o Piloto com Melhor Volta na Corrida
     * @param result_vo Resultado dos Calculos da Corrida
     */
    static private void verificaPilotoMelhorVoltaCorrida(ResultVO result_vo){
    
        int codigo_piloto_melhor_volta_corrida = 0;            
        Duration duracao_melhor_volta_corrida = null;
        
        for(int r=0; r < result_vo.getLista_chegada().size(); r++){

            DataVO vo = result_vo.getMapa_resultados().get(result_vo.getLista_chegada().get(r));

            if(vo != null){

                Duration duracao_melhor_volta_piloto = vo.getDuracaoMelhorVolta();
                
                if(duracao_melhor_volta_corrida == null){

                    duracao_melhor_volta_corrida = duracao_melhor_volta_piloto;
                    codigo_piloto_melhor_volta_corrida = vo.getCodigoPiloto();

                }else{

                    if(duracao_melhor_volta_piloto.compareTo(duracao_melhor_volta_corrida) < 0){

                        duracao_melhor_volta_corrida = duracao_melhor_volta_piloto;
                        codigo_piloto_melhor_volta_corrida = vo.getCodigoPiloto();
                    }
                }
            }
        }

        result_vo.setCodigo_piloto_melhor_volta_corrida(codigo_piloto_melhor_volta_corrida);
    }
}
