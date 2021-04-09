/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.sexo;

import ejb.sessao.SexoFacade;
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
@Named(value = "sexoEditarManagedBean")
@ViewScoped
public class SexoEditarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private SexoFacade sexoFacade;
    
    @Inject
    private SexoManagedBean sexoManagedBean;    
       
    
    /**
     * Creates a new instance of SexoEditarManagedBean
     */
    public SexoEditarManagedBean()
    {
    }
    
    public void editarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (sexoManagedBean.getSexo().getPkSexo() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

//////System.out.println("managedbean.sexo.SexoEditarManagedBean.editarRegisto(): Teste 0: Descrição : " + sexoManagedBean.getSexo().getDescricao());                
//////System.out.println("managedbean.sexo.SexoEditarManagedBean.editarRegisto(): Teste 1: Abreviatura : " + sexoManagedBean.getSexo().getAbreviatura());   

            sexoManagedBean.getSexo().setEliminado(Boolean.FALSE);

            sexoFacade.edit(sexoManagedBean.getSexo());
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item editado com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            sexoManagedBean.init();
            
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
