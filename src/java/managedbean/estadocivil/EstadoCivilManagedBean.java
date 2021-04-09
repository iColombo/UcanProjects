/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.estadocivil;

import ejb.entidade.EstadoCivil;
import ejb.sessao.EstadoCivilFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "estadoCivilManagedBean")
@SessionScoped
public class EstadoCivilManagedBean implements Serializable
{

    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    
    private List<EstadoCivil> listaDeEstadosCivis;      
    
    private EstadoCivil estadoCivil;
    
      
    
    /**
     * Creates a new instance of EstadoCivilManagedBean
     */
    public EstadoCivilManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        listaDeEstadosCivis = estadoCivilFacade.findAllOrderByDescricao();
    }    

    public List<EstadoCivil> getListaDeEstadosCivis()
    {
        return listaDeEstadosCivis;
    }

    public EstadoCivil getEstadoCivil()
    {
        if (estadoCivil == null)
        {
            estadoCivil = new EstadoCivil();
        }
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil)
    {
        this.estadoCivil = estadoCivil;
    }    
    
}
