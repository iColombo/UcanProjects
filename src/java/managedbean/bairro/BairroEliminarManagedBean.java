/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.bairro;

import ejb.sessao.BairroFacade;
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
@Named(value = "bairroEliminarManagedBean")
@ViewScoped
public class BairroEliminarManagedBean implements Serializable
{
    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private BairroFacade bairroFacade;
         
    @Inject
    private BairroManagedBean bairroManagedBean;    

    /**
     * Creates a new instance of MunicipioEliminarManagedBean
     */
    public BairroEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            if (bairroManagedBean.getBairro().getPkBairro() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

System.out.println("managedbean.municipio.BairroEliminarManagedBean.eliminarRegisto(): Teste 0: Nome : " + bairroManagedBean.getBairro().getNome());               
System.out.println("managedbean.municipio.BairroEliminarManagedBean.eliminarRegisto(): Teste 1: Município : " + bairroManagedBean.getBairro().getFkMunicipio().getNome());

            bairroManagedBean.getBairro().setEliminado(Boolean.TRUE);

            bairroFacade.edit(bairroManagedBean.getBairro());
            
            bairroManagedBean.init();
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro removido com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
