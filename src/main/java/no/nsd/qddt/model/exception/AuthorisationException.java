package no.nsd.qddt.model.exception;

public class AuthorisationException extends RuntimeException {

   public AuthorisationException() {
   }

   public AuthorisationException(String msg) {
      super(msg);
   }
}
