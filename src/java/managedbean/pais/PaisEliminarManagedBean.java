/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.pais;

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
@Named(value = "paisEliminarManagedBean")
@ViewScoped
public class PaisEliminarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private PaisFacade paisFacade;
    
    @Inject
    private PaisManagedBean paisManagedBean;
    
    /**
     * Creates a new instance of PaisEliminarManagedBean
     */
    public PaisEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (paisManagedBean.getPais().getPkPais() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

//////System.out.println("managedbean.pais.PaisEliminarManagedBean.eliminarRegisto(): Teste 0: Nome : " + paisBean.getPais().getNome());                
//////System.out.println("managedbean.pais.PaisEliminarManagedBean.eliminarRegisto(): Teste 1: Nacionalidade : " + paisBean.getPais().getNacionalidade());   

            paisManagedBean.getPais().setEliminado(Boolean.TRUE);

            paisFacade.edit(paisManagedBean.getPais());
            
            paisManagedBean.init();
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "País eliminado com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
