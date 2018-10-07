package gympass.service;

import gympass.util.FileUtil;
import gympass.util.TextUtil;
import gympass.util.TimeUtil;
import gympass.vo.DataVO;
import gympass.vo.LogVO;
import gympass.vo.ResultVO;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes da App
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class KartServiceTest {
    
    String log;
    
    @Before
    public void setUp() {
        
         log = FileUtil.leStringFile("/home/andre/Projetos/Gympass/KartLog.txt");
         
         assertNotNull(log);
        
         assertTrue(log.length() > 0);
        
         assertTrue(TextUtil.isStringValida(log));
    }
    
    public LinkedList<LogVO> processaLogs(){
        
        LinkedList<LogVO> log_list = KartService.processaLogs(log);
                
        assertNotNull(log_list);
        
        assertTrue(log_list.size() == 23);
        
        return log_list;
    }
    
    @Test
    public void efetuaCalculosProva(){
        
        LinkedList<LogVO> log_list = processaLogs();
                
        ResultVO resultado = KartService.efetuaCalculosProva(log_list);
        
        assertNotNull(resultado);
        
        assertNotNull(resultado.getLista_chegada());
        assertEquals(6, resultado.getLista_chegada().size());
        
        assertNotNull(resultado.getMapa_resultados());
        assertEquals(6, resultado.getMapa_resultados().size());
        
        //Lista de Chegada
        DataVO dados_1_piloto = resultado.getMapa_resultados().get(resultado.getLista_chegada().get(0));
        assertNotNull(dados_1_piloto);
        assertEquals(38, (int) dados_1_piloto.getCodigoPiloto());
        assertEquals("F.MASSA", dados_1_piloto.getNomePiloto());
        assertEquals(3 , dados_1_piloto.getMelhorVolta());
        assertEquals(4, (int) dados_1_piloto.getQuantidadeVoltasCompletadas());
        assertEquals("04:11.578", TimeUtil.formataDuration(dados_1_piloto.getTempoTotalProva()));
        assertTrue(dados_1_piloto.getVelocidadeMediaCorrida() == 44.24575F);
        assertEquals("00:00.000", TimeUtil.formataDuration(dados_1_piloto.getTempoChegadaAposVencedor()));
        
        DataVO dados_2_piloto = resultado.getMapa_resultados().get(resultado.getLista_chegada().get(1));
        assertNotNull(dados_2_piloto);       
        assertEquals(2, (int) dados_2_piloto.getCodigoPiloto());
        assertEquals("K.RAIKKONEN", dados_2_piloto.getNomePiloto());
        assertEquals(4 , dados_2_piloto.getMelhorVolta());
        assertEquals(4, (int) dados_2_piloto.getQuantidadeVoltasCompletadas());
        assertEquals("04:15.153", TimeUtil.formataDuration(dados_2_piloto.getTempoTotalProva()));        
        assertTrue(dados_2_piloto.getVelocidadeMediaCorrida() == 43.62725F);
        assertEquals("00:03.575", TimeUtil.formataDuration(dados_2_piloto.getTempoChegadaAposVencedor()));
        
        DataVO dados_3_piloto = resultado.getMapa_resultados().get(resultado.getLista_chegada().get(2));
        assertNotNull(dados_3_piloto);
        assertEquals(33, (int) dados_3_piloto.getCodigoPiloto());
        assertEquals("R.BARRICHELLO", dados_3_piloto.getNomePiloto());
        assertEquals(3 , dados_3_piloto.getMelhorVolta());
        assertEquals(4, (int) dados_3_piloto.getQuantidadeVoltasCompletadas());
        assertEquals("04:16.080", TimeUtil.formataDuration(dados_3_piloto.getTempoTotalProva()));        
        assertTrue(dados_3_piloto.getVelocidadeMediaCorrida() == 43.468F);
        assertEquals("00:04.502", TimeUtil.formataDuration(dados_3_piloto.getTempoChegadaAposVencedor()));
        
        DataVO dados_4_piloto = resultado.getMapa_resultados().get(resultado.getLista_chegada().get(3));
        assertNotNull(dados_4_piloto);
        assertEquals(23, (int) dados_4_piloto.getCodigoPiloto());
        assertEquals("M.WEBBER", dados_4_piloto.getNomePiloto());        
        assertEquals(4 , dados_4_piloto.getMelhorVolta());
        assertEquals(4, (int) dados_4_piloto.getQuantidadeVoltasCompletadas());
        assertEquals("04:17.722", TimeUtil.formataDuration(dados_4_piloto.getTempoTotalProva()));
        assertTrue(dados_4_piloto.getVelocidadeMediaCorrida() == 43.191254F);
        assertEquals("00:06.144", TimeUtil.formataDuration(dados_4_piloto.getTempoChegadaAposVencedor()));
        
        DataVO dados_5_piloto = resultado.getMapa_resultados().get(resultado.getLista_chegada().get(4));
        assertNotNull(dados_5_piloto);
        assertEquals(15, (int) dados_5_piloto.getCodigoPiloto());
        assertEquals("F.ALONSO", dados_5_piloto.getNomePiloto());
        assertEquals(2 , dados_5_piloto.getMelhorVolta());
        assertEquals(4, (int) dados_5_piloto.getQuantidadeVoltasCompletadas());
        assertEquals("04:54.221", TimeUtil.formataDuration(dados_5_piloto.getTempoTotalProva()));
        assertTrue(dados_5_piloto.getVelocidadeMediaCorrida() == 38.06625F);
        assertEquals("00:42.643", TimeUtil.formataDuration(dados_5_piloto.getTempoChegadaAposVencedor()));
        
        DataVO dados_6_piloto = resultado.getMapa_resultados().get(resultado.getLista_chegada().get(5));
        assertNotNull(dados_6_piloto);
        assertEquals(11, (int) dados_6_piloto.getCodigoPiloto());
        assertEquals("S.VETTEL", dados_6_piloto.getNomePiloto());
        assertEquals(3 , dados_6_piloto.getMelhorVolta());
        assertEquals(3, (int) dados_6_piloto.getQuantidadeVoltasCompletadas());
        assertEquals("06:27.276", TimeUtil.formataDuration(dados_6_piloto.getTempoTotalProva()));        
        assertTrue(dados_6_piloto.getVelocidadeMediaCorrida() == 25.745667F);
        assertEquals("02:15.698", TimeUtil.formataDuration(dados_6_piloto.getTempoChegadaAposVencedor()));
        
        //Tempo Total de Prova
        assertTrue(dados_1_piloto.getTempoTotalProva().compareTo(dados_2_piloto.getTempoTotalProva()) == -1);
        assertTrue(dados_2_piloto.getTempoTotalProva().compareTo(dados_3_piloto.getTempoTotalProva()) == -1);
        assertTrue(dados_3_piloto.getTempoTotalProva().compareTo(dados_4_piloto.getTempoTotalProva()) == -1);
        assertTrue(dados_4_piloto.getTempoTotalProva().compareTo(dados_5_piloto.getTempoTotalProva()) == -1);
        assertTrue(dados_5_piloto.getTempoTotalProva().compareTo(dados_6_piloto.getTempoTotalProva()) == -1);
        assertTrue(dados_6_piloto.getTempoTotalProva().compareTo(dados_1_piloto.getTempoTotalProva()) == 1);
        
        //Melhor Volta da Corrida
        assertTrue(resultado.getCodigo_piloto_melhor_volta_corrida() == 38);
    }
}
