
function showSection(event, section) {
    if (event) {
        event.preventDefault();
    }

    let sidebarLinks = document.querySelectorAll('.sidebar-link');
    sidebarLinks.forEach(function(link) {
        link.classList.remove('current');
    });

    if (event && event.target) {
        event.target.classList.add('current');
    } else {
        const defaultLink = document.querySelector(`a[href="#${section}"]`);
        if (defaultLink) {
            defaultLink.classList.add('current');
        }
    }

    loadSection(section);
}

function loadSection(section) {
    let sectionUrl = '';
    console.log(section)
    switch (section) {
        case 'page-dash':sectionUrl = 'dashboard';
            break;
        case 'page-eleve':sectionUrl = 'students';
            break;
        case 'page-professeur':sectionUrl = 'teachers';
            break;
        case 'form-professeur':sectionUrl = 'teachers/formTeacher';
             break;
        case 'form-eleve':sectionUrl = 'students/formStudent';
               break;
        case 'form-user':sectionUrl = 'users/formUser';
               break;
        case 'page-utilisateur':sectionUrl = 'users';
            break;
        case 'page-absences':sectionUrl = 'absences';
             break;
        case 'page-carte-eleve':sectionUrl = 'studentcard';
             break;
        case 'page-rapport':sectionUrl = 'reports';
            break;
        default:
            sectionUrl = '';
    }

    if (sectionUrl !== '') {
        fetch(sectionUrl)
            .then(response => response.text())
            .then(html => {
                document.getElementById('main-content').innerHTML = html;
            })
            .catch(error => console.log('Error loading section:', error));
    }
}

document.addEventListener('DOMContentLoaded', function() {
    showSection(null, 'page-dash');
});






function opendelete(studentId) {
    var menuEleve = document.querySelector('.delete-modal');
    menuEleve.style.display = 'flex';
    document.querySelector('.modal-btn-delete.oui').setAttribute('onclick', `deleteStudent(${studentId})`);
}
function openDeleteTeacher(teacherId) {
    var menuEleve = document.querySelector('.delete-modal');
    menuEleve.style.display = 'flex';
    document.querySelector('.modal-btn-delete.oui').setAttribute('onclick', `deleteTeacher(${teacherId})`);
}
function openDeleteUser(userId) {
    var menuEleve = document.querySelector('.delete-modal');
    menuEleve.style.display = 'flex';
    document.querySelector('.modal-btn-delete.oui').setAttribute('onclick', `deleteUser(${userId})`);
}

function closedelete() {var menuEleve = document.querySelector('.delete-modal');menuEleve.style.display = 'none';}

function deleteStudent(studentId) {
    fetch('/students/delete/' + studentId)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            showSection(event, 'page-eleve');
        })
        .catch(error => console.error('Error:', error));
}

function deleteTeacher(teacherId) {
    fetch('/teachers/delete/' + teacherId)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            showSection(event, 'page-professeur');
        })
        .catch(error => console.error('Error:', error));
}

function deleteUser(userId) {
    fetch('/users/delete/' + userId)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            showSection(event, 'page-utilisateur');
        })
        .catch(error => console.error('Error:', error));
}


function openModify(id) {
    fetch('/students/modify/' + id)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            showSection(event, 'form-eleve-modify');
        })
        .catch(error => console.error('Error:', error));
}

function openModifyTeacher(id) {
    fetch('/teachers/modify/' + id)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            showSection(event, 'form-teacher-modify');
        })
        .catch(error => console.error('Error:', error));
}

function openModifyUser(id) {
    fetch('/users/modify/' + id)
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            showSection(event, 'form-user-modify');
        })
        .catch(error => console.error('Error:', error));
}
