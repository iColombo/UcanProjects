/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.provincia;

import ejb.entidade.Provincia;
import ejb.sessao.ProvinciaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import managedbean.pais.PaisManagedBean;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "provinciaManagedBean")
@ApplicationScoped
public class ProvinciaManagedBean implements Serializable
{
    
    @EJB
    private ProvinciaFacade provinciaFacade;
    
    @Inject
    private PaisManagedBean paisManagedBean;

    private Provincia provincia;
    
    private List<Provincia> listaDeProvincias
                          , listaProvincias;
    
    private int idPaisEditar;
    
    /**
     * Creates a new instance of ProvinciaBean
     */
    public ProvinciaManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        listaDeProvincias = provinciaFacade.findAllOrderByNome();
    }

    public List<Provincia> findAllOrderByNome(int idPais)
    {
        return provinciaFacade.findAllOrderByNome(idPais);
    }     

    public List<Provincia> getListaDeProvincias()
    {
        return listaDeProvincias;
    }
    
    public Provincia getProvincia()
    {
        if (provincia == null)
        {
            provincia = provinciaFacade.getInstancia();
        }        
        return provincia;
    }

    public void setProvincia(Provincia provincia)
    {
        this.provincia = provincia;
    }   
    
    public int getIdPaisEditar()
    {
        if (idPaisEditar == 0)
        {
//System.out.println("Entrou . . .");
            if (provincia.getFkPais().getPkPais() != null)
                idPaisEditar = provincia.getFkPais().getPkPais();
        }        
        return idPaisEditar;
    }

    public void setIdPaisEditar(int idPaisEditar)
    {
        this.idPaisEditar = idPaisEditar;
    }       
    
}
