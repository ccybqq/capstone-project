import { UserEntity } from '../object/user-entity';

export interface BookingEntity {
    id: number | null,
    email: string,
    city: string,
    hospital: string,
    date: Date | null,
    bookingSlot: string
}
