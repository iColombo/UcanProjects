/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.profissao;

import ejb.entidade.Profissao;
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
@Named(value = "profissaoCriarManagedBean")
@ViewScoped
public class ProfissaoCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private ProfissaoFacade profissaoFacade;
    
    @Inject
    private ProfissaoManagedBean profissaoManagedBean;    
    
    private Profissao profissao;
   
    
    /**
     * Creates a new instance of ProfissaoCriarManagedBean
     */
    public ProfissaoCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            profissao = new Profissao();

            profissao.setDescricao(profissaoManagedBean.getProfissao().getDescricao());
System.out.println("managedbean.profissao.ProfissaoCriarManagedBean.criarRegisto(): Teste 0: Descrição : " + profissao.getDescricao());                    

            profissao.setEliminado(Boolean.FALSE);

            profissaoFacade.create(profissao);
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profissão guardada com sucesso!", "Operação Realizada Com Sucesso!"));
            
            this.userTransaction.commit();
            
            limpar();
            
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
    
    public void limpar()
    {
        profissaoManagedBean.setProfissao(new Profissao());
    }    
}
