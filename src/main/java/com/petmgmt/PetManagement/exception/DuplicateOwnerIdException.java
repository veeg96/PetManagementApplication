package com.petmgmt.PetManagement.exception;

import java.io.Serial;

public class DuplicateOwnerIdException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateOwnerIdException(String message) {
        super(message);
    }
}
