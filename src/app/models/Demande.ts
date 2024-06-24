import { Societe } from "./Societe";

export class Demande {
    id!: number;
    deadline!: string;
    dateEnvoi!: string;
    typeRegistre!: string;
    statutDemande!: string;
    societe!:Societe;
}