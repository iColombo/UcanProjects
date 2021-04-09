/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivandro-colombo
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByPkPessoa", query = "SELECT p FROM Pessoa p WHERE p.pkPessoa = :pkPessoa"),
    @NamedQuery(name = "Pessoa.findByNomeCompleto", query = "SELECT p FROM Pessoa p WHERE p.nomeCompleto = :nomeCompleto"),
    @NamedQuery(name = "Pessoa.findByNomePai", query = "SELECT p FROM Pessoa p WHERE p.nomePai = :nomePai"),
    @NamedQuery(name = "Pessoa.findByNomeMae", query = "SELECT p FROM Pessoa p WHERE p.nomeMae = :nomeMae"),
    @NamedQuery(name = "Pessoa.findByDataNascimento", query = "SELECT p FROM Pessoa p WHERE p.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Pessoa.findByNumeroContribuinte", query = "SELECT p FROM Pessoa p WHERE p.numeroContribuinte = :numeroContribuinte"),
    @NamedQuery(name = "Pessoa.findByRua", query = "SELECT p FROM Pessoa p WHERE p.rua = :rua"),
    @NamedQuery(name = "Pessoa.findByNumCasa", query = "SELECT p FROM Pessoa p WHERE p.numCasa = :numCasa"),
    @NamedQuery(name = "Pessoa.findByEliminado", query = "SELECT p FROM Pessoa p WHERE p.eliminado = :eliminado")
})
public class Pessoa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pessoa")
    private Integer pkPessoa;
    @Size(max = 2147483647)
    @Column(name = "nome_completo")
    private String nomeCompleto;
    @Size(max = 2147483647)
    @Column(name = "nome_pai")
    private String nomePai;
    @Size(max = 2147483647)
    @Column(name = "nome_mae")
    private String nomeMae;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Size(max = 2147483647)
    @Column(name = "numero_contribuinte")
    private String numeroContribuinte;
    @Size(max = 2147483647)
    @Column(name = "rua")
    private String rua;
    @Size(max = 2147483647)
    @Column(name = "num_casa")
    private String numCasa;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @JoinColumn(name = "fk_bairro", referencedColumnName = "pk_bairro")
    @ManyToOne
    private Bairro fkBairro;
    @JoinColumn(name = "fk_estado_civil", referencedColumnName = "pk_estado_civil")
    @ManyToOne
    private EstadoCivil fkEstadoCivil;
    @JoinColumn(name = "fk_municipio", referencedColumnName = "pk_municipio")
    @ManyToOne
    private Municipio fkMunicipio;
    @JoinColumn(name = "fk_pais", referencedColumnName = "pk_pais")
    @ManyToOne
    private Pais fkPais;
    @JoinColumn(name = "fk_provincia", referencedColumnName = "pk_provincia")
    @ManyToOne
    private Provincia fkProvincia;
    @JoinColumn(name = "fk_sexo", referencedColumnName = "pk_sexo")
    @ManyToOne
    private Sexo fkSexo;
    @OneToMany(mappedBy = "fkPessoa")
    private List<FicheiroAnexado> ficheiroAnexadoList;

    public Pessoa()
    {
    }

    public Pessoa(Integer pkPessoa)
    {
        this.pkPessoa = pkPessoa;
    }

    public Integer getPkPessoa()
    {
        return pkPessoa;
    }

    public void setPkPessoa(Integer pkPessoa)
    {
        this.pkPessoa = pkPessoa;
    }

    public String getNomeCompleto()
    {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto)
    {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomePai()
    {
        return nomePai;
    }

    public void setNomePai(String nomePai)
    {
        this.nomePai = nomePai;
    }

    public String getNomeMae()
    {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae)
    {
        this.nomeMae = nomeMae;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroContribuinte()
    {
        return numeroContribuinte;
    }

    public void setNumeroContribuinte(String numeroContribuinte)
    {
        this.numeroContribuinte = numeroContribuinte;
    }

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public String getNumCasa()
    {
        return numCasa;
    }

    public void setNumCasa(String numCasa)
    {
        this.numCasa = numCasa;
    }

    public Boolean getEliminado()
    {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado)
    {
        this.eliminado = eliminado;
    }

    public Bairro getFkBairro()
    {
        return fkBairro;
    }

    public void setFkBairro(Bairro fkBairro)
    {
        this.fkBairro = fkBairro;
    }

    public EstadoCivil getFkEstadoCivil()
    {
        return fkEstadoCivil;
    }

    public void setFkEstadoCivil(EstadoCivil fkEstadoCivil)
    {
        this.fkEstadoCivil = fkEstadoCivil;
    }

    public Municipio getFkMunicipio()
    {
        return fkMunicipio;
    }

    public void setFkMunicipio(Municipio fkMunicipio)
    {
        this.fkMunicipio = fkMunicipio;
    }

    public Pais getFkPais()
    {
        return fkPais;
    }

    public void setFkPais(Pais fkPais)
    {
        this.fkPais = fkPais;
    }

    public Provincia getFkProvincia()
    {
        return fkProvincia;
    }

    public void setFkProvincia(Provincia fkProvincia)
    {
        this.fkProvincia = fkProvincia;
    }

    public Sexo getFkSexo()
    {
        return fkSexo;
    }

    public void setFkSexo(Sexo fkSexo)
    {
        this.fkSexo = fkSexo;
    }

    @XmlTransient
    public List<FicheiroAnexado> getFicheiroAnexadoList()
    {
        return ficheiroAnexadoList;
    }

    public void setFicheiroAnexadoList(List<FicheiroAnexado> ficheiroAnexadoList)
    {
        this.ficheiroAnexadoList = ficheiroAnexadoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPessoa != null ? pkPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa))
        {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.pkPessoa == null && other.pkPessoa != null) || (this.pkPessoa != null && !this.pkPessoa.equals(other.pkPessoa)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Pessoa[ pkPessoa=" + pkPessoa + " ]";
    }
    
}
