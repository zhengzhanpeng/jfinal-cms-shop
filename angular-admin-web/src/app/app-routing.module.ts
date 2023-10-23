import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'lanmu', component: LanmuComponent },
    { path: 'lanmuEdit', component: LanmuEditComponent },
    { path: 'content', component: ContentComponent },
    { path: 'contentEdit', component: ContentEditComponent },
    { path: 'user', component: UserComponent },
    { path: 'goodslist', component: GoodslistComponent },
    { path: 'goodsEdit', component: GoodsEditComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'attribute', component: AttributeComponent },
    { path: 'adminUser', component: AdminUserComponent },
    { path: 'address', component: AddressComponent },
    { path: 'promotion', component: PromotionComponent },
    { path: 'editPromotion', component: EditPromotionComponent },
    { path: 'ad', component: AdComponent },
    { path: 'attachment', component: AttachmentComponent },
    { path: 'table', component: TableComponent },
    { path: 'tabs', component: TabsComponent },
    { path: 'form', component: FormComponent },
    { path: 'editor', component: EditorComponent },
    { path: 'markdown', component: MarkdownComponent },
    { path: 'upload', component: UploadComponent },
    { path: 'charts', component: ChartsComponent },
    { path: 'drag', component: DragComponent },
    { path: 'permission', component: PermissionComponent },
    { path: 'login', component: LoginComponent },
    { path: '404', component: NotFoundComponent },
    { path: '403', component: ForbiddenComponent },
    { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
