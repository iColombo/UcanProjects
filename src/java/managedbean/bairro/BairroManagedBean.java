/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean.bairro;

import ejb.entidade.Bairro;
import ejb.sessao.BairroFacade;
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
@Named(value = "bairroManagedBean")
@SessionScoped
public class BairroManagedBean implements Serializable
{

    @EJB
    private BairroFacade bairroFacade;    

    @Inject
    private BairroManagedBean bairroManagedBean;
            
    private List<Bairro> listaDeBairros;            
    
    private Bairro bairro;   
    
    private int idMunicipioEditar;  
    
    /**
     * Creates a new instance of BairroManagedBean
     */
    public BairroManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        listaDeBairros = bairroFacade.findAllOrderByNome();
    }    
    
    public List<Bairro> findAllOrderByNome(int idMunicipio)
    {
        return bairroFacade.findAllOrderByNome(idMunicipio);
    }    
    
    public List<Bairro> getListaDeBairros()
    {
        return listaDeBairros;
    }

    public Bairro getBairro()
    {
        if (bairro == null)
        {
            bairro = bairroFacade.getInstancia();
        }        
        return bairro;
    }

    public void setBairro(Bairro bairro)
    {
        this.bairro = bairro;
    }
    
    public int getIdMunicipioEditar()
    {
        if (idMunicipioEditar == 0)
        {
//System.out.println("Entrou . . .");
            if (bairro.getFkMunicipio().getPkMunicipio() != null)//Check this urgently
                idMunicipioEditar = bairro.getFkMunicipio().getPkMunicipio() ;
        }        
        return idMunicipioEditar;
    }

    public void setIdMunicipioEditar(int idMunicipioEditar)
    {
        this.idMunicipioEditar = idMunicipioEditar;
    }     
    
}
