package estructura;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class GrupoProvincias {
    public int length = 0;
    private StackPersona[] provincias = new StackPersona[25];

    public GrupoProvincias() {

        for (int i = 0; i < 25; i++) {
            this.provincias[i] = new StackPersona();
        }

    }

    public void insertar(Persona persona) {
        this.length++;
        switch (persona.cargaProvincia) {

            case "Buenos Aires":
                this.provincias[0].push(persona);
                break;

            case "CABA":
                this.provincias[1].push(persona);
                break;
            case "Catamarca":
                this.provincias[2].push(persona);
                break;
            case "Chaco":
                this.provincias[3].push(persona);
                break;
            case "Chubut":
                this.provincias[4].push(persona);
                break;
            case "Corrientes":
                this.provincias[5].push(persona);
                break;
            case "Córdoba":
                this.provincias[6].push(persona);
                break;
            case "Formosa":
                this.provincias[7].push(persona);
                break;
            case "Jujuy":
                this.provincias[8].push(persona);
                break;
            case "La Pampa":
                this.provincias[9].push(persona);
                break;
            case "La Rioja":
                this.provincias[10].push(persona);
                break;
            case "Mendoza":
                this.provincias[11].push(persona);
                break;
            case "Misiones":
                this.provincias[12].push(persona);
                break;
            case "Neuquén":
                this.provincias[13].push(persona);
                break;
            case "Río Negro":
                this.provincias[14].push(persona);
                break;
            case "Salta":
                this.provincias[15].push(persona);
                break;
            case "San Juan":
                this.provincias[16].push(persona);
                break;
            case "San Luis":
                this.provincias[17].push(persona);
                break;
            case "Santa Cruz":
                this.provincias[18].push(persona);
                break;
            case "Santa Fe":
                this.provincias[19].push(persona);
                break;

            case "Santiago Del Estero":
                this.provincias[20].push(persona);
                break;

            case "Tierra Del Fuego":
                this.provincias[21].push(persona);
                break;

            case "Tucumán":
                this.provincias[22].push(persona);
                break;

            default:
                this.provincias[23].push(persona);
                break;
        }


    }

    public StackPersona[] ordenar() {

        //  quicksort(this.provincias,0,23);
        burbuja(this.provincias);
        return this.provincias;

    }
    
    public static void burbuja(StackPersona[] A) {
        int i, j;
        StackPersona aux;
        for (i = 0; i < A.length - 1; i++)
            for (j = 0; j < A.length - i - 1; j++)
                if (A[j + 1].size > A[j].size) {
                    aux = A[j + 1];
                    A[j + 1] = A[j];
                    A[j] = aux;
                }
    }


    public StackPersona[] getStack() {

        return this.provincias;
    }

}
