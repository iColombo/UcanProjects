/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.estadocivil;

import ejb.entidade.EstadoCivil;
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
@Named(value = "estadoCivilCriarManagedBean")
@SessionScoped
public class EstadoCivilCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private EstadoCivilFacade estadoCivilFacade;
    
    @Inject
    private EstadoCivilManagedBean estadoCivilManagedBean;    
    
    private EstadoCivil estadoCivil;    
    
    /**
     * Creates a new instance of EstadoCivilCriarManagedBean
     */
    public EstadoCivilCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            estadoCivil = new EstadoCivil();

            estadoCivil.setAbreviatura(estadoCivilManagedBean.getEstadoCivil().getAbreviatura());
//////System.out.println("managedbean.estadoCivil.EstadoCivilCriarManagedBean.criarRegisto(): Teste 0: Nome : " + pais.getNome());  
            estadoCivil.setDescricao(estadoCivilManagedBean.getEstadoCivil().getDescricao());
//////System.out.println("managedbean.estadoCivil.EstadoCivilCriarManagedBeanarManagedBean.criarRegisto(): Teste 1: Nacionalidade : " + pais.getNacionalidade());                

            estadoCivil.setEliminado(Boolean.FALSE);

            estadoCivilFacade.create(estadoCivil);
            
            estadoCivilManagedBean.init();
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado Civil guardado com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
        estadoCivilManagedBean.setEstadoCivil(new EstadoCivil());
    }     
    
}
