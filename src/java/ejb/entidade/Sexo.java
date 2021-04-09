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
@Table(name = "sexo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findByPkSexo", query = "SELECT s FROM Sexo s WHERE s.pkSexo = :pkSexo"),
    @NamedQuery(name = "Sexo.findByDescricao", query = "SELECT s FROM Sexo s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "Sexo.findByAbreviatura", query = "SELECT s FROM Sexo s WHERE s.abreviatura = :abreviatura"),
    @NamedQuery(name = "Sexo.findByEliminado", query = "SELECT s FROM Sexo s WHERE s.eliminado = :eliminado")
})
public class Sexo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_sexo")
    private Integer pkSexo;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "fkSexo")
    private List<Pessoa> pessoaList;

    public Sexo()
    {
    }

    public Sexo(Integer pkSexo)
    {
        this.pkSexo = pkSexo;
    }

    public Integer getPkSexo()
    {
        return pkSexo;
    }

    public void setPkSexo(Integer pkSexo)
    {
        this.pkSexo = pkSexo;
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
        hash += (pkSexo != null ? pkSexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo))
        {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.pkSexo == null && other.pkSexo != null) || (this.pkSexo != null && !this.pkSexo.equals(other.pkSexo)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Sexo[ pkSexo=" + pkSexo + " ]";
    }
    
}
