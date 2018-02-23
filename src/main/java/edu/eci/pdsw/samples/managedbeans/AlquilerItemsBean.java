/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    private ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    private Map<Long,Cliente> clientes;
    private String text="lala";
    public AlquilerItemsBean() {
        clientes=sp.getclientes();
    }
    public List<Cliente> getClientes() {
		List<Cliente> list = new ArrayList<Cliente>(sp.getclientes().values());
                return list;
    }
    public String gettext(){
        return "sdfs";
    }
}
