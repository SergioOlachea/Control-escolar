package model.exception;

import java.sql.SQLException;

public class UniqueKeyViolationException extends SQLException{
	private final String keyName;
	
	public UniqueKeyViolationException(String reason, Throwable cause, String keyName) {
		super(reason, cause);
		this.keyName = keyName;
	}
	public String getKeyName() {
		return keyName;
	}
	
	public static UniqueKeyViolationException fromSQLException(SQLException e) {
        String key = null;
        String message = e.getMessage();
     
        if (e.getErrorCode() == 1062) {
            int keyIndex = message.indexOf("for key");
            if (keyIndex != -1) {
                int start = message.indexOf('\'', keyIndex);
                int end = message.indexOf('\'', start + 1);
                if (start != -1 && end != -1) {
                    key = message.substring(start + 1, end);
                }
            }
        }
        return new UniqueKeyViolationException(message, e, key);		
	}
}
