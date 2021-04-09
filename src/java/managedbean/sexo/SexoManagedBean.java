/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.sexo;

import ejb.entidade.Sexo;
import ejb.sessao.SexoFacade;
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
@Named(value = "sexoManagedBean")
@SessionScoped
public class SexoManagedBean implements Serializable
{

    @EJB
    private SexoFacade sexoFacade;
    
    private Sexo sexo;
    
    private List<Sexo> lista;    
    
    /**
     * Creates a new instance of SexoManagedBean
     */
    public SexoManagedBean()
    {
    }

    @PostConstruct
    public void init()
    {
        lista = sexoFacade.findAllOrderByDescricao();
    }
    
    public List<Sexo> getLista()
    {
        return lista;
    }

    public Sexo getSexo()
    {
        if (sexo == null)
        {
            sexo = new Sexo();
        }
        return sexo;
    }

    public void setSexo(Sexo sexo)
    {
        this.sexo = sexo;
    }    
    
}
