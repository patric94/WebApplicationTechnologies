import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule} from '@angular/material';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material';
import { MatInputModule } from '@angular/material';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatDividerModule } from '@angular/material/divider';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonToggleModule } from '@angular/material/button-toggle';

@NgModule({
  imports: [MatButtonToggleModule, MatGridListModule, MatDividerModule, MatBadgeModule, MatAutocompleteModule, MatExpansionModule, MatListModule, MatTableModule, MatMenuModule, MatCheckboxModule, MatCardModule, MatTabsModule, MatTooltipModule, MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatIconModule, MatChipsModule, MatSelectModule],
  exports: [MatButtonToggleModule, MatGridListModule, MatDividerModule, MatBadgeModule, MatAutocompleteModule, MatExpansionModule, MatListModule, MatTableModule, MatMenuModule, MatCheckboxModule, MatCardModule, MatTabsModule, MatTooltipModule, MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatIconModule, MatChipsModule, MatSelectModule],
})
export class MaterialModule { }
