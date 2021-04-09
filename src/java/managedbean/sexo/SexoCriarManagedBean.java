/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.sexo;

import ejb.entidade.Pais;
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
@Named(value = "sexoCriarManagedBean")
@ViewScoped
public class SexoCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private SexoFacade sexoFacade;
    
    @Inject
    private SexoManagedBean sexoManagedBean;    
    
    private Sexo sexo;
   
    
    /**
     * Creates a new instance of SexoCriarManagedBean
     */
    public SexoCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            sexo = new Sexo();

            sexo.setDescricao(sexoManagedBean.getSexo().getDescricao());
//////System.out.println("managedbean.sexo.PaisCriarManagedBean.criarRegisto(): Teste 0: Descrição : " + sexoManagedBean.getSexo().getDescricao());                
            sexo.setAbreviatura(sexoManagedBean.getSexo().getAbreviatura());
//////System.out.println("managedbean.sexo.PaisCriarManagedBean.criarRegisto(): Teste 1: Abreviatura : " + sexoManagedBean.getSexo().getAbreviatura());                

            sexo.setEliminado(Boolean.FALSE);

            sexoFacade.create(sexo);
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sxxo guardado com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
