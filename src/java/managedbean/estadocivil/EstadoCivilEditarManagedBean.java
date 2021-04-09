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
@Named(value = "estadoCivilEditarManagedBean")
@SessionScoped
public class EstadoCivilEditarManagedBean implements Serializable
{
    
    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    
    @Inject
    private EstadoCivilManagedBean estadoCivilManagedBean;    

    /**
     * Creates a new instance of EstadoCivilEditarManagedBean
     */
    public EstadoCivilEditarManagedBean()
    {
    }
    
    public void editarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (estadoCivilManagedBean.getEstadoCivil().getPkEstadoCivil() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

//////System.out.println("managedbean.estadoCivil.estadoCivilEditarManagedBean.editarRegisto(): Teste 0: Nome : " + paisBean.getPais().getNome());                
//////System.out.println("managedbean.estadoCivil.estadoCivilEditarManagedBean.editarRegisto(): Teste 1: Nacionalidade : " + paisBean.getPais().getNacionalidade());   

            estadoCivilFacade.edit(estadoCivilManagedBean.getEstadoCivil());
            
            estadoCivilManagedBean.init();
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "País editado com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
