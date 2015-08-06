package exceptions;

/**
 * Created by pramothinidk on 8/5/15.
 * API that allows to fix the exceptions
 */
public interface FixExceptions {
    public Object fix(int errorno, String errmsg);
    public boolean validator(String msg);
    public boolean checkIfListNameAlreadyExists(String listname);
}
