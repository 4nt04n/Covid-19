package estructura;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Prueba {

    public static void main(String[] args) {
      HashTable table=  new HashTable(1200);
         Persona persona;
         String[] fila=null;
         int contador=0;
        //Declarar una variable FileReader
        FileReader fr = null;
        String nombreFichero = "Covid19Casos-1000.csv";
        boolean llave=true;
        String id="";

        try {
            //Abrir el fichero indicado en la variable nombreFichero
            fr = new FileReader(nombreFichero);
            //Leer el primer carácter
            //Se debe almacenar en una variable de tipo int
            int caract = fr.read();
            //Se recorre el fichero hasta encontrar el carácter -1
            //   que marca el final del fichero
            while (caract != -1) {
                //Mostrar en pantalla el carácter leído convertido a char
                //System.out.print((char) caract);
                if(caract== 34 && llave){ //Analizo apertura de llaves y salteo
                    caract = fr.read();
                    llave=false;
                }

                if(caract==34 && !llave){ //Analizo cierre de llaves y reseteo y almaceno el id
                    caract = fr.read();
                    // System.out.print(id+" ")
                    fila[contador]=id;
                    contador++;


                    if(caract==10) //salto de linea osea cambio de persona
                    {//  System.out.println("");
                        persona=new Persona(fila);
                        contador=0;
                    }
                    id="";
                    llave=true;


                }
                if(caract != 44) //cargo caracter si no es coma
                {id+=(char) caract;}
                //Leer el siguiente carácter
                caract = fr.read();
            }
        } catch (FileNotFoundException e) {
            //Operaciones en caso de no encontrar el fichero
            System.out.println("Error: Fichero no encontrado");
            //Mostrar el error producido por la excepción
            System.out.println(e.getMessage());
        } catch (Exception e) {
            //Operaciones en caso de error general
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            //Operaciones que se harán en cualquier caso. Si hay error o no.
            try {
                //Cerrar el fichero si se ha abierto
                if (fr != null)
                    fr.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }

        }


    }



}
