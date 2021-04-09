/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.context.FacesContext;

/**
 *
 * @author ivandro-colombo
 */
public class Defs
{

    //Constants for attach
    public static final String CERTIFICADO_ATTACH = "certificado";
    public static final String CV_ATTACH = "cv";
    public static final String COMPROVATIVO_FINANCAS_ATTACH = "comprovativo_financas";
    public static final String FOTO_ATTACH = "foto";    
    
    //////CentOS
    public static final String FOTO_DEFAULT = "/home/avatar.png";
    public static final String DESTINO_FOTO = "/home/SIG_ISDB_MASTER/Upload/";
    public static final String HOME_SYSTEM = System.getProperty("user.home");
    public static final String WILDFLY_ATTACH_FOLDER = "opt/wildfly-20.0.1.Final/welcome-content/anexos/";
    public static final String ROOT_SYSTEM = HOME_SYSTEM.substring(0, 1);
   
     
//    //Windows
//    public static final String FOTO_DEFAULT = "\\home\\avatar.png";
//    public static final String DESTINO_FOTO = "\\home\\App\\Upload\\";
//    public static String HOME_SYSTEM = System.getProperty("user.home");
//    public static String payaraFolder = "personal_domain";
//    public static String PAYARA_ATTACH_FOLDER = "wildfly-20.0.1.Final\\welcome-content\\anexos\\";
//    public static String ROOT_SYSTEM = HOME_SYSTEM.substring(0, 3);
     
    /**
     * Devolve uma String com o caminho (path) de anexos do WildFly
     * @author ivandro-colombo
     * @return
     */    
    public static String getPathWildFly()
    {
        return ROOT_SYSTEM + WILDFLY_ATTACH_FOLDER;
    }    

}
