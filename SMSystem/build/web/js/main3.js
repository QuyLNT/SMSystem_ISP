/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var emailInput = document.getElementById('email-exist');
document.getElementById('edit-btn-email').addEventListener('click', function() {
    emailInput.removeAttribute('readonly');
    emailInput.classList.toggle('active');
    emailInput.focus();
    this.style.display = 'none';
});

var userNameInput = document.getElementById('userName-exist');
document.getElementById('edit-btn-userName').addEventListener('click', function() {
    userNameInput.removeAttribute('readonly');
    userNameInput.classList.toggle('active');
    userNameInput.focus();
    this.style.display = 'none';
});


 var passInput = document.getElementById('password-exist');
document.getElementById('edit-btn-pass').addEventListener('click', function() {
    passInput.removeAttribute('readonly');
    passInput.classList.toggle('active');
    passInput.focus();
    this.style.display = 'none';
});
    
    var fullNameInput = document.getElementById('fullName-exist');
document.getElementById('edit-btn-fullName').addEventListener('click', function() {
   fullNameInput.removeAttribute('readonly');
    fullNameInput.classList.toggle('active');
    fullNameInput.focus();
    this.style.display = 'none';
});

var phoneInput = document.getElementById('phone-exist');
document.getElementById('edit-btn-phone').addEventListener('click', function() {
   phoneInput.removeAttribute('readonly');
    phoneInput.classList.toggle('active');
    phoneInput.focus();
    this.style.display = 'none';
});

var streetInput = document.getElementById('street-exist');
document.getElementById('edit-btn-street').addEventListener('click', function() {
   streetInput.removeAttribute('readonly');
    streetInput.classList.toggle('active');
    streetInput.focus();
    this.style.display = 'none';
});

var districtInput = document.getElementById('district-exist');
document.getElementById('edit-btn-district').addEventListener('click', function() {
  districtInput.removeAttribute('readonly');
    districtInput.classList.toggle('active');
   districtInput.focus();
   this.style.display = 'none';
});

var cityInput = document.getElementById('city-exist');
document.getElementById('edit-btn-city').addEventListener('click', function() {
  cityInput.removeAttribute('readonly');
    cityInput.classList.toggle('active');
   cityInput.focus();
   this.style.display = 'none';
});

var birthInput = document.getElementById('birth-exist');
document.getElementById('edit-btn-birth').addEventListener('click', function() {
  birthInput.removeAttribute('readonly');
    birthInput.classList.toggle('active');
   birthInput.focus();
   this.style.display = 'none';
});

//var sexInput = document.getElementById('sex-exist');
//document.getElementById('edit-btn-sex').addEventListener('click', function() {
//  sexInput.removeAttribute('readonly');
//    sexInput.classList.toggle('active');
//   sexInput.focus();
//});
 document.getElementById('edit-btn-sex').addEventListener('click', function() {
            var sexInput = document.getElementById('sex-exist');
            var sexSelect = document.createElement('select');
            sexSelect.id = 'sex';
            sexSelect.name = 'sex';
            
            var options = [
                { value: 'male', text: 'Male' },
                { value: 'female', text: 'Female' },
                { value: 'other', text: 'Other' }
            ];

            options.forEach(function(optionData) {
                var option = document.createElement('option');
                option.value = optionData.value;
                option.text = optionData.text;
                if (optionData.value === sexInput.value) {
                    option.selected = true;
                }
                sexSelect.appendChild(option);
            });

            sexInput.replaceWith(sexSelect);
            this.style.display = 'none';
        });

