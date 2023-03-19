const init = () => {
    $('#btn-save').on('click', () => {
        save()
    });
};

const save = () => {
    const data = {
        title: $('#title').val(),
        author: $('#author').val(),
        content: $('#content').val()
    };

    console.log(data)

    $.ajax({
        type: 'POST',
        url: '/api/v1/posts',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(() => {
        alert('글이 등록되었습니다.');
        window.location.href = '/'
    }).fail((error) => {
        alert(JSON.stringify(error));
    });
};

init()
