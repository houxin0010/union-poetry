$('.ui.form').form({
    fields: {
        username: {
            identifier: 'username',
            rules: [
                {
                    type: 'empty',
                    prompt: "{name}不能为空"
                }
            ]
        },
        password: {
            identifier: 'password',
            rules: [
                {
                    type: 'empty',
                    prompt: "{name}不能为空"
                }
            ]
        }
    }
});

$('.message .close').on('click', function () {
    $(this).closest('.message').transition('fade');
});