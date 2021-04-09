/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.pessoa;

import ejb.entidade.FicheiroAnexado;
import ejb.entidade.Pessoa;
import ejb.sessao.FicheiroAnexadoFacade;
import ejb.sessao.PessoaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import managedbean.provincia.ProvinciaManagedBean;
import util.Defs;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "pessoaManagedBean")
@SessionScoped
public class PessoaManagedBean implements Serializable
{

    @EJB
    private FicheiroAnexadoFacade grlFicheiroAnexadoFacade;    
    @EJB
    private PessoaFacade pessoaFacade;    

    @Inject
    private ProvinciaManagedBean provinciaManagedBean;
            
    private List<Pessoa> listaDePessoas;            
    
    private Pessoa pessoa;
    
    private int idPaisEditar
              , idProvinciaEditar;    
    
    /**
     * Creates a new instance of PessoaManagedBean
     */
    public PessoaManagedBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        listaDePessoas = pessoaFacade.findAllOrderByNome();
    }    
    
    public List<Pessoa> findAllOrderByNome()
    {
        return pessoaFacade.findAllOrderByNome();
    }    

    public Pessoa getPessoa()
    {
        if (pessoa == null)
        {
            pessoa = pessoaFacade.getInstancia();
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListaDePessoas()
    {
        return listaDePessoas;
    }
    
    /**
     * Carrega a foto do candidato de acordo o criterio especificado
     * @author ivandro-colombo
     * @param pessoa
     * @return
     */    
    public String carregaFoto(Pessoa pessoa)
    {
        int delimiter = 0;
        String resultado = null;
        String foto = null;
            
        for (FicheiroAnexado fa : grlFicheiroAnexadoFacade.findAll())
        {
            if (fa.getFkPessoa().equals(pessoa))
            {
                delimiter = fa.getFicheiro().indexOf("_", 0);
                foto = fa.getFicheiro().substring(0, delimiter);
System.err.println("managedbean.pessoa.PessoaManagedBean.carregaFoto(): Teste 0: " + foto);  
                if (foto.equals(Defs.FOTO_ATTACH))
                {
                    resultado = fa.getFicheiro();
System.err.println("managedbean.pessoa.PessoaManagedBean.carregaFoto(): Teste 1:" + resultado);
                }
            }
        }
        return resultado;
    }    
    
    /**
     * Devolve TRUE se a extensao do valor especificado for JPG de acordo o criterio
     * @author ivandro-colombo
     * @param file
     * @return
     */    
    public boolean renderedTabJPG(String file)
    {
        if (file != null && file.length() > 0)
        {
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabJPG(): Teste 0: " + file.length());            
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabJPG(): Teste 1: " + file);            
            String extensao = file.substring(file.lastIndexOf(".") + 1, file.length());
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabJPG(): Teste 2: " + extensao);            
            if (extensao.equals("jpeg") || extensao.equals("jpg"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }    

    /**
     * Devolve TRUE se a extensao do valor especificado for PNG de acordo o criterio
     * @author ivandro-colombo
     * @param file
     * @return
     */    
    public boolean renderedTabPNG(String file)
    {
        if (file != null && file.length() > 0)
        {
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabPNG(): Teste 0: " + file.length());            
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabPNG(): Teste 1: " + file);            
            String extensao = file.substring(file.lastIndexOf(".") + 1, file.length());
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabPNG(): Teste 2: " + extensao);            
            if (extensao.equals("png"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }    
    
    /**
     * Devolve TRUE se a extensao do valor especificado for PDF de acordo o criterio
     * @author ivandro-colombo
     * @param file
     * @return
     */    
    public boolean renderedTabPDF(String file)
    {
        if (file != null && file.length() > 0)
        {
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabPDF(): Teste 0: " + file.length());            
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabPDF(): Teste 1: " + file);            
            String extensao = file.substring(file.lastIndexOf(".") + 1, file.length());
//////System.err.println("managedbean.pessoa.PessoaManagedBean.renderedTabPDF(): Teste 2: " + extensao);            
            if (extensao.equals("pdf"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }    
    
    public int getIdPaisEditar()
    {
        if (idPaisEditar == 0)
        {
//System.out.println("Entrou . . .");
            if (pessoa.getFkProvincia().getFkPais().getPkPais() != null)
                idPaisEditar = pessoa.getFkProvincia().getFkPais().getPkPais();
        }        
        return idPaisEditar;
    }

    public void setIdPaisEditar(int idPaisEditar)
    {
        this.idPaisEditar = idPaisEditar;
    }    
    
    public int getIdProvinciaEditar()
    {
        if (idProvinciaEditar == 0)
        {
//System.out.println("Entrou . . .");
            if (pessoa.getFkProvincia().getPkProvincia() != null)
                idProvinciaEditar = pessoa.getFkProvincia().getPkProvincia();
        }        
        return idProvinciaEditar;
    }

    public void setIdProvinciaEditar(int idProvinciaEditar)
    {
        this.idProvinciaEditar = idProvinciaEditar;
    }     
    
    public String redirect()
    {
        return "faces/home.xhtml?faces-redirect=true";
    }
    
}
