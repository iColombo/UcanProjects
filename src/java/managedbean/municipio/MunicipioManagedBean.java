/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.municipio;

import ejb.entidade.Municipio;
import ejb.sessao.MunicipioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "municipioManagedBean")
@SessionScoped
public class MunicipioManagedBean implements Serializable
{

    @EJB
    private MunicipioFacade municipioFacade;
    
    @Inject
    private MunicipioManagedBean municipioManagedBean;

    private Municipio municipio;
    
    private List<Municipio> listaDeMunicipios
                          , listaMunicipios;
    
    private int idProvinciaEditar;    
    
    /**
     * Creates a new instance of MunicipioManagedBean
     */
    public MunicipioManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        listaDeMunicipios = municipioFacade.findAllOrderByNome();
    }    
    
    public List<Municipio> findAllOrderByNome(int idProvincia)
    {
        return municipioFacade.findAllOrderByNome(idProvincia);
    }     

    public List<Municipio> getListaDeMunicipios()
    {
        return listaDeMunicipios;
    }
    
    public Municipio getMunicipio()
    {
        if (municipio == null)
        {
            municipio = municipioFacade.getInstancia();
        }        
        return municipio;
    }

    public void setMunicipio(Municipio municipio)
    {
        this.municipio = municipio;
    }   
    
    public int getIdProvinciaEditar()
    {
        if (idProvinciaEditar == 0)
        {
//System.out.println("Entrou . . .");
            if (municipio.getFkProvincia().getPkProvincia() != null)
                idProvinciaEditar = municipio.getFkProvincia().getPkProvincia();
        }        
        return idProvinciaEditar;
    }

    public void setIdProvinciaEditar(int idProvinciaEditar)
    {
        this.idProvinciaEditar = idProvinciaEditar;
    }        
    
}
