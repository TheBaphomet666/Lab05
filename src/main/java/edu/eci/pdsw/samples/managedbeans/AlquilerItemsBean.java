/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import java.sql.Date;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    private ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    private Map<Long,Cliente> clientes;
    private Map<Integer,Long> items;
    private String nombre="nombre";
    private long documento=0;
    private String telefono="telefono";
    private String direccion="direccion";
    private String email="email";
    private Cliente current;
    private int id;
    private int dias;
    private java.sql.Date fecha;
    private long multa;
    private long costo;
    
    
    public AlquilerItemsBean() {
        clientes=sp.getclientes();
        
    }
    public List<Cliente> getClientes() {
		List<Cliente> list = new ArrayList<Cliente>(sp.getclientes().values());
                return list;
    }
    
    public List<ItemRentado> getItems(){
                List<ItemRentado> list = current.getRentados();
                return list;
    }
    
    
    
    public void setNombre(String nombree){
        nombre=nombree;
    }
    public String getNombre(){
        //System.out.println("ME LAMARON");
        return nombre;
    }
    public void setDocumento(long documentoo){
        documento=documentoo;
    }
    public long getDocumento(){
        return documento;
    }
    public void setTelefono(String telefonoo){
        telefono=telefonoo;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setDireccion(String direccionn){
        direccion=direccionn;
    }
    public String getDireccion(){
        return direccion;
    }
    public void setEmail(String emaill){
        email=emaill;
    }
    public String getEmail(){
        return email;
    }
    public void registrar(){
        //System.out.println("SE ESTA REGISTRANDO"+nombre+telefono+direccion+email);
        Cliente c = new Cliente(nombre,documento,telefono,direccion,email);
        //System.out.println("NOMBRE="+c.getNombre());
        try{
        sp.registrarCliente(c);
        }
        catch ( Exception e){
            addMessage("Ese Cliente ya se ha registrado");
        }
            
    }
    
    public void setId(int ide){
        id = ide;
    }
    
    public int getId(){
        return id;
    }
    
    public void setDias(int diass){
        dias = diass;
        java.util.Date nfecha = new java.util.Date();
        fecha = new java.sql.Date(nfecha.getTime());
        
    }
    
    public int getDias(){
        return dias;
    }
    
    public long multa(int idem){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date fechaactual = new java.sql.Date(utilDate.getTime());
        try{
            multa = sp.consultarMultaAlquiler(idem,fechaactual);
        }
        catch(Exception e){
            addMessage("Item no encontrado");
        }
        return multa;
    }
    
    
    
    public void registrarItem(){
        try{
          sp.registrarAlquilerCliente(fecha,current.getDocumento(),sp.consultarItem(id),dias);
        }
        catch(Exception e){
            addMessage("Ese Item ya se ha registrado");
        }
    }
    
    
   
    public long costo(int ide,int diee){
        id=ide;
        dias=diee;
        try{
            costo = sp.consultarCostoAlquiler(id,dias);
            return costo;
        }
        catch ( Exception e){
            addMessage("Item no encontrado en la base de datos");
        }
        return costo;
    }
    
    
        public void addMessage(String summary) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
        public void registerAction(ActionEvent actionEvent) {
           // System.out.println("Sreg"+nombre+telefono+direccion+email);
            registrar();
    }
    
    public void itemAction(ActionEvent actionEvent){
            registrarItem();
    }
        
        public void alquiler(Cliente c) throws IOException{
            current=c;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/videotienda/RegistroClienteItem.xhtml");
        }
        
    public void home()throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/videotienda/RegistroClientes.xhtml");
    }
}
