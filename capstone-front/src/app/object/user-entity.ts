import { Area } from "./area";
import { BloodGroup } from "./blood-group";
import { Gender } from "./gender";
import { State } from "./state";

export interface UserEntity {
    id: number,
    email: string,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
    age: number,
    gender: Gender,
    weight: number,
    bloodGroup: BloodGroup,
    contactNumber: String,
    state: State,
    area: Area,
    postalCode: number
}
