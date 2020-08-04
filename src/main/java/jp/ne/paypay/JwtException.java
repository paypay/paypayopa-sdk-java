package jp.ne.paypay;

public class JwtException extends Exception {

    private int code = 0;
    public JwtException() {}

    public JwtException(Throwable throwable) {
        super(throwable);
    }

    public JwtException(String message) {
        super(message);
    }

    public JwtException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getCode() {
        return code;
    }

}
