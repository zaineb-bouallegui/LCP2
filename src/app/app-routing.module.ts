import { NgModule } from '@angular/core';
import { MendixComponent } from './mendix/mendix.component';
import { DemandeComponent } from './demande/demande.component';
import { RouterModule, Routes } from '@angular/router';
import { OutsystemsComponent } from './outsystems/outsystems.component';


const routes: Routes = [
  {path:'',redirectTo:'demandes',pathMatch: 'full'},
  { path: 'demandes', component: DemandeComponent },
  { path:'creationDemande',component:MendixComponent},
  {path:'outsystems',component:OutsystemsComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
