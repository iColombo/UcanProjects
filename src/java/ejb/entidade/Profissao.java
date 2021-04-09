/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivandro-colombo
 */
@Entity
@Table(name = "profissao")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Profissao.findAll", query = "SELECT p FROM Profissao p"),
    @NamedQuery(name = "Profissao.findByPkProfissao", query = "SELECT p FROM Profissao p WHERE p.pkProfissao = :pkProfissao"),
    @NamedQuery(name = "Profissao.findByDescricao", query = "SELECT p FROM Profissao p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Profissao.findByEliminado", query = "SELECT p FROM Profissao p WHERE p.eliminado = :eliminado")
})
public class Profissao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_profissao")
    private Integer pkProfissao;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "eliminado")
    private Boolean eliminado;

    public Profissao()
    {
    }

    public Profissao(Integer pkProfissao)
    {
        this.pkProfissao = pkProfissao;
    }

    public Integer getPkProfissao()
    {
        return pkProfissao;
    }

    public void setPkProfissao(Integer pkProfissao)
    {
        this.pkProfissao = pkProfissao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Boolean getEliminado()
    {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado)
    {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkProfissao != null ? pkProfissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissao))
        {
            return false;
        }
        Profissao other = (Profissao) object;
        if ((this.pkProfissao == null && other.pkProfissao != null) || (this.pkProfissao != null && !this.pkProfissao.equals(other.pkProfissao)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Profissao[ pkProfissao=" + pkProfissao + " ]";
    }
    
}
