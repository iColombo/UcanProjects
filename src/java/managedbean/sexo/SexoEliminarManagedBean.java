/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.sexo;

import ejb.entidade.Sexo;
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
@Named(value = "sexoEliminarManagedBean")
@ViewScoped
public class SexoEliminarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private SexoFacade sexoFacade;
    
    @Inject
    private SexoManagedBean sexoManagedBean;
    
    /**
     * Creates a new instance of SexoEliminarManagedBean
     */
    public SexoEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (sexoManagedBean.getSexo().getPkSexo() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

//////System.out.println("managedbean.sexo.SexoEliminarManagedBean.eliminarRegisto(): Teste 0: Nome : " + sexoManagedBean.getSexo().getDescricao());                
//////System.out.println("managedbean.sexo.SexoEliminarManagedBean.eliminarRegisto(): Teste 1: Nacionalidade : " + sexoManagedBean.getSexo().getDescricao());   

            sexoManagedBean.getSexo().setEliminado(Boolean.TRUE);

            sexoFacade.edit(sexoManagedBean.getSexo());
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item eliminado com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            limpar();
            
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

    public void limpar()
    {
        sexoManagedBean.setSexo(new Sexo());
    }
    
}
