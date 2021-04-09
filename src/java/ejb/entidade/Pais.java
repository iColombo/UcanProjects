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
@Table(name = "pais")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findByPkPais", query = "SELECT p FROM Pais p WHERE p.pkPais = :pkPais"),
    @NamedQuery(name = "Pais.findByNome", query = "SELECT p FROM Pais p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pais.findByNacionalidade", query = "SELECT p FROM Pais p WHERE p.nacionalidade = :nacionalidade"),
    @NamedQuery(name = "Pais.findByEliminado", query = "SELECT p FROM Pais p WHERE p.eliminado = :eliminado")
})
public class Pais implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pais")
    private Integer pkPais;
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "nacionalidade")
    private String nacionalidade;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "fkPais")
    private List<Pessoa> pessoaList;
    @OneToMany(mappedBy = "fkPais")
    private List<Provincia> provinciaList;

    public Pais()
    {
    }

    public Pais(Integer pkPais)
    {
        this.pkPais = pkPais;
    }

    public Integer getPkPais()
    {
        return pkPais;
    }

    public void setPkPais(Integer pkPais)
    {
        this.pkPais = pkPais;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNacionalidade()
    {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade)
    {
        this.nacionalidade = nacionalidade;
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
    public List<Provincia> getProvinciaList()
    {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList)
    {
        this.provinciaList = provinciaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPais != null ? pkPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais))
        {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.pkPais == null && other.pkPais != null) || (this.pkPais != null && !this.pkPais.equals(other.pkPais)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Pais[ pkPais=" + pkPais + " ]";
    }
    
}
