package estructura;

public class HashTable {
    private Nodo[] table;
    private int size;
    public HashTable(int size){
        this.table=new Nodo[size];
        this.size=size;
    }
    public void insert(Nodo nodo, String clave){
        table[hash(clave)]=nodo;

    }

    private int hash(String clave){
        int caracter,hashCode=0;

       for (int i=0;i<clave.length();i++)
      {
           caracter=clave.charAt(i);
           hashCode+=caracter*i;
       }
        return hashCode%size;
        }

}
