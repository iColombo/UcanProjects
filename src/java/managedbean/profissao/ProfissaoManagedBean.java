/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.profissao;

import ejb.entidade.Profissao;
import ejb.sessao.ProfissaoFacade;
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
@Named(value = "profissaoManagedBean")
@SessionScoped
public class ProfissaoManagedBean implements Serializable
{

    @EJB
    private ProfissaoFacade profissaoFacade;
    
    private Profissao profissao;
    
    private List<Profissao> listaDeProfissoes;    
    
    /**
     * Creates a new instance of ProfissaoManagedBean
     */
    public ProfissaoManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        listaDeProfissoes = profissaoFacade.findAllOrderByNome();
    }    
    
    public List<Profissao> getListaDePaises()
    {
        return listaDeProfissoes;
    }

    public Profissao getProfissao()
    {
        if (profissao == null)
        {
            profissao = new Profissao();
        }
        return profissao;
    }

    public void setProfissao(Profissao profissao)
    {
        this.profissao = profissao;
    }    
    
}
