/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.pessoa;

import managedbean.pais.*;
import ejb.sessao.PessoaFacade;
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
@Named(value = "pessoaEliminarManagedBean")
@ViewScoped
public class PessoaEliminarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private PessoaFacade pessoaFacade;
    
    @Inject
    private PessoaManagedBean pessoaManagedBean;
    
    /**
     * Creates a new instance of PessoaEliminarManagedBean
     */
    public PessoaEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (pessoaManagedBean.getPessoa().getPkPessoa() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

System.out.println("managedbean.pessoa.PessoaEliminarManagedBean.eliminarRegisto(): Teste 0: Nome : " + pessoaManagedBean.getPessoa().getNomeCompleto());                
System.out.println("managedbean.pessoa.PessoaEliminarManagedBean.eliminarRegisto(): Teste 1: Nacionalidade : " + pessoaManagedBean.getPessoa().getFkPais().getNacionalidade());   

            pessoaManagedBean.getPessoa().setEliminado(Boolean.TRUE);

            pessoaFacade.edit(pessoaManagedBean.getPessoa());
            
            pessoaManagedBean.init();
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa eliminado com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
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
}
