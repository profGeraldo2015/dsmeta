import './style.css';
import logo from '../../assets/img/logo.svg';


export default function Header() {
  return (
    <div>
    <header>
        <div className="dsmeta-logo-container">
            <img src={logo} alt="DSMeta" />
            <h1>JG Art & Design</h1>
            <p>
              Desenvolvido por
              <a href="https://www.instagram.com/jgartedesign"> @jgartedesign</a>
            </p>
        </div>
    </header>
    </div>
  )
}
