/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.profissao;

import ejb.sessao.ProfissaoFacade;
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
@Named(value = "profissaoEditarManagedBean")
@ViewScoped
public class ProfissaoEditarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private ProfissaoFacade profissaoFacade;
    
    @Inject
    private ProfissaoManagedBean profissaoManagedBean;    
       
    
    /**
     * Creates a new instance of ProfissaoEditarManagedBean
     */
    public ProfissaoEditarManagedBean()
    {
    }
    
    public void editarRegisto()
    {
        try
        {
            this.userTransaction.begin();                        

            if (profissaoManagedBean.getProfissao().getPkProfissao() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }

System.out.println("managedbean.profissao.ProfissaoEditarManagedBean.criarRegisto(): Teste 0: Descrição : " + profissaoManagedBean.getProfissao().getDescricao());   

            profissaoFacade.edit(profissaoManagedBean.getProfissao());
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profissão editada com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            profissaoManagedBean.init();
            
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
