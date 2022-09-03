import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormatterService {

  constructor() { }

  valueToKey(value: string, anEnum: any): string {
    let index = Object.values(anEnum).indexOf(value as unknown as typeof anEnum)
    return Object.keys(anEnum)[index];
  }
}
