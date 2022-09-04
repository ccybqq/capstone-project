export interface BloodRegistryEntity {
    id: number | null,
    bloodGroup: string,
    state: string,
    area: string,
    pinCode: string,
    required: boolean,
    available: boolean
}
