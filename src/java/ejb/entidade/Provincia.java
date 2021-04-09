/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entidade;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivandro-colombo
 */
@Entity
@Table(name = "provincia")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findByPkProvincia", query = "SELECT p FROM Provincia p WHERE p.pkProvincia = :pkProvincia"),
    @NamedQuery(name = "Provincia.findByNome", query = "SELECT p FROM Provincia p WHERE p.nome = :nome"),
    @NamedQuery(name = "Provincia.findByEliminado", query = "SELECT p FROM Provincia p WHERE p.eliminado = :eliminado")
})
public class Provincia implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_provincia")
    private Integer pkProvincia;
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "fkProvincia")
    private List<Pessoa> pessoaList;
    @OneToMany(mappedBy = "fkProvincia")
    private List<Municipio> municipioList;
    @JoinColumn(name = "fk_pais", referencedColumnName = "pk_pais")
    @ManyToOne
    private Pais fkPais;

    public Provincia()
    {
    }

    public Provincia(Integer pkProvincia)
    {
        this.pkProvincia = pkProvincia;
    }

    public Integer getPkProvincia()
    {
        return pkProvincia;
    }

    public void setPkProvincia(Integer pkProvincia)
    {
        this.pkProvincia = pkProvincia;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Boolean getEliminado()
    {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado)
    {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<Pessoa> getPessoaList()
    {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList)
    {
        this.pessoaList = pessoaList;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList()
    {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList)
    {
        this.municipioList = municipioList;
    }

    public Pais getFkPais()
    {
        return fkPais;
    }

    public void setFkPais(Pais fkPais)
    {
        this.fkPais = fkPais;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkProvincia != null ? pkProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia))
        {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.pkProvincia == null && other.pkProvincia != null) || (this.pkProvincia != null && !this.pkProvincia.equals(other.pkProvincia)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Provincia[ pkProvincia=" + pkProvincia + " ]";
    }
    
}
