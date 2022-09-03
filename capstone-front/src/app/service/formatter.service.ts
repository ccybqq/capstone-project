import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormatterService {
  public gender = new Map(
    [
      ['MALE', 'Male'],
      ['FEMALE', 'Female']
    ]
  );
  
  public bloodGroup = new Map(
    [
      ["AB_P", "AB+"],
      ["AB_N", "AB-"],
      ["A_P", "A+"],
      ["A_N", "A-"],
      ["B_P", "B+"],
      ["B_N", "B-"],
      ["O_P", "O+"],
      ["O_N", "O-"]
    ]
  );
  
  public state = new Map(
    [
      ['STATE_A', 'State A'],
      ['STATE_B', 'State B'],
      ['STATE_C', 'State C'],
    ]
  );
  
  public area = new Map(
    [
      ['AREA_1', 'Area 1'],
      ['AREA_2', 'Area 2'],
      ['AREA_3', 'Area 3'],
      ['AREA_4', 'Area 4'],
      ['AREA_5', 'Area 5']
    ]
  );

  constructor() { }

  frontToBack(value: string, map: Map<string, string>): string {
    for (let entry of map.entries()) {
      if (value === entry[1]) return entry[0];
    }
    return '';
  }

  backToFront(value: string, map: Map<string, string>): string {
    return map.get(value) ?? '';
  }
}
