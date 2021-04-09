/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.pais;

import ejb.entidade.Pais;
import ejb.sessao.PaisFacade;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "paisCriarManagedBean")
@ViewScoped
public class PaisCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private PaisFacade paisFacade;
    
    @Inject
    private PaisManagedBean paisManagedBean;    
    
    private Pais pais;
   
    
    /**
     * Creates a new instance of PaisCriarManagedBean
     */
    public PaisCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            pais = new Pais();

            pais.setNome(paisManagedBean.getPais().getNome());
//////System.out.println("managedbean.pais.PaisCriarManagedBean.criarRegisto(): Teste 0: Nome : " + pais.getNome());                
            pais.setNacionalidade(paisManagedBean.getPais().getNacionalidade());
//////System.out.println("managedbean.pais.PaisCriarManagedBean.criarRegisto(): Teste 1: Nacionalidade : " + pais.getNacionalidade());                

            pais.setEliminado(Boolean.FALSE);

            paisFacade.create(pais);
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "País guardado com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            limpar();
            
            paisManagedBean.init();
            
        } catch (Exception e)
        {
            try
            {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tente novamente!", "Erro! Operação Não Realizada!"));
                this.userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void limpar()
    {
        paisManagedBean.setPais(new Pais());
    }    
}
