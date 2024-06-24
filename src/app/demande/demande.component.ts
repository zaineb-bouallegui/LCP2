import { Component, OnInit } from '@angular/core';
import { Demande } from '../models/Demande';
import { DemandeService } from '../services/demande.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-demande',
  templateUrl: './demande.component.html',
  styleUrls: ['./demande.component.css']
})
export class DemandeComponent implements OnInit {
  demandes: Demande[] = [];

  constructor(private demandeService: DemandeService, private router: Router) {}

  ngOnInit(): void {
    this.getAllDemande();
  }

  getAllDemande() {
    this.demandeService.getAllDemandes().subscribe(
      res => {
        console.log(res);
        this.demandes = res;
      },
      error => {
        console.error("Erreur dans l'affichage de demande", error);
      }
    );
  }

  saveDemande() {
    this.router.navigateByUrl('creationDemande');
  }

  saveDemandeOutsystems() {
    this.router.navigateByUrl('outsystems');
  }

  deleteDemande(id: number) {
    this.demandeService.deleteDemande(id).subscribe(
      () => {
        this.getAllDemande();
      },
      error => {
        console.error("Erreur dans la suppression de la demande", error);
      }
    );
  }

 
}
