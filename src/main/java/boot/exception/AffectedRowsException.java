package boot.exception;
// 自定义数据库影响行数为0的异常
public class AffectedRowsException extends RuntimeException {
    // 无参构造方法
    public AffectedRowsException () {
        super("数据库中受影响的行数为0");
    }
    // 有参构造方法
    public AffectedRowsException (String message) {
        super(message);
    }
}
