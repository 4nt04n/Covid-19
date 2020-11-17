package estructura;

public class StackPersona {
    private NodoPersona cima;
   public int size;
   public String provincia;

    public StackPersona() {
        this.cima = null;
        this.size=0;
    }

    public void push(Persona persona) {
        cima = new NodoPersona(cima, persona);
        if(size==0)
        { provincia=persona.cargaProvincia;}
        size++;
    }

    public void pop() throws Exception {
        if (cima == null)
            throw new Exception("Stack Empty");
        cima = cima.next;
        size--;
    }
    public Persona top() throws Exception {
        if (cima == null)
            throw new Exception("Stack Empty");
        return cima.persona;
    }
    public Persona topAndPop() throws Exception {
        Persona persona = top();
        pop();
        return persona;
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return cima == null;
    }

}
