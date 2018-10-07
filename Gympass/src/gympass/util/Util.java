package gympass.util;

/**
 * Utilitarios em Geral
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class Util {
 
     /***
     * Verifica a Velocidade a Partir de uma String
     * @param velocidade String de Velocidade
     * @return O Float da Velocidade (Null: Erro)
     */
    static public Float converteVelocidade(String velocidade){
        
        if(TextUtil.isStringValida(velocidade))
            return Float.parseFloat(TextUtil.substituiTokenString(velocidade, ",", "."));
        
        return null;
    }
}
