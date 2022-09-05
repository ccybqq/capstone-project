import { UserEntity } from '../object/user-entity';

export interface BookingEntity {
    id: number | null,
    userEntity: UserEntity,
    city: string,
    hospital: string,
    date: Date,
    slot: string
}
