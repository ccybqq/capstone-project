import { Area } from "./area";
import { BloodGroup } from "./blood-group";
import { Gender } from "./gender";
import { State } from "./state";

export interface UserEntity {
    id: number | null,
    email: string | null,
    firstName: string | null,
    lastName: string | null,
    dateOfBirth: string | null,
    age: number | null,
    gender: Gender | null,
    weight: number | null,
    bloodGroup: BloodGroup | null,
    contactNumber: String | null,
    state: State | null,
    area: Area | null,
    postalCode: number | null
}
