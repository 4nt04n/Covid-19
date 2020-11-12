package estructura;

public class StackPersona {
    private NodoPersona cima;
    private int size;

    public void push( Persona persona) {
        cima = new NodoPersona(cima, persona);
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
