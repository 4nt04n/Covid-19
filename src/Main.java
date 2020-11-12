
import estructura.Persona;
import java.io.*;

class Main {

    public static void main (String[] args) {

        String nombreFichero = "csv/Covid19Casos-10.csv";
        boolean llave=true;
        String id="";String parametros;
        String[] datAux= new String[25];
        long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        int length=0;
        int infectEdad[]={0,0,0,0,0,0,0,0,0,0,0};
        int muertesEdad[]={0,0,0,0,0,0,0,0,0,0,0};
       float numMuertes =0,numInfect =0;
        int count=0,aux=0;
        Persona persona;


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


                if(caract== 44 ){ //Analizo la coma y almaceno y reseteo  el id
                    caract = fr.read();
                    datAux[count]=id; //guardo dato

                    count++;
                    id="";
                }
                if(caract==10)// si es salto de linea AGREGO PERSONA ENTERA
                {

                    datAux[count]=id;  //Almaceno ultimo valor

                    if(datAux[14].equals("SI")) //Cuento los fallecidos
                    {
                        numMuertes++;
                        aux=Integer.parseInt(datAux[2]);
                        for(int i =1;i<=10;i++){
                            if(aux<=i*10 && aux>(i*10-10))
                                muertesEdad[i-1]++;



                        }
                      /*  if(aux<=10){
                            muertesEdad[0]++;

                        }else if(aux<=20 && aux>10){
                            muertesEdad[1]++;

                        } else if(aux<=30 && aux>20){
                            muertesEdad[2]++;

                        }else if(aux<=40 && aux>30){
                            muertesEdad[3]++;

                        }else if(aux<=50 && aux>40){
                            muertesEdad[4]++;

                        }else if(aux<=60 && aux>50){
                            muertesEdad[5]++;

                        }else if(aux<=70 && aux>60){
                            muertesEdad[6]++;

                        }else if(aux<=80 && aux>70){
                            muertesEdad[7]++;

                        }else if(aux<=90 && aux>80){
                            muertesEdad[8]++;
                        }else if(aux<=100 && aux>90){
                            muertesEdad[9]++;
                        }*/


                    }

                    if(datAux[20].equals("Confirmado")) //Cuento infectados
                    {  numInfect++;
                        aux=Integer.parseInt(datAux[2]);
                        for(int i =1;i<=10;i++){
                            if(aux<=i*10 && aux>(i*10-10))
                                infectEdad[i-1]++;



                        }
                      /*  if(aux<=10){
                            infectEdad[0]++;

                        }else if(aux<=20 && aux>10){
                            infectEdad[1]++;

                        } else if(aux<=30 && aux>20){
                            infectEdad[2]++;

                        }else if(aux<=40 && aux>30){
                            infectEdad[3]++;

                        }else if(aux<=50 && aux>40){
                            infectEdad[4]++;

                        }else if(aux<=60 && aux>50){
                            infectEdad[5]++;

                        }else if(aux<=70 && aux>60){
                            infectEdad[6]++;

                        }else if(aux<=80 && aux>70){
                            infectEdad[7]++;

                        }else if(aux<=90 && aux>80){
                            infectEdad[8]++;
                        }else if(aux<=100 && aux>90){
                            infectEdad[9]++;
                        }*/




                    }




                    persona =new Persona(datAux);//almaceno en persona
                    count=0;
                    id="";
                    length++;

                }
                if(caract !=34 ) //cargo caracter si no es llave
                {
                    id+=(char) caract;
                    if(caract==44)
                    {
                        id=null;
                        datAux[count]=id; //guardo dato
                        count++;
                        id="";
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

        }TFin = System.nanoTime(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        System.out.println((TFin-TInicio)/10000);

        for (int i=0;args.length>i;i++){
            parametros=args[i];
            switch (parametros){
                case "-estad":

                    System.out.println("Cantidad total de muestras:" + length);
                    System.out.println("Cantidad total de infectados:" + numInfect);
                    System.out.println("Cantidad de fallecidos:" + numMuertes);
                    System.out.println("Porcentaje de infectado por muestras: %" + (numInfect/length)*100);
                    System.out.println("Porcentaje de fallecidos por infectados: %" + (numMuertes /length)*100);
                    System.out.println("Cantidad de infectados por rango etario de 10 años:");
                    for(int j=1;j<10;j++){
                      // System.out.println("Desde los " + (j*10-10) +" hasta los "+j*10+" años: "+infectEdad[j-1]);
                        System.out.println("| "+(j*10-10)+" a "+ (j*10)+" |= "+infectEdad[j-1]);
                    }



                    System.out.println("Cantidad de muertes por rango etario:" + "");
                    for(int j=1;j<10;j++){
                       // System.out.println("Desde los " + (j*10-10) +" hasta los "+j*10+" años: "+ muertesEdad[j-1]);
                        System.out.println("| "+(j*10-10)+" a "+ (j*10)+" |= "+muertesEdad[j-1]);
                    }

                    break;
                case "-p_casos":
                   
                    break;
                case "-p_muertes":

                    break;
                case "-casos_edad":

                    break;
                default:

            }

        }

    }
}