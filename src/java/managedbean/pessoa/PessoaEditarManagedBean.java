/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.pessoa;

import ejb.entidade.Bairro;
import ejb.entidade.EstadoCivil;
import ejb.entidade.FicheiroAnexado;
import ejb.entidade.Municipio;
import ejb.entidade.Pais;
import ejb.entidade.Pessoa;
import ejb.entidade.Provincia;
import ejb.entidade.Sexo;
import ejb.sessao.FicheiroAnexadoFacade;
import ejb.sessao.PessoaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import managedbean.bairro.BairroManagedBean;
import managedbean.estadocivil.EstadoCivilManagedBean;
import managedbean.municipio.MunicipioManagedBean;
import managedbean.pais.PaisManagedBean;
import managedbean.provincia.ProvinciaManagedBean;
import managedbean.sexo.SexoManagedBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import util.Defs;
import util.FicheiroModelo;

/**
 *
 * @author ivandro-colombo
 */
@Named(value = "pessoaEditarManagedBean")
@ViewScoped
public class PessoaEditarManagedBean implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private FicheiroAnexadoFacade ficheiroAnexadoFacade;
    @EJB
    private PessoaFacade pessoaFacade;

    @Inject
    private BairroManagedBean bairroManagedBean;    
    @Inject
    private EstadoCivilManagedBean estadoCivilManagedBean;     
    @Inject
    private MunicipioManagedBean municipioManagedBean;    
    @Inject
    private PaisManagedBean paisManagedBean;
    @Inject
    private PessoaCriarManagedBean pessoaCriarManagedBean;    
    @Inject
    private PessoaManagedBean pessoaManagedBean;
    @Inject
    private ProvinciaManagedBean provinciaManagedBean;
    @Inject
    private SexoManagedBean sexoManagedBean;
    

    private FicheiroAnexado ficheiroAnexado;
    private Bairro bairro;
//    private Pessoa pessoa;

    ///Variable that used for upload doc's
    private UploadedFile uploadedFile;
    private String pathDocument;

    /**
     * Creates a new instance of PessoaEditarManagedBean
     */
    public PessoaEditarManagedBean()
    {      
    }

    @PostConstruct
    public void init()
    {
        bairro = new Bairro();
        pathDocument = Defs.getPathWildFly();
//        pessoa = new Pessoa();  
    }     
    
    public void editarRegisto()
    {
        try
        {
            this.userTransaction.begin();

            if (pessoaManagedBean.getPessoa().getPkPessoa() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }
            
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 0: Número de Contribuinte : " + pessoaManagedBean.getPessoa().getNumeroContribuinte());
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 1: Nome : " + pessoaManagedBean.getPessoa().getNomeCompleto());
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 2: Data de Nascimento : " + pessoaManagedBean.getPessoa().getDataNascimento());
            
            if (sexoManagedBean.getSexo() != null && sexoManagedBean.getSexo().getPkSexo() != null)
            {
                pessoaManagedBean.getPessoa().setFkSexo(new Sexo(sexoManagedBean.getSexo().getPkSexo()));
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 3: Sexo : " + pessoaManagedBean.getPessoa().getFkSexo());
            }
                    

System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 4: Nome Pai : " + pessoaManagedBean.getPessoa().getNomePai());
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 5: Nome Mae : " + pessoaManagedBean.getPessoa().getNomeMae());

            if (estadoCivilManagedBean.getEstadoCivil() != null && estadoCivilManagedBean.getEstadoCivil().getPkEstadoCivil() != null)
            {
                pessoaManagedBean.getPessoa().setFkEstadoCivil(new EstadoCivil(estadoCivilManagedBean.getEstadoCivil().getPkEstadoCivil()));
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 6: Estado Civil : " + pessoaManagedBean.getPessoa().getFkEstadoCivil());
            }

            if (paisManagedBean.getPais() != null && paisManagedBean.getPais().getPkPais() != null)
            {
                pessoaManagedBean.getPessoa().setFkPais(new Pais(paisManagedBean.getPais().getPkPais()));
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 7: País : " + pessoaManagedBean.getPessoa().getFkPais());
            }
            
            if (provinciaManagedBean.getProvincia() != null && provinciaManagedBean.getProvincia().getPkProvincia() != null)
            {
                pessoaManagedBean.getPessoa().setFkProvincia(new Provincia(provinciaManagedBean.getProvincia().getPkProvincia()));
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 8: Província : " + pessoaManagedBean.getPessoa().getFkProvincia());
            }            
           
            if (municipioManagedBean.getMunicipio() != null && municipioManagedBean.getMunicipio().getPkMunicipio() != null)
            {
                pessoaManagedBean.getPessoa().setFkMunicipio(new Municipio(municipioManagedBean.getMunicipio().getPkMunicipio()));
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 9: Município : " + pessoaManagedBean.getPessoa().getFkMunicipio());
            }             

            if (bairroManagedBean.getBairro() != null && bairroManagedBean.getBairro().getPkBairro() != null)
            {
                pessoaManagedBean.getPessoa().setFkBairro(new Bairro(bairroManagedBean.getBairro().getPkBairro()));
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 10: Bairro : " + pessoaManagedBean.getPessoa().getFkBairro());
            }             

System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 11: Rua : " + pessoaManagedBean.getPessoa().getRua());
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 12: Número da Casa : " + pessoaManagedBean.getPessoa().getNumCasa());          
            
System.out.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): Teste 13: Estado : " + pessoaManagedBean.getPessoa().getEliminado());                      

            pessoaFacade.edit(pessoaManagedBean.getPessoa());
            
            limpar();
            
            pessoaManagedBean.init();                

            //Put here the code that response to attach files
            if (pessoaCriarManagedBean.getListaFicheirosCarregados() != null && !pessoaCriarManagedBean.getListaFicheirosCarregados().isEmpty())
            {
                for (int i = 0; i < pessoaCriarManagedBean.getListaFicheirosCarregados().size(); i++)
                {
                    int index = pessoaCriarManagedBean.getListaFicheirosCarregados().get(i).getNomeOriginal().lastIndexOf(".");
                    String nome = pessoaCriarManagedBean.getListaFicheirosCarregados().get(i).getNomeOriginal().substring(0, index);
System.err.println("managedbean.pessoa.PessoaCriarMPessoaEditarManagedBeananagedBean.editarRegisto(): Teste 14.0: " + nome);

                    Calendar calendar = GregorianCalendar.getInstance();

                    String nomeFile = nome + "_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_"
                            + calendar.get(Calendar.DATE) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE)
                            + "_" + calendar.get(Calendar.SECOND) + pessoaCriarManagedBean.getListaFicheirosCarregados().get(i).getCodigo();

System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.1: Origem  : " + pessoaCriarManagedBean.getListaFicheirosCarregados().get(i).getCaminho());
System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.2: Destino : " + (pathDocument + nomeFile));
System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.3: Nome : " +  nomeFile);
                    Path origem = Paths.get(pessoaCriarManagedBean.getListaFicheirosCarregados().get(i).getCaminho());
                    Path destino = Paths.get((pathDocument + nomeFile));

                    Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

                    //attach
                    ficheiroAnexado = new FicheiroAnexado();
System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.4: Anexo   : " + ficheiroAnexado);                    
                    ficheiroAnexado.setFicheiro(destino.getFileName().toString());
System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.5: Ficheiro: " + ficheiroAnexado.getFicheiro());                    

                    ficheiroAnexado.setFkPessoa(pessoaManagedBean.getPessoa());
System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.6: Pessoa  : " + ficheiroAnexado.getFkPessoa());                    

                    for (FicheiroAnexado fa : ficheiroAnexadoFacade.findAll())
                    {
                        if (fa.getFkPessoa().equals(pessoaManagedBean.getPessoa()))
                        {
                            fa = ficheiroAnexado;
System.err.println("managedbean.pessoa.PessoaEditarManagedBean.editarRegisto(): 14.7: Pessoa  : " + fa.getFkPessoa());                             
                            ficheiroAnexadoFacade.edit(fa);
                            
                            ficheiroAnexado = null;
                        }
                    }            
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa edtada com sucesso!", "Operação Realizada Com Sucesso!"));        
            
            this.userTransaction.commit();

        }
        catch (Exception e)
        {
            try
            {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tente novamente!", "Erro! Operação Não Realizada!"));
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                e.printStackTrace();
            }
        }
    }

    public void limpar()
    {
        pessoaManagedBean.setPessoa(pessoaFacade.getInstancia());
    }

