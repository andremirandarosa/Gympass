package gympass.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Utilitarios para Arquivos
 * @author Andre Luiz (andremirandarosa@gmail.com)
 */
public class FileUtil {
    
     /**
     * Le String de um Arquivo
     * @param endereco Endereco Completo do Arquivo a ser Lido
     * @return A String do Conteudo do Arquivo (ERRO: NULL)
     */
    static public String leStringFile(String endereco){

        if(TextUtil.isStringValida(endereco) == true){
        
            try{       
                StringBuilder contents = new StringBuilder();

                File file = new File(endereco);
                
                BufferedReader input = new BufferedReader(new FileReader(file));

                String line;

                while((line = input.readLine()) != null){

                    if(line != null){
                            contents.append(line);

                            contents.append(System.getProperty("line.separator"));
                    }
                }

                input.close();

                return contents.toString();

            }catch(Exception e){

                System.err.println("Erro ao verificar o conteudo do arquivo: " + endereco);
                
                System.err.println(e.getMessage());
            }
        }

        return null;
    }
}
