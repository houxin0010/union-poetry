$('.left.fixed a').removeClass("active");
$('#answerRecordMenu').addClass("active");
$('#topTip').text("答题记录");

function resst() {
    $('form').form('clear');
}

function queryAnswerRecord(page) {
    let $form = $('.ui.segment form');
    let allFields = $form.form('get values');
    allFields.page = page;
    allFields.size = 10;
    console.log(allFields);
    let url = "/poetry/answerRecord/getAnswerRecord";
    $.ajax({
        url: url,
        type: 'POST',
        data: allFields,
        success: function (data) {
            $('#answerRecordList').html(data);
        }
    });
}

function findDetail(answerRecordId) {
    console.log(answerRecordId);
    $('.ui.modal').modal('show');
    let url = "/poetry/answerRecord/getQuestionDetail";
    $.ajax({
        url: url,
        type: 'GET',
        data: {id:answerRecordId},
        success: function (data) {
            $('#questionDetail').html(data);
        }
    });
}

