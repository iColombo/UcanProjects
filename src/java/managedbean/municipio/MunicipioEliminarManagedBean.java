/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.municipio;

import ejb.sessao.MunicipioFacade;
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
@Named(value = "municipioEliminarManagedBean")
@ViewScoped
public class MunicipioEliminarManagedBean implements Serializable
{
    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private MunicipioFacade municipioFacade;
         
    @Inject
    private MunicipioManagedBean municipioManagedBean;    

    /**
     * Creates a new instance of MunicipioEliminarManagedBean
     */
    public MunicipioEliminarManagedBean()
    {
    }
    
    public void eliminarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            if (municipioManagedBean.getMunicipio().getPkMunicipio() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

System.out.println("managedbean.municipio.MunicipioEliminarManagedBean.eliminarRegisto(): Teste 0: Nome : " + municipioManagedBean.getMunicipio().getNome());                
System.out.println("managedbean.municipio.MunicipioEliminarManagedBean.eliminarRegisto(): Teste 1: Provincia : " + municipioManagedBean.getMunicipio().getFkProvincia().getNome());

            municipioManagedBean.getMunicipio().setEliminado(Boolean.TRUE);

            municipioFacade.edit(municipioManagedBean.getMunicipio());
            
            municipioManagedBean.init();
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Município removido com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
