
import java.io.*;

class Main {

    public static void main (String[] args) {

        String nombreFichero = "csv/Covid19Casos-10.csv";
        boolean llave=true;
        String id="";
        long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        String parametros;
       // for (String parametros:args) {
        for (int i=0;args.length>i;i++){
            parametros=args[i];
            switch (parametros){
                case "-estad":

                    System.out.println("Cantidad total de muestras:" +"");
                    System.out.println("Cantidad total de infectados:" +"");
                    System.out.println("Cantidad de fallecidos:" +"");
                    System.out.println("Porcentaje de infectado por muestras:" +"");
                    System.out.println("Porcentaje de fallecidos por infectados:" +"");
                    System.out.println("Cantidad de infectados por rango etario:" +"");
                    System.out.println("Cantidad de muertes por rango etario:" + "");


                    break;
                case "-p_casos":
                        if(args[i+1]==null || args[i+1]=="-p_muertes" ||args[i+1]=="-p_muertes"  )
                        {
                                //

                              //
                        }
                        else{
                          //  int n=Integer.parseInt(args[i+1]);

                            //}
                        }
                    break;
                case "-p_muertes":

                    break;
                case "-casos_edad":

                    break;
                default:

            }

        }


        //Declarar una variable FileReader
        FileReader fr = null;
        TInicio = System.nanoTime(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
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


                if(caract== 44 ){ //Analizo la coma y reseteo y almaceno el id
                    caract = fr.read();

                    System.out.print(id+" "); //guardo dato
                    id="";
                }
                if(caract==10)// si es salto de linea
                {  System.out.println(""); //guardo dato;
                    id="";
                }
                if(caract !=34 ) //cargo caracter si no es llave
                {id+=(char) caract;

                    if(caract==44)
                    { id=null;}

                }

                caract = fr.read();//Leer el siguiente carácter
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

        }TFin = System.nanoTime(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        System.out.println((TFin-TInicio)/10000);

    }
}