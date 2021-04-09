/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.sessao;

import ejb.entidade.Bairro;
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
public class BairroFacade extends AbstractFacade<Bairro>
{

    @EJB
    private MunicipioFacade municipioFacade;
    
    @PersistenceContext(unitName = "AppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public BairroFacade()
    {
        super(Bairro.class);
    }
    
    public List<Bairro> findAllOrderByNome()
    {
        return em.createQuery("SELECT p FROM Bairro p WHERE p.eliminado = FALSE ORDER BY p.nome", Bairro.class).getResultList();
    }
    
    public List<Bairro> findAllOrderByNome(int idMunicipio)
    {
        return em.createQuery("SELECT p FROM Bairro p WHERE p.eliminado = FALSE AND p.fkMunicipio.pkMunicipio = :idMunicipio ORDER BY p.nome", Bairro.class)
                .setParameter("idMunicipio", idMunicipio).getResultList();
    }  
    
    public Bairro getInstancia()
    {
        Bairro bairro = new Bairro();
        
        bairro.setFkMunicipio(municipioFacade.getInstancia());
        
        return bairro;
    }    
    
}
