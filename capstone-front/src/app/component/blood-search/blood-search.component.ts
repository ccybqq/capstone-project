import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodRegistryEntity } from 'src/app/object/blood-registry-entity';
import { AuthService } from 'src/app/service/auth.service';
import { BloodRegistryService } from 'src/app/service/blood-registry.service';
import { FormatterService } from 'src/app/service/formatter.service';

@Component({
  selector: 'app-blood-search',
  templateUrl: './blood-search.component.html',
  styleUrls: ['./blood-search.component.css']
})
export class BloodSearchComponent implements OnInit {
  protected bloodRegistryEntity: BloodRegistryEntity = {
    id: null,
    bloodGroup: '',
    state: '',
    area: '',
    pinCode: '',
    required: false,
    available: true
  };

  protected bloodGroup: string | null = null;

  protected result: BloodRegistryEntity = {
    id: null,
    bloodGroup: '',
    state: '',
    area: '',
    pinCode: '',
    required: false,
    available: false
  };

  protected hasFailedSearch: boolean = false;
  protected isAdmin: boolean = false;

  constructor(
    private bloodRegistryService: BloodRegistryService,
    protected formatter: FormatterService,
    protected authService: AuthService,
    protected router: Router) { }

  ngOnInit(): void {
    if (!this.authService.isLoggedIn) this.router.navigate(['/login']);

    this.isAdmin = this.authService.isAdmin();
  }

  search() {
    this.bloodRegistryEntity.bloodGroup = this.formatter.frontToBack(this.bloodRegistryEntity.bloodGroup, this.formatter.bloodGroup);
    this.bloodRegistryEntity.state = this.formatter.frontToBack(this.bloodRegistryEntity.state, this.formatter.state);
    this.bloodRegistryEntity.area = this.formatter.frontToBack(this.bloodRegistryEntity.area, this.formatter.area);
    this.bloodRegistryService.getBloodRegistryEntity(this.bloodRegistryEntity).subscribe(
      {
        next: (response: HttpResponse<BloodRegistryEntity>) => {
          this.result = response.body ?? this.result;
          this.result.bloodGroup = this.formatter.backToFront(this.result.bloodGroup, this.formatter.bloodGroup);
          this.result.state = this.formatter.backToFront(this.result.state, this.formatter.state);
          this.result.area = this.formatter.backToFront(this.result.area, this.formatter.area);
        },
        error: (error: HttpErrorResponse) => {
          this.hasFailedSearch = true;
          this.result = {
            id: null,
            bloodGroup: '',
            state: '',
            area: '',
            pinCode: '',
            required: false,
            available: false
          };
          console.log(error.headers.get('Message'));
        }
      }
    );
  }
}
