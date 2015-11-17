package texteditor;

import java.io.*;

/**
 * Created by Student on 16/11/2015.
 */
public class FileClass {

    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private BufferedWriter bw;
    private StringBuilder sb;

    public FileClass(){}

    /**
     * Lee el contenido del archivo y devuelve su contenido
     * @return {@link String}
     * */
    public String getContentFile(File file) throws IOException {
        sb = new StringBuilder();
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null){
//            System.out.println(line);
            sb.append(line + "\n");
        }

        br.close();
        fr.close();
        return sb.toString();
    }

    /**
     * Guarda el contenido en un Archivo
     * @param file {@link File}
     * @param text {@link String}
     * */
    public void setContentFile(File file, String text) throws IOException {
        fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write(text);//Escribe el contenido en el archivo
        bw.close();//Cierra el Buffer
        fw.close();//Cierra el archivo
        System.out.println("Successfully Saved . . .");
    }




}
