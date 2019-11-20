package pl.lodz.p.spjava.exception;

public class DbException {

    public static class BadExecution extends AppBaseException
    {
        private static final long serialVersionUID = 3555714415375055302L;
        public BadExecution(String msg) {
            super(msg);
        }
    }
}
