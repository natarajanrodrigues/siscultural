/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function show_error(textError) {
    
    $('p#error-body').html(textError);
    
    $('#errorModal').modal({
        
        show: 'true'
    });
    
}

function executeAjax(button, url, data) {

    $(button).click(function (e) {

        e.preventDefault();

        $.post(url, $(data).serialize(), function (response) {

            if (response.error !== undefined) {

                show_error(response.error);

            } else if (response.redirect !== undefined) {

                window.location.replace(response.redirect);

            }

        });

    });

}