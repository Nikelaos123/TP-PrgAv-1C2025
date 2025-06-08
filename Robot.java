public class Robot {
    private int id;
    private int carga;
    private String estado;
    private Coordenada ubicacion; // CLASE PARA LABURAR EN DONDE ESTA


    public Robot(int id){
        this.id = id;
        this.carga = 100;
        this.estado = "En espera";
    }

    public void cargarBateria(){
        this.carga = 100;
    }
}
