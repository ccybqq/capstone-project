import { UserEntity } from "./user-entity";

export interface UserAuth {
    password: string,
    userEntity: UserEntity
}
