/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.Sexo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ivandro-colombo
 */
@Stateless
public class SexoFacade extends AbstractFacade<Sexo>
{

    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public SexoFacade()
    {
        super(Sexo.class);
    }
    
    public List<Sexo> findAllOrderByDescricao()
    {
        return em.createQuery("SELECT p FROM Sexo p WHERE p.eliminado = FALSE ORDER BY p.descricao", Sexo.class).getResultList();
    }    
    
}
