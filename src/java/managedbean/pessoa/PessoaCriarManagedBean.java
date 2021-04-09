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
@Named(value = "pessoaCriarManagedBean")
@ViewScoped
public class PessoaCriarManagedBean implements Serializable
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
    private PessoaManagedBean pessoaManagedBean;
    @Inject
    private ProvinciaManagedBean provinciaManagedBean;
    @Inject
    private SexoManagedBean sexoManagedBean;
    

    private FicheiroAnexado ficheiroAnexado;
    private Bairro bairro;
    private Pessoa pessoa;

    ///Variable that used for upload doc's
    private UploadedFile uploadedFile;
    private String pathDocument;
    private List<FicheiroModelo> listaFicheirosCarregados = new ArrayList<>();

    /**
     * Creates a new instance of PessoaCriarManagedBean
     */
    public PessoaCriarManagedBean()
    {
//        bairro = new Bairro();
//        pathDocument = Defs.getPathWildFly();
//        pessoa = new Pessoa();       
    }

    @PostConstruct
    public void init()
    {
        bairro = new Bairro();
        pathDocument = Defs.getPathWildFly();
        pessoa = new Pessoa();  
    }     
    
    public void criarRegisto()
    {
        try
        {
            this.userTransaction.begin();

            pessoa.setNumeroContribuinte(pessoaManagedBean.getPessoa().getNumeroContribuinte());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 0: Número de Contribuinte : " + pessoa.getNumeroContribuinte());            
            pessoa.setNomeCompleto(pessoaManagedBean.getPessoa().getNomeCompleto());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 1: Nome : " + pessoa.getNomeCompleto());
            pessoa.setDataNascimento(pessoaManagedBean.getPessoa().getDataNascimento());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 2: Data de Nascimento : " + pessoa.getDataNascimento());
            pessoa.setFkSexo(new Sexo(sexoManagedBean.getSexo().getPkSexo()));
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 3: Sexo : " + pessoa.getFkSexo());
            pessoa.setNomePai(pessoaManagedBean.getPessoa().getNomePai());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 4: Nome Pai : " + pessoa.getNomePai());
            pessoa.setNomeMae(pessoaManagedBean.getPessoa().getNomeMae());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 5: Nome Mae : " + pessoa.getNomeMae());
            pessoa.setFkEstadoCivil(new EstadoCivil(estadoCivilManagedBean.getEstadoCivil().getPkEstadoCivil()));
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 6: Estado Civil : " + pessoa.getFkEstadoCivil());
            pessoa.setFkPais(new Pais(paisManagedBean.getPais().getPkPais()));
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 7: País : " + pessoa.getFkPais());
            pessoa.setFkProvincia(new Provincia(provinciaManagedBean.getProvincia().getPkProvincia()));
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 8: Província : " + pessoa.getFkProvincia());
            pessoa.setFkMunicipio(new Municipio(municipioManagedBean.getMunicipio().getPkMunicipio()));
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 9: Município : " + pessoa.getFkMunicipio());
            pessoa.setFkBairro(new Bairro(bairroManagedBean.getBairro().getPkBairro()));
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 10: Bairro : " + pessoa.getFkBairro());
            pessoa.setRua(pessoaManagedBean.getPessoa().getRua());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 11: Rua : " + pessoa.getRua());
            pessoa.setNumCasa(pessoaManagedBean.getPessoa().getNumCasa());
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 12: Número da Casa : " + pessoa.getNumCasa());          
            
            pessoa.setEliminado(Boolean.FALSE);
System.out.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 13: Estado : " + pessoa.getEliminado());            

            pessoaFacade.create(pessoa);

            //Put here the code that response to attach files
            if (listaFicheirosCarregados != null && !listaFicheirosCarregados.isEmpty())
            {
                for (int i = 0; i < listaFicheirosCarregados.size(); i++)
                {
                    int index = listaFicheirosCarregados.get(i).getNomeOriginal().lastIndexOf(".");
                    String nome = listaFicheirosCarregados.get(i).getNomeOriginal().substring(0, index);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): Teste 14.0: " + nome);

                    Calendar calendar = GregorianCalendar.getInstance();

                    String nomeFile = nome + "_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_"
                            + calendar.get(Calendar.DATE) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE)
                            + "_" + calendar.get(Calendar.SECOND) + listaFicheirosCarregados.get(i).getCodigo();

System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): 14.1: Origem  : " + listaFicheirosCarregados.get(i).getCaminho());
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): 14.2: Destino : " + (pathDocument + nomeFile));
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): 14.3: Nome : " +  nomeFile);
                    Path origem = Paths.get(listaFicheirosCarregados.get(i).getCaminho());
                    Path destino = Paths.get((pathDocument + nomeFile));

                    Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

                    //attach
                    ficheiroAnexado = new FicheiroAnexado();
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): 14.4: Anexo   : " + ficheiroAnexado);                    
                    ficheiroAnexado.setFicheiro(destino.getFileName().toString());
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): 14.5: Ficheiro: " + ficheiroAnexado.getFicheiro());                    

                    ficheiroAnexado.setFkPessoa(pessoa);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.criarRegisto(): 14.6: Pessoa  : " + ficheiroAnexado.getFkPessoa());                    

                    ficheiroAnexadoFacade.create(ficheiroAnexado);

                    ficheiroAnexado = null;
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa guardada com sucesso!", "Operação Realizada Com Sucesso!"));

            this.userTransaction.commit();

            limpar();
            
            pessoaManagedBean.init();

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
        listaFicheirosCarregados = new ArrayList<>();
        pessoaManagedBean.setPessoa(pessoaFacade.getInstancia());
    }

    /**
     * Carrega um ficheiro de acordo o criterio especificado
     *
     * @author ivandro-colombo
     * @param evento
     */
    public void upload(FileUploadEvent evento)
    {
        UploadedFile ficheiroOriginal = evento.getFile();

        int delimiter = ficheiroOriginal.getFileName().indexOf("_", 0);
        String nomeFicheiro = ficheiroOriginal.getFileName().substring(0, delimiter);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 0.0: " + nomeFicheiro);

        if (nomeFicheiro.equals(Defs.FOTO_ATTACH))
        {
            try
            {
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 0  : ");            
                Path ficheiroTemporario = Files.createTempFile(null, null);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 1  : " + ficheiroTemporario);            
                Files.copy(ficheiroOriginal.getInputStream(), ficheiroTemporario, StandardCopyOption.REPLACE_EXISTING);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 2  : ");            
                FicheiroModelo ficheiroModelo = new FicheiroModelo();
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 3  : " + ficheiroModelo);            
                String extensao = ficheiroOriginal.getFileName().substring(ficheiroOriginal.getFileName().lastIndexOf(".", ficheiroOriginal.getFileName().length()));
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 4  : " + extensao);            
                ficheiroModelo.setCodigo(extensao);
                int index = ficheiroOriginal.getFileName().lastIndexOf(".");
                String nome = ficheiroOriginal.getFileName().substring(delimiter + 1, index);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 4.1: " + nome);            
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 5  : " + ficheiroModelo.getCodigo());            
                ficheiroModelo.setCaminho(ficheiroTemporario.toString());
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 6  : " + ficheiroModelo.getCaminho());            
                ficheiroModelo.setNomeOriginal(ficheiroOriginal.getFileName());
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 7  : " + ficheiroModelo.getNomeOriginal());

                int id = FicheiroModelo.retornarProximoId(listaFicheirosCarregados);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 8  : " + id);            
                ficheiroModelo.setId(id);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 9  : " + ficheiroModelo.getId());            
                listaFicheirosCarregados.add(ficheiroModelo);
System.err.println("managedbean.pessoa.PessoaCriarManagedBean.upload(): Teste 10 : " + listaFicheirosCarregados.size());            

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficheiro Carregado com sucesso: " + ficheiroModelo.getNomeOriginal(), "Operação Realizada Com Sucesso!"));

                return;
            }
            catch (IOException erro)
            {
                System.err.println("" + erro.getMessage());
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome do Ficheiro inválido, tente novamente!", "Erro! Operação Não Realizada!"));
    }

    public List<FicheiroModelo> getListaFicheirosCarregados()
    {
        return listaFicheirosCarregados;
    }

    public Bairro getBairro()
    {
        return bairro;
    }

    public void setBairro(Bairro bairro)
    {
        this.bairro = bairro;
    }   
    
}
