package estructura;

public class Persona {
    public String idEventoCaso;
    public char sexo;
    public int edad;
    public boolean edadTipo; // 1 -> meses //// 0 -> anios
    public String residenciaPais;
    public String residenciaProvincia;
    public String residenciaDepartamento;
    public String cargaProvincia;
    public String fechaInicioSintomas;
    public String fechaApertura;
    public String sepiApertura;
    public String fechaInternacion;
    public boolean cuidadoIntensivo; //Verdadero -> Estuvo en cuidado
    public String fechaCuidadoIntensivo;
    public boolean fallecido; //Verdadero->Muerto
    public String fechaFallecimiento;
    public boolean asistenciaRespiratoriaMecanica; //Verdadero-> Tuvo asistencia
    public int cargaProvinciaId;
    public boolean origenFinanciamiento; //Verdadero-> Tenia obra social
    public String clasificacion;
    public String clasificacionResumen;
    public int residenciaProvinciaId;
    public String fechaDiagnostico;
    public int residenciaDepartamentoId;
    public String ultimaActualizacion; //Fecha
    public Persona(String[] fila){


    }
}
