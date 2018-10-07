package gympass.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Utilitarios para Texto
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class TextUtil {
 
      /***
     * Verifica se uma String eh Valida (Nao-Nula e Tamanho>0)
     * @param string String a ser verificada
     * @return  Flag de String valida
     */
    static public boolean isStringValida(String string){
        
        return ((string != null) && (string.length() > 0));
    }
    
      /** 
      * Separa uma String de acordo com um Token delimitador
      * @param texto String para ser separada
      * @param delimitador String com o Delimitador
      * @return Lista com os elementos separados | NULL: Erro
     */
    static public LinkedList<String> separaStringTokens(String texto, String delimitador){
                
        LinkedList<String> ll = new LinkedList<>();
        
        if((texto.length() > 0) && (delimitador.length() > 0)){

            if(delimitador.length() == 1){

                StringTokenizer st = new StringTokenizer(texto, delimitador);

                while(st.hasMoreTokens())
                    ll.add(st.nextToken());

            }else{

                String[] vet_tokens = texto.split(delimitador);
    
                ll.addAll(Arrays.asList(vet_tokens));
            }
        }
        
        if(ll.size() > 0) return ll;
        else{
            
            ll.add(texto);
            
            return ll;
        }
    }
    
     /**
      * Substitui um Token de uma String
      * @param s_original String Oringial
      * @param token_velho Token a ser substituido
      * @param token_novo Token novo
      * @return String com o token substituido
     */
    static public String substituiTokenString(String s_original, String token_velho, String token_novo){

        return s_original.replaceAll(token_velho, token_novo);
    }
    
}
