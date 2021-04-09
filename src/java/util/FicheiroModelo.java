
package util;

import java.util.List;

/**
 *
 * @author ivandro-colombo
 */
public class FicheiroModelo 
{

    private int id,tipoFicheiro;
    private String codigo, caminho, nomeOriginal;
    
    public FicheiroModelo() 
    {
    }
    
    /**
     * Devolve uma valor inteiro de acordo o criterio especificado
     * @author unknown
     * @param lista
     * @return
     */    
    public static int retornarProximoId(List<FicheiroModelo> lista)
    {
        if(lista.isEmpty())
            return 1;
        return lista.get(lista.size()-1).getId() + 1;
    }

    public String getCodigo() 
    {
        return codigo;
    }

    public void setCodigo(String codigo) 
    {
        this.codigo = codigo;
    }

    public String getCaminho() 
    {
        return caminho;
    }

    public void setCaminho(String caminho) 
    {
        this.caminho = caminho;
    }

    public String getNomeOriginal() 
    {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) 
    {
        this.nomeOriginal = nomeOriginal;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public int getTipoFicheiro () 
    {
        return tipoFicheiro;
    }

    public void setTipoFicheiro ( int tipoFicheiro ) 
    {
        this.tipoFicheiro = tipoFicheiro;
    }
}
