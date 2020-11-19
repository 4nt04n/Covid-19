
import estructura.AvlTree;
import estructura.GrupoProvincias;
import estructura.Persona;
import estructura.StackPersona;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Main {
    private static int length = 0; //cantidad de gente
    private static int infectEdad[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //infectador por rango de edad
    private static int muertesEdad[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //muertos por rango de edad
    private static float numMuertes = 0, numInfect = 0;

    public static void main(String[] args) throws Exception {
        String parametros = "";
        String dirDoc = "";
        long TInicio, TFin = 0, tiempo; //Variables para determinar el tiempo de ejecución
        boolean estad = false, pcasos = false, pmuertes = false, cEdad = false, cui = false;
        String años = null;
        int aux = 0, n = 24;
        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
        Date dt_1 = objSDF.parse("1900-01-01");
        AvlTree arbol = new AvlTree();

        for (int i = 0; args.length - 1 > i; i++) { //Control de Args Menu
            parametros = args[i];
            switch (parametros) {
                case "-estad":

                    estad = true;
                    break;
                case "-p_casos":

                    pcasos = true;
                    try {
                        if (i + 1 != args.length) {
                            n = Integer.parseInt(args[i + 1]);
                        }
                    } catch (Exception e) {


                    }
                    break;
                case "-p_muertes":
                    pmuertes = true;
                    try {
                        if (i + 1 != args.length) {
                            n = Integer.parseInt(args[i + 1]);
                        }

                    } catch (Exception e) {

                    }
                    break;
                case "-casos_edad":
                    cEdad = true;
                    try {
                        if (args[i + 1] != null) {
                            años = args[i + 1];
                        }
                    } catch (Exception e) {
                        System.out.println("Falta los años en -casos_edad años");
                        return;
                    }
                    break;

                case "-casos_cui":
                    cui = true;
                    try {
                        if (args[i + 1] != null)
                            dt_1 = objSDF.parse(args[i + 1]);
                    } catch (Exception E) {
                    }
                    break;
                default:

            }
        }
        dirDoc = args[args.length - 1];

        if (args.length == 0) //No envian argumentos
        {
            System.out.println("Faltan Parametros");
            return;
        }

        if ((pcasos && pmuertes) || (pcasos && cEdad) || (pcasos && cui) || (pmuertes && cui) || (cEdad && cui))//CD + BD + AD + AC + AB  Nos envian mas Parametros de los que van
        {
            System.out.println("Parametros Erroneos");
            return;
        }
        TInicio = System.nanoTime(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        if (pcasos || pmuertes) //Si nos piden -p_casos  o  -p_muertes
        {

            GrupoProvincias datos = insertP(estad, pcasos, pmuertes, cEdad, años, dirDoc); //Leo datos y filtro lo pedido
            StackPersona[] ordenado = datos.ordenar(); //ordeno el stack recibido


            for (int i = 0; i <= n - 1; i++) {
                if (ordenado[i].provincia != null) {
                    System.out.println("\n La provincia de " + ordenado[i].provincia + " tiene: " + ordenado[i].size + "\n");
                    aux = ordenado[i].size;
                    for (int j = 0; j < aux; j++) {
                        System.out.println(ordenado[i].topAndPop().toString());
                    }

                }

            }

        }
        if (cEdad) { //-casos_edad
            GrupoProvincias datos = insertP(estad, pcasos, pmuertes, cEdad, años, dirDoc);
            StackPersona[] pilas = datos.getStack();
            for (int i = 0; i <= 23; i++) {
                if (pilas[i].provincia != null) {
                    System.out.println("\n La provincia de " + pilas[i].provincia + " tiene " + pilas[i].size + " personas infectadas de " + años + " años" + "\n");
                    aux = pilas[i].size;
                    for (int j = 0; j < aux; j++) {
                        System.out.println(pilas[i].topAndPop().toString());
                    }
                }
            }
        }

        if (cui) {

            arbol = insertCui(estad, dt_1, dirDoc);
            System.out.println("Casos en cuidados intensivos: \n");
            arbol.printInOrder();
        }

        System.out.println();

        if (estad) {
            if (!cEdad && !pcasos && !pmuertes && !cui) {
                GrupoProvincias datos = insertP(estad, pcasos, pmuertes, cEdad, años, dirDoc);
            }
            estad();
        }

        TFin = System.nanoTime(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        System.out.println((TFin - TInicio) / 1000000);

    }

    public static void estad() {

        System.out.println("Cantidad total de muestras:" + length);
        System.out.println("Cantidad total de infectados:" + numInfect);
        System.out.println("Cantidad de fallecidos:" + numMuertes);
        System.out.println("Porcentaje de infectado por muestras: %" + (numInfect / length) * 100);
        System.out.println("Porcentaje de fallecidos por infectados: %" + (numMuertes / length) * 100);
        System.out.println("Cantidad de infectados por rango etario de 10 años:");
        for (int j = 1; j <= 10; j++) {
            // System.out.println("Desde los " + (j*10-10) +" hasta los "+j*10+" años: "+infectEdad[j-1]);
            System.out.println("| " + (j * 10 - 10) + " a " + (j * 10) + " |= " + infectEdad[j - 1]);
        }

        System.out.println("Cantidad de muertes por rango etario:" + "");
        for (int j = 1; j <= 10; j++) {
            // System.out.println("Desde los " + (j*10-10) +" hasta los "+j*10+" años: "+ muertesEdad[j-1]);
            System.out.println("| " + (j * 10 - 10) + " a " + (j * 10) + " |= " + muertesEdad[j - 1]);
        }


    }

    public static GrupoProvincias insertP(boolean estad, boolean casos, boolean muertes, boolean casoEdad, String años, String dirDoc) {

        //Declarar una variable FileReader
        String nombreFichero = dirDoc;
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
                    if (datAux[3].equals("Meses")) {
                        datAux[2] = "0";
                        datAux[3]="Años";
                    }


                    if (datAux[14].equals("SI")) //Cuento los fallecidos
                    {
                        if (estad) {
                            numMuertes++;
                            aux = Integer.parseInt(datAux[2]);
                            for (int i = 1; i <= 10; i++) {
                                if (aux <= i * 10 && aux > (i * 10 - 10))
                                    muertesEdad[i - 1]++;
                            }
                        }
                        if (muertes) {    //agrego una persona muerta si la bandera es true
                            persona = new Persona(datAux);//almaceno en persona
                            grupo.insertar(persona);
                        }


                    }

                    if (datAux[20].equals("Confirmado")) //Cuento infectados
                    {
                        if (estad) {
                            numInfect++;
                            aux = Integer.parseInt(datAux[2]);
                            for (int i = 1; i <= 10; i++) {
                                if (aux <= i * 10 && aux > (i * 10 - 10))
                                    infectEdad[i - 1]++;
                            }
                        }
                        if (casos) {
                            persona = new Persona(datAux);//almaceno en persona
                            grupo.insertar(persona);
                        }
                        if (casoEdad && (años.equals(datAux[2]))) {
                            persona = new Persona(datAux);//almaceno en persona
                            grupo.insertar(persona);
                        }
                    }

                    count = 0;  //Reinicio todos los valores para nueva persona
                    id = "";
                    length++;

                }
                if (caract != 34) //cargo caracter si no es comillas
                {
                    if (caract == '\n') { // Evito salto de linea
                        fr.read();
                        caract = fr.read();
                    }
                    if (caract != '\r') { // Evito salto de linea
                        id += (char) caract;
                    }
                    if (caract == 44) { // Cargo 0 si no hay dato
                        id = "0";
                        datAux[count] = id; //guardo dato
                        count++;
                        id = "";
                    }
                }
                caract = fr.read();//Leer el siguiente carácter
            }


        } catch (FileNotFoundException e) {                     //control de errores
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


        return grupo;

    }

    public static AvlTree insertCui(boolean estad, Date fecha, String dirDoc) {

        //Declarar una variable FileReader
        String nombreFichero = dirDoc;
        String id = "";
        String[] datAux = new String[25];
        int count = 0, aux = 0;
        Persona persona;
        FileReader fr = null;
        AvlTree arbol = new AvlTree();
        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
        Date dt_1 = fecha;
        Date dt_2 = null;
        boolean info = false;


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
                    if (info) {
                        datAux[count] = id;  //Almaceno ultimo valor
                        if (datAux[3] == "Meses") {
                            datAux[2] = "0";
                        }
                        if (datAux[13] != "0") {
                            dt_2 = objSDF.parse(datAux[13]);
                        } else {
                            dt_2 = objSDF.parse("0000-01-01");
                        }

                        if (datAux[14].equals("SI")) //Cuento los fallecidos
                        {
                            if (estad) {
                                numMuertes++;
                                aux = Integer.parseInt(datAux[2]);
                                for (int i = 1; i <= 10; i++) {
                                    if (aux <= i * 10 && aux > (i * 10 - 10))
                                        muertesEdad[i - 1]++;
                                }
                            }

                        }

                        if (datAux[20].equals("Confirmado")) //Cuento infectados
                        {
                            if (estad) {
                                numInfect++;
                                aux = Integer.parseInt(datAux[2]);
                                for (int i = 1; i <= 10; i++) {
                                    if (aux <= i * 10 && aux > (i * 10 - 10))
                                        infectEdad[i - 1]++;
                                }

                            }
                        }
                        if (dt_2.compareTo(dt_1) >= 0) {
                            persona = new Persona(datAux, dt_2);//almaceno en persona
                            arbol.insert(dt_2, persona);
                        }


                        count = 0;
                        id = "";
                        length++;
                    }
                    count = 0;
                    id = "";
                    info = true;
                }
                if ((caract != 34)) //cargo caracter si no es comillas
                {
                    if (caract == '\n') { //evito salto de lineas
                        fr.read();
                        caract = fr.read();
                    }


                    if (caract != '\r') { //evito salto de lineas
                        id += (char) caract;
                    }

                    if (caract == 44) {  //si es espacio vacio guardo un 0
                        id = "0";
                        datAux[count] = id; //guardo dato
                        count++;
                        id = "";

                    }


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

        }


        return arbol;

    }
}




