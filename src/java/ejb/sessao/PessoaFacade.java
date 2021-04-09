/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.Bairro;
import ejb.entidade.EstadoCivil;
import ejb.entidade.Pais;
import ejb.entidade.Pessoa;
import ejb.entidade.Sexo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ivandro-colombo
 */
@Stateless
public class PessoaFacade extends AbstractFacade<Pessoa>
{
    
    @EJB
    private MunicipioFacade municipioFacade;    
    @EJB
    private ProvinciaFacade provinciaFacade;
    

    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PessoaFacade()
    {
        super(Pessoa.class);
    }
    
    public List<Pessoa> findAllOrderByNome()
    {
        return em.createQuery("SELECT p FROM Pessoa p WHERE p.eliminado = FALSE ORDER BY p.nomeCompleto", Pessoa.class).getResultList();
    }
    
    public Pessoa getInstancia()
    {
        Pessoa pessoa = new Pessoa();
        
        pessoa.setFkBairro(new Bairro());
        pessoa.setFkEstadoCivil(new EstadoCivil());
        pessoa.setFkMunicipio(municipioFacade.getInstancia());
        pessoa.setFkPais(new Pais());
        pessoa.setFkProvincia(provinciaFacade.getInstancia());
        pessoa.setFkSexo(new Sexo());
        
        return pessoa;
    }     
    
}
