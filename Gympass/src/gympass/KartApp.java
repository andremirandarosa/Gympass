package gympass;

import gympass.service.KartService;
import gympass.util.FileUtil;
import gympass.util.TextUtil;
import gympass.util.TimeUtil;
import gympass.vo.DataVO;
import gympass.vo.LogVO;
import gympass.vo.ResultVO;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Classe Principal da App de Calculo de Corrida
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class KartApp {

    public static void main(String[] args) {
        
        if((args != null) && (args.length == 1)){
            
            String path_log = args[0];
            
            String log = FileUtil.leStringFile(path_log);
        
            if(TextUtil.isStringValida(log)){
                
                LinkedList<LogVO> vo_list = KartService.processaLogs(log);
                
                if(vo_list != null){
                 
                    ResultVO resultado = KartService.efetuaCalculosProva(vo_list);
                    
                    if(resultado != null) imprimeResultados(resultado);
                    else System.err.println("Erro ao efetuar os calculos da corrida.");
                    
                }else System.err.println("Erro ao processar o arquivo de logs.");
                
            }else System.err.println("Arquivo de Log invalido.");
            
        }else{
            
            System.err.println("Erro - Parametro de Path Invalido: Por favor utilize o parametro de Path completo do arquivo de Log. (Ex: \"java -jar Gympass.jar /home/andre/Projetos/Gympass/KartLog.txt\").");
        }
    }
    
    /***
     * Imprime os Resultados da Corrida
     * @param result_vo Resultados da Corrida
     */
    static public void imprimeResultados(ResultVO result_vo){
    
        System.out.println("*******************************************");
        System.out.println("*         GYMPASS - BACKEND TEST          *");
        System.out.println("*******************************************");
        System.out.println("*    Autor: Andre Luiz Miranda da Rosa    *");
        System.out.println("*    Email: andremirandarosa@gmail.com    *");
        System.out.println("*******************************************");
        System.out.println("******** RESULTADO - CORRIDA KART *********");
        System.out.println("*******************************************");

        for(int r=0; r < result_vo.getLista_chegada().size(); r++){

            DataVO vo = result_vo.getMapa_resultados().get(result_vo.getLista_chegada().get(r));

            if(vo != null){

                System.out.println("Posicao Chegada: " + vo.getPosicaoChegada());                        
                System.out.println("Codigo Piloto: "+ vo.getCodigoPiloto());
                System.out.println("Nome Piloto: " + vo.getNomePiloto());
                System.out.println("Voltas Completadas: " + vo.getQuantidadeVoltasCompletadas());
                System.out.println("Tempo Total Prova: " + TimeUtil.formataDuration(vo.getTempoTotalProva()));             
                System.out.println("Melhor Volta: " + vo.getMelhorVolta() + " (Tempo: " + vo.getDuracaoMelhorVoltaFormatada() + ")");
                System.out.println("Velocidade Media na Corrida: " + new DecimalFormat("#.000").format(vo.getVelocidadeMediaCorrida()));
                
                if(r > 0)
                    System.out.println("Tempo de Chegada Apos Vencedor: " + TimeUtil.formataDuration(vo.getTempoChegadaAposVencedor()));
                
                System.out.println("===========================================");
            }
        }

        DataVO vo = result_vo.getMapa_resultados().get(result_vo.getCodigo_piloto_melhor_volta_corrida());

        System.out.println("*******************************************");
        System.out.println("******** MELHOR VOLTA DA CORRIDA **********");
        System.out.println("*******************************************");            
        System.out.println("Codigo Piloto: "+ vo.getCodigoPiloto());
        System.out.println("Nome Piloto: " + vo.getNomePiloto());         
        System.out.println("Volta: " + vo.getMelhorVolta() + " (Tempo: " + vo.getDuracaoMelhorVoltaFormatada() + ")");
        System.out.println("*******************************************");
    }
}

