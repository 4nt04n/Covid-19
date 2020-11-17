
import estructura.GrupoProvincias;
import estructura.Persona;
import estructura.StackPersona;

import javax.sound.midi.Soundbank;
import java.io.*;

class Main {
    private static int length = 0;
    private static int infectEdad[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int muertesEdad[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static float numMuertes = 0, numInfect = 0;

    public static void main(String[] args) throws Exception {
        String parametros = "";

        boolean estad = false, pcasos = false, pmuertes = false, cEdad = false, cui = false;
        for (int i = 0; args.length > i; i++) {
            parametros = args[i];
            switch (parametros) {
                case "-estad":

                    estad = true;
                    break;
                case "-p_casos":

                    pcasos = true;

                    break;
                case "-p_muertes":
                    pmuertes = true;
                    break;
                case "-casos_edad":
                    cEdad = true;
                    break;

                case "-casos_cui":
                    cui = true;
                    break;
                default:

            }
        }
        if (args.length == 0) {
            System.out.println("Faltan Parametros");
            return;
        }

        if ((pcasos && pmuertes) || (pcasos && cEdad) || (pcasos && cui) || (pmuertes && cui) || (cEdad && cui))//CD + BD + AD + AC + AB
        {
            System.out.println("Parametros Erroneos");
            return;
        }

        if (pcasos || pmuertes) {
            GrupoProvincias datos = insertP(pcasos, pmuertes, estad);
            StackPersona[] ordenado = datos.ordenar();
            //  StackPersona[] ordenado= datos.prueba();
            for (int i = 23; i >= 0; i--) {
                if (ordenado[i].provincia != null) {
                    System.out.println("La provincia de " + ordenado[i].provincia + " tiene: " + ordenado[i].size);

                    for (int j = 0; j < ordenado[i].size; j++) {
                        System.out.println(ordenado[i].topAndPop().toString());
                    }

                }

            }

        }

    }
    public static void estad() {

        System.out.println("Cantidad total de muestras:" + length);
        System.out.println("Cantidad total de infectados:" + numInfect);
        System.out.println("Cantidad de fallecidos:" + numMuertes);
        System.out.println("Porcentaje de infectado por muestras: %" + (numInfect / length) * 100);
        System.out.println("Porcentaje de fallecidos por infectados: %" + (numMuertes / length) * 100);
        System.out.println("Cantidad de infectados por rango etario de 10 años:");
        for (int j = 1; j < 10; j++) {
            // System.out.println("Desde los " + (j*10-10) +" hasta los "+j*10+" años: "+infectEdad[j-1]);
            System.out.println("| " + (j * 10 - 10) + " a " + (j * 10) + " |= " + infectEdad[j - 1]);
        }


        System.out.println("Cantidad de muertes por rango etario:" + "");
        for (int j = 1; j < 10; j++) {
            // System.out.println("Desde los " + (j*10-10) +" hasta los "+j*10+" años: "+ muertesEdad[j-1]);
            System.out.println("| " + (j * 10 - 10) + " a " + (j * 10) + " |= " + muertesEdad[j - 1]);
        }


    }

    public static GrupoProvincias insertP(boolean casos, boolean muertes, boolean estad) {

        //Declarar una variable FileReader
        String nombreFichero = "csv/Covid19Casos-1000.csv";
        String id = "";
        String[] datAux = new String[25];
        int count = 0, aux = 0;
        Persona persona;
        FileReader fr = null;
        GrupoProvincias grupo = new GrupoProvincias();

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


                if (caract == 44) { //Analizo la coma y almaceno y reseteo  el id
                    caract = fr.read();
                    datAux[count] = id; //guardo dato

                    count++;
                    id = "";
                }
                if (caract == 10)// si es salto de linea AGREGO PERSONA ENTERA
                {

                    datAux[count] = id;  //Almaceno ultimo valor
                    persona = new Persona(datAux);//almaceno en persona
                    if (datAux[14].equals("SI")) //Cuento los fallecidos
                    {
                        numMuertes++;
                        aux = Integer.parseInt(datAux[2]);
                        for (int i = 1; i <= 10; i++) {
                            if (aux <= i * 10 && aux > (i * 10 - 10))
                                muertesEdad[i - 1]++;
                        }
                        if (muertes) {    //agrego una persona muerta si la bandera es true

                            grupo.insertar(persona);
                        }


                    }

                    if (datAux[20].equals("Confirmado")) //Cuento infectados
                    {
                        numInfect++;
                        aux = Integer.parseInt(datAux[2]);
                        for (int i = 1; i <= 10; i++) {
                            if (aux <= i * 10 && aux > (i * 10 - 10))
                                infectEdad[i - 1]++;
                        }

                        if (casos) {
                            grupo.insertar(persona);
                        }


                    }

                    count = 0;
                    id = "";
                    length++;

                }
                if ((caract != 34)) //cargo caracter si no es comillas
                {
                        if(caract=='\n')
                        {   fr.read();
                         caract=fr.read();  }


                    if(caract != '\r')
                    {id += (char) caract;}

                    if (caract == 44) {
                        id = null;
                        datAux[count] = id; //guardo dato
                        count++;
                        id = "";

                    }





                }
                caract = fr.read();//Leer el siguiente carácter
            }


        }
     catch(FileNotFoundException e)

    {
        //Operaciones en caso de no encontrar el fichero
        System.out.println("Error: Fichero no encontrado");
        //Mostrar el error producido por la excepción
        System.out.println(e.getMessage());
    } catch(Exception e)

    {
        //Operaciones en caso de error general
        System.out.println("Error de lectura del fichero");
        System.out.println(e.getMessage());
    } finally

    {
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


        return grupo;

}


}