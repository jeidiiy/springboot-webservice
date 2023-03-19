const init = () => {
    $('#btn-save').on('click', () => {
        save()
    });

    $('#btn-update').on('click', () => {
        update();
    })

    $('#btn-delete').on('click', () => {
        remove();
    })
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
}

const update = () => {
    const data = {
        title: $('#title').val(),
        content: $('#content').val()
    };

    const id = $('#id').val();

    $.ajax({
        type: 'PUT',
        url: `/api/v1/posts/${id}`,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(() => {
        alert('글이 수정되었습니다.');
        window.location.href = '/';
    }).fail((error) => {
        alert(JSON.stringify(error));
    });
};

const remove = () => {
    const id = $('#id').val();

    $.ajax({
        type: 'DELETE',
        url: `/api/v1/posts/${id}`,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8'
    }).done(() => {
        alert('글이 삭제되었습니다.');
        window.location.href = '/'
    }).fail((error) => {
        alert(JSON.stringify(error));
    });
}

init()
