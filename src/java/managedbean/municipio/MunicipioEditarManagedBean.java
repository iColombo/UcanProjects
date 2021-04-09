/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.municipio;

import ejb.entidade.Provincia;
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
@Named(value = "municipioEditarManagedBean")
@ViewScoped
public class MunicipioEditarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private MunicipioFacade municipioFacade;
         
    @Inject
    private MunicipioManagedBean municipioManagedBean;    
    
    /**
     * Creates a new instance of MunicipioEditarManagedBean
     */
    public MunicipioEditarManagedBean()
    {
    }

    public void editarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            if (municipioManagedBean.getMunicipio().getPkMunicipio() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

System.out.println("managedbean.municipio.MunicipioEditarManagedBean.editarRegisto(): Teste 0: Nome : " + municipioManagedBean.getMunicipio().getNome());                
System.out.println("managedbean.municipio.MunicipioEditarManagedBean.editarRegisto(): Teste 1: Pais : " + municipioManagedBean.getMunicipio().getFkProvincia().getNome());

            municipioManagedBean.getMunicipio().setFkProvincia(new Provincia(municipioManagedBean.getIdProvinciaEditar()));

            municipioFacade.edit(municipioManagedBean.getMunicipio());
            
            municipioManagedBean.init();
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Município editada com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
