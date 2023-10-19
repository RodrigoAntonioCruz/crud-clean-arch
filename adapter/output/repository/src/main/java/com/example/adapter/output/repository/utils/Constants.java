package com.example.adapter.output.repository.utils;


public class Constants {

    private Constants() {
    }

    /**
     * LOG_KEY
     */
    public static final String LOG_KEY_METHOD = "method={} ";
    public static final String LOG_KEY_MESSAGE = "msg=\"{}\" ";

    public static final String LOG_KEY_CAUSE = "cause=\"{}\" ";
    public static final String LOG_KEY_ENTITY = "entity=\"{}\" ";
    public static final String LOG_KEY_ENTITY_ID = "entityId={} ";
    public static final String LOG_KEY_EVENT = "event={} ";

    public static final String LOG_KEY_FILTER = "filter={} ";
    public static final String LOG_KEY_DESCRIPTION = "description=\"{}\" ";
    public static final String LOG_KEY_HTTP_CODE = "httpCode={} ";


    /**
     * LOG_METHOD
     * */
    public static final Object LOG_METHOD_FIND_BY_FILTER = "findByFilter";
    public static final Object LOG_METHOD_DELETE_BY_ID = "deleteById";
    public static final String LOG_METHOD_FIND_USER_BY_ID = "findUserById";
    public static final String LOG_METHOD_SAVE = "save";
    public static final String LOG_METHOD_UPDATE = "update";
    public static final String LOG_METHOD_BUSINESS_EXCEPTION = "BusinessException";
    public static final String LOG_METHOD_BIND_EXCEPTION = "BindException";
    public static final String LOG_METHOD_CONSTRAINT_VIOLATION_EXCEPTION = "ConstraintViolationException";
    public static final String LOG_METHOD_CLIENT_ABORT_EXCEPTION = "ClientAbortException";
    public static final String LOG_METHOD_EMPTY_RESULT_DATA_ACCESS_EXCEPTION = "EmptyResultDataAccessException";
    public static final String LOG_METHOD_HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "HttpMessageNotReadableException";
    public static final String LOG_METHOD_HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = "HttpRequestMethodNotSupportedException";
    public static final String LOG_METHOD_IO_EXCEPTION = "IOException";
    public static final String LOG_METHOD_MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = "MissingServletRequestParameterException";
    public static final String LOG_METHOD_METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION = "MethodArgumentTypeMismatchException";
    public static final String LOG_METHOD_METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "MethodArgumentNotValidException";
    public static final String LOG_METHOD_THROWABLE = "Throwable";
    public static final String LOG_METHOD_NUMBER_FORMAT_EXCEPTION = "NumberFormatException";
    public static final String LOG_METHOD_USER_NOT_FOUND_EXCEPTION = "UserNotFoundException";
    public static final String LOG_METHOD_NOT_AVAILABLE_EXCEPTION = "not available";
    public static final String LOG_METHOD_CURRENT_TIMESTAMP = "dd/MM/yyyy HH:mm:ss";
    public static final String SHOULD_BE = " should be ";

    /**
     * LOG_OTHER
     */
    public static final String LOG_EXCEPTION = "exception={} ";
    public static final String X_RD_TRACEID = "X-rd-traceid";
    public static final String TRACE_ID_KEY = "traceId";
    public static final String DUPLICATION_CODE = "E11000";
    public static final String KEY_CPF = "cpf";
    public static final String KEY_EMAIL = "email";
    public static final String DUPLICATION_CPF = "Já existe um CPF cadastrado com esse número";
    public static final String DUPLICATION_EMAIL = "Já existe um e-mail cadastrado com esse endereço";
    public static final String CPF_NOT_NULL = "O CPF não pode ser nulo";
    public static final String CPF_MAX_LENGTH = "O tamanho do campo CPF deve ter somente 11 dígitos sem caracteres especiais";
    public static final String CPF_INVALID = "O CPF digitado é inválido";
    public static final String EMAIL_INVALID = "O e-mail digitado é inválido";
    public static final String EMAIL_NOT_NULL = "O e-mail não pode ser nulo";
    public static final String EMAIL_MAX_LENGTH = "O tamanho do campo e-mail deve ter somente até 120 caracteres";
    public static final String NAME_NOT_NULL = "O nome não pode ser nulo";
    public static final String NAME_MAX_LENGTH = "O tamanho do campo nome deve ter entre 3 e 120 caracteres";
}
