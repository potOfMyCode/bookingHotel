package model.util;

public interface LogMsg {String ALL_MOVIES_RECEIVED = "All movies were received from the DB";
    String CANT_CLOSE_CONNECTION = "Couldn't close connection";
    String CANT_CREATE_MOVIE = "Couldn't create movie";
    String CANT_CREATE_SESSION = "Couldnt create session";
    String CANT_CREATE_TICKET = "SQL exception while creating";
    String CANT_CREATE_TICKET2 = "Failure while ticket was about to be bought";
    String CANT_DOWNLOAD_PICTURE = "Couldn't download picture while admin tried to add new movie";
    String CANT_FIND_SESSION ="Can't find session";
    String CANT_FIND_SUCH_USER = "Can't find such user";
    String CANT_GET_DAY_BY_ID = "cant get day by id";
    String CANT_REMOVE_MOVIE = "Couldn't remove movie";
    String CANT_REMOVE_SESSION = "Couldn't remove session";
    String CANT_REMOVE_TICKET = "Ticket wasn't removed";
    String CONNECTION_CLOSED = "Connection closer (returned to pool)";
    String CONNECTION_NOT_RECEIVED = "Connection not recieved!!!";
    String CONNECTION_POOL_CREATED = "Connection closer (returned to pool)";
    String DAO_LOCALE_IS_SET = "DAO locale is set up";
    String EXCEPTION_IN_PREPARED_STATEMENT_PROCESS = "Connection or prep statement threw an exception";
    String EXCEPTION_IN_PREPARED_STATEMENT = "Connection or prep statement threw an exception";
    String EXCEPTION_IN_ROLLBACK = "Exception in rollback";
    String EXCEPTION_WRITE_RESPONSE = "Exception occurred while wrote status message into a responce";
    String GOT_DAY_BY_ID_FROM_DB = "Received day from DB by its id";
    String INVALID_DATA_MOVIE_CREATION = "Invalid data from client while created new movie";
    String INVALID_DATA_SESSION_CREATION = "Invalid data from client while created new session";
    String MOVIE_CREATED = "Movie was created";
    String MOVIE_CREATED_SUCCESSFULLY = "Invalid data from client while created new movie";
    String MOVIE_REMOVED = "Movie was removed from DB";
    String NO_SUCH_ENTRY_IN_DB = "No such entry in the DB";
    String PREP_STAT_OPENED = "Prepared statement was opened";
    String PURCH_TICKET_BAD_INPUT_DATA = "Bad data from client while tried to purchase a ticket";
    String QUERY_EXECUTED = "SQL query executed successfully";
    String RECEIVED_SESSION_BY_ID = "Received session from DB by its id";
    String REGISTERED_SUCCESSFULLY = "User was registererd successfully";
    String REGISTER_ALREADY_EXISTS = "Tried to register but such user's data is already exists";
    String SESSION_CREATED_SUCCESSFULLY = "Session was created successfully";
    String SESSION_WAS_CREATED = "Session was created";
    String SESSION_WAS_REMOVED = "Session was removed from the DB";
    String SQL_EXCEPTION_WHILE_CREATE = "SQL exception while creating";
    String SQL_EXCEPTION_WHILE_CREATE_BILL_RESERVATION = "SQL exception while creating bill and reservation";
    String SQL_EXCEPTION_WHILE_DELETING = "SQL exception while deleting from DB";
    String SQL_EXCEPTION_WHILE_ROLLBACK = "SQL exception while rollback() query.";
    String SQL_EXCEPTION_WHILE_INSERT = "SQL exception while inserting into DB";
    String SQL_EXCEPTION_WHILE_READING_FROM_DB = "SQL exception while reading from DB";
    String SQL_EXCEPTION_WHILE_UPDATE = "SQL exception while updating";
    String SUCH_USER_ALREADY_EXISTS = "Such user already exists";
    String SUCH_ID_NOT_EXIST = "Such id of reserved room not exist";
    String TICKET_CREATED = "Ticket was created";
    String TICKET_CREATED_SUCCESSFULLY = "Ticket was bought successfully";
    String TICKET_PURCHASED_SUCCESSFULLY = "ticket_purchased_successfully";
    String TICKET_REMOVED = "Ticket was created";
    String TRIED_TO_AUTHORIZE_VIA_BAD_PASSWORD = "User tried to authorize via not his password";
    String USER_LOGGED_OUT = "User logged out";
    String USER_LOGGED_SUCCESSFULLY = "User logged successfully";
    String USER_WAS_LOGGED_IN = "User was logged in";
    String NO_ORDERS_FOR_THIS_USER = "No orders for this user";
}
