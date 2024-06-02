import { createElement } from "react";

export function HelloWorldSample({ sampleText }) {
    return (
        <nav className="navbar" style={{ backgroundColor: '#243153', color: 'white' }}>
        <ul className="navbarList">
            <li>Recherche Personne</li>
            <li>Recherche Societe</li>
            <li>Extrait du Registre</li>
            <li>Bulletin Officiel</li>
            <li>Convocation Assemblée Générale</li>
            <li>Payer une Redevance</li>
            <li>Immatricule Entreprise</li>
            <li>Vérification statut</li>
            
        </ul>
        </nav>
    );
}
