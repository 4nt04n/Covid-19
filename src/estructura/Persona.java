package estructura;

import java.util.Date;

public class Persona {
    public String idEventoCaso;
    public String sexo;//CHAR
    public String edad;//INT
    public String edadTipo; // 1 -> meses //// 0 -> anios BOOLEAN
    public String residenciaPais;
    public String residenciaProvincia;
    public String residenciaDepartamento;
    public String cargaProvincia;
    public String fechaInicioSintomas;
    public String fechaApertura;
    public String sepiApertura;
    public String fechaInternacion;
    public String cuidadoIntensivo; //Verdadero -> Estuvo en cuidado BOOLEAN
    public String fechaCuidadoIntensivo;
    public String fallecido; //Verdadero->Muerto BOOLEAN
    public String fechaFallecimiento;
    public String asistenciaRespiratoriaMecanica; //Verdadero-> Tuvo asistencia BOOLEAN
    public String cargaProvinciaId; // INT
    public String origenFinanciamiento; //Verdadero-> Tenia obra social BOOLEAN
    public String clasificacion;
    public String clasificacionResumen;
    public String residenciaProvinciaId; //INT
    public String fechaDiagnostico;
    public String residenciaDepartamentoId;// INT
    public String ultimaActualizacion; //Fecha
    public Date fechaCui;

    public Persona(String[] fila) {
        idEventoCaso=fila[0];
        sexo=fila[1];
        edad=fila[2];
        edadTipo=fila[3];
        residenciaPais=fila[4];
        residenciaProvincia=fila[5];
        residenciaDepartamento=fila[6];
        cargaProvincia =fila[7];
        fechaInicioSintomas=fila[8];
        fechaApertura=fila[9];
        sepiApertura =fila[10];
        fechaInternacion=fila[11];
        cuidadoIntensivo=fila[12];
        fechaCuidadoIntensivo=fila[13];
        fallecido=fila[14];
        fechaFallecimiento=fila[15];
        asistenciaRespiratoriaMecanica =fila[16];
        cargaProvinciaId=fila[17];
        origenFinanciamiento =fila[18];
        clasificacion=fila[19];
        clasificacionResumen=fila[20];
        residenciaProvinciaId =fila[21];
        fechaDiagnostico =fila[22];
        residenciaDepartamentoId=fila[23];
        ultimaActualizacion=fila[24];


    }
    public Persona(String[] fila, Date cui) {
        idEventoCaso = fila[0];
        sexo = fila[1];
        edad = fila[2];
        edadTipo = fila[3];
        residenciaPais = fila[4];
        residenciaProvincia = fila[5];
        residenciaDepartamento = fila[6];
        cargaProvincia = fila[7];
        fechaInicioSintomas = fila[8];
        fechaApertura = fila[9];
        sepiApertura = fila[10];
        fechaInternacion = fila[11];
        cuidadoIntensivo = fila[12];
        fechaCuidadoIntensivo = fila[13];
        fallecido = fila[14];
        fechaFallecimiento = fila[15];
        asistenciaRespiratoriaMecanica = fila[16];
        cargaProvinciaId = fila[17];
        origenFinanciamiento = fila[18];
        clasificacion = fila[19];
        clasificacionResumen = fila[20];
        residenciaProvinciaId = fila[21];
        fechaDiagnostico = fila[22];
        residenciaDepartamentoId = fila[23];
        ultimaActualizacion = fila[24];
        fechaCui=cui;

    }


    public Persona(){

    }

    @Override
    public String toString() {
        return  ' ' + idEventoCaso + ' ' + sexo + ' ' +edad + ' ' +



                edadTipo + ' ' +
                residenciaPais + ' ' +
                residenciaProvincia + ' ' +
                residenciaDepartamento + ' ' +
                cargaProvincia + ' ' +
                fechaInicioSintomas + ' ' +
                fechaApertura + ' ' +
                sepiApertura + ' ' +
                fechaInternacion + ' ' +
                cuidadoIntensivo + ' ' +
                fechaCuidadoIntensivo + ' ' +
                fallecido + ' ' +
                fechaFallecimiento + ' ' +
                 asistenciaRespiratoriaMecanica + ' ' +
                cargaProvinciaId + ' ' +
                origenFinanciamiento + ' ' +
                clasificacion + ' ' +
                clasificacionResumen + ' ' +
                residenciaProvinciaId + ' ' +
                fechaDiagnostico + ' ' +
                residenciaDepartamentoId + ' ' +
                ultimaActualizacion + ' ' ;
    }
}