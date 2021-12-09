package com.techelevator.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InviteType {

    private Long receiverId;
    private Long inviteTypeId = 3L;
    private Long gameId;
    private Long senderId;


}
