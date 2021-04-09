/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.EstadoCivil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ivandro-colombo
 */
@Stateless
public class EstadoCivilFacade extends AbstractFacade<EstadoCivil>
{

    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EstadoCivilFacade()
    {
        super(EstadoCivil.class);
    }
    
    public List<EstadoCivil> findAllOrderByDescricao()
    {
        return em.createQuery("SELECT p FROM EstadoCivil p WHERE p.eliminado = FALSE ORDER BY p.descricao", EstadoCivil.class).getResultList();
    }    
    
}
