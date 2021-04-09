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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ficheiro_anexado")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FicheiroAnexado.findAll", query = "SELECT f FROM FicheiroAnexado f"),
    @NamedQuery(name = "FicheiroAnexado.findByPkFicheiroAnexado", query = "SELECT f FROM FicheiroAnexado f WHERE f.pkFicheiroAnexado = :pkFicheiroAnexado"),
    @NamedQuery(name = "FicheiroAnexado.findByFicheiro", query = "SELECT f FROM FicheiroAnexado f WHERE f.ficheiro = :ficheiro")
})
public class FicheiroAnexado implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_ficheiro_anexado")
    private Integer pkFicheiroAnexado;
    @Size(max = 2147483647)
    @Column(name = "ficheiro")
    private String ficheiro;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_pessoa")
    @ManyToOne
    private Pessoa fkPessoa;

    public FicheiroAnexado()
    {
    }

    public FicheiroAnexado(Integer pkFicheiroAnexado)
    {
        this.pkFicheiroAnexado = pkFicheiroAnexado;
    }

    public Integer getPkFicheiroAnexado()
    {
        return pkFicheiroAnexado;
    }

    public void setPkFicheiroAnexado(Integer pkFicheiroAnexado)
    {
        this.pkFicheiroAnexado = pkFicheiroAnexado;
    }

    public String getFicheiro()
    {
        return ficheiro;
    }

    public void setFicheiro(String ficheiro)
    {
        this.ficheiro = ficheiro;
    }

    public Pessoa getFkPessoa()
    {
        return fkPessoa;
    }

    public void setFkPessoa(Pessoa fkPessoa)
    {
        this.fkPessoa = fkPessoa;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkFicheiroAnexado != null ? pkFicheiroAnexado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FicheiroAnexado))
        {
            return false;
        }
        FicheiroAnexado other = (FicheiroAnexado) object;
        if ((this.pkFicheiroAnexado == null && other.pkFicheiroAnexado != null) || (this.pkFicheiroAnexado != null && !this.pkFicheiroAnexado.equals(other.pkFicheiroAnexado)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.FicheiroAnexado[ pkFicheiroAnexado=" + pkFicheiroAnexado + " ]";
    }
    
}
