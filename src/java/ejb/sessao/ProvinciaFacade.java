/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.Pais;
import ejb.entidade.Provincia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ivandro-colombo
 */
@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia>
{

    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public ProvinciaFacade()
    {
        super(Provincia.class);
    }
    
    public List<Provincia> findAllOrderByNome()
    {
        return em.createQuery("SELECT p FROM Provincia p  WHERE p.eliminado = FALSE ORDER BY p.nome", Provincia.class).getResultList();
    }
    
    public List<Provincia> findAllOrderByNome(int idPais)
    {
        return em.createQuery("SELECT p FROM Provincia p WHERE p.eliminado = FALSE AND p.fkPais.pkPais = :idPais ORDER BY p.nome", Provincia.class).setParameter("idPais", idPais).getResultList();
    }    
    
    public Provincia getInstancia()
    {
        Provincia provincia = new Provincia();
        
        provincia.setFkPais(new Pais());
        
        return provincia;
    }
}
