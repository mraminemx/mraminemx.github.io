package co.tenisu.atelierrestapp.exception;

public class BusinessException extends RuntimeException {

    /**
     * serial UUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Error code
     */
    private final BusinessErrorCode code;

    /**
     *
     * @param pCode
     */
    public BusinessException(BusinessErrorCode pCode) {
        super(pCode.toString());
        code = pCode;
    }

    /**
     * constructor
     *
     * @param pCode
     *            the business code of the error
     * @param e
     *            the related exception if any
     */
    public BusinessException(BusinessErrorCode pCode, Throwable e) {
        super(e);
        code = pCode;
    }

    public BusinessException(final BusinessErrorCode pCode, final String message) {
        super(message);
        code = pCode;
    }

    /**
     * Return the business code
     *
     * @return the code
     */
    public BusinessErrorCode getCode() {
        return code;
    }
}
