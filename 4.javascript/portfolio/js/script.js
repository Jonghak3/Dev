
document.addEventListener('DOMContentLoaded', () => {

        // --- 메인 화면 소셜 링크 스크립트 ---
    const profileGithub = document.querySelector('.sosial-btn.github');
    const profileLinkedin = document.querySelector('.sosial-btn.linkedin');
    const profileEmail = document.querySelector('.sosial-btn.email');
    const profilePortfolio = document.querySelector('.sosial-btn.portfolio');

    const githubUrl = 'https://github.com/Jonghak3';
    const linkedinUrl = 'https://www.linkedin.com/in/jonghak-hong-360722376/';
    const emailAddress = 'mailto:ghdwhdgkr92@gmail.com';

    // 깃허브
    profileGithub.addEventListener('click', (event) => {
        event.preventDefault();
        window.open(githubUrl, '_blank');
    });

    // 링크드인
    profileLinkedin.addEventListener('click', (event) => {
        event.preventDefault();
        window.open(linkedinUrl, '_blank');
    });

    // 이메일
    profileEmail.addEventListener('click', (event) => {
        event.preventDefault();
        location.href = emailAddress;
    });
    
    // 포트폴리오 링크 
    profilePortfolio.addEventListener('click', (event) => {
        event.preventDefault();
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });

    const footerSocialButtons = document.querySelectorAll('.footer-social button');

    const links = {
        github: 'https://github.com/Jonghak3',
        linkedin: 'https://www.linkedin.com/in/jonghak-hong-360722376/',
        email: 'mailto:ghdwhdgkr92@gmail.com'
    };

    const githubBtn = footerSocialButtons[0];
    const linkedinBtn = footerSocialButtons[1];
    const emailBtn = footerSocialButtons[2];

    // 깃허브
    githubBtn.addEventListener('click', () => {
        window.open(links.github, '_blank');
    });

    // 링크드인
    linkedinBtn.addEventListener('click', () => {
        window.open(links.linkedin, '_blank');
    });

    // 이메일
    emailBtn.addEventListener('click', () => {
        location.href = links.email;
    });
});