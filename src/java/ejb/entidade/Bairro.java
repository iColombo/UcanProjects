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
@Table(name = "bairro")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Bairro.findAll", query = "SELECT b FROM Bairro b"),
    @NamedQuery(name = "Bairro.findByPkBairro", query = "SELECT b FROM Bairro b WHERE b.pkBairro = :pkBairro"),
    @NamedQuery(name = "Bairro.findByNome", query = "SELECT b FROM Bairro b WHERE b.nome = :nome"),
    @NamedQuery(name = "Bairro.findByEliminado", query = "SELECT b FROM Bairro b WHERE b.eliminado = :eliminado")
})
public class Bairro implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bairro")
    private Integer pkBairro;
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "fkBairro")
    private List<Pessoa> pessoaList;
    @JoinColumn(name = "fk_municipio", referencedColumnName = "pk_municipio")
    @ManyToOne
    private Municipio fkMunicipio;

    public Bairro()
    {
    }

    public Bairro(Integer pkBairro)
    {
        this.pkBairro = pkBairro;
    }

    public Integer getPkBairro()
    {
        return pkBairro;
    }

    public void setPkBairro(Integer pkBairro)
    {
        this.pkBairro = pkBairro;
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

    public Municipio getFkMunicipio()
    {
        return fkMunicipio;
    }

    public void setFkMunicipio(Municipio fkMunicipio)
    {
        this.fkMunicipio = fkMunicipio;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBairro != null ? pkBairro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bairro))
        {
            return false;
        }
        Bairro other = (Bairro) object;
        if ((this.pkBairro == null && other.pkBairro != null) || (this.pkBairro != null && !this.pkBairro.equals(other.pkBairro)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Bairro[ pkBairro=" + pkBairro + " ]";
    }
    
}
