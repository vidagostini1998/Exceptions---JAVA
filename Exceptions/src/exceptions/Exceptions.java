/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exceptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author vidag
 */
public class Exceptions {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        /*Unchecked
        String a = JOptionPane.showInputDialog("Numerador: ");
        String b = JOptionPane.showInputDialog("Denominador: ");
        
        try{
        int resultado = dividir(Integer.parseInt(a),Integer.parseInt(b));
        System.out.println("Resultado: "+resultado);
        }
        catch(NumberFormatException e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Entrada invalida " +e.getMessage());
        }
        catch(ArithmeticException e){
            JOptionPane.showMessageDialog(null, "Impossivel dividir numero por 0. "+e);
        }
        finally{
            System.out.println("finally");
        }*/

        /*Checked
        String nomeDoArquivo = "ttxt.txt";
        try {
            imprimirArquivoNoConsole(nomeDoArquivo);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Revise o nome do arquivo! " + ex.getMessage());
        } catch (IOException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado! " + ex.getMessage());
        } finally {
            System.out.println("Finally");
        }
        System.out.println("Apesar da Exception ou não, o programa continua...");
*/
        
        //Personalizada
        String nomeDoArquivo = JOptionPane.showInputDialog("Nome do arquivo a ser exibido: ");

        imprimirArquivoNoConsole1(nomeDoArquivo);
        System.out.println("\nCom exception ou não, o programa continua...");
    }

    public static void imprimirArquivoNoConsole(String nomeDoArquivo) throws FileNotFoundException, IOException {
        File file = new File(nomeDoArquivo);
        try (BufferedReader br = new BufferedReader(new FileReader(file.getName()))) {
            String line = br.readLine();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
            do {
                bw.write(line);
                bw.newLine();
                line = br.readLine();
            } while (line != null);
            bw.flush();
        }

    }
    
     public static void imprimirArquivoNoConsole1(String nomeDoArquivo) {
        try{
            try (BufferedReader br = lerArquivo(nomeDoArquivo)) {
                String line = br.readLine();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                
                do {
                    bw.write(line);
                    bw.newLine();
                    line = br.readLine();
                } while (line != null);
                bw.flush();
            }
        }
        catch (ImpossivelAberturadeArquivoException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado! "+ex.getMessage());
        } 
    }

    private static int dividir(int a, int b) {
        return a / b;
    }

    public static BufferedReader lerArquivo(String nomeDoArquivo) throws ImpossivelAberturadeArquivoException {
        File file = new File(nomeDoArquivo);
        try {
            return new BufferedReader(new FileReader(nomeDoArquivo));
        } catch (FileNotFoundException ex) {
            throw new ImpossivelAberturadeArquivoException(file.getName(),file.getPath());
        }
    }

}


class ImpossivelAberturadeArquivoException extends Exception{
    private final String nomeDoArquivo;
    private final String diretorio;

    public ImpossivelAberturadeArquivoException(String nomeDoArquivo, String diretorio) {
        super("O arquivo "+nomeDoArquivo+" não foi encontrado no diretorio "+diretorio);
        this.nomeDoArquivo = nomeDoArquivo;
        this.diretorio = diretorio;
    }

    @Override
    public String toString() {
        return "ImpossivelAberturaDeArquivoException{" +
                "nomeDoArquivo='" + nomeDoArquivo + '\'' +
                ", diretorio='" + diretorio + '\'' +
                '}';
    }
    
    
    
    
}