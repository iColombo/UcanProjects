/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.provincia;

import ejb.sessao.ProvinciaFacade;
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
@Named(value = "provinciaEliminarManagedBean")
@ViewScoped
public class ProvinciaEliminarManagedBean implements Serializable
{
    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private ProvinciaFacade provinciaFacade;
         
    @Inject
    private ProvinciaManagedBean provinciaManagedBean;    

    /**
     * Creates a new instance of ProvinciaEliminarBean
     */
    public ProvinciaEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            if (provinciaManagedBean.getProvincia().getPkProvincia() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

System.out.println("managedbean.provincia.ProvinciaEliminarBean.eliminarRegisto(): Teste 0: Nome : " + provinciaManagedBean.getProvincia().getNome());                
System.out.println("managedbean.provincia.ProvinciaEliminarBean.eliminarRegisto(): Teste 1: Pais : " + provinciaManagedBean.getProvincia().getFkPais().getNome());

            provinciaManagedBean.getProvincia().setEliminado(Boolean.TRUE);

            provinciaFacade.edit(provinciaManagedBean.getProvincia());
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Província removida com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            limpar();
            
            provinciaManagedBean.init();
            
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
        provinciaManagedBean.setProvincia(provinciaFacade.getInstancia());
    }
    
}
