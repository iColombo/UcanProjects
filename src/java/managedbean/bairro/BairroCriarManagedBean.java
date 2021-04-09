/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.bairro;

import ejb.entidade.Bairro;
import managedbean.municipio.*;
import ejb.entidade.Municipio;
import ejb.entidade.Provincia;
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
@Named(value = "bairroCriarManagedBean")
@ViewScoped
public class BairroCriarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;
    
    @EJB
    private BairroFacade bairroFacade;
    
    @Inject
    private BairroManagedBean bairroManagedBean;   
    @Inject
    private MunicipioManagedBean municipioManagedBean;    
    
    private Bairro bairro;    
    
    /**
     * Creates a new instance of BairroCriarManagedBean
     */
    public BairroCriarManagedBean()
    {
    }
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();
            
            bairro = bairroFacade.getInstancia();

            bairro.setNome(bairroManagedBean.getBairro().getNome());
System.out.println("managedbean.bairro.BairroCriarManagedBean.criarRegisto(): Teste 0: Nome : " + bairro.getNome());                
            bairro.setFkMunicipio(new Municipio(municipioManagedBean.getMunicipio().getPkMunicipio()));
System.out.println("managedbean.bairro.BairroCriarManagedBean.criarRegisto(): Teste 1: Município : " + bairro.getFkMunicipio().getNome());

            bairro.setEliminado(Boolean.FALSE);

            bairroFacade.create(bairro);
            
            bairroManagedBean.init();
             
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bairro guardado com sucesso!", "Operação Realizada Com Sucesso!"));
            
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
        bairroManagedBean.setBairro(new Bairro());
    }    
}
