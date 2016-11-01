/**
 * Created by natarajan on 28/08/16.
 */
$('.table-selectable2 > tbody > tr').click(function () {
    if ($(this).hasClass('active')) {
        $(this).removeClass('active');
    } else {
        $(".table-selectable2 > tbody > tr.active").removeClass('active');
        $(this).addClass('active');
    }

    if (($(".table-selectable2 > tbody > tr.active").length)) {
        $("#btn-editar2").removeClass("disabled");
        $("#btn-editar2").prop('disabled', false);
        $("#btn-deletar2").removeClass("disabled");
        $("#btn-deletar2").prop('disabled', false);
    } else {
        $("#btn-editar2").addClass("disabled");
        $("#btn-editar2").prop('disabled', true);
        $("#btn-deletar2").addClass("disabled");
        $("#btn-deletar2").prop('disabled', true);
    }

});


$('.table-selectable > tbody > tr').click(function () {
    if ($(this).hasClass('active')) {
        $(this).removeClass('active');
    } else {
        $(".table-selectable > tbody > tr.active").removeClass('active');
        $(this).addClass('active');
    }

    if (($(".table-selectable > tbody > tr.active").length)) {
        $("#btn-editar").removeClass("disabled");
        $("#btn-editar").prop('disabled', false);
        $("#btn-deletar").removeClass("disabled");
        $("#btn-deletar").prop('disabled', false);
    } else {
        $("#btn-editar").addClass("disabled");
        $("#btn-editar").prop('disabled', true);
        $("#btn-deletar").addClass("disabled");
        $("#btn-deletar").prop('disabled', true);
    }

});

$('.table-selectable > tbody > tr').dblclick(function (event) {

    var target = $(event.target);

    if (($(".table-selectable > tbody > tr.active").length)) {
        // $(".table-selectable > tbody > tr.active").attr( "disabled", "disabled");
        target.click();
    }

    target.click();
    $("#btn-editar").click();


    // if ($(".table-selectable > tbody > tr.active") != target) {
    //     $(".table-selectable > tbody > tr.active").click();
    //     target.click();
    // }


    // if(!($(".table-selectable > tbody > tr.active").length)){
    //
    //
    //
    //     $('.table-selectable > tbody > tr.active').click();
    //     // $('.table-selectable > tbody > tr').click();
    //     clicked.click();
    // } else {
    //     $("#btn-editar").click();
    //     clicked.click();
    // }


});


$(document).ready(function () {

    (function ($) {

        $('#filter').keyup(function () {

            var rex = new RegExp($(this).val(), 'i');
            $('.searchable tr').hide();
            $('.searchable tr').filter(function () {
                // return rex.test($(this).find(':nth-child(2)').text()); //antigo só buscava em umas das colunas
                return rex.test($(this).find('th').text());
            }).show();

        });

    }(jQuery));
});
