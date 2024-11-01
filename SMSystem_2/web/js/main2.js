/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let user = document.querySelector('.user');
document.querySelector('#user-btn').onclick = () => {
    user.classList.toggle('active');
};

window.onscroll = () =>{
    user.classList.remove('active');
};

