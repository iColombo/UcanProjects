/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.Pais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ivandro-colombo
 */
@Stateless
public class PaisFacade extends AbstractFacade<Pais>
{

    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PaisFacade()
    {
        super(Pais.class);
    }
    
    public List<Pais> findAllOrderByNome()
    {
        return em.createQuery("SELECT p FROM Pais p WHERE p.eliminado = FALSE ORDER BY p.nome", Pais.class).getResultList();
    }
    
    public List<Pais> findAllOrderByNome(int idPais)
    {
        return em.createQuery("SELECT p FROM Pais p WHERE p.eliminado = FALSE AND p.pkPais = :idPais ORDER BY p.nome", Pais.class).getResultList();
    }    
    
}
