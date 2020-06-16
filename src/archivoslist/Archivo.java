/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivoslist;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chelis
 */
public class Archivo {
    
    private final String NomArch="Datos.txt";
    List<Atributos> Datos = new ArrayList<>();
    
    public boolean verificaArch(){
    File archivo = new File(NomArch);
    if (!archivo.exists()) return false;
    else return true;
    }
    
    public boolean Grabar(List<Atributos> Datos, int accion){
        //0 = anexa informacion
        //1 = reactualiza toda lainformacion
        FileWriter archivo;
        try{
            if(accion == 0){
                archivo = new FileWriter(NomArch, true);
            }else{
                archivo = new FileWriter(NomArch);
            }
           
            try(BufferedWriter bw = new BufferedWriter(archivo)){
              for(Atributos dato : Datos){
                  bw.write(conviertegson(dato) + "\n");
              }
              bw.close();
            }
            archivo.close();
        }catch(Exception ex){ return false; }
    return true;
    }
    
    public boolean leer(){
        String cadena="";
        try{
            FileReader archivo = new FileReader(NomArch);
            BufferedReader br = new BufferedReader(archivo);
            while((cadena = br.readLine()) != null ){
                Datos.add(convierteClase(cadena));
            }
            br.close();
            archivo.close();
        } catch(Exception ex){ return false; }
    return true;
    }
    
    public void Agregar(Atributos dato){
    Datos.add(dato);
    }
    
    private String conviertegson(Atributos dato){
    Gson gson = new Gson();
    return gson.toJson(dato);
    }
    
    private Atributos convierteClase(String dato){
    Gson gson = new Gson();
    return gson.fromJson(dato, Atributos.class);
    
    }
    
    public List<Atributos>getDatos(){
    return Datos;
    }
    
}
