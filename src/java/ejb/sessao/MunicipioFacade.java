/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.Municipio;
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
public class MunicipioFacade extends AbstractFacade<Municipio>
{

    @EJB
    private ProvinciaFacade provinciaFacade;    
    
    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public MunicipioFacade()
    {
        super(Municipio.class);
    }
    
    public List<Municipio> findAllOrderByNome()
    {
        return em.createQuery("SELECT p FROM Municipio p WHERE p.eliminado = FALSE ORDER BY p.nome", Municipio.class).getResultList();
    }
    
    public List<Municipio> findAllOrderByNome(int idProvincia)
    {
        return em.createQuery("SELECT p FROM Municipio p WHERE p.eliminado = FALSE AND p.fkProvincia.pkProvincia = :idProvincia ORDER BY p.nome", Municipio.class)
                .setParameter("idProvincia", idProvincia).getResultList();
    }    
    
    public Municipio getInstancia()
    {
        Municipio municipio = new Municipio();
        
        municipio.setFkProvincia(provinciaFacade.getInstancia());
        
        return municipio;
    }     
    
}
