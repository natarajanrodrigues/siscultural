/**
 * Created by natarajan on 28/08/16.
 */
$('.table-selectable > tbody > tr').click(function() {
    if($(this).hasClass('active')){
        $(this).removeClass('active');
    }else{
        $(".table-selectable > tbody > tr.active").removeClass('active');
        $(this).addClass('active');
    }

    if(($(".table-selectable > tbody > tr.active").length)){
        $("#btn-editar").removeClass("disabled");
        $("#btn-deletar").removeClass("disabled");
    }else{
        $("#btn-editar").addClass("disabled");
        $("#btn-deletar").addClass("disabled");
    }

});


$(document).ready(function () {

    (function ($) {

        $('#filter').keyup(function () {

            var rex = new RegExp($(this).val(), 'i');
            $('.searchable tr').hide();
            $('.searchable tr').filter(function () {
                return rex.test($(this).find(':nth-child(1)').text());
            }).show();

        });

    }(jQuery));
});
