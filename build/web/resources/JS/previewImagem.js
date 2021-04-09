/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Created on : 1/Jun/2016, 0:45:40
 * @param {String} idImg id do elemento do tipo imagem
 * @param {String} idFile id do input do tipo file
 * @author Ornela Boaventura
 */

function PreviewImagem(idImg, idFile)
{
    
    try {
        var leitorFile = new FileReader();
        leitorFile.readAsDataURL(document.getElementById(idFile).files[0]);

        leitorFile.onload = function(evt) {        

            document.getElementById(idImg).src = evt.target.result;
        };
    }
    catch (e)
    {
        alert(''+e);
    }
}
