/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.provincia;

import ejb.entidade.Pais;
import ejb.entidade.Provincia;
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
import managedbean.pais.PaisManagedBean;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "provinciaCriarManagedBean")
@ViewScoped
public class ProvinciaCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private ProvinciaFacade provinciaFacade;
    
    @Inject
    private PaisManagedBean paisManagedBean;   
    @Inject
    private ProvinciaManagedBean provinciaManagedBean;    
    
    private Provincia provincia;    
    
    /**
     * Creates a new instance of ProvinciaCriarBean
     */
    public ProvinciaCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            provincia = new Provincia();//provinciaFacade.getInstancia();

            provincia.setNome(provinciaManagedBean.getProvincia().getNome());
//////System.out.println("managedbean.provincia.ProvinciaCriarManagedBean.criarRegisto(): Teste 0: Nome : " + provincia.getNome());                
            provincia.setFkPais(new Pais(paisManagedBean.getPais().getPkPais()));
//////System.out.println("managedbean.provincia.ProvinciaCriarManagedBean.criarRegisto(): Teste 1: Pais : " + provincia.getFkPais().getNome());                

            provincia.setEliminado(Boolean.FALSE);

            provinciaFacade.create(provincia);
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Província guardada com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
