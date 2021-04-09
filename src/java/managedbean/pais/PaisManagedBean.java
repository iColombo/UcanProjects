/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.pais;

import ejb.entidade.Pais;
import ejb.sessao.PaisFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "paisManagedBean")
@ApplicationScoped
public class PaisManagedBean implements Serializable
{
    @EJB
    private PaisFacade paisFacade;
    
    private Pais pais;
    
    private List<Pais> listaDePaises;
    
    /**
     * Creates a new instance of PaisManagedBean
     */
    public PaisManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        listaDePaises = paisFacade.findAllOrderByNome();
    }    

    public List<Pais> getListaDePaises()
    {
        return listaDePaises;
    }

    public Pais getPais()
    {
        if (pais == null)
        {
            pais = new Pais();
        }
        return pais;
    }

    public void setPais(Pais pais)
    {
        this.pais = pais;
    }

}
