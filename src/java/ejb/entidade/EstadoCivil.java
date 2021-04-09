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
@Table(name = "estado_civil")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e"),
    @NamedQuery(name = "EstadoCivil.findByPkEstadoCivil", query = "SELECT e FROM EstadoCivil e WHERE e.pkEstadoCivil = :pkEstadoCivil"),
    @NamedQuery(name = "EstadoCivil.findByDescricao", query = "SELECT e FROM EstadoCivil e WHERE e.descricao = :descricao"),
    @NamedQuery(name = "EstadoCivil.findByAbreviatura", query = "SELECT e FROM EstadoCivil e WHERE e.abreviatura = :abreviatura"),
    @NamedQuery(name = "EstadoCivil.findByEliminado", query = "SELECT e FROM EstadoCivil e WHERE e.eliminado = :eliminado")
})
public class EstadoCivil implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_estado_civil")
    private Integer pkEstadoCivil;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "fkEstadoCivil")
    private List<Pessoa> pessoaList;

    public EstadoCivil()
    {
    }

    public EstadoCivil(Integer pkEstadoCivil)
    {
        this.pkEstadoCivil = pkEstadoCivil;
    }

    public Integer getPkEstadoCivil()
    {
        return pkEstadoCivil;
    }

    public void setPkEstadoCivil(Integer pkEstadoCivil)
    {
        this.pkEstadoCivil = pkEstadoCivil;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getAbreviatura()
    {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura)
    {
        this.abreviatura = abreviatura;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEstadoCivil != null ? pkEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil))
        {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        if ((this.pkEstadoCivil == null && other.pkEstadoCivil != null) || (this.pkEstadoCivil != null && !this.pkEstadoCivil.equals(other.pkEstadoCivil)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.EstadoCivil[ pkEstadoCivil=" + pkEstadoCivil + " ]";
    }
    
}
