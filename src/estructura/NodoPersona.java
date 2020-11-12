package estructura;

public class NodoPersona {
    public Persona persona=null;
    public NodoPersona next=null;

    public NodoPersona(NodoPersona next, Persona persona) {
        this.next = next;
        this.persona = persona;
    }
}
