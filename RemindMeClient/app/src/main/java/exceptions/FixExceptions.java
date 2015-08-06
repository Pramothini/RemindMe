package exceptions;

/**
 * Created by pramothinidk on 8/5/15.
 */
public interface FixExceptions {
    public Object fix(int errorno, String errmsg);
    public boolean validator(String msg);
}
