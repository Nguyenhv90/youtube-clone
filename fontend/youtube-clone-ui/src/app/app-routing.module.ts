import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { UploadVideoComponent } from './upload-video/upload-video.component';

const routes: Routes = [
   {
      path: 'upload-video', component: UploadVideoComponent
   },
   {path: 'app-header', component: HeaderComponent}
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
