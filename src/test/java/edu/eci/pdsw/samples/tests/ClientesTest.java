/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import edu.eci.pdsw.samples.entities.Cliente;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class ClientesTest {

    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
  
    @Test
    public void additems1() throws ExcepcionServiciosAlquiler{
    	
    }
    /**
 * 
 * Registro de Clientes:
 * 
 * 
 * 
 * Clases de equivalencia:
 * CE1: Cliente Ya registrado
 * CE2:Cliente sin registrar
 * 
 * 
 * 
 */
    @Test
    public void registrarclientetest1(){
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        Cliente cl=new Cliente("Bruce Willis",11519631,"666","Calle Falsa 123","example@example.com");
        try{sa.registrarCliente(cl);
            assertEquals(sa.consultarCliente(11519631),cl);
        }
        catch(ExcepcionServiciosAlquiler e){
        fail();
        }

    }
    @Test
    public void registrarclientetest2(){
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        Cliente cl=new Cliente("Bruce Willis",11519631,"666","Calle Falsa 123","example@example.com");
        try{sa.registrarCliente(cl);
            sa.registrarCliente(cl);
        }
        catch(ExcepcionServiciosAlquiler e){
        assertEquals(e.getMessage(),"El cliente con documento "+cl+" ya esta registrado.");
        }

    }
    
    
    
    
    
    
    
}
