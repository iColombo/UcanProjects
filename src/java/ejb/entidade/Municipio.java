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
@Table(name = "municipio")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByPkMunicipio", query = "SELECT m FROM Municipio m WHERE m.pkMunicipio = :pkMunicipio"),
    @NamedQuery(name = "Municipio.findByNome", query = "SELECT m FROM Municipio m WHERE m.nome = :nome"),
    @NamedQuery(name = "Municipio.findByEliminado", query = "SELECT m FROM Municipio m WHERE m.eliminado = :eliminado")
})
public class Municipio implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_municipio")
    private Integer pkMunicipio;
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "fkMunicipio")
    private List<Pessoa> pessoaList;
    @OneToMany(mappedBy = "fkMunicipio")
    private List<Bairro> bairroList;
    @JoinColumn(name = "fk_provincia", referencedColumnName = "pk_provincia")
    @ManyToOne
    private Provincia fkProvincia;

    public Municipio()
    {
    }

    public Municipio(Integer pkMunicipio)
    {
        this.pkMunicipio = pkMunicipio;
    }

    public Integer getPkMunicipio()
    {
        return pkMunicipio;
    }

    public void setPkMunicipio(Integer pkMunicipio)
    {
        this.pkMunicipio = pkMunicipio;
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
    public List<Bairro> getBairroList()
    {
        return bairroList;
    }

    public void setBairroList(List<Bairro> bairroList)
    {
        this.bairroList = bairroList;
    }

    public Provincia getFkProvincia()
    {
        return fkProvincia;
    }

    public void setFkProvincia(Provincia fkProvincia)
    {
        this.fkProvincia = fkProvincia;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkMunicipio != null ? pkMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio))
        {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.pkMunicipio == null && other.pkMunicipio != null) || (this.pkMunicipio != null && !this.pkMunicipio.equals(other.pkMunicipio)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ejb.entidade.Municipio[ pkMunicipio=" + pkMunicipio + " ]";
    }
    
}
