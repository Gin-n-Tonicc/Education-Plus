import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PostCardComponent } from './components/post-card/post-card.component';

@NgModule({
    declarations: [PostCardComponent],
    imports: [CommonModule, RouterModule],
    exports: [PostCardComponent],
})
export class SharedModule {}
