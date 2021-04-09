/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.estadocivil;

import ejb.sessao.EstadoCivilFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "estadoCivilEliminarManagedBean")
@SessionScoped
public class EstadoCivilEliminarManagedBean implements Serializable
{
    
    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    
    @Inject
    private EstadoCivilManagedBean estadoCivilManagedBean;    

    /**
     * Creates a new instance of EstadoCivilEliminarManagedBean
     */
    public EstadoCivilEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (estadoCivilManagedBean.getEstadoCivil().getPkEstadoCivil() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

//////System.out.println("managedbean.estadoCivil.EstadoCivilEliminarManagedBean.eliminarRegisto(): Teste 0: Descrição : " + estadoCivilManagedBean.getEstadoCivil().getDescricao());                
//////System.out.println("managedbean.estadoCivil.EstadoCivilEliminarManagedBean.eliminarRegisto(): Teste 1: Abreviatura : " + estadoCivilManagedBean.getEstadoCivil().getAbreviatura());   

            estadoCivilManagedBean.getEstadoCivil().setEliminado(Boolean.TRUE);

            estadoCivilFacade.edit(estadoCivilManagedBean.getEstadoCivil());
            
            estadoCivilManagedBean.init();
                
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