//////    /**
//////     * Carrega um ficheiro de acordo o criterio especificado
//////     *
//////     * @author ivandro-colombo
//////     * @param evento
//////     */
//////    public void upload(FileUploadEvent evento)
//////    {
//////        UploadedFile ficheiroOriginal = evento.getFile();
//////
//////        int delimiter = ficheiroOriginal.getFileName().indexOf("_", 0);
//////        String nomeFicheiro = ficheiroOriginal.getFileName().substring(0, delimiter);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 0.0: " + nomeFicheiro);
//////
//////        if (nomeFicheiro.equals(Defs.FOTO_ATTACH))
//////        {
//////            try
//////            {
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 0  : ");            
//////                Path ficheiroTemporario = Files.createTempFile(null, null);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 1  : " + ficheiroTemporario);            
//////                Files.copy(ficheiroOriginal.getInputStream(), ficheiroTemporario, StandardCopyOption.REPLACE_EXISTING);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 2  : ");            
//////                FicheiroModelo ficheiroModelo = new FicheiroModelo();
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 3  : " + ficheiroModelo);            
//////                String extensao = ficheiroOriginal.getFileName().substring(ficheiroOriginal.getFileName().lastIndexOf(".", ficheiroOriginal.getFileName().length()));
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 4  : " + extensao);            
//////                ficheiroModelo.setCodigo(extensao);
//////                int index = ficheiroOriginal.getFileName().lastIndexOf(".");
//////                String nome = ficheiroOriginal.getFileName().substring(delimiter + 1, index);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 4.1: " + nome);            
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 5  : " + ficheiroModelo.getCodigo());            
//////                ficheiroModelo.setCaminho(ficheiroTemporario.toString());
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 6  : " + ficheiroModelo.getCaminho());            
//////                ficheiroModelo.setNomeOriginal(ficheiroOriginal.getFileName());
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 7  : " + ficheiroModelo.getNomeOriginal());
//////
//////                int id = FicheiroModelo.retornarProximoId(listaFicheirosCarregados);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 8  : " + id);            
//////                ficheiroModelo.setId(id);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 9  : " + ficheiroModelo.getId());            
//////                listaFicheirosCarregados.add(ficheiroModelo);
//////System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 10 : " + listaFicheirosCarregados.size());            
//////
//////                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficheiro Carregado com sucesso: " + ficheiroModelo.getNomeOriginal(), "Operação Realizada Com Sucesso!"));
//////
//////                return;
//////            }
//////            catch (IOException erro)
//////            {
//////                System.err.println("" + erro.getMessage());
//////            }
//////        }
//////
//////        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome do Ficheiro inválido, tente novamente!", "Erro! Operação Não Realizada!"));
//////    }

    public Bairro getBairro()
    {
        return bairro;
    }

    public void setBairro(Bairro bairro)
    {
        this.bairro = bairro;
    }   
    
}
