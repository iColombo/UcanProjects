/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.municipio;

import ejb.entidade.Municipio;
import managedbean.provincia.*;
import ejb.entidade.Pais;
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
@Named(value = "municipioCriarManagedBean")
@ViewScoped
public class MunicipioCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private MunicipioFacade municipioFacade;
    
    @Inject
    private ProvinciaManagedBean provinciaManagedBean;   
    @Inject
    private MunicipioManagedBean municipioManagedBean;    
    
    private Municipio municipio;    
    
    /**
     * Creates a new instance of MunicipioCriarManagedBean
     */
    public MunicipioCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            municipio = new Municipio();//provinciaFacade.getInstancia();

            municipio.setNome(municipioManagedBean.getMunicipio().getNome());
//////System.out.println("managedbean.municipio.MunicipioCriarManagedBean.criarRegisto(): Teste 0: Nome : " + provincia.getNome());                
            municipio.setFkProvincia(new Provincia(provinciaManagedBean.getProvincia().getPkProvincia()));
//////System.out.println("managedbean.municipio.MunicipioCriarManagedBean.criarRegisto(): Teste 1: Pais : " + provincia.getFkPais().getNome());                

            municipio.setEliminado(Boolean.FALSE);

            municipioFacade.create(municipio);
            
            municipioManagedBean.init();
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Município guardado com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            limpar();
            
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
        municipioManagedBean.setMunicipio(municipioFacade.getInstancia());
    }    
}
