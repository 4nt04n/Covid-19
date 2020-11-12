package estructura;

import java.util.Stack;

public class HashTableProvincia {
    private StackPersona[] table;


    public HashTableProvincia(){
        this.table=new StackPersona[29];//29 por ser primo y evitar colisiones

    }
    private void insert(Persona persona){

        StackPersona provincia=buscar(persona.residenciaProvincia);
        if(provincia==null) {
           StackPersona stack= new StackPersona();
            stack.push(persona);
            table[hash(persona.residenciaProvincia)]= stack;

            return;
        }

        provincia.push(persona);
        table[hash(persona.residenciaProvincia)] = provincia ;



    }
    public StackPersona buscar (String clave){
        int num=hash(clave);

        if(table[num]!=null){
            return table[num];
        }
        return null;
    }

    private int hash(String clave){
        int caracter,hashCode=0;

       for (int i=0;i<clave.length();i++)
      {
           caracter=clave.charAt(i);
           hashCode+=caracter*i;
       }
        return hashCode%29;
        }
 public static void main(String[] args) {
    HashTableProvincia tablaPrueba = new HashTableProvincia();
    Persona p=new Persona();
    p.residenciaProvincia="Cordoba";
    tablaPrueba.insert(p);
     System.out.println(tablaPrueba.buscar("Cordoba"));
}
}
