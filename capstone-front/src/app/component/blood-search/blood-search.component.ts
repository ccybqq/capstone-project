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
  protected hasSubmittedRequest = false;

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
    this.hasSubmittedRequest = false;
    this.bloodRegistryService.getBloodRegistryEntity(this.reqObj()).subscribe(
      {
        next: (response: HttpResponse<BloodRegistryEntity>) => {
          this.setResultFromResponse(response);
        },
        error: (error: HttpErrorResponse) => {
          this.hasFailedSearch = true;
          this.initResult();
          console.log(error.headers.get('Message'));
        }
      }
    );
  }

  makeRequest() {
    this.update(this.result.id ?? 0, this.result.available, true);
  }

  unmakeRequest() {
    this.update(this.result.id ?? 0, this.result.available, false);
  }

  makeAvailable() {
    this.update(this.result.id ?? 0, true, this.result.required);
  }

  unmakeAvailable() {
    this.update(this.result.id ?? 0, false, this.result.required);
  }

  private update(id: number, available: boolean, required: boolean): void {
    this.bloodRegistryService.update(id, available, required).subscribe(
      {
        next: (response: HttpResponse<BloodRegistryEntity>) => {
          this.setResultFromResponse(response);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.headers.get('Message'));
        }
      }
    )
  }

  delete() {
    this.bloodRegistryService.delete(this.result.id!).subscribe(
      {
        next: () => {
          this.initResult();
          this.hasFailedSearch = true;
        },
        error: (error: HttpErrorResponse) => console.log(error.headers.get('Message'))
      }
    );
  }

  addAvailable() {
    if (this.result.id != null) this.makeAvailable();
    else this.add(true, false);
  }
  
  addRequest() {
    if (this.result.id != null) this.makeRequest();
    else this.add(false, true);
    this.hasSubmittedRequest = true;
  }

  private add(available: boolean, required: boolean) {
    let req: BloodRegistryEntity = this.reqObj();
    req.available = available;
    req.required = required;
    this.bloodRegistryService.add(req).subscribe(
      {
        next: (response: HttpResponse<BloodRegistryEntity>) => {
          this.setResultFromResponse(response);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error.headers.get('Message'));
        }
      }
    );
  }
  
  private reqObj(): BloodRegistryEntity {
    return {
      id: null,
      bloodGroup: this.formatter.frontToBack(this.bloodRegistryEntity.bloodGroup, this.formatter.bloodGroup),
      state: this.formatter.frontToBack(this.bloodRegistryEntity.state, this.formatter.state),
      area: this.formatter.frontToBack(this.bloodRegistryEntity.area, this.formatter.area),
      pinCode: this.bloodRegistryEntity.pinCode,
      required: false,
      available: false
    }
  };

  private setResultFromResponse(response: HttpResponse<BloodRegistryEntity>): void {
    this.result = response.body ?? this.result;
    this.result.bloodGroup = this.formatter.backToFront(this.result.bloodGroup, this.formatter.bloodGroup);
    this.result.state = this.formatter.backToFront(this.result.state, this.formatter.state);
    this.result.area = this.formatter.backToFront(this.result.area, this.formatter.area);
    console.log(this.result);
    if (this.result.available == true) this.hasFailedSearch = false;
    else this.hasFailedSearch = true;
  }

  private initResult(): void {
    this.result = {
      id: null,
      bloodGroup: '',
      state: '',
      area: '',
      pinCode: '',
      required: false,
      available: false
    };
  }
}
