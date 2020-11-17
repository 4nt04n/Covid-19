package estructura;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class GrupoProvincias {
    public int length=0;
    private StackPersona[] provincias=new StackPersona[25];//29 por ser primo y evitar colisiones
    public GrupoProvincias(){

        for (int i = 0; i < 25; i++) {
            this.provincias[i]=new StackPersona();
        }

    }
    public void insertar(Persona persona){
        this.length++;
        switch(persona.cargaProvincia){

            case "Buenos Aires":
                this.provincias[0].push(persona);

                break;
            case "Córdoba":
                this.provincias[1].push(persona);
               break;
            case "CABA":
                this.provincias[2].push(persona);
            break;
            case "Mendoza":
                this.provincias[3].push(persona);
            break;
            case "Formosa":
               this.provincias[4].push(persona);
            break;
            case "Santa Fe":
                this.provincias[5].push(persona);
            break;
            case "San Luis":
                this.provincias[6].push(persona);
            break;
            case "Tucumán":
                this.provincias[7].push(persona);
            break;
            case "Chaco":
                this.provincias[8].push(persona);
            break;
            case "Santa Cruz":
                this.provincias[9].push(persona);
            break;
            case "Neuquén":
                this.provincias[10].push(persona);
            break;
            case "Corrientes":
                this.provincias[11].push(persona);
            break;
            case "San Juan":
                this.provincias[12].push(persona);
            break;
            case "Jujuy":
                this.provincias[13].push(persona);
            break;
            case "La Pampa":
                this.provincias[14].push(persona);
            break;
            case "Salta":
                this.provincias[15].push(persona);
            break;
            case "Santiago del Estero":
                this.provincias[16].push(persona);
            break;
            case "Tierra del Fuego":
                this.provincias[17].push(persona);
            break;
            case "Catamarca":
                this.provincias[18].push(persona);
                break;
            case "La Rioja":
                this.provincias[19].push(persona);
                break;

            case "Misiones":
                this.provincias[20].push(persona);
                break;

            case "Río Negro":
                this.provincias[21].push(persona);
                break;

            case "Chubut":
                this.provincias[22].push(persona);
                break;

            default:
                this.provincias[23].push(persona);
                break;
        }


    }
    public StackPersona[] ordenar(){

            quicksort(this.provincias,0,23);
            return this.provincias;

    }
   private void quicksort (StackPersona [ ] item, int left, int right)
    {
        int i, j;
        StackPersona temp;
        i = left;
        j = right;
        do {
            while ( item [j].size > item [i].size && j>i) j-- ;
            if ( i<j ) {
                temp=item[i];
                item[i] = item[j];
                item[j] = temp;
                i++;
            }
            while ( item [i].size < item [j].size && i<j ) i++ ;
            if ( i<j ) {
                temp=item[i];
                item[i] = item[j];
                item[j] = temp;
                j--;
            }
        } while ( i<j );

        if ( i < right ) quicksort ( item, i+1, right );
        if ( left < j ) quicksort (item, left, j-1 );
    }
public StackPersona[] prueba(){

        return this.provincias;
}

}
