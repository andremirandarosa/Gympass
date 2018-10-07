package gympass.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utilitario para Tempos
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class TimeUtil {
    
    /***
     * Verifica o LocalTime de uma String de Tempo
     * @param tempo String de Tempo (Ex: 23:54:57.757)
     * @return O LocalTime gerado (Null: Erro)
     */
    static public LocalTime getLocalTime(String tempo){
        
        if(TextUtil.isStringValida(tempo))
            return LocalTime.parse(tempo);
        
        return null;
    }
    
    /***
     * Verifica um Duration de uma String de Duracao
     * @param duracao Stringe de Duracao (Ex: 1:18.097)
     * @return O Duration gerado (Null: Erro)
     */
    static public Duration getDuration(String duracao){
        
         if(TextUtil.isStringValida(duracao)){

             duracao = TextUtil.substituiTokenString(duracao, "\\:", "_");
             duracao = TextUtil.substituiTokenString(duracao, "\\.", "_");

             LinkedList<String> valores = TextUtil.separaStringTokens(duracao, "_");
             
             if((valores != null) && (valores.size() > 0)){
              
                 Duration duration = Duration.ofMinutes(Long.parseLong(valores.get(0)));
                 
                 duration = duration.plusSeconds(Long.parseLong(valores.get(1)));
                 
                 duration = duration.plusMillis(Long.parseLong(valores.get(2)));
                 
                 return duration;
             }
         }
        
        return null;
    }
    
    /**
     * Formata uma Duracao
     * @param duration Duration a ser formatado
     * @return String da formatacao
     */
    static public String formataDuration(Duration duration) {
        
        //return new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()).
        return new SimpleDateFormat("mm:ss.SSS", Locale.getDefault()).
                       format(new Date(duration.toMillis() - TimeZone.getDefault().getRawOffset()));
    }
}
