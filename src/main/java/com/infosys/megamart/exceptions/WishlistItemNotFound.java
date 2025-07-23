package com.infosys.megamart.exceptions;

public class WishlistItemNotFound extends RuntimeException {
    public WishlistItemNotFound(String message) {
        super(message);
    }
}
